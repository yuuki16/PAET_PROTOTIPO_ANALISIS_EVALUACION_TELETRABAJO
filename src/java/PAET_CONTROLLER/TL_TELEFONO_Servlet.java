/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_CONTROLLER;

import PAET_BL.PAET_TL_TELEFONO_BL;
import PAET_DOMAIN.PaetTlTelefono;
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
public class TL_TELEFONO_Servlet extends HttpServlet {

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
            Boolean unico;
            BigDecimal tlCodigo;
            PaetTlTelefono telefono = new PaetTlTelefono();
            PAET_TL_TELEFONO_BL telefonoBl = new PAET_TL_TELEFONO_BL();

            Thread.sleep(1000);

            String accion = request.getParameter("accion");

            switch (accion) {
                case "consultarTelefonos":
                    json = new Gson().toJson(telefonoBl.findAll(PaetTlTelefono.class.getName()));
                    out.print(json);
                    break;
                case "consultarTelefonoByCodigo":
                    //se consulta el objeto por ID
                    tlCodigo = new BigDecimal(request.getParameter("tlCodigo"));
                    telefono = telefonoBl.findById(tlCodigo);

                    //se pasa la informacion del objeto a formato JSON
                    json = new Gson().toJson(telefono);
                    out.print(json);
                    break;
                case "agregarTelefono":
                case "modificarTelefono":
                    
                    telefono.setTlDescripcion(request.getParameter("tlDescripcion"));
                    telefono.setTlEstado(request.getParameter("tlEstado").charAt(0));
                    telefono.setTlTelefono(request.getParameter("tlTelefono"));
                    telefono.setTrTrabajador(request.getParameter("trTrabajador"));
                    
                    if (accion.equals("agregarTelefono")) { //es insertar
                        
                        //Se guarda el objeto
                        telefonoBl.save(telefono);

                        //Se imprime la respuesta con el response
                        out.print("C~El teléfono fue agregado correctamente");

                    } else {//es modificar 
                        tlCodigo = new BigDecimal(request.getParameter("tlCodigo"));
                        telefono.setTlCodigo(tlCodigo);
                        //Se guarda el objeto
                        telefonoBl.merge(telefono);

                        //Se imprime la respuesta con el response
                        out.print("C~El teléfono fue modificado correctamente");
                    }
                    break;
                case "consultaDinamica":
                    campo = request.getParameter("campo");
                    valor = request.getParameter("valor");
                    unico = Boolean.valueOf(request.getParameter("unico"));

                    //se consulta el objeto por el campo y el valor 
                    json = new Gson().toJson(telefonoBl.findDynamicFilter(campo, valor, unico, PaetTlTelefono.class.getName()));
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
