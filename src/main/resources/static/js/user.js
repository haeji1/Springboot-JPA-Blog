let index = {
	init: function(){
		$("#btn-save").on("click", ()=>{ //functin(){} , ()=>{} this를 바인딩 하기 위해서
			this.save();
		});
		$("#btn-update").on("click", ()=>{ //functin(){} , ()=>{} this를 바인딩 하기 위해서
			this.update();
		});
	},
	
	save: function(){
		//alert("user의 save함수 호출됨");
		let data = {
			username : $("#username").val(),
			password : $("#password").val(),
			email : $("#email").val(),
		};
		
		//console.log(data);
		
		//ajax호출시 default가 비동기 호출
		//ajax 통신을 이용해서 3개의 데이터 json으로 변경하여 insert 요청
		//ajax가 통신 성공하고 서버가 json 리턴해주면 자동으로 자바 오브젝트로 변환
		$.ajax({
			type:"POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),
			contentType:"application/json; charset=utf-8", //body데이터가 어떤 타입인지
			dataType:"json" //서버로 요청해서 응답이 왔을 때 json이면 자바스크립트로 변경
		}).done(function(resp){
			if(resp.status === 500){
				alert("회원가입에 실패하였습니다.");
			}else {
				alert("회원가입이 완료되었습니다.");
				location.href = "/";
			}
		
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
		
	},
	
	update: function(){
		//alert("user의 save함수 호출됨");
		let data = {
			id : $("#id").val(),
			username : $("#username").val(),
			password : $("#password").val(),
			email : $("#email").val(),
		};
		
		$.ajax({
			type:"PUT",
			url: "/user",
			data: JSON.stringify(data),
			contentType:"application/json; charset=utf-8", 
			dataType:"json" 
		}).done(function(resp){
			alert("회원수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
		
	},
}

index.init();
