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
@Table(name = "vista_persona_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaPersonaDetalle.findAll", query = "SELECT v FROM VistaPersonaDetalle v"),
    @NamedQuery(name = "VistaPersonaDetalle.findByCodiPers", query = "SELECT v FROM VistaPersonaDetalle v WHERE v.codiPers = :codiPers"),
    @NamedQuery(name = "VistaPersonaDetalle.findByNumeDocu", query = "SELECT v FROM VistaPersonaDetalle v WHERE v.numeDocu = :numeDocu"),
    @NamedQuery(name = "VistaPersonaDetalle.findByNombPers", query = "SELECT v FROM VistaPersonaDetalle v WHERE v.nombPers = :nombPers"),
    @NamedQuery(name = "VistaPersonaDetalle.findBySuelPers", query = "SELECT v FROM VistaPersonaDetalle v WHERE v.suelPers = :suelPers"),
    @NamedQuery(name = "VistaPersonaDetalle.findByCodiAFP", query = "SELECT v FROM VistaPersonaDetalle v WHERE v.codiAFP = :codiAFP"),
    @NamedQuery(name = "VistaPersonaDetalle.findByNombAFP", query = "SELECT v FROM VistaPersonaDetalle v WHERE v.nombAFP = :nombAFP"),
    @NamedQuery(name = "VistaPersonaDetalle.findByMontAFP", query = "SELECT v FROM VistaPersonaDetalle v WHERE v.montAFP = :montAFP"),
    @NamedQuery(name = "VistaPersonaDetalle.findBySeguAFP", query = "SELECT v FROM VistaPersonaDetalle v WHERE v.seguAFP = :seguAFP"),
    @NamedQuery(name = "VistaPersonaDetalle.findByComiAFP", query = "SELECT v FROM VistaPersonaDetalle v WHERE v.comiAFP = :comiAFP"),
    @NamedQuery(name = "VistaPersonaDetalle.findByActiPers", query = "SELECT v FROM VistaPersonaDetalle v WHERE v.actiPers = :actiPers"),
    @NamedQuery(name = "VistaPersonaDetalle.findByAsigFamiPers", query = "SELECT v FROM VistaPersonaDetalle v WHERE v.asigFamiPers = :asigFamiPers"),
    @NamedQuery(name = "VistaPersonaDetalle.findByCodiPlant", query = "SELECT v FROM VistaPersonaDetalle v WHERE v.codiPlant = :codiPlant"),
    @NamedQuery(name = "VistaPersonaDetalle.findByNombPlant", query = "SELECT v FROM VistaPersonaDetalle v WHERE v.nombPlant = :nombPlant")})
public class VistaPersonaDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codiPers")
    private int codiPers;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "numeDocu")
    private String numeDocu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombPers")
    private String nombPers;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "suelPers")
    private BigDecimal suelPers;
    @Column(name = "codiAFP")
    private Integer codiAFP;
    @Size(max = 150)
    @Column(name = "nombAFP")
    private String nombAFP;
    @Column(name = "montAFP")
    private BigDecimal montAFP;
    @Column(name = "seguAFP")
    private BigDecimal seguAFP;
    @Column(name = "comiAFP")
    private BigDecimal comiAFP;
    @Basic(optional = false)
    @NotNull
    @Column(name = "actiPers")
    private boolean actiPers;
    @Column(name = "asigFamiPers")
    private Integer asigFamiPers;
    @Column(name = "codiPlant")
    private Integer codiPlant;
    @Size(max = 50)
    @Column(name = "nombPlant")
    private String nombPlant;

    public VistaPersonaDetalle() {
    }

    public int getCodiPers() {
        return codiPers;
    }

    public void setCodiPers(int codiPers) {
        this.codiPers = codiPers;
    }

    public String getNumeDocu() {
        return numeDocu;
    }

    public void setNumeDocu(String numeDocu) {
        this.numeDocu = numeDocu;
    }

    public String getNombPers() {
        return nombPers;
    }

    public void setNombPers(String nombPers) {
        this.nombPers = nombPers;
    }

    public BigDecimal getSuelPers() {
        return suelPers;
    }

    public void setSuelPers(BigDecimal suelPers) {
        this.suelPers = suelPers;
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

    public boolean getActiPers() {
        return actiPers;
    }

    public void setActiPers(boolean actiPers) {
        this.actiPers = actiPers;
    }

    public Integer getAsigFamiPers() {
        return asigFamiPers;
    }

    public void setAsigFamiPers(Integer asigFamiPers) {
        this.asigFamiPers = asigFamiPers;
    }

    public Integer getCodiPlant() {
        return codiPlant;
    }

    public void setCodiPlant(Integer codiPlant) {
        this.codiPlant = codiPlant;
    }

    public String getNombPlant() {
        return nombPlant;
    }

    public void setNombPlant(String nombPlant) {
        this.nombPlant = nombPlant;
    }
    
}
