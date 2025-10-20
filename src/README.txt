################################### INSTRUÇÕES MINIMAMENTE ÚTEIS ##################################

    Iae galera, estou escrevendo o código faz um tempo e como tem bastante coisa para fazer decidi começar cedo.

    Até agora ainda existem algumas classes vazias, pois só escrevi o básico do programa, como o CRUD e alguns tratamentos de
erro. Como já escrevi vários métodos, acho que só os comentários não seja o suficiente.


    APP / MAIN
    ...
    |__/app
       |__main.java

    Como já vimos ao longo do trabalho, nos dividimos as classes em pacotes de app como main onde vamos rodar o programa.
Importamos todas as classes nela.



    CONTROLLER
    ...
    |__/app
    |  ...
    |__/controller

    Na nossa pasta controller vamos encontrar métodos mais "práticos", onde usamos das classes do CRUD para poder criar
a parte intuitíva do programa, como lógica de navegação, manipulação de dados e tratamento de erros.


    CADASTRO CONTROLLER
    ...
    |
    __/controller
      |__CadastroController.java

    Em CadastroController.java temos a classe "telaCadastro()", usada para realização do cadastro
    usuários.
    A classe é do tipo void e não precisa de parâmetros, ao chamá-la ela imprime a tela de cadastro e pede algumas entradas de dados essenciais como: tipo de conta, nome, data de nascimento, email, senha, endereço e telefone.
    Onde, conforme o tipo de conta escolhido, ela instancia automaticamente para uma classe de entidades definidas no trabalho, como: idoso, familiar e administrador.
    Retornando assim uma função que usa de JDBC para salvar esse novo usuario no banco de dados.

    Chamamos ela de duas maneiras:
    No meio do código "CadastroController.telaCadastro();"
    Importando como "import static controller.CadastroController.telaCadastro;"

    >já podemos vê-la sendo usada na Main.java

    ...
    |
    __/controller
      |__...
      |__HomeController.java

