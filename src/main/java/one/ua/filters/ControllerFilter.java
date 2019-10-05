package one.ua.filters;

import one.ua.entity.User;
import one.ua.services.configManager.ConfigManager;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/java/one.ua.controller")
public class ControllerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        httpRequest.setCharacterEncoding("UTF-8");
        User user = (User) httpRequest.getSession().getAttribute("user");
        String command = httpRequest.getParameter("command");

        if (user == null && (!command.equals("login") && !command.equals("register")&& !command.equals("changeLanguage"))) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher(ConfigManager.getProperty("login"));
            dispatcher.forward(request, response);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
