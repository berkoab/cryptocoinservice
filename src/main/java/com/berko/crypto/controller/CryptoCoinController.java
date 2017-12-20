package com.berko.crypto.controller;

import com.berko.crypto.model.AddressInfo;
import com.berko.crypto.model.TransactionInfo;
import com.berko.crypto.repository.CryptoCoinRepo;
import com.berko.crypto.Response;
import info.blockchain.api.blockexplorer.entity.Address;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Basic Spring web service controller that handles all GET requests.
 */
@RestController
@RequestMapping("/")
public class CryptoCoinController {
    @Resource
    private CryptoCoinRepo repo;

    private static final String MESSAGE_FORMAT = "Hello %s!";

    @RequestMapping(method = RequestMethod.GET)
    public Response helloWorldGet(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Response(String.format(MESSAGE_FORMAT, name));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Response helloWorldPost(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Response(String.format(MESSAGE_FORMAT, name));
    }

    @RequestMapping(value="/info/{address}", method = RequestMethod.GET)
    @ResponseBody
    public List<AddressInfo> getAddressInfo(@PathVariable String address,
                                            @RequestParam(value = "fromDate", defaultValue = "1970-01-01") String fromDate,
                                            @RequestParam(value = "toDate", defaultValue = "2030-12-31") String toDate,
                                            @RequestParam(value = "currency", defaultValue = "USD") String currency,
                                            HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Origin", "*");
        return repo.getAddressInfo(address, fromDate, toDate, currency);
    }

    @RequestMapping(value="/tx/{hash}", method = RequestMethod.GET)
    @ResponseBody
    public TransactionInfo getTransactionInfo(@PathVariable String hash,
                                              @RequestParam(value = "currency", defaultValue = "USD") String currency,
                                              HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Origin", "*");
        return repo.getTransactionInfo(hash, currency);
    }

    @RequestMapping(value="/info/{address}/all", method = RequestMethod.GET)
    @ResponseBody
    public Address getAddressInfo(@PathVariable String address) {
        return repo.getInfo(address);
    }
}