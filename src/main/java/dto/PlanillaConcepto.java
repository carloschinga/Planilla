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
@Table(name = "PlanillaConcepto")
@NamedQueries({
    @NamedQuery(name = "PlanillaConcepto.findAll", query = "SELECT p FROM PlanillaConcepto p"),
    @NamedQuery(name = "PlanillaConcepto.findByCodiDeta", query = "SELECT p FROM PlanillaConcepto p WHERE p.codiDeta = :codiDeta"),
    @NamedQuery(name = "PlanillaConcepto.findByCodiPlani", query = "SELECT p FROM PlanillaConcepto p WHERE p.codiPlani = :codiPlani"),
    @NamedQuery(name = "PlanillaConcepto.findByCodiConc", query = "SELECT p FROM PlanillaConcepto p WHERE p.codiConc = :codiConc"),
    @NamedQuery(name = "PlanillaConcepto.findByNombConc", query = "SELECT p FROM PlanillaConcepto p WHERE p.nombConc = :nombConc"),
    @NamedQuery(name = "PlanillaConcepto.findByOrdnConc", query = "SELECT p FROM PlanillaConcepto p WHERE p.ordnConc = :ordnConc"),
    @NamedQuery(name = "PlanillaConcepto.findByTipoConc", query = "SELECT p FROM PlanillaConcepto p WHERE p.tipoConc = :tipoConc"),
    @NamedQuery(name = "PlanillaConcepto.findByCateConc", query = "SELECT p FROM PlanillaConcepto p WHERE p.cateConc = :cateConc"),
    @NamedQuery(name = "PlanillaConcepto.findByValor", query = "SELECT p FROM PlanillaConcepto p WHERE p.valor = :valor"),
    @NamedQuery(name = "PlanillaConcepto.findByFormula", query = "SELECT p FROM PlanillaConcepto p WHERE p.formula = :formula"),
    @NamedQuery(name = "PlanillaConcepto.findByVisible", query = "SELECT p FROM PlanillaConcepto p WHERE p.visible = :visible"),
    @NamedQuery(name = "PlanillaConcepto.findByActvDeta", query = "SELECT p FROM PlanillaConcepto p WHERE p.actvDeta = :actvDeta")})
public class PlanillaConcepto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiDeta")
    private Integer codiDeta;
    @Column(name = "codiPlani")
    private Integer codiPlani;
    @Column(name = "codiConc")
    private Integer codiConc;
    @Size(max = 70)
    @Column(name = "nombConc")
    private String nombConc;
    @Column(name = "ordnConc")
    private Integer ordnConc;
    @Column(name = "tipoConc")
    private Integer tipoConc;
    @Column(name = "cateConc")
    private Integer cateConc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Size(max = 200)
    @Column(name = "formula")
    private String formula;
    @Column(name = "visible")
    private Boolean visible;
    @Column(name = "actvDeta")
    private Boolean actvDeta;

    public PlanillaConcepto() {
    }

    public PlanillaConcepto(Integer codiDeta, Integer codiPlani, Integer codiConc, String nombConc, Integer ordnConc, Integer tipoConc, Integer cateConc, BigDecimal valor, String formula, Boolean visible, Boolean actvDeta) {
        this.codiDeta = codiDeta;
        this.codiPlani = codiPlani;
        this.codiConc = codiConc;
        this.nombConc = nombConc;
        this.ordnConc = ordnConc;
        this.tipoConc = tipoConc;
        this.cateConc = cateConc;
        this.valor = valor;
        this.formula = formula;
        this.visible = visible;
        this.actvDeta = actvDeta;
    }

    public PlanillaConcepto(Integer codiDeta) {
        this.codiDeta = codiDeta;
    }
     public PlanillaConcepto(Integer codiDeta,BigDecimal valor) {
        this.codiDeta = codiDeta;
        this.valor=valor;
    }

    public Integer getCodiDeta() {
        return codiDeta;
    }

    public void setCodiDeta(Integer codiDeta) {
        this.codiDeta = codiDeta;
    }

    public Integer getCodiPlani() {
        return codiPlani;
    }

    public void setCodiPlani(Integer codiPlani) {
        this.codiPlani = codiPlani;
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

    public Integer getTipoConc() {
        return tipoConc;
    }

    public void setTipoConc(Integer tipoConc) {
        this.tipoConc = tipoConc;
    }

    public Integer getCateConc() {
        return cateConc;
    }

    public void setCateConc(Integer cateConc) {
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

    public Boolean getActvDeta() {
        return actvDeta;
    }

    public void setActvDeta(Boolean actvDeta) {
        this.actvDeta = actvDeta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiDeta != null ? codiDeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanillaConcepto)) {
            return false;
        }
        PlanillaConcepto other = (PlanillaConcepto) object;
        if ((this.codiDeta == null && other.codiDeta != null) || (this.codiDeta != null && !this.codiDeta.equals(other.codiDeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.PlanillaConcepto[ codiDeta=" + codiDeta + " ]";
    }
    
}
