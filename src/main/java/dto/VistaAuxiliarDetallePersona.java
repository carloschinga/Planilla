/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "vista_auxiliar_detalle_persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaAuxiliarDetallePersona.findAll", query = "SELECT v FROM VistaAuxiliarDetallePersona v"),
    @NamedQuery(name = "VistaAuxiliarDetallePersona.findByCodiDetaAux", query = "SELECT v FROM VistaAuxiliarDetallePersona v WHERE v.codiDetaAux = :codiDetaAux"),
    @NamedQuery(name = "VistaAuxiliarDetallePersona.findByCodiAux", query = "SELECT v FROM VistaAuxiliarDetallePersona v WHERE v.codiAux = :codiAux"),
    @NamedQuery(name = "VistaAuxiliarDetallePersona.findByNombAux", query = "SELECT v FROM VistaAuxiliarDetallePersona v WHERE v.nombAux = :nombAux"),
    @NamedQuery(name = "VistaAuxiliarDetallePersona.findByCodiPers", query = "SELECT v FROM VistaAuxiliarDetallePersona v WHERE v.codiPers = :codiPers"),
    @NamedQuery(name = "VistaAuxiliarDetallePersona.findByDescDeta", query = "SELECT v FROM VistaAuxiliarDetallePersona v WHERE v.descDeta = :descDeta"),
    @NamedQuery(name = "VistaAuxiliarDetallePersona.findByMontDeta", query = "SELECT v FROM VistaAuxiliarDetallePersona v WHERE v.montDeta = :montDeta"),
    @NamedQuery(name = "VistaAuxiliarDetallePersona.findByNombPers", query = "SELECT v FROM VistaAuxiliarDetallePersona v WHERE v.nombPers = :nombPers"),
    @NamedQuery(name = "VistaAuxiliarDetallePersona.findByCodiPeri", query = "SELECT v FROM VistaAuxiliarDetallePersona v WHERE v.codiPeri = :codiPeri")})
public class VistaAuxiliarDetallePersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codiDetaAux")
    private int codiDetaAux;
    @Column(name = "codiAux")
    private Integer codiAux;
    @Size(max = 150)
    @Column(name = "nombAux")
    private String nombAux;
    @Column(name = "codiPers")
    private Integer codiPers;
    @Size(max = 150)
    @Column(name = "descDeta")
    private String descDeta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montDeta")
    private BigDecimal montDeta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombPers")
    private String nombPers;
    @Column(name = "codiPeri")
    private Integer codiPeri;

    public VistaAuxiliarDetallePersona() {
    }

    public int getCodiDetaAux() {
        return codiDetaAux;
    }

    public void setCodiDetaAux(int codiDetaAux) {
        this.codiDetaAux = codiDetaAux;
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

    public Integer getCodiPers() {
        return codiPers;
    }

    public void setCodiPers(Integer codiPers) {
        this.codiPers = codiPers;
    }

    public String getDescDeta() {
        return descDeta;
    }

    public void setDescDeta(String descDeta) {
        this.descDeta = descDeta;
    }

    public BigDecimal getMontDeta() {
        return montDeta;
    }

    public void setMontDeta(BigDecimal montDeta) {
        this.montDeta = montDeta;
    }

    public String getNombPers() {
        return nombPers;
    }

    public void setNombPers(String nombPers) {
        this.nombPers = nombPers;
    }

    public Integer getCodiPeri() {
        return codiPeri;
    }

    public void setCodiPeri(Integer codiPeri) {
        this.codiPeri = codiPeri;
    }
    
}
