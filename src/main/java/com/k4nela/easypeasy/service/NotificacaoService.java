package com.k4nela.easypeasy.service;

import com.k4nela.easypeasy.entity.Notificacao;
import com.k4nela.easypeasy.repository.NotificacaoRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Service
public class NotificacaoService {

    @Autowired
    private final NotificacaoRepository repo;

    public Notificacao enviar(String titulo, String mensagem) {
        Notificacao n = new Notificacao();
        n.setTitulo(titulo);
        n.setMensagem(mensagem);
        n.setLida(false);
        n.setData(LocalDateTime.now());
        return repo.save(n);
    }

    public List<Notificacao> listar() {
        return repo.findAll();
    }

    public Notificacao marcarLida(String id) {
        Notificacao n = repo.findById(id).orElseThrow();
        n.setLida(true);
        return repo.save(n);
    }
}
