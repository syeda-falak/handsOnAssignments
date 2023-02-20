package com.example.currencyconvertion.service;

import org.springframework.stereotype.Service;

public interface AuditInfoService {
    String calculateCurrency(int amount) throws Exception;
}
