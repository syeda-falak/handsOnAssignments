package com.example.currencyconvertion.service;

import java.io.*;
import okhttp3.*;
import okio.BufferedSink;

public class Sample {
  public static void main(String []args) throws IOException{
    OkHttpClient client = new OkHttpClient().newBuilder().build();

    Request request = new Request.Builder()
      .url("https://api.apilayer.com/exchangerates_data/convert?to={to}&from={from}&amount={amount}")
      .addHeader("apikey", "{API-KEY}")
      .build();
    Response response = client.newCall(request).execute();
    System.out.println(response.body().string());
  }
}