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

import PAET_BL.PAET_FC_AN_FACTOR_ANALISIS_BL;
import PAET_DOMAIN.PaetFcAnFactorAnalisis;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michelle
 */
public class FC_AN_FACTOR_ANALISIS_Servlet extends HttpServlet {

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
            Short fcAnGrado;
            BigDecimal fcAnCodigo, fcFactor, anAnalisis;
            Boolean unico;
            PaetFcAnFactorAnalisis factorAnalisis = new PaetFcAnFactorAnalisis();
            PAET_FC_AN_FACTOR_ANALISIS_BL factorAnalisisBl = new PAET_FC_AN_FACTOR_ANALISIS_BL();
            
            String accion = request.getParameter("accion");

            switch (accion) {
                case "consultarFactoresAnalisis":
                    json = new Gson().toJson(factorAnalisisBl.findAll(PaetFcAnFactorAnalisis.class.getName()));
                    out.print(json);
                    break;
                case "consultaFactorAnalisisByCodigo":
                    fcAnCodigo = new BigDecimal(request.getParameter("fcAnCodigo"));
                    //se consulta el objeto por ID
                    factorAnalisis = factorAnalisisBl.findById(fcAnCodigo);

                    //se pasa la informacion del objeto a formato JSON
                    json = new Gson().toJson(factorAnalisis);
                    out.print(json);
                    break;
                case "agregarFactorAnalisis":
                case "modificarFactorAnalisis":

                    if (accion.equals("agregarFactorAnalisis")) { //es insertar
                        fcFactor = new BigDecimal(request.getParameter("fcFactor"));
                        anAnalisis = new BigDecimal(request.getParameter("anAnalisis"));
                        fcAnGrado = new Short(request.getParameter("fcAnGrado"));
                        factorAnalisis.setFcFactor(fcFactor);
                        factorAnalisis.setAnAnalisis(anAnalisis);
                        factorAnalisis.setFcAnGrado(fcAnGrado);
                        factorAnalisisBl.save(factorAnalisis);

                        //Se imprime la respuesta con el response
                        out.print("C~El factor del análisis fue agregado correctamente");

                    } else {//es modificar 
                        fcAnCodigo = new BigDecimal(request.getParameter("fcAnCodigo"));
                        //Se guarda el objeto
                        factorAnalisisBl.merge(factorAnalisis);

                        //Se imprime la respuesta con el response
                        out.print("C~La gerencia fue modificado correctamente");
                    }
                    break;
                case "consultaDinamica":
                    campo = request.getParameter("campo");
                    valor = request.getParameter("valor");
                    unico = Boolean.valueOf(request.getParameter("unico"));
                    //se consulta el objeto por el campo y el valor 
                    json = new Gson().toJson(factorAnalisisBl.findDynamicFilter(campo, valor, unico, PaetFcAnFactorAnalisis.class.getName()));
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
