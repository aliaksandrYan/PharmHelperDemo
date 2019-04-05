package servlets;

import db.SchemaControl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FeedbackServlet extends HttpServlet {
    private final SchemaControl sc;

    public FeedbackServlet(SchemaControl sc) {
        this.sc = sc;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
    }

    //sign up
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String country = request.getParameter("country");
        String message = request.getParameter("message");
        if(name != null && surname != null && country != null && message != null){
            sc.insertNewFeedback(name,surname,country,message);
            request.getRequestDispatcher("index.html").forward(request, response);
        }
        else{

        }

    }

 /*   //change profile
    public void doPut(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
    }
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

    }*/
}
