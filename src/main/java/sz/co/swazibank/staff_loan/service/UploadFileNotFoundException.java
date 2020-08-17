package sz.co.swazibank.staff_loan.service;

public class UploadFileNotFoundException extends UploadException{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1960890661245726751L;

	public UploadFileNotFoundException(String message) {
        super(message);
    }

    public UploadFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}