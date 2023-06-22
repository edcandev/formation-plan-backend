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

#Funcion para hacer git clone necesarios (DUAL y JAR)
git_clones(){
  echo "Ejecutando script que hace los git clone necesarios"
  #cd /root/Dual
  #git pull origin main
  #echo "Clon de "Dual" realizado con exito"
  cd /app
  git clone https://github.com/fluffy1720/formation-plan-backend-prod.git
  echo "Clon de "formation-plan-backend-prod" realizado con exito"
  cd /app/formation-plan-backend-prod
  mvn clean package
  echo "Compilacion del proyecto realizada con exito"
  sleep 2
}

# Función para agregar líneas al archivo sources.list
agregar_lineas() {
  echo "Ejecutando script para agregar líneas"
  sh -c "echo '$l1' >> /etc/apt/sources.list"
  sh -c "echo '$l2' >> /etc/apt/sources.list"
  sh -c "echo '$l3' >> /etc/apt/sources.list"
  sh -c "echo '$l4' >> /etc/apt/sources.list"
  sh -c "echo '$l5' >> /etc/apt/sources.list"
  sh -c "echo '$l6' >> /etc/apt/sources.list"
  sh -c "echo '$l7' >> /etc/apt/sources.list"
  sh -c "echo '$l8' >> /etc/apt/sources.list"
  echo "Se han agregado las líneas al archivo sources.list correctamente."
  sleep 2
  apt update && apt upgrade -y
  apt install ttf-mscorefonts-installer -y
  apt-get install libgdiplus -y
  echo "Se han instalado las fuentes y librerias necesarias para la generacion de reportes."
  sleep 2
}

# Función para verificar la existencia del certificado y configurarlo en Nginx
verificar_certificado_valido() {
  echo "Ejecutando el script de verificación de certificados"
  sleep 2
  local cert_file="/root/Dual/live/$domain/fullchain.pem"
  local key_file="/root/Dual/live/$domain/privkey.pem"
  echo "Configurando directorios y Nginx"
  sleep 2
  mkdir -p "/etc/letsencrypt/live/$domain"
  cp "$cert_file" "/etc/letsencrypt/live/$domain/"
  cp "$key_file" "/etc/letsencrypt/live/$domain/"
  if openssl x509 -checkend $((30*24*60*60)) -noout -in "/etc/letsencrypt/live/$domain/fullchain.pem"; then
    echo "Comprobando si el certificado debe renovarse"
    sleep 2
    echo "El certificado no necesita renovación"
  else
    echo "Renovando certificados"
    sleep 2
    certbot renew
    rm /root/Dual/live/$domain/fullchain.pem
    rm /root/Dual/live/$domain/privkey.pem
    cp "/etc/letsencrypt/live/$domain/fullchain.pem" "/root/Dual/live/$domain/"
    cp "/etc/letsencrypt/live/$domain/privkey.pem" "/root/Dual/live/$domain/"
  fi
  echo "Configuración de certificados completada"
  sleep 2
}

#Watchdog que verifica la existencia de archivos .zip
ejec_copy_reports(){
  bash /scripts/copiar_planes.sh &
  echo "El watchdog que copia los .zip de alumnos está activo"
}

#Watchdog que verifica la existencia de residuos de planes de alumnos
ejec_elim(){
  bash /scripts/eliminar.sh &
  echo "El watchdog que elimina los residuos de generacion de planes está activo"
}

# Función para ejecutar la aplicación Java
ejecutar_jar() {
  echo "Ejecutando la función que inicializa el backend"
  sleep 2
  nginx -g 'daemon off;' &
  #systemctl restart nginx
  cd /app/formation-plan-backend-prod/target
  java -jar dual-plans-generator-0.0.1-SNAPSHOT.jar
}

#LLamar a la funcion para hacer los git-clone necesarios
git_clones

# Llamar a la función para agregar líneas al archivo sources.list
agregar_lineas

# Llamar a la función para verificar la existencia del certificado y configurarlo en Nginx
verificar_certificado_valido

#Llamar Watchdog que verifica la existencia de .zips
ejec_copy_reports

#Llamar Watchdog que verifica la existencia de residuos de planes de alumnos
ejec_elim

# Llamar a la función para ejecutar la aplicación Java
ejecutar_jar