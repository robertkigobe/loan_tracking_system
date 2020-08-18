package sz.co.swazibank.staff_loan.controller;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import sz.co.swazibank.staff_loan.MediaTypeUtils;
import sz.co.swazibank.staff_loan.entity.StaffLoan;
import sz.co.swazibank.staff_loan.entity.StaffLoanSetup;
import sz.co.swazibank.staff_loan.service.StaffLoan_Generate_Short_Securities;
import sz.co.swazibank.staff_loan.service.FileService;
import sz.co.swazibank.staff_loan.service.MailService;
import sz.co.swazibank.staff_loan.service.StaffLoanService;

@Controller
@RequestMapping("/staffLoan")
public class StaffLoanController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	private static String JSON_STAFF_LOAN_SETUP = "http://mlu-itc-dw-03.mshome.net:8091/setuprest/staffLoanSetup/1";
	private static String JSON_STAFFLOAN = null;
	
	Logger log = Logger.getLogger(StaffLoanController.class.getName());
	//private static Logger LOGGER = LoggerFactory.getLogger(StaffLoanController.class);
	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	protected Validator validator;

	@Value("${app.documentpath}")
	private String uploadDir;

	@Value("${app.server}")
	private String server;

	@Value("${app.send_email}")
	private boolean send_email;

	@Autowired
	private ServletContext servletContext;

	public String finalString = null;
	
	public String responseAction = null;

	@Autowired
	FileService fileService;

	@Autowired
	private StaffLoanService staffLoanService;

	@Autowired
	private MailService mailService;
	
	 @Autowired
	   private EurekaClient  discoveryClient;
	
	@GetMapping("/settings")
	public void staffLoan(HttpServletResponse response) throws IOException {
		
		String url = null;
		String name = null;
		
		List<Application> applications = discoveryClient.getApplications().getRegisteredApplications();

	    for (Application application : applications) {
	        List<InstanceInfo> applicationsInstances = application.getInstances();
	        for (InstanceInfo applicationsInstance : applicationsInstances) {
	        	
	        	name = applicationsInstance.getAppName();
	        	     	
	        	if(name.equals("LOAN-SETUP-SERVICE"))
	        		
	        	{
	        		
	        		url = applicationsInstance.getHomePageUrl();
	        		
	        		JSON_STAFF_LOAN_SETUP = url+"loanSetup/home";
	        	}

	        }
	        
	    }
	   
	    response.sendRedirect(JSON_STAFF_LOAN_SETUP);
	   
	}
	
	


	@GetMapping("/staffLoanStatus")
	public String staffLoanStatus(Model theModel, Principal principal) {

		List<StaffLoan> theStaffLoans = staffLoanService.findInProgress();

		for (int x = 0; x < theStaffLoans.size(); x++) {

			long seconds = -(theStaffLoans.get(x).getLastUpdated().getTime() - (new java.util.Date().getTime())) / 1000;

			long sec = seconds % 60;
			long minutes = seconds % 3600 / 60;
			long hours = seconds % 86400 / 3600;
			long days = seconds / 86400;

			theStaffLoans.get(x).setTimeElapsed(days + "dd: " + hours + "hh: " + minutes + "mm: " + sec + "ss");

		}

		theModel.addAttribute("theDate", new java.util.Date());

		theModel.addAttribute("staffLoans", theStaffLoans);
		

		return "staffLoan/staffLoanStatus";
	}
	
	@GetMapping("/staffLoanCancelled")
	public String staffLoanCancelled(Model theModel, Principal principal) {

		List<StaffLoan> theStaffLoans = staffLoanService.findCancelled();

		for (int x = 0; x < theStaffLoans.size(); x++) {

			long seconds = -(theStaffLoans.get(x).getLastUpdated().getTime() - (new java.util.Date().getTime())) / 1000;

			long sec = seconds % 60;
			long minutes = seconds % 3600 / 60;
			long hours = seconds % 86400 / 3600;
			long days = seconds / 86400;

			theStaffLoans.get(x).setTimeElapsed(days + "dd: " + hours + "hh: " + minutes + "mm: " + sec + "ss");

		}

		theModel.addAttribute("theDate", new java.util.Date());

		theModel.addAttribute("staffLoans", theStaffLoans);
		

		return "staffLoan/staffLoanStatus";
	}
	
	@GetMapping("/staffLoanDisbursed")
	public String staffLoanDisbursed(Model theModel, Principal principal) {

		List<StaffLoan> theStaffLoans = staffLoanService.findDisbursed();

		for (int x = 0; x < theStaffLoans.size(); x++) {

			long seconds = -(theStaffLoans.get(x).getLastUpdated().getTime() - (new java.util.Date().getTime())) / 1000;

			long sec = seconds % 60;
			long minutes = seconds % 3600 / 60;
			long hours = seconds % 86400 / 3600;
			long days = seconds / 86400;

			theStaffLoans.get(x).setTimeElapsed(days + "dd: " + hours + "hh: " + minutes + "mm: " + sec + "ss");

		}

		theModel.addAttribute("theDate", new java.util.Date());

		theModel.addAttribute("staffLoans", theStaffLoans);
		

		return "staffLoan/staffLoanStatus";
	}


	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		StaffLoan theStaffLoan = new StaffLoan();

		theModel.addAttribute("staffLoan", theStaffLoan);

		return "staffLoan/staffLoan-form";
	}

	@PostMapping("/save")
	public String saveStaffLoan(Model theModel, @ModelAttribute("staffLoan") @Valid StaffLoan theStaffLoan,
			Errors error, @RequestParam("action") String action, @RequestParam("file") MultipartFile file,
			@RequestParam("nextApprover") String nextApprover, RedirectAttributes atts) throws IOException {
		

    Map<String, String> params = new HashMap<String, String>();
    params.put("theId", "1");
     
    RestTemplate restTemplate = new RestTemplate();
    StaffLoanSetup theStaffLoanSetup = restTemplate.getForObject(JSON_STAFF_LOAN_SETUP, StaffLoanSetup.class, params);
     

		float funeralCover = theStaffLoanSetup.getFuneralCover();
		float interestRate = theStaffLoanSetup.getInterestRate();
		String payrollAdministrator = theStaffLoanSetup.getPayrollAdmin();
		String payrollAdministratorEmail = theStaffLoanSetup.getPayrollAdminEmail();
		String seniorHrManager = theStaffLoanSetup.getSeniorHrManager();
		String seniorHrManagerEmail = theStaffLoanSetup.getSeniorHrManagerEmail();
		String securitiesSupervisor = theStaffLoanSetup.getSecuritiesSupervisor();
		String securitiesSupervisorEmail = theStaffLoanSetup.getSecuritiesSupervisorEmail();
		String loanOpeningSupervisor = theStaffLoanSetup.getLoanOpeningSupervisor();
		String loanOpeningSupervisorEmail = theStaffLoanSetup.getLoanOpeningSupervisorEmail();
		

		if (error.hasErrors()) {

			theModel.addAttribute("staffLoan", theStaffLoan);

			responseAction = "staffLoan/staffLoan-form";

		} else if (action.equals("Preview")) {

			atts.addFlashAttribute("theStaffLoan", theStaffLoan);
			responseAction = "redirect:/staffLoan/securitiesShortPdf";

		} else if (action.equals("Save")) {
			String mailBody = null;
			atts.addFlashAttribute("savedStaffLoan", theStaffLoan);
			Random random = new Random();
			int randomWithNextInt = random.nextInt(200);

			theStaffLoan.setEmployeeLoanRef(theStaffLoan.getDepartment() + " " + randomWithNextInt);

			try {

				fileService.uploadFile(file, uploadDir);

			} catch (Exception ex) {
				
				log.log(Level.SEVERE, "failed to upload the attachment");
				
				System.out.println(ex.toString());
			}

			
			if (nextApprover.equals("Supervisor")) {

				theStaffLoan.setLoanStatus("Pending Supervisor Action");

			} else if (nextApprover.equals("EManager")) {

				theStaffLoan.setLoanStatus("Pending EM Action");

			}
			theStaffLoan.setPayslipLocation(uploadDir + file.getOriginalFilename());
			theStaffLoan.setApplicantCommentDate(new Date());
			theStaffLoan.setInterestRate(interestRate);
			theStaffLoan.setFuneralCover(funeralCover);
			theStaffLoan.setPayrollAdministrator(payrollAdministrator);
			theStaffLoan.setPayrollAdministratorEmail(payrollAdministratorEmail);
			theStaffLoan.setSeniorHrManager(seniorHrManager);
			theStaffLoan.setSeniorHrManagerEmail(seniorHrManagerEmail);
			theStaffLoan.setSecuritiesSupervisor(securitiesSupervisor);
			theStaffLoan.setSecuritiesSupervisorEmail(securitiesSupervisorEmail);
			theStaffLoan.setLoanOpeningSupervisor(loanOpeningSupervisor);
			theStaffLoan.setLoanOpeningSupervisorEmail(loanOpeningSupervisorEmail);

			try {
				staffLoanService.saveAndFlush(theStaffLoan);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String toAddress = theStaffLoan.getSupervisorEmail();
			String employeeEmail = theStaffLoan.getEmployeeEmail();
			String fromAddress = theStaffLoan.getEmployeeEmail();
			String subject = "Loan Ref " + theStaffLoan.getEmployeeLoanRef() + " requires your attention";

			if (nextApprover.equals("Supervisor")) {
				mailBody = "Dear " + theStaffLoan.getSupervisor() + "\n \n" + "A new Staff Loan has been Submitted by "
						+ theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname() + " with reference: "
						+ theStaffLoan.getEmployeeLoanRef() + "  Please Click on the link "+staffLoanLink()+"staffLoanGetSupervisorComment/" + theStaffLoan.getId() + " to comment."
						+ "\nThis mail is Auto generated. Please Contact the Applicant on "
						+ theStaffLoan.getEmployeeEmail() + " for additional Information.";
			} else if (nextApprover.equals("EManager")) {
				mailBody = "Dear " + theStaffLoan.getSupervisor() + "\n \n" + "A new Staff Loan has been Submitted by "
						+ theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname() + " with reference: "
						+ theStaffLoan.getEmployeeLoanRef() + "  Please Click on the link "+staffLoanLink()+"staffLoan/staffLoanGetEMComment/" + theStaffLoan.getId() + " to comment."
						+ "\nThis mail is Auto generated. Please Contact the Applicant on "
						+ theStaffLoan.getEmployeeEmail() + " for additional Information.";
			}

			try {

				if (send_email) {

					mailService.send(employeeEmail, toAddress, fromAddress, subject, mailBody);
				}
			}

			catch (Exception e) {

				System.out.println(e.getMessage());
			}

			responseAction = "redirect:/staffLoan/staffLoanStatus";

		}

		return responseAction;

	}

	@PostMapping("/saveRedo")
	public String saveStaffLoanRedo(Model theModel, @ModelAttribute("staffLoan") @Valid StaffLoan theStaffLoan,
			Errors error, @RequestParam("action") String action, @RequestParam("nextApprover") String nextApprover,
			RedirectAttributes atts) throws IOException {

		

	    Map<String, String> params = new HashMap<String, String>();
	    params.put("theId", "1");
	     
	    RestTemplate restTemplate = new RestTemplate();
	    StaffLoanSetup theStaffLoanSetup = restTemplate.getForObject(JSON_STAFF_LOAN_SETUP, StaffLoanSetup.class, params);

		float funeralCover = theStaffLoanSetup.getFuneralCover();
		float interestRate = theStaffLoanSetup.getInterestRate();
		String payrollAdministrator = theStaffLoanSetup.getPayrollAdmin();
		String payrollAdministratorEmail = theStaffLoanSetup.getPayrollAdminEmail();
		String seniorHrManager = theStaffLoanSetup.getSeniorHrManager();
		String seniorHrManagerEmail = theStaffLoanSetup.getSeniorHrManagerEmail();
		String securitiesSupervisor = theStaffLoanSetup.getSecuritiesSupervisor();
		String securitiesSupervisorEmail = theStaffLoanSetup.getSecuritiesSupervisorEmail();
		String loanOpeningSupervisor = theStaffLoanSetup.getLoanOpeningSupervisor();
		String loanOpeningSupervisorEmail = theStaffLoanSetup.getLoanOpeningSupervisorEmail();

		if (error.hasErrors()) {

			theModel.addAttribute("staffLoan", theStaffLoan);

			responseAction = "staffLoan/staffLoan-form";

		} else if (action.equals("Preview")) {

			atts.addFlashAttribute("theStaffLoan", theStaffLoan);
			responseAction = "redirect:/staffLoan/securitiesShortPdf";

		} else if (action.equals("Save")) {

			atts.addFlashAttribute("savedStaffLoan", theStaffLoan);
			Random random = new Random();
			int randomWithNextInt = random.nextInt(200);

			theStaffLoan.setEmployeeLoanRef(theStaffLoan.getDepartment() + " " + randomWithNextInt);

			String mailBody = null;
			if (nextApprover.equals("Supervisor")) {

				theStaffLoan.setLoanStatus("Pending Supervisor Action");

			} else if (nextApprover.equals("EManager")) {

				theStaffLoan.setLoanStatus("Pending EM Action");

			}
			theStaffLoan.setApplicantCommentDate(new Date());
			theStaffLoan.setInterestRate(interestRate);
			theStaffLoan.setFuneralCover(funeralCover);
			theStaffLoan.setPayrollAdministrator(payrollAdministrator);
			theStaffLoan.setPayrollAdministratorEmail(payrollAdministratorEmail);
			theStaffLoan.setSeniorHrManager(seniorHrManager);
			theStaffLoan.setSeniorHrManagerEmail(seniorHrManagerEmail);
			theStaffLoan.setSecuritiesSupervisor(securitiesSupervisor);
			theStaffLoan.setSecuritiesSupervisorEmail(securitiesSupervisorEmail);
			theStaffLoan.setLoanOpeningSupervisor(loanOpeningSupervisor);
			theStaffLoan.setLoanOpeningSupervisorEmail(loanOpeningSupervisorEmail);

			try {
				staffLoanService.saveAndFlush(theStaffLoan);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String toAddress = theStaffLoan.getSupervisorEmail();
			String employeeEmail = theStaffLoan.getEmployeeEmail();
			String fromAddress = theStaffLoan.getEmployeeEmail();
			String subject = "Loan Ref " + theStaffLoan.getEmployeeLoanRef() + " requires your attention";

			if (nextApprover.equals("Supervisor")) {
				mailBody = "Dear " + theStaffLoan.getSupervisor() + "\n \n" + "A new Staff Loan has been Submitted by "
						+ theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname() + " with reference: "
						+ theStaffLoan.getEmployeeLoanRef() + "  Please Click on the link "+staffLoanLink()+"staffLoanGetSupervisorComment/" + theStaffLoan.getId() + " to comment."
						+ "\nThis mail is Auto generated. Please Contact the Applicant on "
						+ theStaffLoan.getEmployeeEmail() + " for additional Information.";
			} else if (nextApprover.equals("EManager")) {
				mailBody = "Dear " + theStaffLoan.getSupervisor() + "\n \n" + "A new Staff Loan has been Submitted by "
						+ theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname() + " with reference: "
						+ theStaffLoan.getEmployeeLoanRef() + "  Please Click on the link http://" + server
						+ "/staffLoan/staffLoanGetEMComment/" + theStaffLoan.getId() + " to comment."
						+ "\nThis mail is Auto generated. Please Contact the Applicant on "
						+ theStaffLoan.getEmployeeEmail() + " for additional Information.";
			}

			try {

				if (send_email) {

					mailService.send(employeeEmail, toAddress, fromAddress, subject, mailBody);
				}
			}

			catch (Exception e) {

				System.out.println(e.getMessage());
			}

			responseAction = "redirect:/staffLoan/staffLoanStatus";

		}

		return responseAction;

	}

	@GetMapping("/staffLoanGetSupervisorComment/{theId}")
	public String getStaffLoanGetSupervisorComment(@PathVariable int theId, Model theModel) {

		StaffLoan theStaffLoan = staffLoanService.findStaffLoanById(theId);

		if (theStaffLoan == null) {
			throw new RuntimeException("Staff Loan by Ref" + theId + " is not found");
		}

		theModel.addAttribute("staffLoan", theStaffLoan);

		return "staffLoan/staffLoan-form-supervisor";
	}

	@PostMapping("/saveSupervisorComments/{id}")
	public String saveSupervisorStaffLoanComments(Model theModel,
			@ModelAttribute("staffLoan") @Valid StaffLoan theStaffLoan, Errors error,
			@RequestParam("action") String action, @RequestParam("nextAction") String nextAction,
			RedirectAttributes atts) throws IOException {

		

		if (error.hasErrors()) {

			responseAction = "staffLoan/staffLoan-form-supervisor";

		} else if (action.equals("Preview")) {

			atts.addFlashAttribute("theStaffLoan", theStaffLoan);
			responseAction = "redirect:/staffLoan/securitiesShortPdf";

		} else if (action.equals("Save")) {

			if (nextAction.equals("progressLoan")) {

				theStaffLoan.setLoanStatus("Pending EM Action");
				theStaffLoan.setSupervisorCommentDate(new Date());

				staffLoanService.saveAndFlush(theStaffLoan);

				String toAddress = theStaffLoan.getHodEmail();
				String employeeEmail = theStaffLoan.getEmployeeEmail();
				String fromAddress = theStaffLoan.getSupervisorEmail();
				String subject = "Loan Ref " + theStaffLoan.getEmployeeLoanRef() + " requires your attention";
				String mailBody = "Dear " + theStaffLoan.getHod() + "\n \n" + "A new Staff Loan has been Submitted by "
						+ theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname() + " with reference: "
						+ theStaffLoan.getEmployeeLoanRef() + "  Please Click on the link "+staffLoanLink()+"staffLoanGetEMComment/" + theStaffLoan.getId() + " to comment."
						+ "\nThis mail is Auto generated. Please Contact the Applicant on "
						+ theStaffLoan.getEmployeeEmail() + " for additional Information.";

				try {

					if (send_email) {

						mailService.send(employeeEmail, toAddress, fromAddress, subject, mailBody);
					}
				}

				catch (Exception e) {

					System.out.println(e.getMessage());
				}

				responseAction = "redirect:/staffLoan/staffLoanStatus";

			} else if (nextAction.equals("returnToStaff")) {

				theStaffLoan.setLoanStatus("Requires Ammendment by Staff");
				theStaffLoan.setSupervisorCommentDate(new Date());

				staffLoanService.saveAndFlush(theStaffLoan);

				String toAddress = theStaffLoan.getEmployeeEmail();
				String employeeEmail = theStaffLoan.getHodEmail();
				String fromAddress = theStaffLoan.getSupervisorEmail();
				String subject = "Loan Ref " + theStaffLoan.getEmployeeLoanRef() + " requires your attention";
				String mailBody = "Dear " + theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname() + "\n \n"
						+ "Your staff loan with reference: " + theStaffLoan.getEmployeeLoanRef()
						+ " requires your action as detailed in the comments. " + "\n\n========> "
						+ theStaffLoan.getSupervisorComment() + " \n\n========> made at this time: " + new Date()
						+ " \n\nPlease Click on the link "+staffLoanLink()+"staffLoanGetStaffComment/"
						+ theStaffLoan.getId() + " to comment."
						+ "\n\nThis mail is Auto generated. Please Contact the Supervisor on "
						+ theStaffLoan.getSupervisorEmail() + " for additional Information.";

				try {

					if (send_email) {

						mailService.send(employeeEmail, toAddress, fromAddress, subject, mailBody);
					}
				}

				catch (Exception e) {

					System.out.println(e.getMessage());
				}

				responseAction = "redirect:/staffLoan/staffLoanStatus";

			}
		}

		return responseAction;

	}

	@GetMapping("/staffLoanGetStaffComment/{theId}")
	public String getStaffLoanGetStaffRedoComment(@PathVariable int theId, Model theModel) {

		StaffLoan theStaffLoan = staffLoanService.findStaffLoanById(theId);

		if (theStaffLoan == null) {
			throw new RuntimeException("Staff Loan by Ref" + theId + " is not found");
		}

		theModel.addAttribute("staffLoan", theStaffLoan);

		return "staffLoan/staffLoan-form-redo";
	}

	@GetMapping("/staffLoanGetEMComment/{theId}")
	public String getStaffLoanbyEM(@PathVariable int theId, Model theModel) {

		StaffLoan theStaffLoan = staffLoanService.findStaffLoanById(theId);

		if (theStaffLoan == null) {
			throw new RuntimeException("Staff Loan by Ref" + theId + " is not found");
		}

		theModel.addAttribute("staffLoan", theStaffLoan);

		return "staffLoan/staffLoan-form-em";
	}

	@PostMapping("/saveEMStaffLoanComments")
	public String saveEMStaffLoan(Model theModel, @ModelAttribute("staffLoan") @Valid StaffLoan theStaffLoan,
			Errors error, @RequestParam("action") String action, @RequestParam("nextAction") String nextAction,
			RedirectAttributes atts) throws IOException {

		

		if (error.hasErrors()) {

			responseAction = "staffLoan/staffLoan-form-em";

		} else if (action.equals("Preview")) {

			atts.addFlashAttribute("theStaffLoan", theStaffLoan);
			responseAction = "redirect:/staffLoan/securitiesShortPdf";

		} else if (action.equals("Save")) {

			if (nextAction.equals("progressLoan")) {

				atts.addFlashAttribute("savedStaffLoan", theStaffLoan);

				theStaffLoan.setLoanStatus("Pending Payroll Admin Action");
				theStaffLoan.setHodCommentDate(new Date());

				staffLoanService.saveAndFlush(theStaffLoan);

				String payrollAdminEmail = theStaffLoan.getPayrollAdministratorEmail();

				String toAddress = payrollAdminEmail;
				String employeeEmail = theStaffLoan.getEmployeeEmail();
				String fromAddress = theStaffLoan.getHodEmail();
				String subject = "Loan Ref " + theStaffLoan.getEmployeeLoanRef() + " requires your attention";
				String mailBody = "Dear " + theStaffLoan.getPayrollAdministrator() + "\n \n"
						+ "A new Staff Loan has been Submitted by " + theStaffLoan.getFirstName() + " "
						+ theStaffLoan.getSurname() + " with reference: " + theStaffLoan.getEmployeeLoanRef()
						+ "  Please Click on the link "+staffLoanLink()+"staffLoanGetPayrollAdminComments/"
						+ theStaffLoan.getId() + " to comment."
						+ "\n\nThis mail is Auto generated. Please Contact the Applicant on "
						+ theStaffLoan.getEmployeeEmail() + " for additional Information.";

				try {

					if (send_email) {

						mailService.send(employeeEmail, toAddress, fromAddress, subject, mailBody);
					}
				}

				catch (Exception e) {

					System.out.println(e.getMessage());
				}

				responseAction = "redirect:/staffLoan/staffLoanStatus";

			}

			else if (nextAction.equals("returnToStaff")) {

				theStaffLoan.setLoanStatus("Requires Ammendment by Staff");
				theStaffLoan.setSupervisorCommentDate(new Date());

				staffLoanService.saveAndFlush(theStaffLoan);

				String toAddress = theStaffLoan.getEmployeeEmail();
				String employeeEmail = theStaffLoan.getSupervisorEmail();
				String fromAddress = theStaffLoan.getHodEmail();
				String subject = "Loan Ref " + theStaffLoan.getEmployeeLoanRef() + " requires your attention";
				String mailBody = "Dear " + theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname() + "\n \n"
						+ "Your staff loan with reference: " + theStaffLoan.getEmployeeLoanRef()
						+ " requires your action as detailed in the comments. " + "\n\n========> "
						+ theStaffLoan.getSupervisorComment() + " \n\n========> made at this time: " + new Date()
						+ " \n\nPlease Click on the link "+staffLoanLink()+"staffLoanGetStaffComment/"
						+ theStaffLoan.getId() + " to comment."
						+ "\n\nThis mail is Auto generated. Please Contact the Supervisor on "
						+ theStaffLoan.getHodEmail() + " for additional Information.";

				try {

					if (send_email) {

						mailService.send(employeeEmail, toAddress, fromAddress, subject, mailBody);
					}
				}

				catch (Exception e) {

					System.out.println(e.getMessage());
				}

				responseAction = "redirect:/staffLoan/staffLoanStatus";

			}
		}

		return responseAction;

	}

	@GetMapping("/staffLoanGetPayrollAdminComments/{theId}")
	public String staffLoanGetPayrollAdminComments(@PathVariable int theId, Model theModel) {

		StaffLoan theStaffLoan = staffLoanService.findStaffLoanById(theId);

		if (theStaffLoan == null) {
			throw new RuntimeException("Staff Loan by Ref" + theId + " is not found");
		}

		theModel.addAttribute("staffLoan", theStaffLoan);

		return "staffLoan/staffLoan-form-Payroll-admin";
	}

	@PostMapping("/savePayrollAdminStaffLoanComments")
	public String savePayrollAdminStaffLoanComments(Model theModel,
			@ModelAttribute("staffLoan") @Valid StaffLoan theStaffLoan, Errors error,
			@RequestParam("action") String action, @RequestParam("nextAction") String nextAction,
			RedirectAttributes atts) throws IOException {

		

		if (error.hasErrors()) {

			responseAction = "staffLoan/staffLoan-form-em";

		} else if (action.equals("Preview")) {

			atts.addFlashAttribute("theStaffLoan", theStaffLoan);
			responseAction = "redirect:/staffLoan/securitiesShortPdf";

		} else if (action.equals("Save")) {

			if (nextAction.equals("progressLoan")) {

				atts.addFlashAttribute("savedStaffLoan", theStaffLoan);

				theStaffLoan.setLoanStatus("Pending Senior Hr Manager Comments");
				theStaffLoan.setPayrollAdministratorCommentDate(new Date());

				staffLoanService.saveAndFlush(theStaffLoan);

				String toAddress = theStaffLoan.getSeniorHrManagerEmail();
				String employeeEmail = theStaffLoan.getEmployeeEmail();
				String fromAddress = theStaffLoan.getEmployeeEmail();
				String subject = "Loan Ref " + theStaffLoan.getEmployeeLoanRef() + " requires your attention";
				String mailBody = "Dear " + theStaffLoan.getSeniorHrManager() + " \n \n"
						+ "A new Staff Loan has been Submitted by " + theStaffLoan.getFirstName() + " "
						+ theStaffLoan.getSurname() + " with reference: " + theStaffLoan.getEmployeeLoanRef()
						+ "  Please Click on the link "+staffLoanLink()+"staffLoanGetStatusSeniorHrManagerComments/" + theStaffLoan.getId()
						+ " to comment." + "\n\nThis mail is Auto generated. Please Contact the Applicant on "
						+ theStaffLoan.getEmployeeEmail() + " for additional Information.";

				try {

					if (send_email) {

						mailService.send(employeeEmail, toAddress, fromAddress, subject, mailBody);
					}
				}

				catch (Exception e) {

					System.out.println(e.getMessage());
				}

				responseAction = "redirect:/staffLoan/staffLoanStatus";

			}

			else if (nextAction.equals("returnToStaff")) {

				theStaffLoan.setLoanStatus("Requires Ammendment by Staff");
				theStaffLoan.setSupervisorCommentDate(new Date());

				staffLoanService.saveAndFlush(theStaffLoan);

				String toAddress = theStaffLoan.getEmployeeEmail();
				String employeeEmail = theStaffLoan.getSupervisorEmail();
				String fromAddress = theStaffLoan.getHodEmail();
				String subject = "Loan Ref " + theStaffLoan.getEmployeeLoanRef() + " requires your attention";
				String mailBody = "Dear " + theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname() + "\n \n"
						+ "Your staff loan with reference: " + theStaffLoan.getEmployeeLoanRef()
						+ " requires your action as detailed in the comments. " + "\n\n========> "
						+ theStaffLoan.getSupervisorComment() + " \n\n========> made at this time: " + new Date()
						+ " \n\nPlease Click on the link "+staffLoanLink()+"staffLoanGetStaffComment/"
						+ theStaffLoan.getId() + " to comment."
						+ "\n\nThis mail is Auto generated. Please Contact the Supervisor on "
						+ theStaffLoan.getHodEmail() + " for additional Information.";

				try {

					if (send_email) {

						mailService.send(employeeEmail, toAddress, fromAddress, subject, mailBody);
					}
				}

				catch (Exception e) {

					System.out.println(e.getMessage());
				}

				responseAction = "redirect:/staffLoan/staffLoanStatus";

			}
		}

		return responseAction;

	}

	@GetMapping("/staffLoanGetStatusSeniorHrManagerComments/{theId}")
	public String staffLoanGetStatusSeniorHrManagerComments(@PathVariable int theId, Model theModel) {

		StaffLoan theStaffLoan = staffLoanService.findStaffLoanById(theId);

		if (theStaffLoan == null) {
			throw new RuntimeException("Staff Loan by Ref" + theId + " is not found");
		}

		theModel.addAttribute("staffLoan", theStaffLoan);

		return "staffLoan/staffLoan-form-senior-hr-manager";
	}

	@PostMapping("/saveSeniorHrManagerStaffLoanComments")
	public String saveSeniorHrManagerStaffLoan(Model theModel,
			@ModelAttribute("staffLoan") @Valid StaffLoan theStaffLoan, Errors error,
			@RequestParam("action") String action, @RequestParam("nextAction") String nextAction,
			RedirectAttributes atts) throws IOException {

		

		if (error.hasErrors()) {

			responseAction = "staffLoan/staffLoan-form-senior-hr-manager";

		} else if (action.equals("Preview")) {

			atts.addFlashAttribute("theStaffLoan", theStaffLoan);
			responseAction = "redirect:/staffLoan/securitiesShortPdf";

		} else if (action.equals("Save")) {

			if (nextAction.equals("progressLoan")) {

				atts.addFlashAttribute("savedStaffLoan", theStaffLoan);

				theStaffLoan.setLoanStatus("Pending Employee Securities Upload");
				theStaffLoan.setSeniorHrManagerDecision("Approved");
				theStaffLoan.setSeniorHrManagerCommentDate(new Date());

				staffLoanService.saveAndFlush(theStaffLoan);

				String toAddress = theStaffLoan.getEmployeeEmail();
				String employeeEmail = theStaffLoan.getEmployeeEmail();
				String fromAddress = theStaffLoan.getSeniorHrManagerEmail();
				String subject = "Loan Ref " + theStaffLoan.getEmployeeLoanRef() + " requires your attention";
				String mailBody = "Dear " + theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname() + " \n \n"
						+ "Your loan with reference: " + theStaffLoan.getEmployeeLoanRef()
						+ " has been Approved. Please Click on the link "+staffLoanLink()+"getStaffLoanUploadSecurities/" + theStaffLoan.getId()
						+ " to generate securities and upload." + "\n\nThis mail is Auto generated.";

				try {

					if (send_email) {

						mailService.send(employeeEmail, toAddress, fromAddress, subject, mailBody);
					}
				}

				catch (Exception e) {

					System.out.println(e.getMessage());
				}

				responseAction = "redirect:/staffLoan/staffLoanStatus";

			}

			else if (nextAction.equals("returnToStaff")) {

				theStaffLoan.setSeniorHrManagerDecision("Declined");
				theStaffLoan.setLoanStatus("Requires Ammendment by Staff");
				theStaffLoan.setSupervisorCommentDate(new Date());

				staffLoanService.saveAndFlush(theStaffLoan);

				String toAddress = theStaffLoan.getEmployeeEmail();
				String employeeEmail = theStaffLoan.getSupervisorEmail();
				String fromAddress = theStaffLoan.getSeniorHrManagerEmail();
				String subject = "Loan Ref " + theStaffLoan.getEmployeeLoanRef() + " requires your attention";
				String mailBody = "Dear " + theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname() + "\n \n"
						+ "Your staff loan with reference: " + theStaffLoan.getEmployeeLoanRef()
						+ " has been declined as detailed in the comments. " + "\n\n========> "
						+ theStaffLoan.getSeniorHrManagerComment() + " \n\n========> made at this time: " + new Date()

						+ "\n\nThis mail is Auto generated. Please Contact the Supervisor on "
						+ theStaffLoan.getSeniorHrManagerEmail() + " for additional Information.";

				try {

					if (send_email) {

						mailService.send(employeeEmail, toAddress, fromAddress, subject, mailBody);
					}
				}

				catch (Exception e) {

					System.out.println(e.getMessage());
				}

				responseAction = "redirect:/staffLoan/staffLoanStatus";

			}
		}

		return responseAction;

	}

	@GetMapping("/getStaffLoanUploadSecurities/{theId}")
	public String getStaffLoanUploadSecurities(@PathVariable int theId, Model theModel) {

		StaffLoan theStaffLoan = staffLoanService.findStaffLoanById(theId);

		if (theStaffLoan == null) {
			throw new RuntimeException("Staff Loan by Ref" + theId + " is not found");
		}

		theModel.addAttribute("staffLoan", theStaffLoan);

		return "staffLoan/staffLoan-form-upload-securities";
	}

	@PostMapping("/saveUploadStaffSecurities")
	public String saveUploadStaffSecurities(@Valid @ModelAttribute("staffLoan") StaffLoan theStaffLoan,
			@RequestParam("action") String action, BindingResult result, @RequestParam("file") MultipartFile file,
			RedirectAttributes atts) throws IOException {

		

		if (result.hasErrors()) {

			responseAction = "staffLoan/staffLoan-form-upload-securities";

		} else if (action.equals("Print_Secutiries")) {

			atts.addFlashAttribute("theStaffLoan", theStaffLoan);
			responseAction = "redirect:/staffLoan/securitiesShortPdf";

		} else if (action.equals("Upload_Securities")) {


			try {
				fileService.uploadFile(file, uploadDir);
			} catch (Exception ex) {
				System.out.println(ex.toString());
			}
			String securitiesDocumentLocation = uploadDir + file.getOriginalFilename();

			atts.addFlashAttribute("savedStaffLoan", theStaffLoan);

			theStaffLoan.setLoanStatus("Pending Employee Securities Validation");
			theStaffLoan.setSecuritiesDocumentUploadDate(new Date());
			theStaffLoan.setSecuritiesDocumentLocation(securitiesDocumentLocation);

			staffLoanService.saveAndFlush(theStaffLoan);

			String toAddress = theStaffLoan.getEmployeeEmail();
			String employeeEmail = theStaffLoan.getEmployeeEmail();
			String fromAddress = theStaffLoan.getSeniorHrManagerEmail();
			String subject = "Loan Ref " + theStaffLoan.getEmployeeLoanRef() + " requires your attention";
			String mailBody = "Dear " + theStaffLoan.getSecuritiesSupervisor() + ", \n\n A loan with reference: "
					+ theStaffLoan.getEmployeeLoanRef() + "belonging to " + theStaffLoan.getFirstName() + " "
					+ theStaffLoan.getSurname() + " has uploaded Securities. Please Click on the link http://" + server
					+ "/staffLoan/getStaffLoanSecuritiesValidation/" + theStaffLoan.getId()
					+ " to validate the securities." + "\n\nThis mail is Auto generated.";

			try {

				if (send_email) {

					mailService.send(employeeEmail, toAddress, fromAddress, subject, mailBody);
				}
			}

			catch (Exception e) {

				System.out.println(e.getMessage());
			}

			responseAction = "redirect:/staffLoan/staffLoanStatus";

		}

		return responseAction;

	}

	@GetMapping("/getStaffLoanSecuritiesValidation/{theId}")
	public String getStaffLoanSecuritiesValidation(@PathVariable int theId, Model theModel) {

		StaffLoan theStaffLoan = staffLoanService.findStaffLoanById(theId);

		if (theStaffLoan == null) {
			throw new RuntimeException("Staff Loan by Ref" + theId + " is not found");
		}

		theModel.addAttribute("staffLoan", theStaffLoan);

		return "staffLoan/staffLoan-form-validate-securities";
	}

	@PostMapping("/saveSecuritiesSupervisorComments")
	public String saveSecuritiesSupervisorComments(Model theModel,
			@ModelAttribute("staffLoan") @Valid StaffLoan theStaffLoan, Errors error,
			@RequestParam("action") String action, @RequestParam("nextAction") String nextAction,
			RedirectAttributes atts) throws IOException {

		

		if (error.hasErrors()) {

			responseAction = "staffLoan/staffLoan-form-upload-securities";

		} else if (action.equals("Print_Secutiries")) {

			atts.addFlashAttribute("theStaffLoan", theStaffLoan);
			responseAction = "redirect:/staffLoan/securitiesShortPdf";

		} else if (action.equals("Save")) {

			if (nextAction.equals("progressLoan")) {

				atts.addFlashAttribute("savedStaffLoan", theStaffLoan);

				theStaffLoan.setLoanStatus("Pending Loan Disbursement Action");
				theStaffLoan.setSecuritiesSupervisorCommentDate(new Date());

				staffLoanService.saveAndFlush(theStaffLoan);

				theStaffLoan.setSecuritiesSupervisorEmail("robertk@swazibank.co.sz");

				String toAddress = theStaffLoan.getLoanOpeningSupervisorEmail();
				String employeeEmail = theStaffLoan.getEmployeeEmail();
				String fromAddress = theStaffLoan.getSecuritiesSupervisorEmail();

				String subject = "Loan Ref " + theStaffLoan.getEmployeeLoanRef() + " requires your attention";

				String mailBody = "Dear " + theStaffLoan.getLoanOpeningSupervisor() + ", \n\n A loan with reference: "
						+ theStaffLoan.getEmployeeLoanRef() + "belonging to " + theStaffLoan.getFirstName() + " "
						+ theStaffLoan.getSurname() + " requires your attention. " + "Please Click on the link http://"
						+ server + "/staffLoan/getStaffLoanDisbursementComment/" + theStaffLoan.getId()
						+ " to validate the securities." + "\n\nThis mail is Auto generated.";

				try {

					if (send_email) {

						mailService.send(employeeEmail, toAddress, fromAddress, subject, mailBody);
					}
				}

				catch (Exception e) {

					System.out.println(e.getMessage());
				}

				responseAction = "redirect:/staffLoan/staffLoanStatus";

			}

			else if (nextAction.equals("returnToStaff")) {

				theStaffLoan.setSeniorHrManagerDecision("Declined");
				theStaffLoan.setLoanStatus("Requires Ammendment by Staff");
				theStaffLoan.setSupervisorCommentDate(new Date());

				staffLoanService.saveAndFlush(theStaffLoan);

				String toAddress = theStaffLoan.getEmployeeEmail();
				String employeeEmail = theStaffLoan.getSupervisorEmail();
				String fromAddress = theStaffLoan.getSecuritiesSupervisorEmail();
				String subject = "Loan Ref " + theStaffLoan.getEmployeeLoanRef() + " requires your attention";
				String mailBody = "Dear " + theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname() + "\n \n"
						+ "Your staff loan with reference: " + theStaffLoan.getEmployeeLoanRef()
						+ " requires attention on the securities as detailed in the comments. " + "\n\n========> "
						+ theStaffLoan.getSecuritiesSupervisorComment() + " \n\n========> made at this time: "
						+ new Date()

						+ "\n\nPlease Click on the link http://\" + server\n"
						+ "						+ \"/staffLoan/getStaffLoanUploadSecurities/\" + theStaffLoan.getId()\n"
						+ "						+ \" to generate securities and upload.\" + \"\\n\\nThis mail is Auto generated.\"; \n\nThis mail is Auto generated. Please Contact the Supervisor on "
						+ theStaffLoan.getSecuritiesSupervisorEmail() + " for additional Information.";

				try {

					if (send_email) {

						mailService.send(employeeEmail, toAddress, fromAddress, subject, mailBody);
					}
				}

				catch (Exception e) {

					System.out.println(e.getMessage());
				}

				responseAction = "redirect:/staffLoan/staffLoanStatus";

			}

		}
		return responseAction;
	}

	

	@GetMapping("/getStaffLoanDisbursementComment/{theId}")
	public String getStaffLoanDisbursementComment(@PathVariable int theId, Model theModel) {

		StaffLoan theStaffLoan = staffLoanService.findStaffLoanById(theId);

		if (theStaffLoan == null) {
			throw new RuntimeException("Staff Loan by Ref" + theId + " is not found");
		}

		theModel.addAttribute("staffLoan", theStaffLoan);

		return "staffLoan/staffLoan-form-loan-disbursement";
	}

	@PostMapping("/saveLoanDisbursementSupervisorComments")
	public String saveLoanDisbursementSupervisorComments(@Valid @ModelAttribute("staffLoan") StaffLoan theStaffLoan,
			@RequestParam("action") String action, BindingResult result, RedirectAttributes atts) throws IOException {

		

		if (result.hasErrors()) {

			responseAction = "staffLoan/staffLoan-form-upload-securities";

		} else if (action.equals("Print_Secutiries")) {

			atts.addFlashAttribute("theStaffLoan", theStaffLoan);
			responseAction = "redirect:/staffLoan/securitiesShortPdf";

		} else if (action.equals("Save")) {

			atts.addFlashAttribute("savedStaffLoan", theStaffLoan);

			theStaffLoan.setLoanStatus("Loan disbursed");
			theStaffLoan.setLoanOpeningSupervisorCommentDate(new Date());
			theStaffLoan.setDisbursedAmount(theStaffLoan.getDisbursedAmount());
			theStaffLoan.setDisbursementDate(new Date());

			staffLoanService.saveAndFlush(theStaffLoan);

			String toAddress = theStaffLoan.getEmployeeEmail();
			String employeeEmail = theStaffLoan.getEmployeeEmail();
			String fromAddress = theStaffLoan.getLoanOpeningSupervisorEmail();

			String subject = "Loan Ref " + theStaffLoan.getEmployeeLoanRef() + " disbursed";

			String mailBody = "Dear " + theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname() + ", "
					+ "\n\n Your loan with reference: " + theStaffLoan.getEmployeeLoanRef() + ""
					+ "has been disbursed with an amount of E:" + theStaffLoan.getDisbursedAmount()
					+ ". Please heck your account balance."

					+ "\n\nThis mail is Auto generated.";

			try {

				if (send_email) {

					mailService.send(employeeEmail, toAddress, fromAddress, subject, mailBody);
				}
			}

			catch (Exception e) {

				System.out.println(e.getMessage());
			}

			responseAction = "redirect:/staffLoan/staffLoanStatus";

		}

		return responseAction;

	}
	
	public String staffLoanLink() throws IOException {

		String url = null;
		String name = null;

		List<Application> applications = discoveryClient.getApplications().getRegisteredApplications();

		for (Application application : applications) {
			List<InstanceInfo> applicationsInstances = application.getInstances();
			for (InstanceInfo applicationsInstance : applicationsInstances) {

				name = applicationsInstance.getAppName();

				if (name.equals("STAFF-LOAN-SERVICE"))

				{

					url = applicationsInstance.getHomePageUrl();

					JSON_STAFFLOAN = url + "staffLoan/";
				}

			}

		}

		return JSON_STAFFLOAN;

	}

	@RequestMapping("/download_file")
	public ResponseEntity<InputStreamResource> downloadFile1(@RequestParam("fileName") String payslipLocation)
			throws IOException {

		MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, payslipLocation);

		File file = new File(payslipLocation);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok()

				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName()).contentType(mediaType)
				.contentLength(file.length()).body(resource);
	}

	@GetMapping("/securitiesShortPdf")
	public ModelAndView securitiesShortPdf(@ModelAttribute("theStaffLoan") StaffLoan theStaffLoan) {

		return new ModelAndView(new StaffLoan_Generate_Short_Securities(), "theStaffLoan", theStaffLoan);
	}

}
