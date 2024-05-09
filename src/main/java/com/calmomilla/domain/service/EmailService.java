package com.calmomilla.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String remetente;


    public String enviarEmailTexto(String destinatario, String assunto, String mensagem){

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(mensagem);
            mailSender.send(simpleMailMessage);
            return "email enviado";
        }catch (Exception e){
            return "Erro ao enviar email "+e.getMessage();
        }

    }

}
