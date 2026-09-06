FROM gradle:ubi9 as builder
COPY * /app

WORKDIR /app
RUN gradle clean build

FROM quay.io/wildfly/wildfly:latest-jdk17

RUN microdnf install -y postgresql-server openldap-server supervisor
COPY --from=builder /app/build/*.war /opt/jboss/wildfly/

COPY supervisord.conf /etc/supervisor.conf