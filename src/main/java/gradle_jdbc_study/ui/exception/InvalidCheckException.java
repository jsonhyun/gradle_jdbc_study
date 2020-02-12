package gradle_jdbc_study.ui.exception;

@SuppressWarnings("serial")
public class InvalidCheckException extends RuntimeException {

	public InvalidCheckException() {
		super("공백이 존재합니다.");
	}

	public InvalidCheckException(Throwable cause) {
		super("공백이 존재합니다.", cause);
	}
	
}
