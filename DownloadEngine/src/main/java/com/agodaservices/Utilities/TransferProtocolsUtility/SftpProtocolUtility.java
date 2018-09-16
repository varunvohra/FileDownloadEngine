package com.agodaservices.Utilities.TransferProtocolsUtility;

import com.agodaservices.VO.DownloadableFileVo;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URI;

public class SftpProtocolUtility extends BaseProtocolUtility {

    private static final int DEFAULT_PORT = 22;

    Session session;
    Channel channel;
    ChannelSftp channelSftp;

    public void getInputStream(DownloadableFileVo fileDetails) throws IOException {

        try {
            String SFTPUSER;
            String SFTPPASS = null;
            String fileURL = fileDetails.getFileURL();
            URI uri = new URI(fileURL);
            String SFTPHOST = uri.getHost();
            int SFTPPORT = (uri.getPort() != -1) ? uri.getPort() : DEFAULT_PORT;
            if (uri.getUserInfo().contains(":")) {
                SFTPUSER = uri.getUserInfo().split(":")[0];
                SFTPPASS = uri.getUserInfo().split(":")[1];
            } else {
                SFTPUSER = uri.getUserInfo();
            }
            JSch jsch = new JSch();
            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
            session.setPassword(SFTPPASS);
            session.setTimeout(defaultConnectionTimeout);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
            System.out.println("Reading input for SFTP protocol from : " + fileURL);
            inputStream = new BufferedInputStream(channelSftp.get(uri.getPath()));
            fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void close() throws IOException {
        System.out.println("Closing SFTP connections");
        inputStream.close();
        outputStream.close();
        channel.disconnect();
        session.disconnect();
    }

}
