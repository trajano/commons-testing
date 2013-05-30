package net.trajano.commons.testing.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import net.trajano.commons.testing.DisableSslCertificateCheckUtil;
import net.trajano.commons.testing.internal.NullHostnameVerifier;
import net.trajano.commons.testing.internal.NullX509TrustManager;

import org.junit.Test;

/**
 * Tests the Null* SSL implementation classes.
 * 
 * @author Archimedes Trajano
 * 
 */
public class NullSslTest {

	@Test
	public void testTrustManager() throws Exception {
		final X509TrustManager trustManager = new NullX509TrustManager();
		trustManager.checkClientTrusted(new X509Certificate[0], "anyAuth");
		trustManager.checkServerTrusted(new X509Certificate[0], "anyAuth");
		assertEquals(0, trustManager.getAcceptedIssuers().length);
	}

	/**
	 * Tests the utility methods can execute, but does not perform any
	 * connectivity tests to ensure that the SSL checks are disabled.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUtilityMethods() throws Exception {
		DisableSslCertificateCheckUtil.disableChecks();
		DisableSslCertificateCheckUtil.reenableChecks();
	}

	/**
	 * Tests the utility methods can execute, but does not perform any
	 * connectivity tests to ensure that the SSL checks are disabled.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUtilityMethodsDoubled() throws Exception {
		DisableSslCertificateCheckUtil.disableChecks();
		DisableSslCertificateCheckUtil.disableChecks();
		DisableSslCertificateCheckUtil.reenableChecks();
		DisableSslCertificateCheckUtil.reenableChecks();
	}

	@Test
	public void testVerifier() {
		final HostnameVerifier hostnameVerifier = new NullHostnameVerifier();
		assertTrue(hostnameVerifier.verify("anyhostname",
				mock(SSLSession.class)));
	}
}