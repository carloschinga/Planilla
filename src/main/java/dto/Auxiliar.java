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
@Table(name = "Auxiliar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auxiliar.findAll", query = "SELECT a FROM Auxiliar a"),
    @NamedQuery(name = "Auxiliar.findByCodiAux", query = "SELECT a FROM Auxiliar a WHERE a.codiAux = :codiAux"),
    @NamedQuery(name = "Auxiliar.findByNombAux", query = "SELECT a FROM Auxiliar a WHERE a.nombAux = :nombAux"),
    @NamedQuery(name = "Auxiliar.findByCodiPeri", query = "SELECT a FROM Auxiliar a WHERE a.codiPeri = :codiPeri"),
    @NamedQuery(name = "Auxiliar.findByActvAux", query = "SELECT a FROM Auxiliar a WHERE a.actvAux = :actvAux")})
public class Auxiliar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiAux")
    private Integer codiAux;
    @Size(max = 150)
    @Column(name = "nombAux")
    private String nombAux;
    @Column(name = "codiPeri")
    private Integer codiPeri;
    @Column(name = "actvAux")
    private Boolean actvAux;

    public Auxiliar() {
    }

    public Auxiliar(Integer codiAux) {
        this.codiAux = codiAux;
    }

    public Integer getCodiAux() {
        return codiAux;
    }

    public void setCodiAux(Integer codiAux) {
        this.codiAux = codiAux;
    }

    public String getNombAux() {
        return nombAux;
    }

    public void setNombAux(String nombAux) {
        this.nombAux = nombAux;
    }

    public Integer getCodiPeri() {
        return codiPeri;
    }

    public void setCodiPeri(Integer codiPeri) {
        this.codiPeri = codiPeri;
    }

    public Boolean getActvAux() {
        return actvAux;
    }

    public void setActvAux(Boolean actvAux) {
        this.actvAux = actvAux;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiAux != null ? codiAux.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auxiliar)) {
            return false;
        }
        Auxiliar other = (Auxiliar) object;
        if ((this.codiAux == null && other.codiAux != null) || (this.codiAux != null && !this.codiAux.equals(other.codiAux))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Auxiliar[ codiAux=" + codiAux + " ]";
    }
    
}
