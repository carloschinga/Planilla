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
@Table(name = "TipoPago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPago.findAll", query = "SELECT t FROM TipoPago t"),
    @NamedQuery(name = "TipoPago.findByCodiTipoPago", query = "SELECT t FROM TipoPago t WHERE t.codiTipoPago = :codiTipoPago"),
    @NamedQuery(name = "TipoPago.findByNombTipoPago", query = "SELECT t FROM TipoPago t WHERE t.nombTipoPago = :nombTipoPago")})
public class TipoPago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "codiTipoPago")
    private String codiTipoPago;
    @Size(max = 50)
    @Column(name = "nombTipoPago")
    private String nombTipoPago;

    public TipoPago() {
    }

    public TipoPago(String codiTipoPago) {
        this.codiTipoPago = codiTipoPago;
    }

    public String getCodiTipoPago() {
        return codiTipoPago;
    }

    public void setCodiTipoPago(String codiTipoPago) {
        this.codiTipoPago = codiTipoPago;
    }

    public String getNombTipoPago() {
        return nombTipoPago;
    }

    public void setNombTipoPago(String nombTipoPago) {
        this.nombTipoPago = nombTipoPago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiTipoPago != null ? codiTipoPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPago)) {
            return false;
        }
        TipoPago other = (TipoPago) object;
        if ((this.codiTipoPago == null && other.codiTipoPago != null) || (this.codiTipoPago != null && !this.codiTipoPago.equals(other.codiTipoPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.TipoPago[ codiTipoPago=" + codiTipoPago + " ]";
    }
    
}
