package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class historico {
    private int id_historico;
    private LocalDate data_historico;
    private int id_item;
    private status status;

    public historico(int id_historico, LocalDate data_historico, int id_item, status status) {
        this.id_historico = id_historico;
        this.data_historico = data_historico;
        this.id_item = id_item;
        this.status = status.PENDENTE;
    }

    public historico(int id_historico, LocalDate data_historico, status status) {
        this.id_historico = id_historico;
        this.data_historico = LocalDate.now();
        this.status = status.PENDENTE;
    }

    public historico(int id_item, status status){
        this.id_item = id_item;
        this.status = status.PENDENTE;
        this.data_historico = LocalDate.now();
    }

    public historico() {
        this.data_historico = LocalDate.now();
        this.status = status.PENDENTE;
    }

    public historico(status status) {
        this.status = status;
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

    public String getData_historico() {
        DateTimeFormatter formatado = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = data_historico.format(formatado);
        return dataFormatada;
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

    public status getStatus() {
        return status;
    }

    public void setStatus(status status) {
        this.status = status;
    }


}
