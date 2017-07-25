package com.hubsport.controller;

import java.util.List;
import java.util.Map;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hubsport.domain.User;
import com.hubsport.service.UserService;

@Controller
@RequestMapping(value = "/phones")
public class UsersControler {
	
	@Autowired
	UserService userService;
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/all/{type}", "/all" }, method = RequestMethod.GET)
	public ModelAndView all(@PathVariable Map<String, String> pathVariablesMap, HttpServletRequest req) {

		PagedListHolder<User> productList = null;

		String type = pathVariablesMap.get("type");
		System.out.println(type);
		if (null == type) {
			// First Request, Return first set of list
			List<User> userList = userService.findAllUsers();

			productList = new PagedListHolder<User>();
			productList.setSource(userList);
			productList.setPageSize(20);

			req.getSession().setAttribute("userList", productList);

			printPageDetails(productList);

		} else if ("next".equals(type)) {
			// Return next set of list
			productList = (PagedListHolder<User>) req.getSession().getAttribute("userList");

			productList.nextPage();

			printPageDetails(productList);

		} else if ("prev".equals(type)) {
			// Return previous set of list
			productList = (PagedListHolder<User>) req.getSession().getAttribute("userList");

			productList.previousPage();

			printPageDetails(productList);

		} else {
			// Return specific index set of list
			System.out.println("type:" + type);

			productList = (PagedListHolder<User>) req.getSession().getAttribute("userList");

			int pageNum = Integer.parseInt(type);

			productList.setPage(pageNum);

			printPageDetails(productList);
		}

		ModelAndView mv = new ModelAndView("users2");

		return mv;
	}

	private void printPageDetails(PagedListHolder<User> productList) {

		System.out.println("curent page - productList.getPage() :" + productList.getPage());

		System.out.println("Total Num of pages - productList.getPageCount :" + productList.getPageCount());

		System.out.println("is First page - productList.isFirstPage :" + productList.isFirstPage());

		System.out.println("is Last page - productList.isLastPage :" + productList.isLastPage());
	}


}
