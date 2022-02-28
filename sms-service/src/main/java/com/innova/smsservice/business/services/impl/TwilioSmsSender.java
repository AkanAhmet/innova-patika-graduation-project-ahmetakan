package com.innova.smsservice.business.services.impl;

import com.innova.smsservice.business.dto.SmsDto;
import com.innova.smsservice.business.services.SmsSender;
import com.innova.smsservice.config.TwilioConfiguration;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation class of the {@link SmsSender} interface.
 *
 * @author Ahmet AKAN
 */

@Service("twilio")
@Log4j2
public class TwilioSmsSender implements SmsSender {

    @Autowired
    TwilioConfiguration twilioConfiguration;

    @Override
    public Boolean sendSms(SmsDto smsDto) {

        if (!smsDto.getPhoneNumber().isBlank()) {

            PhoneNumber to = new PhoneNumber(smsDto.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getPhoneNumber());
            String message = smsDto.getMessage();
            MessageCreator creator = Message.creator(to, from, "message");
                                                          /**   ^: Long messages cant be sended because of sms filtering, I'm trying keep message short ^^ */
            try {
                /** Sending sms here, usually sms cant be sended, because Türkiye wants Alphanumeric-Sender-ID, for receive sms from other countries */
                /** https://support.twilio.com/hc/en-us/articles/223133767-International-support-for-Alphanumeric-Sender-ID */
                creator.create();
                log.info("Sms sent {}", smsDto);
            } catch (Exception exception) {
                log.error(exception);
                return false;
            }

        } else {
            throw new IllegalArgumentException(
                    "Phone number [" + smsDto.getPhoneNumber() + "] is not a valid number"
            );

        }
        return true;
    }
}
