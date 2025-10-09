package model;
import javax.xml.crypto.Data;

public class usuario {
    protected int id;
    protected String nome;
    protected Data data_nasc;
    protected String e_mail;
    protected String senha;
    protected String endereco;
    protected String telefone;

    public usuario(int id, String nome, Data data_nasc, String e_mail, String senha, String endereco, String telefone) {
        this.id = id;
        this.nome = nome;
        this.data_nasc = data_nasc;
        this.e_mail = e_mail;
        this.senha = senha;
        this.endereco = endereco;
        this.telefone = telefone;
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

    public Data getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Data data_nasc) {
        this.data_nasc = data_nasc;
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
