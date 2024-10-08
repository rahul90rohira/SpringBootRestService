package com.learning.controller;

import com.learning.repository.LibraryRepository;
import com.learning.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LibraryController {

    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    LibraryService libraryService;

    LibraryResponse libraryResponse = new LibraryResponse();

    private static final Logger logger=LoggerFactory.getLogger(LibraryController.class);


    @PostMapping("/addBook")
    public ResponseEntity<LibraryResponse> addBook(@RequestBody Library library) {
        String id = libraryService.buildID(library.getIsbn(), library.getAisle());


        if (!libraryService.checkBookAlreadyExist(id)) {

            logger.info("Book not exist ,creating new book");
            libraryService.checkBookAlreadyExist(id);

            library.setId(id);
            libraryRepository.save(library);

            libraryResponse.setMsg("new book added");
            libraryResponse.setId(id);

//          return libraryResponse;
            return new ResponseEntity<LibraryResponse>(libraryResponse, HttpStatus.CREATED);
        }
        else {
            logger.info("book already exist");
            libraryResponse.setMsg("book already exist");
            libraryResponse.setId(id);
            return new ResponseEntity<LibraryResponse>(libraryResponse, HttpStatus.ACCEPTED);
        }

    }

    @GetMapping("/getBooks/{id}")
    public ResponseEntity getBookById(@PathVariable String id) {
        if (libraryService.checkBookAlreadyExist(id)) {
            Library library = libraryRepository.findById(id).get();
            return new ResponseEntity<>(library, HttpStatus.ACCEPTED);
            // return library;
        } else {
            logger.info("book not exist");
            libraryResponse.setMsg("Book not exist of id " + id);
            return new ResponseEntity(libraryResponse, HttpStatus.NOT_FOUND);

            // or we can achive using try catch exception . In ,catch we can throw exception using below class
            //throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/getBooks/author")
    public List<Library> getBookDetailsByAuthor(@RequestParam(value = "authorname") String author) {

        List<Library> bookDetails = libraryRepository.findBookDetailsByAuthor(author);
        return bookDetails;

    }

    @GetMapping("/getAllBook")
    public List<Library> getAllBook() {

     return  libraryRepository.findAll();


    }

    @PutMapping("/updateBook/{id}")
    public Library LibraryupdateBook(@PathVariable String id, @RequestBody Library library) {
        Library existingBook = libraryRepository.findById(id).get();
        existingBook.setIsbn(library.getIsbn());
        existingBook.setAisle(library.getAisle());
        existingBook.setAuthor(library.getAuthor());
        existingBook.setBook_name(library.getBook_name());
        libraryRepository.save(existingBook);
//             libraryResponse.setMsg("book updated");
//             libraryResponse.setId(id);
        return existingBook;


    }

    @PostMapping("/deletebook")
    public String deleteBook(@RequestBody Library library) {
        Optional<Library>  bookDelete=libraryRepository.findById(library.getId());

        if(bookDelete.isPresent()) {
            libraryRepository.delete(library);
            return "Book Deleted Successfully" + library.getId();

        }
        logger.info("book not exist for delete");
        return "Book not exist of id " + library.getId();


    }


}



