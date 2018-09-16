Application supports functionality to download data(files) from multiple sources and protocols to local disk.

General Information

    *   Factory design pattern is used to implement various transfer protocols.
    *   New protocol methods can be added easily by extending Base Protocol.
    *   Thread Pool Executor is used to download files in parallel.
    *   Project is divided into models, utilities, helpers, services, constants and Database objects(VOs).
    *   Initial test cases to check correctness of Downloaded file and to check 3 protocols are written.

For now, it supports three famous transfer protocols (HTTP, FTP and SFTP) to download files from any source URL.

Application can be extended to support more transfer protocols, implement new protocol by extending TransferProtocolFactory.

Example inputs supporting 3 protocols

    *   HTTP
        - URL Format:   http://hostname/sourceFilepath
        - Eg:           http://commons.apache.org/proper/commons-vfs/images/commons-logo.png
        
    *   FTP
        - URL Format:   ftp://username:password@hostname/sourceFilepath
        - Eg:           ftp://varunvohra:1234/Users/varunvohra/Downloads/commons-logo.png
        
    *   SFTP
        - URL Format:   sftp://username:password@hostname/sourceFilepath
        - Eg:           ftp://varunvohra:1234/Users/varunvohra/Downloads/commons-logo.png
        
To run the project along with Unit and Integration Tests:

    *   mvn clean install (inside the DownloadEngine Project)
    
    Structure:
        
        MainClass:  DownloadEngine/src/main/java/DownloadEngine.java
        Jar:        DownloadEngine/out/artifacts/DownloadEngine_jar/DownloadEngine.jar

Command to download the file from the source using jar file:

    java -jar ~/DownloadEngine/target/DownloadEngine-1.0-SNAPSHOT.jar url=http://commons.apache.org/proper/commons-vfs/images/commons-logo.png,ftp://varunvohra:1234/Users/varunvohra/Downloads/commons-logo.png saveDir=/Users/varunvohra/Downloads
    
    Extra parameters can be passed, for now they will be ignored, but we can extend our project to handle them in future.
    
    But below 2 parameters are mandatory to be passed as an arguments.
    *   urls : Multiple sourceUrls can be sent separated by delimiter(',')
    *   saveDir : local directory where file will be saved after downloading
    
