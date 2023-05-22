FROM  maven:3.8.1-openjdk-17-slim AS build
WORKDIR /app
RUN apt-get update -y && apt-get upgrade -y && apt-get install git -y && apt install wget -y 
WORKDIR /root
RUN git clone https://github.com/edcan-dev/Dual.git 
WORKDIR /app
RUN wget https://packages.microsoft.com/config/debian/11/packages-microsoft-prod.deb -O packages-microsoft-prod.deb && dpkg -i packages-microsoft-prod.deb && rm packages-microsoft-prod.deb
RUN apt-get update &&  apt-get install -y aspnetcore-runtime-6.0
RUN git clone https://github.com/fluffy1720/formation-plan-backend-prod.git
WORKDIR /app/formation-plan-backend-prod
#COPY . .
ENV JAVA_HOME=/usr/local/openjdk-17
ENV PATH=${JAVA_HOME}/bin:${PATH}
RUN mvn clean package
EXPOSE 8080
CMD java -jar target/dual-plans-generator-0.0.1-SNAPSHOT.jar c 