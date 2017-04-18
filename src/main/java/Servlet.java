

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class Servlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Validator validator = new Validator(req.getParameter("q"), req.getParameter("length"), req.getParameter("limit"));
        String q = validator.validateQ();
        Integer limit = validator.validateLimit();
        Integer length = validator.validateLength();
        TextService textService = new TextService();
        ResponseObject responseObject = textService.scanParagraphsForQuery(q, length, limit);
        String json = new Gson().toJson(responseObject);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}