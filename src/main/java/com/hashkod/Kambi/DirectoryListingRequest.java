package com.hashkod.Kambi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * POJO for holding the request parameters.
 * Getters and setters are provided by lombok library during runtime.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DirectoryListingRequest {

    private String path;
    private long delay;

}
