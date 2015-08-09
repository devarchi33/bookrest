package devFun.skyfly33.common.service;

import devFun.skyfly33.common.domain.Book;

import java.util.List;

/**
 * Created by donghoon on 15. 8. 9..
 */
public interface BookService {

    /**
     * 도서 정보 목록을 조회한다.
     * @return
     */
    List<Book> getBooks();

    /**
     * 도서 상세 정보 목록을 조회한다.
     * @param id
     * @return
     */
    Book getBook(Long id);

    /**
     * 도서 정보를 등록한다.
     * @param book
     * @return
     */
    int createBook(Book book);

    /**
     * 도서 정보를 수정한다.
     * @param book
     * @return
     */
    int updateBook(Book book);

    /**
     * 도서 정보를 삭제한다.
     * @param id
     * @return
     */
    int deleteBook(Long id);
}
