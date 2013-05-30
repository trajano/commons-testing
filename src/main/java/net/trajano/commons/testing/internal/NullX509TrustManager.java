package net.trajano.commons.testing.internal;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * Trust manager that does not perform any checks.
 */
public class NullX509TrustManager implements X509TrustManager {
	/**
	 * Does nothing.
	 * 
	 * @param chain
	 *            certificate chain
	 * @param authType
	 *            authentication type
	 */
	public void checkClientTrusted(final X509Certificate[] chain,
			final String authType) throws CertificateException {
	}

	/**
	 * Does nothing.
	 * 
	 * @param chain
	 *            certificate chain
	 * @param authType
	 *            authentication type
	 */
	public void checkServerTrusted(final X509Certificate[] chain,
			final String authType) throws CertificateException {
	}

	/**
	 * Gets a list of accepted issuers.
	 * 
	 * @return empty array
	 */
	public X509Certificate[] getAcceptedIssuers() {
		return new X509Certificate[0];
	}
}
