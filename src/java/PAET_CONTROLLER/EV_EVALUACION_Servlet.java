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

import PAET_BL.PAET_AM_ACCION_MEJORA_BL;
import PAET_BL.PAET_CU_CAUSA_BL;
import PAET_BL.PAET_EV_EVALUACION_BL;
import PAET_BL.PAET_MT_META_BL;
import PAET_DOMAIN.PaetAmAccionMejora;
import PAET_DOMAIN.PaetCuCausa;
import PAET_DOMAIN.PaetEvEvaluacion;
import PAET_DOMAIN.PaetMtMeta;
import java.io.IOException;
import java.io.PrintWriter;
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
public class EV_EVALUACION_Servlet extends HttpServlet {

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
            String json, campo, valor, fechaHastaStr, fechaDesdeStr, evResultadoStr;
            BigDecimal evCodigo, pgProcesoSeguimiento;
            Boolean unico;
            Date fechaHoy = new Date();
            Date fechaDesde, fechaHasta;
            DateFormat format;
            Short evResultado;
            PaetEvEvaluacion evaluacion = new PaetEvEvaluacion();
            PaetMtMeta meta = new PaetMtMeta();
            PaetCuCausa causa = new PaetCuCausa();
            PaetAmAccionMejora accionMejora = new PaetAmAccionMejora();
            PAET_EV_EVALUACION_BL evaluacionBl = new PAET_EV_EVALUACION_BL();
            PAET_MT_META_BL metaBl = new PAET_MT_META_BL();
            PAET_CU_CAUSA_BL causaBl = new PAET_CU_CAUSA_BL();
            PAET_AM_ACCION_MEJORA_BL accionMejoraBl = new PAET_AM_ACCION_MEJORA_BL();

            String accion = request.getParameter("accion");

            switch (accion) {
                case "agregarEvaluacion":
                    evaluacion.setEvFecha(fechaHoy);
                    fechaHastaStr = request.getParameter("evFechaHasta");
                    format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                    fechaHasta = format.parse(fechaHastaStr);
                    evaluacion.setEvFechaHasta(fechaHasta);
                    fechaDesdeStr = request.getParameter("evFechaDesde");
                    fechaDesde = format.parse(fechaDesdeStr);
                    evaluacion.setEvFechaDesde(fechaDesde);
                    evaluacion.setEvLogro(request.getParameter("evLogro").charAt(0));
                    evResultadoStr = request.getParameter("evResultado");
                    evResultado = (short)Double.parseDouble(evResultadoStr);
                    evaluacion.setEvResultado(evResultado);
                    pgProcesoSeguimiento = new BigDecimal(request.getParameter("pgProcesoSeguimiento"));
                    evaluacion.setPgProcesoSeguimiento(pgProcesoSeguimiento);
                    //Se guarda el objeto
                    evCodigo = evaluacionBl.saveWithReturn(evaluacion);

                    //Se imprime la respuesta con el response
                    out.print(evCodigo);
                    break;
                case "agregarMeta":
                    evCodigo = new BigDecimal(request.getParameter("evEvaluacion"));
                    meta.setEvEvaluacion(evCodigo);
                    meta.setMtDescripcion(request.getParameter("mtDescripcion"));
                    meta.setMtLimite(new BigDecimal(request.getParameter("mtLimite")));
                    meta.setMtPeso(new BigDecimal(request.getParameter("mtPeso")));
                    meta.setMtLimiteSobresaliente(new BigDecimal(request.getParameter("mtLimiteSobresaliente")));
                    meta.setMtLimiteBajo(new BigDecimal(request.getParameter("mtLimiteBajo")));
                    meta.setMtPorcentajeSobresaliente(new BigDecimal(request.getParameter("mtPorcentajeSobresaliente")));
                    meta.setMtPorcentajeBajo(new BigDecimal(request.getParameter("mtPorcentajeBajo")));
                    meta.setMtLogroAlcanzado(new BigDecimal(request.getParameter("mtLogroAlcanzado")));
                    meta.setMtPorcentajeAlcanzado(new BigDecimal(request.getParameter("mtPorcentajeAlcanzado")));
                    
                    metaBl.save(meta);
                    
                    out.print("C~La meta fue agregada correctamente.");
                    break;
                case "agregarCausa":
                    causa.setCuDescripcion(request.getParameter("cuDescripcion"));
                    causa.setEvEvaluacion(new BigDecimal(request.getParameter("evEvaluacion")));
                    
                    causaBl.save(causa);
                    
                    out.print("C~La causa fue agregada correctamente.");
                    break;
                case "agregarAccionMejora":
                    accionMejora.setAmDescripcion(request.getParameter("amDescripcion"));
                    accionMejora.setEvEvaluacion(new BigDecimal(request.getParameter("evEvaluacion")));
                    
                    accionMejoraBl.save(accionMejora);
                    
                    out.print("C~La accion de mejora fue agregada correctamente.");
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
