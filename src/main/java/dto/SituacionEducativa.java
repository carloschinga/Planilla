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
@Table(name = "SituacionEducativa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SituacionEducativa.findAll", query = "SELECT s FROM SituacionEducativa s"),
    @NamedQuery(name = "SituacionEducativa.findByCodiSituEduc", query = "SELECT s FROM SituacionEducativa s WHERE s.codiSituEduc = :codiSituEduc"),
    @NamedQuery(name = "SituacionEducativa.findByNombSituEduc", query = "SELECT s FROM SituacionEducativa s WHERE s.nombSituEduc = :nombSituEduc"),
    @NamedQuery(name = "SituacionEducativa.findByAbrvSituEduc", query = "SELECT s FROM SituacionEducativa s WHERE s.abrvSituEduc = :abrvSituEduc")})
public class SituacionEducativa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "codiSituEduc")
    private String codiSituEduc;
    @Size(max = 100)
    @Column(name = "nombSituEduc")
    private String nombSituEduc;
    @Size(max = 30)
    @Column(name = "abrvSituEduc")
    private String abrvSituEduc;

    public SituacionEducativa() {
    }

    public SituacionEducativa(String codiSituEduc) {
        this.codiSituEduc = codiSituEduc;
    }

    public String getCodiSituEduc() {
        return codiSituEduc;
    }

    public void setCodiSituEduc(String codiSituEduc) {
        this.codiSituEduc = codiSituEduc;
    }

    public String getNombSituEduc() {
        return nombSituEduc;
    }

    public void setNombSituEduc(String nombSituEduc) {
        this.nombSituEduc = nombSituEduc;
    }

    public String getAbrvSituEduc() {
        return abrvSituEduc;
    }

    public void setAbrvSituEduc(String abrvSituEduc) {
        this.abrvSituEduc = abrvSituEduc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiSituEduc != null ? codiSituEduc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SituacionEducativa)) {
            return false;
        }
        SituacionEducativa other = (SituacionEducativa) object;
        if ((this.codiSituEduc == null && other.codiSituEduc != null) || (this.codiSituEduc != null && !this.codiSituEduc.equals(other.codiSituEduc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.SituacionEducativa[ codiSituEduc=" + codiSituEduc + " ]";
    }
    
}
