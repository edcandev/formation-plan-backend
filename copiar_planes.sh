#!/bin/bash

# Directorio a monitorear
directorio1="/root/Dual/data"

# Obtener la fecha actual
fecha=$(date +%Y%m%d)

# Directorio de origen del archivo .zip
directorio_origen="/root/Dual/Reports"

# Directorio de destino del archivo .zip
directorio_destino="/app/fpb-share/Planes"

# Monitorear el directorio1 constantemente
inotifywait -m -r -e create "$directorio1" --format '%w%f' |
while read archivo
do
    # Verificar si se ha creado una nueva carpeta con el nombre de la matrícula del usuario
    if [[ -d "$archivo" ]]; then
        matricula=$(basename "$archivo")
        
        # Obtener el nombre del mentor del alumno del archivo .xlsx
        archivo_xlsx=$(find "$archivo" -maxdepth 1 -type f -name "*.xlsx")
        mentor=$(xlsx2csv "$archivo_xlsx" | awk -F',' 'NR==27 {print $3}')
        
        # Directorio de destino específico para el mentor y la fecha actual
        directorio_destino_mentor="$directorio_destino/$mentor/$fecha"
        
        # Crear el directorio de destino si no existe
        mkdir -p "$directorio_destino_mentor"
        
        # Copiar el archivo .xlsx al directorio de destino
        cp "$archivo_xlsx" "$directorio_destino_mentor"
        
        echo "Se ha copiado el archivo $archivo_xlsx al directorio $directorio_destino_mentor"
    fi
done
