package com.example.demo.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.board.entity.Board;
import com.example.demo.board.service.BoardService;

@Controller
@RequestMapping (value="/oauth/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping({"/",""})
	public String list(Model model){
		List<Board> boardList = boardService.list();
		model.addAttribute("boardList", boardList);
		return "board/list";
	}
	
	@GetMapping("/post")
	public String post(){
		return "board/post";
	}

	@PostMapping("/post") 
	public String write(Board board, HttpSession session, Model model) {
		boardService.save(board, 
				session.getAttribute("userName").toString(), 
				session.getAttribute("userId").toString());
		model.addAttribute("boardList", boardService.list());
		return "redirect:/oauth/board";
	}
	
	@GetMapping("/post/{id}")
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("board", boardService.get(id));
		return "board/detail";
	}
	
	@PutMapping("/post/{id}")
	public String update(@PathVariable("id")Long id, Board board) {
		boardService.update(id, board);
		return "redirect:/oauth/board";
	}
	
	@DeleteMapping("/post/{id}")
	public String delete(@PathVariable("id") Long id, Model model, HttpSession session) {
		if(!session.getAttribute("userId").equals(boardService.get(id).getUserId())) {
			return "redirect:/denied";
		}
		boardService.delete(id);
		return "redirect:/oauth/board";
	}
}
