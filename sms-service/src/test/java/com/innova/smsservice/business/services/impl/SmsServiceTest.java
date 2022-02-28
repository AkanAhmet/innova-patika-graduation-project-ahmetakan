package com.innova.smsservice.business.services.impl;

import com.innova.smsservice.business.dto.SmsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SmsServiceTest {

    @Mock
    private TwilioSmsSender mockSmsSender;

    private SmsService smsServiceUnderTest;

    @BeforeEach
    void setUp() {
        smsServiceUnderTest = new SmsService(mockSmsSender);
    }


    @Test
    void testAddCountryCodetoPhoneNumber() {
        assertThat(smsServiceUnderTest.addCountryCodetoPhoneNumber("phoneNumber")).isEqualTo("+90phoneNumber");
    }
}
