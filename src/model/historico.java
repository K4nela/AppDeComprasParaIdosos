package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class historico {
    private int id_historico;
    private LocalDate data_historico;
    private int id_item;
    private status status;

    public historico() {
        this.data_historico = LocalDate.now();
        this.status = status.PENDENTE;
    }

    public historico() {

    }

    @Override
    public String toString() {
        return data_historico + " - " + status;
    }

    public int getId_historico() {
        return id_historico;
    }

    public void setId_historico(int id_historico) {
        this.id_historico = id_historico;
    }

    public LocalDate getData_historico() {return data_historico;}

    public void setData_historico(LocalDate data_historico) {
        this.data_historico = data_historico;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public status getStatus() {
        return status;
    }

    public void setStatus(status status) {
        this.status = status;
    }


}
