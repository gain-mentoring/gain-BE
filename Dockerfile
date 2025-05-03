# 1단계: Gradle이 포함된 이미지에서 애플리케이션을 빌드
FROM gradle:8.5-jdk17 AS build

# 프로젝트 전체를 컨테이너로 복사하고 작업 디렉토리 설정
COPY --chown=gradle:gradle . /app
WORKDIR /app

# 테스트를 제외한 빌드 수행 (결과물은 build/libs/*.jar)
RUN gradle build -x test

# 2단계: JDK만 포함된 경량 이미지에서 애플리케이션 실행
FROM eclipse-temurin:17-jdk

# 애플리케이션 실행 위치 설정
WORKDIR /app

# 빌드 결과물인 JAR 파일 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 애플리케이션 실행 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]