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
@Getter
@Setter
@NoArgsConstructor
@Table(name = "personal_loan_setup")
public class PersonalLoanSetup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "floating_rate")
	private float floatingrate;
	
	@Column(name = "funeral_cover")
	private float funeralCover;
	
	@Column(name = "management_fee")
	private float managementFee;
	
	@Column(name = "prime_rate")
	private float primeRate;
	
	@Column(name = "credit_analyst")
	private String creditAnalyst;
	
	@Column(name = "credit_analyst_email")
	private String creditAnalystEmail;

	@Column(name = "securities_supervisor")
	private String securitiesSupervisor;

	@Column(name = "securities_supervisor_email")
	private String securitiesSupervisorEmail;

	@Column(name = "loan_disbursement_supervisor")
	private String loanDisbursementSupervisor;

	@Column(name = "loan_disbursement_supervisor_email")
	private String loanDisbursementSupervisorEmail;
	
	@Column(name = "manager_credit")
	private String managerCredit;

	@Column(name = "manager_credit_email")
	private String managerCreditEmail;

}
