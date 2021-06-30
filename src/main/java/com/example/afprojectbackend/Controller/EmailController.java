package com.example.afprojectbackend.Controller;

import com.example.afprojectbackend.Model.EmailConfiguration;
import com.example.afprojectbackend.Service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://af-icaf-frontend.azurewebsites.net")
@RequestMapping("/api/sendEmails")
public class EmailController {

    private final InformationService informationService;

    @Autowired
    private EmailConfiguration emailConfiguration;

    public EmailController(InformationService informationService) {
        this.informationService = informationService;
    }

    //for multiple emails sending at one time
    @PostMapping("/Emails/{conferenceId}/{subject}/{emailBody}")
    public void sendToAllAttendee(@PathVariable String conferenceId, @PathVariable String subject, @PathVariable String emailBody){

        //get the all user emails according a conference
        List<String> emails = informationService.getEmailAddressesByConference(conferenceId);

        for (String email: emails){
            //create mail sender
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost(this.emailConfiguration.getHost());
            mailSender.setPort(this.emailConfiguration.getPort());
            mailSender.setUsername(this.emailConfiguration.getUsername());
            mailSender.setPassword(this.emailConfiguration.getPassword());

            //create an email instance
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("it19184036@my.sliit.lk");
            mailMessage.setTo(email);
            mailMessage.setSubject(subject);
            mailMessage.setText(emailBody);

            //send E-mail
            mailSender.send(mailMessage);
        }
    }

    //for single email sending
    @PostMapping("/Email/{email}/{subject}/{emailBody}")
    public void sendToEditor(@PathVariable String email, @PathVariable String subject, @PathVariable String emailBody) {

        //create mail sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailConfiguration.getHost());
        mailSender.setPort(this.emailConfiguration.getPort());
        mailSender.setUsername(this.emailConfiguration.getUsername());
        mailSender.setPassword(this.emailConfiguration.getPassword());

        //create an email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("it19184036@my.sliit.lk");
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(emailBody);

        //send E-mail
        mailSender.send(mailMessage);
    }
}
