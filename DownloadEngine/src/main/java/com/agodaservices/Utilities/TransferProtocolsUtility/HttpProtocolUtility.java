package com.agodaservices.Utilities.TransferProtocolsUtility;

import com.agodaservices.VO.DownloadableFileVo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpProtocolUtility extends BaseProtocolUtility {

    HttpURLConnection httpConn;

    public void getInputStream(DownloadableFileVo fileDetails) throws IOException {

        String fileURL = fileDetails.getFileURL();
        System.out.println("Reading input for HTTP protocol from : " + fileURL);

        URL url = new URL(fileURL);
        httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setConnectTimeout(defaultConnectionTimeout);
        httpConn.setReadTimeout(defaultReadTimeout);
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10, disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
            }

            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);

            // opens input stream from the HTTP connection
            inputStream = httpConn.getInputStream();
        }
    }

    public void close() throws IOException {
        System.out.println("Closing HTTP connections");
        outputStream.close();
        inputStream.close();
        if (httpConn != null)
            httpConn.disconnect();
    }

}
