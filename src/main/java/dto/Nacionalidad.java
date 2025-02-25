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
@Table(name = "Nacionalidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nacionalidad.findAll", query = "SELECT n FROM Nacionalidad n"),
    @NamedQuery(name = "Nacionalidad.findByCodiNaci", query = "SELECT n FROM Nacionalidad n WHERE n.codiNaci = :codiNaci"),
    @NamedQuery(name = "Nacionalidad.findByNombNaci", query = "SELECT n FROM Nacionalidad n WHERE n.nombNaci = :nombNaci")})
public class Nacionalidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codiNaci")
    private String codiNaci;
    @Size(max = 50)
    @Column(name = "nombNaci")
    private String nombNaci;

    public Nacionalidad() {
    }

    public Nacionalidad(String codiNaci) {
        this.codiNaci = codiNaci;
    }

    public String getCodiNaci() {
        return codiNaci;
    }

    public void setCodiNaci(String codiNaci) {
        this.codiNaci = codiNaci;
    }

    public String getNombNaci() {
        return nombNaci;
    }

    public void setNombNaci(String nombNaci) {
        this.nombNaci = nombNaci;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiNaci != null ? codiNaci.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nacionalidad)) {
            return false;
        }
        Nacionalidad other = (Nacionalidad) object;
        if ((this.codiNaci == null && other.codiNaci != null) || (this.codiNaci != null && !this.codiNaci.equals(other.codiNaci))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Nacionalidad[ codiNaci=" + codiNaci + " ]";
    }
    
}
