package devFun.skyfly33.common.service;

import static org.junit.Assert.*;

import devFun.skyfly33.common.config.AppConfig;
import devFun.skyfly33.common.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by donghoon on 15. 8. 9..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class BookServiceTest {

    Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    BookService bookService;

    @Test
    public void testGetBooks() {
        List<Book> books = bookService.getBooks();
        assertEquals(3, books.size());

        for (Book book : books) {
            logger.info("Book : {}", book);
        }
    }

    @Test
    public void testGetBook() {
        Book selectedBook = bookService.getBook(2L);
        assertNotNull(selectedBook);
        assertEquals("b", selectedBook.getTitle());
        assertEquals("b", selectedBook.getCreator());
    }

    @Test
    @Rollback(true)
    public void testCreateBook() {
        Book selectedBook = bookService.getBook(10L);
        assertNotNull("아이디가 10번인 도서 정보가 이미 존재 합니다.", selectedBook);

        Book newBook = new Book(11L, "스칼라 프로그래밍", "케이 호스트만", "프로그래밍 언어", new Date());
        bookService.createBook(newBook);
        assertNotNull("아이디가 11번인 도서 정보를 가져올 수 없습니다.", selectedBook);

        assertEquals("스칼라 프로그래밍", selectedBook.getTitle());
        assertEquals("이동훈", selectedBook.getCreator());
    }

    @Test
    public void testUpdateBook() {
        Book selectedBook = bookService.getBook(3L);
        assertNotNull("아이디가 3번인 도서 정보를 가져 왔습니다.", selectedBook);

        assertEquals("c", selectedBook.getTitle());
        assertEquals("c", selectedBook.getCreator());

        Book updateBook = new Book(3L, "스프링", "이동훈","프로그래밍 언어", new Date());
        bookService.updateBook(updateBook);

        selectedBook = bookService.getBook(3L);
        assertEquals("스프링", selectedBook.getTitle());
        assertEquals("이동훈", selectedBook.getCreator());
    }

    @Test
    public void testDeleteBook() {
        Book selectedBook = bookService.getBook(2L);
        assertNotNull(selectedBook);

        bookService.deleteBook(2L);
        selectedBook = bookService.getBook(2L);
        assertNull("아이디가 2L인 도서를 가져올 수 없습니다.", selectedBook);
    }
}
