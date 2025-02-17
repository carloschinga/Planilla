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
@Table(name = "vista_planilla_concepto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaPlanillaConcepto.findAll", query = "SELECT v FROM VistaPlanillaConcepto v"),
    @NamedQuery(name = "VistaPlanillaConcepto.findByCodiPlani", query = "SELECT v FROM VistaPlanillaConcepto v WHERE v.codiPlani = :codiPlani"),
    @NamedQuery(name = "VistaPlanillaConcepto.findByCodiPeri", query = "SELECT v FROM VistaPlanillaConcepto v WHERE v.codiPeri = :codiPeri"), 
    @NamedQuery(name = "VistaPlanillaConcepto.findByCodiPeriAndActvDeta", query = "SELECT v FROM VistaPlanillaConcepto v WHERE v.codiPeri = :codiPeri and v.actvDeta = :actvDeta ORDER BY v.codiPers,v.ordnConc "), 
    @NamedQuery(name = "VistaPlanillaConcepto.findByCodiPers", query = "SELECT v FROM VistaPlanillaConcepto v WHERE v.codiPers = :codiPers"),
    @NamedQuery(name = "VistaPlanillaConcepto.findByActiPlani", query = "SELECT v FROM VistaPlanillaConcepto v WHERE v.actiPlani = :actiPlani"),
    @NamedQuery(name = "VistaPlanillaConcepto.findByNombPers", query = "SELECT v FROM VistaPlanillaConcepto v WHERE v.nombPers = :nombPers"),
    @NamedQuery(name = "VistaPlanillaConcepto.findByCodiDeta", query = "SELECT v FROM VistaPlanillaConcepto v WHERE v.codiDeta = :codiDeta"),
    @NamedQuery(name = "VistaPlanillaConcepto.findByCodiConc", query = "SELECT v FROM VistaPlanillaConcepto v WHERE v.codiConc = :codiConc"),
    @NamedQuery(name = "VistaPlanillaConcepto.findByNombConc", query = "SELECT v FROM VistaPlanillaConcepto v WHERE v.nombConc = :nombConc"),
    @NamedQuery(name = "VistaPlanillaConcepto.findByOrdnConc", query = "SELECT v FROM VistaPlanillaConcepto v WHERE v.ordnConc = :ordnConc"),
    @NamedQuery(name = "VistaPlanillaConcepto.findByTipoConc", query = "SELECT v FROM VistaPlanillaConcepto v WHERE v.tipoConc = :tipoConc"),
    @NamedQuery(name = "VistaPlanillaConcepto.findByCateConc", query = "SELECT v FROM VistaPlanillaConcepto v WHERE v.cateConc = :cateConc"),
    @NamedQuery(name = "VistaPlanillaConcepto.findByValor", query = "SELECT v FROM VistaPlanillaConcepto v WHERE v.valor = :valor"),
    @NamedQuery(name = "VistaPlanillaConcepto.findByFormula", query = "SELECT v FROM VistaPlanillaConcepto v WHERE v.formula = :formula"),
    @NamedQuery(name = "VistaPlanillaConcepto.findByVisible", query = "SELECT v FROM VistaPlanillaConcepto v WHERE v.visible = :visible"),
    @NamedQuery(name = "VistaPlanillaConcepto.findByActvDeta", query = "SELECT v FROM VistaPlanillaConcepto v WHERE v.actvDeta = :actvDeta")})
public class VistaPlanillaConcepto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codiPlani")
    private int codiPlani;
     @Id
    @Column(name = "codiPeri")
    private Integer codiPeri;
    @Id
    @Column(name = "codiPers")
    private Integer codiPers;
    @Column(name = "actiPlani")
    private Boolean actiPlani;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombPers")
    private String nombPers;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codiDeta")
    private int codiDeta;
     @Id
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

    public VistaPlanillaConcepto() {
    }

    public VistaPlanillaConcepto(int codiDeta) {
        this.codiDeta = codiDeta;
    }

    public VistaPlanillaConcepto(int codiDeta, String nombPers) {
        this.codiDeta = codiDeta;
        this.nombPers = nombPers;
    }

    public int getCodiPlani() {
        return codiPlani;
    }

    public void setCodiPlani(int codiPlani) {
        this.codiPlani = codiPlani;
    }

    public Integer getCodiPeri() {
        return codiPeri;
    }

    public void setCodiPeri(Integer codiPeri) {
        this.codiPeri = codiPeri;
    }

    public Integer getCodiPers() {
        return codiPers;
    }

    public void setCodiPers(Integer codiPers) {
        this.codiPers = codiPers;
    }

    public Boolean getActiPlani() {
        return actiPlani;
    }

    public void setActiPlani(Boolean actiPlani) {
        this.actiPlani = actiPlani;
    }

    public String getNombPers() {
        return nombPers;
    }

    public void setNombPers(String nombPers) {
        this.nombPers = nombPers;
    }

    public int getCodiDeta() {
        return codiDeta;
    }

    public void setCodiDeta(int codiDeta) {
        this.codiDeta = codiDeta;
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
        hash += (int) codiDeta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VistaPlanillaConcepto)) {
            return false;
        }
        VistaPlanillaConcepto other = (VistaPlanillaConcepto) object;
        if (this.codiDeta != other.codiDeta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.VistaPlanillaConcepto[ codiDeta=" + codiDeta + " ]";
    }
    
}
