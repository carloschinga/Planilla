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
@Table(name = "PeriocidadRemuneracion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeriocidadRemuneracion.findAll", query = "SELECT p FROM PeriocidadRemuneracion p"),
    @NamedQuery(name = "PeriocidadRemuneracion.findByCodiPeriRemu", query = "SELECT p FROM PeriocidadRemuneracion p WHERE p.codiPeriRemu = :codiPeriRemu"),
    @NamedQuery(name = "PeriocidadRemuneracion.findByNombPeriRemu", query = "SELECT p FROM PeriocidadRemuneracion p WHERE p.nombPeriRemu = :nombPeriRemu")})
public class PeriocidadRemuneracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "codiPeriRemu")
    private String codiPeriRemu;
    @Size(max = 50)
    @Column(name = "nombPeriRemu")
    private String nombPeriRemu;

    public PeriocidadRemuneracion() {
    }

    public PeriocidadRemuneracion(String codiPeriRemu) {
        this.codiPeriRemu = codiPeriRemu;
    }

    public String getCodiPeriRemu() {
        return codiPeriRemu;
    }

    public void setCodiPeriRemu(String codiPeriRemu) {
        this.codiPeriRemu = codiPeriRemu;
    }

    public String getNombPeriRemu() {
        return nombPeriRemu;
    }

    public void setNombPeriRemu(String nombPeriRemu) {
        this.nombPeriRemu = nombPeriRemu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiPeriRemu != null ? codiPeriRemu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriocidadRemuneracion)) {
            return false;
        }
        PeriocidadRemuneracion other = (PeriocidadRemuneracion) object;
        if ((this.codiPeriRemu == null && other.codiPeriRemu != null) || (this.codiPeriRemu != null && !this.codiPeriRemu.equals(other.codiPeriRemu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.PeriocidadRemuneracion[ codiPeriRemu=" + codiPeriRemu + " ]";
    }
    
}
