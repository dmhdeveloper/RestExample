package za.co.entersekt.core.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import za.co.entersekt.Directory;
import za.co.entersekt.SystemFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@Component
public class Transformer {

    @Value("${directory.base.location}")
    private String basePath;
    @Value("${file.size.suffix}")
    private String fileSizeSuffix;
    @Value("${file.size.divisor}")
    private Integer fileSizeDivisor;

    public Directory transformDirectory(Directory directory, File file, boolean recursive) throws IOException {
        if (file.isDirectory()) {
            if(recursive) {
                directory.setTotalSpace(formatSize(Files.getFileStore(file.toPath()).getTotalSpace()));
                directory.setFreeSpace(formatSize(Files.getFileStore(file.toPath()).getUnallocatedSpace()));
            } else {
                directory.setSize(formatSize(getFolderSize(file)));
            }
            directory.setPath(formatPath(file));
            directory.setName(file.getName());
            if (file.listFiles() != null && recursive) {
                List<File> contents = Arrays.asList(file.listFiles());
                for (File rawFile : contents) {
                    if(rawFile.isDirectory()) {
                        directory.getDirectoryList().add(transformDirectory(new Directory(), rawFile, false));
                    } else {
                        transformDirectory(directory, rawFile, false);
                    }
                }
                return directory;
            }
        } else {
            directory.getSystemFileList().add(transformSystemFile(file));
            return directory;
        }
        return directory;
    }

    public SystemFile transformSystemFile(File file) throws IOException {
        SystemFile systemFile = new SystemFile();
        systemFile.setFileType(Files.probeContentType(file.toPath()));
        systemFile.setName(file.getName());
        systemFile.setPath(formatPath(file));
        systemFile.setSize(formatSize(Files.size(file.toPath())));
        return systemFile;
    }

    private String formatPath(File file){
        String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
        return path.replace(basePath, "/");
    }

    private long getFolderSize(File folder) {
        long length = 0;
        File[] files = folder.listFiles();
        int count = files.length;
        for (int i = 0; i < count; i++) {
            if (files[i].isFile()) {
                length += files[i].length();
            } else {
                length += getFolderSize(files[i]);
            }
        }
        return length;
    }

    private String formatSize(Long size){
        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(size/fileSizeDivisor));
        builder.append(".");
        builder.append(String.valueOf(size%fileSizeDivisor));
        builder.append(" ");
        builder.append(fileSizeSuffix);
        return builder.toString();
    }
}
