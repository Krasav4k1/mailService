package com.example;

import com.example.service.MailService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.subethamail.wiser.Wiser;

import javax.mail.MessagingException;

/**
 * Tests for Sending email
 *
 * @author Andrii Blyzniuk
 */
@TestPropertySource(properties = {
        "mail.host = localhost",
        "mail.port = 1025",
        "mail.protocol = smtp",
        "mail.user-name = Krasav4k1@gmail.com",
        "mail.password = @@@Krasav4k@@@",
        "mail.auth = true",
        "mail.starttls-enable = false",
        "mail.check-server-identity = false",
        "mail.starttls-required = false",
        "mail.trust = *",
})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MailServiceTest {

    private Wiser wiser;

    @Autowired
    private MailService mailService;

    @Before
    public void setUp() {
        wiser = new Wiser();
        wiser.setHostname("localhost");
        wiser.setPort(1025);
        wiser.start();
    }

    @After
    public void tearDown() {
        wiser.stop();
    }

    @Test
    public void sendMassage() throws MessagingException {
        mailService.sendMessageToEmail("Krasav4k1@gmail.com", "Registration", "text");

        WiserAssertions
                .assertReceivedMessage(wiser)
                .from("Krasav4k1@gmail.com")
                .to("Krasav4k1@gmail.com")
                .withSubject("Registration")
                .withContent("text");
    }
}