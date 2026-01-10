package net.digest.journalApp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;


@ExtendWith(MockitoExtension.class)
public class EmailServiceTests {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Disabled
    @Test
    void sendMailTest() {
        Assertions.assertTrue(emailService.sendMail(
                "sudipmondal7205@outlook.com",
                "Testing Java Mail Sender",
                "Hi"
        ));
    }
}
