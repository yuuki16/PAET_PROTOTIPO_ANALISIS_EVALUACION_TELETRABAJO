/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_CONTROLLER;

import PAET_BL.PAET_ET_EQUIPO_TECNOLOGICO_BL;
import PAET_DOMAIN.PaetEtEquipoTecnologico;
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
public class ET_EQUIPO_TECNOLOGICO_Servlet extends HttpServlet {

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
            BigDecimal etCodigo;
            Boolean unico;
            PaetEtEquipoTecnologico equipoTecnologico = new PaetEtEquipoTecnologico();
            PAET_ET_EQUIPO_TECNOLOGICO_BL equipoTecnologicoBl = new PAET_ET_EQUIPO_TECNOLOGICO_BL();

            Thread.sleep(1000);

            HttpSession session = request.getSession();
            String accion = request.getParameter("accion");

            switch (accion) {
                case "consultarEquipoTecnologico":
                    json = new Gson().toJson(equipoTecnologicoBl.findAll(PaetEtEquipoTecnologico.class.getName()));
                    out.print(json);
                    break;
                case "consultarEquipoTecnologicoByCodigo":
                    etCodigo = new BigDecimal(request.getParameter("etCodigo"));
                    //se consulta el objeto por ID
                    equipoTecnologico = equipoTecnologicoBl.findById(etCodigo);

                    //se pasa la informacion del objeto a formato JSON
                    json = new Gson().toJson(equipoTecnologico);
                    out.print(json);
                    break;
                case "agregarEquipoTecnologico":
                case "modificarEquipoTecnologico":

                    if (accion.equals("agregarEquipoTecnologico")) { //es insertar
                        equipoTecnologico.setEtDescripcion(request.getParameter("etDescripcion"));
                        equipoTecnologico.setEtEstado(request.getParameter("etEstado").charAt(0));
                        //Se guarda el objeto
                        equipoTecnologicoBl.save(equipoTecnologico);

                        //Se imprime la respuesta con el response
                        out.print("C~El equipo tecnológico fue agregado correctamente");

                    } else {//es modificar 
                        etCodigo = new BigDecimal(request.getParameter("etCodigo"));

                        equipoTecnologico.setEtCodigo(etCodigo);
                        equipoTecnologico.setEtDescripcion(request.getParameter("etDescripcion"));
                        equipoTecnologico.setEtEstado(request.getParameter("etEstado").charAt(0));
                        //Se guarda el objeto
                        equipoTecnologicoBl.merge(equipoTecnologico);

                        //Se imprime la respuesta con el response
                        out.print("C~El equipo tecnológico modificado correctamente");
                    }
                    break;
                case "consultaDinamica":
                    campo = request.getParameter("campo");
                    valor = request.getParameter("valor");
                    unico = Boolean.valueOf(request.getParameter("unico"));
                    //se consulta el objeto por el campo y el valor 
                    json = new Gson().toJson(equipoTecnologicoBl.findDynamicFilter(campo, valor, unico, PaetEtEquipoTecnologico.class.getName()));
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
