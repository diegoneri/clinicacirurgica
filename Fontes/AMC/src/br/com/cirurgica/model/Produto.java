/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.model;

import br.com.cirurgica.generated.model.VendaProduto;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author dfelix3
 */
@Entity
@Table(name = "produto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p ORDER BY p.nome"),
    @NamedQuery(name = "Produto.findByFornecedor", query = "SELECT p FROM FornecedorProduto p WHERE p.fornecedor = :fornecedor ORDER BY p.produto.nome"),
    @NamedQuery(name = "Produto.findByCdProduto", query = "SELECT p FROM Produto p WHERE p.cdProduto = :cdProduto ORDER BY p.nome"),
    @NamedQuery(name = "Produto.findByNome", query = "SELECT p FROM Produto p WHERE p.nome LIKE :nome ORDER BY p.nome"),
    @NamedQuery(name = "Produto.findByDescricao", query = "SELECT p FROM Produto p WHERE p.descricao = :descricao ORDER BY p.nome"),
    @NamedQuery(name = "Produto.findByMarca", query = "SELECT p FROM Produto p WHERE p.marca = :marca ORDER BY p.nome"),
    @NamedQuery(name = "Produto.findByCor", query = "SELECT p FROM Produto p WHERE p.cor = :cor ORDER BY p.nome"),
    @NamedQuery(name = "Produto.findByCodigoBarras", query = "SELECT p FROM Produto p WHERE p.codigoBarras = :codigoBarras ORDER BY p.nome"),
    @NamedQuery(name = "Produto.findByValorCompra", query = "SELECT p FROM Produto p WHERE p.valorCompra = :valorCompra ORDER BY p.nome"),
    @NamedQuery(name = "Produto.findByValorVenda", query = "SELECT p FROM Produto p WHERE p.valorVenda = :valorVenda ORDER BY p.nome"),
    @NamedQuery(name = "Produto.findByQtdeEstoque", query = "SELECT p FROM Produto p WHERE p.qtdeEstoque = :qtdeEstoque ORDER BY p.nome"),
    @NamedQuery(name = "Produto.findByCdFarmaceutico", query = "SELECT p FROM Produto p WHERE p.cdFarmaceutico = :cdFarmaceutico ORDER BY p.nome"),
    @NamedQuery(name = "Produto.findAllByCdFarmaceutico", query = "SELECT p FROM Produto p WHERE p.cdFarmaceutico LIKE :cdFarmaceutico ORDER BY p.nome"),
    @NamedQuery(name = "Produto.findByValorVendaAtacado", query = "SELECT p FROM Produto p WHERE p.valorVendaAtacado = :valorVendaAtacado ORDER BY p.nome"),
    @NamedQuery(name = "Produto.findByTamanho", query = "SELECT p FROM Produto p WHERE p.tamanho = :tamanho ORDER BY p.nome")})
public class Produto extends AbstractModel implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PROP_CDPRODUTO = "PROP_CDPRODUTO";
    public static final String PROP_NOME = "PROP_NOME";
    public static final String PROP_DESCRICAO = "PROP_DESCRICAO";
    public static final String PROP_MARCA = "PROP_MARCA";
    public static final String PROP_COR = "PROP_COR";
    public static final String PROP_CODIGOBARRAS = "PROP_CODIGOBARRAS";
    public static final String PROP_VALORCOMPRA = "PROP_VALORCOMPRA";
    public static final String PROP_VALORVENDA = "PROP_VALORVENDA";
    public static final String PROP_QTDEESTOQUE = "PROP_QTDEESTOQUE";
    public static final String PROP_CDFARMACEUTICO = "PROP_CDFARMACEUTICO";
    public static final String PROP_VALORVENDAATACADO = "PROP_VALORVENDAATACADO";
    public static final String PROP_TAMANHO = "PROP_TAMANHO";
    public static final String PROP_FORNECEDORCOLLECTION = "PROP_FORNECEDORCOLLECTION";
    @Transient
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_produto")
    private Integer cdProduto;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "marca")
    private String marca;
    @Column(name = "cor")
    private String cor;
    @Column(name = "codigo_barras")
    private String codigoBarras;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_compra")
    private Float valorCompra;
    @Column(name = "valor_venda")
    private Float valorVenda;
    @Column(name = "qtde_estoque")
    private Integer qtdeEstoque;
    @Basic(optional = false)
    @Column(name = "cd_farmaceutico")
    private String cdFarmaceutico;
    @Column(name = "valor_venda_atacado")
    private Float valorVendaAtacado;
    @Column(name = "tamanho")
    private String tamanho;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdProduto")
    private Collection<PedidoProduto> pedidoProdutoCollection;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH}, fetch = FetchType.EAGER, mappedBy = "produto")
    private List<FornecedorProduto> fornecedorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendaProdutoPK.cdProduto")
    private Collection<VendaProduto> vendaProdutoCollection;
    private final transient VetoableChangeSupport vetoableChangeSupport = new java.beans.VetoableChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.support.removePropertyChangeListener(listener);
    }

    public Produto() {
    }

    public Produto(Integer cdProduto) {
        this.cdProduto = cdProduto;
    }

    public Produto(Integer cdProduto, String cdFarmaceutico) {
        this.cdProduto = cdProduto;
        this.cdFarmaceutico = cdFarmaceutico;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<PedidoProduto> getPedidoProdutoCollection() {
        return pedidoProdutoCollection;
    }

    public void setPedidoProdutoCollection(Collection<PedidoProduto> pedidoProdutoCollection) {
        this.pedidoProdutoCollection = pedidoProdutoCollection;
    }

    @XmlTransient
    @JsonIgnore
    public List<FornecedorProduto> getFornecedorCollection() {
        return fornecedorCollection;
    }



    @XmlTransient
    @JsonIgnore
    public Collection<VendaProduto> getVendaProdutoCollection() {
        return vendaProdutoCollection;
    }

    public void setVendaProdutoCollection(Collection<VendaProduto> vendaProdutoCollection) {
        this.vendaProdutoCollection = vendaProdutoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getCdProduto() != null ? getCdProduto().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.getCdProduto() == null && other.getCdProduto() != null) || (this.getCdProduto() != null && !this.cdProduto.equals(other.cdProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "(" + this.getCdFarmaceutico() + ") - " + this.getNome();
    }

    //TODO transformar método em reflection
    public void clean() {
        this.setCdFarmaceutico("");
        try {
            this.setFornecedorCollection(null);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setCodigoBarras("");
        this.setCor("");
        this.setDescricao("");
        this.setMarca("");
        this.setNome("");
        this.setQtdeEstoque((Integer) 0);
        this.setTamanho("");
        this.setValorCompra((Float) 0.0f);
        this.setValorVenda((Float) 0.0f);
        this.setValorVendaAtacado((Float) 0.0f);
    }

    /**
     * @return the cdProduto
     */
    public Integer getCdProduto() {
        return cdProduto;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @return the cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * @return the codigoBarras
     */
    public String getCodigoBarras() {
        return codigoBarras;
    }

    /**
     * @return the valorCompra
     */
    public Float getValorCompra() {
        return valorCompra;
    }

    /**
     * @return the valorVenda
     */
    public Float getValorVenda() {
        return valorVenda;
    }

    /**
     * @return the qtdeEstoque
     */
    public Integer getQtdeEstoque() {
        return qtdeEstoque;
    }

    /**
     * @return the cdFarmaceutico
     */
    public String getCdFarmaceutico() {
        return cdFarmaceutico;
    }

    /**
     * @return the valorVendaAtacado
     */
    public Float getValorVendaAtacado() {
        return valorVendaAtacado;
    }

    /**
     * @return the tamanho
     */
    public String getTamanho() {
        return tamanho;
    }

    /**
     * @param cdProduto the cdProduto to set
     */
    public void setCdProduto(Integer cdProduto) {
        java.lang.Integer oldCdProduto = this.cdProduto;
        this.cdProduto = cdProduto;
        support.firePropertyChange(PROP_CDPRODUTO, oldCdProduto, cdProduto);
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        java.lang.String oldNome = this.nome;
        this.nome = nome;
        support.firePropertyChange(PROP_NOME, oldNome, nome);
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        java.lang.String oldDescricao = descricao;
        this.descricao = descricao;
        support.firePropertyChange(PROP_DESCRICAO, oldDescricao, descricao);
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        java.lang.String oldMarca = marca;
        this.marca = marca;
        support.firePropertyChange(PROP_MARCA, oldMarca, marca);
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(String cor) {
        java.lang.String oldCor = cor;
        this.cor = cor;
        support.firePropertyChange(PROP_COR, oldCor, cor);
    }

    /**
     * @param codigoBarras the codigoBarras to set
     */
    public void setCodigoBarras(String codigoBarras) {
        java.lang.String oldCodigoBarras = codigoBarras;
        this.codigoBarras = codigoBarras;
        support.firePropertyChange(PROP_CODIGOBARRAS, oldCodigoBarras, codigoBarras);
    }

    /**
     * @param valorCompra the valorCompra to set
     */
    public void setValorCompra(Float valorCompra) {
        java.lang.Float oldValorCompra = valorCompra;
        this.valorCompra = valorCompra;
        support.firePropertyChange(PROP_VALORCOMPRA, oldValorCompra, valorCompra);
    }

    /**
     * @param valorVenda the valorVenda to set
     */
    public void setValorVenda(Float valorVenda) {
        java.lang.Float oldValorVenda = valorVenda;
        this.valorVenda = valorVenda;
        support.firePropertyChange(PROP_VALORVENDA, oldValorVenda, valorVenda);
    }

    /**
     * @param qtdeEstoque the qtdeEstoque to set
     */
    public void setQtdeEstoque(Integer qtdeEstoque) {
        java.lang.Integer oldQtdeEstoque = qtdeEstoque;
        this.qtdeEstoque = qtdeEstoque;
        support.firePropertyChange(PROP_QTDEESTOQUE, oldQtdeEstoque, qtdeEstoque);
    }

    /**
     * @param cdFarmaceutico the cdFarmaceutico to set
     */
    public void setCdFarmaceutico(String cdFarmaceutico) {
        java.lang.String oldCdFarmaceutico = cdFarmaceutico;
        this.cdFarmaceutico = cdFarmaceutico;
        support.firePropertyChange(PROP_CDFARMACEUTICO, oldCdFarmaceutico, cdFarmaceutico);
    }

    /**
     * @param valorVendaAtacado the valorVendaAtacado to set
     */
    public void setValorVendaAtacado(Float valorVendaAtacado) {
        java.lang.Float oldValorVendaAtacado = valorVendaAtacado;
        this.valorVendaAtacado = valorVendaAtacado;
        support.firePropertyChange(PROP_VALORVENDAATACADO, oldValorVendaAtacado, valorVendaAtacado);
    }

    /**
     * @param tamanho the tamanho to set
     */
    public void setTamanho(String tamanho) {
        java.lang.String oldTamanho = tamanho;
        this.tamanho = tamanho;
        support.firePropertyChange(PROP_TAMANHO, oldTamanho, tamanho);
    }

    /**
     * @param fornecedorCollection the fornecedorCollection to set
     */
    public void setFornecedorCollection(List<FornecedorProduto> fornecedorCollection) throws PropertyVetoException {
        java.util.List<br.com.cirurgica.model.FornecedorProduto> oldFornecedorCollection = this.fornecedorCollection;
        vetoableChangeSupport.fireVetoableChange(PROP_FORNECEDORCOLLECTION, oldFornecedorCollection, fornecedorCollection);
        this.fornecedorCollection = fornecedorCollection;
        support.firePropertyChange(PROP_FORNECEDORCOLLECTION, oldFornecedorCollection, fornecedorCollection);
    }
}
