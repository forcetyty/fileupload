package kr.co.itcen.fileupload.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.fileupload.exception.FileuploadException;

@Service
public class FileuploadService {
	
	private static final String SAVE_PATH = "/uploads";
	private static final String URL_PREFIX = "/images";

	public String resotre(MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		String url = "";
		
		try {
			
			if(multipartFile == null) {
				return url;
			}
			
			String orginalFilename = multipartFile.getOriginalFilename();
			
			String saveFileName = generateSaveFilename(orginalFilename.substring(orginalFilename.lastIndexOf('.')+1));
			
			long fileSize = multipartFile.getSize();
			
			System.out.println("###############################" + orginalFilename + "###############################");
			System.out.println("###############################" + saveFileName + "###############################");
			System.out.println("###############################" + fileSize + "###############################");
			
			
				byte[] fileDate = multipartFile.getBytes();
				OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
				os.write(fileDate);
				os.close();
				
				url = URL_PREFIX + "/" + saveFileName; 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FileuploadException();
		
		}
			
		return url;
	}

	private String generateSaveFilename(String extName) {
		// TODO Auto-generated method stub
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		filename +=	calendar.get(Calendar.YEAR);
		filename +=	calendar.get(Calendar.MONTH);
		filename +=	calendar.get(Calendar.DATE);
		filename +=	calendar.get(Calendar.HOUR);
		filename +=	calendar.get(Calendar.MINUTE);
		filename +=	calendar.get(Calendar.SECOND);
		filename +=	calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		
		return filename;
	}
	
	

}
