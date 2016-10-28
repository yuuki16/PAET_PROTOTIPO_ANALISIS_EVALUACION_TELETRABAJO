/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_CONTROLLER;

import PAET_BL.PAET_PT_PUESTO_BL;
import PAET_DOMAIN.PaetPtPuesto;
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
public class PT_PUESTO_Servlet extends HttpServlet {

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
            String json, campo, valor, arAreaStr, drDireccionStr, dvDivisionStr, grGerenciaStr;
            Boolean unico;
            BigDecimal arArea, drDireccion, dvDivision, grGerencia;
            PaetPtPuesto puesto = new PaetPtPuesto();
            PAET_PT_PUESTO_BL puestoBl = new PAET_PT_PUESTO_BL();

            Thread.sleep(1000);

            HttpSession session = request.getSession();
            String accion = request.getParameter("accion");

            switch (accion) {
                case "consultarPuestos":
                    json = new Gson().toJson(puestoBl.findAll(PaetPtPuesto.class.getName()));
                    out.print(json);
                    break;
                case "consultarPuestoByCodigo":
                    //se consulta el objeto por ID
                    puesto = puestoBl.findById(request.getParameter("ptCodigo"));

                    //se pasa la informacion del objeto a formato JSON
                    json = new Gson().toJson(puesto);
                    out.print(json);
                    break;
                case "agregarPuesto":
                case "modificarPuesto":
                    arAreaStr = request.getParameter("arArea");
                    drDireccionStr = request.getParameter("drDireccion");
                    dvDivisionStr = request.getParameter("dvDivision");
                    grGerenciaStr = request.getParameter("grGerencia");
                    
                    if (!"".equals(arAreaStr.trim())) {
                        arArea = new BigDecimal(request.getParameter("arArea"));
                        puesto.setArArea(arArea);
                    }
                    if (!"".equals(drDireccionStr.trim())) {
                        drDireccion = new BigDecimal(request.getParameter("drDireccion"));
                        puesto.setDrDireccion(drDireccion);
                    }
                    if (!"".equals(dvDivisionStr.trim())) {
                        dvDivision = new BigDecimal(request.getParameter("dvDivision"));
                        puesto.setDvDivision(dvDivision);
                    }
                    if (!"".equals(grGerenciaStr.trim())) {
                        grGerencia = new BigDecimal(request.getParameter("grGerencia"));
                        puesto.setGrGerencia(grGerencia);
                    }
                    
                    puesto.setPtCodigo(request.getParameter("ptCodigo"));
                    puesto.setPtDescripcion(request.getParameter("ptDescripcion"));
                    puesto.setPtEstado(request.getParameter("ptEstado").charAt(0));
                    
                    if (accion.equals("agregarPuesto")) { //es insertar
                        
                        //Se guarda el objeto
                        puestoBl.save(puesto);

                        //Se imprime la respuesta con el response
                        out.print("C~El puesto fue agregado correctamente");

                    } else {//es modificar 
                        //Se guarda el objeto
                        puestoBl.merge(puesto);

                        //Se imprime la respuesta con el response
                        out.print("C~El puesto fue modificado correctamente");
                    }
                    break;
                case "consultaDinamica":
                    campo = request.getParameter("campo");
                    valor = request.getParameter("valor");
                    unico = Boolean.valueOf(request.getParameter("unico"));

                    //se consulta el objeto por el campo y el valor 
                    json = new Gson().toJson(puestoBl.findDynamicFilter(campo, valor, unico, PaetPtPuesto.class.getName()));
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
