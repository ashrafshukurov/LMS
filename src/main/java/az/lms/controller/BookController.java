package az.lms.controller;

import az.lms.dto.request.BookRequest;
import az.lms.dto.response.BookResponse;
import az.lms.dto.response.CategoryResponse;
import az.lms.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

import java.time.LocalDate;
import java.util.List;

/**
 * @author ashraf
 * @project LMS
 */

@RestController
@RequestMapping("/v1/book")
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final BookService bookService;

    @ApiOperation(value = "adding book", notes = "add to Book and book picture")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 400, message = "Invalid insert")
    })
    @PostMapping("/add")
    public void addBook(@RequestBody MultipartFile file, @Valid @ApiParam(name = "Object", value = "BookRequest")  BookRequest bookRequest) throws IOException {
        bookService.createBook(bookRequest, file);
    }

    @ApiOperation(value = "Update Book", notes = "Update Book based on id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 404, message = "Invalid update")
    })
    @PutMapping("/update")
    public void updateBook(@Valid @RequestBody BookRequest bookRequest) {
        bookService.updateBook(bookRequest);
    }

    @ApiOperation(value = "Get-Book-By-Id", notes = "When you enter id it will return book", response = BookResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 404, message = "Invalid getting book by Id")
    })
    @GetMapping("/getBook/{id}")
    public ResponseEntity<BookResponse> getBookByID(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @ApiOperation(value = "Getting-All-Books", notes = "It Will return Book list", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 404, message = "Invalid getting books")
    })
    @GetMapping("/all")
    public ResponseEntity<List<BookResponse>> getBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @ApiOperation(value = "Deleting Book", notes = "Deleting Book based on ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 404, message = "Invalid deleting book by Id")
    })
    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/showCategory/{id}")
    public ResponseEntity<CategoryResponse> getCategoryByBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.showCategoriesByBook(id));
    }

    @ApiOperation(value = "Upload Book_picture", notes = "Upload Book_picture you have to add file to do that")

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 400, message = "Invalid Upload book_picture")
    })
    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        bookService.uploadFile(file);

    }

    @ApiOperation(value = "GetBookByName ", notes = "you can search book by name")

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 404, message = "Not Found book")
    })
    @GetMapping("/GetBookByName/{bookname}")
    public ResponseEntity<BookResponse> getBookByName(@PathVariable String bookname) {
        return ResponseEntity.ok(bookService.getBookByName(bookname));
    }


}
