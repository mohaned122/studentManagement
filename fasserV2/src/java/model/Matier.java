/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author zayoud_mohanned
 */
@Entity
@Table(name = "matier")
@NamedQueries({
    @NamedQuery(name = "Matier.findAll", query = "SELECT m FROM Matier m"),
    @NamedQuery(name = "Matier.findById", query = "SELECT m FROM Matier m WHERE m.id = :id"),
    @NamedQuery(name = "Matier.findByNomMatier", query = "SELECT m FROM Matier m WHERE m.nomMatier = :nomMatier"),
    @NamedQuery(name = "Matier.findByDurre", query = "SELECT m FROM Matier m WHERE m.durre = :durre"),
    @NamedQuery(name = "Matier.findBySmester", query = "SELECT m FROM Matier m WHERE m.smester = :smester"),
    @NamedQuery(name = "Matier.findByProf", query = "SELECT m FROM Matier m WHERE m.prof = :prof"),
    @NamedQuery(name = "Matier.findByAnnee", query = "SELECT m FROM Matier m WHERE m.annee = :annee")})
public class Matier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nomMatier")
    private String nomMatier;
    @Column(name = "durre")
    private Integer durre;
    @Column(name = "smester")
    private String smester;
    @Column(name = "prof")
    private String prof;
    @Column(name = "annee")
    @Temporal(TemporalType.DATE)
    private Date annee;

    public Matier() {
    }

    public Matier(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomMatier() {
        return nomMatier;
    }

    public void setNomMatier(String nomMatier) {
        this.nomMatier = nomMatier;
    }

    public Integer getDurre() {
        return durre;
    }

    public void setDurre(Integer durre) {
        this.durre = durre;
    }

    public String getSmester() {
        return smester;
    }

    public void setSmester(String smester) {
        this.smester = smester;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public Date getAnnee() {
        return annee;
    }

    public void setAnnee(Date annee) {
        this.annee = annee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matier)) {
            return false;
        }
        Matier other = (Matier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Matier[ id=" + id + " ]";
    }
    
}
