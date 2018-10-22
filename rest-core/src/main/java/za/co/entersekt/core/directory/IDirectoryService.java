package za.co.entersekt.core.directory;

import za.co.entersekt.FileListResponse;

public interface IDirectoryService {

    /**
     * Retrieve directory contents for path
     */
    FileListResponse getDirectoryContents(String directoryPath) throws DirectoryNotFoundException, DirectoryTransformationException;
}
