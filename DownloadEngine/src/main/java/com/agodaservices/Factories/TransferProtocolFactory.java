package com.agodaservices.Factories;

import com.agodaservices.Utilities.TransferProtocolsUtility.BaseProtocolUtility;
import com.agodaservices.Utilities.TransferProtocolsUtility.FtpProtocolUtility;
import com.agodaservices.Utilities.TransferProtocolsUtility.HttpProtocolUtility;
import com.agodaservices.Utilities.TransferProtocolsUtility.SftpProtocolUtility;

public enum TransferProtocolFactory {

    HTTP("http", new HttpProtocolUtility()),
    FTP("ftp", new FtpProtocolUtility()),
    SFTP("sftp", new SftpProtocolUtility());

    private BaseProtocolUtility protocolUtility;

    private String value;

    TransferProtocolFactory(String value, BaseProtocolUtility downloadUtility) {
        this.value = value;
        this.protocolUtility = downloadUtility;
    }

    public BaseProtocolUtility getProtocolUtility() {
        return protocolUtility;
    }

    public String getValue() {
        return value;
    }


}
