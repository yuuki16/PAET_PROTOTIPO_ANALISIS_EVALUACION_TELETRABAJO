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

import PAET_BL.PAET_DI_TT_DIA_TELETRABAJADOR_BL;
import PAET_BL.PAET_TT_TELETRABAJADOR_BL;
import PAET_DOMAIN.PaetDiTtDiaTeletrabajador;
import PAET_DOMAIN.PaetTtTeletrabajador;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michelle
 */
public class TT_TELETRABAJADOR_Servlet extends HttpServlet {

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
            BigDecimal ttCodigo, slSolicitud;
            Boolean unico;
            Date fechaHoy = new Date();
            PaetTtTeletrabajador teletrabajador = new PaetTtTeletrabajador();
            PaetDiTtDiaTeletrabajador diaTeletrabajador = new PaetDiTtDiaTeletrabajador();
            PAET_TT_TELETRABAJADOR_BL teletrabajadorBl = new PAET_TT_TELETRABAJADOR_BL();
            PAET_DI_TT_DIA_TELETRABAJADOR_BL diaTeletrabajadorBl = new PAET_DI_TT_DIA_TELETRABAJADOR_BL();
                
            
            String accion = request.getParameter("accion");

            switch (accion) {
                case "consultarTeletrabajadores":
                    json = new Gson().toJson(teletrabajadorBl.findAll(PaetTtTeletrabajador.class.getName()));
                    out.print(json);
                    break;
                case "consultarTeletrabajadorByCodigo":
                    ttCodigo = new BigDecimal(request.getParameter("ttCodigo"));
                    //se consulta el objeto por ID
                    teletrabajador = teletrabajadorBl.findById(ttCodigo);

                    //se pasa la informacion del objeto a formato JSON
                    json = new Gson().toJson(teletrabajador);
                    out.print(json);
                    break;
                case "agregarTeletrabajador":
                case "modificarTeletrabajador":

                    if (accion.equals("agregarTeletrabajador")) { //es insertar
                        slSolicitud = new BigDecimal(request.getParameter("slSolicitud"));
                        teletrabajador.setSlSolicitud(slSolicitud);
                        teletrabajador.setTtFechaInicio(fechaHoy);
                        teletrabajador.setTrTrabajador(request.getParameter("trTrabajador"));
                        teletrabajador.setTtEstado('A');
                        //Se guarda el objeto
                        ttCodigo = teletrabajadorBl.saveWithReturn(teletrabajador);

                        //Se imprime la respuesta con el response
                        out.print(ttCodigo);

                    } else {//es modificar 
                        ttCodigo = new BigDecimal(request.getParameter("ttCodigo"));

                        //Se guarda el objeto
                        teletrabajadorBl.merge(teletrabajador);

                        //Se imprime la respuesta con el response
                        out.print("C~La gerencia fue modificado correctamente");
                    }
                    break;
                case "consultaDinamica":
                    campo = request.getParameter("campo");
                    valor = request.getParameter("valor");
                    unico = Boolean.valueOf(request.getParameter("unico"));
                    //se consulta el objeto por el campo y el valor 
                    json = new Gson().toJson(teletrabajadorBl.findDynamicFilter(campo, valor, unico, PaetTtTeletrabajador.class.getName()));
                    out.print(json);
                    break;
                case "agregarDiasTeletrabajador":
                    ttCodigo = new BigDecimal(request.getParameter("ttTeletrabajador"));
                    diaTeletrabajador.setDiTtEstado('A');
                    diaTeletrabajador.setDiDia(request.getParameter("diDia").charAt(0));
                    diaTeletrabajador.setTtTeletrabajador(ttCodigo);
                    
                    diaTeletrabajadorBl.save(diaTeletrabajador);
                    
                    out.print("C~El día fue agregado correctamente");
                    break;
                 case "consultarDiasTeletrabajador":
                    campo = request.getParameter("campo");
                    valor = request.getParameter("valor");
                    unico = Boolean.valueOf(request.getParameter("unico"));
                    //se consulta el objeto por el campo y el valor 
                    json = new Gson().toJson(diaTeletrabajadorBl.findDynamicFilter(campo, valor, unico, PaetDiTtDiaTeletrabajador.class.getName()));
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
