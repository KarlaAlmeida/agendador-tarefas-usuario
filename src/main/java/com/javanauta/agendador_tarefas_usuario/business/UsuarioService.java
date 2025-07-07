package com.javanauta.agendador_tarefas_usuario.business;

import com.javanauta.agendador_tarefas_usuario.business.converter.UsuarioConverter;
import com.javanauta.agendador_tarefas_usuario.business.dto.EnderecoDTO;
import com.javanauta.agendador_tarefas_usuario.business.dto.TelefoneDTO;
import com.javanauta.agendador_tarefas_usuario.business.dto.UsuarioDTO;
import com.javanauta.agendador_tarefas_usuario.infraestructure.entity.Endereco;
import com.javanauta.agendador_tarefas_usuario.infraestructure.entity.Telefone;
import com.javanauta.agendador_tarefas_usuario.infraestructure.entity.Usuario;
import com.javanauta.agendador_tarefas_usuario.infraestructure.exceptions.ConflictExceptions;
import com.javanauta.agendador_tarefas_usuario.infraestructure.exceptions.ResourceNotFoundException;
import com.javanauta.agendador_tarefas_usuario.infraestructure.repository.EnderecoRepository;
import com.javanauta.agendador_tarefas_usuario.infraestructure.repository.TelefoneRepository;
import com.javanauta.agendador_tarefas_usuario.infraestructure.repository.UsuarioRepository;
import com.javanauta.agendador_tarefas_usuario.infraestructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        try{
            emailExiste(usuarioDTO.getEmail());
            usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
            Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
            usuario = usuarioRepository.save(usuario);
            return usuarioConverter.paraUsuarioDTO(usuario);
        } catch (ConflictExceptions e){
            throw new ConflictExceptions("Email já cadastrado" + e.getCause());
        }
    }

    public void emailExiste(String email){
        try{
            boolean existe = verificaEmailExistente(email);
            if(existe){
                throw new ConflictExceptions("Email já cadastrado " + email);
            }
        }catch (ConflictExceptions e){
            throw new ConflictExceptions("Email já cadastrado" + e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email){
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado " + email)));
    }

    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO dto){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setSenha(dto.getSenha() != null ? passwordEncoder.encode(dto.getSenha()) : null);
        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email não localizado " + email));
        Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    public EnderecoDTO atualizaEndereco(Long id, EnderecoDTO dto){
        Endereco enderecoEntity = enderecoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Id não localizado " + id));
        Endereco endereco = usuarioConverter.updateEndereco(dto, enderecoEntity);
        return usuarioConverter.paraEnderecoDTO(enderecoRepository.save(endereco));
    }

    public TelefoneDTO atualizaTelefone(Long id, TelefoneDTO dto){
        Telefone telefoneEntity = telefoneRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Id não localizado " + id));
        Telefone telefone = usuarioConverter.updateTelefone(dto, telefoneEntity);
        return usuarioConverter.paraTelefoneDTO(telefoneRepository.save(telefone));
    }

}
