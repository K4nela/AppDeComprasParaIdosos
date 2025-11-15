package com.k4nela.easypeasy;

import com.k4nela.easypeasy.entity.Usuario;
import com.k4nela.easypeasy.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.k4nela.easypeasy"})
public class EasypeasyApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasypeasyApplication.class, args);

    }

    @Bean
    CommandLineRunner runner(UsuarioRepository usuarioRepository) {
        return args -> {
            List<Usuario> users = usuarioRepository.findAll();
            users.forEach(System.out::println); // vai printar no console
        };
    }
}
