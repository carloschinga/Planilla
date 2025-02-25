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
@Table(name = "EntidadBancaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntidadBancaria.findAll", query = "SELECT e FROM EntidadBancaria e"),
    @NamedQuery(name = "EntidadBancaria.findByCodiEntiBanc", query = "SELECT e FROM EntidadBancaria e WHERE e.codiEntiBanc = :codiEntiBanc"),
    @NamedQuery(name = "EntidadBancaria.findByNombEntiBanc", query = "SELECT e FROM EntidadBancaria e WHERE e.nombEntiBanc = :nombEntiBanc")})
public class EntidadBancaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "codiEntiBanc")
    private String codiEntiBanc;
    @Size(max = 150)
    @Column(name = "nombEntiBanc")
    private String nombEntiBanc;

    public EntidadBancaria() {
    }

    public EntidadBancaria(String codiEntiBanc) {
        this.codiEntiBanc = codiEntiBanc;
    }

    public String getCodiEntiBanc() {
        return codiEntiBanc;
    }

    public void setCodiEntiBanc(String codiEntiBanc) {
        this.codiEntiBanc = codiEntiBanc;
    }

    public String getNombEntiBanc() {
        return nombEntiBanc;
    }

    public void setNombEntiBanc(String nombEntiBanc) {
        this.nombEntiBanc = nombEntiBanc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiEntiBanc != null ? codiEntiBanc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntidadBancaria)) {
            return false;
        }
        EntidadBancaria other = (EntidadBancaria) object;
        if ((this.codiEntiBanc == null && other.codiEntiBanc != null) || (this.codiEntiBanc != null && !this.codiEntiBanc.equals(other.codiEntiBanc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.EntidadBancaria[ codiEntiBanc=" + codiEntiBanc + " ]";
    }
    
}
