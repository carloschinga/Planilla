/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "CLDN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cldn.findAll", query = "SELECT c FROM Cldn c"),
    @NamedQuery(name = "Cldn.findByCodiCldn", query = "SELECT c FROM Cldn c WHERE c.codiCldn = :codiCldn"),
    @NamedQuery(name = "Cldn.findByNombCldn", query = "SELECT c FROM Cldn c WHERE c.nombCldn = :nombCldn")})
public class Cldn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "codiCldn")
    private String codiCldn;
    @Size(max = 40)
    @Column(name = "nombCldn")
    private String nombCldn;

    public Cldn() {
    }

    public Cldn(String codiCldn) {
        this.codiCldn = codiCldn;
    }

    public String getCodiCldn() {
        return codiCldn;
    }

    public void setCodiCldn(String codiCldn) {
        this.codiCldn = codiCldn;
    }

    public String getNombCldn() {
        return nombCldn;
    }

    public void setNombCldn(String nombCldn) {
        this.nombCldn = nombCldn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiCldn != null ? codiCldn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cldn)) {
            return false;
        }
        Cldn other = (Cldn) object;
        if ((this.codiCldn == null && other.codiCldn != null) || (this.codiCldn != null && !this.codiCldn.equals(other.codiCldn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Cldn[ codiCldn=" + codiCldn + " ]";
    }
    
}
