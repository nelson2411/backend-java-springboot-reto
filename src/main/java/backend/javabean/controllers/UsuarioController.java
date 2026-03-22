package backend.javabean.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

import backend.javabean.dto.UsuarioDTO;
import backend.javabean.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios")
public class UsuarioController {

  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @GetMapping
  public List<UsuarioDTO> getAllUsuarios() {
    return usuarioService.getAllUsuarios();
  }

  // get usuario by id
  @GetMapping("/{id}")
  public UsuarioDTO getUsuarioById(@PathVariable Long id) {
    return usuarioService.getUsuarioById(id);
  }

  @PostMapping
  public UsuarioDTO createUsuario(@RequestBody UsuarioDTO dto) {
    return usuarioService.createUsuario(dto);
  }

}
