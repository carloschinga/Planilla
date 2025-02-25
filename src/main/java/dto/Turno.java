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
@Table(name = "Turno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Turno.findAll", query = "SELECT t FROM Turno t"),
    @NamedQuery(name = "Turno.findByCodiTurn", query = "SELECT t FROM Turno t WHERE t.codiTurn = :codiTurn"),
    @NamedQuery(name = "Turno.findByDescTurn", query = "SELECT t FROM Turno t WHERE t.descTurn = :descTurn"),
    @NamedQuery(name = "Turno.findByHoraIngr", query = "SELECT t FROM Turno t WHERE t.horaIngr = :horaIngr"),
    @NamedQuery(name = "Turno.findByHoraSald", query = "SELECT t FROM Turno t WHERE t.horaSald = :horaSald")})
public class Turno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
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

    public Turno() {
    }

    public Turno(Integer codiTurn) {
        this.codiTurn = codiTurn;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiTurn != null ? codiTurn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turno)) {
            return false;
        }
        Turno other = (Turno) object;
        if ((this.codiTurn == null && other.codiTurn != null) || (this.codiTurn != null && !this.codiTurn.equals(other.codiTurn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Turno[ codiTurn=" + codiTurn + " ]";
    }
    
}
