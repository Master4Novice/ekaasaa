FROM eclipse-temurin:17.0.16_8-jre-jammy

RUN groupadd --system ekaasaa \
    && useradd --system --gid ekaasaa --no-create-home ekaasaa \
    && install -d -o ekaasaa -g ekaasaa /ekaasaa-service

WORKDIR /ekaasaa-service
COPY --chown=ekaasaa:ekaasaa target/ekaasaa-0.0.1-SNAPSHOT.jar ekaasaa-service.jar

EXPOSE 8082

USER ekaasaa:ekaasaa
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "ekaasaa-service.jar"]
