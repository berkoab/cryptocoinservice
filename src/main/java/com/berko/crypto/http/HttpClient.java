package com.berko.crypto.http;

import com.berko.crypto.model.CoinDesk;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class HttpClient {

    private final String BLOCKCHAIN_ADRESS = "https://blockchain.info/address/";
    private final String COINDESK_ADDRESS = "https://api.coindesk.com/v1/bpi/";

    public Double getHistoricalPrice(long time, String currency, String date) {

        CloseableHttpClient httpclient = null;
        httpclient = HttpClients.createDefault();

        HttpGet httpget = new HttpGet(COINDESK_ADDRESS+"historical/close.json?start="+date+"&end="+date+"&currency="+currency);

        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            @Override
            public String handleResponse(
                    final HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }

        };
        String responseBody = null;
        try {
            responseBody = httpclient.execute(httpget, responseHandler);
            CoinDesk coindesk = new Gson().fromJson(responseBody, CoinDesk.class);
            Double price = coindesk.getBpi().get(date);
            return price;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAddressInfo(String address) {
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(BLOCKCHAIN_ADRESS+address+"?format=json");

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            return responseBody;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
