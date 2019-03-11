package br.com.gsw.springBootCurso.SpringBootCurso.conf;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.PagamentoComBoleto;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.PagamentoComCartao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes( PagamentoComCartao.class);
                objectMapper.registerSubtypes( PagamentoComBoleto.class);
                super.configure(objectMapper);
            };
        };
        return builder;
    }
}
