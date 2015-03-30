package net.trajano.commons.testing;

import java.util.concurrent.Callable;

/**
 * This provides utility methods that ensure the {@link #equals(Object)} and
 * {@link #hashCode()} are implemented correctly.
 *
 * @author Archimedes Trajano
 *
 */
public final class EqualsTestUtil {
    /**
     * Builds two objects the same way and checks if they are equal.
     *
     * @param objectBuilder
     *            object builder
     * @throws Exception
     *             thrown when there is a problem building the object
     */
    public static <T> void assertEqualsImplementedCorrectly(
            final Callable<T> objectBuilder) throws Exception {
        assertEqualsImplementedCorrectly(objectBuilder, objectBuilder);
    }

    /**
     * Builds two objects and ensures that they are equal.
     *
     * @param objectBuilder1
     *            first object builder
     * @param objectBuilder2
     *            second object builder
     * @throws Exception
     *             thrown when there is a problem building the object
     */
    public static <T> void assertEqualsImplementedCorrectly(
            final Callable<T> objectBuilder1, final Callable<T> objectBuilder2)
            throws Exception {
        final T o1 = objectBuilder1.call();
        final T o2 = objectBuilder2.call();
        assertEqualsImplementedCorrectly(o1, o2);
    }

    /**
     * Take a single object and ensure its equality is implemented correctly.
     *
     * @param o
     *            object
     */
    public static <T> void assertEqualsImplementedCorrectly(final T o) {
        assertEqualsImplementedCorrectly(o, o);
    }

    /**
     * Take two objects and ensure their are implemented correctly.
     *
     * @param o1
     *            first object
     * @param o2
     *            second object
     */
    public static <T> void assertEqualsImplementedCorrectly(final T o1,
            final T o2) {
        assert o1.equals(o2);
        assert o2.equals(o1);
        assert o1.equals(o1);
        assert o2.equals(o2);
        assert !o1.equals(new EqualsTestUtil());
        assert !o2.equals(new EqualsTestUtil());
        assert !o1.equals(null);
        assert !o2.equals(null);
        assert o1.hashCode() == o2.hashCode();
    }

    /**
     * Prevent instantiation of utility class.
     */
    private EqualsTestUtil() {
    }
}