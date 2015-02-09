package net.trajano.commons.testing;

import static javax.net.ssl.HttpsURLConnection.getDefaultHostnameVerifier;
import static javax.net.ssl.HttpsURLConnection.getDefaultSSLSocketFactory;
import static javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier;
import static javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.trajano.commons.testing.internal.NullHostnameVerifier;
import net.trajano.commons.testing.internal.NullX509TrustManager;

/**
 * Disables SSL certificate checks. Primarily used for doing integration testing
 * against self-signed servers.
 */
public final class DisableSslCertificateCheckUtil {
    /**
     * Flag to indicate that certificate checks are disabled. If this is true,
     * then the process to disable the checks are not executed again.
     */
    private static boolean disabled;

    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(
            DisableSslCertificateCheckUtil.class.getName(),
            "net.trajano.commons.testing.Messages");

    /**
     * Null host name verifier. Made it a constant to prevent new
     * instantiations. Made public so the instance can be retrieved directly.
     */
    public static final HostnameVerifier NULL_HOSTNAME_VERIFIER = new NullHostnameVerifier();

    /**
     * Null SSL context. Made it a constant to prevent new instantiations. Made
     * public so the instance can be retrieved directly. Normally this is not
     * guarantee thread-safety so applications should not use this directly.
     */
    public static final SSLContext NULL_SSL_CONTEXT;

    /**
     * Original hostname verifier, set by {{@link #disableChecks()}.
     */
    private static HostnameVerifier originalHostnameVerifier;

    /**
     * Original SSL Socket factory, set by {{@link #disableChecks()}.
     */
    private static SSLSocketFactory originalSslSocketFactory;

    static {
        try {
            NULL_SSL_CONTEXT = SSLContext.getInstance("TLSv1");
            final TrustManager[] trustManagerArray = { new NullX509TrustManager() };
            NULL_SSL_CONTEXT.init(null, trustManagerArray, null);
        } catch (final GeneralSecurityException e) {
            throw new AssertionError(e.toString(), e);
        }

    }

    /**
     * Disable trust checks for SSL connections. Saves the present ones if it is
     * not the disabled ones.
     *
     * @throws GeneralSecurityException
     *             thrown when there is a problem disabling the SSL. Shouldn't
     *             happen unless there is something wrong with the Java
     *             implementation.
     */
    public static void disableChecks() throws GeneralSecurityException {
        if (disabled) {
            return;
        }
        try {
            new URL("https", "0", "/").getContent();
        } catch (final IOException e) {
            // This invocation will always fail, but it will register the
            // default SSL provider to the URL class.
            LOG.log(Level.FINEST,
                    "DisableSSLCertificateCheckUtil.disableCertificateCheck", e);
        }
        originalSslSocketFactory = getDefaultSSLSocketFactory();
        originalHostnameVerifier = getDefaultHostnameVerifier();
        final SSLContext context = NULL_SSL_CONTEXT;
        setDefaultSSLSocketFactory(context.getSocketFactory());
        setDefaultHostnameVerifier(NULL_HOSTNAME_VERIFIER);
        disabled = true;
    }

    /**
     * This will re-enable the SSL checks after it was disabled by
     * {@link #disableChecks()}.
     */
    public static void reenableChecks() {
        if (!disabled) {
            return;
        }
        setDefaultSSLSocketFactory(originalSslSocketFactory);
        setDefaultHostnameVerifier(originalHostnameVerifier);
        disabled = false;
    }

    /**
     * Prevent instantiation of utility class.
     */
    private DisableSslCertificateCheckUtil() {
    }
}
