package za.co.example.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.co.example.BaseResponse;
import za.co.example.FileListResponse;
import za.co.example.Status;
import za.co.example.core.directory.DirectoryNotFoundException;
import za.co.example.core.directory.DirectoryTransformationException;
import za.co.example.core.directory.IDirectoryService;

@RestController
public class DirectoryController {

    @Autowired
    IDirectoryService directoryService;

    @RequestMapping(path = "/directoryContents", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getDirectoryContents(String directoryPath) {

        try {
            FileListResponse response = directoryService.getDirectoryContents(directoryPath);
            return ResponseEntity.ok(response);
        } catch (DirectoryNotFoundException e) {
            BaseResponse response = new BaseResponse();
            response.setStatus(Status.FAILED);
            response.setMessage(String.format("Failed to get directory contents [%s]", e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } catch (DirectoryTransformationException e) {
            BaseResponse response = new BaseResponse();
            response.setStatus(Status.FAILED);
            response.setMessage(String.format("Failed to transform directory and its contents [%s]", e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
