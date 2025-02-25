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
@Table(name = "ConvenioEvitarDobleTributacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConvenioEvitarDobleTributacion.findAll", query = "SELECT c FROM ConvenioEvitarDobleTributacion c"),
    @NamedQuery(name = "ConvenioEvitarDobleTributacion.findByCodiConvDoblTrib", query = "SELECT c FROM ConvenioEvitarDobleTributacion c WHERE c.codiConvDoblTrib = :codiConvDoblTrib"),
    @NamedQuery(name = "ConvenioEvitarDobleTributacion.findByNombConvDoblTrib", query = "SELECT c FROM ConvenioEvitarDobleTributacion c WHERE c.nombConvDoblTrib = :nombConvDoblTrib")})
public class ConvenioEvitarDobleTributacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "codiConvDoblTrib")
    private String codiConvDoblTrib;
    @Size(max = 30)
    @Column(name = "nombConvDoblTrib")
    private String nombConvDoblTrib;

    public ConvenioEvitarDobleTributacion() {
    }

    public ConvenioEvitarDobleTributacion(String codiConvDoblTrib) {
        this.codiConvDoblTrib = codiConvDoblTrib;
    }

    public String getCodiConvDoblTrib() {
        return codiConvDoblTrib;
    }

    public void setCodiConvDoblTrib(String codiConvDoblTrib) {
        this.codiConvDoblTrib = codiConvDoblTrib;
    }

    public String getNombConvDoblTrib() {
        return nombConvDoblTrib;
    }

    public void setNombConvDoblTrib(String nombConvDoblTrib) {
        this.nombConvDoblTrib = nombConvDoblTrib;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiConvDoblTrib != null ? codiConvDoblTrib.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConvenioEvitarDobleTributacion)) {
            return false;
        }
        ConvenioEvitarDobleTributacion other = (ConvenioEvitarDobleTributacion) object;
        if ((this.codiConvDoblTrib == null && other.codiConvDoblTrib != null) || (this.codiConvDoblTrib != null && !this.codiConvDoblTrib.equals(other.codiConvDoblTrib))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.ConvenioEvitarDobleTributacion[ codiConvDoblTrib=" + codiConvDoblTrib + " ]";
    }
    
}
