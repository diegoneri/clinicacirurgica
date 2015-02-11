/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.model;

import br.com.cirurgica.generated.model.Cidade;
import br.com.cirurgica.model.Produto;
import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author dfelix3
 */
@Entity
@Table(name = "fornecedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM Fornecedor f"),
    @NamedQuery(name = "Fornecedor.findByCdFornecedor", query = "SELECT f FROM Fornecedor f WHERE f.cdFornecedor = :cdFornecedor"),
    @NamedQuery(name = "Fornecedor.findByNmRazaoSocial", query = "SELECT f FROM Fornecedor f WHERE f.nmRazaoSocial LIKE :nmRazaoSocial"),
    @NamedQuery(name = "Fornecedor.findByCnpj", query = "SELECT f FROM Fornecedor f WHERE f.cnpj LIKE :cnpj"),
    @NamedQuery(name = "Fornecedor.findByNmEndereco", query = "SELECT f FROM Fornecedor f WHERE f.nmEndereco = :nmEndereco"),
    @NamedQuery(name = "Fornecedor.findByNumeroEndereco", query = "SELECT f FROM Fornecedor f WHERE f.numeroEndereco = :numeroEndereco"),
    @NamedQuery(name = "Fornecedor.findByBairro", query = "SELECT f FROM Fornecedor f WHERE f.bairro = :bairro"),
    @NamedQuery(name = "Fornecedor.findByComplemento", query = "SELECT f FROM Fornecedor f WHERE f.complemento = :complemento"),
    @NamedQuery(name = "Fornecedor.findByCep", query = "SELECT f FROM Fornecedor f WHERE f.cep = :cep"),
    @NamedQuery(name = "Fornecedor.findByTelefoneFixo1", query = "SELECT f FROM Fornecedor f WHERE f.telefoneFixo1 = :telefoneFixo1"),
    @NamedQuery(name = "Fornecedor.findByTelefoneFixo2", query = "SELECT f FROM Fornecedor f WHERE f.telefoneFixo2 = :telefoneFixo2"),
    @NamedQuery(name = "Fornecedor.findByTelefoneCelular1", query = "SELECT f FROM Fornecedor f WHERE f.telefoneCelular1 = :telefoneCelular1"),
    @NamedQuery(name = "Fornecedor.findByTelefoneCelular2", query = "SELECT f FROM Fornecedor f WHERE f.telefoneCelular2 = :telefoneCelular2"),
    @NamedQuery(name = "Fornecedor.findByEmail", query = "SELECT f FROM Fornecedor f WHERE f.email = :email"),
    @NamedQuery(name = "Fornecedor.findByNomeFantasia", query = "SELECT f FROM Fornecedor f WHERE f.nomeFantasia LIKE :nomeFantasia")})
public class Fornecedor extends AbstractModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_fornecedor")
    private Integer cdFornecedor;
    @Column(name = "nm_razao_social")
    private String nmRazaoSocial;
    @Column(name = "CNPJ")
    private String cnpj;
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
    @Column(name = "nome_fantasia")
    private String nomeFantasia;
    @JoinColumn(name = "cd_cidade", referencedColumnName = "cd_cidade")
    @ManyToOne
    private Cidade cdCidade;
    @OneToMany(mappedBy = "fornecedor")
    private Collection<FornecedorProduto> fornecedorProdutoCollection;
    @OneToMany(mappedBy = "cdFornecedor")
    private Collection<Pedido> pedidoCollection;

    public Fornecedor() {
    }

    public Fornecedor(Integer cdFornecedor) {
        this.cdFornecedor = cdFornecedor;
    }

    public Integer getCdFornecedor() {
        return cdFornecedor;
    }

    public void setCdFornecedor(Integer cdFornecedor) {
        this.cdFornecedor = cdFornecedor;
    }

    public String getNmRazaoSocial() {
        return nmRazaoSocial;
    }

    public void setNmRazaoSocial(String nmRazaoSocial) {
        this.nmRazaoSocial = nmRazaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public Cidade getCdCidade() {
        return cdCidade;
    }

    public void setCdCidade(Cidade cdCidade) {
        this.cdCidade = cdCidade;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<FornecedorProduto> getFornecedorProdutoCollection() {
        return fornecedorProdutoCollection;
    }

    public void setProdutoCollection(Collection<FornecedorProduto> fornecedorprodutoCollection) {
        this.fornecedorProdutoCollection = fornecedorprodutoCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdFornecedor != null ? cdFornecedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
        if ((this.cdFornecedor == null && other.cdFornecedor != null) || (this.cdFornecedor != null && !this.cdFornecedor.equals(other.cdFornecedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + this.getCdFornecedor() + "] " + this.getNmRazaoSocial();
    }

}
