package batch7.support.template;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

@ActiveProfiles("test")
public class TestTemplate {

    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "1234";
    private static final String DB_NAME = "test-spring-batch";
    private static final int DB_PORT = 3306;

    static final MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:8.0")
        .withUsername(DB_USERNAME)
        .withPassword(DB_PASSWORD)
        .withDatabaseName(DB_NAME)
        .withExposedPorts(DB_PORT);

    static {
        Startables.deepStart(mySQLContainer)
            .join();
    }

    @BeforeAll
    static void beforeAll() {
        setProperties();
    }

    //region Private Methods
    private static void setProperties() {
        System.setProperty("spring.datasource.url", mySQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", DB_USERNAME);
        System.setProperty("spring.datasource.password", DB_PASSWORD);
        System.setProperty("spring.jpa.hibernate.ddl-auto", "update");
    }

}
