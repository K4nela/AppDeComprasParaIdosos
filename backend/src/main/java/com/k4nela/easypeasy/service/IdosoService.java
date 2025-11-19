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

    // LISTAR IDOSOS
    public List<Idoso> listar() {
        List<Idoso> lista = idosoRepository.findAll();

        logService.registrar(
                "Listagem de Idosos",
                "INFO",
                "IdosoService",
                "Total retornado: " + lista.size()
        );

        notificacaoService.enviar(
                "Listagem de idosos",
                "Foram encontrados " + lista.size() + " idosos cadastrados."
        );

        return lista;
    }

    // CRIAR IDOSO
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

    // BUSCAR POR ID
    public Idoso buscarPorId(String id) {
        Idoso i = idosoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Idoso não encontrado: " + id));

        logService.registrar(
                "Idoso Consultado",
                "INFO",
                "IdosoService",
                "ID " + id
        );

        notificacaoService.enviar(
                "Consulta de idoso",
                "Os dados do idoso " + i.getUsuario().getNome() + " foram consultados."
        );

        return i;
    }

    // ATUALIZAR IDOSO
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

    // DELETAR IDOSO
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

    // CRIAR LISTA DE DESEJOS PARA IDOSO
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

    // ADICIONAR ITEM NA LISTA
    public Item adicionarItemNaLista(String idLista, Item item) {
        ListaDeDesejos lista = listaRepository.findById(idLista)
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));

        item.setListaDeDesejos(lista);
        Item salvo = itemRepository.save(item);

        logService.registrar(
                "Item adicionado à lista",
                "INFO",
                "IdosoService",
                "Item " + salvo.getNomeItem() + " adicionado à lista " + lista.getNomeLista()
        );

        notificacaoService.enviar(
                "Novo item na lista",
                "O item '" + salvo.getNomeItem() + "' foi adicionado à lista '" + lista.getNomeLista() + "'."
        );

        return salvo;
    }

    // LISTAR ITENS DA LISTA DO IDOSO
    public List<Item> listarItensDaListaDoIdoso(String idosoId, int listaId) {
        Idoso idoso = idosoRepository.findById(idosoId)
                .orElseThrow(() -> new RuntimeException("Idoso não encontrado"));

        ListaDeDesejos lista = listaRepository.findByIdAndIdoso(listaId, idoso)
                .orElseThrow(() -> new RuntimeException("Lista não encontrada ou não pertence ao idoso"));

        return lista.getItens();
    }

    // LISTAR LISTAS DE DESEJOS DO IDOSO
    public List<ListaDeDesejos> listarListaDeDesejosById(String idosoId) {
        return listaRepository.findByIdosoId(idosoId);
    }

    // BUSCAR LISTA ESPECÍFICA DO IDOSO
    public ListaDeDesejos buscarListaDoIdosoPorId(String idosoId, int listaId) {
        Idoso idoso = idosoRepository.findById(idosoId)
                .orElseThrow(() -> new RuntimeException("Idoso não encontrado"));

        return listaRepository.findByIdAndIdoso(listaId, idoso)
                .orElseThrow(() -> new RuntimeException("Lista de desejos não encontrada ou não pertence a esse idoso"));
    }

    // ADICIONAR ITEM UTILIZANDO A LISTA DESEJOS SERVICE
    public Item adicionarItem(ListaDeDesejos lista, Item item) {
        return listaService.criarItemNaLista(String.valueOf(lista.getId()), item);
    }
}
