package com.innova.smsservice.controller;

import com.innova.smsservice.business.dto.SmsDto;
import com.innova.smsservice.business.services.impl.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Base controller class for all sms operations.
 *
 * @author Ahmet AKAN
 */

@RestController
@RequestMapping("/api/sms-service")
public class SmsController {

    @Autowired
    private SmsService smsService;


    @PostMapping
    public ResponseEntity<Boolean> sendSms(@Valid @RequestBody SmsDto smsDto) {
        return ResponseEntity.ok(smsService.sendSms(smsDto));
    }

}
