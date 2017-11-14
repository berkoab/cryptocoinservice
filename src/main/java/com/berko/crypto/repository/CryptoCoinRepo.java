package com.berko.crypto.repository;

//import com.berko.crypto.model.*;
//import com.berko.crypto.model.AddressInfo;
import com.berko.crypto.model.SingleTransaction;
        import info.blockchain.api.APIException;
import info.blockchain.api.blockexplorer.BlockExplorer;
import info.blockchain.api.blockexplorer.entity.Address;
import info.blockchain.api.blockexplorer.entity.Input;
import info.blockchain.api.blockexplorer.entity.Output;
import info.blockchain.api.blockexplorer.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CryptoCoinRepo {

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

    public List<SingleTransaction> getTransactionInfo(String add, long fromDate, long toDate) {
        BlockExplorer blockExplorer = new BlockExplorer();
        List<SingleTransaction> singleTransactions = new ArrayList<SingleTransaction>();
        try {
            Address address = blockExplorer.getAddress(add);
            List<Transaction> transactions = address.getTransactions();
            for (Transaction tx : transactions) {
                if(tx.getTime()<fromDate||tx.getTime()>toDate) {
                    continue;
                }
                SingleTransaction transact = new SingleTransaction();
                transact.setAddress((add));
                transact.setTime(tx.getTime());
                for (Input in : tx.getInputs()) {
                    if(in.getPreviousOutput().getAddress().equals(add)) {
                        transact.getFromAddresses().clear();
                        transact.getFromAddresses().add(add);
                        transact.setDirection(SingleTransaction.Direction.OUTGOING);
                        transact.setTransactionAmount((double)in.getPreviousOutput().getValue() / 100000000);
                        break;
                    } else {
                        transact.getFromAddresses().add(in.getPreviousOutput().getAddress());
                    }
                }

                for (Output out : tx.getOutputs()) {
                    if(out.getAddress().equals(add)) {
                        transact.setDirection(SingleTransaction.Direction.INCOMING);
                        transact.setTransactionAmount((double)out.getValue()/100000000);
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
}
