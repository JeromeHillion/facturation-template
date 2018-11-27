package fr.laerce.webapp;

import fr.laerce.webapp.model.Client;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.*;
import java.util.*;

public class ListeClients extends HttpServlet {
    Connection conn;
    Template listeClients;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {


        try {

            Statement req = conn.createStatement();
            String query = "SELECT clt_num, clt_nom, clt_pnom, clt_loc, clt_pays FROM clients";
            ResultSet res = req.executeQuery(query);
            List<Client> clients = new ArrayList<Client>();
            while(res.next()){
                clients.add(new Client(res.getString("clt_num"),
                        res.getString("clt_nom"),
                        res.getString("clt_pnom"),
                        res.getString("clt_loc"),
                        res.getString("clt_pays")));
            }
            Map<String,Object> datas = new HashMap<>();
            datas.put("clients",clients);
            listeClients.process(datas, httpServletResponse.getWriter());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("org.postgresql.Driver");
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "secret");
            //conn = DriverManager.getConnection("jdbc:postgresql://192.168.99.100/exemple", props);
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/exemple", props);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
        cfg.setServletContextForTemplateLoading(getServletContext(),"/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF8");
        try {
            listeClients = cfg.getTemplate("clients.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
