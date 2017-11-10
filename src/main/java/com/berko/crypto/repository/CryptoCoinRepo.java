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

//    public AddressInfo getInfo(String address) {
//        HttpClient client = new HttpClient();
//        AddressInfo info = new Gson().fromJson(client.getAddressInfo(address), AddressInfo.class);
//
//        List<Tx> transactions = info.getTxs();
//        for(Tx tx:transactions) {
//            SingleTransaction transaction = new SingleTransaction();
//            List<Input> inputs = tx.getInputs();
//            List<Out> outs = tx.getOut();
//            for(Input in:inputs) {
//                String inAddress = in.getPrevOut().getAddr();
//                Long value = in.getPrevOut().getValue();
//            }
//            for(Out out:outs) {
//                String outAddress = out.getAddr();
//                Long outValue = out.getValue();
//            }
//        }
//        return new Gson().fromJson(client.getAddressInfo(address), AddressInfo.class);
//    }

    public List<SingleTransaction> getInfo(String add) {
        BlockExplorer blockExplorer = new BlockExplorer();
        List<SingleTransaction> singleTransactions = new ArrayList<SingleTransaction>();
        try {
            Address address = blockExplorer.getAddress(add);
            List<Transaction> transactions = address.getTransactions();
            for (Transaction tx : transactions) {
                SingleTransaction transact = new SingleTransaction();
                transact.setAddress(add);
                List<Input> inputs = tx.getInputs();
                List<Output> outs = tx.getOutputs();
                for (Input in : inputs) {
                    if(in.getPreviousOutput().getAddress().equals(add)) {
                        transact.setDirection(SingleTransaction.Direction.OUTGOING);
                        transact.setTransactionAmount(in.getPreviousOutput().getValue() / 100000000);
                    }
                }

                if(transact.getDirection()==null) {
                    transact.setDirection(SingleTransaction.Direction.INCOMING);
                    for (Output out : outs) {
                        if(out.getAddress().equals(add)) {
                            transact.setTransactionAmount(out.getValue()/100000000);
                        }
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
