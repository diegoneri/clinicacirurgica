/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relatorio;

import br.com.cirurgica.dao.CirurgicaConnectionManager;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRXlsExporter;

/**
 *
 * @author 13b Pessoal
 */
public class ImprimirXLS {

    public void mprimir() {

        try {

            Connection conn = CirurgicaConnectionManager.getConnection();
            File reportFile = new File("C:jspsample.jasper");
            String path = reportFile.getAbsolutePath(); //tried getAbsolutePath()
//System.out.println("Absolute Path = " + reportFile.getAbsolutePath());
            Map parameters = new HashMap();
            parameters.put("myparam", new Integer(2));
            JasperPrint jasperPrint = null;// JasperManager.fillReport(path, parameters, conn);
            byte bytes[] = new byte[10];
            String result = JasperRunManager.runReportToHtmlFile("C:jspsample.jasper", parameters, conn);
            JRXlsExporter exporter = new JRXlsExporter();

            ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, "C:JSP");
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "sample.xls");
            exporter.exportReport();
            System.out.println("Sixe of byte array:" + xlsReport.size());
            bytes = xlsReport.toByteArray();
//            response.setContentType("application/vnd.ms-excel");
            System.out.println("After JasperPrint = " + result);
//            response.setContentLength(bytes.length);
            xlsReport.close();
//            OutputStream ouputStream = response.getOutputStream();
//            ouputStream.write(bytes, 0, bytes.length);
//            ouputStream.flush();
//            ouputStream.close();
        } catch (Exception e) {
        }
    }
}
