package dev.edcan.dualplansgenerator.services;

import dev.edcan.dualplansgenerator.models.PlanGeneratorRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class DLLExecutorServiceImpl implements IDLLExecutorService {



    @Override
    public void generatePlan(PlanGeneratorRequest planGeneratorRequest) {



        try {
            ProcessBuilder processBuilder = new ProcessBuilder("dotnet", "Tese.EducacionDual.Gestor.Etapas.dll", "E:Anexo5_1", "PE:TES5061300046", "MED:201920732", "FE:07/03/2022","PER:2023-1");
            //ProcessBuilder processBuilder = new ProcessBuilder("dotnet");
            Path dllDirectory = Paths.get(System.getProperty("user.dir")).resolve("Dual");
            processBuilder.directory(new File(dllDirectory.toUri()));

            //processBuilder.directory(dllDirectory.toFile());
            //Process proc = processBuilder.start();
            String output = IOUtils.toString(processBuilder.start().getInputStream(), StandardCharsets.UTF_8);
            System.out.println(output);


        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // dotnet Tese.EducacionDual.Gestor.Etapas.dll E:Anexo5_1 PE:TES5061300046 MED:201910642 FE:07/03/2022 PER:2023-1



}
