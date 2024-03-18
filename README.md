# 끝말잇기 게임

📅 진행 기간 <br/>
- 2024.02.16 ~ 2024.03.01 <br/>

💻 개발환경<br>

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white"><img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"><img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"><br>
🛠️Tool🛠️<br>
<img src="https://img.shields.io/badge/eclipse-2C2255?style=for-the-badge&logo=eclipseide&logoColor=white">

🎯 목적
- 혼자서 즐기는 게임이 아닌 다수의 유저와 함께 즐길수 있는 간단한 끝말잇기 게임
  
📝 학습목표
- RDBMS의 이해 및 활용
- 사용자 입력값 유효성 검사
- 소켓 서버를 이용하여 다수의 클라이언트 정보 제공

📚 담당업무
- 소켓 서버 구축
- 클라이언트GUI에서 이벤트 발생시 서버에서 수행할 로직 설계
- HTTP API 서버와 연결
- DB, HTTP API를 이용하여 클라이언트에서 전송하는 단어 유효성 확인
- 싱글, 멀티 2가지 모드 제공
- 멀티모드에서 다수의 클라이언트에게 동일한 정보 제공

⭐ 주요 기능
- 여러 사람이 참여하여 진행할 수 있는 끝말잇기 게임
- 표준국어대사전 API를 이용하여 작성한 단어의 유효성 확인

💥이슈사항 및 해결</br>
- 유효하지 않은 단어를 입력을 하였을 때 에러가 발생함</br>
  - HttpsURLConnection을 사용하였기 때문에 HTTP 프로토콜중 유효한 단어와 존재하지 않는 단어를 입력 하였을때 반환되는 값 중 식별이 가능한 부분을 확인 하여 분류를 하였다.</br>

- 멀티모드에서 동일한 방에 있는 다수의 클라이언트에게 동일한 정보제공</br>    
  - 원인 : 다수의 클라이언트의 입력을 하였을때 서버에서 로직이 수행이 되지 않음 </br>
  - 해결 : ReentrantReadWriteLock를 사용하여 클라이언트가 서버에 접속한 순서를 정하여 우선 순위기반으로 작업을 처리 하였다. 

- 멀티모드에서 다수의 유저의 순서를 정하는 방법
  - 원인 : 클라이언트별 유저의 1번,2번,3번 유저의 순서가 일치가 되지 않음<br> ex)1번클라이언트의 순서 A,B,C/ 2번 클라이언트의 순서 B,A,C/ 3번 클라이언트의 순서 C,A,B
  - 해결 : 멀티모드의 진행방식을 1->2->3번의 순서대로가 아닌 가장 빨리 끝말잇기를 입력한 유저가 살아남는 서바이벌 방식을 채택


