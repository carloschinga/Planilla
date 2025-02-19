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
import javax.validation.constraints.Size;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "Plantilla")
@NamedQueries({
    @NamedQuery(name = "Plantilla.findAll", query = "SELECT p FROM Plantilla p"),
    @NamedQuery(name = "Plantilla.findByCodiPlant", query = "SELECT p FROM Plantilla p WHERE p.codiPlant = :codiPlant"),
    @NamedQuery(name = "Plantilla.findByNombPlant", query = "SELECT p FROM Plantilla p WHERE p.nombPlant = :nombPlant")})
public class Plantilla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiPlant")
    private Integer codiPlant;
    @Size(max = 50)
    @Column(name = "nombPlant")
    private String nombPlant;

    public Plantilla() {
    }

    public Plantilla(String nombPlant) {
        this.nombPlant = nombPlant;
    }

    public Plantilla(Integer codiPlant) {
        this.codiPlant = codiPlant;
    }

    public Integer getCodiPlant() {
        return codiPlant;
    }

    public void setCodiPlant(Integer codiPlant) {
        this.codiPlant = codiPlant;
    }

    public String getNombPlant() {
        return nombPlant;
    }

    public void setNombPlant(String nombPlant) {
        this.nombPlant = nombPlant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiPlant != null ? codiPlant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plantilla)) {
            return false;
        }
        Plantilla other = (Plantilla) object;
        if ((this.codiPlant == null && other.codiPlant != null) || (this.codiPlant != null && !this.codiPlant.equals(other.codiPlant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Plantilla[ codiPlant=" + codiPlant + " ]";
    }
    
}
