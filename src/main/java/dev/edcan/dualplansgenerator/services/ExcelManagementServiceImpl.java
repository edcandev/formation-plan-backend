package dev.edcan.dualplansgenerator.services;

import dev.edcan.dualplansgenerator.models.Materias;
import dev.edcan.dualplansgenerator.models.StudentExcelResponse;
import dev.edcan.dualplansgenerator.models.Subject;
import dev.edcan.dualplansgenerator.repositories.IMateriasRepository;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelManagementServiceImpl implements IExcelManagementService {

    @Autowired
    IFileUploadService fileUploadService;
    @Autowired
    IMateriasRepository materiasRepository;

    public StudentExcelResponse getStudentExcelInfo(String filename) {

        Path folderPath =  fileUploadService.getFolderPath(filename);
        Path filePath = Path.of(String.valueOf(folderPath), filename);
        Workbook workbook;
        try {
            FileInputStream file = new FileInputStream(new File(filePath.toUri()));
            workbook = new XSSFWorkbook(file);
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
        Sheet sheet = workbook.getSheetAt(0);

        ArrayList<Subject> subjects = new ArrayList<>();
        for(int i = 53; i < 67; i++) { // Iterador de materias
            Integer intSubjectId = getNumericDataByRowandCell(sheet,i,1);

            if(intSubjectId > 0) {

                String strSubjectId = String.valueOf(intSubjectId);

                Subject currentSubject = new Subject()
                        .withSubjectId(strSubjectId)
                        .withPeriod(getStringDataByRowandCell(sheet, i, 2))
                        .withPartial(getStringDataByRowandCell(sheet, i, 3)).withValid(isAValidSubject(strSubjectId)).build();
                subjects.add(currentSubject);
            }
        }

        System.out.println(subjects);
        StudentExcelResponse response = new StudentExcelResponse()
                .withStudentId(getStringDataByRowandCell(sheet,10, 2))
                .withFirstSurname(getStringDataByRowandCell(sheet,12, 2))
                .withLastSurname(getStringDataByRowandCell(sheet,14,2))
                .withName(getStringDataByRowandCell(sheet,16,2))
                .withfileName(filename)
                .withIeMentor(getStringDataByRowandCell(sheet,26,2))
                .withSubjectList(subjects)
                .build();
        return response;
    }

    @Override
    public boolean existsByFilename(String filename) {
        Path filePath = Path.of(String.valueOf(fileUploadService.getFolderPath(filename)), filename);
        return filePath.toFile().exists();
    }


    @Override
    public boolean isAValidSubject(String subjectId) {
        for(Materias materia : materiasRepository.getAllMaterias()) {
            if(subjectId.equals(materia.getClave())) return true;
        }
        return false;
    }


    private String getStringDataByRowandCell(Sheet sheet, int r, int c) throws NullPointerException {
        return sheet.getRow(r).getCell(c).getRichStringCellValue().getString();
    }
    private Integer getNumericDataByRowandCell(Sheet sheet, int r, int c) throws NullArgumentException {
        return (Integer) (int) sheet.getRow(r).getCell(c).getNumericCellValue();
    }
}
