package com.learning.controller;

import com.learning.repository.LibraryRepository;
import com.learning.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {

    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    LibraryService libraryService;


    @PostMapping("/addBook")
    public ResponseEntity<LibraryResponse> addBook(@RequestBody Library library)
    {
        String id= libraryService.buildID(library.getIsbn(),library.getAisle());
        LibraryResponse libraryResponse = new LibraryResponse();

        if (!libraryService.checkBookAlreadyExist(id)) {


            libraryService.checkBookAlreadyExist(id);

            library.setId(id);
            libraryRepository.save(library);

            libraryResponse.setMsg("new book added");
            libraryResponse.setId(id);

//       return libraryResponse;
            return new ResponseEntity<LibraryResponse>(libraryResponse, HttpStatus.CREATED);
        }
        else
        {
            libraryResponse.setMsg("book already exist");
            libraryResponse.setId(id);
            return new ResponseEntity<>(libraryResponse, HttpStatus.ACCEPTED);
        }

    }



}
