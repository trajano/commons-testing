package net.trajano.commons.testing.test;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import net.trajano.commons.testing.DisableSslCertificateCheckUtil;

import org.junit.Test;

/**
 * Tests for disabling certificate checks with a JAX-RS client.
 *
 * @author Archimedes
 *
 */
public class DisableSslCertificateChecksJaxRsIT {

    /**
     * Google should always have a valid SSL. Test this.
     */
    @Test
    public void testHttpsConnectionToGoogle() throws Exception {
        final Client client = ClientBuilder
                .newBuilder()
                .hostnameVerifier(
                        DisableSslCertificateCheckUtil.NULL_HOSTNAME_VERIFIER)
                        .sslContext(DisableSslCertificateCheckUtil.NULL_SSL_CONTEXT)
                        .build();
        client.target(
                "https://accounts.google.com/.well-known/openid-configuration")
                .request().get();
    }

    /**
     * This is an invalid certificate referenced by
     * https://onlinessl.netlock.hu/en/test-center/invalid-ssl-certificate.html
     *
     * @throws Exception
     */
    @Test
    public void testInvalidCertificate() throws Exception {
        final Client client = ClientBuilder
                .newBuilder()
                .hostnameVerifier(
                        DisableSslCertificateCheckUtil.NULL_HOSTNAME_VERIFIER)
                        .sslContext(DisableSslCertificateCheckUtil.NULL_SSL_CONTEXT)
                        .build();
        client.target("https://tv.eurosport.com/").request().get();
    }

    /**
     * Failure test. This is an invalid certificate referenced by
     * https://onlinessl.netlock.hu/en/test-center/invalid-ssl-certificate.html
     *
     * @throws Exception
     */
    @Test(expected = ProcessingException.class)
    public void testInvalidCertificateFailure() throws Exception {
        final Client client = ClientBuilder.newClient();
        client.target("https://tv.eurosport.com/").request().get();
    }
}
