package nhatnghe.controller;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("uploader")
public class UploaderController {
	@Autowired
	ServletContext app;
	
	@RequestMapping("index")
	public String index() {
		return "uploader/index";
	}
	
	@RequestMapping("upload")
	public String upload(ModelMap model, 
			@RequestParam("image") MultipartFile image) {
		String filename = image.getOriginalFilename();
		String path = app.getRealPath("images/"+filename);
		System.out.println(path);
		try {
			image.transferTo(new File(path));
			model.addAttribute("message", "Upload file successfully at: " + path);
			
			model.addAttribute("filename", filename);
			model.addAttribute("filesize", image.getSize());
			model.addAttribute("filetype", image.getContentType());
		} 
		catch (Exception e) {
			model.addAttribute("message", "Upload file failed!");
		}
		
		return "uploader/success";
	}
}
