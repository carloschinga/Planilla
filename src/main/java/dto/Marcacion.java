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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "Marcacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Marcacion.findAll", query = "SELECT m FROM Marcacion m"),
    @NamedQuery(name = "Marcacion.findByCodiMarc", query = "SELECT m FROM Marcacion m WHERE m.codiMarc = :codiMarc"),
    @NamedQuery(name = "Marcacion.findByCodiPers", query = "SELECT m FROM Marcacion m WHERE m.codiPers = :codiPers"),
    @NamedQuery(name = "Marcacion.findByFechMarc", query = "SELECT m FROM Marcacion m WHERE m.fechMarc = :fechMarc"),
    @NamedQuery(name = "Marcacion.findByCodiHoraDeta", query = "SELECT m FROM Marcacion m WHERE m.codiHoraDeta = :codiHoraDeta"),
    @NamedQuery(name = "Marcacion.findByMarcIngr", query = "SELECT m FROM Marcacion m WHERE m.marcIngr = :marcIngr"),
    @NamedQuery(name = "Marcacion.findByMarcSald", query = "SELECT m FROM Marcacion m WHERE m.marcSald = :marcSald")})
public class Marcacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiMarc")
    private Integer codiMarc;
    @Column(name = "codiPers")
    private Integer codiPers;
    @Column(name = "fechMarc")
    @Temporal(TemporalType.DATE)
    private Date fechMarc;
    @Column(name = "codiHoraDeta")
    private Integer codiHoraDeta;
    @Column(name = "marcIngr")
    @Temporal(TemporalType.TIME)
    private Date marcIngr;
    @Column(name = "marcSald")
    @Temporal(TemporalType.TIME)
    private Date marcSald;

    public Marcacion() {
    }

    public Marcacion(Integer codiMarc) {
        this.codiMarc = codiMarc;
    }

    public Integer getCodiMarc() {
        return codiMarc;
    }

    public void setCodiMarc(Integer codiMarc) {
        this.codiMarc = codiMarc;
    }

    public Integer getCodiPers() {
        return codiPers;
    }

    public void setCodiPers(Integer codiPers) {
        this.codiPers = codiPers;
    }

    public Date getFechMarc() {
        return fechMarc;
    }

    public void setFechMarc(Date fechMarc) {
        this.fechMarc = fechMarc;
    }

    public Integer getCodiHoraDeta() {
        return codiHoraDeta;
    }

    public void setCodiHoraDeta(Integer codiHoraDeta) {
        this.codiHoraDeta = codiHoraDeta;
    }

    public Date getMarcIngr() {
        return marcIngr;
    }

    public void setMarcIngr(Date marcIngr) {
        this.marcIngr = marcIngr;
    }

    public Date getMarcSald() {
        return marcSald;
    }

    public void setMarcSald(Date marcSald) {
        this.marcSald = marcSald;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiMarc != null ? codiMarc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marcacion)) {
            return false;
        }
        Marcacion other = (Marcacion) object;
        if ((this.codiMarc == null && other.codiMarc != null) || (this.codiMarc != null && !this.codiMarc.equals(other.codiMarc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Marcacion[ codiMarc=" + codiMarc + " ]";
    }
    
}
