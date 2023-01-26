package com.samisezgin.finalproject;

import com.samisezgin.finalproject.model.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class KodluyoruzSolmazFinalProjectApplication {

//    private final RabbitTemplate template;
//
//    public KodluyoruzSolmazFinalProjectApplication(RabbitTemplate template) {
//        this.template = template;
//    }

    public static void main(String[] args) {
        SpringApplication.run(KodluyoruzSolmazFinalProjectApplication.class, args);
    }

//    @PostConstruct
//    public void sendMsg()
//    {
//        template.convertAndSend("notification", new Notification("msg", "EMAIL","sami@sami.com"));
//        template.convertAndSend("notification", new Notification("msg", "PUSH","mesirmacunu"));
//        template.convertAndSend("notification", new Notification("msg", "SMS","09008883169"));
//    }

}
