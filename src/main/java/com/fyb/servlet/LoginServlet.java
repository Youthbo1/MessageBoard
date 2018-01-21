package com.fyb.servlet;


import com.fyb.bean.User;
import com.fyb.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录Servlet
 *
 * @version 1.0
 */
@WebServlet(urlPatterns = "/main.do" )
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.login(username, password);
        if (null != user) {
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("/message/list.do").forward(request, response);
        } else {
            request.getRequestDispatcher("/login.do").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
