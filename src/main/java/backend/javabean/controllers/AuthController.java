package backend.javabean.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
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

        return ResponseEntity.ok(new AuthResponseDto(token, new UsuarioDto(usuario.getNombre())));
    }
}