package com.berko.crypto.repository;

import com.berko.crypto.http.HttpClient;
import com.berko.crypto.model.Price;
import com.berko.crypto.model.AddressInfo;


import com.berko.crypto.model.TransactionInfo;
import com.berko.crypto.utils.TimeUtils;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import info.blockchain.api.APIException;
import info.blockchain.api.blockexplorer.BlockExplorer;
import info.blockchain.api.blockexplorer.entity.Address;
import info.blockchain.api.blockexplorer.entity.Input;
import info.blockchain.api.blockexplorer.entity.Output;
import info.blockchain.api.blockexplorer.entity.Transaction;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

@Repository
public class CryptoCoinRepo {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private HttpClient httpClient;
    Datastore datastore = null;

    public CryptoCoinRepo() {
        httpClient = new HttpClient();
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://berkoab:Gemorah1@" +
                "bitcoinprices-shard-00-00-avqra.mongodb.net:27017," +
                "bitcoinprices-shard-00-01-avqra.mongodb.net:27017," +
                "bitcoinprices-shard-00-02-avqra.mongodb.net:27017" +
                "/test?ssl=true&replicaSet=bitcoinprices-shard-0&authSource=admin"));

        Morphia morphia = new Morphia();
        morphia.mapPackage("com.berko.crypto.model");
        datastore = morphia.createDatastore(mongoClient, "bitcoin");
        datastore.ensureIndexes();
    }

    public Address getInfo(String add) {
        BlockExplorer blockExplorer = new BlockExplorer();
        Address address = null;
        try {
            address = blockExplorer.getAddress(add);
        } catch (APIException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }

    public List<AddressInfo> getAddressInfo(String add, String fromDate, String toDate, String currency) {
        BlockExplorer blockExplorer = new BlockExplorer();

        List<AddressInfo> singleTransactions = new ArrayList<AddressInfo>();
        try {
            Address address = blockExplorer.getAddress(add);
            List<Transaction> transactions = address.getTransactions();
            for (Transaction tx : transactions) {
                long time = tx.getTime();
                if(TimeUtils.timeToDate(time).compareTo(TimeUtils.stringToDate(fromDate))<0
                        ||TimeUtils.timeToDate(time).compareTo(TimeUtils.stringToDate(toDate))>0) {
                    continue;
                }

                AddressInfo transact = new AddressInfo();
                transact.setTransactionId(tx.getHash());
                transact.setAddress(add);
                transact.setTime(time);
                transact.setDate(TimeUtils.timeToString(time));
                for (Input in : tx.getInputs()) {
                    if(in.getPreviousOutput().getAddress().equals(add)) {
                        transact.getFromAddresses().clear();
                        transact.getFromAddresses().add(add);
                        transact.setDirection(AddressInfo.Direction.OUTGOING);
                        setAmounts(transact, in.getPreviousOutput().getValue(), time, currency);
                        break;
                    } else {
                        transact.getFromAddresses().add(in.getPreviousOutput().getAddress());
                    }
                }

                for (Output out : tx.getOutputs()) {
                    if(out.getAddress().equals(add)) {
                        transact.setDirection(AddressInfo.Direction.INCOMING);
                        setAmounts(transact, out.getValue(), time, currency);
                        transact.getToAddresses().clear();
                        transact.getToAddresses().add(out.getAddress());
                        break;
                    } else {
                        transact.getToAddresses().add(out.getAddress());
                    }
                }
                singleTransactions.add(transact);
            }
            return singleTransactions;
        } catch (APIException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TransactionInfo getTransactionInfo(String hash, String currency) {
        BlockExplorer blockExplorer = new BlockExplorer();
        TransactionInfo transact = new TransactionInfo();
        try {
            Transaction tx = blockExplorer.getTransaction(hash);

            transact.setTransactionId(tx.getHash());
            long time = tx.getTime();
            transact.setTime(time);
            transact.setDate(TimeUtils.timeToString(time));
            for (Input in : tx.getInputs()) {
                Output output = in.getPreviousOutput();
                double amount = (double)output.getValue() / 100000000;
                transact.getOutgoingBitcoin().put(output.getAddress(), amount);
                transact.getOutgoingCurrency().put(output.getAddress(),  getPrice(time, currency)*amount);
            }
            for(Output out : tx.getOutputs()) {
                double amount = (double)out.getValue() / 100000000;
                transact.getIncomingBitcoin().put(out.getAddress(), amount);
                transact.getIncomingCurrency().put(out.getAddress(),  getPrice(time, currency)*amount);
            }
        } catch (APIException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transact;
    }

    private void setAmounts(AddressInfo transact, long btc, long time, String currency) {
        double amount = (double)btc / 100000000;
        transact.setTransactionAmount(amount);

        double price = getPrice(time, currency);
        transact.setHistoricalTransactAmount(price*amount);
    }

    private double getPrice(long time, String currency) {
        List<Price> prices = datastore.createQuery(Price.class)
                .field("date").equal(TimeUtils.timeToDate(time))
                .field("currency").equal(currency)
                .asList();
        if(prices.size()>0) {
            return prices.get(0).getPrice();
        } else {
            double price = httpClient.getHistoricalPrice(time, currency, TimeUtils.timeToString(time));
            writePriceToMongo(currency, TimeUtils.timeToDate(time), price);
            return price;
        }
    }

    private void writePriceToMongo(String currency, Date date, double pr) {
        Price price = new Price();
        price.setCurrency(currency);
        price.setDate(date);
        price.setPrice(pr);
//        collection.insertOne(price);
        datastore.save(price);
    }
}
