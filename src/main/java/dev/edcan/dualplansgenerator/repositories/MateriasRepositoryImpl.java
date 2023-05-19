package dev.edcan.dualplansgenerator.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.edcan.dualplansgenerator.models.Materias;
import dev.edcan.dualplansgenerator.utils.IFileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
public class MateriasRepositoryImpl implements IMateriasRepository {

    @Autowired
    IFileUploadUtil fileUploadUtil;
    ObjectMapper om;
    Path materiasFilePath;
    Materias[] materias;

    public MateriasRepositoryImpl() {

    }
    @Override
    public List<Materias> getAllMaterias() {

        try {
            om = new ObjectMapper();
            //materiasFile = ResourceUtils.getFile("classpath:subjects.json");
            materiasFilePath = fileUploadUtil.getGeneratorProjectPath().resolve("subjects.json");

            byte[] JSON = Files.readAllBytes(materiasFilePath);
            Materias[] materias = om.readValue(JSON, Materias[].class);
            return Arrays.asList(materias);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Materias getMateriaById(String subjectId) {

        for(Materias mat : getAllMaterias()) {
           if (Objects.equals(mat.getClave(), subjectId)) return mat;
        }
        return null;
    }
}
