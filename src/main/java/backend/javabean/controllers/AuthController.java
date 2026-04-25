package backend.javabean.controllers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.javabean.dto.AuthResponseDTO;

import backend.javabean.dto.RegistroUsuarioDto;
import backend.javabean.dto.UsuarioDTO;
import backend.javabean.dto.UsuarioLoginDto;
import backend.javabean.models.Usuario;
import backend.javabean.repositories.UsuarioRepository;
import backend.javabean.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDto loginDto) {

        Usuario usuario = usuarioRepository.findByEmail(loginDto.getEmail()).orElse(null);

        if (usuario == null || !passwordEncoder.matches(loginDto.getPassword(), usuario.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }

        String token = jwtUtil.generateToken(usuario.getEmail());

        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .email(usuario.getEmail())
                .telefono(usuario.getTelefono())
                .build();

        return ResponseEntity.ok(new AuthResponseDTO(token, usuarioDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistroUsuarioDto registroDto) {

        if (usuarioRepository.findByEmail(registroDto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Ya existe un usuario con ese email");
        }

        Usuario usuario = Usuario.builder()
                .nombre(registroDto.getNombre())
                .apellidos(registroDto.getApellidos())
                .email(registroDto.getEmail())
                .password(passwordEncoder.encode(registroDto.getPassword()))
                .telefono(registroDto.getTelefono())
                .activo(true)
                .fechaRegistro(LocalDateTime.now())
                .build();

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        String token = jwtUtil.generateToken(usuarioGuardado.getEmail());

        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                .id(usuarioGuardado.getId())
                .nombre(usuarioGuardado.getNombre())
                .apellidos(usuarioGuardado.getApellidos())
                .email(usuarioGuardado.getEmail())
                .telefono(usuarioGuardado.getTelefono())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponseDTO(token, usuarioDTO));
    }
}