package batch10.job;

import java.util.List;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Component
public class CustomReader implements ItemReader<String>, StepExecutionListener {

    private List<String> data = List.of("A", "B", "C", "D", "E");
    private int currentIndex = 0;
    private ExecutionContext executionContext;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        // ExecutionContext 초기화
        this.executionContext = stepExecution.getExecutionContext();

        // ExecutionContext에서 이전 상태 복구
        if (executionContext.containsKey("current.index")) {
            currentIndex = executionContext.getInt("current.index");
        }
    }

    @Override
    public String read() {
        if (currentIndex >= data.size()) {
            return null;
        }

        // 현재 처리 중인 데이터 저장
        executionContext.putInt("current.index", currentIndex);
        if (data.get(currentIndex).equals("C")) { // 예: C에서 실패
            executionContext.putString("failed.item", "C");
            throw new RuntimeException("Processing failed for item: " + "C");
        }
        return data.get(currentIndex++);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }
}

