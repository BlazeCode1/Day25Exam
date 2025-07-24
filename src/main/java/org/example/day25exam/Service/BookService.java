package org.example.day25exam.Service;

import org.example.day25exam.Model.Book;
import org.example.day25exam.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService {
        ArrayList<Book> books = new ArrayList<>();


        public ArrayList<Book> getBooks(){
            return books;
        }



        public boolean addBook(Book book){
            for(Book b : books){
                if(book.getID().equals(b.getID())){
                    return false;
                }
            }
            books.add(book);
            return true;
        }

        public boolean updateBook(Book book){
            for (Book b : books){
                if(b.getID().equals(book.getID())){
                    books.set(books.indexOf(b),book);
                    return true;
                }

            }
            return false;
        }

        public boolean deleteBook(String ID){
            for (Book b : books){
                if(ID.equals(b.getID())){
                    books.remove(b);
                    return true;
                }
            }
            return false;
        }

        public Book getBookByName(String name){
            for (Book b: books){
                if(b.getName().equalsIgnoreCase(name)){
                    return b;
                }
            }
            return null;
        }
    public ArrayList<Book> getBooksByCategory(String category){
            ArrayList<Book> sameCategory = new ArrayList<>();
            if(!category.equalsIgnoreCase("novel") && !category.equalsIgnoreCase("academic")){
                return null;
            }
            for (Book b: books){
                if(b.getCategory().equalsIgnoreCase(category)){
                    sameCategory.add(b);
                }
            }
            return sameCategory;
    }

    public ArrayList<Book> numberOfPages(int numberOfPages){
            ArrayList<Book> sameNumberOfPages = new ArrayList<>();
            for (Book b : books){
                if(b.getNumber_of_pages() >= numberOfPages){
                    sameNumberOfPages.add(b);
                }
            }
            return sameNumberOfPages;
    }

    public int changeStatus(String librarianID, String bookID,ArrayList<User> users){
            boolean flag = false;
            for (User u : users){
                if (u.getID().equals(librarianID) && u.getRole().equalsIgnoreCase("librarian")) {
                    flag = true;
                    break;
                }
            }
        if(flag){
            for (Book b : books){
                if(bookID.equals(b.getID()) && b.isAvailable()){
                    b.setAvailable(false);
                    return 1;
                }
            }
            return 2;
        }
        return 0;
    }
}