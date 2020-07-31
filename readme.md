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
 1. mysql, mabatis pom.xml에 추가 및 mysql에서 디비 조회, insert 연습
 
 2. 간단한 signup과 login view페이지 만들고 DB에서 LIST로 데이터 조회 하고 새로운 가입자 DB에 삽입하기
 
 ### 3일차 (feature3)
 1. Spring security<br>
 2. 처음부터 다시 mvc프로젝트 생성해서 시작함<br>
 3. spring security구조와 security에서 제공해주는 login가지고 커스터마이징<br>
  + role별로 접속할 수 있는 페이지 설정
  + 로그아웃
  + 로그인 실패 시 에러문구
  + 접속 제한
  
  
### 4일차 (feature 4)
 1. svn 설치(market place에서) + svn 프로젝트 다운로드<br>
 2. svn 프로젝트 코드 분석 [mvc패턴 원리 적용 , tld 이해]<br>
 3. 먼저 이 프로젝트를 위한 tomcat서버 설정하기 -> new server해서 tomcat설정[이름은 ETS]<br>
 4. 그다음 maven install 하기<br>
 5. board게시판 말고 나만의 페이지 생성해서 추가하기[1,2,3,4,5] 각각 자신에 번호에 해당되는 페이지에 만들기


### 5일차 (feature 5)
 1. spring mvc패턴 view -> controller -> service -> mapper -> mapper.xml 원리 이해
 2. board게시판에 검색 엔진 구현 및 alert 구현
 
### 6일차 
 1. 새로운 프로젝트에서 먼저 역할을 나누고, 어떻개 구상할 것인지 BOARD예제에서 테스트
 
### 7일차 (feature 7)
 1. 내가 맡은 역할: 결제 관리 시스템 카테고리 + 소분류 (이용권 관리, 레퍼럴 관리, 이용권 결제 리스트)
 2. 먼저 필요한 DB 설계(3개의 TABLE과 각각 attribute 지정하고 생성하기)

### 8일차 (feature 8)
 1. 이용권 관리 페이지에서 이용권 관리 리스트 목록 불러오기
 2. 이용권 설정 modal  설정해서 이벤트 등록하기
 
 ### 9일차 (feature 8)
 1. 이용권 등록하는 페이지 작성하고 Insert 하기
 2. 이용권 삭제하는 작업 하기
 
 ### 10일차 (feature 10)
 1. 이용권 삭제 작업 완료
 2. 이벤트 table을 새로 만들어서 이용권 속성값 event를 FK로 두기
 3. INSERT에서 아무것도 입력안하면 오류창 띄우기
 
 ### 11일차 (feature 11)
  1. 이용권 등록시 1일, 7일, 30일, 365일 버튼 누르면 text창에 자동으로 값 증가 , RESET누르면 초기화
  2. 모달창에 EVENT TABLE 리스트 조회해서 SELECT BOX안에 리스트 바인딩하기
  3. main화면에 EXCHANGE TABLE리스트 조회해서 SELECT BOX안에 리스트 바인딩하기
  
 ### 12일차 (featur12)
  1. 이용권-이벤트 등록 팝업창 (아래처럼 띄우기 -> 내가 클릭한 이벤트-이용권 정보여야함)
    * 이용권 명	 :    365days권
    * 적용 기간	 :    365일
    * 가격	 :    500000원
    * 이벤트	 :    FALL 20% Discount
  2. 이용권-등록 팝업창에서 저장 버튼 누르면 이용권table에서 status를 0->1로 update하기
  3. 이용권-등록 status가 1이면 등록버튼이 -> 해제 버튼으로 바뀜
  4. 해제 버튼 누르면 팝업창 띄우기 (아래처럼 -> 내가 클릭한 이용권 정보여야함)
     365days권을 해제 하시겠습니까?
  5. 확인 누르면 이용권table에서 status를 1->0으로 update하기
  6. status가0이면 해제버튼->등록버튼으로 바뀌어 있음
  
 ### 13일차 (feature13)
  1. 이벤트 관리와 이벤트 등록 페이지를 위한 event DB 다시 수정해서 설계
  2. 먼저 이벤트 관리 페이지를 메뉴창안에 넣고 가리키게함
  3. 이벤트 관리 페이지 하단에 이벤트 정보 리스트를 겨조아서 VIEW함
  4. 이벤트 관리 페이지 상단에 이벤트 정보 INSERT하는 페이지만 작성
  
 ### 14일차 (15일차와 합쳐서 올릴것)
  1. 이용권 관리 페이지에서 이벤트 해제 기능
  2. 이벤트 관리 페이지와 이용권 관리 페이지 연동을 위해 DB 수정

### 16일차 (featur16)
  1. 이벤트 변경 -> 해당 이벤트 클릭하면 modal창에서 변경 사항 DB에 UPDATE
  2. 레퍼럴 관리에서 Clone 클릭하면 해당 문자열 복사
  3. 레퍼럴 관리에서 저장 누르면 레퍼럴 저장하기

### 17일차 + 18일차 + 19일차 (feature19)
  1. 이용권 결제 리스트 VIEW단 작성
  2. 이용권 결제에 필요한 결제 TABLE 생성(결제 TABLE-결제일, ID, 이용권명(FK), 이벤트 적용(FK))
  3. 이용권 결제 리스트 VIEW에서 결제 리스트 불러오는 기능 구현
  4. 리스트 불러왔을 때 총 결제 리스트안에 결제 수와 특정 아이디의 총 금액 계산해서 VIEW에 보여주기
  5. 이용권 결제 리스트 검색 기능 구현하기 위해 먼저 ID, 기간, 이용권명으로 검색하기 위한 이영권 결제 리스트 VIEW 수정
  6. 리스트 불러왔을 때 결제 금액 계산 하는 부분 VIEW에 추가해주고 해당 이용권의 금액 정보와 이벤트 할인율 가지고 계산해서 보여주는 기능 구현

### 20일차 (feature20)
  1. 리스트 불러왔을 때 결제 금액 계산 하는 부분 VIEW에 추가해주고 해당 이용권의 금액 정보와 이벤트 할인율 가지고 계산해서 보여주는 기능 구현
  2. 리스트 목록 부분 엑셀 다운로드 기능 추가
  
  
💡 참고사항: 지금 SVN에 서로 COMMIT하고 MERGE되었기 때문에 내가 작성 안한 부분도 있음
내가 작성한 부분은
[eventMg] [useticketMg]에 해당되는 java쪽 과 resource들과 header와 footer와 httputils등임 +[refferal] + [paymentlist]부분 추가됨
