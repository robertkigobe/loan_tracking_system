package sz.co.swazibank.staff_loan.service;

import java.util.List;

import sz.co.swazibank.staff_loan.entity.StaffLoan;

public interface StaffLoanService {
	
	public List<StaffLoan> findAll();
	
	public void saveAndFlush(StaffLoan theStaffLoan);

	public StaffLoan findStaffLoanById(int theId);
	
	public List<StaffLoan> findInProgress();
	
	public List<StaffLoan> findDisbursed();
	
	public List<StaffLoan> findCancelled();
	

}
