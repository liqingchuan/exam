$(function(){
	//获取当前登录用户
	getNick();
	//根据用户获取菜单
	getMenu();
	//动态定义菜单单击事件
	menuClick();
	//退出登录
	exit();
})

//当前登录用户id
var userId = getCookie("userId");
//右侧小页面路径
var viewPath = "../Viewhtml/";

//每页显示的总条数
var pageSize = 5;
//当前页码
var pageNum = 1;
//总页数
var totalPage;
//总条数
var totalRecord;
//开始页
var start;
//结束页
var end;



/**
 * 退出登录
 * @returns
 */
function exit(){
	$("#exit").click(function(){
		delCookie("userId");
		window.location.href="/exam/login/toLogin.do";
	});
}


/*
 * 获取当前登录用户昵称
 * =======================================================================
 */
function getUser(userid) {
	var user;
	if(userid){
    	$.ajax({
    		url:"getUser.do",
    		type:"post",
    		data:{"userId":userid},
    		datatype:"json",
    		async:false,
    		success:function(result){
    			if(result.status=="0"){
    				user = result.data;
    			}else{
    				alert(result.msg);
    			}
    		},
    		error:function(){
    			alert("用户加载失败");
    		}
    	});
	}
	return user;
}
/**
 * 获取昵称并写入登录用户
 */
function getNick(){
	var user = getUser(userId);
	$("#dquser").html(user.nickname+" 你好");
}


/**
 * 
 * 获取角色，
 * 重载，传id时返回单个
 * 不传参数时返回所有
 * 
 */
//重载实现start-----------------------------------------------------------------------
//添加执行函数的方法
function addMethod(object, name, fn){
    var old = object[ name ];
    object[ name ] = function(){
        if ( fn.length == arguments.length )
            return fn.apply( this, arguments );
        else if ( typeof old == 'function' )
            return old.apply( this, arguments );
    };
}
//用法：Role.find(param);
//Role：对象，find：方法名，findOne：执行的方法
function Role(){
    addMethod(Role.prototype, "find", findOne);
    addMethod(Role.prototype, "find", findAll);
}
//重载实现end-----------------------------------------------------------------------


//根据id查role
function findOne(roleid){
	var role;
	if(roleid){
		$.ajax({
    		url:"/exam/role/findOne.do",
    		type:"post",
    		data:{"roleid":roleid},
    		datatype:"json",
    		async:false,
    		success:function(result){
    			if(result.status=="0"){
    				role = result.data;
    			}else{
    				alert(result.msg);
    			}
    		},
    		error:function(){
    			alert("角色获取失败");
    		}
    	});
	}
	return role;
}

//查所有的角色
function findAll(){
	var role;
	$.ajax({
		url:"/exam/role/findAll.do",
		type:"post",
		async:false,
		success:function(result){
			if(result.status=="0"){
				role = result.data;
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("角色获取失败");
		}
	});
	return role;
}



/*function getNick() {
	if(userId){
    	$.ajax({
    		url:"getUser.do",
    		type:"post",
    		data:{"userId":userId},
    		datatype:"json",
    		success:function(result){
    			if(result.status=="0"){
    				$("#dquser").html(result.data.nickname+" 你好");
    			}else{
    				alert(result.msg);
    			}
    		},
    		error:function(){
    			alert("菜单加载失败");
    		}
    	});
	}
}*/


/**
 * 获取当前用户可以使用那些菜单
 * 
 * =============================================================================
 */
function getMenu(){
	if(userId){
		$.ajax({
		url:"/exam/menu/loadMenu.do",
		type:"post",
		data:{"user_id":userId},
		datatype:"json",
		success:function(result){
			if(result.status=="0"){
				var menus = result.data;
				var $menulist = $("#liftmenu");
				for(var i=0;i<menus.length;i++){
					var li = "<li><a > "+menus[i].cd_name+"</a></li>";
					var $li = $(li);
					$li.data("cd_url",menus[i].cd_url)
					$menulist.append($li);
				}
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("菜单加载失败");
		}
	});
	}
}
/**
 * 定义每个菜单的页面跳转
 * 
 */
function menuClick(){
	$("#liftmenu").on("click","li",function(){
		var url = $(this).data("cd_url");
		var $rdiv = $(".divkuang");
		$rdiv.load(viewPath+url);
	})
}