package com.company.servlet.auth;

import com.company.dao.AuthUserDAO;
import com.company.entity.AuthUser;
import com.company.utils.PasswordUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "AuthLoginServlet", value = "/auth/login")
public class AuthLoginServlet extends HttpServlet {
    private final AuthUserDAO authUserDAO = new AuthUserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("next", request.getParameter("next"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/authuser/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        authUserDAO.findByEmail(email).ifPresentOrElse(authUser -> {
            if (!PasswordUtils.check(password, authUser.getPassword())) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/authuser/login.jsp");
                request.setAttribute("error_message", "Bad Credentials");
                try {
                    dispatcher.forward(request, response);
                } catch (ServletException | IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (!authUser.getStatus().equals(AuthUser.Status.ACTIVE)) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/authuser/login.jsp");
                request.setAttribute("error_message", "User not active");
                try {
                    dispatcher.forward(request, response);
                } catch (ServletException | IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                String rememberMe = Objects.requireNonNullElse(request.getParameter("rememberMe"), "off");
                HttpSession session = request.getSession();
                session.setAttribute("email", authUser.getEmail());
                session.setAttribute("role", authUser.getRole());
                session.setAttribute("id", authUser.getId());
                if (rememberMe.equals("on")) {
                    Cookie cookie = new Cookie("rememberMe", authUser.getId());
                    cookie.setMaxAge(10 * 24 * 60 * 60);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
                try {
                    String next = request.getParameter("next");
                    next = next == null || next.length() == 0 ? "/book/list" : next;
                    System.out.println("next = " + next);
                    response.sendRedirect(next);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, () -> {
            try {
                request.setAttribute("error_message", "Bad Credentials");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/authuser/login.jsp");
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
