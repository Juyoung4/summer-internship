### 1일차
#### 환경 세팅
1. 이클립스 설치하기
2. maven, mysql 설치하기
3. spring (market에서 설치)
4. tomcat 설치하기

#### maven 연동 하기
[windows] -> [preferences] -> [Maven] -> [User settings]에서
global setting과 user setting해주기 (내가 다운받은 maven위치에 있는 settings.xml 위치로 설정)
 
 *참고로 settings.xml에서 내 local repository 지정해주고 위 연동하기!
 
 #### spring mvc 프로젝트 생성하기
 [FILE] ->[NEW] -> [Others] -> spring검색 + spring legacy project -> 직접 입력
 
 #### 톰캣 서버 연동하기
 [windows] -> [show view] -> [others] -> server검색 -> apache에 tomcat 버전 선택 -> browser해서 톰캣 위치 찾기
 
 #### 간단한 웹 페이지 구성 (home.jsp)
 
 ### 2일차
 #### mysql, mabatis pom.xml에 추가 및 mysql에서 디비 조회, insert 연습
 
 #### 간단한 signup과 login view페이지 만들고 DB에서 LIST로 데이터 조회 하고 새로운 가입자 DB에 삽입하기
 
 ### 3일차 (feature3)
 #### Spring security
 #### 처음부터 다시 mvc프로젝트 생성해서 시작함
 #### spring security구조와 security에서 제공해주는 login가지고 커스터마이징
  + role별로 접속할 수 있는 페이지 설정
  + 로그아웃
  + 로그인 실패 시 에러문구
  + 접속 제한
  
  
### 4일차 (feature 4)
#### svn 설치(market place에서) + svn 프로젝트 다운로드
#### svn 프로젝트 코드 분석 [mvc패턴 원리 적용 , tld 이해]
#### board게시판 말고 나만의 페이지 생성해서 추가하기[1,2,3,4,5] 각각 자신에 번호에 해당되는 페이지에 만들기
