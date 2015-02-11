/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.controller;

import br.com.cirurgica.dao.FornecedorDAO;
import br.com.cirurgica.dao.MarkupDAO;
import br.com.cirurgica.dao.ProdutoDAO;
import br.com.cirurgica.generated.model.Markup;
import br.com.cirurgica.model.Produto;
import br.com.cirurgica.view.IView;
import br.com.cirurgica.view.PrincipalView;
import br.com.cirurgica.view.produto.CadastrarProduto;
import br.com.cirurgica.view.produto.GestaoProduto;
import br.com.cirurgica.view.produto.ProdutoFindView;
import br.com.cirurgica.view.utils.ProgressBarView;
import com.utilidades.Utilidades;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dfelix3
 */
public class ProdutoController extends GenericController<Produto> {

    private boolean carregou = false;
    // <editor-fold defaultstate="Collapsed" desc="Constantes">
    private static final String PRODUTOFINDVIEW_BUSCARPRODUTO = "ProdutoFindView.buscarProduto";
    private static final String FAVOR_SELECIONAR_UM_TIPO_DE_PESQUISA = "Favor selecionar um tipo de pesquisa";
    private static final String PRODUTO_FIND_VIEW_OBTER_PRODUTO = "ProdutoFindView.obterProduto";
    private static final String PRODUTO_FIND_VIEW_VOLTAR = "ProdutoFindView.voltar";
    private static final String BUSCA_DE_PRODUTO = "Busca de Produto";
    private static final String DIGITE_UM_NOVO_VALOR_DE_MARKUP = "Digite um novo Valor de Markup (Ex. 0,47 = 47%)";
    private static final String GESTAO_PRODUTO_ALTERAR_MARKUP = "GestaoProduto.alterarMarkup";
    private static final String VALOR_DEFAULT_0_5 = "0,5";
    private static final String MARKUP_ATUALIZADO_COM_SUCESSO = "Markup atualizado com sucesso!";
    private static final String NAO_FORAM_ENCONTRADOS_PRODUTOS_COM_O_CRIT = "Não foram encontrados produtos com o critério: ";
    private static final String PESQUISA_DE_PRODUTO = "Pesquisa de Produto";
    private static final String GESTAO_PRODUTO_PESQUISAR_PRODUTO = "GestaoProduto.pesquisarProduto";
    private static final String GESTAO_PRODUTO_TXT_NOME = "GestaoProduto.txtNome";
    private static final String EXCLUSAO_DE_PRODUTO = "Exclusão de Produto";
    private static final String DESEJA_EXCLUIR_O_PRODUTO_ = "Deseja excluir o produto: \'";
    private static final String INTERROGACAO = "\'?";
    private static final String PRODUTO_EXCLUÍDO_COM_SUCESSO_ = "Produto excluído com sucesso: ";
    private static final String O_PRODUTO_NAO_FOI_EXCLUIDO = "O produto não foi excluido!";
    private static final String GESTAO_PRODUTO_EXCLUIR_PRODUTO = "GestaoProduto.excluirProduto";
    private static final String GESTAO_PRODUTO_ADICIONAR_PRODUTO = "GestaoProduto.adicionarProduto";
    private static final String GESTAO_PRODUTO_ALTERAR_PRODUTO = "GestaoProduto.alterarProduto";
    private static final String CADASTRO_PRODUTO_LIMPAR_CAMPOS = "CadastroProduto.limparCampos";
    private static final String CADASTRO_PRODUTO_SALVAR = "CadastroProduto.salvar";
    private static final String CADASTRO_PRODUTO_CANCELAR = "CadastroProduto.cancelar";
    private static final String GESTAO_PRODUTO_VOLTAR = "GestaoProduto.voltar";
    private static final String OPCAO_CODIGO = "C";
    private static final String OPCAO_NOME = "P";
    private static final String STRING_VAZIA = "";
    private static final String PREENCHA_TODOS_OS_CAMPOS_ANTES_DE_CADASTRA = "Preencha todos os campos antes de cadastrar um Produto!";
    private static final String CANNOT_BE_NULL = "cannot be null";
    private static final String PREENCHA_TODOS_OS_CAMPOS = "Preencha todos os campos!";
    private static final String DUPLICATE_ENTRY = "Duplicate entry";
    private static final String PREFIXO_CODIGO_DUPLICADO = "Código \'";
    private static final String SUFIXO_CODIGO_DUPLICADO = "\' duplicado";
    private static final String PREFIXO_HOUVE_UM_ERRO_AO_ALTERAR = "Houve um erro ao ";
    private static final String SUFIXO_HOUVE_UM_ERRO_AO_ALTERAR = " o produto.";
    private static final String PREFIXO_PRODUTO_CADASTRADO_SUCESSO = "Produto ";
    private static final String SUFIXO_PRODUTO_CADASTRADO_SUCESSO = " com sucesso!";
    // </editor-fold>
    private ProdutoDAO dao;
    private ProdutoFindView findView;
    private CadastrarProduto registerView;
    private GestaoProduto view;
    private Produto resultadoBusca;

    public ProdutoController() {
        super();
        dao = new ProdutoDAO();
    }

    @Override
    public ProdutoController init() {
        carregou = false;
        view = new GestaoProduto(this);
        view.setVisible(true);
        return this;
    }

    @Override
    public Produto find() {
        findView = new ProdutoFindView(this);
        findView.setVisible(true);
        return this.resultadoBusca;
    }

    @Override
    public void register(Produto model) {
        view.fecharJanela();
        if (model != null) {
            model = dao.getProdutoAlterar(model.getCdProduto());
        }
        FornecedorDAO daoF = new FornecedorDAO();
        registerView = new CadastrarProduto(model, this, daoF.findAll(), model != null ? daoF.getAllByProduto(model) : null);
        registerView.setVisible(true);
        dao.closeEM();
    }

    // <editor-fold defaultstate="collapsed" desc="Métodos de busca">
    public List<Produto> obterTodos() {
        return dao.findAll();
    }

    public List<Produto> obterTodosPorCodigoFarmaceutico(Produto produto) {
        return dao.getAllByCodigoFarmaceutico(produto);
    }

    public Produto obterPorCodigoFarmaceutico(Produto produto) {
        return dao.getByCodigoFarmaceutico(produto);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Busca de Produto">
    private void buscarProduto() {
        Collection lista = null;

        switch (findView.getOptionSelected()) {
            case OPCAO_CODIGO:
                if (!findView.getCriteria().getCdFarmaceutico().trim().equals(STRING_VAZIA)) {
                    lista = dao.getAllByCodigoFarmaceutico(findView.getCriteria());
                }
                break;
            case OPCAO_NOME:
                if (!findView.getCriteria().getNome().trim().equals(STRING_VAZIA)) {
                    lista = dao.getAllByNome(findView.getCriteria());
                }
                break;
            default:
                JOptionPane.showMessageDialog(findView, FAVOR_SELECIONAR_UM_TIPO_DE_PESQUISA, BUSCA_DE_PRODUTO, JOptionPane.WARNING_MESSAGE);
                return;
        }

        this.findView.getListaProduto().clear();
        if (lista != null && lista.size() > 0) {
            this.findView.getListaProduto().addAll(lista);
        }
    }

    private void obterProdutoBusca() {
        this.resultadoBusca = findView.getProduto();
        this.findView.dispose();
    }

    private void cancelarProdutoBusca() {
        this.resultadoBusca = null;
        this.findView.dispose();
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Gestão de Produto">
    private void popularTabela() {
        this.view.getListaProduto().clear();
        Collection listaRetorno = dao.findAll();
        if (listaRetorno != null && listaRetorno.size() > 0) {
            this.view.getListaProduto().addAll(listaRetorno);
        }
    }

    public void alterarMarkup() {
        String markup = JOptionPane.showInputDialog(DIGITE_UM_NOVO_VALOR_DE_MARKUP, VALOR_DEFAULT_0_5);

        if (markup != null) {
            try {
                Double valor = Utilidades.getDoubleByNumeric(markup);
                Markup m = new Markup(valor);
                new MarkupDAO().alterar(m);
                JOptionPane.showMessageDialog(view, MARKUP_ATUALIZADO_COM_SUCESSO);
            } catch (ParseException ex) {
                Logger.getLogger(GestaoProduto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(GestaoProduto.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void pesquisarProduto() {
        String nome = this.view.getNomePesquisa();
        Produto p = new Produto();
        p.setNome(nome);


        Collection listaRetorno = dao.getAllByNome(p);
        this.view.getListaProduto().clear();
        if (listaRetorno != null && listaRetorno.size() > 0) {
            this.view.getListaProduto().addAll(listaRetorno);
        } else {
            JOptionPane.showMessageDialog(view, NAO_FORAM_ENCONTRADOS_PRODUTOS_COM_O_CRIT + nome, PESQUISA_DE_PRODUTO, JOptionPane.WARNING_MESSAGE);
        }
    }

    public void excluirProduto() {
        try {
            int resp =
                    JOptionPane.showConfirmDialog(view, DESEJA_EXCLUIR_O_PRODUTO_ + this.view.getProduto() + INTERROGACAO, EXCLUSAO_DE_PRODUTO, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resp == JOptionPane.YES_OPTION) {
                dao.excluir(this.view.getProduto());
                JOptionPane.showMessageDialog(view, PRODUTO_EXCLUÍDO_COM_SUCESSO_ + this.view.getProduto(), EXCLUSAO_DE_PRODUTO, JOptionPane.INFORMATION_MESSAGE);
                this.view.getListaProduto().remove(this.view.getProduto());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, O_PRODUTO_NAO_FOI_EXCLUIDO, EXCLUSAO_DE_PRODUTO, JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(GestaoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Cadastro e Alteração">
    public void cadastrarProduto() {
        if (this.registerView.validarCampos()) {
            JOptionPane.showMessageDialog(registerView, PREENCHA_TODOS_OS_CAMPOS_ANTES_DE_CADASTRA);
        } else {
            try {
                switch (this.registerView.getAcao()) {
                    case INCLUIR:
                        dao.incluir(registerView.getProdutoTela());
                        break;
                    case ALTERAR:
                        dao.alterar(registerView.getProdutoTela());
                        break;
                }
                JOptionPane.showMessageDialog(registerView, PREFIXO_PRODUTO_CADASTRADO_SUCESSO
                        + registerView.getAcao().getPastAction()
                        + SUFIXO_PRODUTO_CADASTRADO_SUCESSO);
                registerView.fecharJanela();
                this.init();

            } catch (Exception ex) {
                if (ex.getMessage().contains(CANNOT_BE_NULL)) {
                    JOptionPane.showMessageDialog(registerView, PREENCHA_TODOS_OS_CAMPOS);
                } else if (ex.getMessage().contains(DUPLICATE_ENTRY)) {
                    JOptionPane.showMessageDialog(registerView, PREFIXO_CODIGO_DUPLICADO
                            + registerView.getProdutoTela().getCdFarmaceutico() + SUFIXO_CODIGO_DUPLICADO);
                } else {
                    JOptionPane.showMessageDialog(registerView, PREFIXO_HOUVE_UM_ERRO_AO_ALTERAR
                            + registerView.getAcao().getAction() + SUFIXO_HOUVE_UM_ERRO_AO_ALTERAR);
                }
                Logger.getLogger(CadastrarProduto.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(registerView.getProdutoTela());
        }
    }

    //</editor-fold>
    /**
     *
     * @see br.com.cirurgica.controller.GenericController.evaluateAction
     */
    @Override
    protected void evaluateAction(String source) {
        switch (source) {
            case PRODUTOFINDVIEW_BUSCARPRODUTO:
                buscarProduto();
                break;
            case PRODUTO_FIND_VIEW_OBTER_PRODUTO:
                obterProdutoBusca();
                break;
            case PRODUTO_FIND_VIEW_VOLTAR:
                cancelarProdutoBusca();
                break;
            case GESTAO_PRODUTO_ALTERAR_MARKUP:
                alterarMarkup();
                break;
            case GESTAO_PRODUTO_PESQUISAR_PRODUTO:
            case GESTAO_PRODUTO_TXT_NOME:
                pesquisarProduto();
                break;
            case GESTAO_PRODUTO_EXCLUIR_PRODUTO:
                excluirProduto();
                break;
            case GESTAO_PRODUTO_ADICIONAR_PRODUTO:
                register(null);
                break;
            case GESTAO_PRODUTO_ALTERAR_PRODUTO:
                register(this.view.getProduto());
                break;
            case GESTAO_PRODUTO_VOLTAR:
                super.closeWindowSimulation(view);
                break;
            case CADASTRO_PRODUTO_LIMPAR_CAMPOS:
                this.registerView.limparCampos();
                break;
            case CADASTRO_PRODUTO_SALVAR:
                cadastrarProduto();
                break;
            case CADASTRO_PRODUTO_CANCELAR:
                super.closeWindowSimulation(registerView);
                break;
            default:
                break;
        }
    }

    /**
     *
     * @see br.com.cirurgica.controller.GenericController.evaluateEscape
     */
    @Override
    protected void evaluateEscape(String source) {
        String form = source.substring(0, source.indexOf("."));
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
    public void ativarJanela(WindowEvent e) {
        if (e.getSource() == view) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    // aqui eu inicio a progressbar  
                    ProgressBarView rel = new ProgressBarView("Carregando produtos...");
                    rel.setVisible(true);
                    try {
                        if (!carregou){
                            popularTabela();
                            carregou = true;
                        }
                    } finally {
                        rel.setVisible(false);
                        rel.dispose();
                    }
                    return;
                }
            };
            t.start();
            System.out.println("Populou tabela");
        }
    }

    @Override
    public void desativarJanela(WindowEvent e) {
        System.out.println("Desativou tela: " + e.getSource().toString());
    }

    /**
     *
     *
     */
    @Override
    protected void fecharJanela(WindowEvent e) {
        int resp =
                JOptionPane.showConfirmDialog((Component) getView(), GenericController.DESEJA_FECHAR_A_TELA_ATUAL, GenericController.SAÍDA_DE_TELA, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resp == JOptionPane.OK_OPTION) {
            if (e.getSource() == view) {
                view.dispose();
                try {
                    finalize();
                    //TODO chamar tela principal
                    new PrincipalView().setVisible(true);
                } catch (Throwable ex) {
                    Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == registerView) {
                registerView.dispose();
                init();
            }
        }
    }
}
