package com.example.afprojectbackend.Controller;

import com.example.afprojectbackend.Model.EmailConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/sendemails")
public class EmailController {

    @Autowired
    private EmailConfiguration emailConfiguration;

//    List<String> emails = new ArrayList<>();
//    List<String> Emails = new ArrayList<String>();


    @PostMapping("/Emails")
    public void sendToAllAttendee(@RequestBody String[] Emails){

        for (int i=0; i<Emails.length; i++){
            //create mail sender
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost(this.emailConfiguration.getHost());
            mailSender.setPort(this.emailConfiguration.getPort());
            mailSender.setUsername(this.emailConfiguration.getUsername());
            mailSender.setPassword(this.emailConfiguration.getPassword());

            //create an email instance
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("it19184036@my.sliit.lk");
            mailMessage.setTo(Emails[i]);
            mailMessage.setSubject("Conference details updated");
            mailMessage.setText("Conference details changed please check from the site. Thank You!!!");

            //send E-mail
            mailSender.send(mailMessage);
        }
    }

    @PostMapping("/Email")
    public void sendToEditor(@RequestBody String email) {

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
        mailMessage.setSubject("Conference details updated");
        mailMessage.setText("Conference details changed please check from the site. Thank You!!!");

        //send E-mail
        mailSender.send(mailMessage);
    }
}
