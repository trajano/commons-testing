package net.trajano.commons.testing.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.mockito.Mockito;

import net.trajano.commons.testing.ResourceUtil;

public class ResourceUtilTest {

    @SuppressWarnings("unchecked")
    @Test(expected = AssertionError.class)
    public void testBadStream() throws Exception {

        final InputStream mock = Mockito.mock(InputStream.class);
        Mockito.when(mock.read())
                .thenThrow(IOException.class);
        ResourceUtil.streamToBytes(mock);
    }

    @Test
    public void testCopy() throws Exception {

        final File f = ResourceUtil.getResourceCopy(ResourceUtilTest.class, "deep.txt");
        assertTrue(f.exists());
        f.delete();
    }

    @Test
    public void testCopyContextClassLoader() throws Exception {

        final File f = ResourceUtil.getResourceCopy("sample.txt");
        assertTrue(f.exists());
        f.delete();
    }

    @Test(expected = AssertionError.class)
    public void testNonExistentFile() throws Exception {

        ResourceUtil.getResourceAsString(ResourceUtilTest.class, "not-there.txt");
    }

    @Test(expected = AssertionError.class)
    public void testNonExistentFileContextClassLoader() throws Exception {

        ResourceUtil.getResourceAsString("not-there.txt");
    }

    @Test
    public void testReadString() throws Exception {

        assertEquals("hello world", ResourceUtil.getResourceAsString("sample.txt"));
    }

    @Test
    public void testReadStringWithClass() throws Exception {

        assertEquals("deep inside", ResourceUtil.getResourceAsString(ResourceUtilTest.class, "deep.txt"));
    }

}
