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
        System.out.println("0 - sair");
        System.out.println("---------------------------");
    }

    public static void menuMain(){
        System.out.println("-------Opções-------");
        System.out.println("1 - criar usuário");
        System.out.println("2 - listar informações");
        System.out.println("3 - atualizar dados pessoais");
        System.out.println("4 - DELETAR usuário");
        System.out.println("0 - sair");
    }

    public static void menuTipoUsuario(){
        System.out.println("-------Tipo de Usuário-------");
        System.out.println("1 - Idoso");
        System.out.println("2 - Familiar");
        System.out.println("3 - Administrador");
        System.out.println("0 - sair");
        System.out.println("---------------------------");
    }
}
//