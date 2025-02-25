/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "HorarioDetalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HorarioDetalle.findAll", query = "SELECT h FROM HorarioDetalle h"),
    @NamedQuery(name = "HorarioDetalle.findByCodiHoraDeta", query = "SELECT h FROM HorarioDetalle h WHERE h.codiHoraDeta = :codiHoraDeta"),
    @NamedQuery(name = "HorarioDetalle.findByCodiHora", query = "SELECT h FROM HorarioDetalle h WHERE h.codiHora = :codiHora"),
    @NamedQuery(name = "HorarioDetalle.findByCodiDia", query = "SELECT h FROM HorarioDetalle h WHERE h.codiDia = :codiDia"),
    @NamedQuery(name = "HorarioDetalle.findByCodiTurn", query = "SELECT h FROM HorarioDetalle h WHERE h.codiTurn = :codiTurn")})
public class HorarioDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiHoraDeta")
    private Integer codiHoraDeta;
    @Column(name = "codiHora")
    private Integer codiHora;
    @Column(name = "codiDia")
    private Integer codiDia;
    @Column(name = "codiTurn")
    private Integer codiTurn;

    public HorarioDetalle() {
    }

    public HorarioDetalle(Integer codiHoraDeta) {
        this.codiHoraDeta = codiHoraDeta;
    }

    public Integer getCodiHoraDeta() {
        return codiHoraDeta;
    }

    public void setCodiHoraDeta(Integer codiHoraDeta) {
        this.codiHoraDeta = codiHoraDeta;
    }

    public Integer getCodiHora() {
        return codiHora;
    }

    public void setCodiHora(Integer codiHora) {
        this.codiHora = codiHora;
    }

    public Integer getCodiDia() {
        return codiDia;
    }

    public void setCodiDia(Integer codiDia) {
        this.codiDia = codiDia;
    }

    public Integer getCodiTurn() {
        return codiTurn;
    }

    public void setCodiTurn(Integer codiTurn) {
        this.codiTurn = codiTurn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiHoraDeta != null ? codiHoraDeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorarioDetalle)) {
            return false;
        }
        HorarioDetalle other = (HorarioDetalle) object;
        if ((this.codiHoraDeta == null && other.codiHoraDeta != null) || (this.codiHoraDeta != null && !this.codiHoraDeta.equals(other.codiHoraDeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.HorarioDetalle[ codiHoraDeta=" + codiHoraDeta + " ]";
    }
    
}
