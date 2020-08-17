package sz.co.swazibank.staff_loan.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sz.co.swazibank.staff_loan.dao.StaffLoanRepository;
import sz.co.swazibank.staff_loan.entity.StaffLoan;

@Service
public class StaffLoanServiceImpl implements StaffLoanService {

	private StaffLoanRepository staffLoanRepository;

	@Autowired
	public StaffLoanServiceImpl(StaffLoanRepository theStaffLoanRepository) {

		staffLoanRepository = theStaffLoanRepository;	
		
	}

	@Override
	public List<StaffLoan> findAll() {
		return staffLoanRepository.findAll();
	}

	@Override
	public void saveAndFlush(StaffLoan theStaffLoan) {
		staffLoanRepository.save(theStaffLoan);

	}

	@Override
	public StaffLoan findStaffLoanById(int theId) {

		return staffLoanRepository.findStaffLoanById(theId);

	}

	@Override
	public List<StaffLoan> findInProgress() {
		// TODO Auto-generated method stub
		return staffLoanRepository.findInProgress();
	}

	@Override
	public List<StaffLoan> findDisbursed() {
		
		return staffLoanRepository.findDisbursed();
	}

	@Override
	public List<StaffLoan> findCancelled() {
		// TODO Auto-generated method stub
		return staffLoanRepository.findCancelled();
	}

}
