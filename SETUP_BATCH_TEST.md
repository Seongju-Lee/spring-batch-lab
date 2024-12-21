## Spring Batch Test 환경 setup

### 내용
해당 Repository에서 작성한 모든 테스트 코드는 아래와 같은 설정을 따른다.  
여기서는 어떤 방식으로 배치 Job 실행을 테스트하는지 설명한다. 

### 목적
각 모듈들은 같은 시나리오를 기반으로, 예시 코드를 가지고 있다.  

각 모듈에서 테스트는 IntegrationTestTemplate.class를 상속받아 구현하고 있는데, 본 README 파일에선 이 클래스 파일에 대해 설명하고자 한다.  
추가로, 테스트에서 사용하는 `JobLauncherTestUtils` 클래스에 대해 설명한다.


---


