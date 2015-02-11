package br.com.cirurgica.view.utils;

import br.com.cirurgica.model.Fornecedor;
import br.com.cirurgica.model.FornecedorProduto;
import br.com.cirurgica.model.FornecedorProdutoPK;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.beansbinding.Converter;

/**
 * <code>FornecedorProduto</code> to <code>Fornecedor</code> converter
 * 
 * @author Diego
 */
public class FornecedorProdutoConverter extends Converter<List<Fornecedor>, List<FornecedorProduto>> {

    @Override
    public List<Fornecedor> convertReverse(List<FornecedorProduto> value) {
        List<Fornecedor> list = new ArrayList<>();
        for(FornecedorProduto fp: value){
            list.add(fp.getFornecedor());
        }
        return list;
    }

    @Override
    public List<FornecedorProduto> convertForward(List<Fornecedor> value) {
        //return value;
        List<FornecedorProduto> list = new ArrayList<>();
        for(Fornecedor f: value){
            FornecedorProduto fp = new FornecedorProduto();
            FornecedorProdutoPK fppk = new FornecedorProdutoPK(f.getCdFornecedor());
            fp.setFornecedor(f);
            fp.setId(fppk);
            list.add(fp);            
        }
        return list;
    }

//    @Override
//    public Fornecedor convertReverse(FornecedorProduto value) {
//        return value.getFornecedor();
//    }
//
//    @Override
//    public FornecedorProduto convertForward(Fornecedor value) {

//    }

}
