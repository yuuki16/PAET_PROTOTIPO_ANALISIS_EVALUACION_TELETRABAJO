/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_CONTROLLER;

import PAET_BL.PAET_CR_CORREO_BL;
import PAET_DOMAIN.PaetCrCorreo;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michelle
 */
public class CR_CORREO_Servlet extends HttpServlet {

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
            String json, campo, valor;
            Boolean unico;
            BigDecimal crCodigo;
            PaetCrCorreo correo = new PaetCrCorreo();
            PAET_CR_CORREO_BL correoBl = new PAET_CR_CORREO_BL();

            Thread.sleep(1000);

            String accion = request.getParameter("accion");

            switch (accion) {
                case "consultarCorreos":
                    json = new Gson().toJson(correoBl.findAll(PaetCrCorreo.class.getName()));
                    out.print(json);
                    break;
                case "consultarCorreoByCodigo":
                    //se consulta el objeto por ID
                    crCodigo = new BigDecimal(request.getParameter("crCodigo"));
                    correo = correoBl.findById(crCodigo);

                    //se pasa la informacion del objeto a formato JSON
                    json = new Gson().toJson(correo);
                    out.print(json);
                    break;
                case "agregarCorreo":
                case "modificarCorreo":
                    
                    correo.setCrCorreo(request.getParameter("crCorreo"));
                    correo.setCrEstado(request.getParameter("crEstado").charAt(0));
                    correo.setTrTrabajador(request.getParameter("trTrabajador"));
                    
                    if (accion.equals("agregarCorreo")) { //es insertar
                        
                        //Se guarda el objeto
                        correoBl.save(correo);

                        //Se imprime la respuesta con el response
                        out.print("C~El correo fue agregado correctamente");

                    } else {//es modificar 
                        crCodigo = new BigDecimal(request.getParameter("crCodigo"));
                        correo.setCrCodigo(crCodigo);
                        //Se guarda el objeto
                        correoBl.merge(correo);

                        //Se imprime la respuesta con el response
                        out.print("C~El correo fue modificado correctamente");
                    }
                    break;
                case "consultaDinamica":
                    campo = request.getParameter("campo");
                    valor = request.getParameter("valor");
                    unico = Boolean.valueOf(request.getParameter("unico"));

                    //se consulta el objeto por el campo y el valor 
                    json = new Gson().toJson(correoBl.findDynamicFilter(campo, valor, unico, PaetCrCorreo.class.getName()));
                    out.print(json);
                    break;
                default:
                    out.print("E~No se indicó la acción que se desea realizar");
                    break;
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

}
