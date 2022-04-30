package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Blog;
import com.example.domain.BlogReply;
import com.example.domain.Member;
import com.example.persistence.BlogReplyRepository;
import com.example.persistence.BlogRepository;
import com.example.persistence.BlogRepositoryImpl;
import com.example.service.BlogServiceImpl;
//import com.example.persistence.BlogRepository;
//import com.example.persistence.CustomBlogRepository;
//import com.example.persistence.CustomBlogRepositoryImpl;
//import com.example.persistence.CustomJpaBlogRepository;
//import com.example.persistence.CustomJpaBlogRepositoryImpl;
//import com.example.persistence.CustomJpaBlogRepository;
import com.example.vo.PageMaker2;

//import com.example.vo.PageMaker;
//import com.example.vo.PageVo;
import com.example.vo.VO;

import com.querydsl.core.types.Predicate;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/project01/")
@Log
public class Project01Controller {
	
	private final BlogRepository fuck;
	private final BlogReplyRepository repo;
	private final BlogServiceImpl service;
	
	@Autowired
	public Project01Controller(BlogRepository fuck, BlogReplyRepository repo, BlogServiceImpl service) {
		this.fuck = fuck;
		this.repo = repo;
		this.service = service;
	}
//	@Autowired
//	BlogServiceImpl service;
	
	@GetMapping("home")
	public void home(@ModelAttribute("vo") VO vo,Model model, Long bno) {
		
		
		Pageable pageable = vo.makePageable("bno");
		
		Page<Object[]> result = fuck.search(vo.getType(), vo.getKeyword(),pageable);
		
		//Predicate search = repo.makePredicate(vo.getType(), vo.getKeyword());
		
		model.addAttribute("result", new PageMaker2<>(result));
		//fuck.findById(bno).ifPresent(blog -> model.addAttribute("login",blog));
		
		//model.addAttribute("uid", member.getUid());

		
		//return "project01/home";
	}
	@GetMapping("register")
	public void registerGet(@ModelAttribute("vo") Blog vo) {
		
	}
	@PostMapping("register")
	public String registerPost(@ModelAttribute("vo") Blog vo) {
		
		fuck.save(vo);
		
		return "redirect:/project01/home";
	}
	

	@RequestMapping("view")
	public void view(Long bno, @ModelAttribute("pagevo") VO pagevo, Model model) {
//		Pageable pageable = vo1.makeViewPageable("bno");
//		Page<Object[]> result1 = fuck.viewPage(pageable);
//		
		log.info("bno : "+bno);
//		model.addAttribute("result1", new PageMakerView<>(result1));
		fuck.findById(bno).ifPresent(blog -> model.addAttribute("vo",blog));
		

//		return "project01/view";
	}
	
	@Secured(value= {"ROLE_ADMIN"})
	@GetMapping("modify")
	public void modify(Long bno, @ModelAttribute("pagevo") VO vo, Model model) {
		
		
		log.info("Modify info: "+bno);
		fuck.findById(bno).ifPresent(blog ->model.addAttribute("vo", blog));
	
	}
	
	@Secured(value= {"ROLE_ADMIN"})
	@PostMapping("modify")
	public String modifyPost(Blog blog, VO vo, RedirectAttributes rttr) {
		fuck.findById(blog.getBno()).ifPresent( origin ->{
			origin.setTitle(blog.getTitle());
			origin.setCategory(blog.getCategory());
			origin.setContent(blog.getContent());
			
			fuck.save(origin);
			rttr.addAttribute("msg", "success");
			rttr.addAttribute("bno", origin.getBno());
			
		});
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());
		log.info("modify"+blog.getBno());
		
		return "redirect:/project01/view";
	}
	
	@Secured(value= {"ROLE_ADMIN"})
	@PostMapping("delete")
	public String delete(Long bno, VO vo,  RedirectAttributes rttr) {
		
		

		Blog blog = new Blog();
		blog.setBno(bno);
		
		service.del(blog, bno);
		//fuck.deleteById(bno);
		rttr.addFlashAttribute("msg", "success");
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());
		log.info("delete bno:"+bno);
		return "redirect:/project01/home";
	}

}
