package com.learning.SpringBootRestService;

import com.learning.controller.Library;
import com.learning.controller.LibraryController;
import com.learning.controller.LibraryResponse;
import com.learning.repository.LibraryRepository;
import com.learning.service.LibraryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SpringBootRestServiceApplicationTests {

	@Autowired
	LibraryController libraryController;
	@MockBean
	LibraryService libraryService;
	@MockBean
	LibraryRepository libraryRepository;


	@Test
	void verifyAddNewBooK() {
		Library library = new Library();
		when(libraryService.buildID(library.getIsbn(), library.getAisle())).thenReturn("isbnAisle_01");
		when(libraryService.checkBookAlreadyExist(library.getId())).thenReturn(false);
		ResponseEntity<LibraryResponse> response= libraryController.addBook(library);

		// verify status code
		Assertions.assertEquals(response.getStatusCode(),HttpStatus.CREATED);

		//verify response body
		LibraryResponse actualBody= response.getBody();
        assert actualBody != null;
        Assertions.assertEquals("new book added", actualBody.getMsg());
		Assertions.assertEquals(actualBody.getId(), library.getId());



	}

	@Test
	void verifyAddExistingBooK() {
		Library library = new Library();
		when(libraryService.buildID(library.getIsbn(), library.getAisle())).thenReturn("isbnAisle_01");
		when(libraryService.checkBookAlreadyExist(library.getId())).thenReturn(true);
		ResponseEntity<LibraryResponse> response= libraryController.addBook(library);
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.ACCEPTED);

	}


	public Library buildLibrayObject() {
		Library library = new Library();
		library.setBook_name("Biology");
		library.setIsbn("isbn");
		library.setAisle("Aisle_01");
		library.setAuthor("Mahima");
		library.setId("isbnAisle_01");
		return library;


	}
}
