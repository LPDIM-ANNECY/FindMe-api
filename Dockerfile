ARG JAVA_VERSION=8
# ----------------- Build stage --------------------

# Open JDK for building here
FROM openjdk:${JAVA_VERSION}-jdk as building-stage

# You can pass compile time environment variable for gradle or your project
ENV PROJECT_ENV=prod
ENV DB_URI="jdbc:postgresql://localhost:5432/postgres?pgsql=postgres&user=postgres&password=442442*&lc_messages=en_US.UTF-8"
ENV PORT=8081

# Some useful data for future debugging maybe ?
LABEL java_version=${JAVA_VERSION}

COPY . /src
WORKDIR /src

# Executing our previously configured shadowJar
# RUN ./gradlew test
RUN chmod ugo+x gradlew
RUN ./gradlew --no-daemon shadowJar


# ----------------- Run stage --------------------

# Open JRE for running here, much smaller image to upload (70mb~ instead of 200mb~+)
FROM openjdk:${JAVA_VERSION}-jre as runner-stage

# Good practice to put some labeling here it will be seen onto the registry interface
# LABEL git="https://gitlab.agoods.fr/test.git"

# Passing environment variable -> you define this kind of behavior
ENV PROJECT_ENV=prod
#ENV DB_URI="jdbc:postgresql://localhost:5433/spaceteam?pgsql=postgres&user=admin&password=spaceteam*&lc_messages=en_US.UTF-8"
ENV DB_URI="jdbc:postgresql://localhost:5432/postgres?pgsql=postgres&user=postgres&password=442442*&lc_messages=en_US.UTF-8"

ENV PORT=8081

# renaming + moving the "nameOfYourJar" fat jar file to a `run.jar` for reuse purpose
COPY --from=building-stage /src/build/libs/shadowJarBuild.jar /bin/runner/run.jar
COPY --from=building-stage /src/static/ /bin/runner/static/
COPY --from=building-stage /src/documentation/ /bin/runner/documentation/

WORKDIR /bin/runner

# We pass here some JVM specific flags like logging configuration
CMD ["java", "-jar","run.jar"]