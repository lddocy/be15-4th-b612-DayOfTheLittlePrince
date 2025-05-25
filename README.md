<br>
<img src="https://github.com/user-attachments/assets/9e9d201a-1f3e-40ce-a266-f44450217ac3" />


<br><br>
## 👑 B612 팀원 소개
<div align="center">
<table>
  <tr>
    <td align="center"><img src="https://github.com/user-attachments/assets/1dd37956-9ff1-4a43-8652-ead470144481" width="1300" /></td>
    <td align="center"><img src="https://github.com/user-attachments/assets/b7e55152-6493-4e2b-8bcb-96805c3b6ed3" width="1300"/></td>
    <td align="center"><img src="https://github.com/user-attachments/assets/9feae13f-adfb-4d48-91f0-d0e1109f99ea" width="1300"/></td>
    <td align="center"><img src="https://github.com/user-attachments/assets/0677de08-4ef6-4cf1-af92-292096511be8" width="1300"/></td>
    <td align="center"><img src="https://github.com/user-attachments/assets/a3d657f2-6a50-41ca-a6e6-8bcc85a2e349" width="1300"/></td>
    <td align="center"><img src="https://github.com/user-attachments/assets/a3fc268f-be83-4cdb-beae-641897ee6e96" width="1300"/></td>
  </tr>
  <tr>
    <td align="center"><a href="https://github.com/Jangwoo0710">박장우</a></td>
    <td align="center"><a href="https://github.com/Joonspar">박준서</a></td>
    <td align="center"><a href="https://github.com/oyk0510">오유경</a></td>
    <td align="center"><a href="https://github.com/lddocy">윤채영</a></td>
    <td align="center"><a href="https://github.com/kkkwid">이승재</a></td>
    <td align="center"><a href="https://github.com/Cho-Hyun-Seung">조현승</a></td>
  </tr>
</table>

</div>
<br>


## 📍 목차

<a href="#1">1. 프로젝트 기획</a>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#1-1">1-1. 프로젝트 소개</a>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#1-2">1-2. 주요 기능</a>

<a href="#2">2. 설계 문서</a>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#2-1">2-1. 요구사항 명세서</a>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#2-2">2-2. 화면 설계서</a>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#2-3">2-3. DB 모델링 </a>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#2-4">2-4. REST API 명세서 </a>

<a href="#3">3. 프로젝트 아키텍처 구조도 </a>

<a href="#4">4. 빌드 및 배포 </a>

<a href="#5">5. 테스트 결과 </a>

<a href="#6">6. 기술 스택 </a>

<a href="#7">7. 트러블 슈팅 </a>

<a href="#8">8. 팀원 회고 </a>

<br>

---

## <p id="1"> 🪐 1. 프로젝트 기획</p>

### <p id="1-1">1-1. 프로젝트 소개</p>
> #### 프로젝트 개요
> 
![little_prince_banner_twinkle](https://github.com/user-attachments/assets/02937fa2-9fe8-4e76-b25a-06c3e358af88)

**어린왕자의 하루(Day Of The Little Prince)**는 '어린왕자' 세계관을 기반으로 구성된 감성적인 **투두리스트** 서비스입니다. 사용자들은 자신만의 행성을 꾸미고, 할 일을 통해 경험치를 얻고, 성장하며, 다양한 아이템과 칭호를 획득할 수 있습니다. 단순한 일정 관리 이상의 정서적 동기부여와 성취감을 제공하는 것이 핵심입니다.
<br> <br>

> #### 기획 배경

현대인들은 반복되는 일상 속에서 자신을 잊고 지내기 쉬우며, 단순한 일정 관리 앱으로는 꾸준한 동기부여가 어렵습니다. 이에 따라, 감성적인 요소와 서사 구조를 결합한 형태의 일정 관리 도구가 필요했습니다. ‘어린왕자’는 성장, 성찰, 관계에 대한 메시지를 담고 있어 이러한 감성 기반의 자기 관리 서비스에 적합한 소재입니다.

<br>

> #### 필요성

✅ 기존 투두리스트의 낮은 지속 사용률 개선<p/>
✅ MZ세대의 감성적 소비/사용 트렌드 반영<p/>
✅ 게임적 요소를 통한 몰입 유도<p/>
✅ 자기효능감 및 성취감 증진

<br>

> #### 차별점

|기존 서비스|어린왕자의 하루|
|:-----------:|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|텍스트 기반 단순 UI|감성적 디자인과 스토리텔링 요소|
|단순 완료 체크|경험치, 레벨업, 칭호 시스템|
|무감정적 사용 흐름|행성 성장과 감성 회고 기반 UX|
|반복 작업 유도|AI 기반 내일의 할 일 추천 기능|

<br>

> #### 서비스 대상
> 
💫 20~30대 감성형 소비자<p/>
🔌 꾸준한 자기 관리가 필요한 직장인/학생<p/>
🌧️ 기존 투두리스트 앱 사용에 쉽게 지치는 사용자<p/>
🔮 나만의 세계관을 꾸미며 성취감을 느끼고 싶은 유저

<br>

> #### 기대 효과

✅ 지속적인 자기계발 습관 형성<p/>
✅ 할 일을 통한 성취 경험 증대<p/>
✅ 감성적 만족감과 몰입도 상승<p/>
✅ 개인의 성장 이력을 시각적으로 기록 가능

<br>

> #### 추후 방향성

✅ 소셜 기능 추가 (다른 사용자의 행성 탐방 및 응원)<p/>
✅ AI 맞춤형 할 일 추천 고도화<p/>
✅ 웹/모바일 크로스 플랫폼 연동<p/>
✅ 커스터마이징 가능한 행성 및 아이템 스킨 확대<p/>
✅ ‘어린왕자’ 명언 기반 콘텐츠 큐레이션 기능

<br>

### <p id="1-2">1-2. 주요 기능</p>

<details>
<summary><strong>🔒 회원 기능</strong></summary>
<div>
<br>
<p>- 이메일 로그인/회원가입</p>
<p>- 비밀번호 찾기/비밀번호 재설정</p>
<p>- 회원 탈퇴</p>
<br>
</div>
</details>

<details>
<summary><strong>👽 프로필</strong></summary>
<div>
<br>
<p>- 획득한 칭호 목록 조회</p>
<p>- 대표 칭호 선택</p>
<p>- 획득한 아이템 목록 조회</p>
<p>- 아이템 숨김/배치 </p>
<p>- 경험치 조회</p>
<p>- 이번 달 달성률/총 달성률 조회</p>
<br>
</div>
</details>

<details>
<summary><strong>✏️ 투두리스트</strong></summary>
<div>
<br>
<p>- 할 일 등록/삭제</p>
<p>- 할 일 목록 조회</p>
<p>- 할 일 상세 조회</p>
<p>- 할 일 완료 체크</p>
<p>- AI 추천 요청</p>
<p>- 날짜/제목 지정 (장기 목표)</p>
<p>- 오늘의 할 일 상세 조회</p>
<br>
</div>
</details>

<details>
<summary><strong>🪐 행성</strong></summary>
<div>
<br>
<p>- 행성 이름 조회/수정<p>
<p>- 경험치 증가<p>
<p>- 아이템 획득<p>
<p>- 캡처 모드<p>
<p>- BGM 재생/중단<p>
<br>
</div>
</details>

<details>
<summary><strong>📅 캘린더</strong></summary>
<div>
<br>
<p>- 회고 입력/수정/조회</p>
<br>
</div>
</details>

<details>
<summary><strong>🔔 알림</strong></summary>
<div>
<br>
<p>- 알림 목록 조회</p>
<p>- 알림 읽음 표시</p>
<p>- 오전/오후 알림</p>
<p>- 레벨업 알림</p>
<br>
</div>
</details>

<br><br>

## <p id="2"> 📑 2. 설계 문서</p>

### <p id="2-1">2-1. 요구사항 명세서</p>
[![요구사항명세서](https://img.shields.io/badge/요구사항명세서-바로가기-yellow?style=for-the-badge)](https://docs.google.com/spreadsheets/d/1SZNyWQLIJLVS90pm35UEcq7Z-KEai1p2RJoas12Ae-Q/edit?gid=0#gid=0) 
<div>
  <img src="https://github.com/user-attachments/assets/f8c971b2-acb1-4291-84bc-6c55475e0398" />  
</div>

### <p id="2-2">2-2. 화면 설계</p>
[![화면설계서](https://img.shields.io/badge/Figma-바로가기-yellow?style=for-the-badge)](https://www.figma.com/design/ysz1Fl7uJ3ZB3P6qS7LCXz/%EC%96%B4%EB%A6%B0%EC%99%95%EC%9E%90%EC%9D%98-%ED%95%98%EB%A3%A8?node-id=0-1&t=In65T3vSkLxL2pr7-1) 
<div>
  <img src="https://github.com/user-attachments/assets/081fee3f-3c91-43ef-b54d-4be998716f23" />
</div>


<br><br>

##  <p id="2-3">2-3. DB 모델링</p>
[![DB모델링](https://img.shields.io/badge/ERDCloud-바로가기-green?style=for-the-badge)](https://www.erdcloud.com/d/BybX9eAbfxLva38Fs)
<div>
  <img src="https://github.com/user-attachments/assets/7da742a0-4fd4-4a22-85b0-d3f2dd280a95"/>
</div>

<br><br>

##  <p id="2-4">2-4. REST API 명세서 </p>
<div >
  <img src="https://github.com/user-attachments/assets/0fac6c7d-313c-434e-a97d-c912c27bb93e" />
</div>

<br><br>

## <p id="3"> 🧩 3. 프로젝트 아키텍처 구조도 </p>
내용추가예정
<br><br>

## <p id="4"> 🤖 4. 빌드 및 배포 </p>

<details>
<summary><strong>Jenkins Pipeline Script</strong></summary>
<div>
내용 추가
</div>
</details>


<br><br>

## <p id="5"> 🌠 5. 테스트 결과 </p>

<details>
<summary><strong>회원</strong></summary>
<div>
<h2>회원</h2>
<h4>로그인<h4>
<h4>회원가입<h4>
<h4>비밀번호 찾기<h4>
</div>
</details>

<details>
<summary><strong>메인페이지</strong></summary>
<div>
<h2>메인페이지</h2>
<h4>아이템 조회<h4>
<h4>행성 이름 수정<h4>
<img src="https://github.com/user-attachments/assets/818a296f-1c4f-4ac8-b59c-e805d473233b"/>

</div>
</details>

<details>
<summary><strong>마이페이지</strong></summary>
<div>
<h2>마이페이지</h2>
<h4>아이템 숨김/배치</h4>
<h4>칭호 선택<h4>
<h4>달성률 조회<h4>
<h4>경험치 조회<h4>
</div>
</details>

<details>
<summary><strong>투두 리스트</strong></summary>
<div>
<h2>투두 리스트</h2>
<h4>단기 투두 리스트</h4>
<h5>등록</h5>
<h5>AI 추천</h5>
<h5>조회</h5>
<h4>장기 목표<h4>
<h5>등록</h5>
<h5>조회</h5>
</div>
</details>

<details>
<summary><strong>캘린더</strong></summary>
<div>
<h2>캘린더</h2>
<h4>메인 캘린더 조회</h4>
<h4>회고 등록</h4>
<h4>회고 수정</h4>
</div>
</details>

<details>
<summary><strong>알림</strong></summary>
<div>
<h2>알림</h2>
<h4>알림 확인</h4>
<h5>오전/오후 알림</h5>
<h5>레벨업 알림</h5>
<h4>알림 읽음 표시</h4>
</div>
</details>

<br><br>
## <p id="6"> 🔧 6. 기술 스택 </p>
<div align="center">
<div dir="auto">
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"/>
<img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white"/>
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white"/>
<img src="https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white">
</div>
<div dir="auto">
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/ERDCLOUD-339AF0?style=for-the-badge&logoColor=white">
<img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white">
<img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white">
<img src="https://img.shields.io/badge/Redis-FF4438?style=for-the-badge&logo=redis&logoColor=white">
</div>
</div>

<br><br>

##  <p id="7"> ☄️ 7. 트러블 슈팅 </p>

> ### 🚧 GitHub 대용량 파일 푸시 오류 <br>
> #### **1️⃣ 문제 상황** <br>
> .exr 확장자의 고해상도 HDRI 배경 이미지를 git add 후 커밋
> GitHub는 100MB 초과 단일 파일에 대해 푸시 자체를 거절
> .gitignore을 추가해도, 이미 커밋된 히스토리는 여전히 남아 있음
> #### **2️⃣ 원인** <br>
> 대용량 .exr 파일이 커밋 이력에 포함되어 있음
> 단일 파일 최대 100MB, 권장 50MB 이하
> .gitignore만으로는 커밋 이력 제거 불가
> #### **3️⃣ 해결 방법** <br>
> .gitignore에 예외 등록
>  Git 기록 정리를 위한 새 clone
> git filter-repo로 Git 히스토리에서 완전 삭제
> .gitignore 반영 커밋
> 강제 푸시 (push --force)

<br><br>

##  <p id="8"> 🧑🏻‍🚀 8. 팀원 회고 </p>

|이름|회고|
|:-----------:|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|박장우|  |
|박준서|  |
|오유경|  |
|윤채영|  |
|이승재|  |
|조현승|  |

<br>

