<img src="https://github.com/user-attachments/assets/9e9d201a-1f3e-40ce-a266-f44450217ac3" />

<br><br>
## 👑 B612 팀원 소개
<div align="center">
<table>
  <tr>
    <td align="center"><img src="https://github.com/user-attachments/assets/1dd37956-9ff1-4a43-8652-ead470144481" width="1300" /></td>
    <td align="center"><img src="https://github.com/user-attachments/assets/b7e55152-6493-4e2b-8bcb-96805c3b6ed3" width="1300"/></td>
    <td align="center"><img src="https://github.com/user-attachments/assets/9feae13f-adfb-4d48-91f0-d0e1109f99ea" width="1300"/></td>
    <td align="center"><img src="https://github.com/user-attachments/assets/c56abfa9-75e8-494d-8471-60037347f94b" width="1300"/></td>
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

<br><br>

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

### <p id="1-3">1-3. 사용자 메뉴얼</p>
<details>
<summary><strong>사용자 메뉴얼 보러가기</strong></summary>
<div>
<img src="https://github.com/user-attachments/assets/13b4c142-aa19-4108-883d-c9b284022469"/>
<img src="https://github.com/user-attachments/assets/4e465160-b596-4d5e-8e4e-075a9e7f3a1c"/>
<img src="https://github.com/user-attachments/assets/22d9af87-3bf4-4dad-b370-ee2eeabd05d9"/>
<img src="https://github.com/user-attachments/assets/0eabc5fd-6f6f-4723-a559-a898bcf462ba"/>
<img src="https://github.com/user-attachments/assets/a54acd27-6661-4389-8846-469a8e115264"/>
<img src="https://github.com/user-attachments/assets/d8e01ba6-d45f-4b52-a804-0193d44c0f36"/>
<img src="https://github.com/user-attachments/assets/85974028-ee46-4ca9-a4be-50cffd638123"/>
<img src="https://github.com/user-attachments/assets/f59fef5a-608d-4654-b423-902f1962ccbf"/>
<img src="https://github.com/user-attachments/assets/9a7e64c2-6320-4ffe-bba2-3911f070b718"/>
<img src="https://github.com/user-attachments/assets/ea5cb157-94cc-48cc-9437-aa67a8a1aedb"/>
<img src="https://github.com/user-attachments/assets/7b35d29a-acd9-4c5e-bcbe-1db48c4f7ec3"/>
<img src="https://github.com/user-attachments/assets/f00b551d-944e-46f7-b66e-9e6290f44249"/>
<img src="https://github.com/user-attachments/assets/8f7430c6-668c-4954-937f-1108d611c342"/>
<img src="https://github.com/user-attachments/assets/e45f62bb-3c5b-4368-b6d1-55a75bdc9a40"/>
<img src="https://github.com/user-attachments/assets/fc5f6c9c-2458-46ba-b403-c7abfc4f3409"/>
<img src="https://github.com/user-attachments/assets/6978f7ea-903e-4cd6-bbd5-be9d8098d012"/>
<img src="https://github.com/user-attachments/assets/008e78d1-a0ee-4a14-bf28-2bef039e0e74"/>

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
<img src="https://github.com/user-attachments/assets/7dad7c4d-48ab-40a3-ae58-72a3d4fe9362"/>

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
<img src="https://github.com/user-attachments/assets/981369c3-170b-49f8-a867-64eb78076c88"/>

<h4>회원가입</h4>
<img src="https://github.com/user-attachments/assets/d7b80f67-dded-4cc6-8a08-d5ab5d3560d7"/>

<h4>비밀번호 찾기</h4>
<img src="https://github.com/user-attachments/assets/64db753a-1925-4d66-a1d1-25efa0b6faad"/>

<h4>회원 탈퇴</h4>
<img src="https://github.com/user-attachments/assets/8847919d-c0e6-4e29-b5d9-aa919ee885b8"/>

</div>
</details>

<details>
<summary><strong>메인페이지</strong></summary>
<div>
<h2>메인페이지</h2>
<h4>날씨로 할 일 추천 & 아이템 조회</h4>
<img src="https://github.com/user-attachments/assets/bd36ac0d-e726-4b61-9d1e-5bc690844da4"/>

<h4>행성 이름 수정</h4>
<img src="https://github.com/user-attachments/assets/818a296f-1c4f-4ac8-b59c-e805d473233b"/>

</div>
</details>

<details>
<summary><strong>마이페이지</strong></summary>
<div>
<h2>마이페이지</h2>
  
<h4>아이템 숨김/배치</h4>
<img src="https://github.com/user-attachments/assets/3ecfb889-fdbd-404a-af7b-b8593f7e5ad1"/>

<h4>칭호 선택</h4>
<img src="https://github.com/user-attachments/assets/c30d1ebd-4b53-44f0-be11-e3acc76520c8"/>

<h4>달성률 조회</h4>
<img src="https://github.com/user-attachments/assets/f73f0e33-0bbc-4950-95a7-e9052f90981c"/>

<h4>경험치 조회</h4>
<img src="https://github.com/user-attachments/assets/03cb5e7a-27f9-403d-8bab-fbfd1785f5b3"/>
</div>
</details>
  
<details>
<summary><strong>투두 리스트</strong></summary>
<div>
<h2>투두 리스트</h2>
<h4>단기 투두 리스트</h4>
<h5>단기 투두리스트 등록</h5>
<img src="https://github.com/user-attachments/assets/b133dd6f-be2e-4f0f-a23b-589e04d85127"/>

<h5>단기 투두리스트 조회</h5>
<img src="https://github.com/user-attachments/assets/c1d894d2-b661-4a49-a5c8-85b1647836d8"/>

<h5>단기 투두리스트 삭제</h5>
<img src="https://github.com/user-attachments/assets/d193472a-0222-4985-b150-1469ae4c7840"/>

<h4>AI 추천<h4>
<h5>AI 생성 성공</h5>
<img src="https://github.com/user-attachments/assets/62640adc-c763-4a3d-9370-733a4dd9f5f8"/>

<h5>AI 생성 실패</h5>
<img src="https://github.com/user-attachments/assets/f61354f9-5545-41c2-8371-2ea6d575f971"/>

<h4>장기 플랜<h4>
<h5>장기 플랜 등록</h5>
<img src="https://github.com/user-attachments/assets/5ba6d17a-d6f5-45fa-86ec-e59ea9409b97"/>

<h5>장기 플랜 조회</h5>
<img src="https://github.com/user-attachments/assets/3c9f107b-c2d2-40a4-81a3-72b0dbca8255"/>

<h5>장기 플랜 하위 투두리스트 등록</h5>
<img src="https://github.com/user-attachments/assets/7c0cd3ac-2cc5-433d-9b02-f04e16b67420"/>

<h5>장기 플랜 삭제</h5>
<img src="https://github.com/user-attachments/assets/12e6ab8b-8788-484c-8642-d1d3139b1637"/>

</div>
</details>

<details>
<summary><strong>캘린더</strong></summary>
<div>
<h2>캘린더</h2>
<h4>메인 캘린더 조회</h4>
<img src="https://github.com/user-attachments/assets/0741a74f-ffa4-43bd-a254-0b1425f355d2"/>

<h4>회고 등록</h4>
<img src="https://github.com/user-attachments/assets/db04dae2-86ab-4c6a-bf7b-79e928519991"/>

<h4>회고 수정</h4>
<img src="https://github.com/user-attachments/assets/2831e576-9005-4617-a6a6-2f7b167ad214"/>

</div>
</details>

<details>
<summary><strong>알림</strong></summary>
<div>
<h2>알림</h2>
<h4>알림 확인</h4>
<img src="https://github.com/user-attachments/assets/ba392977-0c95-4a19-bfce-bf33325e0395"/>

<h5>오전/오후 알림</h5>
<img src="https://github.com/user-attachments/assets/78fffe45-270e-4480-8569-d7804c8a60d5"/>

<h5>레벨업 알림</h5>
<img src="https://github.com/user-attachments/assets/623bcff1-c682-4092-8f58-21c0a10b3fe2"/>

<h4>알림 읽음 표시</h4>
<img src="https://github.com/user-attachments/assets/577ba7be-6ebe-4828-a5f1-ba14d02e5a55"/>

</div>
</details>

<br><br>
## <p id="6"> 🔧 6. 기술 스택 </p>
<div align="center">
  
<h3>Backend & Database</h3>
<div dir="auto">
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"/>
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
<img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white">
<img src="https://img.shields.io/badge/Redis-FF4438?style=for-the-badge&logo=redis&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
</div>

<h3>Frontend</h3>
<div dir="auto">
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> 
<img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> 
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> 
<img src="https://img.shields.io/badge/vue.js-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white"> 
<img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
<img src="https://img.shields.io/badge/tailwindcss-%2338B2AC.svg?style=for-the-badge&logo=tailwind-css&logoColor=white"/>
<img src="https://img.shields.io/badge/vite-%23646CFF.svg?style=for-the-badge&logo=vite&logoColor=white"/>
</div>

<h3>Devops</h3>
<div dir="auto">
<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white"/>
<img src="https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white">
<img src="https://img.shields.io/badge/kubernetes-%23326ce5.svg?style=for-the-badge&logo=kubernetes&logoColor=white"/>
<img src="https://img.shields.io/badge/Argo-EF7B4D?style=for-the-badge&logo=argo&logoColor=white"/>
</div>

<h3>Tools & Communication</h3>
<div dir="auto">
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white"/>
<img src="https://img.shields.io/badge/ERDCLOUD-339AF0?style=for-the-badge&logoColor=white">
<img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white">
<img src="https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white"/>
<img src="https://img.shields.io/badge/jira-%230A0FFF.svg?style=for-the-badge&logo=jira&logoColor=white"/>
</div>

</div>

<br><br>

##  <p id="7"> ☄️ 7. 트러블 슈팅 </p>

> ## 🚧 GitHub 대용량 파일 푸시 오류 <br>
> #### **1️⃣ 문제 상황** <br>
```
vbnet
복사편집
error: File ... is 147.17 MB; this exceeds GitHub's file size limit of 100.00 MB
remote: error: GH001: Large files detected.
fatal: remote rejected

```
> - .exr 확장자의 고해상도 HDRI 배경 이미지를 git add 후 커밋<br>
> - GitHub는 100MB 초과 단일 파일에 대해 푸시 자체를 거절<br>
> - .gitignore을 추가해도, 이미 커밋된 히스토리는 여전히 남아 있음<br>
> #### **2️⃣ 원인** <br>
> - 대용량 .exr 파일이 커밋 이력에 포함되어 있음<br>
> - 단일 파일 최대 100MB, 권장 50MB 이하<br>
> - .gitignore만으로는 커밋 이력 제거 불가<br>
> #### **3️⃣ 해결 방법** <br>
> - .gitignore에 예외 등록  
> - Git 기록 정리를 위한 새 clone<br>
> - git filter-repo로 Git 히스토리에서 완전 삭제<br>
> - .gitignore 반영 커밋<br>
> - 강제 푸시 (push --force)
> #### **🚨 주의 사항** <br>
> - 클린 클론 없이 filter-repo 시도 → 히스토리에 여전히 대형 파일 남아있어 푸시 실패
> - 새로 clone 후 작업하니 히스토리까지 완전 정리됨 → push 성공
> - `git filter-repo`는 히스토리를 파괴하므로 **백업 브랜치 권장**
```bash
bash
복사편집
git branch backup-before-filter
```  
> - 협업 중이라면 `push --force` 전에 반드시 **팀원과 조율할 것**
> - 장기적으로는 Git LFS, S3, CDN 등을 통한 대용량 파일 관리 체계 고려
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
> #### **4️⃣ 최종 결과**  
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

<br>

> ## 🚧 FullCalendar에서 end_date가 하루 전까지만 표시되는 문제 <br>
> #### **1️⃣ 문제 상황** <br>
> FullCalendar에서 일정 데이터를 아래와 같이 등록했음에도 불구하고
```jsx
start: plan.startDate
end: plan.endDate // "2025-05-07"
```
> 실제로는 **5월 6일까지만 일정이 표시**되고, **7일은 빈 칸처럼 보임** (마치 6일이 종료일인 것처럼)
> ![image](https://github.com/user-attachments/assets/3d82659e-1cb7-48d4-a986-cda56314d1d0)
> #### **2️⃣ 원인** <br>
> - FullCalendar는 `end` 값을 **exclusive (해당 날짜 전까지 표시)** 방식으로 처리함
> - 즉, `end: "2025-05-07"` → **내부적으로 `2025-05-07T00:00:00` 처리**
> - 결과적으로 **5월 7일 00:00에 끝나므로, 실제 표시 범위는 5월 1일 ~ 6일**
> #### **3️⃣ 해결 방법** <br>
> - addDays() 함수로 endDate를 명시적으로 Date 객체로 변환
```jsx
function addDays(date, days) {
  const result = new Date(date);
  result.setDate(result.getDate() + days);
  return result;
}
```
> - 기존 코드에서 변경
```jsx
// 기존: 문자열 그대로 사용
const longEvents = longPlans.map((plan, index) => {
      const classIndex = (index % 5) + 1
      return {
        title: plan.title,
        start: plan.startDate,
        end: plan.endDate,
        className: ['fc-event-bar', `bg-event-${classIndex}`],
      }
    })

// 수정: 날짜 객체로 감싸서 전달 (타임존 적용)
const longEvents = longPlans.map((plan, index) => {
      const classIndex = (index % 5) + 1
      return {
        title: plan.title,
        start: plan.startDate,
        end: addDays(plan.endDate, 0),
        className: ['fc-event-bar', `bg-event-${classIndex}`],
      }
    })

**end: addDays(plan.endDate, 0), // Wed May 07 2025 00:00:00 GMT+0900 (한국 표준시)**

```
> - addDays(..., 0)은 단순히 문자열을 Date 객체로 명확히 바꿔주는 역할을 하며,
> FullCalendar는 이를 **정확한 로컬 시간**으로 해석해 해당 날짜까지 포함시킴
> - 후속 문제 : 이벤트에 12a가 붙음	(시작 시간이 00:00이라 자동 표시)
> displayEventTime: false 옵션 추가로 해결
<br><br>

##  <p id="8"> 🧑🏻‍🚀 8. 팀원 회고 </p>

|이름|회고|
|:-----------:|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|박장우|수행 기간이 길지 않았던지라 걱정도 많이 되었고 불확실성으로 가득 찼었던 이번 프로젝트였는데, 팀원 모두가 원활한 소통을 기반으로 합심해서 수행한 덕분에 수월하게 마칠 수 있었습니다. 직전 프로젝트에서 놓치고, 만들어보지 못했었던 코드들을 이번 프로젝트를 통해서 팀원 분들의 많은 도움을 받아 가며 만들어내보며 전체적인 감을 잡는 데 큰 도움이 되었던 것 같습니다. 다만, 데브옵스 프로젝트에서 데브옵스에 관련한 일을 수행해 보지 못한 것은 많이 아쉽게 남습니다. 따라서, 최종 프로젝트에 들어가서는 배포 기능 구현에도 힘을 보태고자 하며, 마지막인 만큼 완성도 높은 결과물을 도출해낼 수 있도록 후회 없이 최선을 다해서 임하도록 하겠습니다. 짧은 시간 모두 고생 많으셨습니다 !|
|박준서|이번 프로젝트는 Docker를 활용하여 배포까지 직접 경험해볼 수 있었던 소중한 기회였습니다. 프로젝트 기간이 짧았던 만큼, 기능적인 범위는 이전 프로젝트에 비해 다소 축소되었지만, 주제가 Todolist였기 때문에 구현 난이도가 낮아 전체적인 진행은 비교적 수월했습니다. 이를 통해 프로젝트의 난이도와 주어진 기간을 고려하여 기능을 어떻게 조절할지에 대한 감각을 점차 익힐 수 있었습니다. 이번 프로젝트에서는 새로운 기술과 API를 적용해볼 수 있는 기회가 많았습니다. Three.js와 Web Push처럼 처음 접해보는 기술을 실제 코드에 적용하면서, 단순한 구현을 넘어서 UX 측면에서도 어떻게 구성할지 고민하며 개발에 임했습니다. 특히 프론트엔드에서 애니메이션을 직접 구현해본 경험은 매우 인상 깊었습니다. 전체적으로 기능 구현, 새로운 기술 학습, UX에 대한 고민, 그리고 배포 환경까지 경험하면서 개발자로서 한 단계 성장할 수 있었던 프로젝트였습니다. 이번 경험을 바탕으로, 다가올 최종 프로젝트에서는 보다 완성도 높은 결과물을 만들 수 있도록 더욱 적극적이고 주도적으로 임하겠습니다.|
|오유경|이번 프로젝트는 CI/CD 파이프라인 구축 및 실제 배포를 목표로 설계되었습니다. 배포를 우선 과제로 삼기 위해 필수 기능만을 기획하여 풀스택 개발에 집중했고, 덕분에 기획 → 개발 → 테스트 → 배포의 전 과정을 체계적으로 경험할 수 있었습니다. 아쉽게도 실제 배포 단계에는 제가 직접 참여하지 못했지만, 다음 프로젝트에서는 배포까지 온전히 책임질 수 있도록 개인적으로 복습과 실습을 진행할 예정입니다. 단기간에 완전한 개발 사이클을 완성해야 한다고 생각했지만, 불필요한 기능을 과감히 줄이고 팀원 간 효율적인 협업을 이뤄낸 덕분에 예상보다 여유롭게 마무리할 수 있었습니다. 특히 테스트와 검토 단계에 충분한 시간을 들여 안정성을 확보했고, 그 결과 배포 직전에도 큰 문제 없이 릴리즈 준비를 완료할 수 있었습니다. 이번 경험을 바탕으로, 다음에는 직접 배포까지 담당하며 더욱 견고한 ****CI/CD 파이프라인을 구현하고, 배포 자동화를 시도해 볼 생각입니다. 짧은 기간 안에 핵심에 집중하는 방법을 확실히 익힌 만큼, 앞으로는 기능과 안정성 모두를 놓치지 않는 개발자가 되도록 노력하겠습니다.|
|윤채영|Three.js를 활용한 3D 아이템 배치 기능과 게임 요소를 결합한 웹 서비스를 팀 프로젝트로 개발하며, 단순한 기술 구현을 넘어서 사용자 경험과 상업성까지 함께 고민했습니다. 프론트엔드에서는 직관적인 UI/UX의 중요성을, 백엔드에서는 사용자 상태를 정교하게 관리하기 위한 데이터 흐름과 구조 설계의 필요성을 절감했습니다. 특히 자유도 높은 아이템 배치를 통해 의미 있는 상호작용을 제공하려 노력했고, 그 과정에서 게임성과 실용성, 시장성 간의 균형을 추구했습니다. 또한 CI/CD 파이프라인과 자동 배포 환경 구축을 통해 개발 효율성과 안정성을 동시에 확보한 것도 큰 성과였습니다. GitHub Actions와 같은 자동화 도구를 활용해 테스트 및 빌드, 배포 과정이 매끄럽게 이루어졌고, 이를 통해 개발-운영 간의 피드백 루프가 빨라졌습니다. 버전 관리, 브랜치 전략, 그리고 배포 주기 설계 등에서 체계적인 협업 문화를 경험할 수 있었으며, 이러한 DevOps적인 접근이 서비스의 완성도 향상에 큰 기여를 했습니다. 무엇보다 각자의 영역을 책임감 있게 수행해준 팀원들과의 협업을 통해, 혼자였다면 구현하기 어려웠을 완성도를 이끌어낼 수 있었고, 커뮤니케이션의 중요성과 팀워크의 시너지를 다시 한 번 실감한 프로젝트였습니다.|
|이승재|이번 4차 프로젝트는 배포가 주된 목적이었기 때문에 기능 구현은 최소한으로 진행했습니다. 백엔드의 경우, 복잡한 기능 없이 기본적인 구조만 구현했기 때문에 비교적 수월하게 마무리할 수 있었습니다. 반면, 프론트엔드는 아직 미숙한 부분이 많다는 것을 느꼈고, 앞으로 더 보완이 필요하다는 생각이 들었습니다. 실제 배포는 팀원 한 분이 전담해주셨기 때문에, 큰 어려움 없이 프로젝트를 마칠 수 있었지만, 개인적으로는 Kubernetes와 Docker에 대한 이해가 부족해 아쉬움이 남았습니다. 최종 프로젝트 전까지 배포 환경에 대한 이해를 높여, 직접 실습해보고 팀에 도움이 되는 역할을 할 수 있도록 준비해야겠습니다.|
|조현승|처음으로 배포 과정을 직접 경험하면서, 그동안 개념적으로만 알고 있던 Docker와 Kubernetes를 실제로 활용해볼 수 있어 뜻깊은 시간이었습니다. 특히 Jenkins를 활용한 테스트 및 버전 관리 자동화를 통해 DevOps 환경에 대한 이해도 함께 높일 수 있었습니다. 또한 OpenAI API를 적용해 AI 기반 서비스 개발도 직접 경험해보며, 단순한 구현을 넘어 실제 사용자에게 가치를 줄 수 있는 AI 활용 방식에 대해 고민할 수 있는 좋은 기회가 되었습니다.|

<br>

