$(function(){
		$("#go").click(checkLogin);
	})
	function checkLogin(){
		
		$("#span").hide();
		var username = $("#username").val();
		var password = $("#password").val();
		var flag = true;
		if(username==""){
			$("#span").html("请填写账号").show();
			flag=false;
		}else if(password==""){
			$("#span").html("请填写密码").show();
			flag=false;
		}
		if(flag){
			$.ajax({
				url:"login.do",
				type:"post",
				data:{"username":username,"password":password},
				datatype:"json",
				success:function(result){
					if(result.status=="0"){
						var userId = result.data.id;
						$.cookie('userId',userId, {
							path:'/',
							expires :new Date().getTime() + (60 * 60 * 1000)
							});
						window.location.href="index.do";
					}else{
						alert(result.msg);
					}
				},
				error:function(){
					alert("登陆失败");
				}
			});
		}
		
	}