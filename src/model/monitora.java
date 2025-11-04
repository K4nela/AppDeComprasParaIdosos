package model;

import java.time.LocalDate;

public class monitora {
    private int id_monitora;
    private int id_familiar;
    private int id_idoso;

    public monitora() {}

    public int getId() {
        return id_monitora;
    }

    public void setId(int id_monitora) {
        this.id_monitora = id_monitora;
    }

    public int getId_familiar() {
        return id_familiar;
    }

    public void setId_familiar(int id_familiar) {
        this.id_familiar = id_familiar;
    }

    public int getId_idoso() {
        return id_idoso;
    }

    public void setId_idoso(int id_idoso) {
        this.id_idoso = id_idoso;
    }

}
