package net.trajano.commons.testing.test;

import static net.trajano.commons.testing.UtilityClassTestUtil.assertUtilityClassWellDefined;

import org.junit.Test;

import net.trajano.commons.testing.DisableSslCertificateCheckUtil;
import net.trajano.commons.testing.EqualsTestUtil;
import net.trajano.commons.testing.ResourceUtil;
import net.trajano.commons.testing.UtilityClassTestUtil;
import net.trajano.commons.testing.test.util.MultipleConstructorUtil;
import net.trajano.commons.testing.test.util.NonFinalUtil;
import net.trajano.commons.testing.test.util.NonStaticMethodUtil;
import net.trajano.commons.testing.test.util.PublicConstructorUtil;

/**
 * Tests {@link UtilityClassTestUtil}.
 *
 * @author Archimedes Trajano
 */
public class UtilityClassTestUtilTest {

    @Test(expected = AssertionError.class)
    public void testBadUtil1() throws Exception {

        assertUtilityClassWellDefined(NonFinalUtil.class);
    }

    @Test(expected = AssertionError.class)
    public void testBadUtil2() throws Exception {

        assertUtilityClassWellDefined(PublicConstructorUtil.class);
    }

    @Test(expected = AssertionError.class)
    public void testBadUtil3() throws Exception {

        assertUtilityClassWellDefined(MultipleConstructorUtil.class);
    }

    @Test(expected = AssertionError.class)
    public void testBadUtil4() throws Exception {

        assertUtilityClassWellDefined(NonStaticMethodUtil.class);
    }

    /**
     * Tests {@link net.trajano.commons.testing.DisableSslCertificateCheckUtil} which should be valid.
     *
     * @throws Exception
     */
    @Test
    public void testDisableSSLCerificateCheckUtil() throws Exception {

        assertUtilityClassWellDefined(DisableSslCertificateCheckUtil.class);
    }

    /**
     * Tests {@link net.trajano.commons.testing.EqualsTestUtil} which should be valid.
     *
     * @throws Exception
     */
    @Test
    public void testEqualsTestUtil() throws Exception {

        assertUtilityClassWellDefined(EqualsTestUtil.class);
    }

    /**
     * Tests {@link ResourceUtil} which should be valid.
     *
     * @throws Exception
     */
    @Test
    public void testResourceUtil() throws Exception {

        assertUtilityClassWellDefined(ResourceUtil.class);
    }

    /**
     * Tests the utility class itself which should be valid.
     *
     * @throws Exception
     */
    @Test
    public void testSelf() throws Exception {

        assertUtilityClassWellDefined(UtilityClassTestUtil.class);
    }

    @Test(expected = AssertionError.class)
    public void testTestSelf() throws Exception {

        assertUtilityClassWellDefined(UtilityClassTestUtilTest.class);
    }
}
