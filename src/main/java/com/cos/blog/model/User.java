package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
//ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity //User class가 읽어서 자동으로 MySQL에 테이블이 생성
//@DynamicInsert // insert시에 null인 필드 제외
public class User {
	
	
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결 된 DB 넘버링 전략
	private int id;
	
	@Column(nullable = false, length = 30,unique = true)
	private String username; //아이디
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("user")
	//DB는 RoleType 없음
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum을 쓰는게 좋다 //ADMIN,USER
	
	@CreationTimestamp // 시간이 자동입력
	private Timestamp createDate;
}
