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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "Dia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dia.findAll", query = "SELECT d FROM Dia d"),
    @NamedQuery(name = "Dia.findByCodiDia", query = "SELECT d FROM Dia d WHERE d.codiDia = :codiDia"),
    @NamedQuery(name = "Dia.findByNombDia", query = "SELECT d FROM Dia d WHERE d.nombDia = :nombDia")})
public class Dia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiDia")
    private Integer codiDia;
    @Size(max = 50)
    @Column(name = "nombDia")
    private String nombDia;

    public Dia() {
    }

    public Dia(Integer codiDia) {
        this.codiDia = codiDia;
    }

    public Integer getCodiDia() {
        return codiDia;
    }

    public void setCodiDia(Integer codiDia) {
        this.codiDia = codiDia;
    }

    public String getNombDia() {
        return nombDia;
    }

    public void setNombDia(String nombDia) {
        this.nombDia = nombDia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiDia != null ? codiDia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dia)) {
            return false;
        }
        Dia other = (Dia) object;
        if ((this.codiDia == null && other.codiDia != null) || (this.codiDia != null && !this.codiDia.equals(other.codiDia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Dia[ codiDia=" + codiDia + " ]";
    }
    
}
