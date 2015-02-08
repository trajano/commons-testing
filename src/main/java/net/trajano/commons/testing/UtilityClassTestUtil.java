package net.trajano.commons.testing;

import static java.util.logging.Level.SEVERE;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.logging.Logger;

/**
 * This provides a test to check certain properties of a utility class.
 * Primarily that it has only one private constructor and no non-static methods
 * and it is final.
 * 
 * @author Archimedes Trajano
 */
public final class UtilityClassTestUtil {
    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(
            UtilityClassTestUtil.class.getName(),
            "net.trajano.commons.testing.Messages");

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
        assert Modifier.isFinal(clazz.getModifiers());
        assert clazz.getDeclaredConstructors().length == 1;
        final Constructor<?> constructor = clazz.getDeclaredConstructor();
        if (constructor.isAccessible()
                || !Modifier.isPrivate(constructor.getModifiers())) {
            LOG.log(SEVERE,
                    "UtilityClassTestUtil.constructorNotPrivate", constructor); //$NON-NLS-1$
            assert false;
        }
        constructor.setAccessible(true);
        constructor.newInstance();
        constructor.setAccessible(false);
        for (final Method method : clazz.getMethods()) {
            if (!Modifier.isStatic(method.getModifiers())
                    && method.getDeclaringClass().equals(clazz)) {
                LOG.log(SEVERE, "UtilityClassTestUtil.methodNotStatic", method); //$NON-NLS-1$
                assert false;
            }
        }
    }

    /**
     * Prevent instantiation of utility class.
     */
    private UtilityClassTestUtil() {
    }
}
