var replyManager = (function(){
	var getAll = function(obj, callback){  //콜백함수란 다른 함수의 인자로써 넘겨진 후 특정 이벤트에 의해 호출되는 함수. 라는데...네??
		console.log("get All....");
		$.getJSON('/replies/'+obj,callback);
	};
	
	var add = function(obj, callback){
		console.log("add....");
		$.ajax({
			type:'post',
			url:'/replies/'+obj.bno,
			data:JSON.stringify(obj),
			dataType:'json',
			beforeSend : function(xhr){
									xhr.setRequestHeader(obj.csrf.headerName, obj.csrf.token);
			},
			contentType:"application/json",
			success:callback
		});
	};
	
	var update = function(obj, callback){
		console.log("update.......");
		$.ajax({
			type:'put',
			url:'/replies/'+obj.bno,
			dataType:'json',
			data:JSON.stringify(obj),
			beforeSend : function(xhr){
									xhr.setRequestHeader(obj.csrf.headerName, obj.csrf.token);
			},
			contentType:"application/json",
			success:callback
		});
	};
	
	var remove = function(obj, callback){
		console.log("remove......");
		
		$.ajax({
			type:'delete',
			url:'/replies/'+obj.bno+"/"+obj.rno,
			dataType:'json',
			beforeSend : function(xhr){
									xhr.setRequestHeader(obj.csrf.headerName, obj.csrf.token);
			},
			ccontentType: "application/json",
			success:callback
			
		});
	};
	
	return {
		getAll:getAll,
		add:add,
		update:update,
		remove:remove
	}
})();