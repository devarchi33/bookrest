package devFun.skyfly33.common.service.internal;

import devFun.skyfly33.common.domain.Book;
import devFun.skyfly33.common.mapper.BookMapper;
import devFun.skyfly33.common.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by donghoon on 15. 8. 9..
 */
@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Book> getBooks() {
        return bookMapper.select();
    }

    @Override
    public Book getBook(Long id) {
        return bookMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int createBook(Book book) {
        return bookMapper.insert(book);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int updateBook(Book book) {
        return bookMapper.updateByPrimaryKey(book);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int deleteBook(Long id) {
        return bookMapper.deleteByPrimaryKey(id);
    }
}
