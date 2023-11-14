package Servlets;

import Beans.trabajadores;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        trabajadores trabajadoresLogged = (trabajadores) httpSession.getAttribute("usuarioLogueado");

        if(trabajadoresLogged != null && trabajadoresLogged.getDni() != null ){
            if(request.getParameter("a") != null){
                httpSession.invalidate();;
            }
            response.sendRedirect(request.getContextPath());
        }else{
            request.getRequestDispatcher("loginForm.jsp").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usarname = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("username: " +usarname + "| password: " + password);

    }
}
