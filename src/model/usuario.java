package model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class usuario {
    public Object getTipo;
    protected int id;
    protected String nome;
    protected LocalDate dataNasc;
    protected String email;
    protected String senha;
    protected String endereco;
    protected String telefone;
    protected String tipo;

    public usuario(int id, String nome, LocalDate dataNasc, String email, String senha, String endereco, String telefone, String tipo) {
        this.id = id;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public usuario(String nome, LocalDate dataNasc, String email, String senha, String endereco, String telefone, String tipo) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public usuario(){
    }

    public usuario(String nome, LocalDate dataNasc, String email, String endereco, String telefone) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public usuario(String nome) {
        this.nome = nome;
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

    public LocalDate getDataNasc() {return dataNasc;}

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getE_mail() {
        return email;
    }

    public void setE_mail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return String.format("|ID: %d | Tipo: %s | Nome: %s | Data de nascimento: %s | Email: %s | Telefone: %s | Endereço: %s |",
                id, tipo, nome, dataNasc.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), email, telefone, endereco);
    }

}