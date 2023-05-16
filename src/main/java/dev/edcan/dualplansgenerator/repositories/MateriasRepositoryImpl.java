package dev.edcan.dualplansgenerator.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.edcan.dualplansgenerator.models.Materias;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@Repository
public class MateriasRepositoryImpl implements IMateriasRepository {

    ObjectMapper om;
    File materiasFile;
    Materias[] materias;



    public MateriasRepositoryImpl() {

    }
    @Override
    public List<Materias> getAllMaterias() {

        try {
            om = new ObjectMapper();
            materiasFile = ResourceUtils.getFile("classpath:subjects.json");
            byte[] JSON = Files.readAllBytes(materiasFile.toPath());
            Materias[] materias = om.readValue(JSON, Materias[].class);
            return Arrays.asList(materias);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
