/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.model;

import br.com.cirurgica.model.Usuario;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import org.jdesktop.observablecollections.ObservableList;

/**
 *
 * @author dfelix3
 */
@Entity
@Table(name = "venda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venda.findAll", query = "SELECT v FROM Venda v"),
    @NamedQuery(name = "Venda.findByCdVenda", query = "SELECT v FROM Venda v WHERE v.cdVenda = :cdVenda"),
    @NamedQuery(name = "Venda.findByValorTotalVenda", query = "SELECT v FROM Venda v WHERE v.valorTotalVenda = :valorTotalVenda"),
    @NamedQuery(name = "Venda.findByDataVenda", query = "SELECT v FROM Venda v WHERE v.dataVenda = :dataVenda"),
    @NamedQuery(name = "Venda.findByNrParcelas", query = "SELECT v FROM Venda v WHERE v.nrParcelas = :nrParcelas"),
    @NamedQuery(name = "Venda.findByVlDinheiro", query = "SELECT v FROM Venda v WHERE v.vlDinheiro = :vlDinheiro"),
    @NamedQuery(name = "Venda.findByVlTroco", query = "SELECT v FROM Venda v WHERE v.vlTroco = :vlTroco"),
    @NamedQuery(name = "Venda.findByVlParcela", query = "SELECT v FROM Venda v WHERE v.vlParcela = :vlParcela"),
    @NamedQuery(name = "Venda.findByNmCupomCartao", query = "SELECT v FROM Venda v WHERE v.nmCupomCartao = :nmCupomCartao"),
    @NamedQuery(name = "Venda.findByNmTipoVenda", query = "SELECT v FROM Venda v WHERE v.nmTipoVenda = :nmTipoVenda"),
    @NamedQuery(name = "Venda.findByInativa", query = "SELECT v FROM Venda v WHERE v.inativa = :inativa")})
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PROP_VENDAPRODUTOCOLLECTION = "vendaProdutoCollection";
    public static final String PROP_VALORTOTALVENDA = "valorTotalVenda";
    public static final String PROP_DATAVENDA = "dataVenda";
    public static final String PROP_NRPARCELAS = "nrParcelas";
    public static final String PROP_VLDINHEIRO = "vlDinheiro";
    public static final String PROP_VLTROCO = "vlTroco";
    public static final String PROP_VLPARCELA = "vlParcela";
    public static final String PROP_NMCUPOMCARTAO = "nmCupomCartao";
    public static final String PROP_NMTIPOVENDA = "nmTipoVenda";
    public static final String PROP_INATIVA = "inativa";
    public static final String PROP_CDFORMAPAGAMENTO = "cdFormaPagamento";
    public static final String PROP_CDCLIENTE = "cdCliente";
    public static final String PROP_CDNOTAFISCAL = "cdNotaFiscal";
    public static final String PROP_CDUSUARIO = "cdUsuario";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_venda")
    private Integer cdVenda;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_total_venda")
    private Float valorTotalVenda = 0.0f;
    @Column(name = "data_venda")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVenda = new Date();
    @Column(name = "nr_parcelas")
    private Integer nrParcelas = 1;
    @Column(name = "vl_dinheiro")
    private Float vlDinheiro = 0.0f;
    @Column(name = "vl_troco")
    private Float vlTroco = 0.0f;
    @Column(name = "vl_parcela")
    private Float vlParcela = 0.0f;
    @Column(name = "vl_desconto")
    private Float vlDesconto = 0.0f;
    @Column(name = "nm_cupom_cartao")
    private String nmCupomCartao;
    @Column(name = "nm_tipo_venda")
    private String nmTipoVenda;
    @Basic(optional = false)
    @Column(name = "inativa")
    private boolean inativa;
    @JoinColumn(name = "cd_forma_pagamento", referencedColumnName = "cd_forma_pagamento")
    @ManyToOne
    private FormaPagamento cdFormaPagamento;
    @JoinColumn(name = "cd_cliente", referencedColumnName = "cd_cliente")
    @ManyToOne
    private Cliente cdCliente;
    @JoinColumn(name = "cd_nota_fiscal", referencedColumnName = "cd_nota_fiscal")
    @ManyToOne
    private NotaFiscal cdNotaFiscal;
    @JoinColumn(name = "cd_usuario", referencedColumnName = "cd_usuario")
    @ManyToOne
    private Usuario cdUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendaProdutoPK.cdVenda")
    private List<VendaProduto> vendaProdutoCollection;
    protected transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private final transient VetoableChangeSupport vetoableChangeSupport = new java.beans.VetoableChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public Venda() {
        this(null);
    }

    public Venda(Integer cdVenda) {
        this(cdVenda, true);
    }

    public Venda(Integer cdVenda, boolean inativa) {
        this.cdVenda = cdVenda;
        this.inativa = inativa;
    }

    public Integer getCdVenda() {
        return cdVenda;
    }

    public void setCdVenda(Integer cdVenda) {
        Integer oldCdVenda = this.cdVenda;
        this.cdVenda = cdVenda;
        propertyChangeSupport.firePropertyChange("cdVenda", oldCdVenda, cdVenda);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdVenda != null ? cdVenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.cdVenda == null && other.cdVenda != null) || (this.cdVenda != null && !this.cdVenda.equals(other.cdVenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cirurgica.generated.model.Venda[ cdVenda=" + cdVenda + " ]";
    }
    
    @XmlTransient
    @JsonIgnore
    public List<VendaProduto> getVendaProdutoCollection() {
        return vendaProdutoCollection;
    }

    public void setVendaProdutoCollection(List<VendaProduto> vendaProdutoCollection) throws PropertyVetoException {
        java.util.Collection<br.com.cirurgica.generated.model.VendaProduto> oldVendaProdutoCollection = this.vendaProdutoCollection;
        vetoableChangeSupport.fireVetoableChange(PROP_VENDAPRODUTOCOLLECTION, oldVendaProdutoCollection, vendaProdutoCollection);
        this.vendaProdutoCollection = vendaProdutoCollection;
        propertyChangeSupport.firePropertyChange(PROP_VENDAPRODUTOCOLLECTION, oldVendaProdutoCollection, vendaProdutoCollection);
        
        this.recalcularTotalVenda();
    }

    public void addItemVenda(VendaProduto vp){
        this.getVendaProdutoCollection().add(vp);
        this.recalcularTotalVenda();
    }
    
    private void recalcularTotalVenda(){
        Float valorTotal = 0.0f;
        for(VendaProduto vp: vendaProdutoCollection){
            valorTotal += vp.getTotalItem();
        }
        this.setValorTotalVenda(valorTotal);     
        this.atualizarValorTroco();
    }
    public Float getValorTotalVenda() {
        return valorTotalVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public Integer getNrParcelas() {
        return nrParcelas;
    }

    public Float getVlDinheiro() {
        return vlDinheiro;
    }

    public Float getVlTroco() {
        return vlTroco;
    }

    public Float getVlParcela() {
        return vlParcela;
    }

    public Float getVlDesconto() {
        return vlDesconto;
    }

    public String getNmCupomCartao() {
        return nmCupomCartao;
    }

    public String getNmTipoVenda() {
        return nmTipoVenda;
    }

    public boolean isInativa() {
        return inativa;
    }

    public FormaPagamento getCdFormaPagamento() {
        return cdFormaPagamento;
    }

    public Cliente getCdCliente() {
        return cdCliente;
    }

    public NotaFiscal getCdNotaFiscal() {
        return cdNotaFiscal;
    }

    public Usuario getCdUsuario() {
        return cdUsuario;
    }

    public void setValorTotalVenda(Float valorTotalVenda) {
        java.lang.Float oldValorTotalVenda = this.valorTotalVenda;
        this.valorTotalVenda = valorTotalVenda;
        propertyChangeSupport.firePropertyChange(PROP_VALORTOTALVENDA, oldValorTotalVenda, valorTotalVenda);
        //Atualizar o valor da parcela
        this.atualizarValorParcela();
    }

    public void setDataVenda(Date dataVenda) {
        java.util.Date oldDataVenda = this.dataVenda;
        this.dataVenda = dataVenda;
        propertyChangeSupport.firePropertyChange(PROP_DATAVENDA, oldDataVenda, dataVenda);
    }

    public void setNrParcelas(Integer nrParcelas) {
        java.lang.Integer oldNrParcelas = this.nrParcelas;
        this.nrParcelas = nrParcelas;
        propertyChangeSupport.firePropertyChange(PROP_NRPARCELAS, oldNrParcelas, nrParcelas);
        //Atualizar o valor da parcela
        this.atualizarValorParcela();       
    }
    
    private Float getValorTotalComDesconto(){
        return (this.getValorTotalVenda() - this.getVlDesconto());
    }

    private void atualizarValorParcela(){
        this.setVlParcela(this.getValorTotalComDesconto() / this.getNrParcelas()); 
    }
    
    private void atualizarValorTroco(){
        this.setVlTroco((this.getVlDesconto() + this.getVlDinheiro()) - this.getValorTotalVenda());
    }
    
    public void setVlDinheiro(Float vlDinheiro) {
        java.lang.Float oldVlDinheiro = this.vlDinheiro;
        this.vlDinheiro = vlDinheiro;
        propertyChangeSupport.firePropertyChange(PROP_VLDINHEIRO, oldVlDinheiro, vlDinheiro);
        //Atualizar o troco...
        this.atualizarValorTroco();        
    }

    public void setVlTroco(Float vlTroco) {
        java.lang.Float oldVlTroco = this.vlTroco;
        this.vlTroco = vlTroco;
        propertyChangeSupport.firePropertyChange(PROP_VLTROCO, oldVlTroco, vlTroco);
    }

    public void setVlParcela(Float vlParcela) {
        java.lang.Float oldVlParcela = this.vlParcela;
        this.vlParcela = vlParcela;
        propertyChangeSupport.firePropertyChange(PROP_VLPARCELA, oldVlParcela, vlParcela);
    }
    
    public void setVlDesconto(Float vlDesconto) {
        java.lang.Float oldVlDesconto = this.vlDesconto;
        this.vlDesconto = vlDesconto;
        propertyChangeSupport.firePropertyChange("vlDesconto", oldVlDesconto, vlDesconto);
        //Atualizar o troco...
        this.atualizarValorTroco();
        //Atualizar valor da parcela
        this.atualizarValorParcela();
    }

    public void setNmCupomCartao(String nmCupomCartao) {
        java.lang.String oldNmCupomCartao = this.nmCupomCartao;
        this.nmCupomCartao = nmCupomCartao;
        propertyChangeSupport.firePropertyChange(PROP_NMCUPOMCARTAO, oldNmCupomCartao, nmCupomCartao);
    }

    public void setNmTipoVenda(String nmTipoVenda) {
        java.lang.String oldNmTipoVenda = this.nmTipoVenda;
        this.nmTipoVenda = nmTipoVenda;
        propertyChangeSupport.firePropertyChange(PROP_NMTIPOVENDA, oldNmTipoVenda, nmTipoVenda);
    }

    public void setInativa(boolean inativa) {
        boolean oldInativa = this.inativa;
        this.inativa = inativa;
        propertyChangeSupport.firePropertyChange(PROP_INATIVA, oldInativa, inativa);
    }

    public void setCdFormaPagamento(FormaPagamento cdFormaPagamento) {
        br.com.cirurgica.generated.model.FormaPagamento oldCdFormaPagamento = this.cdFormaPagamento;
        this.cdFormaPagamento = cdFormaPagamento;
        propertyChangeSupport.firePropertyChange(PROP_CDFORMAPAGAMENTO, oldCdFormaPagamento, cdFormaPagamento);
    }

    public void setCdCliente(Cliente cdCliente) {
        br.com.cirurgica.generated.model.Cliente oldCdCliente = this.cdCliente;
        this.cdCliente = cdCliente;
        propertyChangeSupport.firePropertyChange(PROP_CDCLIENTE, oldCdCliente, cdCliente);
    }

    public void setCdNotaFiscal(NotaFiscal cdNotaFiscal) {
        br.com.cirurgica.generated.model.NotaFiscal oldCdNotaFiscal = this.cdNotaFiscal;
        this.cdNotaFiscal = cdNotaFiscal;
        propertyChangeSupport.firePropertyChange(PROP_CDNOTAFISCAL, oldCdNotaFiscal, cdNotaFiscal);
    }

    public void setCdUsuario(Usuario cdUsuario) {
        br.com.cirurgica.model.Usuario oldCdUsuario = this.cdUsuario;
        this.cdUsuario = cdUsuario;
        propertyChangeSupport.firePropertyChange(PROP_CDUSUARIO, oldCdUsuario, cdUsuario);
    }

    public void removeItemVenda(int selectedRow) {
        ObservableList vendaProdutos = (ObservableList) this.getVendaProdutoCollection();
        vendaProdutos.remove(selectedRow);
        this.recalcularTotalVenda();
    }
}
