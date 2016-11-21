<%-- 
    Document   : REPORTE_TELETRABAJADORES_JSP
    Created on : 20-nov-2016, 23:43:31
    Author     : Michelle
--%>
<%@page contentType="application/pdf"%>

<%@ page trimDirectiveWhitespaces="true"%> 

<%@ page  import="java.io.*"%> 
<%@ page  import="java.sql.*"%> 
<%@ page  import="net.sf.jasperreports.engine.*"%>
<!DOCTYPE HTML>


<%
    Connection conn = null;
    String reporte = null;
    try {
        reporte = (String)session.getAttribute("reporte");
        //String sear=(String)session.getAttribute("filtro");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "mmonge", "mmonge");

        //File reportFile = new File(application.getRealPath("\\reports\\reporteTeletrabajadoresSexo\\reporteTeletrabajadoresSexo.jasper"));//your report_name.jasper file
        String jrxmlFile = session.getServletContext().getRealPath("/reports/reporteTeletrabajadoresSexo/"+reporte+".jrxml");
        InputStream input = new FileInputStream(new File(jrxmlFile));

        //Generate report
        JasperReport jasperReport = JasperCompileManager.compileReport(input);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

        //report as pdf
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
        if (conn != null) {
            conn.close();
        }
    }
%>
