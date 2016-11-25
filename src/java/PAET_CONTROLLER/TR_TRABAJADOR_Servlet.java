/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_CONTROLLER;

import PAET_BL.PAET_TR_TRABAJADOR_BL;
import PAET_DOMAIN.PaetTrTrabajador;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michelle
 */
public class TR_TRABAJADOR_Servlet extends HttpServlet {

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
            String json, campo, valor, fechaIngreso, fechaEntrada, trJefatura, trApellido2;
            Boolean unico;
            DateFormat format;
            Date date;
            PaetTrTrabajador trabajador = new PaetTrTrabajador();
            PAET_TR_TRABAJADOR_BL trabajadorBl = new PAET_TR_TRABAJADOR_BL();
            
            String accion = request.getParameter("accion");

            switch (accion) {
                case "consultarTrabajadores":
                    json = new Gson().toJson(trabajadorBl.findAll(PaetTrTrabajador.class.getName()));
                    out.print(json);
                    break;
                case "consultarTrabajadorByCodigo":
                    //se consulta el objeto por ID
                    trabajador = trabajadorBl.findById(request.getParameter("trUsuario"));

                    //se pasa la informacion del objeto a formato JSON
                    json = new Gson().toJson(trabajador);
                    out.print(json);
                    break;
                case "agregarTrabajador":
                case "modificarTrabajador":
                    
                    trApellido2 = request.getParameter("trApellido2");
                    trJefatura = request.getParameter("trJefatura");
                    
                    trabajador.setTrUsuario(request.getParameter("trUsuario"));
                    trabajador.setTrCedula(request.getParameter("trCedula"));
                    trabajador.setTrNombre(request.getParameter("trNombre"));
                    trabajador.setTrApellido1(request.getParameter("trApellido1"));
                    if (!"".equals(trApellido2.trim())) {
                        trabajador.setTrApellido2(trApellido2);
                    }
                    trabajador.setTrSexo(request.getParameter("trSexo"));
                    fechaIngreso = request.getParameter("trFechaIngreso");
                    format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                    date = format.parse(fechaIngreso);
                    trabajador.setTrFechaIngreso(date);
                    trabajador.setTrEstado(request.getParameter("trEstado").charAt(0));
                    if (!"".equals(trJefatura.trim())) {
                        trabajador.setTrJefatura(trJefatura);
                    }
                    trabajador.setPtPuesto(request.getParameter("ptPuesto"));
                    fechaEntrada = request.getParameter("trFechaEntrada");
                    format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                    date = format.parse(fechaEntrada);
                    trabajador.setTrPtFechaEntrada(date);
                  
                    if (accion.equals("agregarTrabajador")) { //es insertar
                        
                        //Se guarda el objeto
                        trabajadorBl.save(trabajador);

                        //Se imprime la respuesta con el response
                        out.print("C~El trabajador fue agregado correctamente");

                    } else {//es modificar 
                        //Se guarda el objeto
                        trabajadorBl.merge(trabajador);

                        //Se imprime la respuesta con el response
                        out.print("C~El trabajador fue modificado correctamente");
                    }
                    break;
                case "consultaDinamica":
                    campo = request.getParameter("campo");
                    valor = request.getParameter("valor");
                    unico = Boolean.valueOf(request.getParameter("unico"));

                    //se consulta el objeto por el campo y el valor 
                    json = new Gson().toJson(trabajadorBl.findDynamicFilter(campo, valor, unico, PaetTrTrabajador.class.getName()));
                    out.print(json);
                    break;
                default:
                    out.print("E~No se indicó la acción que se desea realizar");
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
