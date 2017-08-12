package nhatnghe.controller;

import java.io.File;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import nhatnghe.bean.MailInfo;
import nhatnghe.bean.XMailer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("mailer")
public class MailerController {
	@Autowired
	XMailer xmailer;
	
	@Autowired
	ServletContext app;
	
	@Autowired
	JavaMailSender mailer;
	
	@RequestMapping("index")
	public String index(ModelMap model) {
		model.addAttribute("mail", new MailInfo());
		return "mailer";
	}
	
	@RequestMapping("send")
	public String send(ModelMap model, MailInfo mail,
			@RequestParam("file") MultipartFile file) {
		try {
			// Write letter
//			MimeMessage message = mailer.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
//			helper.setFrom(mail.getFrom(), mail.getFrom());
//			helper.setTo(mail.getTo());
//			helper.setSubject(mail.getSubject());
//			helper.setText(mail.getBody());
			
			// Attach the file to the letter
			String filename = file.getOriginalFilename();
			String path = app.getRealPath("/images/"+filename);
			file.transferTo(new File(path));
//			helper.addAttachment(filename, new File(path));
			
			// Drop mail at the post office
//			mailer.send(message);
			
			String from = mail.getFrom();
			String to = mail.getTo();
			String cc = null;
			String bcc = null;
			String subject = mail.getSubject();
			String body = mail.getBody();
			String attachs = path;
			xmailer.send(from, to, cc, bcc, subject, body, attachs);
			
			model.addAttribute("thongbao", "Send mail successfully!");
		} 
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("thongbao", "Send mail failed!");
		}
		
		model.addAttribute("mail", new MailInfo());
		return "mailer";
	}
}
