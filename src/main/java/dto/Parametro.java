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
@Table(name = "Parametro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametro.findAll", query = "SELECT p FROM Parametro p"),
    @NamedQuery(name = "Parametro.findByCodiPara", query = "SELECT p FROM Parametro p WHERE p.codiPara = :codiPara"),
    @NamedQuery(name = "Parametro.findByNombPara", query = "SELECT p FROM Parametro p WHERE p.nombPara = :nombPara"),
    @NamedQuery(name = "Parametro.findByValuPara", query = "SELECT p FROM Parametro p WHERE p.valuPara = :valuPara")})
public class Parametro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiPara")
    private Integer codiPara;
    @Size(max = 50)
    @Column(name = "nombPara")
    private String nombPara;
    @Size(max = 200)
    @Column(name = "valuPara")
    private String valuPara;

    public Parametro() {
    }

    public Parametro(Integer codiPara) {
        this.codiPara = codiPara;
    }

    public Integer getCodiPara() {
        return codiPara;
    }

    public void setCodiPara(Integer codiPara) {
        this.codiPara = codiPara;
    }

    public String getNombPara() {
        return nombPara;
    }

    public void setNombPara(String nombPara) {
        this.nombPara = nombPara;
    }

    public String getValuPara() {
        return valuPara;
    }

    public void setValuPara(String valuPara) {
        this.valuPara = valuPara;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiPara != null ? codiPara.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametro)) {
            return false;
        }
        Parametro other = (Parametro) object;
        if ((this.codiPara == null && other.codiPara != null) || (this.codiPara != null && !this.codiPara.equals(other.codiPara))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Parametro[ codiPara=" + codiPara + " ]";
    }
    
}
