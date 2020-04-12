package org.ekang.webboard;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.*;

@Controller
public class MainController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/")
    public String Main() {
        return "index";
    }

    @RequestMapping("/dbtest")
    public String dbTest(Model model) {
        Connection conn = null;
        Statement st = null;

        try {
            conn = dataSource.getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT firstname FROM users where userid=1");

            while(rs.next()) {
                model.addAttribute("username", rs.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "index";
    }
}
