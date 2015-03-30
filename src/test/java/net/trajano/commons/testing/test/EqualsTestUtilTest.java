package net.trajano.commons.testing.test;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

import net.trajano.commons.testing.EqualsTestUtil;

import org.junit.Test;

/**
 * Tests {@link EqualsClassTestUtil}.
 *
 * @author Archimedes Trajano
 *
 */
public class EqualsTestUtilTest {

    private static final class MissingEquals {
        private final int i;

        public MissingEquals(final int i) {
            this.i = i;
        }

    }

    private static final class MissingHashCode {
        private final int i;

        public MissingHashCode(final int i) {
            this.i = i;
        }

        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final MissingHashCode other = (MissingHashCode) obj;
            if (i != other.i) {
                return false;
            }
            return true;
        }
    }

    @Test
    public void testFailureWithMissingEqualsObject() throws Exception {
        try {
            final MissingEquals i1 = new MissingEquals(1);
            final MissingEquals i2 = new MissingEquals(1);
            EqualsTestUtil.assertEqualsImplementedCorrectly(i1, i2);
            throw new RuntimeException("failure");
        } catch (final AssertionError e) {
        }
    }

    @Test
    public void testFailureWithMissingHashcodeObject() throws Exception {
        try {
            final MissingHashCode i1 = new MissingHashCode(1);
            final MissingHashCode i2 = new MissingHashCode(1);
            EqualsTestUtil.assertEqualsImplementedCorrectly(i1, i2);
            throw new RuntimeException("failure");
        } catch (final AssertionError e) {
        }
    }

    @Test
    public void testFailureWithRandom() throws Exception {
        try {
            final Integer i1 = ThreadLocalRandom.current().nextInt();
            final Integer i2 = ThreadLocalRandom.current().nextInt();
            EqualsTestUtil.assertEqualsImplementedCorrectly(i1, i2);
            throw new RuntimeException("failure");
        } catch (final AssertionError e) {
        }
    }

    @Test
    public void testWithBuilders() throws Exception {
        EqualsTestUtil.assertEqualsImplementedCorrectly(new Callable<UUID>() {

            @Override
            public UUID call() throws Exception {
                return UUID.fromString("c2d8c5c5-5202-44ff-89f1-93d596721df6");
            }

        });
    }

    @Test
    public void testWithOneObjects() throws Exception {
        final UUID u1 = UUID.fromString("c2d8c5c5-5202-44ff-89f1-93d596721df6");
        EqualsTestUtil.assertEqualsImplementedCorrectly(u1);
    }

    @Test
    public void testWithTwoBuilders() throws Exception {
        EqualsTestUtil.assertEqualsImplementedCorrectly(new Callable<UUID>() {

            @Override
            public UUID call() throws Exception {
                return UUID.fromString("c2d8c5c5-5202-44ff-89f1-93d596721df6");
            }

        }, new Callable<UUID>() {

            @Override
            public UUID call() throws Exception {
                return UUID.fromString("c2d8c5c5-5202-44ff-89f1-93d596721df6");
            }

        });
    }

    @Test
    public void testWithTwoObjects() throws Exception {
        final UUID u1 = UUID.fromString("c2d8c5c5-5202-44ff-89f1-93d596721df6");
        final UUID u2 = UUID.fromString("c2d8c5c5-5202-44ff-89f1-93d596721df6");
        EqualsTestUtil.assertEqualsImplementedCorrectly(u1, u2);
    }

}
