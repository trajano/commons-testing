package net.trajano.commons.testing;

/**
 * Exceptions that should not occur during the normal operation of the library.
 * 
 * @author Archimedes Trajano
 */
public class TestingException extends RuntimeException {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 4533548085541078306L;

    /**
     * Constructs the exception.
     * 
     * @param e
     *            chained exception.
     */
    public TestingException(final Exception e) {
        super(e);
    }

}
