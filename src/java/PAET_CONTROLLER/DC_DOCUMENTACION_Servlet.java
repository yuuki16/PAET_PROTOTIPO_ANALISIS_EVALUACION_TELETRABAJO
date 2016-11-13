/*
 * Copyright (C) 2016 Michelle
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

import PAET_BL.PAET_DC_DOCUMENTACION_BL;
import PAET_DOMAIN.PaetDcDocumentacion;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.mail.Multipart;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Michelle
 */
@WebServlet(urlPatterns = {"/DC_DOCUMENTACION_Sevlet"})
@MultipartConfig(location = "/DC_DOCUMENTACION_Sevlet", maxFileSize = 10485760L) // 10MB
public class DC_DOCUMENTACION_Servlet extends HttpServlet {

    String carpetaDestino = "C:/uploads/";
    String nombreArchivo;

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
                FileItemFactory factory = new DiskFileItemFactory();
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
                        guardarDocumento(item, out);
                    } else {
                        String itemname = item.getName();
                        if ((itemname == null) || itemname.equals("")) {
                            continue;
                        }
                        String filename = FilenameUtils.getName(itemname);
                        File f = checkExist(filename);
                        item.write(f);
                        nombreArchivo = f.getName();
                    }
                }
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
        File f = new File(carpetaDestino + "/" + filename);

        if (f.exists()) {
            StringBuffer sb = new StringBuffer(filename);
            sb.insert(sb.lastIndexOf("."), "-" + new Date().getTime());
            f = new File(carpetaDestino + "/" + sb.toString());
        }

        return f;
    }

    private void guardarDocumento(FileItem item, PrintWriter out) throws Exception {

        BigDecimal psProcesoSolicitud;
        PaetDcDocumentacion documentacion = new PaetDcDocumentacion();
        PAET_DC_DOCUMENTACION_BL documentacionBl = new PAET_DC_DOCUMENTACION_BL();
        Date fecha = new Date();

        try {
            psProcesoSolicitud = new BigDecimal(item.getString());
            documentacion.setDcFecha(fecha);
            documentacion.setDcOrigen(carpetaDestino);
            documentacion.setDcTipoProceso('S');
            documentacion.setDcNombre(nombreArchivo);
            documentacion.setDcProceso(psProcesoSolicitud);

            documentacionBl.save(documentacion);

            out.print("C~La documentación fue agregada correctamente");
        } catch (Exception e) {
            out.print("E~" + e.getMessage());
        }
    }

}
