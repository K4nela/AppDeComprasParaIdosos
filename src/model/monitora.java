package model;

import java.time.LocalDate;

/**
 * Representa a associação (monitoramento) entre um familiar e um id_monitoraoso.
 */
public class monitora {
    private int id_monitora;
    private int id_monitora_familiar;
    private int id_monitora_id_monitoraoso;

    public monitora() {}

    public monitora(int id_monitora, int id_monitora_familiar, int id_monitora_id_monitoraoso) {
        this.id_monitora = id_monitora;
        this.id_monitora_familiar = id_monitora_familiar;
        this.id_monitora_id_monitoraoso = id_monitora_id_monitoraoso;
    }

    public monitora(int id_monitora_familiar, int id_monitora_id_monitoraoso) {
        this.id_monitora_familiar = id_monitora_familiar;
        this.id_monitora_id_monitoraoso = id_monitora_id_monitoraoso;
    }

    public int getId() {
        return id_monitora;
    }

    public void setId(int id_monitora) {
        this.id_monitora = id_monitora;
    }

    public int getId_familiar() {
        return id_monitora_familiar;
    }

    public void setId_familiar(int id_monitora_familiar) {
        this.id_monitora_familiar = id_monitora_familiar;
    }

    public int getId_id_monitoraoso() {
        return id_monitora_id_monitoraoso;
    }

    public void setId_id_monitoraoso(int id_monitora_id_monitoraoso) {
        this.id_monitora_id_monitoraoso = id_monitora_id_monitoraoso;
    }

    @Override
    public String toString() {
        return "monitora{" +
                "id_monitora=" + id_monitora +
                ", id_monitora_familiar=" + id_monitora_familiar +
                ", id_monitora_id_monitoraoso=" + id_monitora_id_monitoraoso +
                '}';
    }
}
