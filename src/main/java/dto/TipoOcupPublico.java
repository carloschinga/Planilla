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
@Table(name = "TipoOcupPublico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoOcupPublico.findAll", query = "SELECT t FROM TipoOcupPublico t"),
    @NamedQuery(name = "TipoOcupPublico.findByCodiTipoOcupPubl", query = "SELECT t FROM TipoOcupPublico t WHERE t.codiTipoOcupPubl = :codiTipoOcupPubl"),
    @NamedQuery(name = "TipoOcupPublico.findByNombTipoOcupPubl", query = "SELECT t FROM TipoOcupPublico t WHERE t.nombTipoOcupPubl = :nombTipoOcupPubl")})
public class TipoOcupPublico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "codiTipoOcupPubl")
    private String codiTipoOcupPubl;
    @Size(max = 300)
    @Column(name = "nombTipoOcupPubl")
    private String nombTipoOcupPubl;

    public TipoOcupPublico() {
    }

    public TipoOcupPublico(String codiTipoOcupPubl) {
        this.codiTipoOcupPubl = codiTipoOcupPubl;
    }

    public String getCodiTipoOcupPubl() {
        return codiTipoOcupPubl;
    }

    public void setCodiTipoOcupPubl(String codiTipoOcupPubl) {
        this.codiTipoOcupPubl = codiTipoOcupPubl;
    }

    public String getNombTipoOcupPubl() {
        return nombTipoOcupPubl;
    }

    public void setNombTipoOcupPubl(String nombTipoOcupPubl) {
        this.nombTipoOcupPubl = nombTipoOcupPubl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiTipoOcupPubl != null ? codiTipoOcupPubl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoOcupPublico)) {
            return false;
        }
        TipoOcupPublico other = (TipoOcupPublico) object;
        if ((this.codiTipoOcupPubl == null && other.codiTipoOcupPubl != null) || (this.codiTipoOcupPubl != null && !this.codiTipoOcupPubl.equals(other.codiTipoOcupPubl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.TipoOcupPublico[ codiTipoOcupPubl=" + codiTipoOcupPubl + " ]";
    }
    
}
