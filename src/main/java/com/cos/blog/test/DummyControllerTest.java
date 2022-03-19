package com.cos.blog.test;

import org.springframework.data.domain.Pageable;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import java.util.List;


//html파일이 아니라 data를 리턴해주는 controller
@RestController
public class DummyControllerTest {
	
	@Autowired //의존성 주입(DI)
	private UserRepository userRepository;
	
	//save함수는 id를 전달하지 않으면 insert를 해주고
	//id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	//해당 id에 대한 데이터가 없으면 insert해줌
	
	//email, password, json데이터 받을 때 requestbody필요 !
	//요청받은 비밀번호, 이메일 수정하는 코드
	
	//입력받은 유저를 삭제하는 코드
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다";
		}
		
		return "삭제 되었습니다. id :"+id;
	}
	
	@Transactional // 함수 종료시에 자동으로 commit
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id : "+id);
		System.out.println("password : "+requestUser.getPassword());
		System.out.println("email : "+requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user);
		//더티 체킹
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User>list(){
		return userRepository.findAll();
	}
	
	//한 페이지당 2건의 데이터 리턴받기
	@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size=2 , sort = "id" , direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent(); 
		return pagingUser;
	}
	
	//{id} 주소로 파라미터 전달 받을 수 있다
	//http://localhost:8000/blog/dummy/user/3
	//->3이 id안에 들어오게 됨
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		//람다식
		//User user = userRepository.findById(id).orElseThrow(()->{
		//		return new IllegalArgumentException("해당 사용자는 없습니다.");
		//	});
	User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
		@Override
		public IllegalArgumentException get() {
				// TODO Auto-generated method stub
			return new  IllegalArgumentException ("해당 유저는 없습니다. id : "+id);
		}
	});
		//요청 : 웹브라우저
		//user 객체 = 자바 오브젝트
		//변환 (웹브라우저가 이해할 수 있는 데이터 -> json)
		//스프링 부트 = MessageConverter가 응답시에 자동 작동
		//만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		//user 오브젝트를 json으로 변환해서 브라우저에 던져줌
		return user;
	}
	
	//http://localhost:8000/blog/dummy/join (요청)
	//http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다";
	}
}
