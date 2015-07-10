package net.trajano.commons.testing;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Provides a utility class to load up resource streams.
 *
 * @author Archimedes Trajano
 */
public final class ResourceUtil {

    /**
     * Reads a resource as bytes from the the given class' class loader.
     *
     * @param clazz
     *            class
     * @param name
     *            name of the resource
     * @return input stream
     */
    public static byte[] getResourceAsBytes(final Class<?> clazz,
            final String name) {

        try (InputStream is = getResourceAsStream(clazz, name)) {
            return streamToBytes(is);
        } catch (final IOException e) {
            throw new AssertionError(e.getMessage(), e);
        }
    }

    /**
     * Reads a resource as bytes from the context class loader.
     *
     * @param name
     *            name of the resource
     * @return input stream
     */
    public static byte[] getResourceAsBytes(final String name) {

        try (InputStream is = getResourceAsStream(name)) {
            return streamToBytes(is);
        } catch (final IOException e) {
            throw new AssertionError(e.getMessage(), e);
        }
    }

    /**
     * Reads a resource as a stream from the the given class' class loader.
     * Callers are expected to close the resource after.
     *
     * @param clazz
     *            class
     * @param name
     *            name of the resource
     * @return input stream
     */
    public static InputStream getResourceAsStream(final Class<?> clazz,
            final String name) {

        final InputStream is = clazz.getResourceAsStream(name);
        if (is == null) {
            throw new AssertionError(String.format("Unable to locate resource %s in %s", name, clazz));
        }
        return is;
    }

    /**
     * Reads a resource as a stream from the context class loader. Callers are
     * expected to close the resource after.
     *
     * @param name
     *            name of the resource
     * @return input stream
     */
    public static InputStream getResourceAsStream(final String name) {

        final InputStream is = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(name);
        if (is == null) {
            throw new AssertionError(String.format("Unable to locate resource %s in context class loader", name));
        }
        return is;
    }

    /**
     * Reads a resource as a string from the the given class' class loader.
     * Callers are expected to close the resource after. The data is expected to
     * be UTF-8.
     *
     * @param clazz
     *            class
     * @param name
     *            name of the resource
     * @return input stream
     */
    public static String getResourceAsString(final Class<?> clazz,
            final String name) {

        try {
            return new String(getResourceAsBytes(clazz, name), "UTF-8");
        } catch (final UnsupportedEncodingException e) {
            throw new AssertionError(e.getMessage(), e);
        }
    }

    /**
     * Reads a resource as a String from the from the context class loader.
     * Callers are expected to close the resource after.
     *
     * @param name
     *            name of the resource
     * @return string
     */
    public static String getResourceAsString(final String name) {

        try {
            return new String(getResourceAsBytes(name), "UTF-8");
        } catch (final UnsupportedEncodingException e) {
            throw new AssertionError(e.getMessage(), e);
        }
    }

    /**
     * Creates a copy of the resource from the given class' class loader to a
     * temporary file. It is expected that the test will delete the file
     * afterwards.
     *
     * @param clazz
     *            class
     * @param name
     *            name of the resource
     * @return temporary file
     */
    public static File getResourceCopy(final Class<?> clazz,
            final String name) {

        try (InputStream is = getResourceAsStream(clazz, name)) {
            return streamToTempFile(is);
        } catch (final IOException e) {
            throw new AssertionError(e.getMessage(), e);
        }
    }

    /**
     * Creates a copy of the resource from the context class loader to a
     * temporary file. It is expected that the test will delete the file
     * afterwards.
     *
     * @param name
     *            name of the resource
     * @return temporary file
     */
    public static File getResourceCopy(final String name) {

        try (InputStream is = getResourceAsStream(name)) {
            return streamToTempFile(is);
        } catch (final IOException e) {
            throw new AssertionError(e.getMessage(), e);
        }
    }

    /**
     * Converts an {@link InputStream} to bytes. This will not throw an
     * {@link IOException} but wraps it in an {@link AssertionError}. This reads
     * the {@link InputStream} to end of file, but does not close it.
     *
     * @param is
     *            input stream
     * @return byte array.
     */
    public static byte[] streamToBytes(final InputStream is) {

        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int c = is.read();
            while (c != -1) {
                baos.write(c);
                c = is.read();
            }
            baos.close();
            return baos.toByteArray();
        } catch (final IOException e) {
            throw new AssertionError(e.getMessage(), e);
        }
    }

    /**
     * Copies the contents of an {@link InputStream} to a temporary file. This
     * will not throw an {@link IOException} but wraps it in an
     * {@link AssertionError}. This reads the {@link InputStream} to end of
     * file, but does not close it.
     *
     * @param is
     *            input stream
     * @return temporary file.
     */
    public static File streamToTempFile(final InputStream is) {

        try {
            final File file = File.createTempFile("test", ".tmp");
            final FileOutputStream os = new FileOutputStream(file);

            int c = is.read();
            while (c != -1) {
                os.write(c);
                c = is.read();
            }
            os.close();
            return file;
        } catch (final IOException e) {
            throw new AssertionError(e.getMessage(), e);
        }
    }

    /**
     * Prevent instantiation of utility class.
     */
    private ResourceUtil() {
    }
}
