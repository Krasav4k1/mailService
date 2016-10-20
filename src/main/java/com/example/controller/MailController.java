package com.example.controller;

import com.example.service.MailService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

/**
 * Mail Controller fot send mail to user
 *
 * @author Andrii Blyzniuk
 */

@RestController
public class MailController {

    @Autowired
    MailService mailService;

    @RequestMapping(value = "/api/{receiverEmail}/{subject}/{text}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String sendEmailGET(@PathVariable("receiverEmail") String receiverEmail,
                               @PathVariable("subject") String subject,
                               @PathVariable("text") String text) throws MessagingException {
        mailService.sendMessageToEmail(receiverEmail, subject, text);
        return "Successfully sent to " + receiverEmail + " with the theme " + subject;
    }

    @RequestMapping(value = "/api/send/mail", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String sendEmailPOST(@RequestBody String jsonRequest) throws MessagingException {
        JSONObject data = new JSONObject(jsonRequest);
        mailService.sendMessageToEmail(data.getString("receiverEmail"), data.getString("subject"), data.getString("text"));
        return data.toString();
    }

}
