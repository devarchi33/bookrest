package devFun.skyfly33.common.mapper;

import static org.junit.Assert.*;

import devFun.skyfly33.common.config.AppConfig;
import devFun.skyfly33.common.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by donghoon on 15. 8. 8..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class BookMapperTest {

    Logger logger = LoggerFactory.getLogger(BookMapperTest.class);

    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    BookMapper bookMapper = (BookMapper) ctx.getBean("bookMapper");

    @Test
    public void testBookMapper() {
        List<Book> bookList = bookMapper.select();
        assertEquals(3, bookList.size());
    }

}
