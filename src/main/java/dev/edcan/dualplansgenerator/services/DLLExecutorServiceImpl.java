package dev.edcan.dualplansgenerator.services;

import dev.edcan.dualplansgenerator.models.PlanGeneratorRequest;
import dev.edcan.dualplansgenerator.utils.IFileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zeroturnaround.zip.ZipUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class DLLExecutorServiceImpl implements IDLLExecutorService {

    @Autowired
    IFileUploadUtil fileUploadUtil;



    @Override
    public boolean generatePlan(PlanGeneratorRequest planGeneratorRequest) {

        try {

            String studentId = planGeneratorRequest.getStudentId();
            String period = planGeneratorRequest.getPeriod();
            String generationDateString = planGeneratorRequest.getGenerationDateString();

            File reportDirectory = new File(fileUploadUtil.getStudentReportFolderPath(planGeneratorRequest.getStudentFileName()).toUri());


            if(reportDirectory.exists()) return true;

            System.out.println(studentId + " - " + generationDateString + " - " + period);

            ProcessBuilder processBuilder = new ProcessBuilder("dotnet", "Tese.EducacionDual.Gestor.Etapas.dll", "E:Anexo5_1", "PE:TES5061300046", "MED:" + studentId, "FE:" + generationDateString,"PER:" + period);

            Path dllDirectory = fileUploadUtil.getGeneratorProjectPath();
            processBuilder.directory(new File(dllDirectory.toUri()));

            Process processResult = processBuilder.start();
            //processResult.getOutputStream();

            InputStream processStdOutput = processResult.getInputStream();
            Reader r = new InputStreamReader(processStdOutput);
            BufferedReader br = new BufferedReader(r);

            while (br.readLine() != null) { System.out.println(br.readLine()); }


            if(reportDirectory.exists()) {
                System.out.println("REPORTES CREADOS PARA:".concat(planGeneratorRequest.getStudentId()));

                Files.walk(fileUploadUtil.getDataFolderPath(planGeneratorRequest.getStudentFileName()))
                        .sorted(Comparator.reverseOrder())
                        .forEach( path -> {
                            try {
                                Files.delete(path);
                            } catch(IOException ignored){}
                        });
                return true;
            } else {
                System.out.println("==================== ERROR DE GENERACIÓN PARA:".concat(planGeneratorRequest.getStudentId()).concat(" ===================="));
                return false;
            }
        } catch(Exception e) {
            return false;
        }
    }

    @Override
    public void zipPlan(String studentId) {

        Path reportsFolder = fileUploadUtil.getStudentReportFolderPath(studentId);
        File reportsFile = reportsFolder.toFile();

        Path zipPath = fileUploadUtil.getGeneratorProjectPath().resolve("Reports").resolve(studentId.concat(".zip"));
        File zipFile = zipPath.toFile();
        try {
            if(! Files.exists(zipFile.toPath())) {
                Files.createFile  (zipFile.toPath()) ;
                ZipUtil.pack(reportsFile, zipFile);
            }
        } catch(IOException e){}
    }

    private static void zipSingleFile(File file, String zipFileName) {
        try {
            //create ZipOutputStream to write to the zip file
            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            //add a new Zip Entry to the ZipOutputStream
            ZipEntry ze = new ZipEntry(file.getName());
            zos.putNextEntry(ze);
            //read the file and write to ZipOutputStream
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }

            //Close the zip entry to write to zip file
            zos.closeEntry();
            //Close resources
            zos.close();
            fis.close();
            fos.close();
            System.out.println(file.getCanonicalPath()+" is zipped to "+zipFileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // dotnet Tese.EducacionDual.Gestor.Etapas.dll E:Anexo5_1 PE:TES5061300046 MED:201910642 FE:07/03/2022 PER:2023-1



}
