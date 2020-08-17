package sz.co.swazibank.staff_loan.service;

public interface MailService {
	
	public void send(String employeeEmail, String toAddress, String fromAddress, String subject, String mailBody) throws Exception;

}
