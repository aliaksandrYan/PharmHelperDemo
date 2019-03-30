package servlets;

import accounts.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        if (accountService.getUserByLogin(login) != null) {
            if (accountService.getUserByLogin(login).getPass().equals(pass)) {
                accountService.addSession(req.getRequestedSessionId(),accountService.getUserByLogin(login));
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("Authorized: " + login);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("Authorized: wrong password!");
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
