# 🎲 끝말잇기 게임 프로젝트

## 🗓️ 프로젝트 기간
- **2024.02.16** ~ **2024.03.01**

## 🛠️ 개발 환경 및 도구
![Java](https://img.shields.io/badge/java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white)
![GitHub](https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white)
![Eclipse](https://img.shields.io/badge/eclipse-2C2255?style=for-the-badge&logo=eclipseide&logoColor=white)

## 🎯 프로젝트 소개
- 주어진 시간 내에 단어를 작성하여 끝말잇기 게임을 즐길 수 있는 프로젝트입니다.
- 여러 유저들과 함께 즐기는 간단한 끝말잇기 게임.
- 유저 대 CPU로 진행되는 싱글 모드도 지원합니다.

## 📊 플로우차트
<img src="https://github.com/Leegipyo/Concluding-remarks/assets/154950251/a606c0a6-d22c-4265-91ae-ce8a07edc8bc" width="800" height="1000"/>

## 📝 학습 목표
- 관계형 데이터베이스 관리 시스템(RDBMS)의 이해와 활용
- 사용자 입력값의 유효성을 검사하는 방법 습득
- 다수의 클라이언트 정보를 제공하기 위해 소켓 서버 활용 방법 이해

## 💼 업무 내용 및 책임
- 소켓 서버 구축 및 관리
- 클라이언트 GUI에서 발생하는 이벤트에 대한 클라이언트 – 서버 간 프로토콜 정의 및 구현
- 외부 웹 API를 이용한 단어의 유효성 검토
- 클라이언트가 전송한 단어의 유효성을 국립국어원의 표준국어대사전 API를 활용하여 구현
- 멀티모드 : 끝말잇기 단어를 가장 빨리 입력한 유저가 살아남는 서바이벌 방식
- 싱글모드 : 사용자의 끝말을 확인하여 DB에서 끝말로 시작하는 단어를 랜덤으로 출력

## 🎮 핵심 기능
- 다수 참여 가능한 끝말잇기 게임 기능 제공
- 국립국어원의 표준국어대사전 API를 활용하여 단어의 유효성 검사
- 싱글 모드 진행 시, 사용자가 입력한 단어의 끝말을 확인하고 해당하는 시작 단어를 DB에서 랜덤으로 출력

## 👨🏻‍💻 기능 구현 이미지
### 1. 멀티모드 끝말잇기 진행
<img src="https://github.com/Leegipyo/Concluding-remarks/assets/154950251/1c1c611c-5aa9-4973-b7f8-bcd78a52b032" width="800" height="400"/>
<img src="https://github.com/Leegipyo/Concluding-remarks/assets/154950251/25645448-5f86-4e84-bbdf-acbf7ae0fbca" width="800" height="400"/>

### 2. 멀티모드 끝말잇기 실패
<img src="https://github.com/Leegipyo/Concluding-remarks/assets/154950251/8fb5e55f-50e5-4935-80ec-e3e51f5d5280" width="400" height="400"/>

### 3. 한글자 사용 불가
<img src="https://github.com/Leegipyo/Concluding-remarks/assets/154950251/b8dbb120-ecef-4ada-9e99-58a9db10ac3d" width="400" height="400"/>

## 💥 이슈사항 및 해결
- 국립국어원에 단어 수록 여부를 검색 시 결과가 없는 경우에도, 모든 HTTP 상태 코드가 200 응답.
  - 해결: API 문서 상에는 사용법이 기록되어 있지 않으나, Response Header를 분석하여 Content-Length의 값이 -1일 경우 존재하는 단어, 0일 경우 존재하지 않은 단어임을 확인하고 활용함

- 멀티모드에서 동일한 방에 있는 다수의 클라이언트에게 동기화된 상태 정보 제공
  - 클라이언트가 서버에 전송한 명령어를 순서대로 처리하기 위해 ReentrantReadWriteLock를 사용, 로직을 실행하며 작업을 처리

## 🛑 개선할 사항
- 두음 법칙 적용
  - 두음 법칙에는 다양한 규칙이 존재하는데 모든 규칙을 구현하기 위해서는 로직을 구상을 해보았으나 생각보다 소요 시간이 많았으며 핵심 기능이 아니기에, 추후 개선 사항으로 남겨두었음
