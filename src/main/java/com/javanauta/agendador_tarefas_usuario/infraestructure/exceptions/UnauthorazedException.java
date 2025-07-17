package com.javanauta.agendador_tarefas_usuario.infraestructure.exceptions;

import javax.naming.AuthenticationException;

public class UnauthorazedException extends AuthenticationException {

    public UnauthorazedException(String mensagem){
        super(mensagem);
    }

    public UnauthorazedException(String mensagem, Throwable throwable){
        super(mensagem);
    }
}
