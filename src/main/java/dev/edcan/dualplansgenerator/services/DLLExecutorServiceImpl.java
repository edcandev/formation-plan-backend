package dev.edcan.dualplansgenerator.services;

import dev.edcan.dualplansgenerator.models.PlanGeneratorRequest;
import dev.edcan.dualplansgenerator.utils.IFileUploadUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

@Component
public class DLLExecutorServiceImpl implements IDLLExecutorService {

    @Autowired
    IFileUploadUtil fileUploadUtil;



    @Override
    public boolean generatePlan(PlanGeneratorRequest planGeneratorRequest) {

        try {

            String studentId = planGeneratorRequest.getStudentId();
            String period = planGeneratorRequest.getPeriod();
            String generationDateString = planGeneratorRequest.getGenerationDateString();

            ProcessBuilder processBuilder = new ProcessBuilder("dotnet", "Tese.EducacionDual.Gestor.Etapas.dll", "E:Anexo5_1", "PE:TES5061300046", "MED:" + studentId, "FE:" + generationDateString,"PER:" + period);

            Path dllDirectory = Paths.get(System.getProperty("user.dir")).resolve("Dual");
            processBuilder.directory(new File(dllDirectory.toUri()));

            Process processResult = processBuilder.start();
            //processResult.getOutputStream();

            InputStream processStdOutput = processResult.getInputStream();
            Reader r = new InputStreamReader(processStdOutput);
            BufferedReader br = new BufferedReader(r);

            while (br.readLine() != null) { System.out.println(br.readLine()); }

            File reportDirectory = new File(fileUploadUtil.getReportFolderPath(planGeneratorRequest.getStudentFileName()).toUri());

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

    // dotnet Tese.EducacionDual.Gestor.Etapas.dll E:Anexo5_1 PE:TES5061300046 MED:201910642 FE:07/03/2022 PER:2023-1



}
