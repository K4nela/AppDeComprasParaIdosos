package com.k4nela.easypeasy.service;

import com.k4nela.easypeasy.entity.Idoso;
import com.k4nela.easypeasy.entity.Item;
import com.k4nela.easypeasy.entity.ListaDeDesejos;
import com.k4nela.easypeasy.repository.IdosoRepository;
import com.k4nela.easypeasy.repository.ItemRepository;
import com.k4nela.easypeasy.repository.ListaDeDesejosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdosoService {

    @Autowired
    private IdosoRepository idosoRepository;

    @Autowired
    private LogService logService;

    @Autowired
    private NotificacaoService notificacaoService;

    @Autowired
    private ListaDeDesejosRepository listaRepository;


    @Autowired
    private ListaDeDesejosService listaService;

    @Autowired
    private ItemRepository itemRepository;


    public List<Idoso> listar() {
        List<Idoso> lista = idosoRepository.findAll();

        logService.registrar(
                "Listagem de Idosos",
                "INFO",
                "IdosoService",
                "Total retornado: " + lista.size()
        );

        return lista;
    }

    public Idoso criar(Idoso idoso) {
        Idoso criado = idosoRepository.save(idoso);

        logService.registrar(
                "Idoso Criado",
                "INFO",
                "IdosoService",
                "ID " + criado.getId()
        );

        notificacaoService.enviar(
                "Novo idoso cadastrado!",
                "O idoso " + criado.getUsuario().getNome() + " foi adicionado ao sistema."
        );
        return criado;
    }

    public Idoso buscarPorId(String id) {
        Idoso i = idosoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Idoso não encontrado: " + id));

        logService.registrar(
                "Idoso Consultado",
                "INFO",
                "IdosoService",
                "ID " + id
        );

        return i;
    }

    public Idoso atualizar(String id, Idoso atualizado) {
        Idoso existente = buscarPorId(id);

        atualizado.setId(existente.getId());
        Idoso atualizadoFinal = idosoRepository.save(atualizado);

        logService.registrar(
                "Idoso Atualizado",
                "INFO",
                "IdosoService",
                "ID " + id
        );

        notificacaoService.enviar(
                "Dados atualizados!",
                "O idoso " + atualizadoFinal.getUsuario().getNome() + " teve seus dados atualizados."
        );

        return atualizadoFinal;
    }

    public void deletar(String id) {
        Idoso i = buscarPorId(id);
        idosoRepository.delete(i);

        logService.registrar(
                "Idoso Deletado",
                "WARN",
                "IdosoService",
                "ID " + id
        );

        notificacaoService.enviar(
                "Registro removido",
                "O idoso " + i.getUsuario().getNome() + " foi removido do sistema."
        );
    }

    public ListaDeDesejos criarListaDeDesejos(Idoso idoso, ListaDeDesejos lista) {
        lista.setIdoso(idoso);
        ListaDeDesejos salva = listaRepository.save(lista);

        logService.registrar(
                "Lista de desejos criada",
                "INFO",
                "IdosoService",
                "Lista ID " + salva.getId() + " criada para o idoso " + idoso.getUsuario().getNome()
        );

        notificacaoService.enviar(
                "Nova lista de desejos",
                "O idoso " + idoso.getUsuario().getNome() + " criou uma nova lista de desejos."
        );

        return salva;
    }

    public Item adicionarItemNaLista(String idLista, Item item) {
        ListaDeDesejos lista = listaRepository.findById(idLista)
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));

        item.setListaDeDesejos(lista);
        return itemRepository.save(item);
    }

    public List<Item> listarItensDaListaDoIdoso(String idosoId, int listaId) {
        Idoso idoso = idosoRepository.findById(idosoId)
                .orElseThrow(() -> new RuntimeException("Idoso não encontrado"));

        ListaDeDesejos lista = listaRepository.findByIdAndIdoso(listaId, idoso)
                .orElseThrow(() -> new RuntimeException("Lista não encontrada ou não pertence ao idoso"));

        return lista.getItens();
    }

    public List<ListaDeDesejos> listarListaDeDesejosById(String idosoId) {
        return listaRepository.findByIdosoId(idosoId);
    }

    public ListaDeDesejos buscarListaDoIdosoPorId(String idosoId, int listaId) {
        // Primeiro verifica se o idoso existe
        Idoso idoso = idosoRepository.findById(idosoId)
                .orElseThrow(() -> new RuntimeException("Idoso não encontrado"));

        // Busca a lista específica que pertence a esse idoso
        return listaRepository.findByIdAndIdoso(listaId, idoso)
                .orElseThrow(() -> new RuntimeException("Lista de desejos não encontrada ou não pertence a esse idoso"));
    }

    public Item adicionarItem(ListaDeDesejos lista, Item item) {
        return listaService.criarItemNaLista(String.valueOf(lista.getId()), item);
    }
}

