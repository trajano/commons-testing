package net.trajano.commons.testing;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * This provides a test to check certain properties of a utility class.
 * Primarily that it has only one private constructor and no non-static methods
 * and it is final.
 *
 * @author Archimedes Trajano
 */
public final class UtilityClassTestUtil {
    /**
     * Resource bundle.
     */
    private static final ResourceBundle R = ResourceBundle
            .getBundle("META-INF.Messages");

    /**
     * A utility class class is well defined if it is final and there is one and
     * only one declared constructor.
     *
     * @param clazz
     *            class to evaluate
     */
    private static void assertUtilityClassClassWellDefined(final Class<?> clazz) {
        if (!Modifier.isFinal(clazz.getModifiers())) {
            throw new AssertionError(MessageFormat.format(
                    R.getString("UtilityClassTestUtil.notFinal"), //$NON-NLS-1$
                    clazz));
        }
        if (clazz.getDeclaredConstructors().length != 1) {
            throw new AssertionError(MessageFormat.format(
                    R.getString("UtilityClassTestUtil.notOneConstructor"), //$NON-NLS-1$
                    clazz));
        }
    }

    /**
     * A utility class constructor is well defined if it is private.
     *
     * @param constructor
     * @throws ReflectiveOperationException
     *             when there is a problem performing reflection on the class.
     */
    private static void assertUtilityClassConstructorWellDefined(
            final Constructor<?> constructor)
                    throws ReflectiveOperationException {
        if (constructor.isAccessible()
                || !Modifier.isPrivate(constructor.getModifiers())) {
            throw new AssertionError(MessageFormat.format(
                    R.getString("UtilityClassTestUtil.constructorNotPrivate"), //$NON-NLS-1$
                    constructor));
        }
        constructor.setAccessible(true);
        constructor.newInstance();
        constructor.setAccessible(false);
    }

    /**
     * Methods of a utility class should be static if it was defined on the
     * class itself.
     *
     * @param clazz
     *            class to evaluate
     */
    private static void assertUtilityClassMethodsWellDefined(
            final Class<?> clazz) {
        for (final Method method : clazz.getMethods()) {
            if (!Modifier.isStatic(method.getModifiers())
                    && method.getDeclaringClass().equals(clazz)) {
                throw new AssertionError(MessageFormat.format(
                        R.getString("UtilityClassTestUtil.methodNotStatic"), //$NON-NLS-1$
                        method));
            }
        }
    }

    /**
     * Verifies that a utility class is well defined.
     *
     * @param clazz
     *            utility class to verify.
     * @throws ReflectiveOperationException
     *             problem accessing the class or its elements using reflection.
     */
    public static void assertUtilityClassWellDefined(final Class<?> clazz)
            throws ReflectiveOperationException {
        assertUtilityClassClassWellDefined(clazz);
        final Constructor<?> constructor = clazz.getDeclaredConstructor();
        assertUtilityClassConstructorWellDefined(constructor);
        assertUtilityClassMethodsWellDefined(clazz);
    }

    /**
     * Prevent instantiation of utility class.
     */
    private UtilityClassTestUtil() {
    }
}
