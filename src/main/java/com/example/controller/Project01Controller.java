package com.example.controller;

import com.example.vo.PageMaker2;
import com.example.vo.VO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Blog;
import com.example.persistence.BlogReplyRepository;
import com.example.persistence.BlogRepository;
import com.example.service.BlogServiceImpl;

@Controller
@RequestMapping("/project01/")
@Log
public class Project01Controller {
	
	private final BlogRepository blogRepository;
	private final BlogReplyRepository repo;
	private final BlogServiceImpl service;
	
	@Autowired
	public Project01Controller(BlogRepository blogRepository, BlogReplyRepository repo, BlogServiceImpl service) {
		this.blogRepository = blogRepository;
		this.repo = repo;
		this.service = service;
	}
//	@Autowired
//	BlogServiceImpl service;
	
	@GetMapping("home")
	public void home(@ModelAttribute("vo") VO vo,Model model, Long bno) {
		
		
		Pageable pageable = vo.makePageable("bno");
		
		Page<Object[]> result = blogRepository.search(vo.getType(), vo.getKeyword(),pageable);

		
		model.addAttribute("result", new PageMaker2<>(result));
	}
	@GetMapping("register")
	public void registerGet(@ModelAttribute("vo") Blog vo) {
		
	}
	@PostMapping("register")
	public String registerPost(@ModelAttribute("vo") Blog vo) {
		
		blogRepository.save(vo);
		
		return "redirect:/project01/home";
	}
	

	@RequestMapping("view")
	public void view(Long bno, @ModelAttribute("pagevo") VO pagevo, Model model) {
//		
		log.info("bno : "+bno);
		blogRepository.findById(bno).ifPresent(blog -> model.addAttribute("vo",blog));

	}
	
	@Secured(value= {"ROLE_ADMIN"})
	@GetMapping("modify")
	public void modify(Long bno, @ModelAttribute("pagevo") VO vo, Model model) {
		
		
		log.info("Modify info: "+bno);
		blogRepository.findById(bno).ifPresent(blog ->model.addAttribute("vo", blog));
	
	}
	
	@Secured(value= {"ROLE_ADMIN"})
	@PostMapping("modify")
	public String modifyPost(Blog blog, VO vo, RedirectAttributes rttr) {
		blogRepository.findById(blog.getBno()).ifPresent(origin ->{
			origin.setTitle(blog.getTitle());
			origin.setCategory(blog.getCategory());
			origin.setContent(blog.getContent());
			
			blogRepository.save(origin);
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
