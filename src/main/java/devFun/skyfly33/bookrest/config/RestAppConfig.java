package devFun.skyfly33.bookrest.config;

import devFun.skyfly33.common.domain.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Date;
import java.util.List;

/**
 * Created by donghoon on 15. 8. 8..
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"devFun.skyfly33.bookrest.controller"},
        useDefaultFilters = false, includeFilters = {@Filter(Controller.class)})
public class RestAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        converters.add(mappingJackson2HttpMessageConverter());
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        return converter;
    }

    @Bean
    public Book getDummyBook() {
        Book book = new Book(10L,
                "스칼라 프로그래밍",
                "케이 호스트만",
                "프로그래밍 언어", new Date());

        return book;
    }
}
