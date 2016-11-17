package globantWorkshop.services.implementation;

import globantWorkshop.models.dao.BookDao;
import globantWorkshop.models.entities.Book;
import globantWorkshop.services.interfaces.BookServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leandromaro on 29/10/16.
 */
@Service
public class BookService implements BookServiceInterface {

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    @Autowired
    BookDao bookDao;

    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    @Override
    public ArrayList<Book> getAllBooks() {
        List<Book> books = new ArrayList<Book>();
        try {
            books = bookDao.getAllBooks();
        }
        catch (Exception e){
            throw e;
        }
        return (ArrayList<Book>) books;
    }

    @Override
    public Book create(Book book) throws PersistenceException {
        bookDao.create(book);
        return book;
    }

    @Override
    public String delete(int id) {
        Book b = bookDao.getById(id);
        bookDao.delete(b);
        return "Book successfully deleted!";
    }

    @Override
    public String updateBook(Book newBook)
    {
        String msg = "Error updating the book "+ newBook.getName();
        try {
            bookDao.update(newBook);
            msg = "Book successfully updated!";

        } catch (Exception e) {
            msg+="\n Error: "+e.toString();
        }
        return msg;
    }

    @Override
    public Book findBookById(int bookId) throws TransactionSystemException {
        Book book = new Book();
        try{
            book = bookDao.getById(bookId);

        }
        catch (Exception e) {
            throw e;
        }
        return book;
    }

}
