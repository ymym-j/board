package com.example.demo.board.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	private String content;
	
	@Column(name="user_id", nullable = false)
	private String userId;
	
	@Column(name="user_name", nullable = false)
	private String userName;
	
	@Column(name="register_date", nullable = false)
	private LocalDateTime registerDate;
	
	@Column(name="modify_date", nullable = false)
	private LocalDateTime modifyDate;
	
	@Builder
	public Board(Long id, String title, String content, String userName, String userId, LocalDateTime registerDate, LocalDateTime modifyDate) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.userName = userName;
		this.userId = userId;
		this.registerDate = registerDate;
		this.modifyDate = modifyDate;
	}
}
