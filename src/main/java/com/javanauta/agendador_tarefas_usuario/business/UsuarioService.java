package com.javanauta.agendador_tarefas_usuario.business;

import com.javanauta.agendador_tarefas_usuario.business.converter.UsuarioConverter;
import com.javanauta.agendador_tarefas_usuario.business.dto.UsuarioDTO;
import com.javanauta.agendador_tarefas_usuario.infraestructure.entity.Usuario;
import com.javanauta.agendador_tarefas_usuario.infraestructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);
    }
}
