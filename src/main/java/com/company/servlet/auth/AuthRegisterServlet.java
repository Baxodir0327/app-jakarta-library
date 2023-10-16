package com.company.servlet.auth;

import com.company.dao.AuthUserDAO;
import com.company.entity.AuthUser;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@WebServlet(name = "AuthRegisterServlet", value = "/auth/register")
public class AuthRegisterServlet extends HttpServlet {
    private final AuthUserDAO authUserDAO = new AuthUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/auth-user/register.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirm_password = req.getParameter("confirm_password");

        Optional<AuthUser> optionalAuthUser = authUserDAO.findByemail(email);
        Map<String, String> errors = new HashMap<>();
        authUserDAO.findByemail(email)
                .ifPresent(authUser -> errors.put("email_error", "Email Already Taken"));

        if (password == null) {
            errors.put("password_error", "password is invalid");
        }

        if (!Objects.equals(password,confirm_password)){
            errors.put("password_error", "password is invalid");
        }

        if (!errors.isEmpty()){
            errors.forEach(req::setAttribute);
            resp.sendRedirect("/auth/register");
        }


        AuthUser authUser = AuthUser
                .childBuilder()
                .email(email)
                .password(password)
                .build();

    }
}
