# 使用官方 OpenJDK 镜像（兼容 JDK 21）
FROM eclipse-temurin:21-jdk

# 设置工作目录
WORKDIR /app

# 拷贝 jar 包到容器
COPY target/steam_discount-0.0.1-SNAPSHOT.jar app.jar

# 启动应用
ENTRYPOINT ["java", "-jar", "app.jar"]
