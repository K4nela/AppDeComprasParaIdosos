package model;

import java.time.LocalDate;
import java.util.Date;

public class historico {
    private int id_histrico;
    private LocalDate data_historico;
    private int id_item;
    private int id_status;

    public historico(int id_histrico, LocalDate data_historico, int id_item, int id_status) {
        this.id_histrico = id_histrico;
        this.data_historico = data_historico;
        this.id_item = id_item;
        this.id_status = id_status;
    }

    public historico(LocalDate data_historico, int id_status){
        this.data_historico = data_historico;
        this.id_status = id_status;
    }

    @Override
    public String toString() {
        return data_historico + " - " + id_status;
    }

    public int getId_histrico() {
        return id_histrico;
    }

    public void setId_histrico(int id_histrico) {
        this.id_histrico = id_histrico;
    }

    public LocalDate getData_historico() {
        return data_historico;
    }

    public void setData_historico(LocalDate data_historico) {
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


}
