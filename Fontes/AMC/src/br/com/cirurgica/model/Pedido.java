/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.model;

import br.com.cirurgica.generated.model.NotaFiscal;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findByCdPedido", query = "SELECT p FROM Pedido p WHERE p.cdPedido = :cdPedido"),
    @NamedQuery(name = "Pedido.findByDataPedido", query = "SELECT p FROM Pedido p WHERE p.dataPedido = :dataPedido"),
    @NamedQuery(name = "Pedido.findByValorPedido", query = "SELECT p FROM Pedido p WHERE p.valorPedido = :valorPedido"),
    @NamedQuery(name = "Pedido.findByIsPago", query = "SELECT p FROM Pedido p WHERE p.isPago = :isPago"),
    @NamedQuery(name = "Pedido.findByIsRecebido", query = "SELECT p FROM Pedido p WHERE p.isRecebido = :isRecebido"),
    @NamedQuery(name = "Pedido.findByDescricao", query = "SELECT p FROM Pedido p WHERE p.descricao = :descricao")})
public class Pedido extends AbstractModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_pedido")
    private Integer cdPedido;
    @Column(name = "data_pedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPedido;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_pedido")
    private Float valorPedido;
    @Column(name = "isPago")
    private Boolean isPago;
    @Column(name = "isRecebido")
    private Boolean isRecebido;
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "cd_nota_fiscal", referencedColumnName = "cd_nota_fiscal")
    @ManyToOne
    private NotaFiscal cdNotaFiscal;
    @JoinColumn(name = "cd_fornecedor", referencedColumnName = "cd_fornecedor")
    @ManyToOne
    private Fornecedor cdFornecedor;    
    @OneToMany(mappedBy = "cdPedido",fetch= FetchType.EAGER)
    private Collection<PedidoProduto> pedidoProdutoCollection;
    @XmlTransient
    @JsonIgnore
    public Collection<PedidoProduto> getPedidoProdutoCollection() {
        return pedidoProdutoCollection;
    }

    public void setPedidoProdutoCollection(Collection<PedidoProduto> listaPedidoProduto) {
        this.pedidoProdutoCollection = listaPedidoProduto;
    }
        
    public Pedido() {
    }

    public Pedido(Integer cdPedido) {
        this.cdPedido = cdPedido;
    }

    public Integer getCdPedido() {
        return cdPedido;
    }

    public void setCdPedido(Integer cdPedido) {
        this.cdPedido = cdPedido;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Float getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(Float valorPedido) {
        if (this.valorPedido == null){
            this.valorPedido = valorPedido;
        }else{
            this.valorPedido = valorPedido.floatValue();
        }
    }

    public Boolean getIsPago() {
        return isPago;
    }

    public void setIsPago(Boolean isPago) {
        this.isPago = isPago;
    }

    public Boolean getIsRecebido() {
        return isRecebido;
    }

    public void setIsRecebido(Boolean isRecebido) {
        this.isRecebido = isRecebido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float calcularValorTotal() {
        Float finalValue = 0f;
        if (this.getPedidoProdutoCollection() != null) {
            for (PedidoProduto p : getPedidoProdutoCollection()) {
                finalValue += p.getQtdeProduto() * p.getVlProduto();
            }
        }
        return finalValue;
    }
    
    public NotaFiscal getCdNotaFiscal() {
        return cdNotaFiscal;
    }

    public void setCdNotaFiscal(NotaFiscal cdNotaFiscal) {
        this.cdNotaFiscal = cdNotaFiscal;
    }

    public Fornecedor getCdFornecedor() {
        return cdFornecedor;
    }

    public void setCdFornecedor(Fornecedor cdFornecedor) {
        this.cdFornecedor = cdFornecedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdPedido != null ? cdPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.cdPedido == null && other.cdPedido != null) || (this.cdPedido != null && !this.cdPedido.equals(other.cdPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cirurgica.model.Pedido[ cdPedido=" + cdPedido + " ]";
    }
}
