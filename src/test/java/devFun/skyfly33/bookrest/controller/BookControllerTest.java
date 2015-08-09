package devFun.skyfly33.bookrest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import devFun.skyfly33.bookrest.config.RestAppConfig;
import devFun.skyfly33.common.config.AppConfig;
import devFun.skyfly33.common.domain.Book;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by donghoon on 15. 8. 9..
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, RestAppConfig.class})
public class BookControllerTest {

    Logger logger = LoggerFactory.getLogger(BookControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    BookController bookController;

    @Before
    public void initMockMvc() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).addFilter(filter).build();
    }

    @Test
    public void testGetBooks() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/books")
                .accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].title", is("b")))
                .andExpect(jsonPath("$[0].creator", is("b")))
                .andExpect(jsonPath("$[1].id", is(10)))
                .andExpect(jsonPath("$[1].title", is("스칼라 프로그래밍")))
                .andExpect(jsonPath("$[1].creator", is("이동훈")));

    }

    @Test
    public void testGetBook() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/books/2")
                .accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.title", is("b")))
                .andExpect(jsonPath("$.creator", is("b")));
    }

    @Test
    public void testCreateBook() throws Exception {
        String content = "{\"id\" : 11, \"title\":\"바라야 내전\", " +
                "\"creator\":\"로이스 맥마스터 부졸드\", \"type\":\"외국 판타지 소설\",\"date\":131337846000}";

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book updateBook = new Book(10L, "AngularJS", "이동훈", "자바스크립트", new Date());


//        Java 2 Json
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(updateBook);
        logger.debug("content = {}", content);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/books/3")
                .contentType(MediaType.APPLICATION_JSON).content(content)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.title", is("AngularJS")))
                .andExpect(jsonPath("$.creator", is("이동훈")));
    }

    @Test
    public void testDeleteBook() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/books/100")
                .accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(3)));
    }

//    @Test
//    public void testBook() throws Exception {
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
//                .get("/books/10")
//                .accept(MediaType.valueOf("text/plain;charset=UTF-8"));
//        this.mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk());
//    }
}
