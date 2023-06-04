#!/bin/bash
# Made by Pedro Ivan
domain="generadordeplanes.centralus.azurecontainer.io"
l1="deb http://deb.debian.org/debian buster main contrib non-free"
l2="deb-src http://deb.debian.org/debian buster main contrib non-free"
l3="deb http://deb.debian.org/debian buster-updates main contrib non-free"
l4="deb-src http://deb.debian.org/debian buster-updates main contrib non-free"
l5="deb http://deb.debian.org/debian buster-backports main contrib non-free"
l6="deb-src http://deb.debian.org/debian buster-backports main contrib non-free"
l7="deb http://security.debian.org/debian-security/ buster/updates main contrib non-free"
l8="deb-src http://security.debian.org/debian-security/ buster/updates main contrib non-free"

# Función para agregar líneas al archivo sources.list
agregar_lineas() {
  echo "Ejecutando script para agregar líneas"
  sleep 3
  sh -c "echo '$l1' >> /etc/apt/sources.list"
  sh -c "echo '$l2' >> /etc/apt/sources.list"
  sh -c "echo '$l3' >> /etc/apt/sources.list"
  sh -c "echo '$l4' >> /etc/apt/sources.list"
  sh -c "echo '$l5' >> /etc/apt/sources.list"
  sh -c "echo '$l6' >> /etc/apt/sources.list"
  sh -c "echo '$l7' >> /etc/apt/sources.list"
  sh -c "echo '$l8' >> /etc/apt/sources.list"
  echo "Se han agregado las líneas al archivo sources.list correctamente."
  sleep 3
  apt update && apt upgrade -y
  apt install ttf-mscorefonts-installer -y
  apt-get install libgdiplus -y
  echo "Se han instalado las fuentes y librerias necesarias para la generacion de reportes."
  sleep 3
}

# Función para verificar la existencia del certificado y configurarlo en Nginx
verificar_certificado_valido() {
  echo "Ejecutando el script de verificación de certificados"
  sleep 3
  local cert_file="/app/fpb-share/live/$domain/fullchain.pem"
  local key_file="/app/fpb-share/live/$domain/privkey.pem"
  echo "Configurando directorios y Nginx"
  sleep 3
  mkdir -p "/etc/letsencrypt/live/$domain"
  cp "$cert_file" "/etc/letsencrypt/live/$domain/"
  cp "$key_file" "/etc/letsencrypt/live/$domain/"
  if openssl x509 -checkend $((30*24*60*60)) -noout -in "/etc/letsencrypt/live/$domain/fullchain.pem"; then
    echo "Comprobando si el certificado debe renovarse"
    sleep 3
    echo "El certificado no necesita renovación"
  else
    echo "Renovando certificados"
    sleep 3
    certbot renew
    cp "/etc/letsencrypt/live/$domain/fullchain.pem" "/app/fpb-share/live/$domain/"
    cp "/etc/letsencrypt/live/$domain/privkey.pem" "/app/fpb-share/live/$domain/"
  fi
  echo "Configuración de certificados completada"
  sleep 3
}

# Función para ejecutar la aplicación Java
ejecutar_jar() {
  echo "Ejecutando la función que inicializa el backend"
  sleep 5
  nginx -g 'daemon off;' &
  systemctl restart nginx
  cd /app/formation-plan-backend-prod/target
  java -jar dual-plans-generator-0.0.1-SNAPSHOT.jar
}

# Llamar a la función para agregar líneas al archivo sources.list
agregar_lineas

# Llamar a la función para verificar la existencia del certificado y configurarlo en Nginx
verificar_certificado_valido

# Llamar a la función para ejecutar la aplicación Java
ejecutar_jar