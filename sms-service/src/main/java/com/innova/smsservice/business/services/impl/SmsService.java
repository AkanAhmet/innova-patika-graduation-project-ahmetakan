package com.innova.smsservice.business.services.impl;

import com.innova.smsservice.business.dto.SmsDto;
import com.innova.smsservice.business.services.SmsSender;
import com.innova.smsservice.util.SmsMessageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private final SmsSender smsSender;

    SmsMessageGenerator smsMessageGenerator;

    @Autowired
    public SmsService(@Qualifier("twilio") TwilioSmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public Boolean sendSms(SmsDto smsDto) {

        if (smsDto.getPhoneNumber().length() == 10) {
            smsDto.setPhoneNumber(addCountryCodetoPhoneNumber(smsDto.getPhoneNumber()));
        }

        smsMessageGenerator = SmsMessageGenerator.builder()
                .firstName(smsDto.getFirstName())
                .lastname(smsDto.getLastname())
                .loanApplicationDate(smsDto.getLoanApplicationDate())
                .loanLimit(smsDto.getLoanLimit())
                .loanApplicationStatus(smsDto.getLoanApplicationStatus())
                .build();

        smsDto.setMessage(smsMessageGenerator.generateSmsMessage());

        return smsSender.sendSms(smsDto);
    }

    public String addCountryCodetoPhoneNumber(String phoneNumber) {
        return "+90" + phoneNumber;
    }
}
