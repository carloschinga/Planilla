/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "MacHabilitadas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MacHabilitadas.findAll", query = "SELECT m FROM MacHabilitadas m"),
    @NamedQuery(name = "MacHabilitadas.findByCodiMacHabi", query = "SELECT m FROM MacHabilitadas m WHERE m.codiMacHabi = :codiMacHabi"),
    @NamedQuery(name = "MacHabilitadas.findByNumeMacHabi", query = "SELECT m FROM MacHabilitadas m WHERE m.numeMacHabi = :numeMacHabi")})
public class MacHabilitadas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiMacHabi")
    private Integer codiMacHabi;
    @Size(max = 18)
    @Column(name = "numeMacHabi")
    private String numeMacHabi;

    public MacHabilitadas() {
    }

    public MacHabilitadas(Integer codiMacHabi) {
        this.codiMacHabi = codiMacHabi;
    }

    public Integer getCodiMacHabi() {
        return codiMacHabi;
    }

    public void setCodiMacHabi(Integer codiMacHabi) {
        this.codiMacHabi = codiMacHabi;
    }

    public String getNumeMacHabi() {
        return numeMacHabi;
    }

    public void setNumeMacHabi(String numeMacHabi) {
        this.numeMacHabi = numeMacHabi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiMacHabi != null ? codiMacHabi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MacHabilitadas)) {
            return false;
        }
        MacHabilitadas other = (MacHabilitadas) object;
        if ((this.codiMacHabi == null && other.codiMacHabi != null) || (this.codiMacHabi != null && !this.codiMacHabi.equals(other.codiMacHabi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.MacHabilitadas[ codiMacHabi=" + codiMacHabi + " ]";
    }
    
}
