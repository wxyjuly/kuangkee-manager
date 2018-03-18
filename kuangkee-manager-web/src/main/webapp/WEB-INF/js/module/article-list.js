 function getSelectionsIds(){
    	var articleList = $("#articleList");
    	var sels = articleList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].articleId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [
/*    	{
	        text:'新增',
	        iconCls:'icon-add',
	        handler:function(){
	        	$(".tree-title:contains('新增文章')").parent().click();
	        	
	        }
    	},*/
    {
        text:'编辑-待完善',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一篇文章才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一篇文章!');
        		return ;
        	}
        	
        	$("#itemEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#articleList").datagrid("getSelections")[0];
        			data.priceView = KuangHee.formatPrice(data.price);
        			$("#itemeEditForm").form("load",data);
        			
        			// 加载文章描述
        			$.getJSON(baseProjectPath+'/desc/'+data.id,function(_data){
        				if(_data.status == 000000){
        					//UM.getEditor('itemeEditDescEditor').setContent(_data.data.itemDesc, false);
        					itemEditEditor.html(_data.data.itemDesc);
        				}
        			});
        			
        			//加载文章规格
        			$.getJSON(baseProjectPath +'/query/'+data.id,function(_data){
        				if(_data && _data.status == 000000 && _data.data && _data.data.paramData){
        					$("#itemeEditForm .params").show();
        					$("#itemeEditForm [name=itemParams]").val(_data.data.paramData);
        					$("#itemeEditForm [name=itemParamId]").val(_data.data.id);
        					
        					//回显文章规格
        					 var paramData = JSON.parse(_data.data.paramData);
        					
        					 var html = "<ul>";
        					 for(var i in paramData){
        						 var pd = paramData[i];
        						 html+="<li><table>";
        						 html+="<tr><td colspan=\"2\" class=\"group\">"+pd.group+"</td></tr>";
        						 
        						 for(var j in pd.params){
        							 var ps = pd.params[j];
        							 html+="<tr><td class=\"param\"><span>"+ps.k+"</span>: </td><td><input autocomplete=\"off\" type=\"text\" value='"+ps.v+"'/></td></tr>";
        						 }
        						 
        						 html+="</li></table>";
        					 }
        					 html+= "</ul>";
        					 $("#itemeEditForm .params td").eq(1).html(html);
        				}
        			});
        			
        			KuangHee.init({
        				"pics" : data.image,
        				"cid" : data.cid,
        				fun:function(node){
        					KuangHee.changeItemParam(node, "itemeEditForm");
        				}
        			});
        		}
        	}).window("open");
        }
    },{
        text:'删除--待完善',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中文章!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的文章吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post(baseProjectPath+"/article/delete",params, function(data){
            			if(data.status == 000000){
            				$.messager.alert('提示','删除文章成功!',undefined,function(){
            					$("#articleList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },'-',{
        text:'文章不可搜索',
        iconCls:'icon-remove',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中文章!');
        		return ;
        	}
        	$.messager.confirm('确认','确定下架ID为 '+ids+' 的文章吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post(baseProjectPath+"/article/unstock",params, function(data){
            			if(data.status == 000000){
            				$.messager.alert('提示','下架文章成功!',undefined,function(){
            					$("#articleList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },{
        text:'文章可搜索',
        iconCls:'icon-remove',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中文章!');
        		return ;
        	}
        	$.messager.confirm('确认','确定上架ID为 '+ids+' 的文章吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post(baseProjectPath+"/article/instock",params, function(data){
            			if(data.status == 000000){
            				$.messager.alert('提示','上架文章成功!',undefined,function(){
            					$("#articleList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];