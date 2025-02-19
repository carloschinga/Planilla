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
@Table(name = "Usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByCodiUsua", query = "SELECT u FROM Usuario u WHERE u.codiUsua = :codiUsua"),
    @NamedQuery(name = "Usuario.findByLogiUsua", query = "SELECT u FROM Usuario u WHERE u.logiUsua = :logiUsua"),
    @NamedQuery(name = "Usuario.findByPassUsua", query = "SELECT u FROM Usuario u WHERE u.passUsua = :passUsua"),
    @NamedQuery(name = "Usuario.findByNdniUsua", query = "SELECT u FROM Usuario u WHERE u.ndniUsua = :ndniUsua"),
    @NamedQuery(name = "Usuario.findByNombUsua", query = "SELECT u FROM Usuario u WHERE u.nombUsua = :nombUsua"),
    @NamedQuery(name = "Usuario.findByCeluUsua", query = "SELECT u FROM Usuario u WHERE u.celuUsua = :celuUsua"),
    @NamedQuery(name = "Usuario.findByCodiRol", query = "SELECT u FROM Usuario u WHERE u.codiRol = :codiRol"),
    @NamedQuery(name = "Usuario.findByActvUsua", query = "SELECT u FROM Usuario u WHERE u.actvUsua = :actvUsua")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiUsua")
    private Integer codiUsua;
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
    @Column(name = "codiRol")
    private Integer codiRol;
    @Column(name = "actvUsua")
    private Boolean actvUsua;

    public Usuario() {
    }

    public Usuario(Integer codiUsua) {
        this.codiUsua = codiUsua;
    }

    public Integer getCodiUsua() {
        return codiUsua;
    }

    public void setCodiUsua(Integer codiUsua) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiUsua != null ? codiUsua.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.codiUsua == null && other.codiUsua != null) || (this.codiUsua != null && !this.codiUsua.equals(other.codiUsua))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Usuario[ codiUsua=" + codiUsua + " ]";
    }
    
}
