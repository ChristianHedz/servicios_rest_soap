package mx.com.asteci.ws.exception;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;

@XmlType(name = "ValidationFault", namespace = "http://ws.asteci.com.mx/")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValidationFaultBean {

    @XmlElement(required = true)
    private String message;

    @XmlElement(required = true)
    private List<String> violations;

    public ValidationFaultBean() {
        this.violations = new ArrayList<>();
    }

    public ValidationFaultBean(String message, List<String> violations) {
        this.message = message;
        this.violations = violations;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getViolations() {
        return violations;
    }

    public void setViolations(List<String> violations) {
        this.violations = violations;
    }

    public void addViolation(String violation) {
        this.violations.add(violation);
    }
}
