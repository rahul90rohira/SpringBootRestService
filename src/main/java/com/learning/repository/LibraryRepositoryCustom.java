package com.learning.repository;

import com.learning.controller.Library;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface LibraryRepositoryCustom  {

   List<Library> findBookDetailsByAuthor(String authorName) ;
}
