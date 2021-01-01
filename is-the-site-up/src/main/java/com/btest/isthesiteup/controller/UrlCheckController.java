package com.btest.isthesiteup.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/base")
public class UrlCheckController {

    private final String SITE_IS_UP = "Site is up";
    private final String SITE_IS_DOWN = "Site is down";
    private final String INCORRECT_URL = "Url is incorrect";

    @RequestMapping(value = "/check", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<String> getUrlStatusMessage(@RequestParam String url) {
        String finalResponse = "";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode() / 100;
            if (responseCode != 2 || responseCode != 3)
                finalResponse = SITE_IS_DOWN;
            else
                finalResponse = SITE_IS_UP;
        } catch (MalformedURLException e) {
            finalResponse = INCORRECT_URL;
        } catch (IOException e) {
            finalResponse = SITE_IS_DOWN;
        }
        return ResponseEntity.ok().body(finalResponse);
    }
    
}
