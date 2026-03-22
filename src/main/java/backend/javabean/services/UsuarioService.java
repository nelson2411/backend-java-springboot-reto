package backend.javabean.services;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.javabean.dto.UsuarioDTO;
import backend.javabean.exceptions.ResourceNotFoundException;
import backend.javabean.mappers.UsuarioMapper;
import backend.javabean.models.Usuario;
import backend.javabean.repositories.UsuarioRepository;

@Service
public class UsuarioService {

  private final UsuarioRepository usuarioRepository;

  public UsuarioService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  public List<UsuarioDTO> getAllUsuarios() {
    return usuarioRepository.findAll().stream()
        .map(UsuarioMapper::toDTO)
        .toList();
  }

  // get usuario by id
  public UsuarioDTO getUsuarioById(Long id) {
    Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

    return UsuarioMapper.toDTO(usuario);
  }

  public UsuarioDTO createUsuario(UsuarioDTO dto) {
    Usuario usuario = UsuarioMapper.toEntity(dto);
    Usuario saved = usuarioRepository.save(usuario);
    return UsuarioMapper.toDTO(saved);
  }

}
