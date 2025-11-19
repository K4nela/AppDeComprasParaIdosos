package com.k4nela.easypeasy.service;

import com.k4nela.easypeasy.entity.Familiar;
import com.k4nela.easypeasy.entity.Idoso;
import com.k4nela.easypeasy.entity.Monitora;
import com.k4nela.easypeasy.repository.*;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class MonitoraService {

    @Autowired
    private MonitoraRepository rep;
    @Autowired
    private UsuarioRepository repU;
    @Autowired
    private IdosoRepository repId;
    @Autowired
    private FamiliarRepository repFm;
    @Autowired
    private LogService logService;
    @Autowired
    private NotificacaoService notificacaoService;

    // Criar vínculo entre idoso e familiar
    public Monitora criarMonitora(Idoso id, Familiar fa){
        Monitora m = new Monitora();
        m.setFamiliar(fa);
        m.setIdoso(id);

        Optional<Monitora> monitoraIdosoExistente = rep.findById(m.getIdoso().getId());
        Optional<Monitora> monitoraFamiliarExistente = rep.findById(m.getFamiliar().getId());

        if(monitoraFamiliarExistente.isPresent() && monitoraIdosoExistente.isPresent()){
            throw new RuntimeException("Os usuários " + m.getIdoso().getUsuario().getNome() + " e " + m.getFamiliar().getUsuario().getNome() + " já estão associados");
        }

        logService.registrar(
                "Criando Monitora",
                "INFO",
                "MonitoraService",
                "Criando vínculo entre " + m.getIdoso().getUsuario().getNome() + " e " + m.getFamiliar().getUsuario().getNome()
        );

        notificacaoService.enviar(
                "Vínculo criado com sucesso!",
                "Vínculo entre " + m.getIdoso().getUsuario().getNome() + " e " + m.getFamiliar().getUsuario().getNome() + " criado com sucesso!"
        );

        return rep.save(m);
    }

    // Listar todos os vínculos
    public List<Monitora> listarMonitora(){
        List<Monitora> lista = rep.findAll();

        logService.registrar(
                "Listagem de vínculos",
                "INFO",
                "MonitoraService",
                "Total retornado: " + lista.size()
        );

        notificacaoService.enviar(
                "Listagem de vínculos",
                "Foram retornados " + lista.size() + " vínculos"
        );

        return lista;
    }

    // Listar vínculo por ID
    public Monitora listarMonitoraPorId(String id){
        Monitora m = rep.findById(id).orElseThrow(() -> new RuntimeException("Vínculo não encontrado!"));

        logService.registrar(
                "Consulta de vínculo",
                "INFO",
                "MonitoraService",
                "ID " + id
        );

        notificacaoService.enviar(
                "Consulta de vínculo",
                "Vínculo ID " + id + " consultado com sucesso"
        );

        return m;
    }

    // Deletar vínculo
    public void deletarMonitora(@NotNull String id){
        Monitora m = rep.findById(id).orElseThrow(() -> new RuntimeException("Vínculo não encontrado!"));

        long total = rep.countByFamiliarId(id);
        if(total == 1){
            throw new RuntimeException("Este idoso possui apenas um vínculo!");
        }

        rep.deleteById(id);

        logService.registrar(
                "Vínculo deletado!",
                "INFO",
                "MonitoraService",
                "Vínculo entre usuários " + m.getIdoso().getUsuario().getNome() + " e " + m.getFamiliar().getUsuario().getNome() + " deletado"
        );

        notificacaoService.enviar(
                "Vínculo deletado!",
                "Vínculo entre usuários " + m.getIdoso().getUsuario().getNome() + " e " + m.getFamiliar().getUsuario().getNome() + " deletado com sucesso!"
        );
    }
}
