#Made by Pedro Ivan
FROM maven:3.8.1-openjdk-17-slim AS build

# Instalar dependencias y herramientas necesarias
WORKDIR /app
RUN apt-get update -y && apt-get upgrade -y \
    && apt-get install -y git wget nano

# Clonar el repositorio de Dual
WORKDIR /root
RUN git clone https://github.com/edcan-dev/Dual.git

# Descargar paquetes de Microsoft
WORKDIR /app
RUN wget https://packages.microsoft.com/config/debian/11/packages-microsoft-prod.deb -O packages-microsoft-prod.deb \
    && dpkg -i packages-microsoft-prod.deb \
    && rm packages-microsoft-prod.deb

# Instalar el tiempo de ejecución de ASP.NET Core 6.0
RUN apt-get update && apt-get install -y aspnetcore-runtime-6.0

# Clonar el repositorio de formation-plan-backend-prod
RUN git clone https://github.com/fluffy1720/formation-plan-backend-prod.git

# Instalar certbot
RUN apt-get install -y certbot

# Configurar variables de entorno para Java
WORKDIR /app/formation-plan-backend-prod
ENV JAVA_HOME=/usr/local/openjdk-17
ENV PATH=${JAVA_HOME}/bin:${PATH}

#Compilar el backend
RUN mvn clean package

# Instalar Nginx 
RUN apt-get install -y nginx 

# Configurar Nginx
COPY nginx.conf /etc/nginx/nginx.conf
COPY default.conf /etc/nginx/conf.d/default.conf
RUN useradd -r nginx

# Copiar el script de generación y configuración del certificado
COPY generate_cert.sh /root/generate_cert.sh

# Dar permisos de ejecución al script
RUN chmod +x /root/generate_cert.sh

# Exponer puertos
EXPOSE 443
EXPOSE 80

# Comando de inicio
CMD bash -c "/root/generate_cert.sh"

