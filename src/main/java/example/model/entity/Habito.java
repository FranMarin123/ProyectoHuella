package example.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "habito")
public class Habito {
    @EmbeddedId
    private HabitoId id;

    @Column(name = "frecuencia", nullable = false)
    private Integer frecuencia;

    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;

    @Column(name = "ultima_fecha", nullable = false, length = 20)
    private String ultimaFecha;

    public HabitoId getId() {
        return id;
    }

    public void setId(HabitoId id) {
        this.id = id;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUltimaFecha() {
        return ultimaFecha;
    }

    public void setUltimaFecha(String ultimaFecha) {
        this.ultimaFecha = ultimaFecha;
    }

}