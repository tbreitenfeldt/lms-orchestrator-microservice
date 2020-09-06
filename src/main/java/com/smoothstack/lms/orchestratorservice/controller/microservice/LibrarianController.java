package com.smoothstack.lms.orchestratorservice.controller.microservice;

import java.util.Arrays;

import javax.validation.constraints.Min;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.smoothstack.lms.orchestratorservice.entity.Book;
import com.smoothstack.lms.orchestratorservice.entity.BookCopy;
import com.smoothstack.lms.orchestratorservice.entity.LibraryBranch;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/lms/librarian")
public class LibrarianController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String baseUrl = "http://librarian-service/lms/librarian";
    private static final Logger logger = LogManager.getLogger(LibrarianController.class);

    @PostMapping("/book-copies")
    public ResponseEntity<BookCopy> createBookCopy(@RequestBody BookCopy bookCopy) {
        logger.debug("request: {}", bookCopy.toString());
        BookCopy response = this.restTemplate.postForObject(baseUrl + "/book-copies", bookCopy, BookCopy.class);
        logger.debug("response: {}", bookCopy);
        return new ResponseEntity<BookCopy>(response, HttpStatus.CREATED);
    }

    @GetMapping("/books/book-copies/branches/{branchId}")
    public ResponseEntity<Book[]> getBooksNotInBookCopies(@PathVariable Long branchId) {
        Book[] response = this.restTemplate.getForObject(baseUrl + "/books/book-copies/branches/" + branchId,
                Book[].class);
        logger.debug("response: {}", Arrays.deepToString(response));
        return new ResponseEntity<Book[]>(response, HttpStatus.OK);
    }

    @GetMapping("/branches")
    public ResponseEntity<LibraryBranch[]> getLibraryBranches() {
        LibraryBranch[] branches = this.restTemplate.getForObject(baseUrl + "/branches", LibraryBranch[].class);
        logger.debug("response: {}", Arrays.deepToString(branches));
        return new ResponseEntity<LibraryBranch[]>(branches, HttpStatus.OK);
    }

    @GetMapping("/branches/{branchId}")
    public ResponseEntity<LibraryBranch> getLibraryBranchById(@PathVariable Long branchId) {
        logger.debug("request: branchId={}", branchId);
        LibraryBranch response = this.restTemplate.getForObject(baseUrl + "/branches/" + branchId, LibraryBranch.class);
        logger.debug("response: {}", response.toString());
        return new ResponseEntity<LibraryBranch>(response, HttpStatus.OK);
    }

    @GetMapping("/book-copies/branches/{branchId}")
    public ResponseEntity<BookCopy[]> getBookCopies(@PathVariable Long branchId) {
        logger.debug("request: branchId={}", branchId);
        BookCopy[] response = this.restTemplate.getForObject(baseUrl + "/book-copies/branches/" + branchId,
                BookCopy[].class);
        logger.debug("response: {}", Arrays.deepToString(response));
        return new ResponseEntity<BookCopy[]>(response, HttpStatus.OK);
    }

    @PutMapping("/book-copies/books/{bookId}/branches/{branchId}")
    public ResponseEntity<BookCopy> updateBookCopy(@PathVariable Long bookId, @PathVariable Long branchId,
            @RequestBody BookCopy bookCopy) {
        logger.debug("request: bookId={}, branchId={},  body: {}", bookId, branchId, bookCopy.toString());
        this.restTemplate.put(baseUrl + "/book-copies/books/" + bookId + "/branches/" + branchId, bookCopy);
        return new ResponseEntity<BookCopy>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/branches/{branchId}")
    public ResponseEntity<LibraryBranch> updateLibraryBranch(@PathVariable Long branchId,
            @RequestBody LibraryBranch branch) {
        logger.debug("request: branchId={}, body=", branchId, branch.toString());
        this.restTemplate.put(baseUrl + "/branches/" + branchId, branch);
        return new ResponseEntity<LibraryBranch>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/book-copies/books/{bookId}/branches/{branchId}")
    public ResponseEntity<BookCopy> deleteBookCopy(@PathVariable @Min(1) Long bookId,
            @PathVariable @Min(1) Long branchId) {
        logger.debug("request: bookId={}, branchId={}", bookId, branchId);
        this.restTemplate.delete(baseUrl + "/book-copies/books/" + bookId + "/branches/" + branchId);
        return new ResponseEntity<BookCopy>(HttpStatus.NO_CONTENT);
    }

}
