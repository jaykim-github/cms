package com.zerobase.cms.domain.service;


import com.zerobase.cms.user.client.MailgunClient;
import com.zerobase.cms.user.client.mailgun.SendMailForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailSendTest {
    @Autowired
    private MailgunClient mailgunClient;

    @Test
    public void EmailTest(){
        SendMailForm form = SendMailForm.builder()
            .from("zerobase@test.com")
            .to("dmb04262@naver.com")
            .subject("Test email")
            .text("test for project")
            .build();

        System.out.println( mailgunClient.sendEmail(form).getBody());
    }
}
