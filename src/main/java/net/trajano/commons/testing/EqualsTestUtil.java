package net.trajano.commons.testing;

import java.util.concurrent.Callable;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * This provides utility methods that ensure the {@link #equals(Object)} and
 * {@link #hashCode()} are implemented correctly.
 *
 * @author Archimedes Trajano
 */
public final class EqualsTestUtil {

    /**
     * Builds two objects the same way and checks if they are equal.
     *
     * @param <T>
     *            type
     * @param objectBuilder
     *            object builder
     */
    public static <T> void assertEqualsImplementedCorrectly(final Callable<T> objectBuilder) {

        assertEqualsImplementedCorrectly(objectBuilder, objectBuilder);
    }

    /**
     * Builds two objects and ensures that they are equal.
     *
     * @param <T>
     *            type
     * @param objectBuilder1
     *            first object builder
     * @param objectBuilder2
     *            second object builder
     */
    public static <T> void assertEqualsImplementedCorrectly(final Callable<T> objectBuilder1,
            final Callable<T> objectBuilder2) {

        try {
            final T o1 = objectBuilder1.call();
            final T o2 = objectBuilder2.call();
            assertEqualsImplementedCorrectly(o1, o2);
        } catch (final Exception e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Take two objects and ensure their are implemented correctly. Warnings are
     * suppressed as this block of code will do things that normal developers
     * are not supposed to do, but are needed to ensure that
     * {@link #equals(Object)} is implemented correctly.
     * <p>
     * The generic check is not put in to allow testing with different classes
     * </p>
     *
     * @param o1
     *            first object
     * @param o2
     *            second object
     */
    @SuppressWarnings("all")
    @SuppressFBWarnings()
    public static void assertEqualsImplementedCorrectly(final Object o1,
            final Object o2) {

        // symmetric
        if (!o1.equals(o2)) {
            throw new AssertionError("Expected " + o1 + " ==  " + o2);
        }
        if (!o2.equals(o1)) {
            throw new AssertionError("Expected " + o2 + " ==  " + o1);
        }

        // this == object tests
        if (!o1.equals(o1)) {
            throw new AssertionError("Expected " + o1 + " ==  " + o1);
        }
        if (!o2.equals(o2)) {
            throw new AssertionError("Expected " + o2 + " ==  " + o2);
        }

        // Different class checks
        if (o1.equals(new EqualsTestUtil())) {
            throw new AssertionError("Type of " + o1 + " does not match expected class");
        }
        if (o2.equals(new EqualsTestUtil())) {
            throw new AssertionError("Type of " + o2 + " does not match expected class");
        }

        // Null tests done poorly but will at least trigger the right paths.

        // CHECKSTYLE:OFF
        if (o1.equals(null)) {
            throw new AssertionError("Did not expect " + o1 + " == null");
        }
        if (o2.equals(null)) {
            throw new AssertionError("Did not expect " + o2 + " == null");
        }
        // CHECKSTYLE:ON

        // hash code validity
        if (o1.hashCode() != o2.hashCode()) {
            throw new AssertionError(String.format("Expected hash code of %s (%d) == %s (%d)", o1, o1.hashCode(), o2, o2.hashCode()));
        }
    }

    /**
     * Take a single object and ensure its equality is implemented correctly.
     *
     * @param <T>
     *            type
     * @param o
     *            object
     */
    public static <T> void assertEqualsImplementedCorrectly(final T o) {

        assertEqualsImplementedCorrectly(o, o);
    }

    /**
     * Prevent instantiation of utility class.
     */
    private EqualsTestUtil() {

    }
}
