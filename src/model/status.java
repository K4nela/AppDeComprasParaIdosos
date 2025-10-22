package model;

public class status {
    private int id_status;
    private String nome_status;

    // Construtor vazio
    public status() {
    }

    public status(int id_status) {
        this.id_status = id_status;
        this.nome_status = nome_status;
    }

    public int getId_status() {
        return id_status;
    }
    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public String getNome_status() {return nome_status;}
    public void setNome_status(String nome_status) {this.nome_status = nome_status;}

    @Override
    public String toString() {
        return "\n------- Status -------" +
                "\nID do status: " + id_status +
                "\nNome do status: " + nome_status +
                "\n----------------------";
    }

}
