## Spring Batch 101

### 내용
[Spring Batch 5 Docs](https://docs.spring.io/spring-batch/reference)와 디버깅을 기반으로 Deep-Dive   
각 모듈 README에 디버깅 내용과 동작 원리 등을 정리 함   


### 목적
Spring Batch 5의 사용법을 익히고, 동작 원리 제대로 이해한다.    
더 나아가 아래와 같은 내용을 파악하는데 초점을 둔다.  
- Spring Batch 사용법 101(구성방식, 작동원리(=Deep-Dive))
- Spring Batch 아키텍처
- 기본적인 배치 원칙과 처리 전략


### 버전
- SpringBoot 3
- Spring Batch 5
- Java 21

### 목차
1. [스프링 배치 기본 구성 코드예시](batch1)
2. [스프링 배치 기본 구성 - Job(JobInstance & JobParameters & JobExecution)](batch2)
3. [Job의 구성방식 및 실행방식 - JobLauncher & Job & JobRepository](batch3)
4. [Step의 기본 구성과 생성원리, 그리고 실행원리 - StepBuilder & Step](batch4)
5. [Tasklet과 TaskletStep](batch5)
7. [Scope - Job Scope & Step Scope (w. 지연 바인딩)](batch7)
8. [Chunk 지향 처리 방식의 기본구성과 아키텍처](batch8)
9. [Chnck 지향 처리 1 - ItemReader](batch9)
10. [스프링 배치 메타 데이터 - Meta-Data Schema](batch10)
