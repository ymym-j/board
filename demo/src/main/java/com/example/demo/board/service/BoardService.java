package com.example.demo.board.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.board.entity.Board;
import com.example.demo.board.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public List<Board> list(){
		return boardRepository.findAll();
	}
	
	public Board get(Long id) {
		return boardRepository.findById(id).get();
	}
	
	public Long save(Board board, String userName, String userId) {
		return boardRepository.save(Board.builder()
				.title(board.getTitle())
				.content(board.getContent())
				.userId(userId)
				.userName(userName)
				.registerDate(LocalDateTime.now())
				.modifyDate(LocalDateTime.now()).build()).getId();
	}
	
	public void update(Long id, Board newBoard) {
		
		Optional<Board> board = boardRepository.findById(id);
		board.ifPresent(selectBoard ->{
			selectBoard.setTitle(newBoard.getTitle());
			selectBoard.setContent(newBoard.getContent());
			selectBoard.setModifyDate(LocalDateTime.now());
			boardRepository.save(selectBoard);
		});
	}
	
	public void delete(Long id) {
		Optional<Board> user = boardRepository.findById(id);
		 
        user.ifPresent(selectUser ->{
        	boardRepository.delete(selectUser);
        });
	}
}
