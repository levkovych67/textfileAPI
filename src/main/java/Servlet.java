

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class Servlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String q = req.getParameter("q");
        Integer limit = Integer.valueOf(req.getParameter("limit"));
        Integer length = Integer.valueOf(req.getParameter("length"));

        TextService textService = new TextService();
        ResponseObject responseObject = new ResponseObject();

        responseObject.setText(textService.scanLinesForQuery(q,length,limit));


        String json = new Gson().toJson(responseObject);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);


    }
}