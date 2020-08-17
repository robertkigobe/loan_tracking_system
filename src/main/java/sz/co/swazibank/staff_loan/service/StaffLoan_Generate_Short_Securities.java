package sz.co.swazibank.staff_loan.service;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.FinanceLib;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import sz.co.swazibank.staff_loan.entity.StaffLoan;

public class StaffLoan_Generate_Short_Securities extends AbstractPdfView {

	@Value("${app.server}")
	private String server;
	

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		StaffLoan theStaffLoan = (StaffLoan) model.get("theStaffLoan");
		
		Image img = Image.getInstance("http://172.16.1.59:8100/img/logo.jpg"); //works perfect
		//URL img  = getClass().getResource("/static/images/header.png");
		
		

		////////////// Date calculations////////////////
		// Date date = Calendar.getInstance().getTime();
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dobStrDate = dateFormat.format(theStaffLoan.getDateOfBirth());
		String loanStartStrDate = dateFormat.format(theStaffLoan.getDateOfFirstInstallment());
		String loanendStrDate = dateFormat.format(theStaffLoan.getDateOfLastInstallment());
		String loanTodayStrDate = dateFormat.format(new Date());

		// employee age
		LocalDate dateOfBirth = LocalDate.parse(dobStrDate);
		LocalDate now = LocalDate.now();
		long empAge = ChronoUnit.YEARS.between(dateOfBirth, now);

		// loan age
		LocalDate loanStartDate = LocalDate.parse(loanStartStrDate);
		LocalDate loanTodayDate = LocalDate.parse(loanTodayStrDate);
		LocalDate loanEndDate = LocalDate.parse(loanendStrDate);
		long loanAge = ChronoUnit.MONTHS.between(loanStartDate, loanEndDate) + 1;

		/////////// date month and year calculation//////
		int dayOneP = 0;
		String dayOne = null;
		int monthOne = 0;
		int yearOne = 0;
		String monthOneV = null;

		try {

			dayOneP = loanStartDate.getDayOfMonth();
			monthOne = loanStartDate.getMonthValue();
			yearOne = loanStartDate.getYear();

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (dayOneP == 1) {
			dayOne = "1st";
		} else if (dayOneP == 2) {
			dayOne = "2nd";
		} else if (dayOneP == 3) {
			dayOne = "3rd";
		} else if (dayOneP == 4) {
			dayOne = "4th";
		} else if (dayOneP == 5) {
			dayOne = "5th";
		} else if (dayOneP == 6) {
			dayOne = "6th";
		} else if (dayOneP == 7) {
			dayOne = "7th";
		} else if (dayOneP == 8) {
			dayOne = "8th";
		} else if (dayOneP == 9) {
			dayOne = "9th";
		} else if (dayOneP == 10) {
			dayOne = "10th";
		} else if (dayOneP == 11) {
			dayOne = "11tt";
		} else if (dayOneP == 12) {
			dayOne = "12th";
		} else if (dayOneP == 13) {
			dayOne = "13th";
		} else if (dayOneP == 14) {
			dayOne = "14th";
		} else if (dayOneP == 15) {
			dayOne = "15th";
		} else if (dayOneP == 16) {
			dayOne = "16th";
		} else if (dayOneP == 17) {
			dayOne = "17th";
		} else if (dayOneP == 18) {
			dayOne = "18th";
		} else if (dayOneP == 19) {
			dayOne = "19th";
		} else if (dayOneP == 20) {
			dayOne = "20th";
		} else if (dayOneP == 21) {
			dayOne = "21st";
		} else if (dayOneP == 22) {
			dayOne = "22nd";
		} else if (dayOneP == 23) {
			dayOne = "23rd";
		} else if (dayOneP == 24) {
			dayOne = "24th";
		} else if (dayOneP == 25) {
			dayOne = "25th";
		} else if (dayOneP == 26) {
			dayOne = "26th";
		} else if (dayOneP == 27) {
			dayOne = "27th";
		} else if (dayOneP == 28) {
			dayOne = "28th";
		} else if (dayOneP == 29) {
			dayOne = "29th";
		} else if (dayOneP == 30) {
			dayOne = "30th";
		} else if (dayOneP == 31) {
			dayOne = "31st";
		}

		if (monthOne == 1) {
			monthOneV = "January";
		} else if (monthOne == 2) {
			monthOneV = "February";
		} else if (monthOne == 3) {
			monthOneV = "March";
		} else if (monthOne == 4) {
			monthOneV = "April";
		} else if (monthOne == 5) {
			monthOneV = "May";
		} else if (monthOne == 6) {
			monthOneV = "June";
		} else if (monthOne == 7) {
			monthOneV = "July";
		} else if (monthOne == 8) {
			monthOneV = "August";
		} else if (monthOne == 9) {
			monthOneV = "September";
		} else if (monthOne == 10) {
			monthOneV = "October";
		} else if (monthOne == 11) {
			monthOneV = "November";
		} else if (monthOne == 12) {
			monthOneV = "December";
		}

///////////date month and year for application date//////
		int dayOneP1 = 0;
		String dayOne1 = null;
		int monthOne1 = 0;
		int yearOne1 = 0;
		String monthOneV1 = null;

		try {

			dayOneP1 = loanTodayDate.getDayOfMonth();
			monthOne1 = loanTodayDate.getMonthValue();
			yearOne1 = loanTodayDate.getYear();

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (dayOneP1 == 1) {
			dayOne1 = "1st";
		} else if (dayOneP == 2) {
			dayOne1 = "2nd";
		} else if (dayOneP == 3) {
			dayOne1 = "3rd";
		} else if (dayOneP == 4) {
			dayOne1 = "4th";
		} else if (dayOneP == 5) {
			dayOne1 = "5th";
		} else if (dayOneP == 6) {
			dayOne1 = "6th";
		} else if (dayOneP == 7) {
			dayOne1 = "7th";
		} else if (dayOneP == 8) {
			dayOne1 = "8th";
		} else if (dayOneP == 9) {
			dayOne1 = "9th";
		} else if (dayOneP == 10) {
			dayOne1 = "10th";
		} else if (dayOneP1 == 11) {
			dayOne1 = "11th";
		} else if (dayOneP == 12) {
			dayOne1 = "12th";
		} else if (dayOneP == 13) {
			dayOne1 = "13th";
		} else if (dayOneP == 14) {
			dayOne1 = "14th";
		} else if (dayOneP == 15) {
			dayOne1 = "15th";
		} else if (dayOneP == 16) {
			dayOne1 = "16th";
		} else if (dayOneP == 17) {
			dayOne1 = "17th";
		} else if (dayOneP == 18) {
			dayOne1 = "18th";
		} else if (dayOneP == 19) {
			dayOne1 = "19th";
		} else if (dayOneP == 20) {
			dayOne1 = "20th";
		} else if (dayOneP1 == 21) {
			dayOne1 = "21st";
		} else if (dayOneP == 22) {
			dayOne1 = "22nd";
		} else if (dayOneP == 23) {
			dayOne1 = "23rd";
		} else if (dayOneP == 24) {
			dayOne1 = "24th";
		} else if (dayOneP == 25) {
			dayOne1 = "25th";
		} else if (dayOneP == 26) {
			dayOne1 = "26th";
		} else if (dayOneP == 27) {
			dayOne1 = "27th";
		} else if (dayOneP == 28) {
			dayOne1 = "28th";
		} else if (dayOneP == 29) {
			dayOne1 = "29th";
		} else if (dayOneP == 30) {
			dayOne1 = "30th";
		} else if (dayOneP1 == 31) {
			dayOne1 = "31st";
		}

		if (monthOne1 == 1) {
			monthOneV1 = "January";
		} else if (monthOne1 == 2) {
			monthOneV1 = "February";
		} else if (monthOne1 == 3) {
			monthOneV1 = "March";
		} else if (monthOne1 == 4) {
			monthOneV1 = "April";
		} else if (monthOne1 == 5) {
			monthOneV1 = "May";
		} else if (monthOne1 == 6) {
			monthOneV1 = "June";
		} else if (monthOne1 == 7) {
			monthOneV1 = "July";
		} else if (monthOne1 == 8) {
			monthOneV1 = "August";
		} else if (monthOne1 == 9) {
			monthOneV1 = "September";
		} else if (monthOne1 == 10) {
			monthOneV1 = "October";
		} else if (monthOne1 == 11) {
			monthOneV1 = "November";
		} else if (monthOne1 == 12) {
			monthOneV1 = "December";
		}

		////////////// Date calculations////////////////

		// Add fonts for the document
		Font body1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
		Font value_body = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		Font heading1 = FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.NORMAL);
		Font heading2 = FontFactory.getFont(FontFactory.TIMES_BOLD, 10, Font.NORMAL);

		// ////////////////armotization /////////////////////

		NumberFormat.getCurrencyInstance();
		Date startDate2 = theStaffLoan.getDateOfFirstInstallment();

		double r = (theStaffLoan.getInterestRate() / 100) / 12;
		double presentValue = theStaffLoan.getLoanAmount();
		float instalment = 0;
		double beginingBalance = 0;
		double totalEarlyPayments = 0;
		double extraPayment = 0;
		double totalPayment = 0;
		double principal = 0;
		double finalValue = 0;
		double interest = 0;
		double endingBalance = 0;
		double totalInterest = 0;
		totalInterest = interest * loanAge;

		theStaffLoan.getOtherDeductions();
		theStaffLoan.getInsurancesDeductions();
		theStaffLoan.getCityCouncilRatesDeductions();
		theStaffLoan.getEducationalLoanDeductions();
		theStaffLoan.getPersonalLoanDeductions();
		theStaffLoan.getCarLoanDeductions();
		theStaffLoan.getFarm_smallHolderDeductions();
		theStaffLoan.getRuralHousingLoanDeductions();
		theStaffLoan.getHousingloanDeductions();
		theStaffLoan.getInsurancesBalance();
		theStaffLoan.getCityCouncilRatesBalance();

		theStaffLoan.getEducationalLoanBalance();
		theStaffLoan.getPersonalLoanBalance();
		theStaffLoan.getCarLoanBalance();
		theStaffLoan.getFarmSmallHolderBalance();
		theStaffLoan.getRuralHousingLoanBalance();
		theStaffLoan.getHousingloanBalance();

		theStaffLoan.getAllowances();
		float basicSalary = theStaffLoan.getBasic_salary();

		theStaffLoan.getOtherDeductionsExcLeaveAllowance();
		theStaffLoan.getTargetSavings();
		theStaffLoan.getStatutoryDeductions();
		float totalDeductions = 0;
		float totalBalances = 0;
		float netSalary = 0;
		double cummulativePercentage = 0;
		double cummulativePercentageB = 0;
		float repaymentAmount = 0;

		double x = 0;
		double singlePremium = 0;
		double levelTermInsurance = 0;
		double initiationfee = 0;
		double loanAndCharges = 0;

		if (empAge <= 29) {
			if (loanAge < 13) {
				x = 6.35;
			} else if (loanAge < 25) {
				x = 12.0;
			} else if (loanAge < 37) {
				x = 17.4;
			} else if (loanAge < 49) {
				x = 22.75;
			} else if (loanAge < 61) {
				x = 27.8;
			}

		} else if (empAge <= 39) {
			if (loanAge < 13) {
				x = 10.5;
			} else if (loanAge < 25) {
				x = 19.50;
			} else if (loanAge < 37) {
				x = 27.65;
			} else if (loanAge < 49) {
				x = 35.1;
			} else if (loanAge < 61) {
				x = 42.1;
			}

		}

		else if (empAge <= 49) {
			if (loanAge < 13) {
				x = 8.55;
			} else if (loanAge < 25) {
				x = 16.85;
			} else if (loanAge < 37) {
				x = 25.4;
			} else if (loanAge < 49) {
				x = 32.0;
			} else if (loanAge < 61) {
				x = 37.75;
			}

		}

		else if (empAge <= 59) {
			if (loanAge < 13) {
				x = 13.45;
			} else if (loanAge < 25) {
				x = 25.5;
			} else if (loanAge < 37) {
				x = 37.2;
			} else if (loanAge < 49) {
				x = 48.3;
			} else if (loanAge < 61) {
				x = 62.0;
			}

		}

		else if (empAge >= 60) {
			if (loanAge < 13) {
				x = 21.9;
			} else if (loanAge < 25) {
				x = 43.45;
			} else if (loanAge < 37) {
				x = 66.3;
			} else if (loanAge < 49) {
				x = 90.75;
			} else if (loanAge < 61) {
				x = 115.45;
			}

		}

		singlePremium = 6.91 * loanAge;
		levelTermInsurance = (x * presentValue / 1000) + singlePremium;

		loanAndCharges = levelTermInsurance + initiationfee + presentValue;

		double totalRepayment = -(FinanceLib.pmt(r, loanAge, loanAndCharges, finalValue, false));

		totalDeductions = theStaffLoan.getOtherDeductions() + theStaffLoan.getInsurancesDeductions()
				+ theStaffLoan.getCityCouncilRatesDeductions() + theStaffLoan.getEducationalLoanDeductions()
				+ theStaffLoan.getPersonalLoanDeductions() + theStaffLoan.getCarLoanDeductions()
				+ theStaffLoan.getFarm_smallHolderDeductions() + theStaffLoan.getRuralHousingLoanDeductions()
				+ theStaffLoan.getHousingloanDeductions();

		totalBalances = theStaffLoan.getOtherBalance() + theStaffLoan.getInsurancesBalance()
				+ theStaffLoan.getCityCouncilRatesBalance() + theStaffLoan.getEducationalLoanBalance()
				+ theStaffLoan.getPersonalLoanBalance() + theStaffLoan.getCarLoanBalance()
				+ theStaffLoan.getFarmSmallHolderBalance() + theStaffLoan.getRuralHousingLoanBalance()
				+ theStaffLoan.getHousingloanBalance();

		netSalary = (theStaffLoan.getAllowances() + theStaffLoan.getBasic_salary())
				- (totalDeductions + theStaffLoan.getOtherDeductionsExcLeaveAllowance()
						+ theStaffLoan.getTargetSavings() + theStaffLoan.getStatutoryDeductions());
		cummulativePercentage = (totalDeductions + totalRepayment);
		cummulativePercentageB = (cummulativePercentage / Double.valueOf(basicSalary)) * 100;

		repaymentAmount = Float.valueOf(instalment) * loanAge;


		
	



		// img.scaleAbsolute(10, 10);
		document.add((Element) img);

		document.addTitle("LOAN CALCULATOR");

		Paragraph loanCalculator = new Paragraph("LOAN CALCULATOR\n\n", heading1);
		document.add(loanCalculator);

		PdfPTable table1 = new PdfPTable(2);
		table1.setWidthPercentage(100);
		table1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.setWidths(new int[] { 5, 8 });
		table1.getDefaultCell().setBorder(Rectangle.BOX);

		table1.addCell(new Paragraph("Item", value_body));
		table1.addCell(new Paragraph("Value", value_body));

		table1.addCell(new Paragraph("Applicant Name", body1));
		table1.addCell(new Paragraph(theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname(), body1));

		table1.addCell(new Paragraph("Date of birth", body1));
		table1.addCell(new Paragraph(dobStrDate, body1));
		table1.addCell(new Paragraph("Employee Age", body1));
		table1.addCell(new Paragraph(String.valueOf(empAge), body1));
		table1.addCell(new Paragraph("Loan Start Date", body1));
		table1.addCell(new Paragraph(loanStartStrDate, body1));

		table1.addCell(new Paragraph("Loan End Date", body1));
		table1.addCell(new Paragraph(loanendStrDate, body1));

		table1.addCell(new Paragraph("Loan Period (Months)", body1));
		table1.addCell(new Paragraph(String.valueOf(loanAge), body1));
		table1.addCell(new Paragraph("Loan Amount Requested before funeral cover", body1));
		table1.addCell(new Paragraph((String.format("%.2f", theStaffLoan.getLoanAmount())), body1));
		table1.addCell(new Paragraph("Funeral Cover", body1));
		table1.addCell(new Paragraph((String.format("%.2f", (theStaffLoan.getFuneralCover()))), body1));

		table1.addCell(new Paragraph("Level Term Insurance", body1));
		table1.addCell(new Paragraph(String.format("%.2f", levelTermInsurance), body1));

		table1.addCell(new Paragraph("Total Loan Amount", body1));
		table1.addCell(new Paragraph(String.format("%.2f", loanAndCharges), body1));

		document.add(table1);

		Paragraph loanArmotization = new Paragraph("LOAN ARMOTIZATION\n\n", heading1);
		document.add(loanArmotization);

		PdfPTable ftable2 = new PdfPTable(9);
		ftable2.setWidthPercentage(100);
		ftable2.setHorizontalAlignment(Element.ALIGN_LEFT);
		ftable2.setWidths(new int[] { 2, 3, 3, 3, 3, 3, 3, 3, 3 });
		ftable2.getDefaultCell().setBorder(Rectangle.BOX);

		ftable2.addCell(new Paragraph("Pmt No:", value_body));
		ftable2.addCell(new Paragraph("Pmt Date:", value_body));
		ftable2.addCell(new Paragraph("Begining Bal:", value_body));
		ftable2.addCell(new Paragraph("Scheduled Pmt:", value_body));
		ftable2.addCell(new Paragraph("Extra Pmt:", value_body));
		ftable2.addCell(new Paragraph("Total Pmt:", value_body));
		ftable2.addCell(new Paragraph("Principal:", value_body));
		ftable2.addCell(new Paragraph("Interest:", value_body));
		ftable2.addCell(new Paragraph("Ending Bal:", value_body));

		int x1 = 0;
		do {

			cal.setTime(startDate2);
			cal.add(Calendar.MONTH, x1);
			String dateTime = dateFormat.format(cal.getTime());

			if (x1 == 0) {

				beginingBalance = loanAndCharges;
				interest = loanAndCharges * r;
				totalPayment = extraPayment + totalRepayment;
				principal = totalPayment - interest;
				endingBalance = beginingBalance - principal;
			} else

			{
				beginingBalance = endingBalance;
				interest = beginingBalance * r;
				principal = totalPayment - interest;
				totalPayment = extraPayment + totalRepayment;
				endingBalance = beginingBalance - principal;

			}

			totalInterest += interest;
			totalEarlyPayments += extraPayment;

			ftable2.addCell(new Paragraph(String.valueOf(x1 + 1), body1));
			ftable2.addCell(new Paragraph(dateTime, body1));
			ftable2.addCell(new Paragraph(String.format("%.2f", beginingBalance), body1));
			ftable2.addCell(new Paragraph(String.format("%.2f", totalRepayment), body1));
			ftable2.addCell(new Paragraph(String.format("%.2f", extraPayment), body1));
			ftable2.addCell(new Paragraph(String.format("%.2f", totalPayment), body1));
			ftable2.addCell(new Paragraph(String.format("%.2f", principal), body1));
			ftable2.addCell(new Paragraph(String.format("%.2f", interest), body1));
			ftable2.addCell(new Paragraph(String.format("%.2f", endingBalance), body1));
			x1++;

		} while (endingBalance > 0);

		// need work ////// date loan required
		PdfPTable ftable1 = new PdfPTable(4);
		ftable1.setWidthPercentage(100);
		ftable1.setHorizontalAlignment(Element.ALIGN_LEFT);
		ftable1.setWidths(new int[] { 8, 5, 8, 5 });
		ftable1.getDefaultCell().setBorder(Rectangle.BOX);

		ftable1.addCell("");
		ftable1.addCell(new Paragraph("Loan Details", value_body));
		ftable1.addCell(" ");
		ftable1.addCell(new Paragraph("Loan Summary", value_body));
		ftable1.addCell(new Paragraph("Loan Amount", body1));
		ftable1.addCell(new Paragraph(String.format("%.2f", loanAndCharges), body1));
		ftable1.addCell(new Paragraph("Scheduled Payment", body1));
		ftable1.addCell(new Paragraph(String.format("%.2f", totalRepayment), body1));
		ftable1.addCell(new Paragraph("Annual Interest Rate", body1));
		ftable1.addCell(new Paragraph(String.format("%.2f", interest), body1));
		ftable1.addCell(new Paragraph("Scheduled # of   Payments", body1));
		ftable1.addCell(new Paragraph(String.valueOf(x1), body1));
		ftable1.addCell(new Paragraph("Loan Period (Y)", body1));
		ftable1.addCell(new Paragraph(String.valueOf(x1 / 12), body1));
		ftable1.addCell(new Paragraph("Actual # of Payments", body1));
		ftable1.addCell(new Paragraph(String.valueOf(x1), body1));
		ftable1.addCell(new Paragraph("Payments per year", body1));
		ftable1.addCell(new Paragraph("12", body1));
		ftable1.addCell(new Paragraph("Total Early Payments", body1));
		ftable1.addCell(new Paragraph(String.valueOf(totalEarlyPayments), body1));

		ftable1.addCell(new Paragraph("First Installment Date", body1));
		ftable1.addCell(new Paragraph(loanStartStrDate, body1));
		ftable1.addCell(new Paragraph("Total Interest", body1));
		ftable1.addCell(new Paragraph(String.valueOf(totalInterest), body1));

		ftable1.addCell(new Paragraph("Last Installment Date", body1));
		ftable1.addCell(new Paragraph(loanendStrDate, body1));
		ftable1.addCell(new Paragraph("", body1));
		ftable1.addCell(new Paragraph("", body1));

		document.add(ftable1);
		document.add(new Paragraph("\n"));
		document.add(ftable2);

		/*
		 * document.newPage(); document.add(img); Paragraph nl = new Paragraph(
		 * "Pre Quotation\n\n", heading1); document.add(nl);
		 * 
		 * PdfPTable finfac = new PdfPTable(2); finfac.setWidthPercentage(100);
		 * finfac.setHorizontalAlignment(Element.ALIGN_LEFT); finfac.setWidths(new int[]
		 * { 5, 5 }); finfac.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		 * 
		 * finfac.addCell("Between:"); finfac.
		 * addCell("Eswatini Development and Savings Bank\n(Hereinafter referred to as �the bank�)"
		 * ); finfac.addCell(" \n"); finfac.addCell(" \n");
		 * 
		 * finfac.addCell("Physical Address:\n(The Bank)"); finfac.
		 * addCell("Eswatini Bank Head Office, Engungwini Building, Gwamile Street,Mbabane�)"
		 * );
		 * 
		 * finfac.addCell(""); finfac.addCell("______________________________________");
		 * 
		 * finfac.addCell("And"); finfac.addCell(theStaffLoan.getFirstName() +
		 * " "+theStaffLoan.getSurname());
		 * 
		 * finfac.addCell(""); finfac.addCell("______________________________________");
		 * 
		 * finfac.addCell("Postal Address"); finfac.addCell(theStaffLoan.getBoxAddress()
		 * + " " + theStaffLoan.getCity()); finfac.addCell(" \n");
		 * finfac.addCell(" \n");
		 * 
		 * finfac.addCell(new Paragraph("The Loan", heading2)); finfac.addCell(" \n");
		 * document.add(finfac);
		 * 
		 * PdfPTable finfac2 = new PdfPTable(2); finfac2.setWidthPercentage(100);
		 * finfac2.setHorizontalAlignment(Element.ALIGN_LEFT); finfac2.setWidths(new
		 * int[] { 5, 8 }); finfac2.getDefaultCell().setBorder(Rectangle.BOX);
		 * 
		 * finfac2.addCell("Loan Type: "); finfac2.addCell(theStaffLoan.getLoanType());
		 * finfac2.addCell("Amount: ");
		 * finfac2.addCell(String.valueOf(theStaffLoan.getLoanAmount()));
		 * finfac2.addCell("Application Fees (0)%: "); finfac2.addCell("");
		 * finfac2.addCell("Establishment Fees (0)%: "); finfac2.addCell("");
		 * finfac2.addCell("Level term: ");
		 * finfac2.addCell(String.valueOf(levelTermInsurance));
		 * finfac2.addCell("Total Collectable: ");
		 * finfac2.addCell(String.valueOf(loanAndCharges));
		 * finfac2.addCell("Repayment Period: ");
		 * finfac2.addCell(String.valueOf(loanAge));
		 * finfac2.addCell("Repayment Amount: ");
		 * finfac2.addCell(String.valueOf(instalment));
		 * finfac2.addCell("Commencement Date: "); finfac2.addCell(loanStartStrDate);
		 * finfac2.addCell("End Date: "); finfac2.addCell(loanendStrDate);
		 * document.add(finfac2);
		 * 
		 * Phrase ffp1 = new Phrase(); ffp1.add(new Chunk("Interest\n", heading2));
		 * ffp1.add(new
		 * Chunk("The loan shall bear an interest at a rate equal E ",body1));
		 * ffp1.add(new Chunk(String.valueOf(instalment), value_body)); ffp1.add(new
		 * Chunk(" compounded monthly commencing on the ", body1)); ffp1.add(new
		 * Chunk(" the   ", body1)); ffp1.add(new Chunk(loanStartStrDate, value_body));
		 * ffp1.add(new
		 * Chunk(" and thereafter on the last day of each and every successive month.",
		 * body1)); document.add(ffp1);
		 * 
		 * Phrase ffp2 = new Phrase(); ffp2.add(new Chunk("\nSecurity\n", heading2));
		 * ffp2.add(new
		 * Chunk("Notwithstanding anything to the contrary in this Agreement, the Borrower shall not be entitled to draw down under the loan unless until the Borrower has furnished the Bank with the Deposit or commitment fee and the following securities:"
		 * , body1)); ffp2.add(new Chunk("\n",body1)); ffp2.add(new
		 * Chunk("\n1.	Letter of Offer",body1)); ffp2.add(new
		 * Chunk("\n2.	The Agreement",body1)); ffp2.add(new
		 * Chunk("\n3.	Acknowledgement of Debt",body1)); ffp2.add(new
		 * Chunk("\n4.	Letter of Set Off",body1)); ffp2.add(new
		 * Chunk("\n5.	Irrevocable Pledge",body1)); ffp2.add(new
		 * Chunk("\n6.	Stop Order",body1)); ffp2.add(new
		 * Chunk("\n7.	Level Term Insurance",body1));
		 * 
		 * document.add(ffp2);
		 */

		// ///////////STAFF LOAN APPLICATION/////////////////
		document.newPage();
		document.add((Element) img);

		Paragraph applicationLetterParagraph1 = new Paragraph("STAFF LOAN APPLICATION\n", heading1);
		document.add(applicationLetterParagraph1);
		document.add(new Paragraph("\n"));

		Paragraph applicationLetterParagraph2 = new Paragraph("1. Staff Details\n", heading2);
		document.add(applicationLetterParagraph2);
		document.add(new Paragraph("\n"));

		PdfPTable tableLoanParticulars1 = new PdfPTable(2);
		tableLoanParticulars1.setWidthPercentage(100);
		tableLoanParticulars1.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableLoanParticulars1.setWidths(new int[] { 5, 8 });
		tableLoanParticulars1.getDefaultCell().setBorder(Rectangle.BOX);

		tableLoanParticulars1.addCell(new Paragraph("Name: ", body1));
		tableLoanParticulars1
				.addCell(new Paragraph((theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname()), body1));
		tableLoanParticulars1.addCell(new Paragraph("Employment No: ", body1));
		tableLoanParticulars1.addCell(new Paragraph((theStaffLoan.getEmployeeCode()), body1));
		tableLoanParticulars1.addCell(new Paragraph("Date of Birth: ", body1));
		tableLoanParticulars1.addCell(new Paragraph(dateOfBirth.toString(), body1));
		tableLoanParticulars1.addCell(new Paragraph("Branch: ", body1));
		tableLoanParticulars1.addCell(new Paragraph((theStaffLoan.getLocation()), body1));
		tableLoanParticulars1.addCell(new Paragraph("Position Held: ", body1));
		tableLoanParticulars1.addCell(new Paragraph((theStaffLoan.getEmployeePosition()), body1));
		tableLoanParticulars1.addCell(new Paragraph("Basic Monthly Salary: ", body1));
		tableLoanParticulars1.addCell(new Paragraph(String.valueOf(theStaffLoan.getBasic_salary()), body1));
		tableLoanParticulars1.addCell(new Paragraph("Position Grade: ", body1));
		tableLoanParticulars1.addCell(new Paragraph((theStaffLoan.getEmployeeGrade()), body1));
		tableLoanParticulars1.addCell(new Paragraph("Address: ", body1));
		tableLoanParticulars1.addCell(new Paragraph((theStaffLoan.getResAddress()), body1));
		tableLoanParticulars1.addCell(new Paragraph("No. of Dependants: ", body1));
		tableLoanParticulars1.addCell(new Paragraph((String.valueOf(theStaffLoan.getNoOfDependants())), body1));
		tableLoanParticulars1.addCell(new Paragraph("Tel / Cell: ", body1));
		tableLoanParticulars1.addCell(new Paragraph((theStaffLoan.getTelelephoneNo()), body1));

		document.add(tableLoanParticulars1);

		Paragraph applicationLetterParagraph3 = new Paragraph(
				"2. LOAN PURPOSE: " + (new Paragraph(theStaffLoan.getPurposeOfLoan(), value_body)), heading2);
		document.add(applicationLetterParagraph3);
		document.add(new Paragraph("\n"));

		PdfPTable tableLoanParticulars2 = new PdfPTable(3);
		tableLoanParticulars2.setWidthPercentage(100);
		tableLoanParticulars2.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableLoanParticulars2.setWidths(new int[] { 7, 3, 5 });
		tableLoanParticulars2.getDefaultCell().setBorder(Rectangle.BOX);

		tableLoanParticulars2.addCell(new Paragraph(("3. Loan Type: " + theStaffLoan.getLoanType()), heading2));
		tableLoanParticulars2.addCell(new Paragraph(("Loan Amount: " + loanAndCharges), heading2));
		tableLoanParticulars2.addCell(new Paragraph(("Date Loan is required: " + loanendStrDate), heading2));
		document.add(tableLoanParticulars2);

		Paragraph applicationLetterParagraph4 = new Paragraph("4. RECENT SALARY ADVICE DETAILS", heading2);
		document.add(applicationLetterParagraph4);

		PdfPTable tableLoanParticulars3 = new PdfPTable(2);
		tableLoanParticulars3.setWidthPercentage(100);
		tableLoanParticulars3.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableLoanParticulars3.setWidths(new int[] { 8, 4 });
		tableLoanParticulars3.getDefaultCell().setBorder(Rectangle.BOX);

		tableLoanParticulars3.addCell(new Paragraph(("Basic Salary:"), body1));
		tableLoanParticulars3
				.addCell(new Paragraph((" E " + String.valueOf(Float.valueOf(theStaffLoan.getBasic_salary()))), body1));

		tableLoanParticulars3.addCell(new Paragraph(("Allowances (exc. Overtime& leave all:"), body1));
		tableLoanParticulars3.addCell(new Paragraph((" E " + String.valueOf(theStaffLoan.getAllowances())), body1));

		tableLoanParticulars3.addCell(new Paragraph(("Total Earnings:"), body1));
		tableLoanParticulars3.addCell(new Paragraph(
				(" E " + String.valueOf(theStaffLoan.getAllowances() + theStaffLoan.getBasic_salary())), body1));

		document.add(tableLoanParticulars3);

		Paragraph applicationLetterParagraph5 = new Paragraph("5. Current Loans and Balances: ", heading2);
		document.add(applicationLetterParagraph5);
		document.add(new Paragraph("\n"));

		PdfPTable tableLoanParticulars4 = new PdfPTable(3);
		tableLoanParticulars4.setWidthPercentage(100);
		tableLoanParticulars4.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableLoanParticulars4.setWidths(new int[] { 5, 5, 5 });
		tableLoanParticulars4.getDefaultCell().setBorder(Rectangle.BOX);

		tableLoanParticulars4.addCell(new Paragraph(("ITEM"), body1));
		tableLoanParticulars4.addCell(new Paragraph(("DEDUCTION"), body1));
		tableLoanParticulars4.addCell(new Paragraph(("BALANCE"), body1));
		tableLoanParticulars4.addCell(new Paragraph(("(4) Housing Loan (Mortgage)"), body1));
		tableLoanParticulars4
				.addCell(new Paragraph((" E " + String.valueOf(theStaffLoan.getHousingloanDeductions())), body1));
		tableLoanParticulars4
				.addCell(new Paragraph((" E " + String.valueOf(theStaffLoan.getHousingloanBalance())), body1));

		tableLoanParticulars4.addCell(new Paragraph(("(5) Rural Housing Loan"), body1));
		tableLoanParticulars4
				.addCell(new Paragraph((" E " + String.valueOf(theStaffLoan.getRuralHousingLoanDeductions())), body1));
		tableLoanParticulars4
				.addCell(new Paragraph((" E " + String.valueOf(theStaffLoan.getRuralHousingLoanBalance())), body1));

		tableLoanParticulars4.addCell(new Paragraph(("(6) Farm / Small Holder"), body1));
		tableLoanParticulars4
				.addCell(new Paragraph((" E " + String.valueOf(theStaffLoan.getFarm_smallHolderDeductions())), body1));
		tableLoanParticulars4
				.addCell(new Paragraph((" E " + String.valueOf(theStaffLoan.getFarmSmallHolderBalance())), body1));

		tableLoanParticulars4.addCell(new Paragraph(("(7) Car Loan"), body1));
		tableLoanParticulars4
				.addCell(new Paragraph((" E " + String.valueOf(theStaffLoan.getCarLoanDeductions())), body1));
		tableLoanParticulars4.addCell(new Paragraph((" E " + String.valueOf(theStaffLoan.getCarLoanBalance())), body1));

		tableLoanParticulars4.addCell(new Paragraph(("(8) Personal Loan (36 Months)"), body1));
		tableLoanParticulars4
				.addCell(new Paragraph((" E " + String.valueOf(theStaffLoan.getPersonalLoanDeductions())), body1));
		tableLoanParticulars4
				.addCell(new Paragraph((" E " + String.valueOf(theStaffLoan.getPersonalLoanBalance())), body1));

		tableLoanParticulars4.addCell(new Paragraph(("(9) Educational Loan (12 Months)"), body1));
		tableLoanParticulars4
				.addCell(new Paragraph((" E " + String.valueOf(theStaffLoan.getEducationalLoanDeductions())), body1));
		tableLoanParticulars4
				.addCell(new Paragraph((" E " + String.valueOf(theStaffLoan.getEducationalLoanBalance())), body1));

		tableLoanParticulars4.addCell(new Paragraph(("(11) City Council Rates"), body1));
		tableLoanParticulars4
				.addCell(new Paragraph((" E " + String.valueOf(theStaffLoan.getCityCouncilRatesDeductions())), body1));
		tableLoanParticulars4
				.addCell(new Paragraph((" E " + String.valueOf(theStaffLoan.getCityCouncilRatesBalance())), body1));

		tableLoanParticulars4.addCell(new Paragraph(("(12) Insurances"), body1));
		tableLoanParticulars4
				.addCell(new Paragraph((" E " + String.valueOf(theStaffLoan.getInsurancesDeductions())), body1));
		tableLoanParticulars4
				.addCell(new Paragraph((" E " + String.valueOf(theStaffLoan.getInsurancesBalance())), body1));

		tableLoanParticulars4.addCell(new Paragraph(("(13) Other Loans"), body1));
		tableLoanParticulars4
				.addCell(new Paragraph((" E " + String.valueOf(theStaffLoan.getOtherDeductions())), body1));
		tableLoanParticulars4.addCell(new Paragraph((" E " + String.valueOf(theStaffLoan.getOtherBalance())), body1));

		tableLoanParticulars4.addCell(new Paragraph(("(14) Total"), body1));
		tableLoanParticulars4.addCell(new Paragraph((" E " + String.format("%.2f", (totalDeductions))), body1));
		tableLoanParticulars4.addCell(new Paragraph((" E " + String.format("%.2f", (totalBalances))), body1));

		document.add(tableLoanParticulars4);

		PdfPTable tableLoanParticulars5 = new PdfPTable(2);
		tableLoanParticulars5.setWidthPercentage(100);
		tableLoanParticulars5.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableLoanParticulars5.setWidths(new int[] { 8, 4 });
		tableLoanParticulars5.getDefaultCell().setBorder(Rectangle.BOX);

		tableLoanParticulars5.addCell(new Paragraph(("(15) Other deductions(excl. Leave allowance)"), body1));
		tableLoanParticulars5.addCell(
				new Paragraph((" E " + String.valueOf(theStaffLoan.getOtherDeductionsExcLeaveAllowance())), body1));

		tableLoanParticulars5.addCell(new Paragraph(("(16) Target Savings:"), body1));
		tableLoanParticulars5.addCell(new Paragraph((" E " + String.valueOf(theStaffLoan.getTargetSavings())), body1));

		tableLoanParticulars5.addCell(new Paragraph(("(17) Statutory Deductions(Paye+ Snpf)"), body1));
		tableLoanParticulars5
				.addCell(new Paragraph((" E " + String.valueOf(theStaffLoan.getStatutoryDeductions())), body1));

		tableLoanParticulars5.addCell(new Paragraph(("(18) NET SALARY (3) -  (14,15,16, 17)"), body1));
		tableLoanParticulars5.addCell(new Paragraph((" E " + String.format("%.2f", (netSalary))), body1));

		document.add(tableLoanParticulars5);

		Paragraph applicationLetterParagraph6 = new Paragraph("6. AMOUNT REQUIRED AND REPAYMENT ARRANGEMENTS: ",
				heading2);
		document.add(applicationLetterParagraph6);
		document.add(new Paragraph("\n"));

		PdfPTable tableLoanParticulars6 = new PdfPTable(4);
		tableLoanParticulars6.setWidthPercentage(100);
		tableLoanParticulars6.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableLoanParticulars6.setWidths(new int[] { 5, 3, 6, 3 });
		tableLoanParticulars6.getDefaultCell().setBorder(Rectangle.BOX);

		tableLoanParticulars6.addCell(new Paragraph(("NEW LOAN: " + loanAndCharges), body1));
		tableLoanParticulars6.addCell(new Paragraph(("INTEREST RATE:" + theStaffLoan.getInterestRate() + "%"), body1));
		tableLoanParticulars6.addCell(new Paragraph(("REPAYMENT PERIOD IN MONTHS: " + loanAge), body1));
		tableLoanParticulars6
				.addCell(new Paragraph(("INSTALMENT:" + " E " + String.format("%.2f", (totalRepayment))), body1));

		tableLoanParticulars6.addCell(" ");
		tableLoanParticulars6.addCell(" ");
		tableLoanParticulars6.addCell(new Paragraph(("CUM. PERCENTAGE (14 +19) / 3:"), body1));

		// needs edit/////
		// tableLoanParticulars6.addCell(new Paragraph(String.format("%.2f",
		// (cummulativePercentageB) + "%", body1)));

		tableLoanParticulars6.addCell(new Paragraph("%", body1));

		tableLoanParticulars6.addCell(" ");
		tableLoanParticulars6.addCell(" ");
		tableLoanParticulars6.addCell(new Paragraph(("19. NET PAY (18) - (19) :"), body1));
		tableLoanParticulars6
				.addCell(new Paragraph(" E " + String.format("%.2f", (netSalary - totalRepayment)), body1));

		document.add(tableLoanParticulars6);

		document.newPage();

		PdfPTable tableLoanParticulars7 = new PdfPTable(1);
		tableLoanParticulars7.setWidthPercentage(100);
		tableLoanParticulars7.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableLoanParticulars7.setWidths(new int[] { 5 });
		tableLoanParticulars7.getDefaultCell().setBorder(Rectangle.BOX);

		tableLoanParticulars7.addCell(new Paragraph("6.	APPLICANT DECLARATION AND AUTHORISATION"

				+ "\n\nThis application is made under the terms of SwaziBank staff loan Policy current at this time."
				+ "\n\nI declare that the information given here is correct.  To the best of my knowledge and belief I have not omitted any information pertinent this application."
				+ "\n\nI hereby authorize and request SwaziBank to deduct the repayments specified overleaf from my salary to the credit of the relative loans(s) until all outstanding balances and interest have been paid in full."
				+ "\n\nI hereby grant to SwaziBank a lien and right of set off over all accounts whatsoever standing to my credit at SwaziBank."
				+ "\n\nI hereby grant to SwaziBank a lien over any/all terminal benefit due to me with the right to apply such benefits as repayment of any outstanding loans and / or interest."
				+ "\n\nI understand that in the event of my leaving the employment of the bank for any reason the outstanding balances of any loans will immediately become due and payable. "
				+ "\n\nApplicant Comment: " + theStaffLoan.getApplicantComment() + "\n\n\nDated: "
				+ theStaffLoan.getApplicantCommentDate()
				+ "        SIGNATURE: ..................................................", body1));

		tableLoanParticulars7.addCell(new Paragraph(("7. Supervisor comments and recommendations."
				+ "\n\nI confirm that his request is within the terms of the Staff Loans Policy currently in force."
				+ "\n\nSpuervisor Comment" + theStaffLoan.getSupervisorComment() + "\n\n Dated: "
				+ theStaffLoan.getSupervisorCommentDate()
				+ "     SIGNATURE: .................................................."), value_body));

		tableLoanParticulars7
				.addCell(
						new Paragraph(
								("8.	MANAGER" + "\n\nManager Comment: " + theStaffLoan.getHodComment()
										+ "\n\nDated: " + theStaffLoan.getHodCommentDate()
										+ "    SIGNATURE: .................................................."),
								value_body));

		tableLoanParticulars7.addCell(new Paragraph(
				("9.	H.R. DIVISION" + "\n\nPayroll Officer Comment: " + theStaffLoan.getPayrollAdministratorComment()
						+ "\n\nDated: " + theStaffLoan.getPayrollAdministratorCommentDate()
						+ "    SIGNATURE: .................................................."),
				value_body));

		tableLoanParticulars7.addCell(new Paragraph(("10. Decision" + theStaffLoan.getSeniorHrManagerDecision()

				+ "\n\n" + theStaffLoan.getSeniorHrManagerComment() + "\n\nDated:"
				+ theStaffLoan.getSeniorHrManagerCommentDate()
				+ "SIGNATURE: .................................................."), value_body));

		document.add(tableLoanParticulars7);

		cal2.setTime(startDate2);
		cal2.add(Calendar.MONTH, x1);
		String dateTimeReview = dateFormat.format(cal2.getTime());

		Phrase hrSanction = new Phrase();
		hrSanction.add(new Chunk("MEMORANDUM-SANCTION", heading1));
		hrSanction.add(new Chunk("\nTO: CREDIT DEPARTMENT", value_body));
		hrSanction.add(new Chunk("\nFROM : HUMAN RESOURCES DEPARTMENT", value_body));
		hrSanction.add(new Chunk("\nDATE : " + theStaffLoan.getSeniorHrManagerCommentDate(), value_body));
		hrSanction.add(new Chunk("\nSTAFF NAME : " + theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname(),
				value_body));
		hrSanction.add(new Chunk("\nFACILITY : " + theStaffLoan.getLoanType(), value_body));
		hrSanction.add(new Chunk(
				"\n=======>AMOUNT " + theStaffLoan.getLoanAmount() + " RATE: " + theStaffLoan.getInterestRate()
						+ " REPAYMENT: " + totalRepayment + " EXPIRY: " + theStaffLoan.getDateOfLastInstallment(),
				value_body));
		hrSanction.add(new Chunk("\nDECISION:" + theStaffLoan.getSeniorHrManagerDecision(), value_body));
		hrSanction.add(new Chunk("\nNEXT REVIEWABLE DATE: " + dateTimeReview, value_body));
		hrSanction.add(new Chunk("\nPURPOSE: " + theStaffLoan.getPurposeOfLoan(), value_body));
		hrSanction.add(new Chunk("\nSECURITY: " + "\n1.	Letter of Offer and Acceptance " + "\n2.	Finance Facility "
				+ "\n3. Acknowledgment of debt " + "\n4. Level term insurance Policy" + "\n5. Stop order from source"
				+ "\nAny security held now, or at any time, shall be security for all liabilities of the borrower to the Bank. Security currently held and or that is required for the above loan is as listed above.",
				value_body));

		hrSanction
				.add(new Chunk("\nCONDITIONS: \n" + "1.	Disbursement must be effected upon perfection of security;\n"
						+ "2.	Account should be closely monitored.\n"
						+ "3.	Disbursement must be made when all loan past dues are cleared.", value_body));

		hrSanction.add(new Chunk("\n\n" + theStaffLoan.getSeniorHrManager(), value_body));
		hrSanction.add(new Chunk("\nSENIOR MANAGER CORPORATE SERVICES", value_body));

		document.add(hrSanction);

		// //LETTER OF OFFER AND ACCEPTANCE//////

		document.newPage();
		document.add((Element) img);

		Phrase loaHeader = new Phrase();
		loaHeader.add(new Chunk("LETTER OF OFFER AND ACCEPTANCE ", heading1));
		document.add(loaHeader);

		PdfPTable loa = new PdfPTable(2);
		loa.setWidthPercentage(100);
		loa.setHorizontalAlignment(Element.ALIGN_LEFT);
		loa.setWidths(new int[] { 5, 5 });
		loa.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Phrase loa1 = new Phrase();
		loa1.add(new Chunk("Date: " + theStaffLoan.getApplicantCommentDate(), value_body));
		loa1.add(new Chunk(
				"\n\nBetween: 	Eswatini Development and Savings Bank \n(herein after referred to as �Eswatini Bank� or �the Bank�)"
						+ "\n\nAND\n\n	",
				body1));
		loa1.add(new Chunk(
				theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname() + "\n" + theStaffLoan.getResAddress(),
				value_body));

		loa1.add(new Chunk(
				"\n\n(Hereinafter referred to as 'the Borrower)" + "\n\nReference is made to your application dated",
				body1));
		loa1.add(new Chunk("\n\nThis " + String.valueOf(dayOne1) + " day of " + monthOneV1 + " , " + yearOne1,
				value_body));
		loa1.add(new Chunk(" in response to which the Bank is pleased to offer you a finance facility of ", body1));
		loa1.add(new Chunk(" E" + loanAndCharges, value_body));
		loa1.add(new Chunk(
				" upon the terms and conditions stipulated hereunder and further subject to the terms and conditions contained in the Finance Facility Agreement and the Finance Facility security documents annexed hereto.  		"
						+ "\n\nIf you decide to accept this offer, kindly append your signature in the space provided hereunder and return it promptly to Eswatini Bank.		",
				body1));
		loa1.add(new Chunk("\n\n1. DECLARATION AND ACKNOWLEDGEMENT BY THE BORROWER ", value_body));
		loa1.add(new Chunk(
				"\n\n1.1.	The Borrower is aware that all the Bank�s rights in this offer, including rights of ownership in any movable or immovable assets that may be pledged as security for the borrower�s indebtedness to the Bank will remain with the Bank until the last installment of the Finance Facility Agreement has been paid in full."
						+ "\n\n1.2.	In contemplation thereof the Borrower :	"
						+ "\n\n1.2.1	Agrees to recognize EswatiniBank as the owner of the immovable assets, movable goods/ equipment as declared in paragraph 1.1 above.\n\n1.2.2	Warrants that prior to the Borrower signing the main agreement hereto, all particulars and facts already furnished by the Borrower to the Bank are true and correct."
						+ "\n\n1.2.3	Confirms that the finance facility the Borrower signs for herein constitutes and fits the purpose for which the borrower requires it.\n\n1.2.4	Declares that prior to signing this offer, no benefits not recorded herein were offered, given or promised by the Bank, or any official of the Bank, either directly or indirectly as an inducement to accept this offer.",
				body1));
		loa1.add(new Chunk(
				"\n1.2.5	Warrants that the initiative in connection with this transaction and the signing and acceptance of this offer emanated from the Borrower voluntarily and without any duress or undue influence.								"
						+ "\n\n1.2.6	Warrants further that the Borrower shall sign all relevant documents including, but not limited to the main agreement, security documents and schedules relating to this transaction prior to the disbursement of funds.",
				body1));

		loa1.add(new Chunk("\n\n2. PURPOSE OF THE FACILITY OFFERED BY THE BANK", value_body));
		loa1.add(new Chunk(
				"\n\nThe Finance Facility is granted to the Borrower for the purpose of STAFF PERSONAL LOAN. (The facility type will not necessarily be determined by its purpose)",
				body1));

		loa1.add(new Chunk("\n\n3. DURATION OF THE FACILITY", value_body));
		loa1.add(new Chunk("\n\nThe Finance Facility shall be repaid by the Borrower over a period of ", body1));
		loa1.add(new Chunk(String.valueOf(x1) + " MONTHS", value_body));
		loa1.add(new Chunk(" commencing from  ", body1));
		loa1.add(new Chunk(loanendStrDate, value_body));

		loa1.add(new Chunk("or from the date of signing the finance facility Agreement and to Expire on ", body1));
		loa1.add(new Chunk(loanendStrDate, value_body));

		loa1.add(new Chunk("\n\n4. INTEREST & RATE", value_body));
		loa1.add(new Chunk(
				"\n\nThe Finance Facility shall bear Interest which shall be linked to prime as determined from time to time by the Bank subject to the initial interest rate of 6.5% per annum.",
				body1));

		loa1.add(new Chunk("\n\n5. REPAYMENT", value_body));
		loa1.add(new Chunk(
				"\n\nThe Finance Facility shall be repaid to the Bank in the following manner (E.g. Whether by a lump sum, by",
				body1));
		loa1.add(new Chunk("  E " + String.valueOf(String.format("%.2f", totalRepayment)), value_body));
		loa1.add(new Chunk(" per Month Effective  ", body1));
		loa1.add(new Chunk("\nThis " + String.valueOf(dayOne) + " day of " + monthOneV + " , " + yearOne, value_body));
		loa1.add(new Chunk(" Unitl Loan is Fully Paid."
				+ "\nThe Borrower agrees that this offer may require the Borrower to facilitate repayments by means of authorizing a standing debit order against the Borrower�s account, whether it be held with the Bank or another institution. The Borrower further undertakes not to alter or nullify such debit instruction without the written approval of the Bank.",
				body1));

		loa.addCell(loa1);

		Phrase loa2 = new Phrase();

		loa2.add(new Chunk("6. REQUIRED SECURITY", value_body));
		loa2.add(new Chunk(
				"\nAs part of the requirements of the Finance Facility Agreement, the Borrower hereby offers the following items as the Bank�s security for the requested facility",
				body1));
		loa2.add(new Chunk("\n1.	Letter of Offer and Acceptance " + "\n2.	Finance Facility "
				+ "\n3. Acknowledgment of debt " + "\n4. Level term insurance Policy" + "\n5. Stop order from source",
				value_body));

		loa2.add(new Chunk("\n\n7. CONDITIONS PRECEDENT", value_body));
		loa2.add(new Chunk(
				"\n7.1 Notwithstanding anything to the contrary in this agreement, the Borrower shall not be entitled to draw on the facility offered unless and until the required security has been furnished and perfected to the satisfaction of the Bank.",
				body1));
		loa2.add(new Chunk("\n\n8. OTHER TERMS", value_body));
		loa2.add(new Chunk(
				"\n\n8.1	Disbursement of funds is further subject to approval by the Bank�s Legal Department."
						+ "\n\n8.2	The Bank reserves the right to withdraw this offer without notice to the Borrower if in its view the security pledged by the Borrower is insufficient or on grounds of change in circumstances of the borrower."
						+ "\n\n8.3	The Bank reserves the right to consider the outstanding portion of the loan to be due and payable by the Borrower on demand at any time."
						+ "\n\n8.4	This Finance Facility is subject to further terms & conditions per the Finance Facility Agreement document and related security documents."
						+ "\n\n8.5	All insurance premiums, municipal rates and taxes and all other expenses and charges which the Bank considers necessary or desirable to be disbursed for the preservation, repair and maintenance of the security provided, shall be for the Borrower�s account and shall be repayable to the Bank on demand."
						+ "\n\n8.6	The Borrower recognizes the right of the Bank to seize any or all items offered as security and preserve them where it deems necessary, or  to employ interim service providers to take care of the security pending resolution of  any issues in court or any other forum. The costs for such preservation of the security shall be borne by the Borrower through debit to his/her/its account by the bank."
						+ "\n\n8.7	The Bank shall be entitled to withdraw this offer if it  is not accepted or if the Borrower does not place the Bank in a position to lodge and perfect the security offered within thirty days (30) days from the date of  issue hereof.",
				body1));
		loa2.add(new Chunk("\n\n9. COSTS", value_body));
		loa2.add(new Chunk(
				"\nThe Borrower agrees that in the event of foreclosure of the Finance  Facility, for whatever reason(s), the Borrower shall be liable for all costs including legal costs at attorney and own client scale, collection commission, valuation costs, recovery costs, tracing costs, and all other related costs, charges and expenses.",
				body1));

		loa2.add(new Chunk("\n\n10. REPRESENTATION AND WARRANTIES", value_body));
		loa2.add(new Chunk(
				"\nThe Borrower represents and warrants that his/her /its/their acceptance of the terms and conditions of this offer has been duly authorized and does not contravene any law and that there is no material litigation or similar proceedings, to the knowledge of the borrower presently pending or threatened which would have adverse effect on the assets or business of the borrower.\n\nI/We the undersigned do hereby acknowledge that I/we have read and understood to our/my satisfaction the above conditions, which I /we accept wholly, freely and voluntarily. I further confirm that the information furnished to the Bank is true and correct in every respect.   I/We undertake to produce and/or sign all documentation as may be required to complete the security. I/we further authorize the opening of a Finance Facility account in my / our name for the purpose of servicing this facility.",
				body1));
		loa2.add(new Chunk(
				"\n\n---------------------------------------- -------------------------------------                   SIGNATURE                                       DATE                                                     \n\n(AUTHORISED BANK REPRESENTATIVE)\n\n---------------------------------------------------------------------	       SIGNATURE	(BORROWER)			\n\n���������������������\n\nCOMPANY STAMP (WHERE NECESSARY)",
				value_body));
		loa.addCell(loa2);

		document.add(loa);

		// //////////////////The Agreement //////////////////

		document.newPage();
		document.add((Element) img);

		Phrase fcaggheader = new Phrase();
		fcaggheader.add(new Chunk("FINANCE FACILLITY AGREEMENT", heading1));

		document.add(fcaggheader);
		document.add(new Chunk(""));

		PdfPTable finsecfac1 = new PdfPTable(2);
		finsecfac1.setWidthPercentage(100);
		finsecfac1.setHorizontalAlignment(Element.ALIGN_LEFT);
		finsecfac1.setWidths(new int[] { 5, 5 });
		finsecfac1.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Phrase fcsecAg1 = new Phrase();
		Phrase fcsecAg2 = new Phrase();
		Phrase fcsecAg3 = new Phrase();
		Phrase fcsecAg4 = new Phrase();
		Phrase fcsecAg5 = new Phrase();
		Phrase fcsecAg6 = new Phrase();

		fcsecAg2.add(new Chunk(""));
		fcsecAg3.add(new Chunk(""));
		fcsecAg4.add(new Chunk(""));

		fcsecAg1.add(new Chunk("Made and entered into by and between:", body1));

		fcsecAg1.add(new Chunk("\n\nEswatini Development & Savings Bank", value_body));
		fcsecAg1.add(new Chunk("(hereinafter  referred to as “the Bank”\nAnd\n)", body1));
		fcsecAg1.add(new Chunk(
				theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname() + "\n" + theStaffLoan.getResAddress(),
				value_body));
		fcsecAg1.add(new Chunk("(hereinafter referred to as “the borrower”)\n\n", body1));

		fcsecAg1.add(new Chunk("It is agreed as follows:", value_body));
		fcsecAg1.add(new Chunk("\n\n1.  Interpretation", value_body));
		fcsecAg1.add(new Chunk("\n1.1 In this agreement, unless the context otherwise indicates-"
				+ "\n(a)	The singular shall include the plural and vice versa:"
				+ "\n(b)	The words indicating gender shall import and include the other genders;"
				+ "\n(c)	The headings to this agreement are used for the sake of convenience and shall not govern the interpretation hereof;"
				+ "\n(d)	Any reference to a natural person shall include an artificial or corporate person and vice versa;"
				+ "\n(e)	“Borrower” means the Borrower in terms of this Agreement, either a private individual or juristic person, as the case may be;"
				+ "\n(f)	“Finance Facility” “Facility”  or  “Loan”  includes  Loan or Overdraft and any other advance and finance facility in terms of this Agreement;"
				+ "\n(g)	“Prime lending rate” or “prime” means the discount  rate as published (per cent, per annum, compounded monthly) from time to time by the Central Bank as certified by any manager of the Bank whose appointment and designation need not be proved."
				+ "\n(h)	“This Agreement” means this Finance Facility agreement, together with annexures hereto, including the Letter of Offer & Acceptance and the loan securities thereof;"
				+ "\n\n	1.2  This Agreement shall be binding to the Borrower and its successors in title and successors in law.",
				body1));

		fcsecAg1.add(new Chunk("\n\n2.  The Loan", value_body));

		fcsecAg1.add(new Chunk(
				"\nUpon and subject to the terms and conditions hereinafter set out, the Bank will provide or procure for the Borrower who hereby borrows the sum of",
				body1));
		fcsecAg1.add(new Chunk(
				"E" + String.valueOf(loanAndCharges) + " (Emalangeni" + theStaffLoan.getLoanAmountWords() + ")",
				value_body));
		fcsecAg1.add(new Chunk(
				" Subject to the requirements of Clauses 5, 6.1-6.3 below, and the provisions of the Letter of Offer & Acceptance, the Borrower shall be entitled to draw down under the loan.",
				body1));

		fcsecAg1.add(new Chunk("\n\n3.  Loan Interest", value_body));

		fcsecAg1.add(new Chunk("\n\n3.1	The loan shall bear interest at a rate equal to ", body1));
		fcsecAg1.add(new Chunk("6.5% per annum", value_body));
		fcsecAg1.add(new Chunk("compounded monthly."
				+ "\n3.2	Interest shall be calculated on the daily balance of the amount of the loan, plus any interest thereon outstanding from time to time."
				+ "\n3.3	Interest on the loan shall be paid to the Bank monthly in arrears on the last day of the month, the first of such payments to be made on the ",
				body1));
		fcsecAg1.add(
				new Chunk("This " + String.valueOf(dayOne) + " day of " + monthOneV + "," + yearOne + ",", value_body));
		fcsecAg1.add(new Chunk(", and thereafter on the last day of each and every successive month.", body1));

		fcsecAg1.add(new Chunk("\n\n4.  Repayment", value_body));

		fcsecAg1.add(new Chunk(
				"\n4.1	The loan capital together with interest thereon shall be repaid to the Bank in the following manner in monthly instalments E",
				body1));
		fcsecAg1.add(new Chunk(String.format("%.2f", totalRepayment), value_body));
		fcsecAg1.add(new Chunk(" effective", body1));
		fcsecAg1.add(new Chunk(" This " + String.valueOf(dayOne) + " day of " + monthOneV + "," + yearOne + ",",
				value_body));
		fcsecAg1.add(new Chunk(" and to continue until the loan is fully repaid."
				+ "\n4.2	The Borrower agrees that all payments to be made pursuant to this Agreement may be made by means of the debit order authorization in terms of Clause 20.",
				body1));

		fcsecAg1.add(new Chunk("\n\n5.  Conditions Precedent", value_body));

		fcsecAg1.add(new Chunk(
				"\n\nNotwithstanding anything to the contrary in this Agreement, the Borrower shall not be entitled to draw down under the loan unless and until the Borrower has furnished the Bank with the Deposit or commitment fee and the following security(ies):",
				body1));
		fcsecAg1.add(new Chunk("\n•	Finance Facility Agreement " + "\n•	Letter of Offer and Acceptance"
				+ "\n•	Letter of Set Off" + "\n•	Acknowledgement Of Debt " + "\n•	General Pledge"
				+ "\n•	Irrevocable Pledge " + "\n•	Stop order over salary", value_body));

		fcsecAg1.add(new Chunk("\n\n6.  Fees and Charges", value_body));
		fcsecAg1.add(new Chunk("\n6.1	Application Fee"
				+ "\nThe Borrower shall be charged a once off non-refundable application fee equal to", body1));
		fcsecAg1.add(new Chunk(" E0.00, ", value_body));
		fcsecAg1.add(new Chunk("payable by the Borrower upon the submission of the application. ", body1));

		fcsecAg2.add(new Chunk("6.2	Administration Fee: ", value_body));

		fcsecAg2.add(new Chunk(
				"The Borrower shall be charged with paying to the Bank a monthly administration fee equal to ", body1));
		fcsecAg2.add(new Chunk(" E0.00, ", value_body));
		fcsecAg2.add(new Chunk(" per month.", body1));

		fcsecAg2.add(new Chunk("\n\n6.3	Establishment and Application Fee:", value_body));
		fcsecAg2.add(new Chunk(
				"\nThe Bank shall be entitled to charge a non-refundable establishment fee and an application fee equivalent to the capital amount, "
						+ "payable by the Borrower upon the signature of this Agreement. The amount of Establishment fee due in terms hereof is ",
				body1));
		fcsecAg2.add(new Chunk(" E0.00, ", value_body));
		fcsecAg2.add(new Chunk(" and application fee is ", body1));
		fcsecAg2.add(new Chunk(" E0.00", value_body));

		fcsecAg2.add(new Chunk("\n\n6.4	Early Settlement Fee:", value_body));
		fcsecAg2.add(new Chunk(
				"The Borrower may, subject to 10 days’ notice to the Bank, prepay all or a portion of the loan, provided that the Borrower shall be liable to pay to the Bank, a once-off prepayment fee equal to 90 days interest. ",
				body1));

		fcsecAg2.add(new Chunk("\n\n6.5	Late Drawdown Fee:", value_body));
		fcsecAg2.add(new Chunk(
				"Should the Borrower fail to draw down the loan within 30 days of being entitled to do so, it should obtain a   written permission from the Bank in the event of any further delay from the said 30 days, failing which the Bank will be entitled to levy a late drawdown fee.",
				body1));

		fcsecAg2.add(new Chunk("\n\n6.6	Late Payment Fee:", value_body));
		fcsecAg2.add(new Chunk(
				"Should the Borrower fail to pay on due date any amounts falling due or payable to the Bank under or arising from this Agreement, then, without prejudice to such other rights as may accrue to the Bank  consequent upon such failure, such overdraft amounts shall bear additional finance charges at the rate of 2% per day of the overdue amount.",
				body1));

		fcsecAg2.add(new Chunk("\n\n6.7  Other Fees and Charges", value_body));
		fcsecAg2.add(new Chunk(
				"The list of fees and charges contained in clauses 6.1 to 6.6 may be changed at any time at the discretion of the bank without prior notice.",
				body1));

		fcsecAg2.add(new Chunk("\n\n7.  Breach", value_body));
		fcsecAg2.add(new Chunk(
				"\n\n7.1	In the event of the Borrower failing to observe and / or to perform any of the terms, conditions and or obligations of this or any other agreement which it may have with the bank, or defaulting in the punctual payment of any amount falling due in terms of this or any other Agreement it may have with the Bank, the Bank shall be entitled, without notice to the Borrower, to cancel this Agreement and withdraw the services with immediate effect.  In that event the bank shall be entitled to call on the loan and to foreclose on the securities and recover the full amount of the loan, interest accrued and any charges levied but unpaid, in terms of this Agreement.",
				body1));

		fcsecAg2.add(new Chunk(
				"\n\nThe following non exhaustive list shall constitute events of default entitling the Bank to call on the full loan amount plus   interest and to foreclose under this Agreement: "
						+ "\n(a)	Any judgement, attachment being made, and execution being levied against the Borrower;"
						+ "\n(b)	The Borrower being placed under judicial management or wound up, whether compulsorily or voluntarily, or compromising with any of its creditors, or committing an act of insolvency, as defined in the Eswatini Insolvency Act No. 81 of 1955; insanity ;unemployment or"
						+ "\n(c)	Stopping payment of any liquid document made payable to the Bank; or"
						+ "\n(d)	Without the prior written consent of the Bank, ceasing or intimating to the Bank its intention to cease to carry on its business; or"
						+ "\n(e)	Failing for whatever reason, at any time during the duration of this agreement, to record a trading profit during any one or more year’s trading; or"
						+ "\n(f)	The Borrower proposing any rescheduling, reorganisation or rearrangement of the whole, or part of the indebtedness to the Bank or any of the Borrower’s creditors;"
						+ "\n(g)	The Borrower’s business operations, or any significant part thereof being interrupted for a continuous period of at least three months or more;"
						+ "\n(h)	Any representation, warranty or statement made in, or in connection with this Agreement by or on behalf of the Borrower pursuant to this Agreement being found to be false or incorrect."
						+ "\n(i)	Occurrence of any fact or circumstance which in the opinion of the Bank may adversely affect the ability or willingness of the Borrower to comply with all or any of its obligations under this Agreement;"
						+ "\n(j)	Death, in the case of a natural person or a major share holder whose input is critical for the operation of the business.",
				body1));

		fcsecAg2.add(new Chunk(
				"\n\n7.2	The exercise of the rights by the bank in terms of this clause 7 shall be without prejudice, and/or in addition to any other rights which might thereupon be available to the Bank in terms of this Agreement or in law.",
				body1));

		fcsecAg3.add(new Chunk(
				"\n7.3	If the bank terminates the loan agreement in terms of the  provisions of  clause 7  the bank may in addition to any other rights it has;  set off any credit balances held by the Borrower in any other accounts with Eswatini Bank  that are due and payable, against the debt on the loan facility.",
				body1));

		fcsecAg3.add(new Chunk("\n\n8.  Project Management", value_body));

		fcsecAg3.add(new Chunk(
				"\n\n8.1	In the event of failure by the Borrower to honour obligations under a project financed by the Bank under this Agreement, for whatever reason, the Bank may at its sole discretion, and without prejudice to any rights it may have against the Borrower, in terms of this Agreement  or in law, elect to appoint a management on contract with or without staff, to manage and carry out the operations of the project for the purpose of repayment and settlement of the loan, interest thereon and related costs. "
						+ "\n\nThe Borrower shall upon written notice by the Bank of the decision herein, co-operate with the Bank "
						+ "\n\n8.2	The income generated shall in addition to being used for the loan repayment be, applied towards the reasonable running expenses of the project, which shall include but not be limited to, management, operational and legal costs.  No dividends shall be declared or paid"
						+ "\n\n8.3	Upon full settlement of the loan, and related costs, the Bank shall release the business project to the Borrower.",
				body1));

		fcsecAg3.add(new Chunk("\n\n9.  Change in Circumstances", value_body));

		fcsecAg3.add(new Chunk("\n\n9.1	If at any time during the currency of this Agreement –"

				+ "\n\n9.1.1	Any new law, ruling or regulation is promulgated given or adopted;"
				+ "\n9.1.2	There are any changes to the present or future law, ruling or regulation;"
				+ "\n9.1.3	There are any changes to the interpretation or administration of any law, ruling or regulation by any relevant monetary or fiscal authority;"
				+ "\n9.1.4	There is any compliance by the Bank with any directive or request, whether or not having the force of law, from any monetary or fiscal authority, which would or does –"
				+ "\n(a)	Subject the Bank to any taxes, duties, or other charges, in respect of this agreement or change the basis of taxation of the Bank in respect of payments of principal or interest/fees payable to the Bank (except for changes in the rate of taxation on the overall net income of the Bank); or"
				+ "\n(b)	Impose, modify or deem applicable any reverse, special deposit or similar requirement against assets of, deposits with or for the account of, or credit extended by the Bank, or"
				+ "\n(c)	Impose on the Bank any other obligation or condition affecting the loan or its commitment in terms of this agreement; And –"

				+ "\n\nThe result of any of the occurrences in 9.1.1 – 9.1.4 (a) – (c) above is to increase the cost to the Bank of making any advance or maintaining this Agreement or to reduce any amount or amounts received or receivable or loanable by the Bank hereunder by a sum which the Bank deems material, then the Borrower shall pay the Bank on demand and while such circumstances continue, such commitment fees as the Bank may impose or such additional amounts which will compensate the Bank for such additional costs incurred."

				+ "\n\n9.2	The Bank may give the Borrower thirty days notice of all amounts payable in terms of this clause 9.  A certificate signed by any manager of the Bank (whose appointment it shall not be necessary to prove) as to such additional amount(s) shall be prima facie proof for all purposes."

				+ "\n\n9.3	If the Borrower is required to pay additional amount(s) to the Bank pursuant to Clause 9.1 above, it shall be entitled to prepay any amounts owed in terms hereof without penalty, or to forthwith cancel any unutilized portion of the loan by giving the Bank thirty (30) days written notice thereof."

				+ "\n\n9.4  Notwithstanding the provisions of clause 9 the bank shall not be liable for any failure to  notify the  Borrower of any changes. ",
				body1));

		fcsecAg3.add(new Chunk("\n\n10.  Representations and Warranties", value_body));

		fcsecAg3.add(new Chunk("\n\nThe Borrower represents and warrants that – ", body1));

		fcsecAg3.add(new Chunk(
				"\n\n10.1	Its acceptance of the terms of this Agreement has been duly authorised and does not contravene any law or any contractual obligation binding upon it;"
						+ "\n10.2	There is no material litigation or similar proceedings, to the knowledge of the Borrower presently  Pending or threatened which would have a material adverse effect on the business or assets of the Borrower;"
						+ "\n10.3  The Borrower is not in default in respect of any of its obligations in respect of money advanced and that no event  specified in Clause 7 above has occurred or is continuing.",
				body1));

		fcsecAg3.add(new Chunk("\n\n11.  Certificate of Indebtedness", value_body));

		fcsecAg3.add(new Chunk(
				"\n\nA certificate signed by any manager of the Bank (whose appointment it shall not be necessary to prove) as to any indebtedness of the Borrower herein, or as to any other fact, shall be prima facie evidence of the Borrower’s indebtedness to the Bank or of such other fact for the purpose of any application or action, judgement or order or for any other purpose whatsoever.",
				body1));

		fcsecAg4.add(new Chunk("\n12.   Renunciation", value_body));

		fcsecAg4.add(new Chunk(
				"\n\nThe Borrower specifically renounces the legal exceptions non numeratae pecuniae, non causa debiti, revision of accounts and error of calculation (errore  calculi) de duobus Vel plu rubus reis debindi, beneficium ordinus seu execussionis, benefitium devisionis and all other exceptions which might or could be pleaded to the validity of the indebtedness, whether capital, finance charges or legal costs, or any part thereof, and declares that he is fully acquainted with the force, effect and meaning thereof, the same having been explained to the Borrower by the Bank.",
				body1));

		fcsecAg4.add(new Chunk("\n\n13. Domicilium", value_body));
		  
		 fcsecAg4.add(new Chunk("\n\n13.1 The parties respectively choose their domicilia citandi et executandi for all notices and processes to be given or served in pursuance hereof at the following addresses. (physical address)",body1));
		  
		 fcsecAg4.add(new Chunk("\n\nThe Bank: Eswatini Development and Savings Bank \nHead Office Engungwini \nBuilding Gwamile Street Mbabane", value_body));
		  
		 
		 fcsecAg4.add(new Chunk("\n\nThe Borrower: " + theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname() + "\n" + theStaffLoan.getResAddress(), value_body));
		  
		 fcsecAg4.add(new Chunk("\n\n13.2 Either of the parties shall be entitled to change its domicilium from "
		 		+ "time to time: Provided that any new domicilium selected by either of them "
		 		+ "shall be a physical address and shall be situate in the Kingdom of Eswatini "
		 		+ "and any such change shall only be effective upon receipt by the other party"
		 		+ "of notice in writing of such change. All other notices or communications"
		 		+ "intended for either party shall be made or given in writing at such party’s"
		 		+ "domicilium for the time being and, if forwarded by the Bank by prepaid"
		 		+ "registered post, shall be deemed to have been made or given three days after the date of posting.",body1));
		 
		 fcsecAg4.add(new Chunk("\n\n14. Entire Agreement", value_body));
		 		fcsecAg4.add(new Chunk("\n\n This agreement constitutes the entire agreement between "
		 				+ "the parties and any amendment, addition or alteration to the provisions "
		 				+ "hereof shall only be deemed to be of force and effect if such amendment, addition or alteration is reduced to writing and signed by the parties.",body1));
		  
		  
		  
		 		fcsecAg4.add(new Chunk("\n\n15. Costs and Expenses", value_body));
		  
		 				fcsecAg4.add(new Chunk("\n\nAll stamp duties payable in respect of this agreement or in respect of any "
		 						+ "guarantees or securities given in respect hereof and costs and expenses"
		 						+ " incurred by the Bank in connection with the preparation of this agreement and "
		 						+ "any securities relating thereto shall be for the account of the Borrower and "
		 						+ "shall be payable on demand. \n\nFurthermore, all legal costs as between attorney and his own client, tracing "
		 						+ "fees, valuation costs, collection commission, disbursements and fees of a like nature incurred by the Bank in successfully enforcing or defending any "
		 						+ "of the provisions of this Agreement, or any claim there under, shall be for "
		 						+ "the account of the Borrower and be payable on demand.",body1));
		  
		 				fcsecAg4.add(new Chunk("\n\n16. Dispute", value_body));
		  
		 				fcsecAg4.add(new Chunk("\n\n16.1 In the event of any dispute arising between the Bank and the Borrower"
		 						+ " relating to any matter under his Agreement, such dispute shall be resolved"
		  + " amicably by the parties, and if they fail to resolve it between themselves,"
		  + "the dispute may be referred to court by any aggrieved party."
		  
		  + "\n\n 16.2 In appropriate cases, and due to the technical nature of the matters"
		  + "involved, the Bank and the Borrower may by agreement refer the dispute to an"
		  + "Arbitrator jointly selected by the parties for resolution by arbitration .",body1));
		  
		 				fcsecAg4.add(new Chunk("\n\n17. Jurisdiction", value_body));
		  
		 				fcsecAg4.add(new Chunk("\n\n17.1 The Borrower hereby consents and submits to the jurisdiction of the"
		 						+ "magistrate’s court having jurisdiction over its person in respect of all"
		  + "proceedings connected with this agreement, notwithstanding that the amount"
		  + "claimed or the value of the matter in dispute exceeds such jurisdiction;"
		  + "provided that the Bank shall not be obliged to institute action in the"
		  + "magistrate’s court."
		  
		 
+ "\n\n17.2 Notwithstanding the provisions of Clause 17.1, the Bank shall be"
+ "entitled to institute all or any proceedings against the Borrower in"
		  + "connection with this Agreement in the High Court of Eswatini and the Borrower"
		  + "hereby consents and submits to the jurisdiction of that court and agrees that"
		  + "any costs awarded against the Borrower be awarded or paid in accordance with"
		  + "Clause 15 hereof on Attorney and Own Client Scale.",body1));
		 				fcsecAg4.add(new Chunk("\n\n18. Indulgence", value_body));
		  
		 				fcsecAg4.add(new Chunk("\n\n No relaxation or indulgence granted by the Bank to the Borrower from time to"
		 						+ "time shall be deemed to be a waiver of the Bank’s rights in terms hereof, nor"
		  + "shall any such relaxation or indulgence be deemed to be a novation or waiver"
		  + "of the terms and conditions of this Agreement.",body1));
		 
		 				fcsecAg5.add(new Chunk("\n\n19. Applicable Law", value_body));
		  
		 				fcsecAg5.add(new Chunk("\n\nThis Agreement shall in all respects be governed by and construed in"
				  + "accordance with the laws of the Kingdom of Eswatini and all disputes, actions"
		  + "and other matters in connection therewith shall be determined in accordance"
		  + "with such law.",body1));
		  
		  
		 				fcsecAg5.add(new Chunk("\n\n20. Debit Order Authorization", value_body));
		  
		 				fcsecAg5.add(new Chunk("\n\n20.1 The bank shall be entitled to originate debits to the Borrower’s account at:",body1));
		  
		 				fcsecAg5.add(new Chunk("\n\nBank: Eswatini Bank",value_body));
		  
		 				fcsecAg5.add(new Chunk("\n\nBranch: "+theStaffLoan.getBranchAddress() ,value_body));
		  
		 				fcsecAg5.add(new Chunk("\n\nCIF: " + theStaffLoan.getCif(),value_body));
		  
		 				fcsecAg5.add(new Chunk("\n\nor any other bank or branch to which the Borrower may subsequently transfer its account, with all amounts due or which might at any future time become due by the Borrower under this Agreement.",body1));
		 				fcsecAg5.add(new Chunk("\n\n20.2 This authority shall in no way be construed as constituting a novation, substitution or variation of the Borrower’s obligation under this agreement.",body1));
		  
		 				fcsecAg5.add(new Chunk("\n\n21. Execution", value_body));
		 				fcsecAg5.add(new Chunk("\n\nThis agreement is executed at  ", value_body));
		 				fcsecAg5.add(new Chunk(theStaffLoan.getLocation(), value_body));
		 				 
			 			fcsecAg5.add(new Chunk("\n\nfor and on behalf of the Bank by ( Full names):________________________________________ ", value_body));
			 			fcsecAg5.add(new Chunk("\n\nin her capacity as: ________________________________________ ", value_body));
			 			fcsecAg5.add(new Chunk("\n\n This " + String.valueOf(dayOne1) + " day of " + monthOneV1 + " , " + yearOne1 + "in the presence of the undersigned witness:", value_body));
			 			fcsecAg5.add(new Chunk("\n\nAs For the Bank: ___________________ \n\nWitness____________________", value_body));
		 				
		 				
		 				
			 			
			 			fcsecAg5.add(new Chunk("\n\n\nACKNOWLEDGEMENT OF DEBT AGREEMENT", heading1));

			 			fcsecAg5.add(new Chunk("\n\n 1.	I/We, the undersigned. ", body1));
			 			fcsecAg5.add(new Chunk(theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname(), value_body));
			 			fcsecAg5.add(new Chunk(
			 					"( The �debtor/s�), do hereby acknowledge myself/ourselves to be truly and lawfully indebted to Eswatini Development & Savings Bank (hereinafter referred to as �the Bank�) in the sum of E ",
			 					body1));
			 			fcsecAg5.add(new Chunk(String.valueOf(loanAndCharges), value_body));
			 			fcsecAg5.add(new Chunk(" for and in respect of ", body1));
			 			fcsecAg5.add(new Chunk(theStaffLoan.getLoanType() + " ", value_body));
			 			fcsecAg5.add(new Chunk(theStaffLoan.getPurposeOfLoan(), value_body));
			 			fcsecAg5
			 					.add(new Chunk("\n\n\n2. I/We hereby promise and undertake to liquidate and pay the sum of E ", body1));
			 			fcsecAg5.add(new Chunk(String.valueOf(loanAndCharges), value_body));
			 			fcsecAg5.add(new Chunk(" together with interest thereon at the rate of ", body1));
			 			fcsecAg5.add(new Chunk(String.valueOf(theStaffLoan.getInterestRate()), value_body));

			 			fcsecAg5.add(
			 					new Chunk("% per annum or a rate advised by the bank from time to time reckoned from the ", body1));
			 			fcsecAg5.add(new Chunk(String.valueOf(dayOne1) + " day of " + monthOneV1 + " , " + yearOne1, value_body));

			 			fcsecAg5.add(new Chunk(
			 					" and calculated on the balance owing from time to time as follows:-" + "\n\na. In instalments of E ",
			 					body1));
			 			fcsecAg5.add(new Chunk(String.format("%.2f", (totalRepayment)), value_body));
			 			fcsecAg5.add(new Chunk(" Per month commencing on the  " + loanStartStrDate, body1));

			 			fcsecAg5.add(new Chunk(
			 					"  And thereafter monthly at the same rate on the same day of each and every succeeding month thereafter."
			 							+ "\n\n3.  In the event of any one payment due hereafter not being paid on due date, the full balance then outstanding including interest thereon shall immediately become due and recoverable from me/us without further notice."
			 							+ "\n\n4.  All payments to be made hereafter shall be made to the said Bank at its address, namely  ",
			 					body1));
			 			fcsecAg5.add(new Chunk("The Bank", value_body));

			 			fcsecAg5.add(new Chunk(
			 					"  or such other address of which the Bank may from time to time notify me/us in writing."
			 							+ "\5\n5.  I/We hereby choose as my/our domicilium citandi et executandi for all purposes hereunder  ",
			 					body1));
			 			fcsecAg5.add(new Chunk(theStaffLoan.getResAddress(), value_body));
			 			fcsecAg5.add(new Chunk("   (Show street address)."
			 					+ "\n\n6.  I/We hereby consent to the Bank taking any legal proceedings for the enforcing of its rights hereunder or for the recovery of any moneys claimable hereunder or otherwise in the Magistrate�s Court for the district having jurisdiction in respect of any such proceedings against me/us. ",
			 					body1));

			 			fcsecAg5.add(new Chunk(
			 					"7.  In the event of it being necessary for the Bank to institute any legal proceedings against me/us, I/we shall be responsible for the payment of all fees and disbursements thereby incurred by the Bank including collection commission on the scale as between Attorney and Client. ",
			 					body1));

			 			fcsecAg5.add(new Chunk(
			 					"\n\n8.  I/we renounce the benefits of the legal exception �No Numeratae Pecuniae�, �Non Causa Debiti�, �Errore Calculi�, �Revision of Accounts�, and �No value received� the meaning and effect of such renunciations of which I/we acknowledge and understand.   ",
			 					body1));

			 			fcsecAg5.add(new Chunk(
			 					"\n\n9. 	I/We accept that a statement signed by any authorized officer or manager of     the Bank (whose appointment it shall not be necessary to prove) specifying the amounts owing by Myself/ Ourselves in terms of this Agreement or the amount by which the capital liability has been reduced shall be conclusive proof of its contents and sufficient for all purposes hereunder including the issue of Writs of Execution. ",
			 					body1));
			 			fcsecAg6.add(new Chunk("\n\nDated at  ", body1));
			 			fcsecAg6.add(new Chunk(theStaffLoan.getLocation(), value_body));

			 			fcsecAg6.add(new Chunk(" on this the   ", body1));
			 			fcsecAg6.add(new Chunk(String.valueOf(dayOne1) + " day of " + monthOneV1 + " , " + yearOne1, value_body));
			 			fcsecAg6.add(new Chunk("\n\nDebtor: " + theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname()
			 					+ "\n\nAs Witness: 1.___________________ 2.____________________", value_body));
			 			
			 			
		  
				 fcsecAg6.add(new Chunk("\n\nThis agreement is executed at  ", value_body));
				 fcsecAg6.add(new Chunk(theStaffLoan.getLocation(), value_body));
				 fcsecAg6.add(new Chunk("\n\nBy the Borrower:  \nOR in the case of the Borrower being a juristic person, by (Full Names): \nin his/her capacity as the borrower\n" + 
				 		"\n This " + String.valueOf(dayOne1) + " day of " + monthOneV1 + " , " + yearOne1, value_body));
				 fcsecAg6.add(new Chunk("\nCustomer: " + theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname()
							+ "\n\nAs Witness: 1.___________________ 2.____________________", value_body));
				 fcsecAg6.add(new Chunk("\n(All pages to be initialled)", body1));
				 
				 
				 fcsecAg6.add(new Chunk("\n\n\nIRREVOCABLE PLEGDE", heading1));

				 fcsecAg6.add(new Chunk(
							"\n\nIn consideration of credit facilities having been afforded to myself by Eswatini Development and Savings Bank , at my own special request and instance and on the terms and conditions set out in the loan documents, I the undersigned    ",
							body1));
				 fcsecAg6.add(new Chunk(theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname(), value_body));
				 fcsecAg6.add(new Chunk(
							"  do hereby irrevocably consent and authorize the said Eswatini Bank or any subsequent employer to deduct all monthly installments payable by myself in respect of the said credit facilities from my salary and pay such amounts  into my loan account/ accounts until all monies due from me in respect of the credit facility/ facilities have been fully paid to Eswatini Bank. I further authorize the Bank to appropriate and or commute, fully or in part, any other contractual benefits that may accrue to me upon retirement or termination prior to retirement as much as I hereby cede, assign and surrender to the Bank all my right, title and interest in moneys standing to the credit of all accounts held in my name by the Bank in order to redeem any outstanding balance owing by myself on the afforded credit facilities.",
							body1));
				 fcsecAg6.add(new Chunk("\n\n on this the   ", body1));
				 fcsecAg6.add(new Chunk(String.valueOf(dayOne) + " day of " + monthOneV + " , " + yearOne, value_body));

				 fcsecAg6.add(new Chunk("\n\nDebtor: " + theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname()
							+ "\n\nAs Witness: 1.___________________ 2.____________________", value_body));
				 
				 fcsecAg6.add(new Chunk("\n\n\nLETTER OF SET OFF", heading1));
				 fcsecAg6.add(new Chunk(
							" \n\nIn consideration of your agreeing at my request not to require immediate payment of such of the sums mentioned below as may be now due and in consideration  of any like sums which you may hereafter advance or permit to become due, I the under signed\n______________________________________________\n\n",
							body1));
				 fcsecAg6.add(new Chunk(theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname(), value_body));
				 fcsecAg6.add(new Chunk(
							" \n\nHereby charge all moneys from time to time due to me from your bank on any Savings Account which I may now or hereafter have with you with the payment of any sums of money which may be now or may hereafter have with you with the payment of any sums of money owing to your Bank anywhere from or by me either solely or jointly with any other person or persons in partnership or otherwise upon banking account or upon any discount or other account for any other matter or thing whatsoever including the usual banking charges.",
							body1));
				 fcsecAg6.add(new Chunk(
							" \n\nAnd I authorize you to retain all money to me on any such account and to apply the same or any portion thereof towards payment of any sum or sums as aforesaid.",
							body1));
				 fcsecAg6.add(new Chunk(
							" \n\nThis is to be a continuing security and in addition and without prejudice to any other securities you may now or hereafter hold. ",
							body1));
				 fcsecAg6.add(new Chunk("\n\n Thus done at  ", body1));
				 fcsecAg6.add(new Chunk(theStaffLoan.getLocation(), value_body));
				 fcsecAg6.add(new Chunk(" on this the   ", body1));
				 fcsecAg6.add(new Chunk(String.valueOf(dayOne1) + " day of " + monthOneV1 + " , " + yearOne1, value_body));

				 fcsecAg6.add(new Chunk("\n\nDebtor: " + theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname()
							+ "\n\nAs Witness: 1.___________________ 2.____________________", value_body));
		 
		finsecfac1.addCell(fcsecAg1);
		finsecfac1.addCell(fcsecAg2);
		finsecfac1.addCell(fcsecAg3);
		finsecfac1.addCell(fcsecAg4);
		
		finsecfac1.addCell(fcsecAg5);
		finsecfac1.addCell(fcsecAg6);

		document.add(finsecfac1);

		// GENERAL PLEDGE////////
		document.newPage();

		PdfPTable gp = new PdfPTable(2);
		gp.setWidthPercentage(100);
		gp.setHorizontalAlignment(Element.ALIGN_LEFT);
		gp.setWidths(new int[] { 5, 5 });
		gp.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Phrase gp1 = new Phrase();
		gp1.add(new Chunk("GENERAL PLEDGE", heading1));

		gp1.add(new Chunk(
				"\nIn consideration of certain banking facilities having been granted and which may from time to time hereafter be granted to Me\n\nBy the",
				body1));
		gp1.add(new Chunk(" ESWATINI DEVELOPMENT AND SAVINGS BANK ", value_body));
		gp1.add(new Chunk(
				"(hereinafter referred to as �the said Bank�) upon the terms and conditions hereinafter set out, I / we the undersigned ",
				body1));
		gp1.add(new Chunk(theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname(), value_body));
		gp1.add(new Chunk(
				"\n\nHereby cede, assign and make over to the said Bank all my/our right, title and interest in and to, and I/we do hereby pledge and deliver to the said Bank.",
				body1));
		gp1.add(new Chunk("\na)	Stop Order over Salary", value_body));
		gp1.add(new Chunk("\nb)	Level Term Insurance Cover", value_body));
		gp1.add(new Chunk(
				"\n\nTogether with all other securities which have been or may hereafter be provided deposited with and delivered to the said Bank by me/us or on my/our behalf, including shares, stock, Bonds, Debentures and other instruments whether negotiable or not, sum or sums of money, merchandise, goods and other movable property, whether corporeal or incorporeal of every kind and description together with all interest, dividends, rights, income and benefits however named or described; without any exception, arising there from or by virtue thereof (all of which are hereinafter referred to as �the said securities�); and I/we declare that I am / we are the lawful owner(s) or legal holder(s) of the said securities and an / are entitled to all interest, dividends, rights, income and benefits, however named or described, without any exception which are now or may hereafter be due and payable or arising there from or by virtue thereof;\n\nI/we hereby authorise and empower the said Bank to take all such steps as and to do whatever it may in its discretion deem necessary to control, store, converse, transport, insure and otherwise protector or deal with the said securities or any of them, and to that end to notify or instruct any person concerned or otherwise interested therein, but notwithstanding the terms of this agreement the said Bank shall not be obliged to take any such steps or to do anything by virtue of this clause. \n\nThe said securities are ceded assigned, made over, pledged and delivered to the said Bank as a continuing security for each and every sum in which I/we may now be or hereafter indebted to the said Bank from whatever cause arising notwithstanding any fluctuation in the amount or even temporary extinction of such indebtedness, whether such indebtedness be a direct or indirect liability incurred by me/us individually or jointly with others or by any partnership, firm, business concern in which I/we have or hold or may held or may hereafter have or hold any interest and whether such indebtedness arise from money lent and advanced, overdrawn accounts, Drafts, Promissory Notes, or Bills of Exchange or other Negotiable instruments made, signed, accepted or endorsed by me/us or others with whom I/we am/are interested or concerned or any renewals thereof, or thereof, or thought any acts of suretyship or guarantee, or other undertaking signed and executed by my/us solely or jointly with others or otherwise or given by the said Bank on my/our behalf including interest, if not otherwise specially arranged, at the said Bank�s rate current from time to time, discount, commission, legal costs, incurred or to be incurred, collection costs, stamps premiums of insurance and all other necessary, usual or incidental charges and expenses as hereinafter provided and otherwise. 							\"\n"
						+ "\nI/we agree that it shall always be in the entire discretion of the said Bank to determine the extent, nature and duration or advances made or to be made and of the facilities allowed or to be allowed to me/us and to demand that I/we shall provide, deposit with and deliver to the said Bank such other or further security as it may require, notwithstanding the apparent sufficiency of the securities actually held by it. "
						+ "\n\nI/we agree that in the event of any default by me/us in the observance or performance of any of the conditions of this Agreement or of my/our failure or discharge any obligation or liability to the said Bank on the due date thereof or within 24 hours of being called upon in writing so to do to pay such amount as may be demanded of me/us by the said Bank and/or to provide, deposit with and deliver to the said Bank such other or further security as it may require, then and in such case the said Bank shall at its sole option be entitled forthwith to consider the amount of my/our indebtedness or liability as aforesaid to be legally claimable and due without notice and the said Bank may forthwith proceed for recovery thereof and of such other moneys as may be due under any by virtue of this agreement and I/we authorise and empower the Bank then immediately or at any time thereafter irrevocably and in rem suam or in its discretion in my/our name to sell, call up, collect and in any way dispose of realise the said securities or any of them in such manner and on such terms and conditions as the said Bank in its sole and unfettered discretion may deem necessary and whether the said securities or any of them have reached maturity or be due and payable or otherwise  and at any time in its discretion to collect, obtain, take up such interest, Dividends, Rights, Income and Benefits however named or described without any exception which may from time to time become due and payable or available to me/us  on or by virtue of the said securities or any of them and to give in my/our names valid and effectual receipts for all or any sums received by the said Bank on my/our behalf and to cede, assign, transfer and delivered the said securities or any of "
						+ "them disposed of or realised in terms of this agreement,  and to take or institute or cause to be taken or instituted such legal or  other action as may be "
						+ "necessary and to proceed to the final end and  determination thereof "
						+ "provided that the after payment of all cost and expenses in ",
				body1));
		gp.addCell(gp1);

		Phrase gp2 = new Phrase();
		gp2.add(new Chunk(
				"connection with such realisation and legal proceedings, and of all debts and liabilities secured by this agreements, the balance if any of such proceeds shall be paid to me/us or to my/our order; whereas in the event of the proceeds of the said securities being insufficient to pay my/our indebtedness or liabilities as aforesaid to the said Bank, I/we bind myself/ourselves and undertake to pay the deficiency on demand. But notwithstanding the provisions of this agreement, the said Bank shall be entitled at any "
						+ "time whether before, during or after the realisation of the said securities to sue for and take pay debts and liabilities secured by this agreement, the balance if any of such proceeds shall be paid to me/us or to my/our order, whereas in the event of the proceeds of the said securities to sue for and take all necessary or advisable legal steps for the recovery of the moneys debts or liabilities as aforesaid, which may then be due and owing by me/us or any part thereof and to execute upon all or any of my/our assets or property according to law just as if the said securities had not been given. 							"
						+ "\n\nAll or any amounts collected by the said Bank in terms of this agreement may be appropriated by it towards payment of such of my/our liability or indebtedness as aforesaid or part thereof as it in its sole and unfettered discretion shall decide. \n\nI/we agree that an account rendered by the said Bank and certified by any manager or accountant or any person or persons acting in such capacity thereof setting out the amount of my/our liability or indebtedness as aforesaid from time to time and of any interest due to accrued and costs interest, dividends, rights, income and benefits, monies, securities other assets received and of the proceeds of the sale, disposal or realisation of the said securities or any of them shall be prima facie proof of the corrections of the matters contained in such account and I/we hereby authorise and empower the said Bank to apply for and obtain Provisional Sentence in any competent Court against me/us upon this document and the certified account of my/our liability or indebtedness as aforesaid. \n\nI/we bind myself / ourselves undertake to pay all or any amounts disbursed or costs and charges reasonable incurred by the said Bank in carrying out the provisions of this agreement or in taking action to enforce the terms hereof, and I/we agree that the said Bank shall be liable or responsible and I/we hereby absolve and hold the said Bank blameless for any loss or damage sustained in or by reason of the sale, disposal or other realisation of the said securities or any of them whether through brokers, agents or otherwise or by reason of deterioration in value of the said securities or any of them while in the custody of the said Bank or by reason of its failure or omission to take up or collect any interest, Dividends, Rights, Income and Benefits however named or described or to insure or to protect my/our interests in the said securities in any other way. \n\nI/we bind myself / ourselves and undertake not to cede, assign, make over and pledge to anyone else or to realise, alienate, receive, collect, in any other manner deal with the said securities, or any interest, dividends, rights, income and benefits however named or described without any exception arising there from or by virtue thereof without the written consent of the said Bank first being ha and obtained.  \n\nI/we renounce all benefits of the legal exceptions non numerate pecuniae, non causa debit, errore calculi, revision of accounts, no value received, ordinis seu excussioni et divisionis and all other exception which might or could be taken at law or in equity to the payment of my/our liability or indebtedness as aforesaid or any part thereof with the forece and effect of which exceptions I/we declare to be fully acquainted. ",
				body1));

		gp2.add(new Chunk(
				"In as far as one or other of us in surety for the other or others or surety for any other debtor or debtors to the said Bank. I/we hereby renounce the benefits of the legal exceptions of division and execution and agree and undertake to accept the liability/ies jointly and severally with the other debtor or debtors and in so far as one or other of us is a woman or married woman I/we furthermore renounce the benefits of the legal exceptions �senatus consultum velleianum� and �authentica si qua mulier� with the force and effect whereof I/we are fully acquainted. ",
				body1));
		gp2.add(new Chunk(
				"\nAny notice or demand required to be given in terms of this agreement shall be validly given if delivered or addressed and posted to me/us ",
				body1));
		gp2.add(new Chunk(theStaffLoan.getResAddress(), value_body));
		gp2.add(new Chunk(
				"\n\nWhich I/we choose as my/our domicilium citandi et executandi for the purpose of this agreement. All communications sent by post and addressed to me/us and the service of any legal process issued against me/us at the said address shall be deemed to have been delivered to me/us in the ordinary and usual course postal delivery. "
						+ "\n\nAnd for the purposes of this agreement, I/we hereby nominate, constitute and appoint the Manager or any person or persons acting in this capacity from time to time of the said Bank at ",
				body1));
		gp2.add(new Chunk(theStaffLoan.getBranchAddress(), value_body));
		gp2.add(new Chunk(
				"\n\nWith power of substitution, to be my/our true and lawful Attorney and Agent in remsuam or in his discretion in my/our name with all necessary power and authority irrevocably during the subsistence of any indebtedness or liability whatsoever by me/us to the said Bank to do all that may be necessary and required to give effect to this agreement hereby ratifying, allowing and confirming and promising and agreeing to ratify, allow and confirm all and whatsoever my/our said Attorney and Agent shall lawfully do or cause to be done by virtue of these presents.",
				body1));
		gp2.add(new Chunk("\nThus done and signed at ", value_body));
		gp2.add(new Chunk(theStaffLoan.getLocation(), value_body));
		gp2.add(new Chunk(" This " + String.valueOf(dayOne1) + " day of " + monthOneV1 + " , " + yearOne1, value_body));
		gp2.add(new Chunk("\nCustomer: " + theStaffLoan.getFirstName() + " " + theStaffLoan.getSurname()
				+ "\n\nAs Witness: 1.___________________ 2.____________________", value_body));
		gp.addCell(gp2);

		document.add(gp);



		// STOP ORDER OVER SALARY/////
		document.newPage();
		document.add((Element) img);

		Paragraph stopOrderParagraph1 = new Paragraph("STOP ORDER OVER SALARY", heading1);
		document.add(stopOrderParagraph1);

		document.add(new Paragraph("\n"));

		PdfPTable soosOuter = new PdfPTable(3);
		soosOuter.setWidthPercentage(100);
		soosOuter.setHorizontalAlignment(Element.ALIGN_CENTER);
		soosOuter.setWidths(new int[] { 6, 2, 6 });
		soosOuter.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPTable soosInner1 = new PdfPTable(1);
		soosInner1.setWidthPercentage(100);
		soosInner1.setHorizontalAlignment(Element.ALIGN_CENTER);
		soosInner1.setWidths(new int[] { 15 });
		soosInner1.getDefaultCell().setBorder(Rectangle.BOX);
		soosInner1.addCell(new Paragraph(("\nENTERED BY ..................................................."
				+ "\n\nDATE ........................................................."
				+ "\n\nChecking Officer to sign below as certifying signature and to sign the diary and Debit Card. \n\n"),
				body1));

		PdfPTable soosInner2 = new PdfPTable(1);
		soosInner2.setWidthPercentage(100);
		soosInner2.setHorizontalAlignment(Element.ALIGN_CENTER);
		soosInner2.setWidths(new int[] { 15 });
		soosInner2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		soosInner2.addCell(new Paragraph((" "), body1));

		PdfPTable soosInner3 = new PdfPTable(1);
		soosInner3.setWidthPercentage(100);
		soosInner3.setHorizontalAlignment(Element.ALIGN_CENTER);
		soosInner3.setWidths(new int[] { 15 });
		soosInner3.getDefaultCell().setBorder(Rectangle.BOX);
		soosInner3
				.addCell(new Paragraph(("\n\nCUSTOMER NAME" + "\n\nSURNAME: " + theStaffLoan.getSurname().toUpperCase()
						+ "\n\nCHRISTIAN NAMES: " + theStaffLoan.getFirstName().toUpperCase() + "\n\nEMPLOYMENT NO: "
						+ theStaffLoan.getEmployeeCode() + "     CIF: " + theStaffLoan.getCif()), body1));

		soosOuter.addCell(soosInner1);
		soosOuter.addCell(soosInner2);
		soosOuter.addCell(soosInner3);
		document.add(soosOuter);

		Phrase soos = new Phrase();

		soos.add(new Chunk(
				"\nTo: The Manager\nESWATINI DEVELOPMENT AND SAVINGS BANK                                            Date:"
						+ loanStartStrDate + "\n" + theStaffLoan.getBranchAddress(),
				body1));

		soos.add(new Chunk(
				"\n\nI/We hereby request and authorize you, until you receive notice to the contrary in writing, to make the periodical payment mentioned below according to the Instructions contained therein, to the debit of my/our Savings/Loan Account with your bank.\n\nTo be dispatched on: PAYDAYS............................................... commencing: ",
				body1));

		soos.add(new Chunk(loanStartStrDate.toUpperCase(), value_body));

		soos.add(new Chunk("...............................................\n", body1));

		soos.add(new Chunk(
				"\n To:- .............................................................................................. Eswatini Bank\n",
				value_body));

		soos.add(new Chunk(
				"\n 'A':- ..............................................................................................",
				value_body));

		soos.add(new Chunk(theStaffLoan.getLocation(), value_body));

		soos.add(new Chunk("\n\nFor Credit of: ", body1));

		soos.add(new Chunk(theStaffLoan.getLoanType().toUpperCase(), value_body));

		soos.add(new Chunk("\n\nTo be paid To:", body1));
		soos.add(new Chunk("Eswatini Bank\n                             " + theStaffLoan.getBranchAddress(),
				value_body));
		soos.add(new Chunk("\nAmount in words: ", body1));

		soos.add(new Chunk(theStaffLoan.getLoanAmountWords(), value_body));
		soos.add(new Chunk("\n\nSpecial Instructions:          UNTIL LOAN IS FULLY REPAID"
				+ "\n\n                                                       ", body1));
		soos.add(new Chunk("Yours faithfully", value_body));
		soos.add(new Chunk("\n\n\n                                                     ............................."
				+ "\n�A�           Payments to be made to through Bankers                           ) Delete whichever"
				+ "\n�B�           Payments to be made direct to individuals or concerns        ) is not applicable"
				+ "\n\nThe bank will not send you special advices relating in the above payments. "
				+ "\n\nN.B.        If insufficient funds are available to meet this Stop Order on due date, payment will not be made.	The Bank will not 		make special arrangements to meet Stop Orders after due date.",
				body1));
		document.add(soos);

	}


}
