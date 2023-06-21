#!/bin/bash
#Iván
# Script para monitorear y eliminar archivos y carpetas en un directorio después de 30 segundos.

# Directorio a monitorear
directorio="/root/Dual/Reports"

# Función para eliminar archivo y carpeta después de 25 segundos
eliminar_d30() {
  archivo="$1"
  carpeta="${archivo%.*}"
  sleep 30
  if [ -f "$directorio/$archivo" ]; then
    rm -f "$directorio/$archivo"
  fi
  if [ -d "$directorio/$carpeta" ]; then
    rm -rf "$directorio/$carpeta"
  fi
}

# Monitorear eventos en el directorio
inotifywait -m -e create "$directorio" | while read -r directory event file
do
  if [[ "$file" =~ ^[0-9]+\.zip$ ]]; then
    eliminar_d30 "$file" &
  fi
  if [[ "$file" =~ ^[0-9]+$ ]]; then
    eliminar_d30 "$file.zip" &
  fi
done
