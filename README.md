## Spring Batch 101

### 내용
100% [Spring Batch 5 Docs](https://docs.spring.io/spring-batch/reference)를 기반으로 작성함.  
목차도 비슷하게 구성하되, 단계적으로 내용을 이해할 수 있도록 변경. 앞선 내용이 후속 내용을 이해하는 데 필요한 기초를 제공하는 식으로 목차 구성.


### 목적
Spring Batch 5의 사용법을 익히고, 동작 원리 제대로 이해한다.    
더 나아가 아래와 같은 내용을 파악하는데 초점을 둔다.  
- Spring Batch 사용법 101(구성방식, 작동원리(=Deep-Dive))
- Spring Batch 아키텍처
- **왜 스프링 배치가 탄생**했는가?  
- 기본적인 배치 원칙과 처리 전략


### 버전
- SpringBoot 3
- Spring Batch 5
- Java 21

### 목차
0. [배치 테스트 환경 setup]()
1. [스프링 배치 기본 구성 코드예시](batch1)
2. [스프링 배치 기본 구성 - Job(JobInstance & JobParameters & JobExecution)](batch2)
3. [Job의 구성방식 및 실행방식 - JobLauncher & Job & JobRepository](batch3)
4. [Step의 기본 구성과 생성원리, 그리고 실행원리 - StepBuilder & Step](batch4)
5. [Tasklet과 TaskletStep](batch5)
6. [Job과 Step의 흐름제어 - Flow]()
7. [Scope - Job Scope & Step Scope (w. 지연 바인딩)]() -> 2번 내용과 밀접하게 이어짐

위 내용까지가 SpringBatch의 기본적인 구성이라고 생각한다. 
기본적인 구성이지만, 가장 중요하기 때문에 디버깅을 통해 동작원리를 깊게 파악해봤다.

아래 이어 나오는 내용들은 SpringBath의 핵심 개념인 청크 지향 처리(Chunk-oriented Processing)와 관련된 내용이다. 
위 내용을 기반으로 하여, 아래 내용들을 깊게 파악하고 정리했다.  

---
8. [Chunk 지향 처리 아키텍처와 기본구성 - ItemReader & ItemWriter & ItemProcessor]()
9. [Chnck 지향 처리 1 - ItemReader]()
10. [Chnck 지향 처리 2 - ItemWriter]()
11. [Chnck 지향 처리 3 - ItemProcessor]()
12. [스프링 배치 메타 데이터 - Meta-Data Schema]()
