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
@Table(name = "CategoriaOcupacional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaOcupacional.findAll", query = "SELECT c FROM CategoriaOcupacional c"),
    @NamedQuery(name = "CategoriaOcupacional.findByCodiCateOcup", query = "SELECT c FROM CategoriaOcupacional c WHERE c.codiCateOcup = :codiCateOcup"),
    @NamedQuery(name = "CategoriaOcupacional.findByNombCateOcup", query = "SELECT c FROM CategoriaOcupacional c WHERE c.nombCateOcup = :nombCateOcup"),
    @NamedQuery(name = "CategoriaOcupacional.findBySectorPrivado", query = "SELECT c FROM CategoriaOcupacional c WHERE c.sectorPrivado = :sectorPrivado"),
    @NamedQuery(name = "CategoriaOcupacional.findBySectorPublico", query = "SELECT c FROM CategoriaOcupacional c WHERE c.sectorPublico = :sectorPublico"),
    @NamedQuery(name = "CategoriaOcupacional.findByOtras", query = "SELECT c FROM CategoriaOcupacional c WHERE c.otras = :otras")})
public class CategoriaOcupacional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "codiCateOcup")
    private String codiCateOcup;
    @Size(max = 50)
    @Column(name = "nombCateOcup")
    private String nombCateOcup;
    @Size(max = 4)
    @Column(name = "sectorPrivado")
    private String sectorPrivado;
    @Size(max = 4)
    @Column(name = "sectorPublico")
    private String sectorPublico;
    @Size(max = 4)
    @Column(name = "otras")
    private String otras;

    public CategoriaOcupacional() {
    }

    public CategoriaOcupacional(String codiCateOcup) {
        this.codiCateOcup = codiCateOcup;
    }

    public String getCodiCateOcup() {
        return codiCateOcup;
    }

    public void setCodiCateOcup(String codiCateOcup) {
        this.codiCateOcup = codiCateOcup;
    }

    public String getNombCateOcup() {
        return nombCateOcup;
    }

    public void setNombCateOcup(String nombCateOcup) {
        this.nombCateOcup = nombCateOcup;
    }

    public String getSectorPrivado() {
        return sectorPrivado;
    }

    public void setSectorPrivado(String sectorPrivado) {
        this.sectorPrivado = sectorPrivado;
    }

    public String getSectorPublico() {
        return sectorPublico;
    }

    public void setSectorPublico(String sectorPublico) {
        this.sectorPublico = sectorPublico;
    }

    public String getOtras() {
        return otras;
    }

    public void setOtras(String otras) {
        this.otras = otras;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiCateOcup != null ? codiCateOcup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaOcupacional)) {
            return false;
        }
        CategoriaOcupacional other = (CategoriaOcupacional) object;
        if ((this.codiCateOcup == null && other.codiCateOcup != null) || (this.codiCateOcup != null && !this.codiCateOcup.equals(other.codiCateOcup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.CategoriaOcupacional[ codiCateOcup=" + codiCateOcup + " ]";
    }
    
}
