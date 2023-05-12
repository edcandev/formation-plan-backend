package dev.edcan.dualplansgenerator.services;

import dev.edcan.dualplansgenerator.models.StudentExcelResponse;
import dev.edcan.dualplansgenerator.models.Subject;
import dev.edcan.dualplansgenerator.utils.FileUploadUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class ExcelManagementService {

    @Autowired
    FileUploadUtil fileUploadUtil;

    public ExcelManagementService() {
        System.out.println("SERVICIO DE EXCELES");
    }

    public StudentExcelResponse getStudentExcelInfo(String filename) {
        Path folderPath =  fileUploadUtil.getFolderPath(filename);
        Path filePath = Path.of(String.valueOf(folderPath), filename);
        Workbook workbook;
        try {
            FileInputStream file = new FileInputStream(new File(filePath.toUri()));
            workbook = new XSSFWorkbook(file);
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
        Sheet sheet = workbook.getSheetAt(0);


        // Obteniendo los datos de manera manual



        System.out.println(filePath);


        StudentExcelResponse response = new StudentExcelResponse();

        response.setStudentId(getStringDataByRowandCell(sheet,10, 2)); // Matrícula
        response.setFirstSurname(getStringDataByRowandCell(sheet,12, 2)); // Apellido Paterno
        response.setLastSurname(getStringDataByRowandCell(sheet,14,2));
        response.setName(getStringDataByRowandCell(sheet,16,2));
        response.setIeMentor(getStringDataByRowandCell(sheet,26,2));
        // System.out.println(response.toString());
        ArrayList<Subject> subjects = new ArrayList<>();
        for(int i = 53; i < 67; i++) { // Iterador de materias
            Integer numericValue = getNumericDataByRowandCell(sheet,i,1);
            if(numericValue > 0) {
                Subject currentSubject = new Subject(
                        String.valueOf(numericValue),
                        getStringDataByRowandCell(sheet, i, 2),
                        getStringDataByRowandCell(sheet, i, 3));
                System.out.println(currentSubject.toString());
                subjects.add(currentSubject);
            }
        }
        response.setSubjectList(subjects);
        return response;
    }

    public String getStringDataByRowandCell(Sheet sheet, int r, int c) {
        return sheet.getRow(r).getCell(c).getRichStringCellValue().getString();
    }
    public Integer getNumericDataByRowandCell(Sheet sheet, int r, int c) {
        return (Integer) (int) sheet.getRow(r).getCell(c).getNumericCellValue();
    }
}
