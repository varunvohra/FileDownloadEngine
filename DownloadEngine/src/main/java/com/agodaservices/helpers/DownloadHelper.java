package com.agodaservices.helpers;

import com.agodaservices.Constants.ThreadPool;
import com.agodaservices.Factories.TransferProtocolFactory;
import com.agodaservices.Models.FileDownloadJob;
import com.agodaservices.Services.FileInfoService;
import com.agodaservices.Utilities.TransferProtocolsUtility.BaseProtocolUtility;
import com.agodaservices.VO.DownloadableFileVo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DownloadHelper {

    final private static int RETRY_COUNT = 3;

    // Thread Pool Executor to run the tasks in multiple threads in parallel
    final static ThreadPoolExecutor executor = new ThreadPoolExecutor(ThreadPool.poolSize,
            ThreadPool.maxPoolSize,
            ThreadPool.ttl,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

    public static void downloadFilesInParallel(ArrayList<String> fileUrls, String saveDir) {

        ArrayList<DownloadableFileVo> fileDetailsArrayList = FileInfoService.getFileDetails(fileUrls);

        System.out.println("Starting Thread Executor Pool Service.");
        // Submitting the tasks in parallel to the thread pool executor service
        for (DownloadableFileVo fileDetails : fileDetailsArrayList) {
            executor.execute(new FileDownloadJob(fileDetails, saveDir));
        }

        System.out.println("Closing Thread Executor Pool Service.");
        executor.shutdown();

    }

    public static void downloadFile(DownloadableFileVo fileDetails, String saveDir) {
        int retryCount = 1;
        BaseProtocolUtility protocolDownloadUtility = TransferProtocolFactory.valueOf(fileDetails.getProtocol().toUpperCase()).getProtocolUtility();
        while (retryCount <= RETRY_COUNT) {
            try {
                protocolDownloadUtility.getInputStream(fileDetails);
                protocolDownloadUtility.writeToFile(saveDir);
                break;
            } catch (Exception e) {
                System.out.println("Error in downloading the file : " + fileDetails.toString());
                System.out.println("Deleting file in case of unsuccessful download : " + fileDetails.toString());
                protocolDownloadUtility.deleteFile();
                e.printStackTrace();
            } finally {
                try {
                    retryCount++;
                    protocolDownloadUtility.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
