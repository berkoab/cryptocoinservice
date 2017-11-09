package com.berko.crypto.controller;

import com.berko.crypto.model.AddressInfo;
import com.berko.crypto.repository.CryptoCoinRepo;
import com.berko.crypto.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    public AddressInfo getAddressInfo(@PathVariable String address) {
        return repo.getInfo(address);
    }
}