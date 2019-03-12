package br.com.gsw.springBootCurso.SpringBootCurso.conf;

import br.com.gsw.springBootCurso.SpringBootCurso.service.DBService;
import br.com.gsw.springBootCurso.SpringBootCurso.service.EmailService;
import br.com.gsw.springBootCurso.SpringBootCurso.service.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile( "test" )
public class TestConfig {
    @Autowired
    DBService service;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        service.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }
}
