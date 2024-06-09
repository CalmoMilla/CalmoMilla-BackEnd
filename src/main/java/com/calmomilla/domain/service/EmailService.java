package com.calmomilla.domain.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class EmailService {

    private JavaMailSender mailSender;


    public Boolean enviarEmailDeBoasVindas(String destinatario, String assunto) {
        try {
            // Cria o objeto MimeMessage
            // Lê o conteúdo do arquivo HTML
            ClassPathResource htmlResource = new ClassPathResource("static/boasVindas.html");
            String corpoHtml = new String(Files.readAllBytes(Paths.get(htmlResource.getURI())), "UTF-8");

            // Substitui o marcador da imagem pelo identificador inline
            corpoHtml = corpoHtml.replace("${destinatario}", destinatario);
            corpoHtml = corpoHtml.replace("${imagem1}", "cid:imagem1");
            corpoHtml = corpoHtml.replace("${imagem2}", "cid:imagem2");
            corpoHtml = corpoHtml.replace("${imagem3}", "cid:imagem3");
            corpoHtml = corpoHtml.replace("${imagem4}", "cid:imagem4");


            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(corpoHtml, true); // Configura para enviar como HTML

            // Adiciona a imagem inline
            helper.addInline("imagem1", new ClassPathResource("static/img/LogoCalmomilla.png"));
            helper.addInline("imagem2", new ClassPathResource("static/img/CalmoMillaLg.png"));
            helper.addInline("imagem3", new ClassPathResource("static/img/instagram.png"));
            helper.addInline("imagem4", new ClassPathResource("static/img/icone-linkedin-ronde-noire.png"));


            mailSender.send(message);
            System.out.println("Email enviado com sucesso para: " + destinatario);

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean enviarEmailDeRecuperarSenha(String destinatario, String assunto) {
        try {
            // Cria o objeto MimeMessage
            // Lê o conteúdo do arquivo HTML
            ClassPathResource htmlResource = new ClassPathResource("static/redefinirSenha.html");
            String corpoHtml = new String(Files.readAllBytes(Paths.get(htmlResource.getURI())), "UTF-8");

            // Substitui o marcador da imagem pelo identificador inline
            corpoHtml = corpoHtml.replace("${destinatario}", destinatario);
            corpoHtml = corpoHtml.replace("${imagem1}", "cid:imagem1");
            corpoHtml = corpoHtml.replace("${imagem2}", "cid:imagem2");

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(corpoHtml, true); // Configura para enviar como HTML

            // Adiciona a imagem inline
            helper.addInline("imagem2", new ClassPathResource("static/img/CalmoMillaLg.png"));
            helper.addInline("imagem1", new ClassPathResource("static/img/LogoCalmomilla.png"));

            mailSender.send(message);
            System.out.println("Email enviado com sucesso para: " + destinatario);

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
