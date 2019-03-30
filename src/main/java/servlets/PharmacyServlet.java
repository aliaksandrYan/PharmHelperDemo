package servlets;

import db.SchemaControl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PharmacyServlet extends HttpServlet {
    private final SchemaControl sc;

    public PharmacyServlet(SchemaControl sc) {
        this.sc = sc;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String medicine = request.getParameter("medicine");
       // String [] medicine = request.getParameterValues("medicine");
       // List<String> medicines = Arrays.asList(request.getParameterValues("medicine"));
        //System.out.println(medicines);
        String json = sc.getGetPricesByMedicine(medicine);
        response.setContentType("text/html");
        response.setContentType("application/json");
        response.getWriter().println(json);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    //sign up
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
    }

 /*   //change profile
    public void doPut(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
    }
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

    }*/
}
