/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.model;

import br.com.cirurgica.generated.model.Estadocivil;
import br.com.cirurgica.model.Usuario;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author dfelix3
 */
@Entity
@Table(name = "pessoa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p"),
    @NamedQuery(name = "Pessoa.findByCdPessoa", query = "SELECT p FROM Pessoa p WHERE p.cdPessoa = :cdPessoa"),
    @NamedQuery(name = "Pessoa.findByNmPessoa", query = "SELECT p FROM Pessoa p WHERE p.nmPessoa = :nmPessoa"),
    @NamedQuery(name = "Pessoa.findByRg", query = "SELECT p FROM Pessoa p WHERE p.rg = :rg"),
    @NamedQuery(name = "Pessoa.findByCpf", query = "SELECT p FROM Pessoa p WHERE p.cpf = :cpf"),
    @NamedQuery(name = "Pessoa.findByDataNascimento", query = "SELECT p FROM Pessoa p WHERE p.dataNascimento = :dataNascimento"),
    @NamedQuery(name = "Pessoa.findBySexo", query = "SELECT p FROM Pessoa p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "Pessoa.findByNmEndereco", query = "SELECT p FROM Pessoa p WHERE p.nmEndereco = :nmEndereco"),
    @NamedQuery(name = "Pessoa.findByNumeroEndereco", query = "SELECT p FROM Pessoa p WHERE p.numeroEndereco = :numeroEndereco"),
    @NamedQuery(name = "Pessoa.findByBairro", query = "SELECT p FROM Pessoa p WHERE p.bairro = :bairro"),
    @NamedQuery(name = "Pessoa.findByComplemento", query = "SELECT p FROM Pessoa p WHERE p.complemento = :complemento"),
    @NamedQuery(name = "Pessoa.findByCep", query = "SELECT p FROM Pessoa p WHERE p.cep = :cep"),
    @NamedQuery(name = "Pessoa.findByTelefoneFixo1", query = "SELECT p FROM Pessoa p WHERE p.telefoneFixo1 = :telefoneFixo1"),
    @NamedQuery(name = "Pessoa.findByTelefoneFixo2", query = "SELECT p FROM Pessoa p WHERE p.telefoneFixo2 = :telefoneFixo2"),
    @NamedQuery(name = "Pessoa.findByTelefoneCelular1", query = "SELECT p FROM Pessoa p WHERE p.telefoneCelular1 = :telefoneCelular1"),
    @NamedQuery(name = "Pessoa.findByTelefoneCelular2", query = "SELECT p FROM Pessoa p WHERE p.telefoneCelular2 = :telefoneCelular2"),
    @NamedQuery(name = "Pessoa.findByEmail", query = "SELECT p FROM Pessoa p WHERE p.email = :email")})
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_pessoa")
    private Integer cdPessoa;
    @Column(name = "nm_pessoa")
    private String nmPessoa;
    @Column(name = "RG")
    private String rg;
    @Column(name = "CPF")
    private String cpf;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "nm_endereco")
    private String nmEndereco;
    @Column(name = "numero_endereco")
    private String numeroEndereco;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "complemento")
    private String complemento;
    @Column(name = "CEP")
    private String cep;
    @Column(name = "telefone_fixo1")
    private String telefoneFixo1;
    @Column(name = "telefone_fixo2")
    private String telefoneFixo2;
    @Column(name = "telefone_celular1")
    private String telefoneCelular1;
    @Column(name = "telefone_celular2")
    private String telefoneCelular2;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "cdPessoa")
    private Collection<Usuario> usuarioCollection;
    @JoinColumn(name = "cd_cidade", referencedColumnName = "cd_cidade")
    @ManyToOne
    private br.com.cirurgica.generated.model.Cidade cdCidade;
    @JoinColumn(name = "cd_estado_civil", referencedColumnName = "cd_estado_civil")
    @ManyToOne
    private Estadocivil cdEstadoCivil;

    public Pessoa() {
    }

    public Pessoa(Integer cdPessoa) {
        this.cdPessoa = cdPessoa;
    }

    public Integer getCdPessoa() {
        return cdPessoa;
    }

    public void setCdPessoa(Integer cdPessoa) {
        this.cdPessoa = cdPessoa;
    }

    public String getNmPessoa() {
        return nmPessoa;
    }

    public void setNmPessoa(String nmPessoa) {
        this.nmPessoa = nmPessoa;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNmEndereco() {
        return nmEndereco;
    }

    public void setNmEndereco(String nmEndereco) {
        this.nmEndereco = nmEndereco;
    }

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefoneFixo1() {
        return telefoneFixo1;
    }

    public void setTelefoneFixo1(String telefoneFixo1) {
        this.telefoneFixo1 = telefoneFixo1;
    }

    public String getTelefoneFixo2() {
        return telefoneFixo2;
    }

    public void setTelefoneFixo2(String telefoneFixo2) {
        this.telefoneFixo2 = telefoneFixo2;
    }

    public String getTelefoneCelular1() {
        return telefoneCelular1;
    }

    public void setTelefoneCelular1(String telefoneCelular1) {
        this.telefoneCelular1 = telefoneCelular1;
    }

    public String getTelefoneCelular2() {
        return telefoneCelular2;
    }

    public void setTelefoneCelular2(String telefoneCelular2) {
        this.telefoneCelular2 = telefoneCelular2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    public br.com.cirurgica.generated.model.Cidade getCdCidade() {
        return cdCidade;
    }

    public void setCdCidade(br.com.cirurgica.generated.model.Cidade cdCidade) {
        this.cdCidade = cdCidade;
    }

    public Estadocivil getCdEstadoCivil() {
        return cdEstadoCivil;
    }

    public void setCdEstadoCivil(Estadocivil cdEstadoCivil) {
        this.cdEstadoCivil = cdEstadoCivil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdPessoa != null ? cdPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof br.com.cirurgica.model.Pessoa)) {
            return false;
        }
        br.com.cirurgica.model.Pessoa other = (br.com.cirurgica.model.Pessoa) object;
        if ((this.cdPessoa == null && other.cdPessoa != null) || (this.cdPessoa != null && !this.cdPessoa.equals(other.cdPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cirurgica.generated.model.Pessoa[ cdPessoa=" + cdPessoa + " ]";
    }
    
}
