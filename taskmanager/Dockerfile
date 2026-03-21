# -------------------------------
# 🏗 STAGE 1 — BUILD PHASE
# -------------------------------
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the JAR file
RUN mvn clean package -DskipTests


# -------------------------------
# 🚀 STAGE 2 — RUNTIME PHASE
# -------------------------------
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copy ONLY the built JAR from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Run the application
CMD ["java", "-jar", "app.jar"]
