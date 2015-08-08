package devFun.skyfly33.common.config;

import devFun.skyfly33.common.utils.Config;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by donghoon on 15. 8. 8..
 *
 * database, transaction, ,DAO, service management config.
 */

@MapperScan("devFun.skyfly33.common.mapper")
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

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSourceForMysql());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceForMysql());
        return sessionFactory.getObject();
    }


}
