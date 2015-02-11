/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.controller;

import br.com.cirurgica.dao.PedidoDAO;
import br.com.cirurgica.dao.ProdutoDAO;
import br.com.cirurgica.model.Pedido;
import br.com.cirurgica.model.Fornecedor;
import br.com.cirurgica.model.Produto;
import br.com.cirurgica.view.IView;
import br.com.cirurgica.view.pedido.CadastrarPedido;
import br.com.cirurgica.view.pedido.GestaoPedido;
import br.com.cirurgica.view.pedido.PesquisarFornecedor;
import br.com.cirurgica.view.produto.PedidoProdutoFindView;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dfelix3
 */
public class PedidoController extends GenericController<Pedido> {

    private PedidoDAO dao;
    private ProdutoDAO produtoDao;
    private CadastrarPedido registerView;
    private GestaoPedido view;
    private PesquisarFornecedor findView;
    private PedidoProdutoFindView findProdutoView;
    private Produto resultadoBuscaProduto;

    public PedidoController(){
        dao = new PedidoDAO();
        produtoDao = new ProdutoDAO();
    }
    
    @Override
    public IView getView() {
        return this.view;
    }

    @Override
    public IView getFindView() {
        return this.findView;
    }

    @Override
    public IView getRegisterView() {
        return this.registerView;
    }

    @Override
    protected PedidoController init() {
        view = new GestaoPedido(this);
        view.setVisible(true);
        return this;
    }

    @Override
    public void register(Pedido model) {
        view.fecharJanela();
        registerView = new CadastrarPedido(model,this);
        registerView.setVisible(true);        
    }

    // <editor-fold defaultstate="collapsed" desc="Busca de Pedidos">
    public List<Pedido> getAllPedidos(){
        return dao.findAll();
    }
    
    public List<Pedido> obterPedidoPorData(Date dataPedido){
        return dao.getByDataPedido(dataPedido);
    }
    //</editor-fold>
    
    @Override
    public Pedido find() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Produto findProduto(Fornecedor fornecedor){
        findProdutoView = new PedidoProdutoFindView(this);
        
        List<Produto> listaProduto =
                produtoDao.getAllByFornecedor(fornecedor);
        this.findProdutoView.getListaProduto().clear();
        if (listaProduto != null && listaProduto.size() > 0) {
            
            this.findProdutoView.getListaProduto().addAll(listaProduto);
        }
    
        findProdutoView.setNomeFornecedor(fornecedor.getNomeFantasia());
        findProdutoView.setVisible(true);
        return this.resultadoBuscaProduto;        
    }
    
    private void obterProdutoPedidoBusca() {
        this.resultadoBuscaProduto = findProdutoView.getProduto();
        this.findProdutoView.dispose();
    }

    private void cancelarProdutoPedidoBusca() {
        this.resultadoBuscaProduto = null;
        this.findProdutoView.dispose();
    }
    @Override
    protected void evaluateAction(String action) {
        switch(action){
            case "PedidoProdutoFindView.obterProduto":
                obterProdutoPedidoBusca();
                break;
            case "PedidoProdutoFindView.voltar":
                cancelarProdutoPedidoBusca();
                break;
            default:
                break;
        }
    }
    
    @Override
    protected void evaluateEscape(String source) {
        String form = source.substring(0,source.indexOf("."));
        Window w = null;
        switch (form) {
            case "CadastrarProduto":
                w = registerView;
                break;
            default:
                w = view;
                break;
        }
        WindowEvent evt = new WindowEvent(w, WindowEvent.WINDOW_OPENED);
        this.fecharJanela(evt);
    }

    @Override
    public void ativarJanela(WindowEvent e) {
        System.out.println("Ativou janela : " + e.getSource());
    }

    @Override
    public void desativarJanela(WindowEvent e) {
        System.out.println("Desativou janela : " + e.getSource());
    }
}
