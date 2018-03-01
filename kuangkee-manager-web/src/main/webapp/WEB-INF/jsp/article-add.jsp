<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="<%=this.getServletContext().getContextPath() %>/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="<%=this.getServletContext().getContextPath() %>/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=this.getServletContext().getContextPath() %>/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=this.getServletContext().getContextPath() %>/js/common.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=this.getServletContext().getContextPath() %>/js/module/article-add.js"></script>

<div style="padding:10px 10px 10px 10px">
	<form id="itemAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>故障编码:</td>
	            <td><input class="easyui-textbox" type="text" name="errorCode" data-options="required:true" style="width: 280px;"/></td>
	        </tr>
	        <tr>
	            <td>故障描述/文章标题:</td>
	            <td><input class="easyui-textbox" type="text" name="title" data-options="required:true" style="width: 280px;"/></td>
	        </tr>
<!-- 	        <tr>
	            <td>品牌:</td>
	            <td>
	            	<a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择品牌</a>
	            	<input type="hidden" name="brandId1" style="width: 280px;"/>
	            	<input type="hidden" name="brandName2" style="width: 280px;"/>
	            </td>
	        </tr> -->
	        
	        <tr>
	            <td>品牌:</td>
	            <td>
	            	<input id="cc1" class="easyui-combobox" data-options="
					    valueField: 'id',
					    textField: 'name',
					    url: '<%=this.getServletContext().getContextPath() %>/article/qryBrands',
					    onSelect: function(rec){
					    	$('#brandId').val(rec.id) ;
					    	$('#brandName').val(rec.name) ;
					    }">
					<input type="hidden" id="brandId" name="brandId" style="width: 280px;"/>
	            	<input type="hidden" id="brandName" name="brandName" style="width: 280px;"/>    
	            </td>
	        </tr>
	        
<!-- 	        <tr>
	            <td>商品卖点:</td>
	            <td><input class="easyui-textbox" name="sellPoint" data-options="multiline:true,validType:'length[0,150]'" style="height:60px;width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>商品价格:</td>
	            <td><input class="easyui-numberbox" type="text" name="priceView" data-options="min:1,max:99999999,precision:2,required:true" />
	            	<input type="hidden" name="price"/>
	            </td>
	        </tr> -->
<!-- 	        <tr>
	            <td>库存数量:</td>
	            <td><input class="easyui-numberbox" type="text" name="num" data-options="min:1,max:99999999,precision:0,required:true" /></td>
	        </tr>
	        <tr>
	            <td>条形码:</td>
	            <td>
	                <input class="easyui-textbox" type="text" name="barcode" data-options="validType:'length[1,30]'" />
	            </td>
	        </tr> -->
	        <tr>
	            <td>故障图片（可选）:</td>
	            <td>
	            	 <a href="javascript:void(0)" class="easyui-linkbutton picFileUpload">上传图片</a>
	                 <input type="hidden" name="imgSearchSmall"/>
	            </td>
	        </tr>
	        <tr>
	            <td>故障明细/文章正文:</td>
	            <td>
	                <textarea style="width:800px;height:300px;visibility:hidden;" name="content"></textarea>
	            </td>
	        </tr>
<!-- 	        <tr class="params hide">
	        	<td>商品规格:</td>
	        	<td>
	        		
	        	</td>
	        </tr> -->
	    </table>
	    <input type="hidden" name="creater" value="admin"/>
	    <input type="hidden" name="createrDesc" value="系统管理员录入"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
