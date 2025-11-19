package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.entity.Usuario;
import com.k4nela.easypeasy.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000") // libera o front React
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(loginRequest.getEmail());

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(401).body(new ErrorResponse("Usuário não encontrado"));
        }

        Usuario usuario = usuarioOpt.get();

        // senha só pra teste, igual ao que tá no DB
        if (!usuario.getSenha().equals(loginRequest.getSenha())) {
            return ResponseEntity.status(401).body(new ErrorResponse("Senha incorreta"));
        }

        // token fake por enquanto
        String tokenFake = "1234567890";

        // garante que o tipo venha maiúsculo pro front
        String tipoUsuario = usuario.getTipo() != null ? usuario.getTipo().toUpperCase() : "IDOSO";

        // retorna pro front
        AuthResponse response = new AuthResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                tipoUsuario,
                tokenFake
        );

        return ResponseEntity.ok(response);
    }

    // DTOs
    static class LoginRequest {
        private String email;
        private String senha;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
    }

    static class AuthResponse {
        private String id;
        private String nome;
        private String email;
        private String tipo;
        private String token;

        public AuthResponse(String id, String nome, String email, String tipo, String token) {
            this.id = id;
            this.nome = nome;
            this.email = email;
            this.tipo = tipo;
            this.token = token;
        }

        public String getId() { return id; }
        public String getNome() { return nome; }
        public String getEmail() { return email; }
        public String getTipo() { return tipo; }
        public String getToken() { return token; }
    }

    static class ErrorResponse {
        private String message;
        public ErrorResponse(String message) { this.message = message; }
        public String getMessage() { return message; }
    }
}
