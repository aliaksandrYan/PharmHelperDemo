package servlets;

import db.SchemaControl;
import entities.Pharmacy;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PharmacyServlet extends HttpServlet {
    private final SchemaControl sc;

    public PharmacyServlet(SchemaControl sc) {
        this.sc = sc;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String medicine = request.getParameter("medicine");
        String [] medicines =  medicine.split(" |,");
        List<String> meds = new ArrayList<>();
        for(String med : medicines) {
            if(med.length() != 0) {
                meds.add(med);
            }

        }
        List<Pharmacy> pharmaciesResult = sc.getPricesByList(meds);
        String json = sc.getPricesByMedicine(medicine);
        request.setAttribute("pharmaciesResult", pharmaciesResult);
        request.getRequestDispatcher("pharmacies.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
    }
}
