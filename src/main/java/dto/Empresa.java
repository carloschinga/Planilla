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

/**
 *
 * @author USER
 */
@Entity
@Table(name = "Empresa")
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByCodiEmpr", query = "SELECT e FROM Empresa e WHERE e.codiEmpr = :codiEmpr"),
    @NamedQuery(name = "Empresa.findByNrucEmpr", query = "SELECT e FROM Empresa e WHERE e.nrucEmpr = :nrucEmpr"),
    @NamedQuery(name = "Empresa.findByNombEmpr", query = "SELECT e FROM Empresa e WHERE e.nombEmpr = :nombEmpr"),
    @NamedQuery(name = "Empresa.findByDefaEmpr", query = "SELECT e FROM Empresa e WHERE e.defaEmpr = :defaEmpr")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiEmpr")
    private Integer codiEmpr;
    @Size(max = 11)
    @Column(name = "nrucEmpr")
    private String nrucEmpr;
    @Size(max = 200)
    @Column(name = "nombEmpr")
    private String nombEmpr;
    @Column(name = "defaEmpr")
    private Boolean defaEmpr;

    public Empresa() {
    }

    public Empresa(Integer codiEmpr) {
        this.codiEmpr = codiEmpr;
    }

    public Integer getCodiEmpr() {
        return codiEmpr;
    }

    public void setCodiEmpr(Integer codiEmpr) {
        this.codiEmpr = codiEmpr;
    }

    public String getNrucEmpr() {
        return nrucEmpr;
    }

    public void setNrucEmpr(String nrucEmpr) {
        this.nrucEmpr = nrucEmpr;
    }

    public String getNombEmpr() {
        return nombEmpr;
    }

    public void setNombEmpr(String nombEmpr) {
        this.nombEmpr = nombEmpr;
    }

    public Boolean getDefaEmpr() {
        return defaEmpr;
    }

    public void setDefaEmpr(Boolean defaEmpr) {
        this.defaEmpr = defaEmpr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiEmpr != null ? codiEmpr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.codiEmpr == null && other.codiEmpr != null) || (this.codiEmpr != null && !this.codiEmpr.equals(other.codiEmpr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Empresa[ codiEmpr=" + codiEmpr + " ]";
    }
    
}
