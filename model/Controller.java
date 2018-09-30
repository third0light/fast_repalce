package com.sdhq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sdhq.[<m>].pojo.Pages;
import com.sdhq.[<m>].pojo.[<A>];                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
import com.sdhq.service.[<A>]Service;
import com.sdhq.tools.Constants;

@Controller
public class [<A>]Controller {
	@Resource
	private [<A>]Service [<a>]Service;
	
	@RequestMapping(value="/admin/[<A>]Sel",method=RequestMethod.GET)
	public ModelAndView [<A>]Sel([<A>] [<a>],Pages pages){
		//分页
		Integer pageSize;
		Integer pageStart;
		if(pages.getPageSize()==null){
			pageSize=Constants.PAGE_SIZE;
		}else{
			pageSize=pages.getPageSize();
		}
		if(pages.getPage()==null){
			pages.setPage(1);
			pageStart=0;
		}else{
			pageStart=(pages.getPage()-1)*pageSize;
		}
		Integer rows=[<a>]Service.[<A>]Size([<a>]).size();
		Integer totalpage=rows%pageSize==0?rows/pageSize:rows/pageSize+1;
		pages.setRows(rows);
		pages.setTotalPage(totalpage);
		//跳转
		ModelAndView mv=new ModelAndView("admin/[<A>]/[<A>]Sel");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("[<A>]", [<a>]);
		map.put("PageSize", pageSize);
		map.put("PageStart",pageStart);
		List<[<A>]> list= [<a>]Service.[<A>]Sel(map);
		mv.addObject("list", list);
		mv.addObject("pages", pages);
		mv.addObject("[<A>]", [<a>]);
		return mv;
	}
	
	@RequestMapping(value="/admin/[<A>]Del",method=RequestMethod.GET)
	public void [<A>]Del([<A>] [<a>], HttpServletResponse response){
		response.setContentType("text; charset=UTF-8");
		Integer i = [<a>]Service.[<A>]Del([<a>]);
		PrintWriter out =null;
		try {
			 out = response.getWriter();
			 out.print(i);
			 out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
	}
	
	@RequestMapping(value="/admin/show[<A>]Add",method=RequestMethod.GET)
	public ModelAndView show[<A>]Add(){
		return new ModelAndView("admin/[<A>]/[<A>]Add");
	}

	@RequestMapping(value = "/admin/[<A>]Add", method = RequestMethod.POST)
	public void [<A>]Add([<A>] [<a>], HttpServletResponse response,
			HttpSession session) {
		response.setContentType("text; charset=UTF-8");
		PrintWriter out = null;
		Integer i = [<a>]Service.[<A>]Add([<a>]);
		try {
			out = response.getWriter();
			out.print(i);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	@RequestMapping(value="/admin/show[<A>]Mod", method = RequestMethod.GET)
	public ModelAndView showCompanyMod([<A>] [<a>]){
		return new ModelAndView("admin/[<A>]/[<A>]Mod", "[<A>]", [<a>]Service.[<A>]Info([<a>]));
	}


	@RequestMapping(value = "/admin/[<A>]Mod", method = RequestMethod.POST)
	public void [<A>]Mod([<A>] [<a>], HttpServletResponse response, HttpSession session) {
		response.setContentType("text; charset=UTF-8");
		PrintWriter out = null;
		[<A>] fff = [<a>];
		Integer i = [<a>]Service.[<A>]Mod([<a>]);
		try {
			out = response.getWriter();
			out.print(i);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
