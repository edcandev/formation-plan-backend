package dev.edcan.dualplansgenerator.helper;

import dev.edcan.dualplansgenerator.utils.IFileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RemoveTask extends Thread {

    IFileUploadUtil fileUploadUtil;

    private static String studentId;

    @Autowired
    public RemoveTask(IFileUploadUtil fileUploadUtil) {
        this.fileUploadUtil = fileUploadUtil;
    }

    public RemoveTask prepareRemove(String studentId) {
        RemoveTask.studentId = studentId;
        return this;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ProcessBuilder process01 = new ProcessBuilder("rm","-rf",studentId);
        process01.directory(fileUploadUtil.getReportFolderPath().toFile());

        ProcessBuilder process02 = new ProcessBuilder("rm","-rf",studentId.concat(".zip"));
        process02.directory(fileUploadUtil.getReportFolderPath().toFile());

        try {
            process01.start();
            process02.start();
            System.out.println("Se han eliminado los reportes de " + studentId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
