package dev.edcan.dualplansgenerator.services;

import dev.edcan.dualplansgenerator.models.PlanGeneratorRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class DLLExecutorServiceImpl implements IDLLExecutorService {



    @Override
    public void generatePlan(PlanGeneratorRequest planGeneratorRequest) {

        try {

            String studentId = planGeneratorRequest.getStudentId();
            String period = planGeneratorRequest.getPeriod();
            String generationDateString = planGeneratorRequest.getGenerationDateString();

            ProcessBuilder processBuilder = new ProcessBuilder("dotnet", "Tese.EducacionDual.Gestor.Etapas.dll", "E:Anexo5_1", "PE:TES5061300046", "MED:" + studentId, "FE:" + generationDateString,"PER:" + period);

            Path dllDirectory = Paths.get(System.getProperty("user.dir")).resolve("Dual");
            processBuilder.directory(new File(dllDirectory.toUri()));

            Process process = processBuilder.start();
            process.getOutputStream().close();

            InputStream processStdOutput = process.getInputStream();
            Reader r = new InputStreamReader(processStdOutput);
            BufferedReader br = new BufferedReader(r);
            String line;
            while ((line = br.readLine()) != null) {

                if(! line.contains("error") || ! line.contains("ERROR")) {
                    System.out.println(line);
                } else {
                    System.out.println("Error...!");
                    return;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // dotnet Tese.EducacionDual.Gestor.Etapas.dll E:Anexo5_1 PE:TES5061300046 MED:201910642 FE:07/03/2022 PER:2023-1



}
