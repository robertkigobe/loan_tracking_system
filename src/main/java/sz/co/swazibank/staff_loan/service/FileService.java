package sz.co.swazibank.staff_loan.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import sz.co.swazibank.staff_loan.FileStorageException;

@Service
public class FileService {

	 

	public void uploadFile(MultipartFile file, String uploadDir) {
		
		

		Path copyLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
		
		
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		
		
		try {
			
			if (file.isEmpty()) {
                throw new UploadException("Failed to store empty file " + filename);
            }
			
			if (filename.contains("..")) {
                // This is a security check
                throw new UploadException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
			
			
			
			Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileStorageException("Could not store file " + file.getOriginalFilename() + ". Please try again!");
			
		}
	}

}
