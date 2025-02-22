/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "Feriado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feriado.findAll", query = "SELECT f FROM Feriado f"),
    @NamedQuery(name = "Feriado.findByCodiFeri", query = "SELECT f FROM Feriado f WHERE f.codiFeri = :codiFeri"),
    @NamedQuery(name = "Feriado.findByFechFeri", query = "SELECT f FROM Feriado f WHERE f.fechFeri = :fechFeri")})
public class Feriado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiFeri")
    private Integer codiFeri;
    @Column(name = "fechFeri")
    @Temporal(TemporalType.DATE)
    private Date fechFeri;

    public Feriado() {
    }

    public Feriado(Integer codiFeri) {
        this.codiFeri = codiFeri;
    }

    public Integer getCodiFeri() {
        return codiFeri;
    }

    public void setCodiFeri(Integer codiFeri) {
        this.codiFeri = codiFeri;
    }

    public Date getFechFeri() {
        return fechFeri;
    }

    public void setFechFeri(Date fechFeri) {
        this.fechFeri = fechFeri;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiFeri != null ? codiFeri.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feriado)) {
            return false;
        }
        Feriado other = (Feriado) object;
        if ((this.codiFeri == null && other.codiFeri != null) || (this.codiFeri != null && !this.codiFeri.equals(other.codiFeri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Feriado[ codiFeri=" + codiFeri + " ]";
    }
    
}
