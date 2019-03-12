package br.com.gsw.springBootCurso.SpringBootCurso.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailService {
    @Autowired
    private MailSender sender;

    @Autowired
    JavaMailSender javaMailSender;

    private static Logger log = LoggerFactory.getLogger( SmtpEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        sender.send( msg );
        log.info( "Email enviado" );
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        javaMailSender.send( msg );
        log.info( "Email enviado" );
    }


}
