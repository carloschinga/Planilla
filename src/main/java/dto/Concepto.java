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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "Concepto")
@NamedQueries({
    @NamedQuery(name = "Concepto.findAll", query = "SELECT c FROM Concepto c"),
    @NamedQuery(name = "Concepto.findByCodiConc", query = "SELECT c FROM Concepto c WHERE c.codiConc = :codiConc"),
    @NamedQuery(name = "Concepto.findByNombConc", query = "SELECT c FROM Concepto c WHERE c.nombConc = :nombConc"),
    @NamedQuery(name = "Concepto.findByOrdnConc", query = "SELECT c FROM Concepto c WHERE c.ordnConc = :ordnConc"),
    @NamedQuery(name = "Concepto.findByCodiPlant", query = "SELECT c FROM Concepto c WHERE c.codiPlant = :codiPlant"),
    @NamedQuery(name = "Concepto.findByTipoConc", query = "SELECT c FROM Concepto c WHERE c.tipoConc = :tipoConc"),
    @NamedQuery(name = "Concepto.findByCateConc", query = "SELECT c FROM Concepto c WHERE c.cateConc = :cateConc"),
    @NamedQuery(name = "Concepto.findByValor", query = "SELECT c FROM Concepto c WHERE c.valor = :valor"),
    @NamedQuery(name = "Concepto.findByFormula", query = "SELECT c FROM Concepto c WHERE c.formula = :formula"),
    @NamedQuery(name = "Concepto.findByVisible", query = "SELECT c FROM Concepto c WHERE c.visible = :visible")})
public class Concepto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiConc")
    private Integer codiConc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombConc")
    private String nombConc;
    @Column(name = "ordnConc")
    private Integer ordnConc;
    @Column(name = "codiPlant")
    private Integer codiPlant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipoConc")
    private int tipoConc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cateConc")
    private int cateConc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Size(max = 200)
    @Column(name = "formula")
    private String formula;
    @Column(name = "visible")
    private Boolean visible;

    public Concepto() {
    }

    public Concepto(Integer codiConc) {
        this.codiConc = codiConc;
    }

    public Concepto(Integer codiConc, String nombConc, int tipoConc, int cateConc) {
        this.codiConc = codiConc;
        this.nombConc = nombConc;
        this.tipoConc = tipoConc;
        this.cateConc = cateConc;
    }

    public Integer getCodiConc() {
        return codiConc;
    }

    public void setCodiConc(Integer codiConc) {
        this.codiConc = codiConc;
    }

    public String getNombConc() {
        return nombConc;
    }

    public void setNombConc(String nombConc) {
        this.nombConc = nombConc;
    }

    public Integer getOrdnConc() {
        return ordnConc;
    }

    public void setOrdnConc(Integer ordnConc) {
        this.ordnConc = ordnConc;
    }

    public Integer getCodiPlant() {
        return codiPlant;
    }

    public void setCodiPlant(Integer codiPlant) {
        this.codiPlant = codiPlant;
    }

    public int getTipoConc() {
        return tipoConc;
    }

    public void setTipoConc(int tipoConc) {
        this.tipoConc = tipoConc;
    }

    public int getCateConc() {
        return cateConc;
    }

    public void setCateConc(int cateConc) {
        this.cateConc = cateConc;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiConc != null ? codiConc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Concepto)) {
            return false;
        }
        Concepto other = (Concepto) object;
        if ((this.codiConc == null && other.codiConc != null) || (this.codiConc != null && !this.codiConc.equals(other.codiConc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Concepto[ codiConc=" + codiConc + " ]";
    }
    
}
