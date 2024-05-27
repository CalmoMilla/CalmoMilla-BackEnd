package com.calmomilla.domain.utils;

import com.calmomilla.domain.exception.NegocioException;
import com.calmomilla.domain.model.Paciente;
import com.calmomilla.domain.model.Psicologo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class SalvarArquivo {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/fotosUsuarios/";

    public Paciente salvarFoto(MultipartFile file, Paciente paciente){
        try {
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            if (paciente.getFoto() != null && !paciente.getFoto().isEmpty()) {
                File oldFile = new File(UPLOAD_DIR + paciente.getFoto());
                if (oldFile.exists()) {
                    oldFile.delete();
                }
            }
            String fileExtension = getExtension(file.getOriginalFilename());
            String newFileName = paciente.getId() + "." + fileExtension;
            String filePath = UPLOAD_DIR + newFileName;
            file.transferTo(new File(filePath));
            System.out.println(filePath);
            paciente.setFoto(filePath);
            return paciente;
        } catch (IOException e) {
            e.printStackTrace();
           throw new NegocioException("não foi possivel salvar a foto");
        }
    }

    public Psicologo salvarFoto(MultipartFile file, Psicologo psicologo){
        try {
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            if (psicologo.getFoto() != null && !psicologo.getFoto().isEmpty()) {
                File oldFile = new File(UPLOAD_DIR + psicologo.getFoto());
                if (oldFile.exists()) {
                    oldFile.delete();
                }
            }
            String fileExtension = getExtension(file.getOriginalFilename());
            String newFileName = psicologo.getId() + "." + fileExtension;
            String filePath = UPLOAD_DIR + newFileName;
            file.transferTo(new File(filePath));
            System.out.println(filePath);
            psicologo.setFoto(filePath);
            return psicologo;
        } catch (IOException e) {
            e.printStackTrace();
            throw new NegocioException("não foi possivel salvar a foto");
        }
    }
    private String getExtension(String filename) {
        int lastIndexOfDot = filename.lastIndexOf(".");
        return lastIndexOfDot == -1 ? "" : filename.substring(lastIndexOfDot + 1);
    }
}
