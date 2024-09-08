package com.learning.SpringBootRestService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.controller.Library;
import com.learning.controller.LibraryController;
import com.learning.controller.LibraryResponse;
import com.learning.repository.LibraryRepository;
import com.learning.service.LibraryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootRestServiceApplicationTests {

    @Autowired
    LibraryController libraryController;
    @MockBean
    LibraryService libraryService;
    @MockBean
    LibraryRepository libraryRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

//	@Before()
//	public void setup()
//	{
//		//Init MockMvc Object and build
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//	}

    @Test
    void verifyAddNewBooK() {
        Library library = new Library();
        when(libraryService.buildID(library.getIsbn(), library.getAisle())).thenReturn("isbnAisle_01");
        when(libraryService.checkBookAlreadyExist(library.getId())).thenReturn(false);
        ResponseEntity<LibraryResponse> response = libraryController.addBook(library);

        // verify status code
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        //verify response body
        LibraryResponse actualBody = response.getBody();
        assert actualBody != null;
        Assertions.assertEquals("new book added", actualBody.getMsg());
        Assertions.assertEquals(actualBody.getId(), library.getId());


    }

    @Test
    void verifyAddExistingBooK() {
        Library library = new Library();
        when(libraryService.buildID(library.getIsbn(), library.getAisle())).thenReturn("isbnAisle_01");
        when(libraryService.checkBookAlreadyExist(library.getId())).thenReturn(true);
        ResponseEntity<LibraryResponse> response = libraryController.addBook(library);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.ACCEPTED);

    }

    @Test
    void verifyAddBookControllerTest_MockMvc() throws Exception {
        Library library = buildLibrayObject();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(library);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        when(libraryService.buildID(library.getIsbn(), library.getAisle())).thenReturn("isbnAisle_01");
        when(libraryService.checkBookAlreadyExist(library.getId())).thenReturn(false);
        when(libraryRepository.save(any())).thenReturn(library);
        this.mockMvc.perform(post("/addBook").contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andDo(print())
                .andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(library.getId()));

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
