package za.co.example;

public class FileListResponse extends BaseResponse {

    Directory directory;

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }
}
