/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "vista_horario_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaHorarioDetalle.findAll", query = "SELECT v FROM VistaHorarioDetalle v"),
    @NamedQuery(name = "VistaHorarioDetalle.findByCodiHora", query = "SELECT v FROM VistaHorarioDetalle v WHERE v.codiHora = :codiHora"),
    @NamedQuery(name = "VistaHorarioDetalle.findByNombHora", query = "SELECT v FROM VistaHorarioDetalle v WHERE v.nombHora = :nombHora"),
    @NamedQuery(name = "VistaHorarioDetalle.findByCodiHoraDeta", query = "SELECT v FROM VistaHorarioDetalle v WHERE v.codiHoraDeta = :codiHoraDeta"),
    @NamedQuery(name = "VistaHorarioDetalle.findByCodiDia", query = "SELECT v FROM VistaHorarioDetalle v WHERE v.codiDia = :codiDia"),
    @NamedQuery(name = "VistaHorarioDetalle.findByNombDia", query = "SELECT v FROM VistaHorarioDetalle v WHERE v.nombDia = :nombDia"),
    @NamedQuery(name = "VistaHorarioDetalle.findByCodiTurn", query = "SELECT v FROM VistaHorarioDetalle v WHERE v.codiTurn = :codiTurn"),
    @NamedQuery(name = "VistaHorarioDetalle.findByDescTurn", query = "SELECT v FROM VistaHorarioDetalle v WHERE v.descTurn = :descTurn"),
    @NamedQuery(name = "VistaHorarioDetalle.findByHoraIngr", query = "SELECT v FROM VistaHorarioDetalle v WHERE v.horaIngr = :horaIngr"),
    @NamedQuery(name = "VistaHorarioDetalle.findByHoraSald", query = "SELECT v FROM VistaHorarioDetalle v WHERE v.horaSald = :horaSald")})
public class VistaHorarioDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codiHora")
    private int codiHora;
    @Size(max = 50)
    @Column(name = "nombHora")
    private String nombHora;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codiHoraDeta")
    private int codiHoraDeta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codiDia")
    private int codiDia;
    @Size(max = 50)
    @Column(name = "nombDia")
    private String nombDia;
    @Column(name = "codiTurn")
    private Integer codiTurn;
    @Size(max = 50)
    @Column(name = "descTurn")
    private String descTurn;
    @Column(name = "horaIngr")
    @Temporal(TemporalType.TIME)
    private Date horaIngr;
    @Column(name = "horaSald")
    @Temporal(TemporalType.TIME)
    private Date horaSald;

    public VistaHorarioDetalle() {
    }

    public int getCodiHora() {
        return codiHora;
    }

    public void setCodiHora(int codiHora) {
        this.codiHora = codiHora;
    }

    public String getNombHora() {
        return nombHora;
    }

    public void setNombHora(String nombHora) {
        this.nombHora = nombHora;
    }

    public int getCodiHoraDeta() {
        return codiHoraDeta;
    }

    public void setCodiHoraDeta(int codiHoraDeta) {
        this.codiHoraDeta = codiHoraDeta;
    }

    public int getCodiDia() {
        return codiDia;
    }

    public void setCodiDia(int codiDia) {
        this.codiDia = codiDia;
    }

    public String getNombDia() {
        return nombDia;
    }

    public void setNombDia(String nombDia) {
        this.nombDia = nombDia;
    }

    public Integer getCodiTurn() {
        return codiTurn;
    }

    public void setCodiTurn(Integer codiTurn) {
        this.codiTurn = codiTurn;
    }

    public String getDescTurn() {
        return descTurn;
    }

    public void setDescTurn(String descTurn) {
        this.descTurn = descTurn;
    }

    public Date getHoraIngr() {
        return horaIngr;
    }

    public void setHoraIngr(Date horaIngr) {
        this.horaIngr = horaIngr;
    }

    public Date getHoraSald() {
        return horaSald;
    }

    public void setHoraSald(Date horaSald) {
        this.horaSald = horaSald;
    }
    
}
