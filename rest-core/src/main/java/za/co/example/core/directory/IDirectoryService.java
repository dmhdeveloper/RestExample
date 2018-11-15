package za.co.example.core.directory;

import za.co.example.FileListResponse;

public interface IDirectoryService {

    /**
     * Retrieve directory contents for path
     */
    FileListResponse getDirectoryContents(String directoryPath) throws DirectoryNotFoundException, DirectoryTransformationException;
}
