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
@Table(name = "RegimenLaboral")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegimenLaboral.findAll", query = "SELECT r FROM RegimenLaboral r"),
    @NamedQuery(name = "RegimenLaboral.findByCodiRegiLabo", query = "SELECT r FROM RegimenLaboral r WHERE r.codiRegiLabo = :codiRegiLabo"),
    @NamedQuery(name = "RegimenLaboral.findByNombRegiLabo", query = "SELECT r FROM RegimenLaboral r WHERE r.nombRegiLabo = :nombRegiLabo"),
    @NamedQuery(name = "RegimenLaboral.findByResuRegiLabo", query = "SELECT r FROM RegimenLaboral r WHERE r.resuRegiLabo = :resuRegiLabo"),
    @NamedQuery(name = "RegimenLaboral.findBySectPriv", query = "SELECT r FROM RegimenLaboral r WHERE r.sectPriv = :sectPriv"),
    @NamedQuery(name = "RegimenLaboral.findBySectPubl", query = "SELECT r FROM RegimenLaboral r WHERE r.sectPubl = :sectPubl"),
    @NamedQuery(name = "RegimenLaboral.findByOtraEnti", query = "SELECT r FROM RegimenLaboral r WHERE r.otraEnti = :otraEnti")})
public class RegimenLaboral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "codiRegiLabo")
    private String codiRegiLabo;
    @Size(max = 50)
    @Column(name = "nombRegiLabo")
    private String nombRegiLabo;
    @Size(max = 20)
    @Column(name = "resuRegiLabo")
    private String resuRegiLabo;
    @Size(max = 4)
    @Column(name = "sectPriv")
    private String sectPriv;
    @Size(max = 4)
    @Column(name = "sectPubl")
    private String sectPubl;
    @Size(max = 4)
    @Column(name = "otraEnti")
    private String otraEnti;

    public RegimenLaboral() {
    }

    public RegimenLaboral(String codiRegiLabo) {
        this.codiRegiLabo = codiRegiLabo;
    }

    public String getCodiRegiLabo() {
        return codiRegiLabo;
    }

    public void setCodiRegiLabo(String codiRegiLabo) {
        this.codiRegiLabo = codiRegiLabo;
    }

    public String getNombRegiLabo() {
        return nombRegiLabo;
    }

    public void setNombRegiLabo(String nombRegiLabo) {
        this.nombRegiLabo = nombRegiLabo;
    }

    public String getResuRegiLabo() {
        return resuRegiLabo;
    }

    public void setResuRegiLabo(String resuRegiLabo) {
        this.resuRegiLabo = resuRegiLabo;
    }

    public String getSectPriv() {
        return sectPriv;
    }

    public void setSectPriv(String sectPriv) {
        this.sectPriv = sectPriv;
    }

    public String getSectPubl() {
        return sectPubl;
    }

    public void setSectPubl(String sectPubl) {
        this.sectPubl = sectPubl;
    }

    public String getOtraEnti() {
        return otraEnti;
    }

    public void setOtraEnti(String otraEnti) {
        this.otraEnti = otraEnti;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiRegiLabo != null ? codiRegiLabo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegimenLaboral)) {
            return false;
        }
        RegimenLaboral other = (RegimenLaboral) object;
        if ((this.codiRegiLabo == null && other.codiRegiLabo != null) || (this.codiRegiLabo != null && !this.codiRegiLabo.equals(other.codiRegiLabo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.RegimenLaboral[ codiRegiLabo=" + codiRegiLabo + " ]";
    }
    
}
