/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_CONTROLLER;

import PAET_BL.PAET_DF_DIRECCION_FISICA_BL;
import PAET_DOMAIN.PaetDfDireccionFisica;
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
public class DF_DIRECCION_FISICA_Servlet extends HttpServlet {

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
            BigDecimal dfCodigo, dsDistrito;
            PaetDfDireccionFisica direccionFisica = new PaetDfDireccionFisica();
            PAET_DF_DIRECCION_FISICA_BL direccionFisicaBl = new PAET_DF_DIRECCION_FISICA_BL();

            Thread.sleep(1000);

            String accion = request.getParameter("accion");

            switch (accion) {
                case "consultarDireccionesFisicas":
                    json = new Gson().toJson(direccionFisicaBl.findAll(PaetDfDireccionFisica.class.getName()));
                    out.print(json);
                    break;
                case "consultarDireccionFisicaByCodigo":
                    //se consulta el objeto por ID
                    dfCodigo = new BigDecimal(request.getParameter("dfCodigo"));
                    direccionFisica = direccionFisicaBl.findById(dfCodigo);

                    //se pasa la informacion del objeto a formato JSON
                    json = new Gson().toJson(direccionFisica);
                    out.print(json);
                    break;
                case "agregarDireccionFisica":
                case "modificarDireccionFisica":
                    
                    direccionFisica.setDfDireccion(request.getParameter("dfDireccion"));
                    direccionFisica.setDfEstado(request.getParameter("dfEstado").charAt(0));
                    dsDistrito = new BigDecimal(request.getParameter("dsDistrito"));
                    direccionFisica.setDsDistrito(dsDistrito);
                    direccionFisica.setTrTrabajador(request.getParameter("trUsuario"));
                    
                    if (accion.equals("agregarDireccionFisica")) { //es insertar
                        
                        //Se guarda el objeto
                        direccionFisicaBl.save(direccionFisica);

                        //Se imprime la respuesta con el response
                        out.print("C~La direccion física fue agregada correctamente");

                    } else {//es modificar 
                        dfCodigo = new BigDecimal(request.getParameter("dfCodigo"));
                        direccionFisica.setDfCodigo(dfCodigo);
                        //Se guarda el objeto
                        direccionFisicaBl.merge(direccionFisica);

                        //Se imprime la respuesta con el response
                        out.print("C~La dirección física fue modificada correctamente");
                    }
                    break;
                case "consultaDinamica":
                    campo = request.getParameter("campo");
                    valor = request.getParameter("valor");
                    unico = Boolean.valueOf(request.getParameter("unico"));

                    //se consulta el objeto por el campo y el valor 
                    json = new Gson().toJson(direccionFisicaBl.findDynamicFilter(campo, valor, unico, PaetDfDireccionFisica.class.getName()));
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
