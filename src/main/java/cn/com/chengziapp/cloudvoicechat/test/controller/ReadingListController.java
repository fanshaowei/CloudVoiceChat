package cn.com.chengziapp.cloudvoicechat.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.chengziapp.cloudvoicechat.test.bean.Book;
import cn.com.chengziapp.cloudvoicechat.test.jpainterface.ReadingListRepository;
import cn.com.chengziapp.cloudvoicechat.test.properties.AmazonProperties;

@RestController
@RequestMapping("/reader")
public class ReadingListController {
	
	private AmazonProperties amazonProperties;
	
	private ReadingListRepository readingListRepository;
	
	@Autowired
	public ReadingListController(ReadingListRepository readingListRepository,
			AmazonProperties amazonProperties){
		this.readingListRepository = readingListRepository;
		this.amazonProperties = amazonProperties;
	}
	
	@RequestMapping(value="/{reader}", method=RequestMethod.GET)
	public String readersBooks(@PathVariable("reader") String reader, Model model){
		List<Book> readingList = readingListRepository.findByReader(reader);
		if(readingList != null){
			model.addAttribute("books", readingList);
			model.addAttribute("reader",reader);
			model.addAttribute("amazonId",amazonProperties.getAssociateId());
		}
		return "readingList";
	}
	
	@RequestMapping(value="/{reader}", method=RequestMethod.POST)
	public String addToReadingList(@PathVariable("reader") String reader, Book book){
		book.setReader(reader);
		readingListRepository.save(book);
		return "redirect:/{reader}";
	}
		
}
