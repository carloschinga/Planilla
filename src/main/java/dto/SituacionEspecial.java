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
@Table(name = "SituacionEspecial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SituacionEspecial.findAll", query = "SELECT s FROM SituacionEspecial s"),
    @NamedQuery(name = "SituacionEspecial.findByCodiSituEspe", query = "SELECT s FROM SituacionEspecial s WHERE s.codiSituEspe = :codiSituEspe"),
    @NamedQuery(name = "SituacionEspecial.findByNombSituEspe", query = "SELECT s FROM SituacionEspecial s WHERE s.nombSituEspe = :nombSituEspe"),
    @NamedQuery(name = "SituacionEspecial.findByAbrvSituEspe", query = "SELECT s FROM SituacionEspecial s WHERE s.abrvSituEspe = :abrvSituEspe")})
public class SituacionEspecial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "codiSituEspe")
    private String codiSituEspe;
    @Size(max = 100)
    @Column(name = "nombSituEspe")
    private String nombSituEspe;
    @Size(max = 50)
    @Column(name = "abrvSituEspe")
    private String abrvSituEspe;

    public SituacionEspecial() {
    }

    public SituacionEspecial(String codiSituEspe) {
        this.codiSituEspe = codiSituEspe;
    }

    public String getCodiSituEspe() {
        return codiSituEspe;
    }

    public void setCodiSituEspe(String codiSituEspe) {
        this.codiSituEspe = codiSituEspe;
    }

    public String getNombSituEspe() {
        return nombSituEspe;
    }

    public void setNombSituEspe(String nombSituEspe) {
        this.nombSituEspe = nombSituEspe;
    }

    public String getAbrvSituEspe() {
        return abrvSituEspe;
    }

    public void setAbrvSituEspe(String abrvSituEspe) {
        this.abrvSituEspe = abrvSituEspe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiSituEspe != null ? codiSituEspe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SituacionEspecial)) {
            return false;
        }
        SituacionEspecial other = (SituacionEspecial) object;
        if ((this.codiSituEspe == null && other.codiSituEspe != null) || (this.codiSituEspe != null && !this.codiSituEspe.equals(other.codiSituEspe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.SituacionEspecial[ codiSituEspe=" + codiSituEspe + " ]";
    }
    
}
