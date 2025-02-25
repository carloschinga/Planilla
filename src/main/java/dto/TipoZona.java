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
@Table(name = "TipoZona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoZona.findAll", query = "SELECT t FROM TipoZona t"),
    @NamedQuery(name = "TipoZona.findByCodiTipoZona", query = "SELECT t FROM TipoZona t WHERE t.codiTipoZona = :codiTipoZona"),
    @NamedQuery(name = "TipoZona.findByNombTipoZona", query = "SELECT t FROM TipoZona t WHERE t.nombTipoZona = :nombTipoZona")})
public class TipoZona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "codiTipoZona")
    private String codiTipoZona;
    @Size(max = 50)
    @Column(name = "nombTipoZona")
    private String nombTipoZona;

    public TipoZona() {
    }

    public TipoZona(String codiTipoZona) {
        this.codiTipoZona = codiTipoZona;
    }

    public String getCodiTipoZona() {
        return codiTipoZona;
    }

    public void setCodiTipoZona(String codiTipoZona) {
        this.codiTipoZona = codiTipoZona;
    }

    public String getNombTipoZona() {
        return nombTipoZona;
    }

    public void setNombTipoZona(String nombTipoZona) {
        this.nombTipoZona = nombTipoZona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiTipoZona != null ? codiTipoZona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoZona)) {
            return false;
        }
        TipoZona other = (TipoZona) object;
        if ((this.codiTipoZona == null && other.codiTipoZona != null) || (this.codiTipoZona != null && !this.codiTipoZona.equals(other.codiTipoZona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.TipoZona[ codiTipoZona=" + codiTipoZona + " ]";
    }
    
}
