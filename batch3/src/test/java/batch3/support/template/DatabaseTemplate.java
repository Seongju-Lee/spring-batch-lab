package batch3.support.template;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseTemplate {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void truncate() {
        truncateDatabase();
    }

    private void truncateDatabase() {
        final List<String> tableNames = new ArrayList<>();
        final List<String> tables = entityManager.createNativeQuery("SHOW TABLES").getResultList(); // MySQL
        for (String table : tables) {
            tableNames.add(table);
        }
        excludeBatchSequenceTables(tableNames);

        entityManager.createNativeQuery(String.format("SET FOREIGN_KEY_CHECKS = %d", 0)).executeUpdate();
        for (final String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
        }
        entityManager.createNativeQuery(String.format("SET FOREIGN_KEY_CHECKS = %d", 1)).executeUpdate();
    }

    private void excludeBatchSequenceTables(List<String> tableNames) {
        tableNames.removeIf(tableName ->
            tableName.equals("BATCH_STEP_EXECUTION_SEQ")||
                tableName.equals("BATCH_JOB_EXECUTION_SEQ") ||
                tableName.equals("BATCH_JOB_SEQ")
        );
    }
}
