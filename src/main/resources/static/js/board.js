let index = {
	init: function(){
		$("#btn-save").on("click", ()=>{ //functin(){} , ()=>{} this를 바인딩 하기 위해서
			this.save();
		});
		$("#btn-delete").on("click", ()=>{ //functin(){} , ()=>{} this를 바인딩 하기 위해서
			this.deleteById();
		});
		$("#btn-update").on("click", ()=>{ //functin(){} , ()=>{} this를 바인딩 하기 위해서
			this.update();
		});
	},
	
	save: function(){
		//alert("user의 save함수 호출됨");
		let data = {
			title : $("#title").val(),
			content : $("#content").val()
		};
		
		//console.log(data);
		
		//ajax호출시 default가 비동기 호출
		//ajax 통신을 이용해서 3개의 데이터 json으로 변경하여 insert 요청
		//ajax가 통신 성공하고 서버가 json 리턴해주면 자동으로 자바 오브젝트로 변환
		$.ajax({
			type:"POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType:"application/json; charset=utf-8", //body데이터가 어떤 타입인지
			dataType:"json" //서버로 요청해서 응답이 왔을 때 json이면 자바스크립트로 변경
		}).done(function(resp){
			alert("글쓰기가 완료되었습니다.");
			//console.log(resp);
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	
	deleteById: function(){
		let id = $("#id").text();
		
		$.ajax({
			type:"DELETE",
			url: "/api/board/"+id,
			dataType:"json" 
		}).done(function(resp){
			alert("삭제가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	
	update: function(){
		let id = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		
		$.ajax({
			type:"PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data),
			contentType:"application/json; charset=utf-8", 
			dataType:"json"
		}).done(function(resp){
			alert("글수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
}

index.init();
