package com.yangtzelsl.ioc4configuration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yangtzelsl.ioc4configuration.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;

}
