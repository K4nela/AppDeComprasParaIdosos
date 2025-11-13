package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.repository.LogRepository;
import com.k4nela.easypeasy.model.Log;
import com.k4nela.easypeasy.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
   public LogRepository logRepository;

    // 📥 Cria um novo log
    @PostMapping
    public Log criarLog(@RequestBody Log log) {
        return logRepository.save(log);

    }

    // 📤 Lista todos os logs
    @GetMapping
    public List<Log> listarlogs() {
        return logRepository.findAll();

    }
}
