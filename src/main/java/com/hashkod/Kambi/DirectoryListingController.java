package com.hashkod.Kambi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Publishes the directory listing endpoint(s).
 */
@RestController
@RequestMapping("/api/v1/")
public class DirectoryListingController {

    @Autowired
    private DirectoryListingService directoryListingService;

    @PostMapping(path = "dir/list")
    ResponseEntity<?> directoryList(@RequestBody DirectoryListingRequest directoryListingRequest) {

        /* Validate request parameters */
        if(directoryListingRequest.getPath() == null) {
            return new ResponseEntity<String>("Missing 'path' parameter.", HttpStatus.BAD_REQUEST);
        }
        if(directoryListingRequest.getPath().isBlank()) {
            return new ResponseEntity<String>("Invalid 'path' value.", HttpStatus.BAD_REQUEST);
        }
        if(directoryListingRequest.getDelay() < 0) {
            return new ResponseEntity<String>("Invalid 'delay' value.", HttpStatus.BAD_REQUEST);
        }

        /* Get the directory listing, If result is null it means the directory does not exist */
        ArrayList<String> listing = directoryListingService.blockingListContents(directoryListingRequest.getPath(), directoryListingRequest.getDelay());

        if(listing == null) {
            return new ResponseEntity<String>("Directory for listing does not exist!", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ArrayList<String>>(listing, HttpStatus.OK);
    }

}
