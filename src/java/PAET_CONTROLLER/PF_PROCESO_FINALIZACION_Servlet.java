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

import PAET_BL.PAET_PF_PROCESO_FINALIZACION_BL;
import PAET_DOMAIN.PaetPfProcesoFinalizacion;
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
public class PF_PROCESO_FINALIZACION_Servlet extends HttpServlet {

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
            BigDecimal pfCodigo, ttTeletrabajador, esEstado;
            Boolean unico;
            Date fechaHoy = new Date();
            PaetPfProcesoFinalizacion procesoFinalizacion = new PaetPfProcesoFinalizacion();
            PAET_PF_PROCESO_FINALIZACION_BL procesoFinalizacionBl = new PAET_PF_PROCESO_FINALIZACION_BL();

            String accion = request.getParameter("accion");

            switch (accion) {
                case "consultarProcesosFinalizacion":
                    json = new Gson().toJson(procesoFinalizacionBl.findAll(PaetPfProcesoFinalizacion.class.getName()));
                    out.print(json);
                    break;
                case "consultarProcesoFinalizacionByCodigo":
                    pfCodigo = new BigDecimal(request.getParameter("pfCodigo"));
                    //se consulta el objeto por ID
                    procesoFinalizacion = procesoFinalizacionBl.findById(pfCodigo);

                    //se pasa la informacion del objeto a formato JSON
                    json = new Gson().toJson(procesoFinalizacion);
                    out.print(json);
                    break;
                case "agregarProcesoFinalizacion":
                case "modificarProcesoFinalizacion":

                    if (accion.equals("agregarProcesoFinalizacion")) { //es insertar
                        ttTeletrabajador = new BigDecimal(request.getParameter("ttTeletrabajador"));
                        procesoFinalizacion.setPfFecha(fechaHoy);
                        procesoFinalizacion.setPfFechaAtendido(fechaHoy);
                        procesoFinalizacion.setTtTeletrabajador(ttTeletrabajador);
                        procesoFinalizacion.setEsEstado(new BigDecimal(61));
                        procesoFinalizacion.setPfEstado('F');
                        //Se guarda el objeto
                        procesoFinalizacionBl.save(procesoFinalizacion);

                        procesoFinalizacion.setPfFechaAtendido(null);
                        procesoFinalizacion.setEsEstado(new BigDecimal(11));
                        procesoFinalizacion.setPfEstado('P');

                        procesoFinalizacionBl.save(procesoFinalizacion);

                        //Se imprime la respuesta con el response
                        out.print("C~El proceso de finalización fue agregado correctamente");

                    } else {//es modificar 
                        pfCodigo = new BigDecimal(request.getParameter("pfProcesoFinalizacion"));
                        if (!"".equals(request.getParameter("pfObservacion").trim())) {
                            procesoFinalizacion.setPfObservacion(request.getParameter("pfObservacion"));
                        }
                        procesoFinalizacion.setPfCodigo(pfCodigo);
                        procesoFinalizacion.setPfFechaAtendido(fechaHoy);
                        procesoFinalizacion.setPfEstado('F');
                        //Se guarda el objeto
                        procesoFinalizacionBl.merge(procesoFinalizacion);

                        //Se imprime la respuesta con el response
                        out.print("C~El proceso de finalización fue modificado correctamente");
                    }
                    break;
                case "consultaDinamica":
                    campo = request.getParameter("campo");
                    valor = request.getParameter("valor");
                    unico = Boolean.valueOf(request.getParameter("unico"));
                    //se consulta el objeto por el campo y el valor 
                    json = new Gson().toJson(procesoFinalizacionBl.findDynamicFilter(campo, valor, unico, PaetPfProcesoFinalizacion.class.getName()));
                    out.print(json);
                    break;
                case "avanzarProcesoFinalizacion":
                    pfCodigo = new BigDecimal(request.getParameter("pfCodigo"));
                    esEstado = new BigDecimal(request.getParameter("pfEstadoNuevo"));
                    ttTeletrabajador = new BigDecimal(request.getParameter("ttTeletrabajador"));
                    if (!"".equals(request.getParameter("pfObservacion").trim())) {
                        procesoFinalizacion.setPfObservacion(request.getParameter("pfObservacion"));
                    }
                    procesoFinalizacion.setPfFechaAtendido(fechaHoy);
                    procesoFinalizacion.setPfEstado('F');
                    procesoFinalizacion.setPfCodigo(pfCodigo);

                    procesoFinalizacionBl.merge(procesoFinalizacion);

                    procesoFinalizacion = new PaetPfProcesoFinalizacion();
                    procesoFinalizacion.setPfFecha(fechaHoy);
                    procesoFinalizacion.setEsEstado(esEstado);
                    procesoFinalizacion.setPfEstado('P');
                    procesoFinalizacion.setTtTeletrabajador(ttTeletrabajador);

                    pfCodigo = procesoFinalizacionBl.saveWithReturn(procesoFinalizacion);
                    out.print(pfCodigo);
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
