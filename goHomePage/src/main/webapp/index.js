$(function(){
	var dataTemplate = {};
	dataTemplate = $('.dataTemplate').clone(true);
	$('.dataTemplate').remove();
	
	var loadData = function() {
		
		$.ajax({
			type : "GET",
			url : "http://localhost:8081/goHomePage/hello2/findAll",
			success : function(response){
				for(key in response){
					var append = dataTemplate.clone(true);
					var valueObj = response[key];
					appendData(valueObj);
				}
			}
		}).done(function(){
			deleteEventRegister();
			selectOneEventRegister();
		});
	}
	
	var selectOneEventRegister = function () {
		$(".selectOne").on('click', function(){
			var id = $(this).attr("id");
			$.ajax({
				type : "GET",
				url : "http://localhost:8081/goHomePage/hello2/findOne",
				data: {
	                id: id,
	            },
				success : function(response){
					$("#insert").show();
					$("#formId").val(id);
					$("#formId").prop('disabled', true);
					$("#formName").val(response.name);
					$("#formPrize").val(response.prize);
					$("#formDateTime").val(response.createDate);
					$("#insertOrUpdate").text("修改 : " + response.name);
				}
			});
		});
	}
	var deleteEventRegister = function () {
		// 刪除
		$(".deleteOne").on('click', function(){
			var id = $(this).attr("id");
			var tr = $(this).parent().parent();
			$.ajax({
				type : "GET",
				url : "http://localhost:8081/goHomePage/hello2/delete",
				data: {
	                id: id
	            },
				success : function(response){
					tr.remove();
				}
			});
		});
	}
	
	$("#insertButton").on('click', function(){
		$("#insert").show();
		$("#insertOrUpdate").text("新增");
		$("#formId").prop('disabled', false);
		$("#formId").val("");
		$("#formName").val("");
		$("#formPrize").val("");
		$("#formDateTime").val("");
	});
	
	$("#sendInsert").on('click', function(){
		$("#insert").hide();
		$("#formId").prop('disabled', false);
		var serialized = $("#insertForm").serialize();
		$("#formId").prop('disabled', true);
		$.ajax({
			type : "POST",
			url : "http://localhost:8081/goHomePage/hello2/insert",
			data: serialized,
			success : function(response){
				var valueObj = response;
				refreshData();
			}
		}).done(function() {
			deleteEventRegister();
			selectOneEventRegister();
		});
	});
	
	$("#searchByDate").on('click', function(){
		$(".dataTemplate").empty()
		var postData = {};
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		postData.start = startDate;
		postData.end = endDate;
		$.ajax({
			type : "POST",
			url : "http://localhost:8081/goHomePage/hello2/findBySelectDate",
            contentType: 'application/json',
			data: JSON.stringify(postData),
			success: function(response){
				for (key in response) {
					var append = dataTemplate.clone(true);
					var valueObj = response[key];
					appendData(valueObj);
				}
			}
		}).done(function() {
		});
	});
	
	$("#cancel").on("click", function() {
		$("#insert").hide();
	});
	
	var appendData = function(valueObj) {
		var append = dataTemplate.clone(true);
		append.find('.id').html(valueObj.uuid);
		append.find('.name').html(valueObj.name);
		append.find('.dateTime').html(valueObj.createDate);
		append.find('.prize').html(valueObj.prize);
		append.find('#selectOne').val(valueObj.name);
		append.find('#selectOne').attr("id", valueObj.uuid);
		append.find('#deleteOne').attr("id", valueObj.uuid);
		$("#reportList").append(append);
	}
	
	var refreshData = function() {
		$(".dataTemplate").empty();
		loadData();
	}
	
	loadData();
});