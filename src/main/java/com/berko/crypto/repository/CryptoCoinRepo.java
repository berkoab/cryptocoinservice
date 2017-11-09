package com.berko.crypto.repository;

import com.berko.crypto.http.HttpClient;
import com.berko.crypto.model.AddressInfo;
import com.google.gson.Gson;
import org.springframework.stereotype.Repository;

@Repository
public class CryptoCoinRepo {

    public AddressInfo getInfo(String address) {
        HttpClient client = new HttpClient();
        return new Gson().fromJson(client.getAddressInfo(address), AddressInfo.class);
    }
}
