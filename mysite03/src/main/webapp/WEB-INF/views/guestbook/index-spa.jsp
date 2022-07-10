<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook-spa.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
//Application
var render = function(vo, mode) {
   var htmls = 
      "<li data-no='"+vo.no+"'>"+
      "<strong>"+ vo.name +"</strong>"+
      "<p>"+ vo.message +"</p>"+
      "<strong></strong>"+
      "<a href='' data-no='"+vo.no+"'>삭제</a>"+
      "</li>";
      
   $("#list-guestbook")[mode?"append":"prepend"](htmls);
}

var fetch = function() {
   $.ajax({
      url: "${pageContext.request.contextPath }/api/guestbook",
      type: "get",
      dataType: "json",
      success: function(response) {
         if(response.result !== 'success'){
            console.error(response.message);
            return;
         }
		response.data.forEach(function(vo){
				render(vo, true);
			})
      }      
   });
};

var messageBox = function(title, message, callback){
	$("#dialog-message")
		.attr("title", title)
		.dialog({
			width: 340,
			modal: true,
			buttons:{
				"확인":function(){
					$(this).dialog('close');
				}
			},
			close:callback 
			
		});
	$("#dialog-message p").text(message)
		
}
$(function(){
   // 삭제 다이얼로그 객체를 미리 만들기
   var dialogDelete = $("#dialog-delete-form").dialog({
	   autoOpen: false,
	   modal: true,
	   buttons:{
		   "삭제":function(){
			
			  var vo = {};
		      vo.no =$('#hidden-no').val();
		      vo.password = $("#password-delete").val();
			  $.ajax({
	    	  url: "${pageContext.request.contextPath }/api/guestbook/delete-form",
	    	  type: "delete",
	    	  dataType: "json",
	    	  contentType: "application/json",
	    	  data: JSON.stringify(vo),
	    	  success: function(response){
					if(response.result!=='success'){
						console.error(response.message);
						return;
					}
					if(response.data==-1){
						$("#password-delete").val('').focus();
						$(".validateTips-normal").hide();
						$(".validateTips-error").show();
					
					}else{
						$("#password-delete").val('');
						$("#dialog-delete-form").dialog('close');
						$("[data-no="+vo.no+"]").remove();
					}
	    	  }
	      })
		   },
		   "취소":function(){
			   $("#password-delete").val('');
			   $(this).dialog('close');
			   
		   }
	   }
   })
   // 글 삭제 버튼 Click 이벤트 처리
   $(document)
      .on("click", "#list-guestbook li a", function(event) {
         event.preventDefault();
         
         $('#hidden-no').val($(this).data('no'))
         dialogDelete.dialog('open');
         
        
         
      });
   //...
   
   fetch();
})

$(function(){
	   $("#add-form").submit(function(event){
	      event.preventDefault();
	      
	      /* Validation */
	      var vo = {};
	      vo.name = $("#input-name").val();
	      vo.password = $("#input-password").val();
	      vo.message = $("#tx-content").val();
	      
	      if($("#input-name").val() === '') {
				messageBox("글 작성", "이름이 비어 있습니다.", function(){
					$("#input-name").focus();
				})
				return;
			}
	      
	      if($("#input-password").val() === '') {
				messageBox("글 작성", "비밀번호가 비어 있습니다.", function(){
					$("#input-password").focus();
				})
				return;
			}
	      
	      if($("#tx-content").val() === '') {
				messageBox("글 작성", "내용을 입력해 주세요.", function(){
					$("#tx-content").focus();
				})
				return;
			}
	      $.ajax({
	    	  url: "${pageContext.request.contextPath }/api/guestbook/add-form",
	    	  type: "post",
	    	  dataType: "json",
	    	  contentType: "application/json",
	    	  data: JSON.stringify(vo),
	    	  success: function(response){
					if(response.result!=='success'){
						console.error(response.message);
						return;
					}
					$("#input-name").val('');
				    $("#input-password").val('');
				    $("#tx-content").val('');
					render(response.data, false);
					
	    	  }
	      })
	   })
	   
	})
	
$(function() {
	$(window).scroll(function() {
		var $window = $(this);
		var $document = $(document);
		
		var windowHeight = $window.height();
		var documentHeight = $document.height();
		var scrollTop = $window.scrollTop();
		
		if(documentHeight < windowHeight + scrollTop + 20) {
			console.log("fetch() call");
			scroll();
			
			return;
			
		}
	})
});  	

var scroll = function() {
		var no =$("#list-guestbook li:last-child").attr("data-no");
		
		console.log(no);
		//console.log($("#list-guestbook li data-no"));
		$.ajax({
	      url: "${pageContext.request.contextPath }/api/guestbook/startNo?no="+no,
	      type: "get",
	      dataType: "json",
	      success: function(response) {
	        if(response.result !== 'success'){
	            console.error(response.message);
	            return;
	         	}
			response.data.forEach(function(vo){
					render(vo, true);
				}) 
	     	 }      
	   });
	};
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="${pageContext.request.contextPath }/guestbook/spa" method="post">
					<input type="text" id="input-name" placeholder="이름">
					<input type="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook">	
				</ul>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display:none">
  				<p class="validateTips-normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips-error" style="display:none">비밀번호가 틀립니다.</p>
  				<form>
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
  				</form>
			</div>
			<div id="dialog-message" title="" style="display:none">
  				<p></p>
			</div>						
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>

		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>