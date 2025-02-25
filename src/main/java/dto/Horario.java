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
 * @author san21
 */
@Entity
@Table(name = "Horario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h"),
    @NamedQuery(name = "Horario.findByCodiHora", query = "SELECT h FROM Horario h WHERE h.codiHora = :codiHora"),
    @NamedQuery(name = "Horario.findByNombHora", query = "SELECT h FROM Horario h WHERE h.nombHora = :nombHora")})
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiHora")
    private Integer codiHora;
    @Size(max = 50)
    @Column(name = "nombHora")
    private String nombHora;

    public Horario() {
    }

    public Horario(Integer codiHora) {
        this.codiHora = codiHora;
    }

    public Integer getCodiHora() {
        return codiHora;
    }

    public void setCodiHora(Integer codiHora) {
        this.codiHora = codiHora;
    }

    public String getNombHora() {
        return nombHora;
    }

    public void setNombHora(String nombHora) {
        this.nombHora = nombHora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiHora != null ? codiHora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.codiHora == null && other.codiHora != null) || (this.codiHora != null && !this.codiHora.equals(other.codiHora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Horario[ codiHora=" + codiHora + " ]";
    }
    
}
