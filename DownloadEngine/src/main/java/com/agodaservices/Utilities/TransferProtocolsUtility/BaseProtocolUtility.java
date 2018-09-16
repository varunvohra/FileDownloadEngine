package com.agodaservices.Utilities.TransferProtocolsUtility;

import com.agodaservices.VO.DownloadableFileVo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class BaseProtocolUtility {

    protected static int BUFFER_SIZE = 4096;
    protected static int defaultConnectionTimeout = 5000; //set timeout to 5 seconds
    protected static int defaultReadTimeout = 60 * 60 * 60 * 1000; //set timeout to 1 hour

    InputStream inputStream;

    FileOutputStream outputStream;

    String fileName;

    String saveFilePath;

    /*
     * Get an input stream from a URL
     * Implemented by each subclass on its own
     * @param fileDetails give the details of the Downloadable File
     * @throws IOException
    */
    public abstract void getInputStream(DownloadableFileVo fileDetails) throws IOException;

    /*
     * saves the file to given local location
     * @param saveDir path of the directory to save the file
     * @throws IOException
    */
    public void writeToFile(String saveDir) throws IOException {

        saveFilePath = saveDir + File.separator + fileName;

        // opens an output stream to save into file
        outputStream = new FileOutputStream(saveFilePath);

        int bytesRead = -1;
        byte[] buffer = new byte[BUFFER_SIZE];
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        System.out.println(String.format("File : %s downloaded succesfully to location : %s", fileName, saveDir));
    }

    /*
     * Closing all streams and connections
     * Implemented by each subclass on its own
     * @throws IOException
     */
    public abstract void close() throws IOException;


    /*
     * delete a file from local disk
    */
    public void deleteFile() {
        File file = new File(saveFilePath);
        if (file.delete()) {
            System.out.println(file.getName() + " is deleted from location : " + saveFilePath);
        } else {
            System.out.println("Delete operation is failed at location : " + saveFilePath);
        }
    }

}
