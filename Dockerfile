FROM openjdk:alpine

ENV APP_HOME /app/

# Jaeger Env  values
# For Linux
# ENV JAEGER_ENDPOINT="http://127.0.0.1:14268/api/traces"
ENV JAEGER_ENDPOINT="http://host.docker.internal:14268/api/traces"
ENV JAEGER_SAMPLER_TYPE=const
ENV JAEGER_SAMPLER_PARAM=1
ENV JAEGER_REPORTER_LOG_SPANS=true


COPY /target/tracing-demo-1.0-SNAPSHOT.jar $APP_HOME

EXPOSE 8080

WORKDIR $APP_HOME

# set env variables when starting the container
CMD java -jar tracing-demo-1.0-SNAPSHOT.jar 
