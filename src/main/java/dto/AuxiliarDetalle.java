/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "AuxiliarDetalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuxiliarDetalle.findAll", query = "SELECT a FROM AuxiliarDetalle a"),
    @NamedQuery(name = "AuxiliarDetalle.findByCodiDetaAux", query = "SELECT a FROM AuxiliarDetalle a WHERE a.codiDetaAux = :codiDetaAux"),
    @NamedQuery(name = "AuxiliarDetalle.findByCodiAux", query = "SELECT a FROM AuxiliarDetalle a WHERE a.codiAux = :codiAux"),
    @NamedQuery(name = "AuxiliarDetalle.findByCodiPers", query = "SELECT a FROM AuxiliarDetalle a WHERE a.codiPers = :codiPers"),
    @NamedQuery(name = "AuxiliarDetalle.findByDescDeta", query = "SELECT a FROM AuxiliarDetalle a WHERE a.descDeta = :descDeta"),
    @NamedQuery(name = "AuxiliarDetalle.findByMontDeta", query = "SELECT a FROM AuxiliarDetalle a WHERE a.montDeta = :montDeta")})
public class AuxiliarDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiDetaAux")
    private Integer codiDetaAux;
    @Column(name = "codiAux")
    private Integer codiAux;
    @Column(name = "codiPers")
    private Integer codiPers;
    @Size(max = 150)
    @Column(name = "descDeta")
    private String descDeta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montDeta")
    private BigDecimal montDeta;

    public AuxiliarDetalle() {
    }

    public AuxiliarDetalle(Integer codiDetaAux) {
        this.codiDetaAux = codiDetaAux;
    }

    public Integer getCodiDetaAux() {
        return codiDetaAux;
    }

    public void setCodiDetaAux(Integer codiDetaAux) {
        this.codiDetaAux = codiDetaAux;
    }

    public Integer getCodiAux() {
        return codiAux;
    }

    public void setCodiAux(Integer codiAux) {
        this.codiAux = codiAux;
    }

    public Integer getCodiPers() {
        return codiPers;
    }

    public void setCodiPers(Integer codiPers) {
        this.codiPers = codiPers;
    }

    public String getDescDeta() {
        return descDeta;
    }

    public void setDescDeta(String descDeta) {
        this.descDeta = descDeta;
    }

    public BigDecimal getMontDeta() {
        return montDeta;
    }

    public void setMontDeta(BigDecimal montDeta) {
        this.montDeta = montDeta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiDetaAux != null ? codiDetaAux.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuxiliarDetalle)) {
            return false;
        }
        AuxiliarDetalle other = (AuxiliarDetalle) object;
        if ((this.codiDetaAux == null && other.codiDetaAux != null) || (this.codiDetaAux != null && !this.codiDetaAux.equals(other.codiDetaAux))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.AuxiliarDetalle[ codiDetaAux=" + codiDetaAux + " ]";
    }
    
}
