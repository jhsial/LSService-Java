package com.hashkod.Kambi;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
class KambiTestApplicationTests {

	@Mock
	private DirectoryListingService directoryListingService;

	@InjectMocks
	private DirectoryListingController directoryListingController;


	@Test
	void testDirectoryListMissingPathParam() {
		/* Create a request with a missing 'path' parameter */
		DirectoryListingRequest request = new DirectoryListingRequest(null, 0);

		/* Call the controller method */
		ResponseEntity<?> responseEntity = directoryListingController.directoryList(request);

		/* Verify the response */
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals("Missing 'path' parameter.", responseEntity.getBody());
	}

	@Test
	void testDirectoryListSuccess() {
		/* Create a valid request */
		DirectoryListingRequest request = new DirectoryListingRequest("valid/path", 0);

		/* Create a mock response from the directoryListingService */
		ArrayList<String> listing = new ArrayList<>();
		listing.add("file1");
		listing.add("file2");

		/* Mock the behavior of directoryListingService */
		when(directoryListingService.blockingListContents("valid/path", 0)).thenReturn(listing);

		/* Call the controller method */
		ResponseEntity<?> responseEntity = directoryListingController.directoryList(request);

		/* Verify the response */
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(listing, responseEntity.getBody());
	}

	@Test
	void testDirectoryListDirectoryNotFound() {
		/* Create a valid request */
		DirectoryListingRequest request = new DirectoryListingRequest("/invalid/path", 0);

		/* Mock the behavior of directoryListingService to return null (directory not found) */
		when(directoryListingService.blockingListContents("/invalid/path", 0)).thenReturn(null);

		/* Call the controller method */
		ResponseEntity<?> responseEntity = directoryListingController.directoryList(request);

		/* Verify the response */
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
		assertEquals("Directory for listing does not exist!", responseEntity.getBody());
	}

}
