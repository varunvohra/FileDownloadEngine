package com.agodaservices;

import com.agodaservices.Factories.TransferProtocolFactory;
import com.agodaservices.Services.FileInfoService;
import com.agodaservices.VO.DownloadableFileVo;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class DownloadEngineUnitTest {

    /*
    * setup() and teardown() methods will be called for each test case method calls.
    * This will allow the test case to do a prepration and post clean up process for each of the JUnit method test call.
     */

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Ignore
    public void main() throws Exception {
    }

    @Test
    public void getFileDetails() {
        ArrayList<String> urls = new ArrayList<String>();
        urls.add("http://commons.apache.org/proper/commons-vfs/images/commons-logo.png");
        ArrayList<DownloadableFileVo> fileDetails = FileInfoService.getFileDetails(urls);

        System.out.println("File Details Array Size : " + fileDetails.size());

        // true if all file Urls are properly converted to DownloadableFileVo objects
        assertTrue(urls.size() == fileDetails.size());
    }

    // We can also write stubs to check the correctness of other File parameters

    // stub to check correctness of protocol
    @Test
    public void getProtocolFromFileURL() {
        ArrayList<String> urls = new ArrayList<String>();
        urls.add("http://commons.apache.org/proper/commons-vfs/images/commons-logo.png");
        ArrayList<DownloadableFileVo> fileDetails = FileInfoService.getFileDetails(urls);

        String protocol = TransferProtocolFactory.valueOf(fileDetails.get(0).getProtocol().toUpperCase()).getValue();

        // true if protocol is correctly determined from its fileUrl
        assertTrue(protocol.equals("http"));
    }

    @Test
    public void downloadHttpProtocolFile() throws Exception {
        ArrayList<String> urls = new ArrayList<String>();
        urls.add("http://commons.apache.org/proper/commons-vfs/images/commons-logo.png");

        ArrayList<DownloadableFileVo> fileDetails = FileInfoService.getFileDetails(urls);
        String saveDir = DownloadEngineUnitTest.class.getProtectionDomain().getCodeSource().getLocation().getFile();

        //stub method
        downloadFile(fileDetails.get(0), saveDir);
        assertTrue(true);
    }

    @Test
    public void downloadFTPProtocolFile() throws Exception {
        ArrayList<String> urls = new ArrayList<String>();
        urls.add("ftp://varunvohra:1234/Users/varunvohra/Downloads/commons-logo.png");

        ArrayList<DownloadableFileVo> fileDetails = FileInfoService.getFileDetails(urls);
        String saveDir = DownloadEngineUnitTest.class.getProtectionDomain().getCodeSource().getLocation().getFile();

        //stub method
        downloadFile(fileDetails.get(0), saveDir);
        assertTrue(true);
    }

    @Test
    public void downloadSFTPProtocolFile() throws Exception {
        ArrayList<String> urls = new ArrayList<String>();
        urls.add("sftp://varunvohra:1234/Users/varunvohra/Downloads/commons-logo.png");

        ArrayList<DownloadableFileVo> fileDetails = FileInfoService.getFileDetails(urls);
        String saveDir = DownloadEngineUnitTest.class.getProtectionDomain().getCodeSource().getLocation().getFile();

        //stub method
        downloadFile(fileDetails.get(0), saveDir);
        assertTrue(true);
    }

    private void downloadFile(DownloadableFileVo fileDetails, String saveDir){
        return;
    }


}
