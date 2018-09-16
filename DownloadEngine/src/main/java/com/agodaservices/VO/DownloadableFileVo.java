package com.agodaservices.VO;

public class DownloadableFileVo {

    private String protocol;
    private String hostname;
    private String filename;
    private int port;
    private String fileURL;

    public DownloadableFileVo(String protocol, String hostname, String filename, int port, String fileURL) {
        this.protocol = protocol;
        this.hostname = hostname;
        this.filename = filename;
        this.port = port;
        this.fileURL = fileURL;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DownloadableFileVo that = (DownloadableFileVo) o;

        if (port != that.port) return false;
        if (protocol != null ? !protocol.equals(that.protocol) : that.protocol != null) return false;
        if (hostname != null ? !hostname.equals(that.hostname) : that.hostname != null) return false;
        if (filename != null ? !filename.equals(that.filename) : that.filename != null) return false;
        return fileURL != null ? fileURL.equals(that.fileURL) : that.fileURL == null;
    }

    @Override
    public int hashCode() {
        int result = protocol != null ? protocol.hashCode() : 0;
        result = 31 * result + (hostname != null ? hostname.hashCode() : 0);
        result = 31 * result + (filename != null ? filename.hashCode() : 0);
        result = 31 * result + port;
        result = 31 * result + (fileURL != null ? fileURL.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DownloadableFileVo{" +
                "protocol='" + protocol + '\'' +
                ", hostname='" + hostname + '\'' +
                ", filename='" + filename + '\'' +
                ", port=" + port +
                ", fileURL='" + fileURL + '\'' +
                '}';
    }
}
