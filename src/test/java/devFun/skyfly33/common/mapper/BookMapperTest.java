package devFun.skyfly33.common.mapper;

import devFun.skyfly33.common.config.AppConfig;
import devFun.skyfly33.common.domain.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by donghoon on 15. 8. 8..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class BookMapperTest {

    Logger logger = LoggerFactory.getLogger(BookMapperTest.class);

    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    BookMapper bookMapper = (BookMapper) ctx.getBean("bookMapper");

    @Before
    public void setUpTest() {
//        Book Table init.
        bookMapper.deleteAllBook();
        List<Book> initBooks = bookMapper.select();
        assertEquals(0, initBooks.size());
//        Book Table Sample Data Insert.
        List<Book> sampleBookList = new ArrayList<Book>();
        Book book1 = new Book(1L, "a", "a", "a", new Date());
        Book book2 = new Book(2L, "b", "b", "b", new Date());
        Book book3 = new Book(3L, "c", "c", "c", new Date());
        sampleBookList.add(book1);
        sampleBookList.add(book2);
        sampleBookList.add(book3);

        for (Book book : sampleBookList) {
            bookMapper.insert(book);
        }
        sampleBookList = bookMapper.select();
        assertEquals(3, sampleBookList.size());

    }

    @Test
    public void testBookMapper() {
        List<Book> bookList = bookMapper.select();
        assertEquals(3, bookList.size());
        for (Book book : bookList) {
            logger.info("book = {}", book);
        }

        Book newBook = new Book(10L, "스칼라 프로그래밍", "케이 호스트만", "프로그래밍 언어", new Date());

        bookMapper.insert(newBook);
        bookList = bookMapper.select();
        assertEquals(4, bookList.size());

        Book selectedBook = bookMapper.selectByPrimaryKey(10L);
        logger.info("i.selectBook = {}", selectedBook);
        assertEquals(newBook.getCreator(), selectedBook.getCreator());

        newBook.setCreator("이동훈");
        bookMapper.updateByPrimaryKey(newBook);
        selectedBook = bookMapper.selectByPrimaryKey(10L);
        assertEquals("이동훈", selectedBook.getCreator());

        bookMapper.deleteByPrimaryKey(1L);
        selectedBook = bookMapper.selectByPrimaryKey(1L);
        assertNull(selectedBook);
    }

}
