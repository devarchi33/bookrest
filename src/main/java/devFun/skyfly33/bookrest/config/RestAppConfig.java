package devFun.skyfly33.bookrest.config;

import devFun.skyfly33.common.domain.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Date;

/**
 * Created by donghoon on 15. 8. 8..
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"devFun.skyfly33.bookrest.controller"},
        useDefaultFilters = false, includeFilters = {@ComponentScan.Filter(Controller.class)})
public class RestAppConfig {

    @Bean
    public Book getDummyBook() {
        Book book = new Book(10L,
                "스칼라 프로그래밍",
                "케이 호스트만",
                "프로그래밍 언어", new Date());

        return book;
    }
}
