package com.javanauta.agendador_tarefas_usuario.infraestructure.exceptions;

public class IllegalArgumentExceptions extends RuntimeException{

    public IllegalArgumentExceptions(String mensagem){
        super(mensagem);
    }

    public IllegalArgumentExceptions(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
}
