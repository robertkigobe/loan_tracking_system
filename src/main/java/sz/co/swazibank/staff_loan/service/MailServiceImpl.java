package sz.co.swazibank.staff_loan.service;

import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl  implements MailService{
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void send(String employeeEmail, String toAddress, String fromAddress, String subject, String mailBody) throws Exception {
		// TODO Auto-generated method stub
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setFrom(fromAddress);
		mimeMessageHelper.setTo(toAddress);
		mimeMessageHelper.setCc(employeeEmail);
		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setText(mailBody);
		mimeMessageHelper.setSentDate(new Date());
		javaMailSender.send(mimeMessage);
		
	}

}
