#!/bin/bash

# Directorio a monitorear
directorio1="/root/Dual/data"

# Obtener la fecha actual
fecha=$(date +%Y%m%d)

# Directorio de origen del archivo .zip
directorio_origen="/root/Dual/Reports"

# Directorio de destino del archivo .zip
directorio_destino="/root/Dual/Planes"
while read archivo
do
    # Verificar si se ha creado una nueva carpeta con el nombre de la matrícula del usuario
    if [[ -d "$archivo" ]]; then
        matricula=$(basename "$archivo")
        echo "matricula $matricula en memoria"
        
        # Obtener el nombre del mentor del alumno del archivo .xlsx
        archivo_xlsx=$(find "$archivo" -maxdepth 1 -type f -name "*.xlsx")
        mentor=$(xlsx2csv "$archivo_xlsx" | awk -F',' 'NR==27 {print $3}' | iconv -f UTF-8 -t ASCII//TRANSLIT | tr 'ñ' 'N')
        
        # Obtener el periodo escolar en curso del archivo .xlsx
        periodo=$(xlsx2csv "$archivo_xlsx" | awk -F',' 'NR==54 {print $3}' | iconv -f UTF-8 -t ASCII//TRANSLIT | tr 'ñ' 'N')
        
        # Directorio de destino específico para el mentor y el periodo escolar
        directorio_destino_mentor="$directorio_destino/$mentor/$periodo"
        
        # Crear el directorio de destino si no existe
        mkdir -p "$directorio_destino_mentor"
        
        # Copiar el archivo .xlsx al directorio de destino
        cp "$archivo_xlsx" "$directorio_destino_mentor"
        echo "Se ha copiado el archivo $archivo_xlsx al directorio $directorio_destino_mentor"

        # Verificar la existencia del archivo .zip
        while [ ! -f "$directorio_origen/$matricula.zip" ]; do
            sleep 1
        done

        # Copiar el archivo .zip al directorio de destino
        cp "$directorio_origen/$matricula.zip" "$directorio_destino_mentor"
        echo "Se ha copiado el archivo $matricula.zip al directorio $directorio_destino_mentor"
    fi
done




