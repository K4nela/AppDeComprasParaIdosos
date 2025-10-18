package view;

public abstract class Menus {
    public static void menuUpdate() {
        System.out.println("-------DadosPessoais-------");
        System.out.println("1 - alterar nome");
        System.out.println("2 - alterar data de nascimento");
        System.out.println("3 - alterar email");
        System.out.println("4 - alterar senha");
        System.out.println("5 - alterar endereço");
        System.out.println("6 - alterar telefone");
        System.out.println("0 - Sair");
        System.out.println("---------------------------");
        System.out.printf("Escolha uma opção: ");
    }

    public static void menuMain(){
        System.out.println("-------Opções-------");
        System.out.println("1 - criar usuário");
        System.out.println("2 - listar informações");
        System.out.println("3 - atualizar dados pessoais");
        System.out.println("4 - DELETAR usuário");
        System.out.println("0 - Sair");
        System.out.printf("Escolha uma opção: ");
    }

    public static void menuTipoUsuario(){
        System.out.println("-------Tipo de Usuário-------");
        System.out.println("1 - Idoso");
        System.out.println("2 - Familiar");
        System.out.println("3 - Administrador");
        System.out.println("0 - Sair");
        System.out.println("---------------------------");
        System.out.printf("Escolha uma opção: ");
    }

    public static void menuListarUsuarios(){
        System.out.println("-------Lista de Usuários-------");
        System.out.println("1 - Listar todos os usuários");
        System.out.println("2 - Listar usuário por ID");
        System.out.println("0 - Sair");
        System.out.println("-------------------------------");
        System.out.printf("Escolha uma opção: ");
    }

    public static void menuLogin() {
        System.out.println("--------Tela De Login--------");
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
        System.out.println("0 - Sair");
        System.out.println("------------------");
        System.out.printf("Escolha uma opção: ");
    }

    public static void menuHomeIdoso(){
        System.out.println("-------Menu-------");
        System.out.println("1 - Ver perfil");
        System.out.println("2 - Ver lista de desejos");
        System.out.println("3 - Criar lista de desejos");
        System.out.println("4 - Ver familiares");
        System.out.println("0 - Sair");
        System.out.println("------------------");
        System.out.printf("Escolha uma opção: ");
    }

    public static void menuHomeAdmin(){
        System.out.println("-------Menu-------");
        System.out.println("1 - Ver perfil");
        System.out.println("2 - Gerenciar usuários");
        System.out.println("3 - Ver usuários");
        System.out.println("0 - Sair");
        System.out.println("------------------");
        System.out.printf("Escolha uma opção: ");
    }

    public static void menuPerfil(){
        System.out.println("-------Perfil-------");
        System.out.println("1 - Ver dados pessoais");
        System.out.println("2 - Atualizar dados pessoais");
        System.out.println("0 - Sair");
        System.out.println("--------------------");
        System.out.printf("Escolha uma opção: ");
    }
}
//