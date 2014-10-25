/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author samuel.arenas
 */
@MultipartConfig(maxFileSize = 161772515)
public class SubirServlet extends HttpServlet {

    private String dbURL = "jdbc:mysql://localhost:3306/archivo";

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        InputStream inputStream = null;
        Part filePart = request.getPart("foto");
        if (filePart != null) {
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            inputStream = filePart.getInputStream();
        }
        Connection conn = null;
        String message = null;

        try {
            //Conexion a BD

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(dbURL, "root", "root");
            //Construir el Stament

            String sql = "INSERT INTO contacts(first_name,last_name,photo)values(?,?,?)";
            String sql1 = "INSERT INTO contacts(first_name,last_name,photo)values(?,?,?)select *\n"
                    + "from contacts c\n"
                    + "where c.first_name = 'A';";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, nombre);
            statement.setString(2, apellido);

            if (inputStream != null) {
                statement.setBlob(3, inputStream);

            }

            //Enviar los stamentos  a la DB
            int row = statement.executeUpdate();

            if (row > 0) {
                message = "Archivo actualizado exitosamente";
            }
        } catch (SQLException e) {
            message = "Error " + e.getMessage();
            e.printStackTrace();
        } finally {
            if (conn != null) {
                //cierro la conexion
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //Colocar el mensaje en el ambito del request

            request.setAttribute("Message", message);
            // reenviar a la pagina de message
            getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SubirServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SubirServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
