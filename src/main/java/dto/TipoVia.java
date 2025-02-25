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
@Table(name = "TipoVia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoVia.findAll", query = "SELECT t FROM TipoVia t"),
    @NamedQuery(name = "TipoVia.findByCodiTipoVia", query = "SELECT t FROM TipoVia t WHERE t.codiTipoVia = :codiTipoVia"),
    @NamedQuery(name = "TipoVia.findByNombTipoVia", query = "SELECT t FROM TipoVia t WHERE t.nombTipoVia = :nombTipoVia")})
public class TipoVia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "codiTipoVia")
    private String codiTipoVia;
    @Size(max = 50)
    @Column(name = "nombTipoVia")
    private String nombTipoVia;

    public TipoVia() {
    }

    public TipoVia(String codiTipoVia) {
        this.codiTipoVia = codiTipoVia;
    }

    public String getCodiTipoVia() {
        return codiTipoVia;
    }

    public void setCodiTipoVia(String codiTipoVia) {
        this.codiTipoVia = codiTipoVia;
    }

    public String getNombTipoVia() {
        return nombTipoVia;
    }

    public void setNombTipoVia(String nombTipoVia) {
        this.nombTipoVia = nombTipoVia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiTipoVia != null ? codiTipoVia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoVia)) {
            return false;
        }
        TipoVia other = (TipoVia) object;
        if ((this.codiTipoVia == null && other.codiTipoVia != null) || (this.codiTipoVia != null && !this.codiTipoVia.equals(other.codiTipoVia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.TipoVia[ codiTipoVia=" + codiTipoVia + " ]";
    }
    
}
