/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_CONTROLLER;

import PAET_BL.PAET_GR_GERENCIA_BL;
import PAET_DOMAIN.PaetGrGerencia;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michelle
 */
public class GR_GERENCIA_Servlet extends HttpServlet {

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
            BigDecimal grCodigo;
            Boolean unico;
            PaetGrGerencia gerencia = new PaetGrGerencia();
            PAET_GR_GERENCIA_BL gerenciaBl = new PAET_GR_GERENCIA_BL();
            
            String accion = request.getParameter("accion");

            switch (accion) {
                case "consultarGerencias":
                    json = new Gson().toJson(gerenciaBl.findAll(PaetGrGerencia.class.getName()));
                    out.print(json);
                    break;
                case "consultarGerenciaByCodigo":
                    grCodigo = new BigDecimal(request.getParameter("grCodigo"));
                    //se consulta el objeto por ID
                    gerencia = gerenciaBl.findById(grCodigo);

                    //se pasa la informacion del objeto a formato JSON
                    json = new Gson().toJson(gerencia);
                    out.print(json);
                    break;
                case "agregarGerencia":
                case "modificarGerencia":

                    if (accion.equals("agregarGerencia")) { //es insertar
                        gerencia.setGrDescripcion(request.getParameter("grDescripcion"));
                        gerencia.setGrEstado(request.getParameter("grEstado").charAt(0));
                        //Se guarda el objeto
                        gerenciaBl.save(gerencia);

                        //Se imprime la respuesta con el response
                        out.print("C~La gerencia fue agregado correctamente");

                    } else {//es modificar 
                        grCodigo = new BigDecimal(request.getParameter("grCodigo"));

                        gerencia.setGrCodigo(grCodigo);
                        gerencia.setGrDescripcion(request.getParameter("grDescripcion"));
                        gerencia.setGrEstado(request.getParameter("grEstado").charAt(0));
                        //Se guarda el objeto
                        gerenciaBl.merge(gerencia);

                        //Se imprime la respuesta con el response
                        out.print("C~La gerencia fue modificado correctamente");
                    }
                    break;
                case "consultaDinamica":
                    campo = request.getParameter("campo");
                    valor = request.getParameter("valor");
                    unico = Boolean.valueOf(request.getParameter("unico"));
                    //se consulta el objeto por el campo y el valor 
                    json = new Gson().toJson(gerenciaBl.findDynamicFilter(campo, valor, unico, PaetGrGerencia.class.getName()));
                    out.print(json);
                    break;
                default:
                    out.print("E~No se indico la acción que se desea realizare");
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
