package com.ogue.strategy;


import net.sf.jasperreports.engine.*;
import java.io.InputStream;
import java.util.Map;

public class ReportStrategyContext implements ReportStrategy {


    @Override
    public byte[] generateReport(String reportType, Map<String, Object> params) throws JRException {
        try {

            InputStream reportStream = this.getClass().getResourceAsStream("/reports/" + reportType);
            if (reportStream == null) {
                throw new IllegalArgumentException("Template file not found: " + reportType);
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, params, new JREmptyDataSource());
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new JRException("Error al generar el reporte", e);
        }

    }

}