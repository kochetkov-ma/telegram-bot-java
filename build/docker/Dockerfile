FROM openjdk:11.0.16-jre-slim-buster
LABEL maintainer=max
WORKDIR /app
COPY libs libs/
COPY classes classes/
ENTRYPOINT ["java", "-cp", "/app/resources:/app/classes:/app/libs/*", "ru.brewcode.bot.Main"]
EXPOSE 8080
