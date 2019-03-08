package br.com.gsw.springBootCurso.SpringBootCurso.service.validation;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.enums.TipoCliente;
import br.com.gsw.springBootCurso.SpringBootCurso.dto.ClienteNewDTO;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.ClienteRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.resource.exceptions.FieldMessage;
import br.com.gsw.springBootCurso.SpringBootCurso.service.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
    @Autowired
    ClienteRepositories clienteRepositories;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
            list.add( new FieldMessage( "cpfOuCnpj","CPF inválido" ) );
        }
        if(objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
            list.add( new FieldMessage( "cpfOuCnpj","CNPJ inválido" ) );
        }

        if(clienteRepositories.findByEmail( objDto.getEmail() ) != null){
            list.add(new FieldMessage("email","Email já usado no sistema"));
        }


        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}