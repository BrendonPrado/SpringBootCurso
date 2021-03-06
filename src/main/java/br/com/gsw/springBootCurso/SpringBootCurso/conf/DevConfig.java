package br.com.gsw.springBootCurso.SpringBootCurso.conf;

import br.com.gsw.springBootCurso.SpringBootCurso.service.DBService;
import br.com.gsw.springBootCurso.SpringBootCurso.service.EmailService;
import br.com.gsw.springBootCurso.SpringBootCurso.service.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile( "dev" )
public class DevConfig {
    @Autowired
    DBService service;

    @Value( "${spring.jpa.hibernate.ddl-auto}" )
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        if(!"create".equals( strategy )){
            return false;
        }
        service.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }
}
