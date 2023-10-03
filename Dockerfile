FROM debian:latest
LABEL maintainer="Efimtsev Stanislav Maksimovich IKBO-24-20"
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME

RUN apt update && apt install -y default-jre default-jdk

COPY build.gradle settings.gradle $APP_HOME

RUN apt install -y curl
RUN curl -L https://services.gradle.org/distributions/gradle-7.4-bin.zip -o gradle-7.4-bin.zip
RUN apt install -y unzip

RUN mkdir /opt/gradle
ENV GRADLE_HOME=/opt/gradle
RUN unzip -d ${GRADLE_HOME} gradle-7.4-bin.zip
ENV PATH=$PATH:$GRADLE_HOME/gradle-7.4/bin
RUN gradle -v
COPY . .
RUN gradle build
    
ENV ARTIFACT_NAME=server-0.0.1-SNAPSHOT.jar

RUN apt install -y wget
RUN mkdir filesStorage && wget -P $APP_HOME/filesStorage https://www.mirea.ru/upload/medialibrary/80f/MIREA_Gerb_Colour.png

ENV POSTGRES_DB_PORT="5432"
    
EXPOSE 8080
ENTRYPOINT exec java -jar $APP_HOME/build/libs/$ARTIFACT_NAME
ONBUILD RUN echo "Сборка и запуск произведены. Автор: Ефимцев Станислав Максимович"