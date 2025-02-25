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
@Table(name = "TipoDoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDoc.findAll", query = "SELECT t FROM TipoDoc t"),
    @NamedQuery(name = "TipoDoc.findByCodiTipoDoc", query = "SELECT t FROM TipoDoc t WHERE t.codiTipoDoc = :codiTipoDoc"),
    @NamedQuery(name = "TipoDoc.findByNombTipoDoc", query = "SELECT t FROM TipoDoc t WHERE t.nombTipoDoc = :nombTipoDoc")})
public class TipoDoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "codiTipoDoc")
    private String codiTipoDoc;
    @Size(max = 50)
    @Column(name = "nombTipoDoc")
    private String nombTipoDoc;

    public TipoDoc() {
    }

    public TipoDoc(String codiTipoDoc) {
        this.codiTipoDoc = codiTipoDoc;
    }

    public String getCodiTipoDoc() {
        return codiTipoDoc;
    }

    public void setCodiTipoDoc(String codiTipoDoc) {
        this.codiTipoDoc = codiTipoDoc;
    }

    public String getNombTipoDoc() {
        return nombTipoDoc;
    }

    public void setNombTipoDoc(String nombTipoDoc) {
        this.nombTipoDoc = nombTipoDoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiTipoDoc != null ? codiTipoDoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDoc)) {
            return false;
        }
        TipoDoc other = (TipoDoc) object;
        if ((this.codiTipoDoc == null && other.codiTipoDoc != null) || (this.codiTipoDoc != null && !this.codiTipoDoc.equals(other.codiTipoDoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.TipoDoc[ codiTipoDoc=" + codiTipoDoc + " ]";
    }
    
}
