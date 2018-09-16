package com.agodaservices;

import com.agodaservices.Services.FileInfoService;
import com.agodaservices.VO.DownloadableFileVo;
import com.agodaservices.helpers.DownloadHelper;
import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class DownloadEngineIntegrationTest {

    @Test
    public void longRunningServiceTest() throws Exception {
        ArrayList<String> urls = new ArrayList<String>();
        urls.add("http://commons.apache.org/proper/commons-vfs/images/commons-logo.png");

        ArrayList<DownloadableFileVo> fileDetails = FileInfoService.getFileDetails(urls);

        URL location = DownloadEngineIntegrationTest.class.getProtectionDomain().getCodeSource().getLocation();
        String saveDir = location.getFile();
        DownloadHelper.downloadFile(fileDetails.get(0), saveDir);
        assertTrue(true);
    }

}
