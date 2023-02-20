package com.example.currencyconvertion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.currencyconvertion.service.AuditInfoService;

@RestController
@RequestMapping("/currency")
public class RequestController {

    @Autowired
    AuditInfoService auditInfoService;

    @GetMapping("/currencyConversion")
    public ResponseEntity<String> convertingValues(@RequestParam int amount) throws Exception {
        return ResponseEntity.ok(this.auditInfoService.calculateCurrency(amount));
    }
}
