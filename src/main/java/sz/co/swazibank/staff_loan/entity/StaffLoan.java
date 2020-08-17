package sz.co.swazibank.staff_loan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "staff_loan")
public class StaffLoan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "administation_fee")
	private float administationFee;

	@NotNull
	@Column(name = "allowances")
	private float allowances;

	@NotBlank
	@Size(min=10, max=50, message="Applicant comment is mandatory")
	@Column(name = "applicant_comment")
	private String applicantComment;

	@Column(name = "applicant_comment_date")
	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date applicantCommentDate;

	@Column(name = "basic_salary")
	private float basic_salary;


	@Size(min=5, max=50, message="Check Branch Adress")
	@Column(name = "branch_address")
	private String branchAddress;

	
	@Column(name = "box_address")
	private String boxAddress;

	@Column(name = "car_loan_deductions")
	private float carLoanDeductions;

	@Column(name = "car_loan_balance")
	private float carLoanBalance;

	@NotNull
	@Column(name = "cif")
	private String cif;

	@NotNull
	@Size(min=5, max=50, message="Check Branch Adress")
	@Column(name = "city")
	private String city;

	@Column(name = "city_council_rates_deductions")
	private float cityCouncilRatesDeductions;

	@Column(name = "city_council_rates_balance")
	private float cityCouncilRatesBalance;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "customer_age")
	private int customerAge;

	@Column(name = "date_created")
	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date dateCreated;

	@Column(name = "date_loan_required")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateLoanRequired;

	@Past(message = "Birth date can not be today") 
	@Column(name = "date_of_birth")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;

	@Column(name = "date_engaged")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateEngaged;

	@Future(message = "Date input is invalid for a first installment") 
	@Column(name = "date_of_first_installment")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfFirstInstallment;

	@Future(message = "Date input is invalid for a last installment.") 
	@Column(name = "date_Of_last_installment")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfLastInstallment;

	@NotNull
	@Size(min=5, max=30, message="Check department")
	@Column(name = "department")
	private String department;

	@Column(name = "disbursed_amount")
	private float disbursedAmount;

	@Column(name = "disbursement_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date disbursementDate;

	@Column(name = "educational_loan_deductions")
	private float educationalLoanDeductions;

	@Column(name = "educational_loan_balance")
	private float educationalLoanBalance;

	
	@Column(name = "time_elapsed") 
	private String timeElapsed;

	@NotNull
	@Size(min=3, max=8, message="Check employee code")
	@Column(name = "employee_code")
	private String employeeCode;

	@Email(message = "Enter a valid email address.") 
	@Column(name = "employee_email")
	private String employeeEmail;

	@NotNull
	@Size(min=2, max=3, message="Check grade")
	@Column(name = "employee_grade")
	private String employeeGrade;

	@NotNull
	@Size(min=3, max=50, message="Check employee code")
	@Column(name = "employee_position")
	private String employeePosition;

	@Column(name = "employee_loan_ref")
	private String employeeLoanRef;

	@Column(name = "establishment_fee")
	private float establishmentFee;

	@Column(name = "farm_small_holder_deductions")
	private float farm_smallHolderDeductions;

	@Column(name = "farm_small_holder_balance")
	private float farmSmallHolderBalance;

	@NotNull
	@Size(min=3, max=20, message="Check first name")
	@Column(name = "first_name")
	private String firstName;



	@Column(name = "hod")
	private String hod;

	@Column(name = "hod_comment")
	private String hodComment;

	@Column(name = "hod_comment_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date hodCommentDate;

	@Email(message = "Enter a valid email address.") 
	@Column(name = "hod_email")
	private String hodEmail;

	@Column(name = "housingloan_deductions")
	private float housingloanDeductions;

	@Column(name = "housingloan_balance")
	private float housingloanBalance;

	@Column(name = "payroll_administrator_comment")
	private String payrollAdministratorComment;

	@Column(name = "payroll_administrator")
	private String payrollAdministrator;

	@Column(name = "payroll_administrator_comment_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date payrollAdministratorCommentDate;
	
	
	@Email(message = "Enter a valid email address.") 
	@Column(name = "payroll_administrator_email")
	private String payrollAdministratorEmail;

	@NotNull
	@Size(min=13, max=13, message="Check national ID")
	@Column(name = "id_number")
	private String idNumber;

	@Column(name = "instalment")
	private float instalment;

	@Column(name = "insurances_deductions")
	private float insurancesDeductions;

	@Column(name = "insurances_balance")
	private float insurancesBalance;

	

	@Column(name = "last_updated")
	@UpdateTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date lastUpdated;

	@Column(name = "level_term_insurance")
	private float levelTermInsurance;

	@Column(name = "loan_and_charges")
	private float loanAndCharges;

	@Column(name = "loan_amount")
	private float loanAmount;

	@Column(name = "loan_amount_words")
	private String loanAmountWords;

	@Column(name = "loan_period")
	private int loanPeriod;

	@Column(name = "loan_opening_supervisor")
	private String loanOpeningSupervisor;

	@Column(name = "loan_opening_supervisor_comment")
	private String loanOpeningSupervisorComment;

	@Column(name = "loan_opening_supervisor_comment_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date loanOpeningSupervisorCommentDate;

	@Email(message = "Enter a valid email address.") 
	@Column(name = "loan_opening_supervisor_email")
	private String loanOpeningSupervisorEmail;

	@NotNull
	@Column(name = "location")
	private String location;

	@Column(name = "loan_status")
	private String loanStatus;

	@Column(name = "loan_type")
	private String loanType;

	@Column(name = "marital_status")
	private String maritalStatus;

	@PositiveOrZero(message = "You cannot have negative numbers of children.")
	@Column(name = "no_of_dependants")
	private int noOfDependants;

	@Column(name = "other_balance")
	private float otherBalance;

	@Column(name = "other_deductions")
	private float otherDeductions;

	@Column(name = "other_deductions_exc_leave_allowance")
	private float otherDeductionsExcLeaveAllowance;

	@Column(name = "personal_loan_deductions")
	private float personalLoanDeductions;

	@Column(name = "personal_loan_balance")
	private float personalLoanBalance;

	@Column(name = "purpose_of_loan")
	private String purposeOfLoan;

	@Column(name = "post_code")
	private String postCode;

	@Column(name = "repayment_period")
	private int repaymentPeriod;

	@NotNull
	@Size(min=5, max=50, message="Check residence min 5, max 50")
	@Column(name = "res_address")
	private String resAddress;

	@Column(name = "rural_housing_loan_deductions")
	private float ruralHousingLoanDeductions;

	@Column(name = "rural_housing_loan_balance")
	private float ruralHousingLoanBalance;

	@Column(name = "senior_hr_manager")
	private String seniorHrManager;

	@Email(message = "Enter a valid email address.") 
	@Column(name = "senior_hr_manager_email")
	private String seniorHrManagerEmail;

	@Column(name = "senior_hr_manager_comment")
	private String seniorHrManagerComment;

	@Column(name = "senior_hr_manager_comment_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date seniorHrManagerCommentDate;

	@Column(name = "statutory_deductions")
	private float statutoryDeductions;

	@Column(name = "studyloan_deductions")
	private float studyloanDeductions;

	@Column(name = "studyloan_balance")
	private float studyloanBalance;

	
	@Column(name = "supervisor_comment")
	private String supervisorComment;

	@Column(name = "supervisor_comment_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date supervisorCommentDate;

	@Email(message = "Check email") 
	@Column(name = "supervisor_email")
	private String supervisorEmail;

	@NotNull
	@Size(min=5, max=20, message="Check supervisor")
	@Column(name = "supervisor")
	private String supervisor;

	@NotNull
	@Size(min=3, max=20, message="Check surname")
	@Column(name = "surname")
	private String surname;

	@Column(name = "securities_supervisor")
	private String securitiesSupervisor;

	@Email(message = "Check email") 
	@Column(name = "securities_supervisor_email")
	private String securitiesSupervisorEmail;

	@Column(name = "securities_document_location")
	private String securitiesDocumentLocation;

	@Column(name = "securities_document_upload_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date securitiesDocumentUploadDate;

	@Column(name = "target_savings")
	private float targetSavings;
	
	@Column(name = "senior_hr_manager_decision")
	private String seniorHrManagerDecision;
	
	@Column(name = "total_time_taken")
	private Long totalTimeTaken;

	@NotNull
	@Size(min=8, max=13, message="Check telephone #")
	@Column(name = "telelephon_no")
	private String telelephoneNo;

	@Column(name = "total_repayment")
	private float totalRepayment;

	@Column(name = "total_interest")
	private float totalInterest;
	
	
	@Column(name = "payslip_location")
	String payslipLocation;
	
	@Column(name = "securities_supervisor_comment")
	String securitiesSupervisorComment;
	
	@Column(name = "securities_supervisor_comment_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	Date securitiesSupervisorCommentDate;
	
	@Column(name = "interest_rate")
	private float interestRate;
	
	@Column(name = "funeral_cover")
	private float funeralCover;

	
	
	

	
}
