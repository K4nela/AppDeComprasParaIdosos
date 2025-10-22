package model;

import java.util.Date;

public class historico {
    private int id_histrico;
    private Date data_historico;
    private int id_item;
    private int id_status;

    public historico() {
    }

    public historico(int id_histrico, Date data_historico, int id_item, int id_status) {
        this.id_histrico = id_histrico;
        this.data_historico = data_historico;
        this.id_item = id_item;
        this.id_status = id_status;
    }

    public int getId_histrico() {
        return id_histrico;
    }
    public void setId_histrico(int id_histrico) {
        this.id_histrico = id_histrico;
    }

    public Date getData_historico() {
        return data_historico;
    }
    public void setData_historico(Date data_historico) {
        this.data_historico = data_historico;
    }

    public int getId_item() {
        return id_item;
    }
    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getId_status() {
        return id_status;
    }
    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    @Override
    public String toString() {
        return "\n------- Histórico -------" +
                "\nID do histórico: " + id_histrico +
                "\nData do histórico: " + data_historico +
                "\nID do item: " + id_item +
                "\nID do status: " + id_status +
                "\n-------------------------";
    }

}
