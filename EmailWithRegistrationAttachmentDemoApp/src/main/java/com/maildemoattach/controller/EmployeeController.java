package com.maildemoattach.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.maildemoattach.model.Employee;
import com.maildemoattach.repository.EmployeeRepository;


@Controller
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	private JavaMailSender mailSenderObj;
	
	//Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://adb//";

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public String addEmp(ModelMap model,Employee employee, @RequestParam("file") MultipartFile file) throws IOException {

		byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);
		
		employeeRepository.save(employee);
		
		sendmail(employee, file);
		
		return "redirect:/success";
	}

	@GetMapping("/")
	public String welcome() {

		return "welcome";
	}

	@GetMapping("/add")
	public String addEmployee() {

		return "addEmployeeForm";
	}

	@GetMapping("/success")
	public String success() {

		return "success";
	}

	// send email code
	private void sendmail(Employee employee,  @RequestParam("file") MultipartFile file) {


		final String emailToRecipient = employee.getEmp_email();
		final String emailSubject = "Suceesfully Registration with EmailWithRegistrationDemoApp";
		final String emailMessage1 = "<html> <body> <p>Dear Sir/Madam,</p><p>You have succesfully Registered with our Services"
				 + "<br><br>"
				+ "<table border='1' width='300px' style='text-align:center;font-size:20px;'><tr> <td colspan='2'>"
				+  "</td></tr><tr><td>Name</td><td>" + employee.getEmp_name()
				+ "</td></tr><tr><td>Address</td><td>" + employee.getEmp_address()
				+ "</td></tr><tr><td>Email</td><td>" + employee.getEmp_email()
				+ "</td></tr></table> </body></html>";

		mailSenderObj.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				mimeMsgHelperObj.setTo(emailToRecipient);
				
				mimeMsgHelperObj.setText(emailMessage1, true);

				mimeMsgHelperObj.setSubject(emailSubject);
				
				mimeMsgHelperObj.addAttachment(file.getOriginalFilename(), file);
			}
		});

	}

}
