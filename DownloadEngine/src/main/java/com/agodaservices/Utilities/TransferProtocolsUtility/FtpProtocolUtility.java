package com.agodaservices.Utilities.TransferProtocolsUtility;

import com.agodaservices.VO.DownloadableFileVo;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class FtpProtocolUtility extends BaseProtocolUtility {

    public void getInputStream(DownloadableFileVo fileDetails) throws IOException {
        String fileURL = fileDetails.getFileURL();
        URL url = new URL(fileURL);
        URLConnection URLConn = url.openConnection();
        URLConn.setConnectTimeout(defaultConnectionTimeout);
        URLConn.setReadTimeout(defaultReadTimeout);
        fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
        System.out.println("Reading input for FTP protocol from : " + fileURL);
        inputStream = URLConn.getInputStream();
    }

    public void close() throws IOException {
        System.out.println("Closing FTP connections");
        inputStream.close();
        outputStream.close();
    }

}
