package com.k4nela.easypeasy.service;

import com.k4nela.easypeasy.entity.Log;
import com.k4nela.easypeasy.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogService {

    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public Log registrar(String mensagem, String nivel, String origem, String detalhe) {
        Log log = new Log();
        log.setMensagem(mensagem);
        log.setNivel(nivel);
        log.setOrigem(origem);
        log.setDetalhe(detalhe);
        log.setData(LocalDateTime.now());

        logRepository.save(log);
        return log;
    }

    public List<Log> listarTodos() {
        List<Log> logs = logRepository.findAll();

        for (Log log : logs) {
            System.out.println("[" + log.getNivel() + "] "
                    + log.getOrigem() + ": "
                    + log.getMensagem()
                    + " | " + log.getDetalhe()
                    + " | " + log.getData());
        }
        return logs;
    }
}
