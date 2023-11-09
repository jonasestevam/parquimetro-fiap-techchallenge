package com.jonasestevam.parquimetro;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;

import com.jonasestevam.parquimetro.dtos.ReceiptDTO;
import com.jonasestevam.parquimetro.models.OngoingParkSession;

@Component
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        this.rabbitTemplate = rabbitTemplate;

    }

    public void notifySessionFinished(ReceiptDTO receipt) {
        Map<String, String> message = new HashMap<>();
        message.put("Sessão Finalizada!", receipt.toString());
        rabbitTemplate.convertAndSend("parquimetroQueue", message);
    }

    public void notifySessionAlmostEnding(OngoingParkSession ongoingParkSession) {
        Map<String, String> message = new HashMap<>();
        message.put("Seu tempo de estacionamento está quase acabando!", ongoingParkSession.toString());
        rabbitTemplate.convertAndSend("parquimetroQueue", message);
    }

    public void notifySessionAlmostEndingVariableTime(OngoingParkSession ongoingParkSession) {
        Map<String, String> message = new HashMap<>();
        message.put("Em 10 minutos estenderemos o seu tempo de estacionamento em 1 hora.",
                ongoingParkSession.toString());
        rabbitTemplate.convertAndSend("parquimetroQueue", message);
    }
}
