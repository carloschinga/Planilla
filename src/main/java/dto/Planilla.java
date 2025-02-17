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
@Table(name = "Planilla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planilla.findAll", query = "SELECT p FROM Planilla p"),
    @NamedQuery(name = "Planilla.findByCodiPeri", query = "SELECT p FROM Planilla p WHERE p.codiPeri = :codiPeri"),
    @NamedQuery(name = "Planilla.findByCodiPers", query = "SELECT p FROM Planilla p WHERE p.codiPers = :codiPers"),
    @NamedQuery(name = "Planilla.findByActiPlani", query = "SELECT p FROM Planilla p WHERE p.actiPlani = :actiPlani"),
    @NamedQuery(name = "Planilla.findByCodiPlani", query = "SELECT p FROM Planilla p WHERE p.codiPlani = :codiPlani")})
public class Planilla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "codiPeri")
    private Integer codiPeri;
    @Column(name = "codiPers")
    private Integer codiPers;
    @Column(name = "actiPlani")
    private Boolean actiPlani;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiPlani")
    private Integer codiPlani;

    public Planilla() {
    }

    public Planilla(Integer codiPlani,Integer codiPeri, Integer codiPers, Boolean actiPlani ) {
        this.codiPeri = codiPeri;
        this.codiPers = codiPers;
        this.actiPlani = actiPlani;
        this.codiPlani = codiPlani;
    }

    public Planilla(Integer codiPlani) {
        this.codiPlani = codiPlani;
    }

    public Integer getCodiPeri() {
        return codiPeri;
    }

    public void setCodiPeri(Integer codiPeri) {
        this.codiPeri = codiPeri;
    }

    public Integer getCodiPers() {
        return codiPers;
    }

    public void setCodiPers(Integer codiPers) {
        this.codiPers = codiPers;
    }

    public Boolean getActiPlani() {
        return actiPlani;
    }

    public void setActiPlani(Boolean actiPlani) {
        this.actiPlani = actiPlani;
    }

    public Integer getCodiPlani() {
        return codiPlani;
    }

    public void setCodiPlani(Integer codiPlani) {
        this.codiPlani = codiPlani;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiPlani != null ? codiPlani.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planilla)) {
            return false;
        }
        Planilla other = (Planilla) object;
        if ((this.codiPlani == null && other.codiPlani != null) || (this.codiPlani != null && !this.codiPlani.equals(other.codiPlani))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Planilla[ codiPlani=" + codiPlani + " ]";
    }
    
}
