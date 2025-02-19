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

/**
 *
 * @author USER
 */
@Entity
@Table(name = "AFP")
@NamedQueries({
    @NamedQuery(name = "Afp.findAll", query = "SELECT a FROM Afp a"),
    @NamedQuery(name = "Afp.findByCodiAFP", query = "SELECT a FROM Afp a WHERE a.codiAFP = :codiAFP"),
    @NamedQuery(name = "Afp.findByNombAFP", query = "SELECT a FROM Afp a WHERE a.nombAFP = :nombAFP"),
    @NamedQuery(name = "Afp.findByMontAFP", query = "SELECT a FROM Afp a WHERE a.montAFP = :montAFP"),
    @NamedQuery(name = "Afp.findBySeguAFP", query = "SELECT a FROM Afp a WHERE a.seguAFP = :seguAFP"),
    @NamedQuery(name = "Afp.findByComiAFP", query = "SELECT a FROM Afp a WHERE a.comiAFP = :comiAFP")})
public class Afp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiAFP")
    private Integer codiAFP;
    @Size(max = 150)
    @Column(name = "nombAFP")
    private String nombAFP;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montAFP")
    private BigDecimal montAFP;
    @Column(name = "seguAFP")
    private BigDecimal seguAFP;
    @Column(name = "comiAFP")
    private BigDecimal comiAFP;

    public Afp() {
    }

    public Afp(Integer codiAFP) {
        this.codiAFP = codiAFP;
    }

    public Integer getCodiAFP() {
        return codiAFP;
    }

    public void setCodiAFP(Integer codiAFP) {
        this.codiAFP = codiAFP;
    }

    public String getNombAFP() {
        return nombAFP;
    }

    public void setNombAFP(String nombAFP) {
        this.nombAFP = nombAFP;
    }

    public BigDecimal getMontAFP() {
        return montAFP;
    }

    public void setMontAFP(BigDecimal montAFP) {
        this.montAFP = montAFP;
    }

    public BigDecimal getSeguAFP() {
        return seguAFP;
    }

    public void setSeguAFP(BigDecimal seguAFP) {
        this.seguAFP = seguAFP;
    }

    public BigDecimal getComiAFP() {
        return comiAFP;
    }

    public void setComiAFP(BigDecimal comiAFP) {
        this.comiAFP = comiAFP;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiAFP != null ? codiAFP.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Afp)) {
            return false;
        }
        Afp other = (Afp) object;
        if ((this.codiAFP == null && other.codiAFP != null) || (this.codiAFP != null && !this.codiAFP.equals(other.codiAFP))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Afp[ codiAFP=" + codiAFP + " ]";
    }
    
}
