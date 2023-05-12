package dev.edcan.dualplansgenerator.services;

import dev.edcan.dualplansgenerator.models.StudentExcelResponse;
import dev.edcan.dualplansgenerator.models.Subject;
import dev.edcan.dualplansgenerator.utils.FileUploadUtil;
import org.apache.commons.math3.exception.NullArgumentException;
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

        //System.out.println(filePath);

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
        StudentExcelResponse response = new StudentExcelResponse()
                .withStudentId(getStringDataByRowandCell(sheet,10, 2))
                .withFirstSurname(getStringDataByRowandCell(sheet,12, 2))
                .withLastSurname(getStringDataByRowandCell(sheet,14,2))
                .withName(getStringDataByRowandCell(sheet,16,2))
                .withfileName(filename)
                .withIeMentor(getStringDataByRowandCell(sheet,26,2))
                .withSubjectList(subjects)
                .build();

        System.out.println("=============>" + response.toString());
        return response;
    }

    public String getStringDataByRowandCell(Sheet sheet, int r, int c) throws NullPointerException {
        return sheet.getRow(r).getCell(c).getRichStringCellValue().getString();
    }
    public Integer getNumericDataByRowandCell(Sheet sheet, int r, int c) throws NullArgumentException {
        return (Integer) (int) sheet.getRow(r).getCell(c).getNumericCellValue();
    }
}
