package dev.edcan.dualplansgenerator.services;

import dev.edcan.dualplansgenerator.helper.RemoveTask;
import dev.edcan.dualplansgenerator.utils.IFileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FileRemoveServiceImpl implements IFIleRemoveService {

    @Autowired
    RemoveTask removeTask;

    @Override
    public boolean deleteReportFolderByStudentId(String studentId) throws IOException {
        removeTask.prepareRemove(studentId).start();
        return true;
    }
}
