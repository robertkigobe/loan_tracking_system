package sz.co.swazibank.staff_loan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sz.co.swazibank.staff_loan.entity.StaffLoan;

@Repository
public interface StaffLoanRepository extends JpaRepository<StaffLoan, Integer>{
	
	StaffLoan findStaffLoanById(int id);
	
	@Query(value="SELECT * FROM swazibank_intranet.staff_loan where loan_status != 'Loan disbursed' order by last_updated desc", nativeQuery=true)
	List<StaffLoan> findInProgress();
	
	@Query(value="SELECT * FROM swazibank_intranet.staff_loan where loan_status = 'Loan disbursed' order by last_updated desc", nativeQuery=true)
	List<StaffLoan> findDisbursed();
	
	@Query(value="SELECT * FROM swazibank_intranet.staff_loan where loan_status = 'Loan Cancelled' order by last_updated desc", nativeQuery=true)
	List<StaffLoan> findCancelled();
	
}
