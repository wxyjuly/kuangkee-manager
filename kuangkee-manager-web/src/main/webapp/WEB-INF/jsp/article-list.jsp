<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8" src="<%=this.getServletContext().getContextPath() %>/js/module/article-list.js"></script>

<table class="easyui-datagrid" id="itemList" title="文章列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'<%=this.getServletContext().getContextPath() %>/article/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'articleId',width:60">文章ID</th>
            <th data-options="field:'brandName',width:200">所属品牌</th>
            <th data-options="field:'errorCode',width:200">错误编码</th>
            <th data-options="field:'title',width:100">错误概述</th>
            <th data-options="field:'imgSearchSmall',width:100">图片</th>
            <th data-options="field:'imagContentSmall',width:100">正文图片</th>
            <th data-options="field:'content',width:70,align:'right'">正文内容</th>
            <th data-options="field:'isSearchable',width:100">是否可搜索</th>
            <th data-options="field:'createTime',width:60,align:'center',formatter:KuangHee.formatDateTime">创建日期</th>
            <th data-options="field:'updateTime',width:130,align:'center',formatter:KuangHee.formatDateTime">修改日期</th>
        </tr>
    </thead>
</table>
<div id="itemEditWindow" class="easyui-window" title="编辑商品" 
	data-options="modal:true,closed:true,iconCls:'icon-save',href:''" 
	style="width:80%;height:80%;padding:10px;">
</div>
