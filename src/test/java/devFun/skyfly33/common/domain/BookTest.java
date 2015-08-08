package devFun.skyfly33.common.domain;

import devFun.skyfly33.bookrest.config.RestAppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by donghoon on 15. 8. 9..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestAppConfig.class})
public class BookTest {

    Book book = new Book(10L,
            "스칼라 프로그래밍",
            "케이 호스트만",
            "프로그래밍 언어", new Date());

    @Test
    public void printPojomaticToString() {
        System.out.println("Pojomatic toString : " + book.toString());
    }
}
