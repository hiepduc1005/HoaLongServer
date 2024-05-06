# Sử dụng hình ảnh OpenJDK Java 11 làm base image
FROM openjdk:11-jdk-slim AS build

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép tệp pom.xml và sao chép các tệp khác vào thư mục hiện tại
COPY ./pom.xml .

RUN apt-get update && \
    apt-get install -y maven

# Tải các phụ thuộc Maven (dependencies) và tạo ra một layer riêng
RUN mvn dependency:go-offline -B

# Sao chép mã nguồn và tạo layer khác
COPY ./src ./src

# Xây dựng ứng dụng
RUN mvn package -DskipTests

# Được sử dụng một lần nữa để giảm kích thước của hình ảnh cuối cùng
FROM openjdk:11-jre-slim

# Sao chép tệp JAR đã được xây dựng từ hình ảnh trước đó
COPY --from=build /app/target/*.jar /app/app.jar

# Khai báo port mà ứng dụng sẽ lắng nghe trên
EXPOSE 8080

# Khai báo lệnh chạy khi container được khởi chạy
CMD ["java", "-jar", "/app/app.jar"]
