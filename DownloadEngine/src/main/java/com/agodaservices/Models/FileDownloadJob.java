package com.agodaservices.Models;

import com.agodaservices.VO.DownloadableFileVo;
import com.agodaservices.helpers.DownloadHelper;

public class FileDownloadJob implements Runnable {

    private DownloadableFileVo fileDetails;
    private String saveDir;

    public FileDownloadJob(DownloadableFileVo fileDetails, String saveDir) {
        this.fileDetails = fileDetails;
        this.saveDir = saveDir;
    }

    public void run() {
        DownloadHelper.downloadFile(fileDetails, saveDir);
    }

}
