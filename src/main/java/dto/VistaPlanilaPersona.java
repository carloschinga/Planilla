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
@Table(name = "vista_planila_persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaPlanilaPersona.findAll", query = "SELECT v FROM VistaPlanilaPersona v"),
    @NamedQuery(name = "VistaPlanilaPersona.findByCodiPlani", query = "SELECT v FROM VistaPlanilaPersona v WHERE v.codiPlani = :codiPlani"),
    @NamedQuery(name = "VistaPlanilaPersona.findByCodiPeri", query = "SELECT v FROM VistaPlanilaPersona v WHERE v.codiPeri = :codiPeri"),
    @NamedQuery(name = "VistaPlanilaPersona.findByCodiPers", query = "SELECT v FROM VistaPlanilaPersona v WHERE v.codiPers = :codiPers"),
    @NamedQuery(name = "VistaPlanilaPersona.findByActiPlani", query = "SELECT v FROM VistaPlanilaPersona v WHERE v.actiPlani = :actiPlani"),
    @NamedQuery(name = "VistaPlanilaPersona.findByNombPers", query = "SELECT v FROM VistaPlanilaPersona v WHERE v.nombPers = :nombPers")})
public class VistaPlanilaPersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codiPlani")
    private int codiPlani;
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

    public VistaPlanilaPersona() {
    }

    public VistaPlanilaPersona(Integer codiPers) {
        this.codiPers = codiPers;
    }

    public VistaPlanilaPersona(Integer codiPers, String nombPers) {
        this.codiPers = codiPers;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiPers != null ? codiPers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VistaPlanilaPersona)) {
            return false;
        }
        VistaPlanilaPersona other = (VistaPlanilaPersona) object;
        if ((this.codiPers == null && other.codiPers != null) || (this.codiPers != null && !this.codiPers.equals(other.codiPers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.VistaPlanilaPersona[ codiPers=" + codiPers + " ]";
    }
    
}
