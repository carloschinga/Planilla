/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author san21
 */
@Entity
@Table(name = "Persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByCodiPers", query = "SELECT p FROM Persona p WHERE p.codiPers = :codiPers"),
    @NamedQuery(name = "Persona.findByCodiTipoDoc", query = "SELECT p FROM Persona p WHERE p.codiTipoDoc = :codiTipoDoc"),
    @NamedQuery(name = "Persona.findByNumeDocu", query = "SELECT p FROM Persona p WHERE p.numeDocu = :numeDocu AND p.codiTipoDoc = :codiTipoDoc"),
    @NamedQuery(name = "Persona.findByCodiPaisEmis", query = "SELECT p FROM Persona p WHERE p.codiPaisEmis = :codiPaisEmis"),
    @NamedQuery(name = "Persona.findByAppaPers", query = "SELECT p FROM Persona p WHERE p.appaPers = :appaPers"),
    @NamedQuery(name = "Persona.findByApmaPers", query = "SELECT p FROM Persona p WHERE p.apmaPers = :apmaPers"),
    @NamedQuery(name = "Persona.findByNombPers", query = "SELECT p FROM Persona p WHERE p.nombPers = :nombPers"),
    @NamedQuery(name = "Persona.findBySexoPers", query = "SELECT p FROM Persona p WHERE p.sexoPers = :sexoPers"),
    @NamedQuery(name = "Persona.findByCodiNaci", query = "SELECT p FROM Persona p WHERE p.codiNaci = :codiNaci"),
    @NamedQuery(name = "Persona.findByCodiCLDN", query = "SELECT p FROM Persona p WHERE p.codiCLDN = :codiCLDN"),
    @NamedQuery(name = "Persona.findByNumeCelu", query = "SELECT p FROM Persona p WHERE p.numeCelu = :numeCelu"),
    @NamedQuery(name = "Persona.findByMailPers", query = "SELECT p FROM Persona p WHERE p.mailPers = :mailPers"),
    @NamedQuery(name = "Persona.findByCodiTipoVia", query = "SELECT p FROM Persona p WHERE p.codiTipoVia = :codiTipoVia"),
    @NamedQuery(name = "Persona.findByNombVia", query = "SELECT p FROM Persona p WHERE p.nombVia = :nombVia"),
    @NamedQuery(name = "Persona.findByNumeVia", query = "SELECT p FROM Persona p WHERE p.numeVia = :numeVia"),
    @NamedQuery(name = "Persona.findByDepaPers", query = "SELECT p FROM Persona p WHERE p.depaPers = :depaPers"),
    @NamedQuery(name = "Persona.findByIntePers", query = "SELECT p FROM Persona p WHERE p.intePers = :intePers"),
    @NamedQuery(name = "Persona.findByManzPers", query = "SELECT p FROM Persona p WHERE p.manzPers = :manzPers"),
    @NamedQuery(name = "Persona.findByLotePers", query = "SELECT p FROM Persona p WHERE p.lotePers = :lotePers"),
    @NamedQuery(name = "Persona.findByKiloPers", query = "SELECT p FROM Persona p WHERE p.kiloPers = :kiloPers"),
    @NamedQuery(name = "Persona.findByBlocPers", query = "SELECT p FROM Persona p WHERE p.blocPers = :blocPers"),
    @NamedQuery(name = "Persona.findByEtapPers", query = "SELECT p FROM Persona p WHERE p.etapPers = :etapPers"),
    @NamedQuery(name = "Persona.findByTipoZona", query = "SELECT p FROM Persona p WHERE p.tipoZona = :tipoZona"),
    @NamedQuery(name = "Persona.findByNombZona", query = "SELECT p FROM Persona p WHERE p.nombZona = :nombZona"),
    @NamedQuery(name = "Persona.findByRefeZona", query = "SELECT p FROM Persona p WHERE p.refeZona = :refeZona"),
    @NamedQuery(name = "Persona.findByCodiUbig", query = "SELECT p FROM Persona p WHERE p.codiUbig = :codiUbig"),
    @NamedQuery(name = "Persona.findByCodiTipoVia2", query = "SELECT p FROM Persona p WHERE p.codiTipoVia2 = :codiTipoVia2"),
    @NamedQuery(name = "Persona.findByNombVia2", query = "SELECT p FROM Persona p WHERE p.nombVia2 = :nombVia2"),
    @NamedQuery(name = "Persona.findByNumeVia2", query = "SELECT p FROM Persona p WHERE p.numeVia2 = :numeVia2"),
    @NamedQuery(name = "Persona.findByDepaPers2", query = "SELECT p FROM Persona p WHERE p.depaPers2 = :depaPers2"),
    @NamedQuery(name = "Persona.findByIntePers2", query = "SELECT p FROM Persona p WHERE p.intePers2 = :intePers2"),
    @NamedQuery(name = "Persona.findByManzPers2", query = "SELECT p FROM Persona p WHERE p.manzPers2 = :manzPers2"),
    @NamedQuery(name = "Persona.findByLotePers2", query = "SELECT p FROM Persona p WHERE p.lotePers2 = :lotePers2"),
    @NamedQuery(name = "Persona.findByKiloPers2", query = "SELECT p FROM Persona p WHERE p.kiloPers2 = :kiloPers2"),
    @NamedQuery(name = "Persona.findByBlocPers2", query = "SELECT p FROM Persona p WHERE p.blocPers2 = :blocPers2"),
    @NamedQuery(name = "Persona.findByEtapPers2", query = "SELECT p FROM Persona p WHERE p.etapPers2 = :etapPers2"),
    @NamedQuery(name = "Persona.findByTipoZona2", query = "SELECT p FROM Persona p WHERE p.tipoZona2 = :tipoZona2"),
    @NamedQuery(name = "Persona.findByNombZona2", query = "SELECT p FROM Persona p WHERE p.nombZona2 = :nombZona2"),
    @NamedQuery(name = "Persona.findByRefeZona2", query = "SELECT p FROM Persona p WHERE p.refeZona2 = :refeZona2"),
    @NamedQuery(name = "Persona.findByCodiUbig2", query = "SELECT p FROM Persona p WHERE p.codiUbig2 = :codiUbig2"),
    @NamedQuery(name = "Persona.findByIndcCentAsis", query = "SELECT p FROM Persona p WHERE p.indcCentAsis = :indcCentAsis"),
    @NamedQuery(name = "Persona.findByCodiRegiLabo", query = "SELECT p FROM Persona p WHERE p.codiRegiLabo = :codiRegiLabo"),
    @NamedQuery(name = "Persona.findByCodiSituEduc", query = "SELECT p FROM Persona p WHERE p.codiSituEduc = :codiSituEduc"),
    @NamedQuery(name = "Persona.findByCodiTipoOcup", query = "SELECT p FROM Persona p WHERE p.codiTipoOcup = :codiTipoOcup"),
    @NamedQuery(name = "Persona.findByTipoOcup", query = "SELECT p FROM Persona p WHERE p.tipoOcup = :tipoOcup"),
    @NamedQuery(name = "Persona.findByDiscPers", query = "SELECT p FROM Persona p WHERE p.discPers = :discPers"),
    @NamedQuery(name = "Persona.findByCupsPers", query = "SELECT p FROM Persona p WHERE p.cupsPers = :cupsPers"),
    @NamedQuery(name = "Persona.findBySctrPers", query = "SELECT p FROM Persona p WHERE p.sctrPers = :sctrPers"),
    @NamedQuery(name = "Persona.findBySnpPers", query = "SELECT p FROM Persona p WHERE p.snpPers = :snpPers"),
    @NamedQuery(name = "Persona.findByCodiAFP", query = "SELECT p FROM Persona p WHERE p.codiAFP = :codiAFP"),
    @NamedQuery(name = "Persona.findByCodiTipoCntr", query = "SELECT p FROM Persona p WHERE p.codiTipoCntr = :codiTipoCntr"),
    @NamedQuery(name = "Persona.findBySujeregiAltr", query = "SELECT p FROM Persona p WHERE p.sujeregiAltr = :sujeregiAltr"),
    @NamedQuery(name = "Persona.findBySujeJornTrab", query = "SELECT p FROM Persona p WHERE p.sujeJornTrab = :sujeJornTrab"),
    @NamedQuery(name = "Persona.findBySujeHoraNoct", query = "SELECT p FROM Persona p WHERE p.sujeHoraNoct = :sujeHoraNoct"),
    @NamedQuery(name = "Persona.findBySindPers", query = "SELECT p FROM Persona p WHERE p.sindPers = :sindPers"),
    @NamedQuery(name = "Persona.findByCodiPeriRemu", query = "SELECT p FROM Persona p WHERE p.codiPeriRemu = :codiPeriRemu"),
    @NamedQuery(name = "Persona.findByMontRemuPers", query = "SELECT p FROM Persona p WHERE p.montRemuPers = :montRemuPers"),
    @NamedQuery(name = "Persona.findByCodiSituTRegi", query = "SELECT p FROM Persona p WHERE p.codiSituTRegi = :codiSituTRegi"),
    @NamedQuery(name = "Persona.findByRentQuinta", query = "SELECT p FROM Persona p WHERE p.rentQuinta = :rentQuinta"),
    @NamedQuery(name = "Persona.findByCodiSituEspe", query = "SELECT p FROM Persona p WHERE p.codiSituEspe = :codiSituEspe"),
    @NamedQuery(name = "Persona.findByCodiTipoPago", query = "SELECT p FROM Persona p WHERE p.codiTipoPago = :codiTipoPago"),
    @NamedQuery(name = "Persona.findByCodiCateOcup", query = "SELECT p FROM Persona p WHERE p.codiCateOcup = :codiCateOcup"),
    @NamedQuery(name = "Persona.findByCodiConvDoblTrib", query = "SELECT p FROM Persona p WHERE p.codiConvDoblTrib = :codiConvDoblTrib"),
    @NamedQuery(name = "Persona.findByNumeRucTrab", query = "SELECT p FROM Persona p WHERE p.numeRucTrab = :numeRucTrab"),
    @NamedQuery(name = "Persona.findByAsigFamiPers", query = "SELECT p FROM Persona p WHERE p.asigFamiPers = :asigFamiPers"),
    @NamedQuery(name = "Persona.findByFechInicPlan", query = "SELECT p FROM Persona p WHERE p.fechInicPlan = :fechInicPlan"),
    @NamedQuery(name = "Persona.findByFechFinPlan", query = "SELECT p FROM Persona p WHERE p.fechFinPlan = :fechFinPlan"),
    @NamedQuery(name = "Persona.findByCodiEntiBanc", query = "SELECT p FROM Persona p WHERE p.codiEntiBanc = :codiEntiBanc"),
    @NamedQuery(name = "Persona.findByNumeCuen", query = "SELECT p FROM Persona p WHERE p.numeCuen = :numeCuen"),
    @NamedQuery(name = "Persona.findByCodiPlant", query = "SELECT p FROM Persona p WHERE p.codiPlant = :codiPlant"),
    @NamedQuery(name = "Persona.findByActiPers", query = "SELECT p FROM Persona p WHERE p.actiPers = :actiPers")})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiPers")
    private Integer codiPers;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "codiTipoDoc")
    private String codiTipoDoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "numeDocu")
    private String numeDocu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "codiPaisEmis")
    private String codiPaisEmis;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "appaPers")
    private String appaPers;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "apmaPers")
    private String apmaPers;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombPers")
    private String nombPers;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sexoPers")
    private Character sexoPers;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codiNaci")
    private String codiNaci;
    @Size(max = 3)
    @Column(name = "codiCLDN")
    private String codiCLDN;
    @Size(max = 9)
    @Column(name = "numeCelu")
    private String numeCelu;
    @Size(max = 50)
    @Column(name = "mailPers")
    private String mailPers;
    @Size(max = 2)
    @Column(name = "codiTipoVia")
    private String codiTipoVia;
    @Size(max = 20)
    @Column(name = "nombVia")
    private String nombVia;
    @Size(max = 4)
    @Column(name = "numeVia")
    private String numeVia;
    @Size(max = 4)
    @Column(name = "depaPers")
    private String depaPers;
    @Size(max = 4)
    @Column(name = "intePers")
    private String intePers;
    @Size(max = 4)
    @Column(name = "manzPers")
    private String manzPers;
    @Size(max = 4)
    @Column(name = "lotePers")
    private String lotePers;
    @Size(max = 4)
    @Column(name = "kiloPers")
    private String kiloPers;
    @Size(max = 4)
    @Column(name = "blocPers")
    private String blocPers;
    @Size(max = 4)
    @Column(name = "etapPers")
    private String etapPers;
    @Size(max = 2)
    @Column(name = "tipoZona")
    private String tipoZona;
    @Size(max = 20)
    @Column(name = "nombZona")
    private String nombZona;
    @Size(max = 40)
    @Column(name = "refeZona")
    private String refeZona;
    @Size(max = 6)
    @Column(name = "codiUbig")
    private String codiUbig;
    @Size(max = 2)
    @Column(name = "codiTipoVia2")
    private String codiTipoVia2;
    @Size(max = 20)
    @Column(name = "nombVia2")
    private String nombVia2;
    @Size(max = 4)
    @Column(name = "numeVia2")
    private String numeVia2;
    @Size(max = 4)
    @Column(name = "depaPers2")
    private String depaPers2;
    @Size(max = 4)
    @Column(name = "intePers2")
    private String intePers2;
    @Size(max = 4)
    @Column(name = "manzPers2")
    private String manzPers2;
    @Size(max = 4)
    @Column(name = "lotePers2")
    private String lotePers2;
    @Size(max = 4)
    @Column(name = "kiloPers2")
    private String kiloPers2;
    @Size(max = 4)
    @Column(name = "blocPers2")
    private String blocPers2;
    @Size(max = 4)
    @Column(name = "etapPers2")
    private String etapPers2;
    @Size(max = 2)
    @Column(name = "tipoZona2")
    private String tipoZona2;
    @Size(max = 20)
    @Column(name = "nombZona2")
    private String nombZona2;
    @Size(max = 40)
    @Column(name = "refeZona2")
    private String refeZona2;
    @Size(max = 6)
    @Column(name = "codiUbig2")
    private String codiUbig2;
    @Column(name = "indcCentAsis")
    private Character indcCentAsis;
    @Size(max = 2)
    @Column(name = "codiRegiLabo")
    private String codiRegiLabo;
    @Size(max = 2)
    @Column(name = "codiSituEduc")
    private String codiSituEduc;
    @Column(name = "codiTipoOcup")
    private Character codiTipoOcup;
    @Size(max = 6)
    @Column(name = "tipoOcup")
    private String tipoOcup;
    @Basic(optional = false)
    @NotNull
    @Column(name = "discPers")
    private Character discPers;
    @Size(max = 12)
    @Column(name = "cupsPers")
    private String cupsPers;
    @Column(name = "sctrPers")
    private Character sctrPers;
    @Basic(optional = false)
    @NotNull
    @Column(name = "snpPers")
    private boolean snpPers;
    @Column(name = "codiAFP")
    private Integer codiAFP;
    @Size(max = 2)
    @Column(name = "codiTipoCntr")
    private String codiTipoCntr;
    @Column(name = "sujeregiAltr")
    private Character sujeregiAltr;
    @Column(name = "sujeJornTrab")
    private Character sujeJornTrab;
    @Column(name = "sujeHoraNoct")
    private Character sujeHoraNoct;
    @Column(name = "sindPers")
    private Character sindPers;
    @Column(name = "codiPeriRemu")
    private Character codiPeriRemu;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montRemuPers")
    private BigDecimal montRemuPers;
    @Column(name = "codiSituTRegi")
    private Character codiSituTRegi;
    @Column(name = "rentQuinta")
    private Character rentQuinta;
    @Column(name = "codiSituEspe")
    private Character codiSituEspe;
    @Column(name = "codiTipoPago")
    private Character codiTipoPago;
    @Size(max = 2)
    @Column(name = "codiCateOcup")
    private String codiCateOcup;
    @Column(name = "codiConvDoblTrib")
    private Character codiConvDoblTrib;
    @Size(max = 11)
    @Column(name = "numeRucTrab")
    private String numeRucTrab;
    @Column(name = "asigFamiPers")
    private Integer asigFamiPers;
    @Column(name = "fechInicPlan")
    @Temporal(TemporalType.DATE)
    private Date fechInicPlan;
    @Column(name = "fechFinPlan")
    @Temporal(TemporalType.DATE)
    private Date fechFinPlan;
    @Size(max = 3)
    @Column(name = "codiEntiBanc")
    private String codiEntiBanc;
    @Size(max = 20)
    @Column(name = "numeCuen")
    private String numeCuen;
    @Column(name = "codiPlant")
    private Integer codiPlant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "actiPers")
    private boolean actiPers;

    public Persona() {
    }

    public Persona(Integer codiPers) {
        this.codiPers = codiPers;
    }

    public Persona(Integer codiPers, String codiTipoDoc, String numeDocu, String codiPaisEmis, String appaPers, String apmaPers, String nombPers, Character sexoPers, String codiNaci, Character discPers, boolean snpPers, boolean actiPers) {
        this.codiPers = codiPers;
        this.codiTipoDoc = codiTipoDoc;
        this.numeDocu = numeDocu;
        this.codiPaisEmis = codiPaisEmis;
        this.appaPers = appaPers;
        this.apmaPers = apmaPers;
        this.nombPers = nombPers;
        this.sexoPers = sexoPers;
        this.codiNaci = codiNaci;
        this.discPers = discPers;
        this.snpPers = snpPers;
        this.actiPers = actiPers;
    }

    public Integer getCodiPers() {
        return codiPers;
    }

    public void setCodiPers(Integer codiPers) {
        this.codiPers = codiPers;
    }

    public String getCodiTipoDoc() {
        return codiTipoDoc;
    }

    public void setCodiTipoDoc(String codiTipoDoc) {
        this.codiTipoDoc = codiTipoDoc;
    }

    public String getNumeDocu() {
        return numeDocu;
    }

    public void setNumeDocu(String numeDocu) {
        this.numeDocu = numeDocu;
    }

    public String getCodiPaisEmis() {
        return codiPaisEmis;
    }

    public void setCodiPaisEmis(String codiPaisEmis) {
        this.codiPaisEmis = codiPaisEmis;
    }

    public String getAppaPers() {
        return appaPers;
    }

    public void setAppaPers(String appaPers) {
        this.appaPers = appaPers;
    }

    public String getApmaPers() {
        return apmaPers;
    }

    public void setApmaPers(String apmaPers) {
        this.apmaPers = apmaPers;
    }

    public String getNombPers() {
        return nombPers;
    }

    public void setNombPers(String nombPers) {
        this.nombPers = nombPers;
    }

    public Character getSexoPers() {
        return sexoPers;
    }

    public void setSexoPers(Character sexoPers) {
        this.sexoPers = sexoPers;
    }

    public String getCodiNaci() {
        return codiNaci;
    }

    public void setCodiNaci(String codiNaci) {
        this.codiNaci = codiNaci;
    }

    public String getCodiCLDN() {
        return codiCLDN;
    }

    public void setCodiCLDN(String codiCLDN) {
        this.codiCLDN = codiCLDN;
    }

    public String getNumeCelu() {
        return numeCelu;
    }

    public void setNumeCelu(String numeCelu) {
        this.numeCelu = numeCelu;
    }

    public String getMailPers() {
        return mailPers;
    }

    public void setMailPers(String mailPers) {
        this.mailPers = mailPers;
    }

    public String getCodiTipoVia() {
        return codiTipoVia;
    }

    public void setCodiTipoVia(String codiTipoVia) {
        this.codiTipoVia = codiTipoVia;
    }

    public String getNombVia() {
        return nombVia;
    }

    public void setNombVia(String nombVia) {
        this.nombVia = nombVia;
    }

    public String getNumeVia() {
        return numeVia;
    }

    public void setNumeVia(String numeVia) {
        this.numeVia = numeVia;
    }

    public String getDepaPers() {
        return depaPers;
    }

    public void setDepaPers(String depaPers) {
        this.depaPers = depaPers;
    }

    public String getIntePers() {
        return intePers;
    }

    public void setIntePers(String intePers) {
        this.intePers = intePers;
    }

    public String getManzPers() {
        return manzPers;
    }

    public void setManzPers(String manzPers) {
        this.manzPers = manzPers;
    }

    public String getLotePers() {
        return lotePers;
    }

    public void setLotePers(String lotePers) {
        this.lotePers = lotePers;
    }

    public String getKiloPers() {
        return kiloPers;
    }

    public void setKiloPers(String kiloPers) {
        this.kiloPers = kiloPers;
    }

    public String getBlocPers() {
        return blocPers;
    }

    public void setBlocPers(String blocPers) {
        this.blocPers = blocPers;
    }

    public String getEtapPers() {
        return etapPers;
    }

    public void setEtapPers(String etapPers) {
        this.etapPers = etapPers;
    }

    public String getTipoZona() {
        return tipoZona;
    }

    public void setTipoZona(String tipoZona) {
        this.tipoZona = tipoZona;
    }

    public String getNombZona() {
        return nombZona;
    }

    public void setNombZona(String nombZona) {
        this.nombZona = nombZona;
    }

    public String getRefeZona() {
        return refeZona;
    }

    public void setRefeZona(String refeZona) {
        this.refeZona = refeZona;
    }

    public String getCodiUbig() {
        return codiUbig;
    }

    public void setCodiUbig(String codiUbig) {
        this.codiUbig = codiUbig;
    }

    public String getCodiTipoVia2() {
        return codiTipoVia2;
    }

    public void setCodiTipoVia2(String codiTipoVia2) {
        this.codiTipoVia2 = codiTipoVia2;
    }

    public String getNombVia2() {
        return nombVia2;
    }

    public void setNombVia2(String nombVia2) {
        this.nombVia2 = nombVia2;
    }

    public String getNumeVia2() {
        return numeVia2;
    }

    public void setNumeVia2(String numeVia2) {
        this.numeVia2 = numeVia2;
    }

    public String getDepaPers2() {
        return depaPers2;
    }

    public void setDepaPers2(String depaPers2) {
        this.depaPers2 = depaPers2;
    }

    public String getIntePers2() {
        return intePers2;
    }

    public void setIntePers2(String intePers2) {
        this.intePers2 = intePers2;
    }

    public String getManzPers2() {
        return manzPers2;
    }

    public void setManzPers2(String manzPers2) {
        this.manzPers2 = manzPers2;
    }

    public String getLotePers2() {
        return lotePers2;
    }

    public void setLotePers2(String lotePers2) {
        this.lotePers2 = lotePers2;
    }

    public String getKiloPers2() {
        return kiloPers2;
    }

    public void setKiloPers2(String kiloPers2) {
        this.kiloPers2 = kiloPers2;
    }

    public String getBlocPers2() {
        return blocPers2;
    }

    public void setBlocPers2(String blocPers2) {
        this.blocPers2 = blocPers2;
    }

    public String getEtapPers2() {
        return etapPers2;
    }

    public void setEtapPers2(String etapPers2) {
        this.etapPers2 = etapPers2;
    }

    public String getTipoZona2() {
        return tipoZona2;
    }

    public void setTipoZona2(String tipoZona2) {
        this.tipoZona2 = tipoZona2;
    }

    public String getNombZona2() {
        return nombZona2;
    }

    public void setNombZona2(String nombZona2) {
        this.nombZona2 = nombZona2;
    }

    public String getRefeZona2() {
        return refeZona2;
    }

    public void setRefeZona2(String refeZona2) {
        this.refeZona2 = refeZona2;
    }

    public String getCodiUbig2() {
        return codiUbig2;
    }

    public void setCodiUbig2(String codiUbig2) {
        this.codiUbig2 = codiUbig2;
    }

    public Character getIndcCentAsis() {
        return indcCentAsis;
    }

    public void setIndcCentAsis(Character indcCentAsis) {
        this.indcCentAsis = indcCentAsis;
    }

    public String getCodiRegiLabo() {
        return codiRegiLabo;
    }

    public void setCodiRegiLabo(String codiRegiLabo) {
        this.codiRegiLabo = codiRegiLabo;
    }

    public String getCodiSituEduc() {
        return codiSituEduc;
    }

    public void setCodiSituEduc(String codiSituEduc) {
        this.codiSituEduc = codiSituEduc;
    }

    public Character getCodiTipoOcup() {
        return codiTipoOcup;
    }

    public void setCodiTipoOcup(Character codiTipoOcup) {
        this.codiTipoOcup = codiTipoOcup;
    }

    public String getTipoOcup() {
        return tipoOcup;
    }

    public void setTipoOcup(String tipoOcup) {
        this.tipoOcup = tipoOcup;
    }

    public Character getDiscPers() {
        return discPers;
    }

    public void setDiscPers(Character discPers) {
        this.discPers = discPers;
    }

    public String getCupsPers() {
        return cupsPers;
    }

    public void setCupsPers(String cupsPers) {
        this.cupsPers = cupsPers;
    }

    public Character getSctrPers() {
        return sctrPers;
    }

    public void setSctrPers(Character sctrPers) {
        this.sctrPers = sctrPers;
    }

    public boolean getSnpPers() {
        return snpPers;
    }

    public void setSnpPers(boolean snpPers) {
        this.snpPers = snpPers;
    }

    public Integer getCodiAFP() {
        return codiAFP;
    }

    public void setCodiAFP(Integer codiAFP) {
        this.codiAFP = codiAFP;
    }

    public String getCodiTipoCntr() {
        return codiTipoCntr;
    }

    public void setCodiTipoCntr(String codiTipoCntr) {
        this.codiTipoCntr = codiTipoCntr;
    }

    public Character getSujeregiAltr() {
        return sujeregiAltr;
    }

    public void setSujeregiAltr(Character sujeregiAltr) {
        this.sujeregiAltr = sujeregiAltr;
    }

    public Character getSujeJornTrab() {
        return sujeJornTrab;
    }

    public void setSujeJornTrab(Character sujeJornTrab) {
        this.sujeJornTrab = sujeJornTrab;
    }

    public Character getSujeHoraNoct() {
        return sujeHoraNoct;
    }

    public void setSujeHoraNoct(Character sujeHoraNoct) {
        this.sujeHoraNoct = sujeHoraNoct;
    }

    public Character getSindPers() {
        return sindPers;
    }

    public void setSindPers(Character sindPers) {
        this.sindPers = sindPers;
    }

    public Character getCodiPeriRemu() {
        return codiPeriRemu;
    }

    public void setCodiPeriRemu(Character codiPeriRemu) {
        this.codiPeriRemu = codiPeriRemu;
    }

    public BigDecimal getMontRemuPers() {
        return montRemuPers;
    }

    public void setMontRemuPers(BigDecimal montRemuPers) {
        this.montRemuPers = montRemuPers;
    }

    public Character getCodiSituTRegi() {
        return codiSituTRegi;
    }

    public void setCodiSituTRegi(Character codiSituTRegi) {
        this.codiSituTRegi = codiSituTRegi;
    }

    public Character getRentQuinta() {
        return rentQuinta;
    }

    public void setRentQuinta(Character rentQuinta) {
        this.rentQuinta = rentQuinta;
    }

    public Character getCodiSituEspe() {
        return codiSituEspe;
    }

    public void setCodiSituEspe(Character codiSituEspe) {
        this.codiSituEspe = codiSituEspe;
    }

    public Character getCodiTipoPago() {
        return codiTipoPago;
    }

    public void setCodiTipoPago(Character codiTipoPago) {
        this.codiTipoPago = codiTipoPago;
    }

    public String getCodiCateOcup() {
        return codiCateOcup;
    }

    public void setCodiCateOcup(String codiCateOcup) {
        this.codiCateOcup = codiCateOcup;
    }

    public Character getCodiConvDoblTrib() {
        return codiConvDoblTrib;
    }

    public void setCodiConvDoblTrib(Character codiConvDoblTrib) {
        this.codiConvDoblTrib = codiConvDoblTrib;
    }

    public String getNumeRucTrab() {
        return numeRucTrab;
    }

    public void setNumeRucTrab(String numeRucTrab) {
        this.numeRucTrab = numeRucTrab;
    }

    public Integer getAsigFamiPers() {
        return asigFamiPers;
    }

    public void setAsigFamiPers(Integer asigFamiPers) {
        this.asigFamiPers = asigFamiPers;
    }

    public Date getFechInicPlan() {
        return fechInicPlan;
    }

    public void setFechInicPlan(Date fechInicPlan) {
        this.fechInicPlan = fechInicPlan;
    }

    public Date getFechFinPlan() {
        return fechFinPlan;
    }

    public void setFechFinPlan(Date fechFinPlan) {
        this.fechFinPlan = fechFinPlan;
    }

    public String getCodiEntiBanc() {
        return codiEntiBanc;
    }

    public void setCodiEntiBanc(String codiEntiBanc) {
        this.codiEntiBanc = codiEntiBanc;
    }

    public String getNumeCuen() {
        return numeCuen;
    }

    public void setNumeCuen(String numeCuen) {
        this.numeCuen = numeCuen;
    }

    public Integer getCodiPlant() {
        return codiPlant;
    }

    public void setCodiPlant(Integer codiPlant) {
        this.codiPlant = codiPlant;
    }

    public boolean getActiPers() {
        return actiPers;
    }

    public void setActiPers(boolean actiPers) {
        this.actiPers = actiPers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiPers != null ? codiPers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.codiPers == null && other.codiPers != null) || (this.codiPers != null && !this.codiPers.equals(other.codiPers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Persona[ codiPers=" + codiPers + " ]";
    }

}
