	var itemAddEditor ;
	//页面初始化完毕后执行此方法
	$(function(){
		//创建富文本编辑器
		//itemAddEditor = KuangHee.createEditor("#itemAddForm [name=desc]");
		itemAddEditor = KindEditor.create("#itemAddForm [name=content]", TT.kingEditorParams);
		//初始化类目选择和图片上传器
		KuangHee.init({fun:function(node){
			//根据商品的分类id取商品 的规格模板，生成规格信息。第四天内容。
			KuangHee.changeItemParam(node, "itemAddForm");
		}});
	});
	//提交表单
	function submitForm(){
		//有效性验证
		if(!$('#itemAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
/* 		//取商品价格，单位为“分”
		$("#itemAddForm [name=price]").val(eval($("#itemAddForm [name=priceView]").val()) * 100); */
		var errorCode = $("#itemAddForm [name=errorCode]").val() ;
		var title = $("#itemAddForm [name=title]").val() ;
		var brandId = $("#itemAddForm [name=brandId]").val() ; 
		var brandName = $("#itemAddForm [name=brandName]").attr('brand_name') ;
		var content = $("#itemAddForm [name=content]").val() ;
		
		alert("errorCode:"+errorCode
				+ ";title:"+title
				+ ";brandId:"+brandId
				+ ";brandName:"+brandName
				+ ";content:"+content
				);
/* 		$("#itemAddForm [name=errorCode]").val(errorCode); 
		$("#itemAddForm [name=title]").val(title); 
		//品牌ID，品牌名称
		$("#itemAddForm [name=brandId]").val(brandId); 
		$("#itemAddForm [name=brandName]").val(brandName); 
		$("#itemAddForm [name=content]").val(content);  */
		//同步文本框中的商品描述
		itemAddEditor.sync();
		//取商品的规格
/* 		var paramJson = [];
		$("#itemAddForm .params li").each(function(i,e){
			var trs = $(e).find("tr");
			var group = trs.eq(0).text();
			var ps = [];
			for(var i = 1;i<trs.length;i++){
				var tr = trs.eq(i);
				ps.push({
					"k" : $.trim(tr.find("td").eq(0).find("span").text()),
					"v" : $.trim(tr.find("input").val())
				});
			}
			paramJson.push({
				"group" : group,
				"params": ps
			});
		});
		//把json对象转换成字符串
		paramJson = JSON.stringify(paramJson);
		$("#itemAddForm [name=itemParams]").val(paramJson); */
		
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("<%=this.getServletContext().getContextPath() %>/article/save",
				$("#itemAddForm").serialize(), function(data){
			
			if(data.status == 200){
				$.messager.alert('提示','新增文章成功!');
				window.close() ;
			}
		});
	}
	
	function clearForm(){
		$('#itemAddForm').form('reset');
		itemAddEditor.html('');
	}