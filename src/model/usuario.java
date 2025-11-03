package model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class usuario {
    public Object getTipo;
    protected int id;
    protected String nome;
    protected LocalDate dataNasc;
    protected String e_mail;
    protected String senha;
    protected String endereco;
    protected String telefone;
    protected String tipo;

    public usuario(int id, String nome, LocalDate dataNasc, String e_mail, String senha, String endereco, String telefone, String tipo) {
        this.id = id;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.e_mail = e_mail;
        this.senha = senha;
        this.endereco = endereco;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public usuario(String nome, LocalDate dataNasc, String e_mail, String senha, String endereco, String telefone, String tipo) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.e_mail = e_mail;
        this.senha = senha;
        this.endereco = endereco;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public usuario(){
        this.nome = "";
        this.dataNasc = LocalDate.of(0000,00,00);
        this.e_mail = "";
        this.senha = "";
        this.endereco = "";
        this.telefone = "";
        this.tipo = "";
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNasc() {
        DateTimeFormatter formatado = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataNasc.format(formatado);
        return dataFormatada;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
//