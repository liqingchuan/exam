$(function(){
	//分页查询试题
	getShijuanPage();
	
	fenyeBtnClick();
	
	shitiClick();
})



/**
 * 
 * 分页查询
 * 
 * @returns
 */
function getShijuanPage(){
	//获取表body对象
	var $tbody = $("#tbody");
	//先清空
	$tbody.empty();
	//发送请求查询全部题目遍历写入body
	$.ajax({
		url:"/exam/shijuan/findPage.do",
		type:"post",
		data:{"pageNum":pageNum,"pageSize":pageSize},
		datatype:"json",
		success:function(result){
			loadList(result);
		},
		error:function(){
			alert("获取试题列表失败");
		}
	});
}


/**
 * 查询成功后加载列表（分页）
 * @param result
 * @returns
 */
function loadList(result){
	var $tbody = $("#tbody");
	if(result.status==0){
		var data = result.data;
		var list = data.list;
		totalPage = data.totalPage;
		totalRecord = data.totalRecord;
		start = data.start;
		end = data.end;
		var sjlist = new Array();
		for(var i=0;i<list.length;i++){
			var code = '';
			var time = new Date();
			if(time<list[i].start_time){
				code = '未开始';
			}
			if(time>list[i].start_time && time<list[i].end_time){
				code = '正在考试';
			}
			if(time>list[i].end_time){
				code = '考试结束';
			}
			var sj = {
				id:list[i].id,
				name:list[i].sj_name,
				km:list[i].km_name,
				start:new Date(list[i].start_time).format("yyyy-MM-dd hh:mm:ss"),
				end:new Date(list[i].end_time).format("yyyy-MM-dd hh:mm:ss"),
				code:code
			};
			sjlist.push(sj);
		}
		for(var i=0;i<sjlist.length;i++){
			var line =	'<tr>'+
							'<td>'+(i+1)+'</td>'+
							'<td>'+sjlist[i].name+'</td>'+
							'<td>'+sjlist[i].km+'</td>'+
							'<td>'+sjlist[i].start+'</td>'+
							'<td>'+sjlist[i].end+'</td>'+
							'<td>'+sjlist[i].code+'</td>'+
							'<td>'+
								'<input type="button" value="查"/>'+
								'<input type="button" value="删"/>'+
							'</td>'+
						'</tr>';
			var $line = $(line);
			$line.data("ShijuanId",sjlist[i].id);
			$tbody.append($line);
		}
		//加载分页按钮
		var $fenye = $("#fenye");
		fenyeBtnLoad($fenye);
	}else{
		alert(result.msg);
	}
}


function fenyeBtnLoad($fenye){
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
	var $fenye = $("#fenye");
	fenyeBtnLoad($fenye);
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
		getShijuanPage();
	});
}


/**
 * 试题的操作按钮事件
 * @returns
 */
function shitiClick(){
	$("#tbody").on("click","input",function(){
		var shijuanId = $($(this).parent().parent()).data("ShijuanId")
		var shijuan = getShijuan(shijuanId);
		//点击的是删除
		if($(this).val()=='删'){
			if(confirm("你确定要删除试卷"+shijuan.sj_name+"？")){
				deleteShijuan(shijuanId);
			}
		}
		//点击的是查看
		if($(this).val()=='查'){
			window.open('/exam/shijuan/sj_view.do?shijuanId='+shijuanId);
		}
	})
}

function getShitiBySjId(shijuanId){
	var stlist;
	$.ajax({
		url:"/exam/shijuan/getShitiBySjId.do",
		type:"post",
		data:{"shijuanId":shijuanId},
		datatype:"json",
		async:false,
		success:function(result){
			stlist = result.data;
		},
		error:function(){
			alert("获取试题失败");
		}
	})
	return stlist;
}


/**
 * 根据试题id获取试题
 * @param shitiId
 * @returns
 */
function getShijuan(shijuanId) {
	var shijuan;
	if(shijuanId){
    	$.ajax({
    		url:"/exam/shijuan/findByid.do",
    		type:"post",
    		data:{"shijuanId":shijuanId},
    		datatype:"json",
    		async:false,
    		success:function(result){
    			if(result.status=="0"){
    				shijuan = result.data;
    			}else{
    				alert(result.msg);
    			}
    		},
    		error:function(){
    			alert("试卷查询失败");
    		}
    	});
	}
	return shijuan;
}



/**
 * 删除时一并删除所属答案
 * @param shitiId
 * @returns
 */
function deleteShijuan(shijuanId){
	if(shijuanId){
		$.ajax({
			url:"/exam/shijuan/deleteShijuan.do",
			type:"post",
			data:{"shijuanId":shijuanId},
			datatype:"json",
			success:function(result){
				if(result.status==0){
					getShijuanPage();
				}else{
					alert(result.msg);
				}
			},
			error:function(){
				alert("删除出错")
			}
		})
	}
}

















/**
 *对Date的扩展，将 Date 转化为指定格式的String
 *月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
 *年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
 *例子：
 *(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
 *(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
 */
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}







