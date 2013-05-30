package net.trajano.commons.testing.test;

import java.net.URL;

import javax.net.ssl.SSLHandshakeException;

import net.trajano.commons.testing.DisableSslCertificateCheckUtil;

import org.junit.After;
import org.junit.Test;

public class DisableSslCertificateChecksIT {

	/**
	 * Always reenable certificate checks after running each test.
	 */
	@After
	public void reenableCertificateChecks() {
		DisableSslCertificateCheckUtil.reenableChecks();
	}

	/**
	 * Google should always have a valid SSL. Test this.
	 */
	@Test
	public void testHttpsConnectionToGoogle() throws Exception {
		DisableSslCertificateCheckUtil.disableChecks();
		new URL("https://www.google.com/").getContent();
	}

	/**
	 * This is an invalid certificate referenced by
	 * https://onlinessl.netlock.hu/en/test-center/invalid-ssl-certificate.html
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInvalidCertificate() throws Exception {
		DisableSslCertificateCheckUtil.disableChecks();
		new URL("https://tv.eurosport.com/").openConnection().connect();
	}

	/**
	 * Failure test. This is an invalid certificate referenced by
	 * https://onlinessl.netlock.hu/en/test-center/invalid-ssl-certificate.html
	 * 
	 * @throws Exception
	 */
	@Test(expected = SSLHandshakeException.class)
	public void testInvalidCertificateFailure() throws Exception {
		new URL("https://tv.eurosport.com/").openConnection().connect();
	}
}
