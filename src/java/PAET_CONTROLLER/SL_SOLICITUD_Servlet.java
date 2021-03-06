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

import PAET_BL.PAET_DI_SL_DIA_SOLICITUD_BL;
import PAET_BL.PAET_ET_SL_EQUIPO_SOLICITUD_BL;
import PAET_BL.PAET_SL_SOLICITUD_BL;
import PAET_DOMAIN.PaetDiSlDiaSolicitud;
import PAET_DOMAIN.PaetEtSlEquipoSolicitud;
import PAET_DOMAIN.PaetSlSolicitud;
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
public class SL_SOLICITUD_Servlet extends HttpServlet {

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
            BigDecimal slCodigo, etEquipoTecnologico;
            Date slFecha;
            long slFechal;
            Boolean unico;
            PaetSlSolicitud solicitud = new PaetSlSolicitud();
            PAET_SL_SOLICITUD_BL solicitudBl = new PAET_SL_SOLICITUD_BL();
            PaetDiSlDiaSolicitud diaSolicitud = new PaetDiSlDiaSolicitud();
            PAET_DI_SL_DIA_SOLICITUD_BL diaSolicitudBl = new PAET_DI_SL_DIA_SOLICITUD_BL();
            PaetEtSlEquipoSolicitud equipoTecnologicoSolicitud = new PaetEtSlEquipoSolicitud();
            PAET_ET_SL_EQUIPO_SOLICITUD_BL equipoTecnologicoSolicitudBl = new PAET_ET_SL_EQUIPO_SOLICITUD_BL();

            String accion = request.getParameter("accion");

            switch (accion) {
                case "consultarSolicitudes":
                    json = new Gson().toJson(solicitudBl.findAll(PaetSlSolicitud.class.getName()));
                    out.print(json);
                    break;
                case "consultarSolicitudByCodigo":
                    slCodigo = new BigDecimal(request.getParameter("slCodigo"));
                    //se consulta el objeto por ID
                    solicitud = solicitudBl.findById(slCodigo);

                    //se pasa la informacion del objeto a formato JSON
                    json = new Gson().toJson(solicitud);
                    out.print(json);
                    break;
                case "agregarSolicitud":
                case "modificarSolicitud":

                    if (accion.equals("agregarSolicitud")) { //es insertar
                        slFechal = new Long(request.getParameter("slFecha"));
                        slFecha = new Date(slFechal);
                        
                        solicitud.setSlJustificacion(request.getParameter("slJustificacion"));
                        solicitud.setSlFecha(slFecha);
                        solicitud.setSlModalidad(request.getParameter("slModalidad").charAt(0));
                        solicitud.setSlTiempo(request.getParameter("slTiempo"));
                        solicitud.setSlConectividad(request.getParameter("slConectividad").charAt(0));
                        solicitud.setSlTelefonia(request.getParameter("slTelefonia").charAt(0));
                        solicitud.setSlResultado(request.getParameter("slResultado").charAt(0));
                        solicitud.setTrTrabajador(request.getParameter("trTrabajador"));
                        solicitud.setSlBeneficios(request.getParameter("slBeneficios"));
                        
                        //Se guarda el objeto
                        slCodigo = solicitudBl.saveWithReturn(solicitud);
                        
                        //Se imprime la respuesta con el response
                        out.print(slCodigo);

                    } else {//es modificar 
                        slCodigo = new BigDecimal(request.getParameter("slCodigo"));

                        solicitud.setSlCodigo(slCodigo);
                        solicitud.setSlResultado(request.getParameter("slResultado").charAt(0));
                        //Se guarda el objeto
                        solicitudBl.merge(solicitud);

                        //Se imprime la respuesta con el response
                        out.print("C~La solicitud fue modificada correctamente");
                    }
                    break;
                case "consultaDinamica":
                    campo = request.getParameter("campo");
                    valor = request.getParameter("valor");
                    unico = Boolean.valueOf(request.getParameter("unico"));
                    //se consulta el objeto por el campo y el valor 
                    json = new Gson().toJson(solicitudBl.findDynamicFilter(campo, valor, unico, PaetSlSolicitud.class.getName()));
                    out.print(json);
                    break;
                case "guardarDiaSolicitud":
                    diaSolicitud.setDiDia(request.getParameter("diDia").charAt(0));
                    slCodigo = new BigDecimal(request.getParameter("slSolicitud"));
                    diaSolicitud.setSlSolicitud(slCodigo);
                    
                    diaSolicitudBl.save(diaSolicitud);
                    
                    out.print("C~Los días de la solicitud fueron ingresados correctamente");
                    break;
                case "guardarEquipoTecnologico":
                    etEquipoTecnologico = new BigDecimal(request.getParameter("etEquipoTecnologico"));
                    equipoTecnologicoSolicitud.setEtEquipo(etEquipoTecnologico);
                    slCodigo = new BigDecimal(request.getParameter("slSolicitud"));
                    equipoTecnologicoSolicitud.setSlSolicitud(slCodigo);
                    
                    equipoTecnologicoSolicitudBl.save(equipoTecnologicoSolicitud);
                    
                    out.print("C~El equipo tecnológico de la solicitud fue ingresado correctamente");
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
