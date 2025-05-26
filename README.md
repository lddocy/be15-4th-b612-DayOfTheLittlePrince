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

**어린왕자의 하루**는 '어린왕자' 세계관을 기반으로 구성된 감성적인 **투두리스트** 서비스입니다. 사용자들은 자신만의 행성을 꾸미고, 할 일을 통해 경험치를 얻고, 성장하며, 다양한 아이템과 칭호를 획득할 수 있습니다. 단순한 일정 관리 이상의 정서적 동기부여와 성취감을 제공하는 것이 핵심입니다.
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
<p>- 행성 캡처 모드<p>
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
  <img src="https://github.com/user-attachments/assets/4ea42e49-c2c2-489b-9d14-de5eadb43681" />
</div>

<br><br>

### <p id="2-2">2-2. 화면 설계</p>
[![화면설계서](https://img.shields.io/badge/Figma-바로가기-blue?style=for-the-badge)](https://www.figma.com/design/ysz1Fl7uJ3ZB3P6qS7LCXz/%EC%96%B4%EB%A6%B0%EC%99%95%EC%9E%90%EC%9D%98-%ED%95%98%EB%A3%A8?node-id=0-1&t=In65T3vSkLxL2pr7-1) 
<div>
  <img src="https://github.com/user-attachments/assets/081fee3f-3c91-43ef-b54d-4be998716f23" />
</div>

<br><br>

##  <p id="2-3">2-3. DB 모델링</p>
[![DB모델링](https://img.shields.io/badge/ERDCloud-바로가기-green?style=for-the-badge)](https://www.erdcloud.com/d/BybX9eAbfxLva38Fs)
<div>
  <img src="https://github.com/user-attachments/assets/b03bee15-b85f-462f-813f-29a9071b9980"/>
</div>

<br><br>

##  <p id="2-4">2-4. REST API 명세서 </p>
<div >
  <img src="https://github.com/user-attachments/assets/0fac6c7d-313c-434e-a97d-c912c27bb93e"/>
</div>

<br><br>

## <p id="3"> 🧩 3. 프로젝트 아키텍처 구조도 </p>
내용추가예정

<br><br>

## <p id="4"> 🤖 4. 빌드 및 배포 </p>
[![CI/CD 시연](https://img.shields.io/badge/CI/CD시연영상-바로가기-purple?style=for-the-badge)](https://www.youtube.com/watch?v=t-E7YVeR91A) 

<details>
<summary><strong>Jenkins Pipeline Script 보기</strong></summary>

```groovy
pipeline {
    agent any

    tools {
        gradle 'gradle'
        jdk 'openJDK17'
        nodejs 'Node JS 22.15'
    }

    environment {
        SOURCE_GITHUB_URL = 'https://github.com/Cho-Hyun-Seung/CI-CD-test'
        MANIFESTS_GITHUB_URL = 'https://github.com/Cho-Hyun-Seung/b612-manifest'
        GIT_USERNAME = 'Cho-Hyun-Seung'
        GIT_EMAIL = 'toki0327@naver.com'
    }

    stages {
        stage('Checkout Source') {
            steps {
                git branch: 'main', url: "${env.SOURCE_GITHUB_URL}"
            }
        }

        stage('Build Backend') {
            steps {
                dir('be15-4th-b612-littleprince-be') {
                    script {
                        if (isUnix()) {
                            sh './gradlew clean bootJar'
                        } else {
                            bat 'gradlew.bat clean bootJar'
                        }
                    }
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('be14-4th-b612-littleprince-fe') {
                    script {
                        if (isUnix()) {
                            sh 'npm install'
                            sh 'npm run build'
                        } else {
                            bat 'npm install'
                            bat 'npm run build'
                        }
                    }
                }
            }
        }

        stage('Docker Login & Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    script {
                        if (isUnix()) {
                            sh "docker login -u ${DOCKER_USER} -p ${DOCKER_PASS}"
                        } else {
                            bat "docker login -u %DOCKER_USER% -p %DOCKER_PASS%"
                        }

                        dir('be15-4th-b612-littleprince-be') {
                            if (isUnix()) {
                                sh "docker build -t ${DOCKER_USER}/k8s-b612-backend:${currentBuild.number} ."
                                sh "docker build -t ${DOCKER_USER}/k8s-b612-backend:latest ."
                                sh "docker push ${DOCKER_USER}/k8s-b612-backend:${currentBuild.number}"
                                sh "docker push ${DOCKER_USER}/k8s-b612-backend:latest"
                            } else {
                                bat "docker build -t ${DOCKER_USER}/k8s-b612-backend:${currentBuild.number} ."
                                bat "docker build -t ${DOCKER_USER}/k8s-b612-backend:latest ."
                                bat "docker push ${DOCKER_USER}/k8s-b612-backend:${currentBuild.number}"
                                bat "docker push ${DOCKER_USER}/k8s-b612-backend:latest"
                            }
                        }

                        dir('be14-4th-b612-littleprince-fe') {
                            if (isUnix()) {
                                sh "docker build -t ${DOCKER_USER}/k8s-b612-frontend:${currentBuild.number} ."
                                sh "docker build -t ${DOCKER_USER}/k8s-b612-frontend:latest ."
                                sh "docker push ${DOCKER_USER}/k8s-b612-frontend:${currentBuild.number}"
                                sh "docker push ${DOCKER_USER}/k8s-b612-frontend:latest"
                            } else {
                                bat "docker build -t ${DOCKER_USER}/k8s-b612-frontend:${currentBuild.number} ."
                                bat "docker build -t ${DOCKER_USER}/k8s-b612-frontend:latest ."
                                bat "docker push ${DOCKER_USER}/k8s-b612-frontend:${currentBuild.number}"
                                bat "docker push ${DOCKER_USER}/k8s-b612-frontend:latest"
                            }
                        }
                    }
                }
            }
        }

        stage('Update K8s Manifests') {
            steps {
                git credentialsId: 'github', url: "${env.MANIFESTS_GITHUB_URL}", branch: 'main'
                script {
                    if (isUnix()) {
                        sh "sed -i '' 's/k8s-b612-backend:.*\$/k8s-b612-backend:${currentBuild.number}/g' backend-dep.yml"
                        sh "sed -i '' 's/k8s-b612-frontend:.*\$/k8s-b612-frontend:${currentBuild.number}/g' frontend-dep.yml"
                        sh "git add backend-dep.yml frontend-dep.yml"
                        sh "git config --global user.name '${env.GIT_USERNAME}'"
                        sh "git config --global user.email '${env.GIT_EMAIL}'"
                        sh "git commit -m '[UPDATE] ${currentBuild.number} image versioning'"
                        sh "git push -u origin main"
                    } else {
                        bat "powershell -Command \"(Get-Content backend-dep.yml) -replace 'k8s-b612-backend:.*', 'k8s-b612-backend:${currentBuild.number}' | Set-Content backend-dep.yml\""
                        bat "powershell -Command \"(Get-Content frontend-dep.yml) -replace 'k8s-b612-frontend:.*', 'k8s-b612-frontend:${currentBuild.number}' | Set-Content frontend-dep.yml\""
                        bat "git add backend-dep.yml frontend-dep.yml"
                        bat "git config --global user.name '${env.GIT_USERNAME}'"
                        bat "git config --global user.email '${env.GIT_EMAIL}'"
                        bat "git commit -m \"[UPDATE] ${currentBuild.number} image versioning\""
                        bat "git push -u origin main"
                    }
                }
            }
        }
    }

    post {
        always {
            script {
                if (isUnix()) {
                    sh 'docker logout'
                } else {
                    bat 'docker logout'
                }
            }
        }
        success {
            echo '✅ Pipeline completed successfully.'
        }
        failure {
            echo '❌ Pipeline failed.'
        }
    }
}
```
</details>

<br><br>

## <p id="5"> 🌠 5. 테스트 결과 </p>

<details>
<summary><strong>회원</strong></summary>
<div>
<h2>회원</h2>
<h4>로그인</h4>
<img src="https://github.com/user-attachments/assets/804bde9d-1ae4-4694-ab1f-d544102170cd"/>
<h4>회원가입</h4>
<img src="https://github.com/user-attachments/assets/43ae111d-8331-4550-886b-e6c7432b3f60"/>
<h4>비밀번호 찾기</h4>
<img src="https://github.com/user-attachments/assets/1234150d-845c-4c44-9268-b452e1c59a53"/>
<h4>회원 탈퇴</h4>
<img src="https://github.com/user-attachments/assets/1778a4fc-ad12-412b-99fe-468bfce90121"/>

</div>
</details>

<details>
<summary><strong>메인페이지</strong></summary>
<div>
<h2>메인페이지</h2>
<h4>아이템 조회</h4>
<img src="https://github.com/user-attachments/assets/54367e8b-c54a-4b21-89ff-3e4fadf234df/>
<h4>행성 이름 수정</h4>
<img src="https://github.com/user-attachments/assets/818a296f-1c4f-4ac8-b59c-e805d473233b"/>
<h4>날씨 할 일 추천</h4>
<img src="https://github.com/user-attachments/assets/a0556acf-b04f-4199-b77a-035dea3b7c61"/>
</div>
</details>

<details>
<summary><strong>마이페이지</strong></summary>
<div>
<h2>마이페이지</h2>
  
<h4>아이템 숨김/배치</h4>
<img src="https://github.com/user-attachments/assets/32bed5f7-d6d7-495c-aedd-aa20a08640b6"/>
<h4>칭호 선택</h4>
<img src="https://github.com/user-attachments/assets/b409c552-4dbf-44d4-937e-0f4e50ae2823"/>
<h4>달성률 조회</h4>
<img src="https://github.com/user-attachments/assets/1f30ccad-0b19-4629-8706-d33a715e7386"/>
<h4>경험치 조회</h4>
<img src="https://github.com/user-attachments/assets/a5891987-59bd-442d-a068-defaffc660e1"/>
</div>
</details>
  
<details>
<summary><strong>투두 리스트</strong></summary>
<div>
<h2>투두 리스트</h2>
<h4>단기 투두 리스트</h4>
<h5>단기 투두리스트 조회</h5>
<img src="https://github.com/user-attachments/assets/357479f0-2d29-4783-b755-efc5a4224647"/>
<h5>단기 투두리스트 삭제</h5>
<img src="https://github.com/user-attachments/assets/1d04b311-f161-4ad7-a58c-c4c474397805"/>

  <h4>장기 플랜<h4>
<h5>장기 플랜 조회</h5>
<img src="https://github.com/user-attachments/assets/fa0863f7-dd0b-45fc-9216-4e16ed54d40e"/>
<h5>장기 플랜 등록</h5>
<img src="https://github.com/user-attachments/assets/5cee7c20-d836-47b5-8638-666b7164b1f0"/>
<h5>장기 플랜 하위 투두리스트 등록</h5>
<img src="https://github.com/user-attachments/assets/aae88f98-59c5-41be-a8b4-8e6be3471832"/>
<h5>장기 플랜 삭제</h5>
<img src="https://github.com/user-attachments/assets/1205978a-c169-456f-b6a1-cd2322abf3ea"/>

<h4>AI 추천<h4>
<h5>AI 생성 성공</h5>
<img src="https://github.com/user-attachments/assets/75ea1a77-9903-4f00-8edd-d44633829adc"/>
<h5>AI 생성 실패</h5>
<img src="https://github.com/user-attachments/assets/65e5ae7f-4cfa-4a83-8ac8-e010404eab96"/>

</div>
</details>

<details>
<summary><strong>캘린더</strong></summary>
<div>
<h2>캘린더</h2>
<h4>메인 캘린더 조회</h4>
<img src="https://github.com/user-attachments/assets/d52fee46-3264-4de7-9639-d76f605fc10f"/>
<h4>회고 등록</h4>
<img src="https://github.com/user-attachments/assets/3041a958-337a-4dac-9281-3261c02613e6"/>
<h4>회고 수정</h4>
<img src="https://github.com/user-attachments/assets/aa7bdf79-cab7-4179-9434-e1ee2a20d513"/>
</div>
</details>

<details>
<summary><strong>알림</strong></summary>
<div>
<h2>알림</h2>
<h4>알림 확인</h4>
<img src="https://github.com/user-attachments/assets/88376b06-6d66-43f6-bdaa-7f8d4b5d2c53"/>
<h5>오전/오후 알림</h5>
<img src="https://github.com/user-attachments/assets/e02e8df7-1266-4bb2-af6d-2164bfa96b73"/>
<h5>레벨업 알림</h5>
<img src="https://github.com/user-attachments/assets/fa4dad5d-47e5-4d20-8b09-014e39768c50"/>
<h4>알림 읽음 표시</h4>
<img src="https://github.com/user-attachments/assets/00efd585-790d-419d-b31f-4faa5398e559"/>

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

> ## 🚧 GitHub 대용량 파일 푸시 오류 <br>
> #### **1️⃣ 문제 상황** <br>
> - .exr 확장자의 고해상도 HDRI 배경 이미지를 git add 후 커밋<br>
> - GitHub는 100MB 초과 단일 파일에 대해 푸시 자체를 거절<br>
> - .gitignore을 추가해도, 이미 커밋된 히스토리는 여전히 남아 있음<br>
> #### **2️⃣ 원인** <br>
> - 대용량 .exr 파일이 커밋 이력에 포함되어 있음<br>
> - 단일 파일 최대 100MB, 권장 50MB 이하<br>
> - .gitignore만으로는 커밋 이력 제거 불가<br>
> #### **3️⃣ 해결 방법** <br>
> - .gitignore에 예외 등록<br>
> - Git 기록 정리를 위한 새 clone<br>
> - git filter-repo로 Git 히스토리에서 완전 삭제<br>
> - .gitignore 반영 커밋<br>
> - 강제 푸시 (push --force)

<br>

> ## 🚧 마이페이지 아이템 실시간 반영 실패 <br>
> #### **1️⃣ 문제 상황**  
> 마이페이지에서 아이템을 토글(숨김/표시)하면 `itemVisibilityMap` 값은 분명히 잘 변경되었고, 콘솔에도 정상적으로 출력되었다.  
> 하지만 Three.js 씬에는 그 변경사항이 실시간으로 반영되지 않았다.  
> 강제로 `window.location.reload()`를 호출하면 반영되긴 했지만, 이건 깔끔한 해결은 아니었다.
> #### **2️⃣ 원인**  
> - Three.js는 `scene.add()`로 추가된 오브젝트의 `.visible` 속성을 외부 반응형 상태로 자동 업데이트해주지 않음  
> - Vue에서는 `itemVisibilityMap`을 반응형으로 관리했지만, 해당 값이 바뀌어도 씬에 이미 추가된 모델에는 아무런 영향을 주지 않음  
> - 라우트가 변경될 때는 `.visible` 속성을 조정했지만, `itemVisibilityMap` 변경을 감지해서 처리하는 로직은 빠져 있었음
> #### **3️⃣ 해결 방법**
> 
> - `MyPageModal.vue`에서 아이템을 토글할 때 `@refresh-item-map` 이벤트 emit  
> - 상위 컴포넌트에서 `fetchItemMap()` 호출하여 `itemVisibilityMap`을 갱신  
> - `SceneItemManager.vue` 내에서 `watch(() => props.itemVisibilityMap, ...)`로 변경 사항을 감지해보려 시도  
> - `scene.children` 내 오브젝트를 순회하며 `.name` 기준으로 찾아 `.visible` 속성을 수동으로 업데이트하는 방식도 적용  
> - `props.memberLevel`, `routePath`와의 조건을 다시 체크해 `.visible` 조건을 재계산하도록 작성  
> #### **4️⃣ 해결 방법**  
> 여러 시도를 했지만 결국 실시간 반영에는 실패했다.  
> Three.js가 내부적으로 관리하는 3D 오브젝트와 Vue의 반응형 상태를 자연스럽게 연결하기가 어려웠다.  
> 결국 `window.location.reload()`로 씬 전체를 리로드하는 방식 외에는 반영이 되지 않아 임시 방편으로 사용 중이다.

<br>

> ## 🚧 ArgoCD Sync 에러 <br>
> #### **1️⃣ 문제 상황** <br>
> - ArgoCD Sync 과정에서 에러 발생<br>
![에러 스크린샷](https://github.com/user-attachments/assets/fe9e47bc-5ea0-4d37-b183-895a3ec9e2be)
> #### **2️⃣ 원인** <br>
> - mariadb-pvc를 읽어오는 과정에서 argoCD가 manifest 파일을 수정하려고 해서 오류가 발생<br>
```
one or more objects failed to apply, reason: error when patching "/dev/shm/2792027684": PersistentVolumeClaim "mariadb-pvc" is invalid: spec: Forbidden: spec is immutable after creation except resources.requests and volumeAttributesClassName for bound claims
  core.PersistentVolumeClaimSpec{
  	... // 2 identical fields
  	Resources:        {Requests: {s"storage": {i: {...}, s: "2Gi", Format: "BinarySI"}}},
  	VolumeName:       "mariadb-pv",
- 	StorageClassName: &"manual",
+ 	StorageClassName: nil,
  	VolumeMode:       &"Filesystem",
  	DataSource:       nil,
  	... // 2 identical fields
  }. Retrying attempt #5 at 12:53PM.
```
> #### **3️⃣ 해결 방법** <br>
> - spec.ignoreDifferences 설정과 syncPolicy.syncOptions에서 RespectIgnoreDifferences=true를 설정을 통해 특정 필드 무시 및 동기화 오류 회피  
> <img src="https://github.com/user-attachments/assets/cbedae61-7b98-47b4-a471-bfe5201ece66"/>

<br><br>

##  <p id="8"> 🧑🏻‍🚀 8. 팀원 회고 </p>

|이름|회고|
|:-----------:|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|박장우|  |
|박준서|이번 프로젝트는 Docker를 활용하여 배포까지 직접 경험해볼 수 있었던 소중한 기회였습니다. 프로젝트 기간이 짧았던 만큼, 기능적인 범위는 이전 프로젝트에 비해 다소 축소되었지만, 주제가 Todolist였기 때문에 구현 난이도가 낮아 전체적인 진행은 비교적 수월했습니다. 이를 통해 프로젝트의 난이도와 주어진 기간을 고려하여 기능을 어떻게 조절할지에 대한 감각을 점차 익힐 수 있었습니다. 이번 프로젝트에서는 새로운 기술과 API를 적용해볼 수 있는 기회가 많았습니다. Three.js와 Web Push처럼 처음 접해보는 기술을 실제 코드에 적용하면서, 단순한 구현을 넘어서 UX 측면에서도 어떻게 구성할지 고민하며 개발에 임했습니다. 특히 프론트엔드에서 애니메이션을 직접 구현해본 경험은 매우 인상 깊었습니다. 전체적으로 기능 구현, 새로운 기술 학습, UX에 대한 고민, 그리고 배포 환경까지 경험하면서 개발자로서 한 단계 성장할 수 있었던 프로젝트였습니다. 이번 경험을 바탕으로, 다가올 최종 프로젝트에서는 보다 완성도 높은 결과물을 만들 수 있도록 더욱 적극적이고 주도적으로 임하겠습니다.|
|오유경|  |
|윤채영|Three.js를 활용한 3D 아이템 배치 기능과 게임 요소를 결합한 웹 서비스를 팀 프로젝트로 개발하며, 단순한 기술 구현을 넘어서 사용자 경험과 상업성까지 함께 고민했습니다. 프론트엔드에서는 직관적인 UI/UX의 중요성을, 백엔드에서는 사용자 상태를 정교하게 관리하기 위한 데이터 흐름과 구조 설계의 필요성을 절감했습니다. 특히 자유도 높은 아이템 배치를 통해 의미 있는 상호작용을 제공하려 노력했고, 그 과정에서 게임성과 실용성, 시장성 간의 균형을 추구했습니다. 무엇보다 각자의 전문 영역을 책임감 있게 수행해준 팀원들과의 협업을 통해, 혼자였다면 구현하기 어려웠을 완성도를 이끌어낼 수 있었고, 커뮤니케이션의 중요성과 팀워크의 시너지를 다시 한 번 실감한 프로젝트였습니다.|
|이승재|  |
|조현승|  |

<br>

