package com.calmomilla.domain.service;


import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private JavaMailSender mailSender;

    public Boolean enviarEmailDeBoasVindas(String destinatario, String assunto){
        String corpoHtml = "<html><body style=\"font-family: Arial, sans-serif; background-color: #f4f4f4;\">"
                + "<div style=\"max-width: 600px; margin: 0 auto; padding: 20px;\">"
                + "<h1 style=\"color: red; text-align: center;\">Olá, " + destinatario + "!</h1>"
                + "<p style=\"color: #666666;\">Seja bem-vindo ao nosso aplicativo.</p>"
                + "<p style=\"color: #666666;\">Estamos muito felizes em tê-lo conosco.</p>"
                + "<p style=\"color: #666666;\">Aqui está uma imagem de boas-vindas:</p>"
                + "<img src=\"cid:imagem1\" style=\"max-width: 100%; display: block; margin: 0 auto;\">"
                + "</div>"
                + "</body></html>";
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(corpoHtml, true); // Configura para enviar como HTML

            // Anexa a imagem de exemplo (utilize uma URL de imagem válida)
            helper.addInline("imagem1", new ClassPathResource("static/logo.png"));

            mailSender.send(message);
            System.out.println("Email enviado com sucesso para: " + destinatario);

            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false ;
        }

    }

}
