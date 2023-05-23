package dev.edcan.dualplansgenerator.repositories;

import dev.edcan.dualplansgenerator.models.IEMentor;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IIEMentorsRepository {
    List<IEMentor> getIEMentors();
    IEMentor getMentorByFullName(String fullName);
}
