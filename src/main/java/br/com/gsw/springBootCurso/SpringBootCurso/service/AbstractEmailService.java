package br.com.gsw.springBootCurso.SpringBootCurso.service;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService{

    @Value( "${default.sender}" )
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        SimpleMailMessage sm = prepareSimpleMailMessagefromPedido(pedido);
        sendEmail( sm );
    }

    protected SimpleMailMessage prepareSimpleMailMessagefromPedido(Pedido pedido) {
        SimpleMailMessage sm = new SimpleMailMessage(  );
        sm.setTo( pedido.getCliente().getEmail() );
        sm.setFrom( sender );
        sm.setSubject( "Pedido Confirmado : "+pedido.getId() );
        sm.setSentDate(new Date( System.currentTimeMillis() ) );
        sm.setText( pedido.toString() );
        return sm;
    }

    protected String htmlFromTemplatePedido(Pedido obj){
        Context context = new Context(  );
        context.setVariable( "pedido",obj );
        return templateEngine.process( "email/ConfirmacaoPedido",context );
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido pedido){
        try {
            MimeMessage mimeMessage = prepareSimpleMailMessageFromPedido( pedido );
            sendHtmlEmail( mimeMessage );
        } catch (MessagingException e) {
            sendOrderConfirmationEmail(pedido);
        }

    }

    protected MimeMessage prepareSimpleMailMessageFromPedido(Pedido pedido) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper( mimeMessage,true );
        mimeMessageHelper.setTo( pedido.getCliente().getEmail() );
        mimeMessageHelper.setFrom( sender );
        mimeMessageHelper.setSubject( "Pedido Confirmado : "+pedido.getId() );
        mimeMessageHelper.setSentDate(new Date( System.currentTimeMillis() ) );
        mimeMessageHelper.setText(htmlFromTemplatePedido( pedido ),true);
        return mimeMessage;


    }


}
