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
@Table(name = "vista_usuario_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaUsuarioRol.findAll", query = "SELECT v FROM VistaUsuarioRol v"),
    @NamedQuery(name = "VistaUsuarioRol.findByCodiUsua", query = "SELECT v FROM VistaUsuarioRol v WHERE v.codiUsua = :codiUsua"),
    @NamedQuery(name = "VistaUsuarioRol.validar", query = "SELECT v FROM VistaUsuarioRol v WHERE v.logiUsua = :logiUsua and v.passUsua = :passUsua"),
    @NamedQuery(name = "VistaUsuarioRol.findByLogiUsua", query = "SELECT v FROM VistaUsuarioRol v WHERE v.logiUsua = :logiUsua"),
    @NamedQuery(name = "VistaUsuarioRol.findByPassUsua", query = "SELECT v FROM VistaUsuarioRol v WHERE v.passUsua = :passUsua"),
    @NamedQuery(name = "VistaUsuarioRol.findByNdniUsua", query = "SELECT v FROM VistaUsuarioRol v WHERE v.ndniUsua = :ndniUsua"),
    @NamedQuery(name = "VistaUsuarioRol.findByNombUsua", query = "SELECT v FROM VistaUsuarioRol v WHERE v.nombUsua = :nombUsua"),
    @NamedQuery(name = "VistaUsuarioRol.findByCeluUsua", query = "SELECT v FROM VistaUsuarioRol v WHERE v.celuUsua = :celuUsua"),
    @NamedQuery(name = "VistaUsuarioRol.findByCodiRol", query = "SELECT v FROM VistaUsuarioRol v WHERE v.codiRol = :codiRol"),
    @NamedQuery(name = "VistaUsuarioRol.findByActvUsua", query = "SELECT v FROM VistaUsuarioRol v WHERE v.actvUsua = :actvUsua"),
    @NamedQuery(name = "VistaUsuarioRol.findByNombRol", query = "SELECT v FROM VistaUsuarioRol v WHERE v.nombRol = :nombRol"),
    @NamedQuery(name = "VistaUsuarioRol.findByAdmiRol", query = "SELECT v FROM VistaUsuarioRol v WHERE v.admiRol = :admiRol")})
public class VistaUsuarioRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codiUsua")
    private int codiUsua;
    @Size(max = 100)
    @Column(name = "logiUsua")
    private String logiUsua;
    @Size(max = 100)
    @Column(name = "passUsua")
    private String passUsua;
    @Size(max = 8)
    @Column(name = "ndniUsua")
    private String ndniUsua;
    @Size(max = 100)
    @Column(name = "nombUsua")
    private String nombUsua;
    @Size(max = 9)
    @Column(name = "celuUsua")
    private String celuUsua;
    @Id
    @Column(name = "codiRol")
    private Integer codiRol;
    @Column(name = "actvUsua")
    private Boolean actvUsua;
    @Size(max = 10)
    @Column(name = "nombRol")
    private String nombRol;
    @Column(name = "admiRol")
    private Boolean admiRol;

    public VistaUsuarioRol() {
    }

    public VistaUsuarioRol(Integer codiRol) {
        this.codiRol = codiRol;
    }

    public int getCodiUsua() {
        return codiUsua;
    }

    public void setCodiUsua(int codiUsua) {
        this.codiUsua = codiUsua;
    }

    public String getLogiUsua() {
        return logiUsua;
    }

    public void setLogiUsua(String logiUsua) {
        this.logiUsua = logiUsua;
    }

    public String getPassUsua() {
        return passUsua;
    }

    public void setPassUsua(String passUsua) {
        this.passUsua = passUsua;
    }

    public String getNdniUsua() {
        return ndniUsua;
    }

    public void setNdniUsua(String ndniUsua) {
        this.ndniUsua = ndniUsua;
    }

    public String getNombUsua() {
        return nombUsua;
    }

    public void setNombUsua(String nombUsua) {
        this.nombUsua = nombUsua;
    }

    public String getCeluUsua() {
        return celuUsua;
    }

    public void setCeluUsua(String celuUsua) {
        this.celuUsua = celuUsua;
    }

    public Integer getCodiRol() {
        return codiRol;
    }

    public void setCodiRol(Integer codiRol) {
        this.codiRol = codiRol;
    }

    public Boolean getActvUsua() {
        return actvUsua;
    }

    public void setActvUsua(Boolean actvUsua) {
        this.actvUsua = actvUsua;
    }

    public String getNombRol() {
        return nombRol;
    }

    public void setNombRol(String nombRol) {
        this.nombRol = nombRol;
    }

    public Boolean getAdmiRol() {
        return admiRol;
    }

    public void setAdmiRol(Boolean admiRol) {
        this.admiRol = admiRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiRol != null ? codiRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VistaUsuarioRol)) {
            return false;
        }
        VistaUsuarioRol other = (VistaUsuarioRol) object;
        if ((this.codiRol == null && other.codiRol != null) || (this.codiRol != null && !this.codiRol.equals(other.codiRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.VistaUsuarioRol[ codiRol=" + codiRol + " ]";
    }

}
