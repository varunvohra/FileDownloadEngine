package com.agodaservices.Services;

import com.agodaservices.Factories.TransferProtocolFactory;
import com.agodaservices.VO.DownloadableFileVo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

// this service provides file related information
public class FileInfoService {

    public static ArrayList<DownloadableFileVo> getFileDetails(List<String> urls) {
        ArrayList<DownloadableFileVo> fileDetailsArrayList = new ArrayList<DownloadableFileVo>();
        for (String urlString : urls) {
            try {
                if (urlString.startsWith("sftp")) {
                    fileDetailsArrayList.add(new DownloadableFileVo(TransferProtocolFactory.SFTP.toString(), null, null, -1, urlString));
                } else {
                    URL url = new URL(urlString);
                    fileDetailsArrayList.add(new DownloadableFileVo(url.getProtocol(), url.getHost(), url.getFile(), url.getPort(), urlString));
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("File Objects created from given Urls : " + fileDetailsArrayList.toString());
        return fileDetailsArrayList;
    }
}
