package za.co.example.core.directory.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class DirectoryServiceTest {

    DirectoryService service;

    @Test
    public void testWindowsPath(){
        service = new DirectoryService();
        String path = "C:\\Users\\someone\\Desktop";
        path = service.formatFilePath(path);
        assertNotNull(path);
        assertEquals("C:/Users/someone/Desktop", path);
    }

    @Test
    public void testLinuxPath(){
        service = new DirectoryService();
        String path = "/home/someone/Desktop";
        path = service.formatFilePath(path);
        assertNotNull(path);
        assertEquals("/home/someone/Desktop", path);
    }

    @Test
    public void testDuplicateForwardSlashes(){
        service = new DirectoryService();
        String path = "/home/someone//Desktop/utility//utilx/folder";
        path = service.formatFilePath(path);
        assertNotNull(path);
        assertEquals("/home/someone/Desktop/utility/utilx/folder", path);
    }

    @Test
    public void testCorruptWindowsPath(){
        service = new DirectoryService();
        String path = "C://home//someone\\Desktop\\utility\\\\utilx/folder";
        path = service.formatFilePath(path);
        assertNotNull(path);
        assertEquals("C:/home/someone/Desktop/utility/utilx/folder", path);
    }


}
