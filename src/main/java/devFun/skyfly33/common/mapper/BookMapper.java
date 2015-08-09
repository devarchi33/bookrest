package devFun.skyfly33.common.mapper;

import devFun.skyfly33.common.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by donghoon on 15. 8. 8..
 */
@Repository("bookMapper")
public interface BookMapper {

    /**
     * 도서 정보 목록으로 조회한다.
     * @return
     */
    List<Book> select();

    /**
     * 도서 상세 정보를 조회한다.
     * @param id
     * @return
     */
    Book selectByPrimaryKey(Long id);

    /**
     * 도서 정보를 등록한다.
     * @param book
     * @return
     */
    int insert(Book book);

    /**
     * 도서 정보를 수정한다.
     * @param book
     * @return
     */
    int updateByPrimaryKey(Book book);

    /**
     * 도서 정보를 삭제한다.
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);
}
