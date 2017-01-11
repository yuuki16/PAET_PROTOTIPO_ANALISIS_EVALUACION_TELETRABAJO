/*
 * Copyright (C) 2017 Michelle
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package PAET_CONTROLLER;

import PAET_BL.PAET_DO_DOCUMENTO_BL;
import PAET_DOMAIN.PaetDoDocumento;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Michelle
 */
public class DO_DOCUMENTO_Servlet extends HttpServlet {

    String nombreArchivo, proceso, parte;
    BigDecimal codProceso;
    Clob archivo;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            boolean ismultipart = ServletFileUpload.isMultipartContent(request);
            if (!ismultipart) {

            } else {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setRepository(new File("c:\\temp"));
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;

                try {
                    items = upload.parseRequest(request);
                } catch (Exception e) {
                    out.print("E~" + e.getMessage());
                }

                Iterator itr = items.iterator();
                while (itr.hasNext()) {
                    FileItem item = (FileItem) itr.next();
                    
                    if (item.isFormField()) {
                        parte = item.getFieldName();
                        if (parte.equals("proceso")) {
                            proceso = item.getString();
                        }
                        else if (parte.equals("psProcesoSolicitud")) {
                            codProceso = new BigDecimal(item.getString());
                             //guardarDocumento(item, out);
                        }
                    } else {
                        
                        String itemname = item.getName();
                        if ((itemname == null) || itemname.equals("")) {
                            continue;
                        }
                        String filename = FilenameUtils.getName(itemname);
                        File f = checkExist(filename);
                        item.write(f);
                        nombreArchivo = "C:\\temp\\"+f.getName();
                    }
                }
                
                guardarDocumento(out);
            }
        } catch (NumberFormatException e) {
            out.print("E~" + e.getMessage());
        } catch (Exception e) {
            out.print("E~" + e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private File checkExist(String filename) {
        File f = new File("C:/temp/" + filename);

        if (f.exists()) {
            StringBuffer sb = new StringBuffer(filename);
            sb.insert(sb.lastIndexOf("."), "-" + new Date().getTime());
            f = new File("C:/temp/" + "/" + sb.toString());
        }

        return f;
    }

    private void guardarDocumento(PrintWriter out) throws Exception {

        //BigDecimal psProcesoSolicitud;
        PaetDoDocumento documento = new PaetDoDocumento();
        PAET_DO_DOCUMENTO_BL documentoBl = new PAET_DO_DOCUMENTO_BL();
        Date fecha = new Date();

        try {
            //psProcesoSolicitud = codProceso;
            
            documento.setDoFecha(fecha);
            //documentacion.setDcOrigen(carpetaDestino);
            documento.setDoTipoProceso(proceso.charAt(0));
            //documentacion.setDcNombre(nombreArchivo);
            documento.setDoProceso(codProceso);
            
            String s = readFileAsString(nombreArchivo);
            documento.setDoArchivo(nombreArchivo);
            documento.setDoDocumento(s);
            
            documentoBl.save(documento);

            out.print("C~La documentación fue agregada correctamente");
        } catch (Exception e) {
            out.print("E~" + e.getMessage());
        }
    }
    
    private String readFileAsString(String filePath) throws java.io.IOException {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }
    
}
