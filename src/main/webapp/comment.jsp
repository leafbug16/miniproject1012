<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<style>
	h5 {
		margin-top: 30px;
	}
	li {
		list-style: none;
		overflow: hidden;
	}
	.delBtn {
		color: lightgray;
	}
	.modBtnb {
		margin-left: 50px;
	}
	.spacing {
		height: 20px;
	}
	#sendBtn {
		margin-top: 3px;
	}
	#modBtn {
		margin-top: 3px;
	}
</style>
</head>
<body>
	<h5>댓글</h5>
	<div>
		<input class="form-control form-control-sm" type="text" name="comment" id="comment" style="width: 800px; height:100px;">
		<button class="btn btn-secondary btn-sm" type="button" id="sendBtn">등록</button>
	</div>
	<div class="spacing"></div>
	<div class="mod"></div>
	<div id="commentList"></div>
	
	<script>
		let bno = ${param.num };
		let mode = false;
		let showList = function(bno){
			$('input[name=comment]').val("");
			$.ajax({
				type : "GET",
				url : "./comments?bno=" + bno,
				success : function(result) {
					$("#commentList").html(toHtml(result));
					console.log(result);
				},
				error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"조회중에러") }
			});
		}
		
		let toHtml = function(comments) {
			let tmp = "<ul>";
			comments.forEach(function (comment) {
				tmp += "<li data-cno="+comment.cno + " data-bno="+comment.bno + ">";
				tmp +='<span class="commenter"><b> ['+comment.commenter+']</b></span>';
				tmp +='   : <span class="comment"> '+comment.comment+'</span>';
				if (comment.commenter == "${sessionScope.id }") {
					tmp += "<button type='button' class='btn btn-link modBtnb'>수정</button>";
					tmp += "<button type='button' class='btn btn-link delBtn'>삭제</button>";
				}
				tmp += "</li>";
				tmp += "<hr>";
			})
			return tmp + "</ul>";
		}
		
		$(document).ready(function() {
			showList(bno);
			//등록 버튼
			$("#sendBtn").click(function() {
				let comment = $("input[name=comment]").val();
				if (comment.trim() == "") {
					alert("입력하세요");
					return;
				}
				$.ajax({
					type : "POST",
					url : "./comments",
					data : { bno: bno, comment: comment},
					success : function(result) {
						showList(bno);
					},
					error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error) }
				}); //ajax
			}); //sendBtn
			
			//댓글 옆 수정버튼 클릭 시
			$("#commentList").on("click", ".modBtnb", function() {
			    let cno = $(this).parent().attr("data-cno");
			    let bno = $(this).parent().attr("data-bno");                
			    let originalComment = $("span.comment", $(this).parent()).text();
			    
			    // 대체
			    $("span.comment", $(this).parent()).replaceWith("<input class='form-control form-control-sm' type='text' name='recomment' id='recomment" + cno + "' style='width: 400px; height:50px;' value='"+originalComment+"'>");
			    $(this).replaceWith("<button class='btn btn-secondary btn-sm' type='button' id='modBtn'>수정 완료</button>");
			    
			    $("input[name=recomment]", $(this).parent()).val(originalComment);
			    $("#modBtn").attr("data-cno", cno);
			}); 
			
			//수정완료 클릭 시
			$("#commentList").on("click", "#modBtn", function() {
				let comment = $("input[name=recomment]").val();
				if (comment.trim() == "") {
					alert("내용을 입력하세요");
					return;
				}
				let cno = $("#modBtn").attr("data-cno");

				$.ajax({
					type : "POST",
					url : "./comments",
					data : { cno: cno, comment: comment, mode: "mody" },
					success : function(result){
						showList(bno);
			        },
			        error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error) }
			     }); //ajax

			     // 복구
			     $("input[name=recomment]", $(this).parent()).replaceWith('<span class="comment"> '+ comment +'</span>');
			     $(this).replaceWith("<button type='button' class='btn btn-link modBtnb'>수정</button>");
			});
			
			//삭제 버튼
			$("#commentList").on("click", ".delBtn", (function() {
				let cno = $(this).parent().attr("data-cno");
				let bno = $(this).parent().attr("data-bno");
				$.ajax({
					type : "GET",
					url : "./comments",
					data : { cno: cno, bno: bno, mode: "del" },
					success : function(result) {
						showList(bno);
					},
					error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error) }
				}); //ajax
			})); //delBtn	
		}); //ready
	</script>
</body>
</html>











