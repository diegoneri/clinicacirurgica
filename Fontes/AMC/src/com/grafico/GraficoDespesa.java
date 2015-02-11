/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grafico;

import br.com.cirurgica.model.Despesa;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author 13b Pessoal
 */
public class GraficoDespesa extends JFrame {
   
    
    public GraficoDespesa(String[] listaDespesa) {
        super("Despesas"); //Define o titulo da tela

//add grafico

        DefaultPieDataset pieDataset = new DefaultPieDataset();

//Adiciona os dados ao dataSet deve somar um total de 100%
        pieDataset.setValue("Água : " + listaDespesa[0] + "%", Double.parseDouble(listaDespesa[0]));
        pieDataset.setValue("Energia Elétrica : " + listaDespesa[1] + "%", Double.parseDouble(listaDespesa[1]));
        pieDataset.setValue("Telefone : " + listaDespesa[2] + "%", Double.parseDouble(listaDespesa[2]));
        pieDataset.setValue("Imposto : " + listaDespesa[3] + "%", Double.parseDouble(listaDespesa[3]));
        pieDataset.setValue("Compra : " + listaDespesa[4] + "%", Double.parseDouble(listaDespesa[4]));
        pieDataset.setValue("Internet : " + listaDespesa[5] + "%", Double.parseDouble(listaDespesa[5]));


//Cria um objeto JFreeChart passando os seguintes parametros
        JFreeChart grafico = ChartFactory.createPieChart(
                "Despesas", //Titulo do grafico
                pieDataset, //DataSet
                true, //Para mostrar ou não a legenda
                true, //Para mostrar ou não os tooltips
                false);
        
        this.add(new ChartPanel(grafico));
        
        this.pack(); //para ajustar automaticamente o Frame

        this.setVisible(true);
        this.setBounds(400, 200, 600, 600);
        
          setIconImage(new ImageIcon(getClass().getResource("/resources/ico_gestao.png")).getImage());
    }
    
    public static void main(String[] args) {
//        new GraficoDespesa().setVisible(true);
    }
    
    public void gerarGraficoDespesa() {
    }
}
