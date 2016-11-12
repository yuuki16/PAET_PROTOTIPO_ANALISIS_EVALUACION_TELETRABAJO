/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_CONTROLLER;

import PAET_BL.PAET_DV_DIVISION_BL;
import PAET_DOMAIN.PaetDvDivision;
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
public class DV_DIVISION_Servlet extends HttpServlet {

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
            BigDecimal dvCodigo, grGerencia;
            PaetDvDivision division = new PaetDvDivision();
            PAET_DV_DIVISION_BL divisionBl = new PAET_DV_DIVISION_BL();
            
            String accion = request.getParameter("accion");

            switch (accion) {
                case "consultarDivisiones":
                    json = new Gson().toJson(divisionBl.findAll(PaetDvDivision.class.getName()));
                    out.print(json);
                    break;
                case "consultarDivisionByCodigo":
                    dvCodigo = new BigDecimal(request.getParameter("dvCodigo"));
                    //se consulta el objeto por ID
                    division = divisionBl.findById(dvCodigo);

                    //se pasa la informacion del objeto a formato JSON
                    json = new Gson().toJson(division);
                    out.print(json);
                    break;
                case "agregarDivision":
                case "modificarDivision":

                    grGerencia = new BigDecimal(request.getParameter("grGerencia"));
                    
                    if (accion.equals("agregarDivision")) { //es insertar
                        division.setDvDescripcion(request.getParameter("dvDescripcion"));
                        division.setDvEstado(request.getParameter("dvEstado").charAt(0));
                        division.setGrGerencia(grGerencia);
                        //Se guarda el objeto
                        divisionBl.save(division);

                        //Se imprime la respuesta con el response
                        out.print("C~La división fue agregada correctamente");

                    } else {//es modificar 
                        dvCodigo = new BigDecimal(request.getParameter("dvCodigo"));

                        division.setDvCodigo(dvCodigo);
                        division.setDvDescripcion(request.getParameter("dvDescripcion"));
                        division.setDvEstado(request.getParameter("dvEstado").charAt(0));
                        division.setGrGerencia(grGerencia);
                        //Se guarda el objeto
                        divisionBl.merge(division);

                        //Se imprime la respuesta con el response
                        out.print("C~La división fue modificada correctamente");
                    }
                    break;
                case "consultaDinamica":
                    campo = request.getParameter("campo");
                    valor = request.getParameter("valor");
                    unico = Boolean.valueOf(request.getParameter("unico"));

                    //se consulta el objeto por el campo y el valor 
                    json = new Gson().toJson(divisionBl.findDynamicFilter(campo, valor, unico, PaetDvDivision.class.getName()));
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
