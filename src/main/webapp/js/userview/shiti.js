$(function(){
	//分页查询试题
	getShitiPage();
	//分页按钮单击事件
	fenyeBtnClick();
	//试题操作按钮
	shitiClick();
	//试题模糊查询
	shitiQuery();
	//新建试题
	addShiti();
	//查看全部试题
	QueryAll();
})




function QueryAll(){
	
	pageNum = 1;
	
	$("#selectAll").click(selectAll);
	
	function selectAll(){
		var $rdiv = $(".divkuang");
		$rdiv.empty();
		var data;
		$.ajax({
			url:"/exam/shiti/findpage.do",
			type:"post",
			async:false,
			data:{"pageNum":pageNum,"pageSize":pageSize},
			datatype:"json",
			success:function(result){
				data = result.data;
			},
			error:function(){
				alert("获取试题列表失败");
			}
		})
		var list = data.list;
		totalPage = data.totalPage;
		totalRecord = data.totalRecord;
		start = data.start;
		end = data.end;
		
		var $div = loadShitiDan(list);

		$rdiv.html($div);
		
		var $fenye = $("#fypage");

		fenyeBtnLoad($fenye);
		
		$fenye.on("click","input",function(){
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
			selectAll();
		});
	}
	
}



/**
 * 
 * 加载试题和答案列表
 * 
 * @param list
 * @returns
 */
function loadShitiDan(list){
	var stlb = -1;
	
	var $div = $('<div style="margin-left: 30px;" ></div>');
	
	for(var i = 0; i<list.length;i++){
		if(stlb != list[i].st_lb){
			stlb = list[i].st_lb;
			if(stlb==0){
				$div.append('<h4>单选题</h4>');
			}
			if(stlb==1){
				$div.append('<h4>多选题</h4>');
			}
			if(stlb==2){
				$div.append('<h4>判断题</h4>');
			}
			if(stlb==3){
				$div.append('<h4>解答题</h4>');
			}
		}
		$div.append('<p>第'+(i+1)+'题： '+list[i].tm_name+'</p>');
		var dan = getDaan(list[i].id);
		if(dan.length>0){
			if(stlb==0){
				var $ol = $('<ol style="list-style-type:upper-alpha"></ol>');
				for(var j = 0;j<dan.length;j++){
					//code来确定正确答案是默认选中的
					var code = dan[j].code=="0" ? "checked='checked'":"";
					var $li =$('<li>'+
									'<input type="radio" name='+list[i].id+' '+code+' disabled />'+
									'<input type="text" value='+dan[j].da_name+' readonly="true"/>'+
								'</li>');
					$ol.append($li);
				}
				$div.append($ol);
			}
			if(stlb==1){
				var $ol = $('<ol style="list-style-type:upper-alpha"></ol>');
				for(var j = 0;j<dan.length;j++){
					//code来确定正确答案是默认选中的
					var code = dan[j].code=="0" ? "checked='checked'":"";
					var $li =$('<li>'+
									'<input type="checkbox" '+code+' disabled />'+
									'<input type="text" value='+dan[j].da_name+' readonly="true"/>'+
								'</li>');
					$ol.append($li);
				}
				$div.append($ol);
			}
			if(stlb==2){
				var $ol = $('<ol></ol>');
				for(var j = 0;j<dan.length;j++){
					//code来确定正确答案是默认选中的
					var code = dan[j].code=="0" ? "checked='checked'":"";
					var $li =$('<li>'+
									'<input type="radio" name='+list[i].id+' '+code+' disabled />'+
									'<input type="text" value='+dan[j].da_name+' readonly="true"/>'+
								'</li>');
					$ol.append($li);
				}
				$div.append($ol);
			}
			if(stlb==3){
				$div.append(' 解答：<textarea name="daan"  readonly="true" >'+dan[0].da_name+'</textarea><br>');
			}
		}else{
			$div.append('<h6>此题未查到答案</h6>');
		}
	}
	$div.append("<span id ='fypage' ></span>");
	return $div;
}





/**
 * 
 * 新建试题
 * 
 * 答案添加待写
 * 
 * 先实现新增试题
 * 
 */
function addShiti(){
	$("#addShiti").click(function(){
		var $rdiv = $(".divkuang");
		$rdiv.empty();
		var $div = $('<div></div>');
		//题目
		var $tm = 	$('<p>'+
							' 题目: <textarea id="timu" rows="3" style="width: 750px;" ></textarea><br>'+
					'</p>');
		var kemu = getKemu();
		var km =   '科目: <select id="kemu" >';
		for(i in kemu){
			km += '<option value="'+kemu[i].id+'" >'+kemu[i].km_name+'</option>';
		}
		km += '</select><br>';
		
		var st_lb ='类别: <select id="st_lb" >'+
					  '<option value ="0">单选</option>'+
					  '<option value ="1">多选</option>'+
					  '<option value="2">判断</option>'+
					  '<option value="3">解答</option>'+
					'</select> <br>';
		
		var btn = 	'<input type="button" id="cancel" value="返回"/>'+
					'<input type="button" id="save" value="保存"/>';
		
		$div.append($tm);
		$div.append(st_lb);
		$div.append(km);
		$div.append(btn);
		$rdiv.html($div);
		
		//取消事件
		$("#cancel").click(function(){
			$rdiv.load("../Viewhtml/shiti.html");
		});
		//保存事件
		$("#save").click(function(){
			var timu = $("#timu").val();
			if(timu){
				var st_lb = $("#st_lb").val();
				var kemu = $("#kemu").val();
				$.ajax({
					url:"/exam/shiti/addShiti.do",
					type:"post",
					data:{
						"timu":timu,
						"kemuId":kemu,
						"st_lb":st_lb
					},
					datatype:"json",
					success:function(){
						$rdiv.load("../Viewhtml/shiti.html");
					},
					erorr:function(){
						alert("保存失败");
					}
				});
			}else{
				alert("请填写题目");
			}
			
		})
		
	});
	
}

function getKemu(){
	var kemu;
	$.ajax({
		url:"/exam/kemu/getKemuAll.do",
		type:"post",
		async:false,
		success:function(result){
			kemu = result.data;
		},
		error:function(){
			alert("科目获取失败");
		}
	})
	return kemu;
}




/**
 * 
 * 分页查询
 * 
 * @returns
 */
function getShitiPage(){
	//获取表body对象
	var $tbody = $("#sttable");
	//先清空
	$tbody.empty();
	//发送请求查询全部题目遍历写入body
	$.ajax({
		url:"/exam/shiti/findpage.do",
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
 * 
 * 加载分页按钮
 * 
 */
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
		getShitiPage();
	});
}

/**
 * 查询成功后加载用户列表（分页）
 * @param result
 * @returns
 */
function loadList(result){
	var $tbody = $("#sttable");
	if(result.status==0){
		var data = result.data;
		var list = data.list;
		totalPage = data.totalPage;
		totalRecord = data.totalRecord;
		start = data.start;
		end = data.end;
		for(var i=0;i<list.length;i++){
			
			var code;
			if(list[i].st_lb=='0'){
				code='单选';
			}
			if(list[i].st_lb=='1'){
				code='多选';
			}
			if(list[i].st_lb=='2'){
				code='判断';
			}
			if(list[i].st_lb=='3'){
				code='解答';
			}
			
			var line =	'<tr>'+
							'<td>'+(i+1)+'</td>'+
							'<td>'+list[i].km_name+'</td>'+
							'<td>'+code+'</td>'+
							'<td>'+list[i].tm_name+'</td>'+
							'<td>'+
								'<input type="button" value="查"/>'+
								'<input type="button" value="删"/>'+
							'</td>'+
						'</tr>';
			var $line = $(line);
			$line.data("ShitiId",list[i].id);
			$tbody.append($line);
		}
		//加载分页按钮
		var $fenye = $("#fenye");
		fenyeBtnLoad($fenye);
	}else{
		alert(result.msg);
	}
}



/**
 * 试题的操作按钮事件
 * @returns
 */
function shitiClick(){
	$("#sttable").on("click","input",function(){
		var shitiId = $($(this).parent().parent()).data("ShitiId")
		var shiti = getShiti(shitiId);
		//点击的是删除
		if($(this).val()=='删'){
			if(confirm("你确定要删除"+shiti.tm_name+"？这将删除本题所属答案")){
				deleteShiti(shitiId);
			}
		}
		//点击的是查看
		if($(this).val()=='查'){
			viewClick(shiti);
		}
	})
}

//查看按钮todo
function viewClick(shiti){
	var daList = getDaan(shiti.id);
	var $div = $('<div></div>');
	//试题类别，0---单选，1--多选，2--判断，3--解答
	var stlb = shiti.st_lb;
	if(stlb=='0'){
		//单选------------------------------------------------------------------------单选start
		var ol = '<ol style="list-style-type:upper-alpha"></ol>';
		var $ol = $(ol);
		//题目
		var $tm = 	$('<h4>'+
							'(单选) 题目:<input type="text" id="timu" value='+shiti.tm_name+'>'+
					'</h4>');
		for(i in daList){
			//code来确定正确答案是默认选中的
			var code = daList[i].code=="0" ? "checked='checked'":"";
			$tm.data('shitiid',shiti.id);
			var li ='<li>'+
						'<input type="radio" name='+shiti.id+' '+code+' />'+
						'<input type="text" value='+daList[i].da_name+' readonly="true"/>'+
						'<button style="margin-bottom: 10px;">改</button>'+
						'<button style="margin-bottom: 10px;">删</button>'+
					'</li>';
			var $li = $(li);
			$li.data("danid",daList[i].id);
			$ol.append($li);
		}
		var btn = 	'<input type="button" id="cancel" value="返回"/>'+
					'<input type="button" id="save" value="保存"/>'+
					'<input type="button" id="add" value="添加答案"/>';

		$div.append($tm);
		$div.append($ol);
		$div.append(btn);
		//查看按钮内部操作
		saveView($div,shiti);
		//单选end------------------------------------------------------------------------单选end
	}else if(stlb=='1'){
		//多选---------------------------------------------------------------------------多选start
		var ol = '<ol style="list-style-type:upper-alpha"></ol>';
		var $ol = $(ol);
		//题目
		var $tm = 	$('<h4>'+
							'(多选) 题目:<input type="text" id="timu" value='+shiti.tm_name+'>'+
					'</h4>');
		for(i in daList){
			//code来确定正确答案是默认选中的
			var code = daList[i].code=="0" ? "checked='checked'":"";
			$tm.data('shitiid',shiti.id);
			var li ='<li>'+
						'<input type="checkbox" '+code+' />'+
						'<input type="text" value='+daList[i].da_name+' readonly="true"/>'+
						'<button style="margin-bottom: 10px;">改</button>'+
						'<button style="margin-bottom: 10px;">删</button>'+
					'</li>';
			var $li = $(li);
			$li.data("danid",daList[i].id);
			$ol.append($li);
		}
		var btn = 	'<input type="button" id="cancel" value="返回"/>'+
					'<input type="button" id="save" value="保存"/>'+
					'<input type="button" id="add" value="添加答案"/>';

		$div.append($tm);
		$div.append($ol);
		$div.append(btn);
		//查看按钮内部操作
		saveView($div,shiti);
		//多选---------------------------------------------------------------------------多选end
	}else if(stlb=='2'){
		//判断---------------------------------------------------------------------------判断start
		var $ol = $('<ol></ol>');
		//题目
		var $tm = 	$('<h4>'+
							'(判断) 题目:<input type="text" id="timu" value='+shiti.tm_name+'>'+
					'</h4>');
		for(i in daList){
			//code来确定正确答案是默认选中的
			var code = daList[i].code=="0" ? "checked='checked'":"";
			$tm.data('shitiid',shiti.id);
			var li ='<li>'+
						'<input type="radio" name='+shiti.id+' '+code+' />'+
						'<input type="text" value='+daList[i].da_name+' readonly="true"/>';
					'</li>';
			var $li = $(li);
			$li.data("danid",daList[i].id);
			$ol.append($li);
		}
		var btn = 	'<input type="button" id="cancel" value="返回"/>'+
					'<input type="button" id="save" value="保存"/>';
		$div.append($tm);
		$div.append($ol);
		$div.append(btn);
		//查看按钮内部操作
		saveView($div,shiti);
		//判断---------------------------------------------------------------------------判断end
	}else if(stlb=='3'){
		//解答---------------------------------------------------------------------------解答start
		//题目
		var $tm = 	$('<h4>'+
							'(解答) 题目:<input type="text" id="timu" value='+shiti.tm_name+'>'+
					'</h4>');
		
		var dan_name = daList[0] ? daList[0].da_name:"";
		
		var dan =   ' 解答：<textarea name="daan" >'+dan_name+'</textarea><br>';
		
		var btn = 	'<input type="button" id="cancel" value="返回"/>'+
					'<input type="button" id="save" value="保存"/>';

		$div.append($tm);
		
		$div.append(dan);
		
		$div.append(btn);
		//查看按钮内部操作
		var $rdiv = $(".divkuang");
		$rdiv.empty();
		$rdiv.html($div);
		//取消事件
		$("#cancel").click(function(){
			$rdiv.load("../Viewhtml/shiti.html");
		});
		//保存事件
		$("#save").click(function(){
			var flag = true;
			var timu = $("#timu").val();
			var dan = $("textarea[name='daan']").val();
			if(!timu || !dan){
				flag = false;
			}
			if(flag){
				$.ajax({
					url:"/exam/shiti/updateJiedaTi.do",
		    		type:"post",
		    		data:{
		    			"shitiid":shiti.id,
		    			"shiti":timu,
		    			"daan":dan
		    		},
		    		datatype:"json",
		    		success:function(result){
		    			$rdiv.load("../Viewhtml/shiti.html");
		    		},
		    		error:function(){
		    			alert("修改失败");
		    		}
				});
			}else{
				alert("有未填写，请补充");
			}
			
		});
		//解答---------------------------------------------------------------------------解答end
	}
}




//查看按钮内部操作
function saveView($div,shiti){
	var $rdiv = $(".divkuang");
	$rdiv.empty();
	$rdiv.html($div);
	var $ol = $div.find("ol");
	var $li = $ol.find("li");
	$li.on("click","button",function(){
		var btn = $(this);
		if(btn.html()=="删"){
			btn.parent().remove();
			saveView($div,shiti);
		}else if(btn.html()=="改"){
			btn.prev().removeAttr('readonly');
		}
	});
	//添加答案
	$("#add").click(function(){
		var btnType;
		if(shiti.st_lb=="0"){
			btnType="radio";
		}
		if(shiti.st_lb=="1"){
			btnType="checkbox";
		}
		var li ='<li>'+
					'<input type='+btnType+' name='+shiti.id+' />'+
					'<input type="text"/>'+
					'<button style="margin-bottom: 10px;">改</button>'+
					'<button style="margin-bottom: 10px;">删</button>'+
				'</li>';
		$ol.append(li);
		//回调函数，使新增答案的按钮可以使用
		saveView($div,shiti);
	})
	//取消事件
	$("#cancel").click(function(){
		$rdiv.load("../Viewhtml/shiti.html");
	});
	//保存事件
	$("#save").click(function(){
		//是否有未填写答案
		var flag = true;
		//答案list
		var list = new Array();
		
		var code;
		
		if(shiti.st_lb=="0" || shiti.st_lb=="2"){
			var zqdan = $('input:radio[name='+shiti.id+']:checked').next().val();
			for(var i=0;i<$li.length;i++){
				var daan = $($($li[i]).find('input')[1]).val();
				if(!daan){
					flag=false;
				}
				if(daan == zqdan){
					code = 0;
				}else{
					code = 1;
				}
				var dan = {"da_name":daan,"code":code};
				list.push(dan);
			}
		}
		
		if(shiti.st_lb=="1"){
			for(var i=0;i<$li.length;i++){
				var daan = $($($li[i]).find('input')[1]).val();
				if(!daan){
					flag=false;
				}
				
				var check = $($($li[i]).find('input')[0]).is(':checked');
				
				if(check){
					code = 0;
				}else{
					code = 1;
				}
				
				var dan = {"da_name":daan,"code":code};
				list.push(dan);
			}
		}
		
		if(!$("#timu").val()){
			flag=false;
		}
		//是否修改成功
		var status = false;
		//判断是不是有未填写
		if(flag){
			$.ajax({
	    		url:"/exam/shiti/updateShitiDan.do",
	    		type:"post",
	    		data:{
	    			"shitiid":shiti.id,
	    			"shiti":$("#timu").val(),
	    			"daan":JSON.stringify(list)
	    		},
	    		datatype:"json",
	    		async:false,
	    		success:function(result){
	    				status = true;
	    		},
	    		error:function(){
	    			alert("修改失败");
	    		}
			});
			//如果修改成功则返回试题列表;
			if(status){
				$rdiv.load("../Viewhtml/shiti.html");
			}
			
		}else{
			alert("有未填写，请补充");
		}
		
	})
}






/**
 * 根据试题id查询答案
 * @param shitiId
 * @returns
 */
function getDaan(shitiId){
	var daan;
	if(shitiId){
    	$.ajax({
    		url:"/exam/shiti/queryDaan.do",
    		type:"post",
    		data:{"shitiid":shitiId},
    		datatype:"json",
    		async:false,
    		success:function(result){
    			if(result.status=="0"){
    				daan = result.data;
    			}else{
    				alert(result.msg);
    			}
    		},
    		error:function(){
    			alert("答案查询失败");
    		}
    	});
	}
	return daan;
}



/**
 * 根据试题id获取试题
 * @param shitiId
 * @returns
 */
function getShiti(shitiId) {
	var shiti;
	if(shitiId){
    	$.ajax({
    		url:"/exam/shiti/findById.do",
    		type:"post",
    		data:{"shitiid":shitiId},
    		datatype:"json",
    		async:false,
    		success:function(result){
    			if(result.status=="0"){
    				shiti = result.data;
    			}else{
    				alert(result.msg);
    			}
    		},
    		error:function(){
    			alert("试题查询失败");
    		}
    	});
	}
	return shiti;
}



/**
 * 删除时一并删除所属答案
 * @param shitiId
 * @returns
 */
function deleteShiti(shitiId){
	if(shitiId){
		$.ajax({
			url:"/exam/shiti/deleteShiti.do",
			type:"post",
			data:{"shitiId":shitiId},
			datatype:"json",
			success:function(result){
				if(result.status==0){
					getShitiPage();
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
 * 试题查询
 * 模糊查询
 * @returns
 */

function shitiQuery(){
	$("#sousuo").click(function(){
		var $ssinput = $("#ssinput").val();
		var $tbody = $("#sttable");
		var $fenye = $("#fenye");
		$fenye.empty();
		$fenye.html("<input type='button' id='fahui' value='返回'>");
		$tbody.empty();
		$.ajax({
			url:"/exam/shiti/queryShiti.do",
			type:"post",
			data:{"shitiParam":$ssinput},
			datatype:"json",
			success:function(result){
				if(result.status==0){
					var list = result.data;
					for(var i=0;i<list.length;i++){
						var code;
						if(list[i].st_lb=='0'){
							code='单选';
						}
						if(list[i].st_lb=='1'){
							code='多选';
						}
						if(list[i].st_lb=='2'){
							code='判断';
						}
						if(list[i].st_lb=='3'){
							code='解答';
						}
						var line =	'<tr>'+
										'<td>'+(i+1)+'</td>'+
										'<td>'+list[i].km_name+'</td>'+
										'<td>'+code+'</td>'+
										'<td>'+list[i].tm_name+'</td>'+
										'<td>'+
											'<input type="button" value="查"/>'+
											'<input type="button" value="删"/>'+
										'</td>'+
									'</tr>';
						var $line = $(line);
						$line.data("ShitiId",list[i].id);
						$tbody.append($line);
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
		getShitiPage();
	});
	
}






































