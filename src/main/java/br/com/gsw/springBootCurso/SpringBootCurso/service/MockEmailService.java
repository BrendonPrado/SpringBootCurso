package br.com.gsw.springBootCurso.SpringBootCurso.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public class MockEmailService extends AbstractEmailService {

    private static final Logger log = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        log.info( "Simulando envio de email" );
        log.info( msg.toString());
        log.info( "Email Enviado" );
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        log.info( "Simulando envio de email" );
        log.info( msg.toString());
        log.info( "Email Enviado" );
    }
}
