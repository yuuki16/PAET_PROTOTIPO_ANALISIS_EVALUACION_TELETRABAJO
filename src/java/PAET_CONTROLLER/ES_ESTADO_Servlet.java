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

import PAET_BL.PAET_ES_ESTADO_BL;
import PAET_DOMAIN.PaetEsEstado;
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
public class ES_ESTADO_Servlet extends HttpServlet {

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
            BigDecimal esCodigo;
            Boolean unico;
            PaetEsEstado estado = new PaetEsEstado();
            PAET_ES_ESTADO_BL estadoBl = new PAET_ES_ESTADO_BL();

            String accion = request.getParameter("accion");

            switch (accion) {
                case "consultarEstados":
                    json = new Gson().toJson(estadoBl.findAll(PaetEsEstado.class.getName()));
                    out.print(json);
                    break;
                case "consultarEstadoByCodigo":
                    esCodigo = new BigDecimal(request.getParameter("esCodigo"));
                    //se consulta el objeto por ID
                    estado = estadoBl.findById(esCodigo);

                    //se pasa la informacion del objeto a formato JSON
                    json = new Gson().toJson(estado);
                    out.print(json);
                    break;
                case "agregarEstado":
                case "modificarEstado":

                    if (accion.equals("agregarEstado")) { //es insertar
                        //Se guarda el objeto
                        estadoBl.save(estado);

                        //Se imprime la respuesta con el response
                        out.print("C~El estado fue agregado correctamente");

                    } else {//es modificar 
                        esCodigo = new BigDecimal(request.getParameter("esCodigo"));

                        estado.setEsCodigo(esCodigo);
                        //Se guarda el objeto
                        estadoBl.merge(estado);

                        //Se imprime la respuesta con el response
                        out.print("C~El estado fue modificado correctamente");
                    }
                    break;
                case "consultaDinamica":
                    campo = request.getParameter("campo");
                    valor = request.getParameter("valor");
                    unico = Boolean.valueOf(request.getParameter("unico"));
                    //se consulta el objeto por el campo y el valor 
                    json = new Gson().toJson(estadoBl.findDynamicFilter(campo, valor, unico, PaetEsEstado.class.getName()));
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
