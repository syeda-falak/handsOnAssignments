package com.example.currencyconvertion.utils;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class RestCallService {
    

    public String conversionAPIMethod(String url) throws  Exception {
        HttpHeaders headers = new HttpHeaders();// 0EO3UheNFmCdv5faA0bmBoFM35o7JKkP
        headers.add("apiKey","nPN6J5BIeOkG9sQxwSvPhmSCdBeGn50R");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate=new RestTemplate();
        String response=restTemplate.exchange(url, HttpMethod.GET,entity, String.class).getBody();
        return response;

    }
}
