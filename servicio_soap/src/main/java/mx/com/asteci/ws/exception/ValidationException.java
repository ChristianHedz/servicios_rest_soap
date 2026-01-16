package mx.com.asteci.ws.exception;

import jakarta.xml.ws.WebFault;

@WebFault(name = "ValidationFault", targetNamespace = "http://ws.asteci.com.mx/")
public class ValidationException extends Exception {

    private final ValidationFaultBean faultInfo;

    public ValidationException(String message, ValidationFaultBean faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    public ValidationException(String message, ValidationFaultBean faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    public ValidationFaultBean getFaultInfo() {
        return faultInfo;
    }
}
