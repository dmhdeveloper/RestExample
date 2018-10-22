package za.co.entersekt;

import java.util.LinkedList;
import java.util.List;

public class Directory {

    private String path;
    private String name;
    private String totalSpace;
    private String freeSpace;
    private String size;
    private List<Directory> directoryList;
    private List<SystemFile> systemFileList;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(String totalSpace) {
        this.totalSpace = totalSpace;
    }

    public String getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(String freeSpace) {
        this.freeSpace = freeSpace;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<Directory> getDirectoryList() {
        if(directoryList == null){
            directoryList = new LinkedList<>();
        }
        return directoryList;
    }

    public List<SystemFile> getSystemFileList() {
        if(systemFileList == null){
            systemFileList = new LinkedList<>();
        }
        return systemFileList;
    }

}
