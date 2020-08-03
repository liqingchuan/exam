$(function(){
	//获取用户列表
	getUserList();
	//用户查询
	userQuery();
	//用户删除、修改
	userClick();
	//分页按钮加载
	fenyeBtnClick();
	//添加用户
	addUser();
	
})
/**
 * 
 * 新增用户
 */
function addUser(){
	$("#addUser").click(function(){
		var role = new Role()
		var roles = role.find();
		var txt= '账号:	<input type="text" id="username" /><br>'+
		'密码:	<input type="text" id="password" /><br>'+
		'昵称:	<input type="text" id="nickname" /><br>'+
		'角色:	<select id="role">'+
					'<option value="0" >选择角色</option>';
					for(var i=0;i<roles.length;i++){
						txt+='<option value="'+roles[i].id+'">'+roles[i].role_name+'</option>';
					}
		txtEnd='</select> <br>'+
			'<input type="button" id="cancel" value="返回"/>   '+
			'<input type="button" id="save" value="保存"/>';
		txt += txtEnd;
		var $rdiv = $(".divkuang");
		$rdiv.empty();
		$rdiv.html(txt);
		//取消事件
		$("#cancel").click(function(){
			$rdiv.load("../Viewhtml/user.html");
		});
		//保存事件
		$("#save").click(function(){
			var username = $("#username").val();
			var password = $("#password").val();
			var nickname = $("#nickname").val();
			var role = $("#role").val();
			var rolename;
			if(role!="0"){
				rolename= new Role().find(role).role_name;
			}else{
				rolename="无";
			}
			var flag = false;
	    	$.ajax({
	    		url:"/exam/user/addUser.do",
	    		type:"post",
	    		data:{
	    			"username":username,
	    			"password":password,
	    			"nickname":nickname,
	    			"roleid":role,
	    			"rolename":rolename
	    			},
	    		datatype:"json",
	    		async:false,
	    		success:function(){
	    			if(status==0){
	    				flag = true;
	    			}else{
	    				alert(result.msg);
	    			}
	    		},
	    		error:function(){
	    			alert("保存失败");
	    		}
	    	});
			if(flag){
				$rdiv.load("../Viewhtml/user.html");
			}
		})
	});
}




/**
 * 查询列表,分页查询
 * @returns
 */
function getUserList(){
	var $tbody = $("#usertable");
	$tbody.empty();
	$.ajax({
		url:"/exam/user/findAllUser.do",
		type:"post",
		data:{"pageNum":pageNum,"pageSize":pageSize},
		datatype:"json",
		success:function(result){
			loadList(result);
		},
		error:function(){
			alert("查询出错")
		}
	})
}

/**
 * 用户查询
 * 模糊查询（nickname，username）
 * @returns
 */

function userQuery(){
	$("#sousuo").click(function(){
		var $ssinput = $("#ssinput").val();
		var $tbody = $("#usertable");
		var $fenye = $("#fenye");
		$fenye.empty();
		$fenye.html("<input type='button' id='fahui' value='返回'>");
		$tbody.empty();
		$.ajax({
			url:"/exam/user/findOneUser.do",
			type:"post",
			data:{"userparam":$ssinput},
			datatype:"json",
			success:function(result){
				if(result.status==0){
					var data = result.data;
					var status;
					for(var i=0;i<data.length;i++){
						status = data[i].status==0 ? '正常':'停用';
						var tr = "<tr>"+
									"<td>"+(i+1)+"</td>"+
									"<td>"+data[i].username+"</td>"+
									"<td>"+data[i].nickname+"</td>"+
									"<td>"+data[i].role_name+"</td>"+
									"<td>"+status+"</td>"+
									"<td style='width: 60px;'>"+
										"<input type='button' value='删'/>"+
										"<input type='button' value='修'/>"+
									"</td>"+
								 "</tr>";
						var $tr = $(tr);
						$tr.data("userid",data[i].id);
						$tbody.append($tr);
					}
				}else{
					alert(result.msg);
				}
			},
			error:function(){
				alert("查询出错")
			}
		})
	})
	
	$("#fanhui").click(function(){
		getUserList();
	});
	
}


function fenyeBtnLoad(){
	var $fenye = $("#fenye");
	$fenye.empty();
	var input = "<input type='button' value='<<'/> "+
				"<input type='button' value='<'/> ";
	for(var i=start;i<=end;i++){
		input+="<input id= '"+i+"' type='button' value="+i+" /> ";
	}
	input+="<input type='button' value='>'/> "+
			"<input type='button' value='>>'/> "+
			"<span>总共:"+totalPage+"页</span>";
	$fenye.append(input);
	$("#1").addClass("btncheck");
	var btns = $("#"+pageNum).siblings();
	for(var i=0;i<btns.length;i++){
		$(btns[i]).removeClass("btncheck");
	}
	$("#"+pageNum).addClass("btncheck");
}



//分页按钮的单击事件
function fenyeBtnClick(){
	fenyeBtnLoad();
	$("#fenye").on("click","input",function(){
		var btn = $(this).val();
		if(!isNaN(btn)){
			pageNum = btn;
		}
		if(btn=="<<"){
			pageNum = 1;
		}
		if(btn=="<"&&pageNum>1){
			--pageNum;
		}
		if(btn==">"&&pageNum<end){
			++pageNum;
		}
		if(btn==">>"){
			pageNum=totalPage;
		}
		getUserList();
	});
}


/**
 * 用户的操作按钮事件
 * @returns
 */

function userClick(){
	$("#usertable").on("click","input",function(){
		var userid = $($(this).parent().parent()).data("userid")
		var user = getUser(userid);
		//点击的是删除
		if($(this).val()=='删'){
			if(confirm("你确定要删除"+user.nickname)){
				deleteUser(userid);
			}
		}
		//点击的是修改
		if($(this).val()=='修'){
			var role = new Role()
			var roles = role.find();
			var checkRole = role.find(user.role_id);
			if(checkRole){
				var checkid = checkRole.id;
			}
			//写画面，也可以用load；ifream不会用
			var txt= '账号:	<input type="text" id="checkuser" value="'+user.username+'"/><br>'+
			'用户名:	<input type="text" id="checkname" value="'+user.nickname+'"/><br>'+
			'角色:	<select id="checkrole">'+
						'<option value="0" >选择角色</option>';
						for(var i=0;i<roles.length;i++){
							if(roles[i].id==checkid){
								txt+='<option value="'+roles[i].id+'" selected="selected">'+checkRole.role_name+'</option>';
							}else{
								txt+='<option value="'+roles[i].id+'">'+roles[i].role_name+'</option>';
							}
						}
			txtEnd='</select> <br>'+
				'<input type="button" id="cancel" value="返回"/>   '+
				'<input type="button" id="save" value="保存"/>';
			txt += txtEnd;
			saveUser(txt,userid);
		}
	})
}

/**
 * 
 * 用户修改模块
 * 
 */
function saveUser(txt,userid){
	var $rdiv = $(".divkuang");
	$rdiv.empty();
	$rdiv.html(txt);
	//取消事件
	$("#cancel").click(function(){
		$rdiv.load("../Viewhtml/user.html");
	});
	//保存事件
	$("#save").click(function(){
		var checkuser = $("#checkuser").val();
		var checkname = $("#checkname").val();
		var checkrole = $("#checkrole").val();
		var checkrolename;
		if(checkrole!="0"){
			checkrolename= new Role().find(checkrole).role_name;
		}else{
			checkrolename="无";
		}
		var flag = false;
		if(userid){
	    	$.ajax({
	    		url:"/exam/user/updateUser.do",
	    		type:"post",
	    		data:{
	    			"userid":userid,
	    			"username":checkuser,
	    			"usernick":checkname,
	    			"roleid":checkrole,
	    			"rolename":checkrolename
	    			},
	    		datatype:"json",
	    		async:false,
	    		success:function(result){
	    			flag = true;
	    		},
	    		error:function(){
	    			alert("保存失败");
	    		}
	    	});
		}
		if(flag){
			$rdiv.load("../Viewhtml/user.html");
		}
	})
}





/**
 * 
 * 删除user
 * @param userid
 * @returns
 */
function deleteUser(userid){
	if(userid){
		$.ajax({
			url:"/exam/user/deleteUser.do",
			type:"post",
			data:{"user_id":userid},
			datatype:"json",
			success:function(){
				getUserList()
			},
			error:function(){
				alert("查询出错")
			}
		})
	}
}

/**
 * 查询成功后加载用户列表（分页）
 * @param result
 * @returns
 */
function loadList(result){
	var $tbody = $("#usertable");
	if(result.status==0){
		var data = result.data;
		var $users = data.list;
		totalPage = data.totalPage;
		totalRecord = data.totalRecord;
		start = data.start;
		end = data.end;
		var status;
		for(var i=0;i<$users.length;i++){
			status = $users[i].status==0 ? '正常':'停用';
			var tr = "<tr>"+
						"<td>"+(i+1)+"</td>"+
						"<td>"+$users[i].username+"</td>"+
						"<td>"+$users[i].nickname+"</td>"+
						"<td>"+$users[i].role_name+"</td>"+
						"<td>"+status+"</td>"+
						"<td style='width: 60px;'>"+
							"<input type='button' value='删'/>"+
							"<input type='button' value='修'/>"+
						"</td>"+
					 "</tr>";
			var $tr = $(tr);
			$tr.data("userid",data.list[i].id);
			$tbody.append($tr);
		}
		fenyeBtnLoad();
	}else{
		alert(result.msg);
	}
}



