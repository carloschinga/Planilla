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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "Persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByCodiPers", query = "SELECT p FROM Persona p WHERE p.codiPers = :codiPers"),
    @NamedQuery(name = "Persona.findByNumeDocu", query = "SELECT p FROM Persona p WHERE p.numeDocu = :numeDocu"),
    @NamedQuery(name = "Persona.findByNombPers", query = "SELECT p FROM Persona p WHERE p.nombPers = :nombPers"),
    @NamedQuery(name = "Persona.findByCodiAFP", query = "SELECT p FROM Persona p WHERE p.codiAFP = :codiAFP"),
    @NamedQuery(name = "Persona.findByActiPers", query = "SELECT p FROM Persona p WHERE p.actiPers = :actiPers"),
    @NamedQuery(name = "Persona.findByCodiPlant", query = "SELECT p FROM Persona p WHERE p.codiPlant = :codiPlant"),
    @NamedQuery(name = "Persona.findBySuelPers", query = "SELECT p FROM Persona p WHERE p.suelPers = :suelPers"),
    @NamedQuery(name = "Persona.findByAsigFamiPers", query = "SELECT p FROM Persona p WHERE p.asigFamiPers = :asigFamiPers")})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiPers")
    private Integer codiPers;
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
    @Column(name = "codiAFP")
    private Integer codiAFP;
    @Basic(optional = false)
    @NotNull
    @Column(name = "actiPers")
    private boolean actiPers;
    @Column(name = "codiPlant")
    private Integer codiPlant;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "suelPers")
    private BigDecimal suelPers;
    @Column(name = "asigFamiPers")
    private Integer asigFamiPers;

    public Persona() {
    }

    public Persona(Integer codiPers) {
        this.codiPers = codiPers;
    }

    public Persona(Integer codiPers, String numeDocu, String nombPers, boolean actiPers) {
        this.codiPers = codiPers;
        this.numeDocu = numeDocu;
        this.nombPers = nombPers;
        this.actiPers = actiPers;
    }

    public Integer getCodiPers() {
        return codiPers;
    }

    public void setCodiPers(Integer codiPers) {
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

    public Integer getCodiAFP() {
        return codiAFP;
    }

    public void setCodiAFP(Integer codiAFP) {
        this.codiAFP = codiAFP;
    }

    public boolean getActiPers() {
        return actiPers;
    }

    public void setActiPers(boolean actiPers) {
        this.actiPers = actiPers;
    }

    public Integer getCodiPlant() {
        return codiPlant;
    }

    public void setCodiPlant(Integer codiPlant) {
        this.codiPlant = codiPlant;
    }

    public BigDecimal getSuelPers() {
        return suelPers;
    }

    public void setSuelPers(BigDecimal suelPers) {
        this.suelPers = suelPers;
    }

    public Integer getAsigFamiPers() {
        return asigFamiPers;
    }

    public void setAsigFamiPers(Integer asigFamiPers) {
        this.asigFamiPers = asigFamiPers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiPers != null ? codiPers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.codiPers == null && other.codiPers != null) || (this.codiPers != null && !this.codiPers.equals(other.codiPers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Persona[ codiPers=" + codiPers + " ]";
    }
    
}
