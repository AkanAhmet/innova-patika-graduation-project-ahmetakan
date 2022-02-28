package com.innova.smsservice.business.services;


import com.innova.smsservice.business.dto.SmsDto;

public interface SmsSender {

    Boolean sendSms(SmsDto smsDto);

}
