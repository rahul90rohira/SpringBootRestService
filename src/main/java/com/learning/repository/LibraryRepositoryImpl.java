package com.learning.repository;

import com.learning.controller.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class LibraryRepositoryImpl implements LibraryRepositoryCustom {

     @Lazy
     @Autowired
     LibraryRepository libraryRepository;



    @Override
    public List<Library> findBookDetailsByAuthor(String authorName) {



        List<Library> matchAuthorRecords = new ArrayList<>();
        Iterator<Library> allRecords = libraryRepository.findAll().iterator();

        for (allRecords.next(); allRecords.hasNext(); ) {
            Library library = (Library) allRecords.next();
            if (library.getAuthor().equals(authorName)) {

                matchAuthorRecords.add((Library) library);
            }
        }
        return matchAuthorRecords;
    }

/*
        List<Library> allRecords=libraryRepository.findAll();
        System.out.println("all record"+allRecords);
        List<Library> matchAuthorRecords = new ArrayList<>();
        for(Library item:allRecords){
            System.out.println("lib"+item);
            if(item.getAuthor().equalsIgnoreCase(authorName)){
                matchAuthorRecords.add(item);
            }
        }
        return matchAuthorRecords;
    }}
     */
    }

