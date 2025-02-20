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
@Table(name = "Periodo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Periodo.findAll", query = "SELECT p FROM Periodo p"),
    @NamedQuery(name = "Periodo.findByNombPeri", query = "SELECT p FROM Periodo p WHERE p.nombPeri = :nombPeri"),
    @NamedQuery(name = "Periodo.findBySelePeri", query = "SELECT p FROM Periodo p WHERE p.selePeri = :selePeri"),
    @NamedQuery(name = "Periodo.findByActiPeri", query = "SELECT p FROM Periodo p WHERE p.actiPeri = :actiPeri"),
    @NamedQuery(name = "Periodo.findByCodiPeri", query = "SELECT p FROM Periodo p WHERE p.codiPeri = :codiPeri")})
public class Periodo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 150)
    @Column(name = "nombPeri")
    private String nombPeri;
    @Column(name = "selePeri")
    private Boolean selePeri;
    @Column(name = "actiPeri")
    private Boolean actiPeri;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiPeri")
    private Integer codiPeri;

    public Periodo() {
    }

    public Periodo(Integer codiPeri) {
        this.codiPeri = codiPeri;
    }

    public String getNombPeri() {
        return nombPeri;
    }

    public void setNombPeri(String nombPeri) {
        this.nombPeri = nombPeri;
    }

    public Boolean getSelePeri() {
        return selePeri;
    }

    public void setSelePeri(Boolean selePeri) {
        this.selePeri = selePeri;
    }

    public Boolean getActiPeri() {
        return actiPeri;
    }

    public void setActiPeri(Boolean actiPeri) {
        this.actiPeri = actiPeri;
    }

    public Integer getCodiPeri() {
        return codiPeri;
    }

    public void setCodiPeri(Integer codiPeri) {
        this.codiPeri = codiPeri;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiPeri != null ? codiPeri.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodo)) {
            return false;
        }
        Periodo other = (Periodo) object;
        if ((this.codiPeri == null && other.codiPeri != null) || (this.codiPeri != null && !this.codiPeri.equals(other.codiPeri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Periodo[ codiPeri=" + codiPeri + " ]";
    }
    
}
