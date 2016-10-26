/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_CONTROLLER;

import PAET_BL.PAET_FC_FACTOR_COMPLEMENTARIO_BL;
import PAET_DOMAIN.PaetFcFactorComplementario;
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
public class FC_FACTOR_COMPLEMENTARIO_Servlet extends HttpServlet {

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
            BigDecimal fcCodigo;
            Boolean unico;
            PaetFcFactorComplementario factorComplementario = new PaetFcFactorComplementario();
            PAET_FC_FACTOR_COMPLEMENTARIO_BL factorComplementarioBl = new PAET_FC_FACTOR_COMPLEMENTARIO_BL();

            Thread.sleep(1000);

            HttpSession session = request.getSession();
            String accion = request.getParameter("accion");

            switch (accion) {
                case "consultarFactoresComplementarios":
                    json = new Gson().toJson(factorComplementarioBl.findAll(PaetFcFactorComplementario.class.getName()));
                    out.print(json);
                    break;
                case "consultarFactorComplementarioByCodigo":
                    fcCodigo = new BigDecimal(request.getParameter("fcCodigo"));
                    //se consulta el objeto por ID
                    factorComplementario = factorComplementarioBl.findById(fcCodigo);

                    //se pasa la informacion del objeto a formato JSON
                    json = new Gson().toJson(factorComplementario);
                    out.print(json);
                    break;
                case "agregarFactorComplementario":
                case "modificarFactorComplementario":

                    if (accion.equals("agregarFactorComplementario")) { //es insertar
                        factorComplementario.setFcDescripcion(request.getParameter("fcDescripcion"));
                        factorComplementario.setFcEstado(request.getParameter("fcEstado").charAt(0));
                        //Se guarda el objeto
                        factorComplementarioBl.save(factorComplementario);

                        //Se imprime la respuesta con el response
                        out.print("C~El factor complementario fue agregado correctamente");

                    } else {//es modificar 
                        fcCodigo = new BigDecimal(request.getParameter("fcCodigo"));

                        factorComplementario.setFcCodigo(fcCodigo);
                        factorComplementario.setFcDescripcion(request.getParameter("fcDescripcion"));
                        factorComplementario.setFcEstado(request.getParameter("fcEstado").charAt(0));
                        //Se guarda el objeto
                        factorComplementarioBl.merge(factorComplementario);

                        //Se imprime la respuesta con el response
                        out.print("C~El factor complementario fue modificado correctamente");
                    }
                    break;
                case "consultaDinamica":
                    campo = request.getParameter("campo");
                    valor = request.getParameter("valor");
                    unico = Boolean.valueOf(request.getParameter("unico"));
                    //se consulta el objeto por el campo y el valor 
                    json = new Gson().toJson(factorComplementarioBl.findDynamicFilter(campo, valor, unico, PaetFcFactorComplementario.class.getName()));
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
