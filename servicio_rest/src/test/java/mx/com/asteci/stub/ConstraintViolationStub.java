package mx.com.asteci.stub;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.metadata.ConstraintDescriptor;

/**
 * Stub de ConstraintViolation para tests sin Mockito.
 */
public class ConstraintViolationStub implements ConstraintViolation<Object> {

    private final String propertyPath;
    private final String message;

    public ConstraintViolationStub(String propertyPath, String message) {
        this.propertyPath = propertyPath;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getMessageTemplate() {
        return null;
    }

    @Override
    public Object getRootBean() {
        return null;
    }

    @Override
    public Class<Object> getRootBeanClass() {
        return null;
    }

    @Override
    public Object getLeafBean() {
        return null;
    }

    @Override
    public Object[] getExecutableParameters() {
        return new Object[0];
    }

    @Override
    public Object getExecutableReturnValue() {
        return null;
    }

    @Override
    public Path getPropertyPath() {
        return new Path() {
            @Override
            public java.util.Iterator<Node> iterator() {
                return java.util.Collections.emptyIterator();
            }

            @Override
            public String toString() {
                return propertyPath;
            }
        };
    }

    @Override
    public Object getInvalidValue() {
        return null;
    }

    @Override
    public ConstraintDescriptor<?> getConstraintDescriptor() {
        return null;
    }

    @Override
    public <U> U unwrap(Class<U> type) {
        return null;
    }
}
