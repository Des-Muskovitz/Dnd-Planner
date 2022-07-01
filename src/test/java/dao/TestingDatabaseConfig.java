package dao;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Objects;

public class TestingDatabaseConfig {
    private static final String DB_HOST = "localhost";
    private static final String DB_NAME = "Testing_Files";
    private static final String DB_PORT = "5432";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres1";

    private SingleConnectionDataSource adminDataSource;
    private JdbcTemplate adminJdbcTemplate;

    @PostConstruct
    public void setup(){
        if(System.getenv("DB_HOST") == null){
            adminDataSource = new SingleConnectionDataSource();
            adminDataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
            adminDataSource.setUsername("postgres");
            adminDataSource.setPassword("postgres1");
            adminJdbcTemplate = new JdbcTemplate(adminDataSource);
            adminJdbcTemplate.update("DROP DATABASE IF EXISTS \"" + DB_NAME + "\";");
            adminJdbcTemplate.update("CREATE DATABASE \"" + DB_NAME + "\";");
        }
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
        dataSource.setUrl(String.format("jdbc:postgresql://%s:%s/%s", DB_HOST, DB_PORT, DB_NAME));
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PASSWORD);

        /* The following line disables autocommit for connections
         * returned by this DataSource. This allows us to rollback
         * any changes after each test */
        dataSource.setAutoCommit(false);

        // Spring provides a convenience class called ScriptUtils for running external SQL scripts.
        // You'll find the test-data.sql script file in the test/resources folder.
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("test-data.sql"));

        return dataSource;
    }

    @PreDestroy
    public void cleanup() {
        if (adminDataSource != null) {
            adminJdbcTemplate.update("DROP DATABASE \"" + DB_NAME + "\";");
            adminDataSource.destroy();
        }
    }

}
