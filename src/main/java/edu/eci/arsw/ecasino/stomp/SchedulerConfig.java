package edu.eci.arsw.ecasino.stomp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration
public class SchedulerConfig {

    @Autowired
    SimpMessagingTemplate template;

    /*@Scheduled(fixedDelay = 6000)
    public void sendAdhocMessages() {
        template.convertAndSend("/topic/newgame{channel}", new RouletteResponse("El tiempo no funciona asi",0,"null",null));
    }*/
}
