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
@Table(name = "TipoOcupPrivado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoOcupPrivado.findAll", query = "SELECT t FROM TipoOcupPrivado t"),
    @NamedQuery(name = "TipoOcupPrivado.findByCodiTipoOcupPriv", query = "SELECT t FROM TipoOcupPrivado t WHERE t.codiTipoOcupPriv = :codiTipoOcupPriv"),
    @NamedQuery(name = "TipoOcupPrivado.findByNombTipoOcupPriv", query = "SELECT t FROM TipoOcupPrivado t WHERE t.nombTipoOcupPriv = :nombTipoOcupPriv"),
    @NamedQuery(name = "TipoOcupPrivado.findByEjecTipoOcupPriv", query = "SELECT t FROM TipoOcupPrivado t WHERE t.ejecTipoOcupPriv = :ejecTipoOcupPriv"),
    @NamedQuery(name = "TipoOcupPrivado.findByEmplTipoOcupPriv", query = "SELECT t FROM TipoOcupPrivado t WHERE t.emplTipoOcupPriv = :emplTipoOcupPriv"),
    @NamedQuery(name = "TipoOcupPrivado.findByObreTipoOcupPriv", query = "SELECT t FROM TipoOcupPrivado t WHERE t.obreTipoOcupPriv = :obreTipoOcupPriv")})
public class TipoOcupPrivado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "codiTipoOcupPriv")
    private String codiTipoOcupPriv;
    @Size(max = 150)
    @Column(name = "nombTipoOcupPriv")
    private String nombTipoOcupPriv;
    @Column(name = "ejecTipoOcupPriv")
    private Character ejecTipoOcupPriv;
    @Column(name = "emplTipoOcupPriv")
    private Character emplTipoOcupPriv;
    @Column(name = "obreTipoOcupPriv")
    private Character obreTipoOcupPriv;

    public TipoOcupPrivado() {
    }

    public TipoOcupPrivado(String codiTipoOcupPriv) {
        this.codiTipoOcupPriv = codiTipoOcupPriv;
    }

    public String getCodiTipoOcupPriv() {
        return codiTipoOcupPriv;
    }

    public void setCodiTipoOcupPriv(String codiTipoOcupPriv) {
        this.codiTipoOcupPriv = codiTipoOcupPriv;
    }

    public String getNombTipoOcupPriv() {
        return nombTipoOcupPriv;
    }

    public void setNombTipoOcupPriv(String nombTipoOcupPriv) {
        this.nombTipoOcupPriv = nombTipoOcupPriv;
    }

    public Character getEjecTipoOcupPriv() {
        return ejecTipoOcupPriv;
    }

    public void setEjecTipoOcupPriv(Character ejecTipoOcupPriv) {
        this.ejecTipoOcupPriv = ejecTipoOcupPriv;
    }

    public Character getEmplTipoOcupPriv() {
        return emplTipoOcupPriv;
    }

    public void setEmplTipoOcupPriv(Character emplTipoOcupPriv) {
        this.emplTipoOcupPriv = emplTipoOcupPriv;
    }

    public Character getObreTipoOcupPriv() {
        return obreTipoOcupPriv;
    }

    public void setObreTipoOcupPriv(Character obreTipoOcupPriv) {
        this.obreTipoOcupPriv = obreTipoOcupPriv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiTipoOcupPriv != null ? codiTipoOcupPriv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoOcupPrivado)) {
            return false;
        }
        TipoOcupPrivado other = (TipoOcupPrivado) object;
        if ((this.codiTipoOcupPriv == null && other.codiTipoOcupPriv != null) || (this.codiTipoOcupPriv != null && !this.codiTipoOcupPriv.equals(other.codiTipoOcupPriv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.TipoOcupPrivado[ codiTipoOcupPriv=" + codiTipoOcupPriv + " ]";
    }
    
}
