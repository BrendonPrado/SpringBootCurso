package br.com.gsw.springBootCurso.SpringBootCurso.service;

import br.com.gsw.springBootCurso.SpringBootCurso.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        } catch (Exception e) {
            return null;
        }
    }
}
