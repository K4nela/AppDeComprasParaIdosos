package model;
import javax.xml.crypto.Data;
import java.time.LocalDate;

public class usuario {
    protected int id;
    protected String nome;
    protected LocalDate dataNasc;
    protected String e_mail;
    protected String senha;
    protected String endereco;
    protected String telefone;

    public usuario(int id, String nome, LocalDate dataNasc, String e_mail, String senha, String endereco, String telefone) {
        this.id = id;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.e_mail = e_mail;
        this.senha = senha;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public usuario(String nome, LocalDate dataNasc, String e_mail, String senha, String endereco, String telefone) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.e_mail = e_mail;
        this.senha = senha;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "\n-------Usuário-------\n" +
                "id = " + id +
                "\nnome = " + nome +
                "\ndata de nascimento = " + dataNasc +
                "\nemail = " + e_mail +
                "\nsenha = " + senha +
                "\nendereço = " + endereco +
                "\ntelefone = " + telefone +
                "\n--------------------";
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

    public LocalDate getDataNasc() {
        return dataNasc;
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
