package za.co.entersekt.core.directory.impl;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import za.co.entersekt.Directory;
import za.co.entersekt.FileListResponse;
import za.co.entersekt.Status;
import za.co.entersekt.core.directory.DirectoryNotFoundException;
import za.co.entersekt.core.directory.DirectoryTransformationException;
import za.co.entersekt.core.directory.IDirectoryService;
import za.co.entersekt.core.utility.Transformer;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DirectoryService implements IDirectoryService {

    private static final Logger logger = LoggerFactory.getLogger(DirectoryService.class);

    @Value("${directory.base.location}")
    private String baseLocation;

    @Autowired
    private Transformer transformer;

    @Override
    public FileListResponse getDirectoryContents(String directoryPath) throws DirectoryNotFoundException, DirectoryTransformationException {
        FileListResponse response = new FileListResponse();
        if(directoryPath == null){
            response.setStatus(Status.FAILED);
            response.setMessage("No directory path was passed to service.");
            return response;
        }
        String path = formatFilePath(baseLocation.concat(directoryPath));
        File file = new File(path);
        if(!file.exists()){
            throw new DirectoryNotFoundException(String.format("Directory [%s] does not exist", directoryPath));
        }
        try {
            response.setDirectory(transformer.transformDirectory(new Directory(), file, true));
        } catch (IOException e) {
            logger.error("Unable to transform contents of directory.", e);
            throw new DirectoryTransformationException(e);
        }
        response.setStatus(Status.SUCCESS);
        return response;
    }

    protected String formatFilePath(String filePath) {
        if (filePath == null) {
            return Strings.EMPTY;
        }
        String regex = "(\\\\)|(\\/+\\/)";
        StringBuffer buffer = new StringBuffer();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(filePath);
        m.useTransparentBounds(true);

        while (true) {
            while (m.find()) {
                m.appendReplacement(buffer, "/");
            }
            m.appendTail(buffer);
            m.reset(buffer.toString());
            if (m.find(0)) {
                m.reset();
                buffer = new StringBuffer();
            } else {
                break;
            }
        }

        return buffer.toString();
    }
}
