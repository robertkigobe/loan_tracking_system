package sz.co.swazibank.staff_loan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "staff_loan_setup")
public class StaffLoanSetup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "payroll_admin")
	String payrollAdmin;
	
	@Column(name = "payroll_admin_email")
	String payrollAdminEmail;
	
	@Column(name = "senior_hr_manager")
	String seniorHrManager;
	
	@Column(name = "senior_hr_manager_email")
	String seniorHrManagerEmail;
	
	@Column(name = "securities_supervisor")
	String securitiesSupervisor;
	
	@Column(name = "securities_supervisor_email")
	String securitiesSupervisorEmail;
	
	@Column(name = "loan_opening_supervisor")
	String loanOpeningSupervisor;
	
	@Column(name = "loan_opening_supervisor_email")
	String loanOpeningSupervisorEmail;
	
	@Column(name = "interest_rate")
	private float interestRate;
	
	@Column(name = "funeral_cover")
	private float funeralCover;
	
	
	public StaffLoanSetup() {}
}
