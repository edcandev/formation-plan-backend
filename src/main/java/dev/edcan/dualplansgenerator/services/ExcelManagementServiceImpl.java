package dev.edcan.dualplansgenerator.services;

import dev.edcan.dualplansgenerator.models.IEMentor;
import dev.edcan.dualplansgenerator.models.Materias;
import dev.edcan.dualplansgenerator.models.StudentExcelResponse;
import dev.edcan.dualplansgenerator.models.Subject;
import dev.edcan.dualplansgenerator.repositories.IMateriasRepository;
import dev.edcan.dualplansgenerator.utils.IFileUploadUtil;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.*;

@Service
public class ExcelManagementServiceImpl implements IExcelManagementService {

    @Autowired
    IFileUploadService fileUploadService;
    @Autowired
    IFileUploadUtil fileUploadUtil;
    @Autowired
    IMateriasRepository materiasRepository;
    static List<IEMentor> mentorList;

    public ExcelManagementServiceImpl(IFileUploadUtil fileUploadUtil) {
        // Busca los datos de los mentores al iniciar
        System.out.println("OBTENIENDO MENTORES Y ALUMNOS...");
        mentorList = populateMentorList(fileUploadUtil);
    }

    public StudentExcelResponse getStudentExcelInfo(String filename) {

        Path folderPath =  fileUploadUtil.getDataFolderPath(filename);
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

        try {

            for(int i = 53; i < 67; i++) { // Iterador de materias
                Integer intSubjectId = getNumericDataByRowandCell(sheet, i, 1);

                if (intSubjectId > 0) {

                    String strSubjectId = String.valueOf(intSubjectId);

                    Subject currentSubject = new Subject()
                            .withSubjectId(strSubjectId)
                            .withSubjectName(materiasRepository.getMateriaById(strSubjectId).getNombre())
                            .withPeriod(getStringDataByRowandCell(sheet, i, 2))
                            .withPartial(getStringDataByRowandCell(sheet, i, 3))
                            .withValid(isAValidSubject(strSubjectId))
                            .build();
                    subjects.add(currentSubject);
                }
            }

        } catch (Exception e) {
            System.out.println("=== ERROR EN LA CÉDULA ===");
            return null;
        }

        // System.out.println(subjects);
        return new StudentExcelResponse()
                .withStudentId(getStudentIdString(sheet,10, 2))
                .withFirstSurname(getStringDataByRowandCell(sheet,12, 2))
                .withLastSurname(getStringDataByRowandCell(sheet,14,2))
                .withName(getStringDataByRowandCell(sheet,16,2))
                .withfileName(filename)
                .withIeMentor(getStringDataByRowandCell(sheet,26,2))
                .withSubjectList(subjects)
                .build();
    }

    @Override
    public List<IEMentor> getIEMentors() {
        return populateMentorList(fileUploadUtil);
    }

    private List<IEMentor> populateMentorList(IFileUploadUtil fileUploadUtil) {

        Path alumnosMentoresFilePath = fileUploadUtil.getGeneratorProjectPath().resolve("data").resolve("AlumnosMentores.xlsx");

        Workbook workbook;

        try {
            FileInputStream alumnosMentoresFile = new FileInputStream(alumnosMentoresFilePath.toFile());
            workbook = new XSSFWorkbook(alumnosMentoresFile);
        } catch(IOException ioException){
            System.out.println("ARCHIVO DE ALUMNOS Y MENTORES NO ENCONTRADO");
            return null; }

        List<IEMentor> mentorList = new ArrayList<>();

        Sheet sheet = workbook.getSheetAt(0);

        for(Row row : sheet) {
            if(row.getCell(0) != null && row.getRowNum() != 1) {

                IEMentor mentor = new IEMentor();
                String fullName = row.getCell(6).getStringCellValue();
                Double studentId = row.getCell(2).getNumericCellValue();
                Long longStudentId = studentId.longValue();

                if(mentorList.stream().filter(iem -> Objects.equals(iem.getFullName(), fullName)).toList().size() == 0 ) {
                    mentor.setFullName(fullName);
                    mentor.addStudentId(longStudentId.toString());
                    mentorList.add(mentor);

                } else {
                    mentorList.stream()
                            .filter(iem -> Objects.equals(iem.getFullName(), fullName))
                            .findFirst().get().addStudentId(longStudentId.toString());
                }
            }

        }

        Set<String> studentIdsSet;

        for(IEMentor mentor : mentorList) {
            studentIdsSet = new HashSet<>(mentor.getStudentsIds());

            // System.out.println(studentIdsSet);

            mentor.setStudentsIds(studentIdsSet);
        }

        return mentorList;
    }

    @Override
    public boolean existsByFilename(String filename) {
        Path filePath = Path.of(String.valueOf(fileUploadUtil.getDataFolderPath(filename)), filename);
        return filePath.toFile().exists();
    }

    @Override
    public boolean isAValidSubject(String subjectId) {
        for(Materias materia : materiasRepository.getAllMaterias()) {
            if(subjectId.equals(materia.getClave())) return true;
        }
        return false;
    }

    private String getStudentIdString(Sheet sheet, int r, int c) throws NullPointerException {

        String malformedString = String.valueOf(sheet.getRow(r).getCell(c).getNumericCellValue());
        String fixedString = malformedString.replace(".","").substring(0,9);
        System.out.println("============> " + fixedString);
        return  fixedString;
    }

    private String getStringDataByRowandCell(Sheet sheet, int r, int c) throws NullPointerException {
        return sheet.getRow(r).getCell(c).getRichStringCellValue().getString();
    }
    private Integer getNumericDataByRowandCell(Sheet sheet, int r, int c) throws NullArgumentException {
        return (Integer) (int) sheet.getRow(r).getCell(c).getNumericCellValue();
    }
}
