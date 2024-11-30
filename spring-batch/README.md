## Spring Batch 101

### 내용
100% [Spring Batch 5 Docs](https://docs.spring.io/spring-batch/reference)를 기반으로 작성함.  
목차도 비슷하게 구성하되, 단계적으로 내용을 이해할 수 있도록 변경. 앞선 내용이 후속 내용을 이해하는 데 필요한 기초를 제공하는 식으로 목차 구성.


### 목적
Spring Batch 5의 사용법을 익히고, 동작 원리 이해한다.    
더 나아가 아래와 같은 내용을 파악하는데 초점을 둔다.  
- Spring Batch 사용법 101(구성방식, 작동원리)
- Spring Batch 아키텍처
- **왜 스프링 배치가 탄생**했는가?  
- 기본적인 배치 원칙과 처리 전략


### 버전
- SpringBoot 3
- Spring Batch 5
- Java 21

### 목차
1. [스프링 배치 기본 구성 코드예시](지금 작성한거)
2. [스프링 배치 기본 구성 - Job(JobInstance & JobParameters & JobExecution)](https://docs.spring.io/spring-batch/reference/domain.html#stepexecution)
3. [Job의 구성방식 및 실행방식 - Job & JobRepository & JobLauncher 등]()
4. [Step의 구성방식 1 - Chunk 지향 처리(Chunk-oriented Processing)]()
5. [Step의 구성방식 2 - Tasklet Step]()
6. [Step의 구성방식 3 - Step Flow]()
7. [Step의 구성방식 4 - Job Scope & Step Scope (w. 지연 바인딩)](2번 내용과 밀접하게 이어짐)
8. [Chnck 지향 처리 1 - ItemReader]()
9. [Chnck 지향 처리 2 - ItemWriter]()
10. [Chnck 지향 처리 3 - ItemProcessor]()
11. [Chnck 지향 처리 3 - ItemProcessor]()
12. [스프링 배치 메타 데이터 - Meta-Data Schema]()



