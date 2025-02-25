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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "RolPagina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolPagina.findAll", query = "SELECT r FROM RolPagina r"),
    @NamedQuery(name = "RolPagina.findByCodiRolPagi", query = "SELECT r FROM RolPagina r WHERE r.codiRolPagi = :codiRolPagi"),
    @NamedQuery(name = "RolPagina.findByCodiRol", query = "SELECT r FROM RolPagina r WHERE r.codiRol = :codiRol"),
    @NamedQuery(name = "RolPagina.findByCodiPagi", query = "SELECT r FROM RolPagina r WHERE r.codiPagi = :codiPagi"),
    @NamedQuery(name = "RolPagina.findByActiRolPagi", query = "SELECT r FROM RolPagina r WHERE r.actiRolPagi = :actiRolPagi")})
public class RolPagina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiRolPagi")
    private Integer codiRolPagi;
    @Column(name = "codiRol")
    private Integer codiRol;
    @Column(name = "codiPagi")
    private Integer codiPagi;
    @Column(name = "actiRolPagi")
    private Boolean actiRolPagi;

    public RolPagina() {
    }

    public RolPagina(Integer codiRolPagi) {
        this.codiRolPagi = codiRolPagi;
    }

    public Integer getCodiRolPagi() {
        return codiRolPagi;
    }

    public void setCodiRolPagi(Integer codiRolPagi) {
        this.codiRolPagi = codiRolPagi;
    }

    public Integer getCodiRol() {
        return codiRol;
    }

    public void setCodiRol(Integer codiRol) {
        this.codiRol = codiRol;
    }

    public Integer getCodiPagi() {
        return codiPagi;
    }

    public void setCodiPagi(Integer codiPagi) {
        this.codiPagi = codiPagi;
    }

    public Boolean getActiRolPagi() {
        return actiRolPagi;
    }

    public void setActiRolPagi(Boolean actiRolPagi) {
        this.actiRolPagi = actiRolPagi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiRolPagi != null ? codiRolPagi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolPagina)) {
            return false;
        }
        RolPagina other = (RolPagina) object;
        if ((this.codiRolPagi == null && other.codiRolPagi != null) || (this.codiRolPagi != null && !this.codiRolPagi.equals(other.codiRolPagi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.RolPagina[ codiRolPagi=" + codiRolPagi + " ]";
    }
    
}
