package com.javanauta.agendador_tarefas_usuario.business;

import com.javanauta.agendador_tarefas_usuario.infraestructure.client.ViaCepClient;
import com.javanauta.agendador_tarefas_usuario.infraestructure.client.ViaCepDTO;
import com.javanauta.agendador_tarefas_usuario.infraestructure.exceptions.IllegalArgumentExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepClient viaCepClient;

    public ViaCepDTO buscarDadosEndereco(String cep) {
        return viaCepClient.buscaDadosEndereco(processarCep(cep));
    }

    private String processarCep(String cep) {

        String cepFormatado = cep.replace(" ", "").replace("-", "");

        if (!cepFormatado.matches("\\d+") || !Objects.equals(cepFormatado.length(), 8)) {
            throw new IllegalArgumentExceptions("O cep contém caracteres inválidos, favor verificar.");
        }
        return cepFormatado;
    }
}

