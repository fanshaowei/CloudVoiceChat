package cn.com.chengziapp.cloudvoicechat.test.jpainterface;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.com.chengziapp.cloudvoicechat.test.bean.Reader;

public interface ReaderRepository extends JpaRepository<Reader, String> {

}
