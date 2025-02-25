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
@Table(name = "Pagina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagina.findAll", query = "SELECT p FROM Pagina p"),
    @NamedQuery(name = "Pagina.findByCodiPagi", query = "SELECT p FROM Pagina p WHERE p.codiPagi = :codiPagi"),
    @NamedQuery(name = "Pagina.findByNombPagi", query = "SELECT p FROM Pagina p WHERE p.nombPagi = :nombPagi"),
    @NamedQuery(name = "Pagina.findByHtmlPagi", query = "SELECT p FROM Pagina p WHERE p.htmlPagi = :htmlPagi"),
    @NamedQuery(name = "Pagina.findBySeccPagi", query = "SELECT p FROM Pagina p WHERE p.seccPagi = :seccPagi")})
public class Pagina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiPagi")
    private Integer codiPagi;
    @Size(max = 50)
    @Column(name = "nombPagi")
    private String nombPagi;
    @Size(max = 200)
    @Column(name = "htmlPagi")
    private String htmlPagi;
    @Column(name = "seccPagi")
    private Boolean seccPagi;

    public Pagina() {
    }

    public Pagina(Integer codiPagi) {
        this.codiPagi = codiPagi;
    }

    public Integer getCodiPagi() {
        return codiPagi;
    }

    public void setCodiPagi(Integer codiPagi) {
        this.codiPagi = codiPagi;
    }

    public String getNombPagi() {
        return nombPagi;
    }

    public void setNombPagi(String nombPagi) {
        this.nombPagi = nombPagi;
    }

    public String getHtmlPagi() {
        return htmlPagi;
    }

    public void setHtmlPagi(String htmlPagi) {
        this.htmlPagi = htmlPagi;
    }

    public Boolean getSeccPagi() {
        return seccPagi;
    }

    public void setSeccPagi(Boolean seccPagi) {
        this.seccPagi = seccPagi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiPagi != null ? codiPagi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagina)) {
            return false;
        }
        Pagina other = (Pagina) object;
        if ((this.codiPagi == null && other.codiPagi != null) || (this.codiPagi != null && !this.codiPagi.equals(other.codiPagi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Pagina[ codiPagi=" + codiPagi + " ]";
    }
    
}
