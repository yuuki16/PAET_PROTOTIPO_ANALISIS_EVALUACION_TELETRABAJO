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

import PAET_BL.PAET_PG_PROCESO_SEGUIMIENTO_BL;
import PAET_BL.PAET_RE_RECOMENDACION_BL;
import PAET_DOMAIN.PaetPgProcesoSeguimiento;
import PAET_DOMAIN.PaetReRecomendacion;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.String.format;
import static java.lang.String.format;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michelle
 */
public class PG_PROCESO_SEGUIMIENTO_Servlet extends HttpServlet {

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
            String json, campo, valor, fechaLimiteStr;
            BigDecimal pgCodigo, pgNumero, ttTeletrabajador, esEstado;
            Boolean unico;
            Date fechaHoy = new Date();
            Date fechaLimite;
            DateFormat format;
            PaetPgProcesoSeguimiento procesoSeguimiento = new PaetPgProcesoSeguimiento();
            PaetReRecomendacion recomendacion = new PaetReRecomendacion();
            PAET_PG_PROCESO_SEGUIMIENTO_BL procesoSeguimientoBl = new PAET_PG_PROCESO_SEGUIMIENTO_BL();
            PAET_RE_RECOMENDACION_BL recomendacionBl = new PAET_RE_RECOMENDACION_BL();

            String accion = request.getParameter("accion");

            switch (accion) {
                case "consultarProcesosSeguimiento":
                    json = new Gson().toJson(procesoSeguimientoBl.findAll(PaetPgProcesoSeguimiento.class.getName()));
                    out.print(json);
                    break;
                case "consultarProcesoSeguimientoByCodigo":
                    pgCodigo = new BigDecimal(request.getParameter("pgCodigo"));
                    //se consulta el objeto por ID
                    procesoSeguimiento = procesoSeguimientoBl.findById(pgCodigo);

                    //se pasa la informacion del objeto a formato JSON
                    json = new Gson().toJson(procesoSeguimiento);
                    out.print(json);
                    break;
                case "agregarProcesoSeguimiento":
                case "modificarProcesoSeguimiento":

                    if (accion.equals("agregarProcesoSeguimiento")) { //es insertar
                        pgNumero = new BigDecimal(request.getParameter("pgNumero"));
                        ttTeletrabajador = new BigDecimal(request.getParameter("ttTeletrabajador"));
                        procesoSeguimiento.setPgFecha(fechaHoy);
                        procesoSeguimiento.setPgFechaAtendido(fechaHoy);
                        procesoSeguimiento.setPgNumero(pgNumero);
                        procesoSeguimiento.setTtTeletrabajador(ttTeletrabajador);
                        procesoSeguimiento.setEsEstado(new BigDecimal(41));
                        procesoSeguimiento.setPgEstado('F');
                        //Se guarda el objeto
                        procesoSeguimientoBl.save(procesoSeguimiento);

                        procesoSeguimiento.setPgFechaAtendido(null);
                        procesoSeguimiento.setEsEstado(new BigDecimal(8));
                        procesoSeguimiento.setPgEstado('P');

                        procesoSeguimientoBl.save(procesoSeguimiento);

                        //Se imprime la respuesta con el response
                        out.print("C~El proceso de seguimiento fue agregado correctamente");

                    } else {//es modificar 
                        pgCodigo = new BigDecimal(request.getParameter("pgCodigo"));

                        //Se guarda el objeto
                        procesoSeguimientoBl.merge(procesoSeguimiento);

                        //Se imprime la respuesta con el response
                        out.print("C~El proceso de seguimiento fue modificado correctamente");
                    }
                    break;
                case "consultaDinamica":
                    campo = request.getParameter("campo");
                    valor = request.getParameter("valor");
                    unico = Boolean.valueOf(request.getParameter("unico"));
                    //se consulta el objeto por el campo y el valor 
                    json = new Gson().toJson(procesoSeguimientoBl.findDynamicFilter(campo, valor, unico, PaetPgProcesoSeguimiento.class.getName()));
                    out.print(json);
                    break;
                case "avanzarProcesoSeguimiento":
                    pgCodigo = new BigDecimal(request.getParameter("pgCodigo"));
                    pgNumero = new BigDecimal(request.getParameter("pgNumero"));
                    esEstado = new BigDecimal(request.getParameter("pgEstadoNuevo"));
                    ttTeletrabajador = new BigDecimal(request.getParameter("ttTeletrabajador"));
                    if (!"".equals(request.getParameter("pgObservacion").trim())) {
                        procesoSeguimiento.setPgObservacion(request.getParameter("pgObservacion"));
                    }
                    procesoSeguimiento.setPgFechaAtendido(fechaHoy);
                    procesoSeguimiento.setPgEstado('F');
                    procesoSeguimiento.setPgCodigo(pgCodigo);

                    procesoSeguimientoBl.merge(procesoSeguimiento);

                    procesoSeguimiento = new PaetPgProcesoSeguimiento();
                    procesoSeguimiento.setPgFecha(fechaHoy);
                    procesoSeguimiento.setPgNumero(pgNumero);
                    procesoSeguimiento.setEsEstado(esEstado);
                    procesoSeguimiento.setPgEstado('P');
                    procesoSeguimiento.setTtTeletrabajador(ttTeletrabajador);

                    pgCodigo = procesoSeguimientoBl.saveWithReturn(procesoSeguimiento);
                    out.print(pgCodigo);
                    break;
                case "guardarRecomendacion":
                    pgCodigo = new BigDecimal(request.getParameter("pgProcesoSeguimiento"));
                    fechaLimiteStr = request.getParameter("reFechaLimite");
                    format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                    fechaLimite = format.parse(fechaLimiteStr);
                    recomendacion.setReFechaLimite(fechaLimite);
                    recomendacion.setPgProcesoSeguimiento(pgCodigo);
                    recomendacion.setReDescripcion(request.getParameter("reDescripcion"));
                    recomendacion.setReEstado('P');
                    
                    recomendacionBl.save(recomendacion);
                    
                    out.print("C~El proceso de seguimiento fue agregado correctamente");
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
