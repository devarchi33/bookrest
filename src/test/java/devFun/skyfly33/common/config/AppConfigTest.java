package devFun.skyfly33.common.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by donghoon on 15. 8. 8..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class AppConfigTest {

    @Autowired
    private DataSource dataSourceForMysql;

    @Test
    public void testDBCPDataSource() {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = dataSourceForMysql.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select creator, title from book");
            while (rs.next()) {
                System.out.println("Book Author = " + rs.getString("creator") + ", Title = " + rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
