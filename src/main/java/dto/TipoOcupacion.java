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
@Table(name = "TipoOcupacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoOcupacion.findAll", query = "SELECT t FROM TipoOcupacion t"),
    @NamedQuery(name = "TipoOcupacion.findByCodiTipoOcup", query = "SELECT t FROM TipoOcupacion t WHERE t.codiTipoOcup = :codiTipoOcup"),
    @NamedQuery(name = "TipoOcupacion.findByNombTipoOcup", query = "SELECT t FROM TipoOcupacion t WHERE t.nombTipoOcup = :nombTipoOcup")})
public class TipoOcupacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "codiTipoOcup")
    private String codiTipoOcup;
    @Size(max = 30)
    @Column(name = "nombTipoOcup")
    private String nombTipoOcup;

    public TipoOcupacion() {
    }

    public TipoOcupacion(String codiTipoOcup) {
        this.codiTipoOcup = codiTipoOcup;
    }

    public String getCodiTipoOcup() {
        return codiTipoOcup;
    }

    public void setCodiTipoOcup(String codiTipoOcup) {
        this.codiTipoOcup = codiTipoOcup;
    }

    public String getNombTipoOcup() {
        return nombTipoOcup;
    }

    public void setNombTipoOcup(String nombTipoOcup) {
        this.nombTipoOcup = nombTipoOcup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiTipoOcup != null ? codiTipoOcup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoOcupacion)) {
            return false;
        }
        TipoOcupacion other = (TipoOcupacion) object;
        if ((this.codiTipoOcup == null && other.codiTipoOcup != null) || (this.codiTipoOcup != null && !this.codiTipoOcup.equals(other.codiTipoOcup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.TipoOcupacion[ codiTipoOcup=" + codiTipoOcup + " ]";
    }
    
}
