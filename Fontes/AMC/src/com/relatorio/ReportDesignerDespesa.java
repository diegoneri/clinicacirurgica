/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relatorio;

import java.awt.Color;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;

import net.sf.jasperreports.engine.type.HorizontalAlignEnum;

/**
 *
 * @author 13b Pessoal
 */
public class ReportDesignerDespesa {

    public JasperDesign getDesign() throws JRException {

// Cria um novo relatório
        JasperDesign jasperDesign = new JasperDesign();

// PAGE
        configPage(jasperDesign);

// FIELD

        JRDesignField field = null;
        addField("tipoDespesa.nmTipoDespesa", String.class, jasperDesign);
        addField("dataVencimentoString", String.class, jasperDesign);
        addField("valorDespesaString", String.class, jasperDesign);
        addField("isPagaString", String.class, jasperDesign);


// TITLE

        JRDesignBand jrDesignband = null;
        JRDesignStaticText staticText = null;
        configTitle(jasperDesign);

// PAGE HEADER

        jrDesignband = new JRDesignBand();
        jrDesignband.setHeight(15);
        jasperDesign.setPageHeader(jrDesignband);

// COLUMN HEADER

        jrDesignband = new JRDesignBand();
        jrDesignband.setHeight(25);
        jasperDesign.setColumnHeader(jrDesignband);

//Dados da data
        staticText = new JRDesignStaticText();
        staticText.setX(50);
        staticText.setY(5);
        staticText.setWidth(150);
        staticText.setHeight(15);
//        staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        staticText.setText("Tipo: ");
        staticText.setBold(true);
        jrDesignband.addElement(staticText);

//Dados do valor
        staticText = new JRDesignStaticText();
        staticText.setX(150);
        staticText.setY(5);
        staticText.setWidth(100);
        staticText.setHeight(15);
//        staticText.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
        staticText.setText("Venc.");
        staticText.setBold(true);
        jrDesignband.addElement(staticText);

        //Dados do valor total
        staticText = new JRDesignStaticText();
        staticText.setX(260);
        staticText.setY(5);
        staticText.setWidth(100);
        staticText.setHeight(15);
//        staticText.setVerticalAlignment(VerticalAlignEnum.BOTTOM);
        staticText.setText("Valor : ");
        staticText.setBold(true);
        jrDesignband.addElement(staticText);


        //Dadofdsdfgdfgsdgdgd
        staticText = new JRDesignStaticText();
        staticText.setX(370);
        staticText.setY(5);
        staticText.setWidth(100);
        staticText.setHeight(15);
//        staticText.setVerticalAlignment(VerticalAlignEnum.BOTTOM);
        staticText.setText("Paga: ");
        staticText.setBold(true);
        jrDesignband.addElement(staticText);

//        R$
//        JRDesignStaticText textoR$ = null;
//        textoR$ = new JRDesignStaticText();
//        textoR$.setX(200);
//        textoR$.setY(2);
//        textoR$.setWidth(100);
//        textoR$.setHeight(15);
//        textoR$.setVerticalAlignment(VerticalAlignEnum.BOTTOM);
//        textoR$.setText("R$ ");
//        staticText.setBold(true);



// DETAIL
        jrDesignband = new JRDesignBand();
        jrDesignband.setHeight(20);

//Dados da Data
        JRDesignTextField textField = new JRDesignTextField();
        textField.setBlankWhenNull(false);
        textField.setX(50);
        textField.setY(5);
        textField.setWidth(150);
        textField.setHeight(15);
// Adiciona a expressao no campo
        JRDesignExpression expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{tipoDespesa.nmTipoDespesa}");
        textField.setExpression(expression);
        jrDesignband.addElement(textField);
//        jrDesignband.addElement(textoR$);

//Dados do valor
        textField = new JRDesignTextField();
        textField.setBlankWhenNull(false);
        textField.setX(140);
        textField.setY(5);
        textField.setWidth(51);
        textField.setHeight(15);
        textField.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
//Adiciona a expressao no campo
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{dataVencimentoString}");
        textField.setExpression(expression);
        jrDesignband.addElement(textField);
//        jasperDesign.setDetail(jrDesignband);



        textField = new JRDesignTextField();
        textField.setBlankWhenNull(false);
        textField.setX(250);
        textField.setY(5);
        textField.setWidth(50);
        textField.setHeight(15);
        textField.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
//Adiciona a expressao no campo
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{valorDespesaString}.replace(\".\",\",\")");
        textField.setExpression(expression);
        jrDesignband.addElement(textField);
//        jasperDesign.setDetail(jrDesignband);
//        jrDesignband.addElement(textoR$);


        textField = new JRDesignTextField();
        textField.setBlankWhenNull(false);
        textField.setX(340);
        textField.setY(5);
        textField.setWidth(50);
        textField.setHeight(15);
        textField.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
//Adiciona a expressao no campo
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{isPagaString}");
        textField.setExpression(expression);
        jrDesignband.addElement(textField);
//        jasperDesign.setDetail(jrDesignband);
//        jrDesignband.addElement(textoR$);



// COLUMN FOOTER (Escondido)
        jrDesignband = new JRDesignBand();
        jrDesignband.setHeight(0);
        jasperDesign.setColumnFooter(jrDesignband);

// PAGE FOOTER (Escondido)
        jrDesignband = new JRDesignBand();
        jrDesignband.setHeight(0);
        jasperDesign.setPageFooter(jrDesignband);

// NO DATA (No JasperViewer não mostra, na web mostraria)
        jrDesignband = new JRDesignBand();
        jrDesignband.setHeight(20);
        staticText = new JRDesignStaticText();
        staticText.setX(10);
        staticText.setY(5);
        staticText.setWidth(64);
        staticText.setHeight(15);
        staticText.setText("Sem registros para compor o relatório!");
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        jrDesignband.addElement(staticText);
        jasperDesign.setNoData(jrDesignband);

// SUMMARY (Escondido)
        jrDesignband = new JRDesignBand();
        jrDesignband.setHeight(0);
        jasperDesign.setSummary(jrDesignband);


        return jasperDesign;
    }

    /**
     * Ajusta os parametros do titulo
     *
     * @param jasperDesign
     */
    private void configTitle(JasperDesign jasperDesign) {
        JRDesignBand band = new JRDesignBand();
        band.setHeight(50);
        JRDesignStaticText staticText = new JRDesignStaticText();
        staticText.setX(10);
        staticText.setY(10);
        staticText.setWidth(450);
        staticText.setHeight(40);
        staticText.setFontName("Tahoma");
        staticText.setForecolor(Color.BLUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setFontSize(24);
        staticText.setPdfFontName("Helvetica-Bold");
        staticText.setBold(true);
        staticText.setText("Despesas");

        band.addElement(staticText);

        jasperDesign.setTitle(band);
    }

    /**
     * Adiciona campos ao relatorio
     *
     * @param name
     * @param clazz
     * @param jasperDesign
     * @throws JRException
     */
    private void addField(String name, Class clazz, JasperDesign jasperDesign)
            throws JRException {
        JRDesignField field = new JRDesignField();

        field.setName(name);
        field.setValueClass(clazz);
        jasperDesign.addField(field);
    }

    /**
     * Configura a pagina
     *
     * @param jasperDesign
     */
    private void configPage(JasperDesign jasperDesign) {
        jasperDesign.setPageWidth(500);
        jasperDesign.setPageHeight(1000);
        jasperDesign.setColumnCount(1);
        jasperDesign.setColumnWidth(450);
        jasperDesign.setColumnSpacing(0);
        jasperDesign.setLeftMargin(10);
        jasperDesign.setRightMargin(10);
        jasperDesign.setBottomMargin(5);
        jasperDesign.setTopMargin(5);
        jasperDesign.setName("RelatorioDeDespesa");

    }
}
