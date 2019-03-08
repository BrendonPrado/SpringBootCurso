package br.com.gsw.springBootCurso.SpringBootCurso.service.validation;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Cliente;
import br.com.gsw.springBootCurso.SpringBootCurso.dto.ClienteDTO;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.ClienteRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.resource.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
    @Autowired
    ClienteRepositories clienteRepositories;

    @Autowired
    HttpServletRequest httpServletRequest;


    @Override
    public void initialize(ClienteUpdate ann) {

    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>(  );
        String uri = httpServletRequest.getRequestURI();
        Integer UriId = Integer.parseInt(httpServletRequest.getRequestURI().substring(uri.lastIndexOf( '/' )+1,uri.length()));
        Cliente aux = clienteRepositories.findByEmail(objDto.getEmail());
        if(aux != null && !aux.getId().equals( objDto.getId() )){
            list.add( new FieldMessage( "email","Esse email já esta sendo utilizado por outro usuário" ) );
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
