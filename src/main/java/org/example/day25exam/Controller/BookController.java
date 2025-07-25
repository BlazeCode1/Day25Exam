package org.example.day25exam.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.day25exam.Api.ApiResponse;
import org.example.day25exam.Model.Book;
import org.example.day25exam.Service.BookService;
import org.example.day25exam.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getBooks(){
        return ResponseEntity.ok(bookService.getBooks());
    }


    @PostMapping("/add")
    public ResponseEntity<?> addBook(@Valid @RequestBody Book book, Errors err){
        if(err.hasErrors()){
            return ResponseEntity.badRequest().body(new ApiResponse(err.getFieldError().getDefaultMessage()));
        }
        if(bookService.addBook(book)){
            return ResponseEntity.ok(new ApiResponse("Book Added Successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Book ID Already Added"));
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateBook(@Valid @RequestBody Book book, Errors err){
        if(err.hasErrors())
            return ResponseEntity.badRequest().body(new ApiResponse(err.getFieldError().getDefaultMessage()));

        if(bookService.updateBook(book)){
            return ResponseEntity.ok(new ApiResponse("Book Updated Successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Book ID Not Found"));
    }

    @DeleteMapping("/delete/{ID}")
    public ResponseEntity<?> deleteUser(@PathVariable String ID){
        if(bookService.deleteBook(ID)){
            return ResponseEntity.ok(new ApiResponse("Book Deleted Successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Book ID Not Found"));
    }

    @GetMapping("/get/name/{name}")
    public ResponseEntity<?> getBookByName(@PathVariable String name){
        Book searchedBook = bookService.getBookByName(name);
        if(searchedBook == null){
            return ResponseEntity.badRequest().body(new ApiResponse("Book Not Found"));
        }
        return ResponseEntity.ok(searchedBook);
    }

    @GetMapping("/get/category/{category}")
    public ResponseEntity<?> getBookByCategory(@PathVariable String category){
        ArrayList<Book> filteredCategory = bookService.getBooksByCategory(category);
        if(filteredCategory == null){
            return ResponseEntity.badRequest().body(new ApiResponse("Category is wrong"));
        }
        if(filteredCategory.isEmpty()){
            return ResponseEntity.badRequest().body(new ApiResponse("Book with "+category+" Category are empty"));
        }
        return ResponseEntity.ok(filteredCategory);
    }


    @GetMapping("/get/{numberOfPages}")
    public ResponseEntity<?> getSameNumberOfPages(@PathVariable int numberOfPages){
        if(numberOfPages < 0){
            return ResponseEntity.badRequest().body(new ApiResponse("Number of Pages Should Be Positive"));
        }
        ArrayList<Book> sameNumberPages = bookService.numberOfPages(numberOfPages);

        if(sameNumberPages.isEmpty()){
            return ResponseEntity.badRequest().body(new ApiResponse("No Books With the same Number Of Pages"));
        }
        return ResponseEntity.ok(sameNumberPages);
    }


    @PostMapping("/change/status/{librarianID}/{bookID}")
    public ResponseEntity<?> changeBookStatus(@PathVariable String librarianID,@PathVariable String bookID){

        int status = bookService.changeStatus(librarianID,bookID,userService.getUsers());
        if(status == -1){
            return ResponseEntity.badRequest().body(new ApiResponse("No Authorized Users"));
        }
        if(status == 2){
            return ResponseEntity.badRequest().body(new ApiResponse("All Books Are unAvailable"));
        }
        if(status == 0){
            return ResponseEntity.badRequest().body(new ApiResponse("Not Authorized to change Status!"));
        }
        return ResponseEntity.ok(new ApiResponse("Book Status Changed To Unavailable"));
    }









}
