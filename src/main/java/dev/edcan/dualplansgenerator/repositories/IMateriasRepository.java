package dev.edcan.dualplansgenerator.repositories;

import dev.edcan.dualplansgenerator.models.Materias;

import java.util.List;

public interface IMateriasRepository {
    List<Materias> getAllMaterias();
    Materias getMateriaById(String subjectId);
}
