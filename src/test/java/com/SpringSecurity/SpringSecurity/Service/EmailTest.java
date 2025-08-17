package com.SpringSecurity.SpringSecurity.Service;

import com.SpringSecurity.SpringSecurity.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class EmailTest {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail() {
        emailService.sendEmail("bhupalprajapati12@gmail.com", "Testing java mail", "How are you?");
    }

}
