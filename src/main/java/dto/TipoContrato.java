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
@Table(name = "TipoContrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoContrato.findAll", query = "SELECT t FROM TipoContrato t"),
    @NamedQuery(name = "TipoContrato.findByCodiTipoCntr", query = "SELECT t FROM TipoContrato t WHERE t.codiTipoCntr = :codiTipoCntr"),
    @NamedQuery(name = "TipoContrato.findByNombTipoCntr", query = "SELECT t FROM TipoContrato t WHERE t.nombTipoCntr = :nombTipoCntr"),
    @NamedQuery(name = "TipoContrato.findByAbrvTipoCntr", query = "SELECT t FROM TipoContrato t WHERE t.abrvTipoCntr = :abrvTipoCntr")})
public class TipoContrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "codiTipoCntr")
    private String codiTipoCntr;
    @Size(max = 200)
    @Column(name = "nombTipoCntr")
    private String nombTipoCntr;
    @Size(max = 50)
    @Column(name = "abrvTipoCntr")
    private String abrvTipoCntr;

    public TipoContrato() {
    }

    public TipoContrato(String codiTipoCntr) {
        this.codiTipoCntr = codiTipoCntr;
    }

    public String getCodiTipoCntr() {
        return codiTipoCntr;
    }

    public void setCodiTipoCntr(String codiTipoCntr) {
        this.codiTipoCntr = codiTipoCntr;
    }

    public String getNombTipoCntr() {
        return nombTipoCntr;
    }

    public void setNombTipoCntr(String nombTipoCntr) {
        this.nombTipoCntr = nombTipoCntr;
    }

    public String getAbrvTipoCntr() {
        return abrvTipoCntr;
    }

    public void setAbrvTipoCntr(String abrvTipoCntr) {
        this.abrvTipoCntr = abrvTipoCntr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiTipoCntr != null ? codiTipoCntr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoContrato)) {
            return false;
        }
        TipoContrato other = (TipoContrato) object;
        if ((this.codiTipoCntr == null && other.codiTipoCntr != null) || (this.codiTipoCntr != null && !this.codiTipoCntr.equals(other.codiTipoCntr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.TipoContrato[ codiTipoCntr=" + codiTipoCntr + " ]";
    }
    
}
