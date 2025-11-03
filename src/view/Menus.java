package view;

public abstract class Menus {
    public static void menuTipoStatus(){
        System.out.println("----------Status----------");
        System.out.println("1 - Pendente");
        System.out.println("2 - Em andamento");
        System.out.println("3 - Concluído");
        System.out.println("4 - Cancelado");
        System.out.println("0 - Voltar");
        System.out.println("--------------------------");
        System.out.printf("Escolha uma opção: ");
    }

    public static void menuUpdateUsuario() {
        System.out.println("-------DadosPessoais-------");
        System.out.println("1 - alterar nome");
        System.out.println("2 - alterar data de nascimento");
        System.out.println("3 - alterar email");
        System.out.println("4 - alterar senha");
        System.out.println("5 - alterar endereço");
        System.out.println("6 - alterar telefone");
        System.out.println("0 - Voltar");
        System.out.println("---------------------------");
        System.out.printf("Escolha uma opção: ");
    }

    public static void menuTipoUsuario(){
        System.out.println("-------TipoDeUsuário-------");
        System.out.println("1 - Idoso");
        System.out.println("2 - Familiar");
        System.out.println("3 - Administrador");
        System.out.println("0 - Voltar");
        System.out.println("---------------------------");
        System.out.printf("Escolha uma opção: ");
    }

    public static void menuVerUsuarios(){
        System.out.println("-------ListaDeUsuários-------");
        System.out.println("1 - Listar todos os usuários");
        System.out.println("2 - Listar usuário por ID");
        System.out.println("0 - Voltar");
        System.out.println("-------------------------------");
        System.out.printf("Escolha uma opção: ");
    }

    public static void menuLogin() {
        System.out.println("--------TelaDeLogin--------");
        System.out.println("1 - Entrar");
        System.out.println("2 - Cadastre-se");
        System.out.println("0 - Sair");
        System.out.println("-----------------------------");
        System.out.printf("Escolha uma opção: ");
    }

    public static void menuHomeFamiliar(){
        System.out.println("-------Home-------");
        System.out.println("1 - Ver perfil");
        System.out.println("2 - Ver lista de desejos");
        System.out.println("3 - Ver idosos");
        System.out.println("0 - Voltar");
        System.out.println("------------------");
        System.out.printf("Escolha uma opção: ");
    }

    public static void menuHomeIdoso(){
        System.out.println("-------Menu-------");
        System.out.println("1 - Ver perfil");
        System.out.println("2 - Ver lista de desejos");
        System.out.println("3 - Ver familiares");
        System.out.println("0 - Voltar");
        System.out.println("------------------");
        System.out.printf("Escolha uma opção: ");
    }

    public static void menuItem(){
        System.out.println("---------Itens---------");
        System.out.println("1 - Cadastrar novo item");
        System.out.println("2 - Editar item");
        System.out.println("3 - Excluir item");
        System.out.println("0 - Voltar");
        System.out.println("-----------------------");
        System.out.printf("Escolha uma opção: ");
    }

    public static void menuUpdateItem(){
        System.out.println("---------EditarItem---------");
        System.out.println("1 - alterar nome ");
        System.out.println("2 - alterar descrição ");
        System.out.println("3 - alterar quantidade");
        System.out.println("4 - alterar nome da loja ");
        System.out.println("5 - alterar link ");
        System.out.println("0 - Voltar");
        System.out.println("---------------------------");
        System.out.printf("Escolha uma opção: ");
    }

    public static void menuOpcaoLista(){
        System.out.println("--------Opções-------");
        System.out.println("1 - Criar Lista");
        System.out.println("2 - Acessar lista");
        System.out.println("3 - Editar Lista");
        System.out.println("0 - Voltar");
        System.out.println("----------------------");
        System.out.printf("Escolha uma opção: ");
    }

    public static void menuUpdateLista(){
        System.out.println("-------DadosDaLista-------");
        System.out.println("1 - alterar nome");
        System.out.println("2 - alterar descrição");
        System.out.println("0 - Voltar");
        System.out.println("---------------------------");
        System.out.printf("Escolha uma opção: ");
    }

    public static void menuHomeAdmin(){
        System.out.println("-------Menu-------");
        System.out.println("1 - Ver perfil");
        System.out.println("2 - Gerenciar usuários");
        System.out.println("0 - Voltar");
        System.out.println("------------------");
        System.out.printf("Escolha uma opção: ");
    }

    public static void menuGerenciarUsuarios(){
        System.out.println("-------GerenciarUsuários-------");
        System.out.println("1 - Listar usuários");
        System.out.println("2 - Atualizar usuário");
        System.out.println("3 - Deletar usuário");
        System.out.println("0 - Voltar");
        System.out.println("-------------------------------");
        System.out.printf("Escolha uma opção: ");
    }

    public static void menuPerfil(){
        System.out.println("-------Perfil-------");
        System.out.println("1 - Ver dados pessoais");
        System.out.println("2 - Atualizar dados pessoais");
        System.out.println("3 - Deletar perfil");
        System.out.println("0 - Voltar");
        System.out.println("--------------------");
        System.out.printf("Escolha uma opção: ");
    }
}
