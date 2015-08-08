package devFun.skyfly33.common.config;

import devFun.skyfly33.common.utils.Config;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Created by donghoon on 15. 8. 8..
 * <p>
 * database, transaction, ,DAO, service management config.
 */

@Configuration
public class AppConfig {

//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .setName("testdb")
//                .setType(EmbeddedDatabaseType.HSQL)
//                .build();
//    }

    @Bean(destroyMethod = "close")
    public DataSource dataSourceForMysql() {
        BasicDataSource dataSource = new BasicDataSource();

        if ("mysql".equals(Config.getInstance().getProperties("db.type"))) {
            dataSource.setDriverClassName(Config.getInstance().getProperties("mysql.driver"));
            dataSource.setUrl(Config.getInstance().getProperties("mysql.url"));
            dataSource.setUsername(Config.getInstance().getProperties("mysql.user"));
            dataSource.setPassword(Config.getInstance().getProperties("mysql.pass"));
        } else if ("oracle".equals(Config.getInstance().getProperties("db.type"))) {
            dataSource.setDriverClassName(Config.getInstance().getProperties(".oracle.driver"));
            dataSource.setUrl(Config.getInstance().getProperties("oracle.url"));
            dataSource.setUsername(Config.getInstance().getProperties("oracle.user"));
            dataSource.setPassword(Config.getInstance().getProperties("oracle.pass"));
        } else {
            return null;
        }

        return dataSource;
    }
}
