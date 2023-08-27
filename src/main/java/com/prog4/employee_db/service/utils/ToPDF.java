package com.prog4.employee_db.service.utils;

import com.prog4.employee_db.controller.model.ModelEmployee;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import static org.thymeleaf.templatemode.TemplateMode.HTML;
@Component
@AllArgsConstructor
public class ToPDF {

    public String parseEmployeeInfoTemplate(
            ModelEmployee employee,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/employee/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.addTemplateResolver(templateResolver);

        WebContext context = createContext(servletRequest, servletResponse);
        context.setVariable("employee", employee);
        return templateEngine.process("profiles_toPdf", context);
    }
    public static WebContext createContext(HttpServletRequest req, HttpServletResponse res) {
        var application = JakartaServletWebApplication.buildApplication(req.getServletContext());
        var exchange =application.buildExchange(req, res);
        return new WebContext(exchange);
    }
}
