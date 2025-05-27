## Node.js LTS 버전이 설치된 Alpine Linux 이미지를 가져온다.
#FROM node:lts-alpine
#
## 컨테이너 내부의 작업 디렉토리를 /app으로 설정한다.
#WORKDIR /app
#
## 현재 디렉토리 내의 모든 파일과 폴더를 컨테이너의 /app 디렉토리로 복사한다.
#COPY . .
#
## npm을 사용하여 프로젝트의 종속성을 설치한다.
#RUN npm install
#
## 컨테이너가 시작될 때 npm run dev 명령을 실행한다.
## --는 이후의 인자를 npm 스크립트에 전달하기 위한 것이다.
## --host 0.0.0.0는 서버가 모든 네트워크 인터페이스에서 접근 가능하도록 설정한다.
#CMD ["npm", "run", "dev", "--", "--host", "0.0.0.0"]

FROM node:lts-alpine AS build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# nginx 이미지를 사용하여 프로덕션 스테이지를 구성한다.
FROM nginx:stable-alpine AS production-stage
# 빌드 스테이지에서 생성된 정적 파일들을 Nginx의 기본 웹 서버 디렉토리로 복사한다.
# 이렇게 하면 Nginx가 Vue.js 애플리케이션의 빌드된 파일들을 서빙할 수 있게 된다.
COPY --from=build-stage /app/dist /usr/share/nginx/html

# 로컬 디렉토리의 nginx.conf 파일을 Nginx의 설정 디렉토리로 복사한다.
# 이 설정 파일은 Nginx의 동작을 커스터마이즈하는데 사용된다.
# 예를 들어, 라우팅 규칙, SSL 설정, 로깅 등을 정의할 수 있다.
COPY ./nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]