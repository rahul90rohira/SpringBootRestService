package com.learning.service;

import com.learning.controller.Library;
import com.learning.controller.LibraryResponse;
import com.learning.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryService {

    @Autowired
    LibraryRepository libraryRepository;

    public String buildID(String isbn,String aisle)
    {
        return isbn+ aisle ;
    }
    public Boolean checkBookAlreadyExist(String id)
    {

    Optional<Library> lib =libraryRepository.findById(id);

    if (lib.isPresent())
    {
        return true;
    }
    else
        return false;

    }

}
