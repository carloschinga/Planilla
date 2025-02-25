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
@Table(name = "Ubigeo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ubigeo.findAll", query = "SELECT u FROM Ubigeo u"),
    @NamedQuery(name = "Ubigeo.findByCodiUbig", query = "SELECT u FROM Ubigeo u WHERE u.codiUbig = :codiUbig"),
    @NamedQuery(name = "Ubigeo.findByNombUbig", query = "SELECT u FROM Ubigeo u WHERE u.nombUbig = :nombUbig"),
    @NamedQuery(name = "Ubigeo.findByNiveUbig", query = "SELECT u FROM Ubigeo u WHERE u.niveUbig = :niveUbig")})
public class Ubigeo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "codiUbig")
    private String codiUbig;
    @Size(max = 200)
    @Column(name = "nombUbig")
    private String nombUbig;
    @Column(name = "niveUbig")
    private Character niveUbig;

    public Ubigeo() {
    }

    public Ubigeo(String codiUbig) {
        this.codiUbig = codiUbig;
    }

    public String getCodiUbig() {
        return codiUbig;
    }

    public void setCodiUbig(String codiUbig) {
        this.codiUbig = codiUbig;
    }

    public String getNombUbig() {
        return nombUbig;
    }

    public void setNombUbig(String nombUbig) {
        this.nombUbig = nombUbig;
    }

    public Character getNiveUbig() {
        return niveUbig;
    }

    public void setNiveUbig(Character niveUbig) {
        this.niveUbig = niveUbig;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiUbig != null ? codiUbig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ubigeo)) {
            return false;
        }
        Ubigeo other = (Ubigeo) object;
        if ((this.codiUbig == null && other.codiUbig != null) || (this.codiUbig != null && !this.codiUbig.equals(other.codiUbig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Ubigeo[ codiUbig=" + codiUbig + " ]";
    }
    
}
