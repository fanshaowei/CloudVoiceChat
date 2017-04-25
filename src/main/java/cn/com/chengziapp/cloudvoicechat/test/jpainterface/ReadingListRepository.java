package cn.com.chengziapp.cloudvoicechat.test.jpainterface;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.com.chengziapp.cloudvoicechat.test.bean.Book;

public interface ReadingListRepository extends JpaRepository<Book, Long> {

	List<Book> findByReader(String reader);
}
