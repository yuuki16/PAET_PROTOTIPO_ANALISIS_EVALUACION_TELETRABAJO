/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_CONTROLLER;

import PAET_BL.PAET_AR_AREA_BL;
import PAET_DOMAIN.PaetArArea;
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
public class AR_AREA_Servlet extends HttpServlet {

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
            Boolean unique;
            BigDecimal arCodigo, drDireccion;
            PaetArArea area = new PaetArArea();
            PAET_AR_AREA_BL areaBl = new PAET_AR_AREA_BL();

            Thread.sleep(1000);

            HttpSession session = request.getSession();
            String accion = request.getParameter("accion");

            switch (accion) {
                case "consultarAreas":
                    json = new Gson().toJson(areaBl.findAll(PaetArArea.class.getName()));
                    out.print(json);
                    break;
                case "consultarAreaByCodigo":
                    arCodigo = new BigDecimal(request.getParameter("arCodigo"));
                    //se consulta el objeto por ID
                    area = areaBl.findById(arCodigo);

                    //se pasa la informacion del objeto a formato JSON
                    json = new Gson().toJson(area);
                    out.print(json);
                    break;
                case "agregarArea":
                case "modificarArea":
                    arCodigo = new BigDecimal(request.getParameter("arCodigo"));
                    drDireccion = new BigDecimal(request.getParameter("drDireccion"));
                    
                    area.setArCodigo(arCodigo);
                    area.setArDescripcion(request.getParameter("arDescripcion"));
                    area.setArEstado(request.getParameter("arEstado").charAt(0));
                    area.setDrDireccion(drDireccion);
                    
                    if (accion.equals("agregarArea")) { //es insertar
                        //Se guarda el objeto
                        areaBl.save(area);

                        //Se imprime la respuesta con el response
                        out.print("C~El área fue agregado correctamente");

                    } else {//es modificar 
                        //Se guarda el objeto
                        areaBl.merge(area);

                        //Se imprime la respuesta con el response
                        out.print("C~El área fue modificado correctamente");
                    }
                    break;
                case "consultaDinamica":
                    campo = request.getParameter("campo");
                    valor = request.getParameter("valor");
                    unique = Boolean.valueOf(request.getParameter("unico"));
                    //se consulta el objeto por el campo y el valor 
                    json = new Gson().toJson(areaBl.findDynamicFilter(campo, valor, unique, PaetArArea.class.getName()));
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
