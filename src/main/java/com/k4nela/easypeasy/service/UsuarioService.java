package com.k4nela.easypeasy.service;

import com.k4nela.easypeasy.entity.Usuario;
import com.k4nela.easypeasy.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LogService logService;
    @Autowired
    private NotificacaoService notificacaoService;

    //  Cria um novo usuário com validação de e-mail único
    public Usuario criarUsuario(Usuario usuario) {
        // Verifica se já existe um usuário com o mesmo e-mail
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioExistente.isPresent()) {
            throw new RuntimeException("Já existe um usuário cadastrado com este e-mail!");
        }

        logService.registrar(
                "Usuario criado!",
                "INFO",
                "UsuarioService",
                "ID " + usuario.getId()
        );

        notificacaoService.enviar(
          "Bem Vindo!",
          "Usuário " + usuario.getNome() + " cadastrado com sucésso!"
        );

        return usuarioRepository.save(usuario);
    }

    //  Retorna todos os usuários cadastrados
    public List<Usuario> listarUsuarios() {
        logService.registrar(
                "Listagem de usuários",
                "INFO",
                "UsuarioService",
                "Usuários listados"
        );

        return usuarioRepository.findAll();
    }

    //  Busca um usuário pelo ID
    public Usuario buscarPorId(String id) {
        logService.registrar(
                "Busca de usuário por ID",
                "INFO",
                "UsuarioService",
                "ID " + id
        );
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    //  Atualiza os dados de um usuário existente
    public Usuario atualizarUsuario(String id, Usuario dadosAtualizados) {

        // Busca o usuário existente (já lança exceção se não existir)
        Usuario usuario = buscarPorId(id);

        // Atualiza apenas os campos permitidos
        usuario.setNome(dadosAtualizados.getNome());
        usuario.setEmail(dadosAtualizados.getEmail());
        usuario.setTelefone(dadosAtualizados.getTelefone());
        usuario.setEndereco(dadosAtualizados.getEndereco());
        usuario.setTipo(dadosAtualizados.getTipo());
        usuario.setDataNasc(dadosAtualizados.getDataNasc());

        // REGRA DE OURO: NUNCA sobrescreve a senha com nulo ou vazio
        // Só atualiza a senha se vier algo preenchida no JSON
        if (dadosAtualizados.getSenha() != null && !dadosAtualizados.getSenha().trim().isEmpty()) {
            usuario.setSenha(dadosAtualizados.getSenha()); // depois você pode criptografar aqui
        }
        // Se não vier senha → mantém a antiga (seguro!)

        logService.registrar(
                "Usuário atualizado",
                "INFO",
                "UsuarioService",
                "ID: " + id + " | Nome: " + usuario.getNome()
        );

        notificacaoService.enviar(
                "Seus dados foram atualizados",
                "Olá " + usuario.getNome() + ", suas informações foram alteradas com sucesso!"
        );

        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarInformacao(@PathVariable String id, @RequestBody Map<String, Object> campos){
        Usuario u = usuarioRepository.findById(id).orElseThrow( () ->
                new RuntimeException("Usuario não encontrado")
        );

        if(campos.containsKey("nome")){
            u.setNome((String) campos.get("nome"));
        }
        if(campos.containsKey("dataNasc")){
            u.setDataNasc(LocalDate.parse((String) campos.get("dataNasc")));
        }
        if(campos.containsKey("email")){
            u.setEmail((String) campos.get("email"));
        }
        if(campos.containsKey("senha")){
            u.setSenha((String) campos.get("senha"));
        }
        if(campos.containsKey("endereco")){
            u.setEndereco((String) campos.get("endereco"));
        }
        if(campos.containsKey("telefone")){
            u.setTelefone((String) campos.get("telefone"));
        }

        logService.registrar(
                "Atualização parcial de usuário",
                "INFO",
                "UsuarioService",
                "ID " + id + " | Campos alterados: " + campos.keySet()
        );


        return usuarioRepository.save(u);
    }

    //  Remove um usuário pelo ID
    public void deletarUsuario(String id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado para exclusão!");
        }

        logService.registrar(
                "Usuario deletado!",
                "WARN",
                "UsuarioService",
                "ID deletado: " + id
        );

        notificacaoService.enviar(
                "Usuário removido",
                "O usuário foi excluído do sistema."
        );


        usuarioRepository.deleteById(id);
    }
}
