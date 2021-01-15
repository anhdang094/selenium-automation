package vn.betting.automation.infrastructure.exception;

public class UnexpectedException extends Exception {

    private static final long serialVersionUID = 1L;

    public UnexpectedException(String s) {
        super(s);
    }

    public UnexpectedException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public UnexpectedException(Throwable throwable) {
        super(throwable);
    }

}
