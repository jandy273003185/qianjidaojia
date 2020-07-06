<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.Map"%>
<%@page import="com.qifenqian.platform.web.Constants"%>
<%@page import="com.qifenqian.platform.common.utils.ThreadUtils"%>
<%@page import="com.qifenqian.platform.web.page.bean.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	// 获取分页对象
	PageInfo pageInfo = ThreadUtils.get(Constants.Platform.$_PAGEINFO, PageInfo.class);
%>

<script src='<c:url value="/static/js/jquery.pagination.js"/>'></script>

<style>
	.pagination {
		font-size: 15px;
		font-weight: normal;
	}
	        
	.pagination a {
	    text-decoration: none;
		border: solid 1px #AAE;
		color: #15B;
	}
	
	.pagination a, .pagination span {
	    display: inline-block;
	    padding: 0.3em 0.5em;
	    margin-right: 5px;
		margin-bottom: 5px;
		min-width:1em;
		text-align:center;
	}
	
	.pagination .current {
	    background: #26B;
	    color: #fff;
		border: solid 1px #AAE;
	}
	
	.pagination .current.prev, .pagination .current.next{
		color:#999;
		border-color:#999;
		background:#fff;
	}
	
	.pagination .PageTotalSpan, .pagination .PageJumpSpan {
		color:#15B;
	}
</style>

<script>

	$(function(){
	    $("#Pagination").pagination(<%= pageInfo.getRowCnt() %>, {
	        num_display_entries: 11,
	        callback: pageselectCallback,
	        items_per_page: <%= pageInfo.getPageSize() %>,
	        num_edge_entries: 2,
	        current_page : <%= pageInfo.getCurrentPage() %> - 1
	    });
	    
	    // 矫正上下两个span的位置
	    $("#Pagination div").prepend($('#PageTotalSpan'));
	    $("#Pagination div").append($('#PageJumpSpan'));
	    $("#Pagination div").append( $("#PageJumpSpan").contents());
	    
	    // 监听回车键
	    $('#JumpInput').keypress(function(e){
	    	if(e.keyCode == 13){
	    		paginationJump();
	    	}
	    });
	});
	
	//如果所提交的url是绝对路径,则前面加webContext路径,否则直接发送相对路径
	function pageselectCallback(page_index, jq){
		var url = '<%=request.getAttribute("javax.servlet.forward.request_uri")%>'
		<%
			Map<String, String[]> params = request.getParameterMap();
			JSONObject paramsJson = new JSONObject();
			for (Map.Entry<String, String[]> entry : params.entrySet()) {
				JSONArray paramArray = new JSONArray();
				String paramName = entry.getKey();
				String[] paramValues = entry.getValue();
				for (String paramValue : paramValues) {
					paramArray.add(paramValue);
				}
				paramsJson.put(paramName, paramArray);
			}
			String paramsString = paramsJson.toJSONString();
		%>
		var params = $.parseJSON('<%=paramsString%>');
		params.PAGEINFO_CURRENT_PAGE = page_index + 1;
		$.gyzbadmin.postForm(url,params);
	};
	
	// 分页跳转函数
	function paginationJump(){
		var pageIdx = $('#JumpInput').val();
		if(pageIdx == '' || pageIdx == null){
			$.gyzb-admin.alertFailure('请输入要跳转的页数');
			$('#JumpInput').focus();
			return;
		}
		try{
			if(!pageIdx.match(/^\d+$/)){
				$.gyzbadmin.alertFailure('输入不是一个合法的有效数字!');
				$('#JumpInput').focus();
				return;
			}
			pageIdx = new Number(pageIdx);
			if(pageIdx == 0){
				$.gyzbadmin.alertFailure('跳转的页数页数不能为0!');
				$('#JumpInput').focus();
				return;
			}
			if(pageIdx > <%= pageInfo.getPageCnt() %>){
				$.gyzbadmin.alertFailure('跳转的页数不能超过当前最大页数!');
				$('#JumpInput').focus();
				return;
			}
			// 否则进行跳转
			pageselectCallback(pageIdx - 1);
		}catch(e){}
	}
</script>

<table style="width: 100%;background-color: #eff3f8!important;">
	<tr>
		<td align="center">
			<span id="PageTotalSpan" class="PageTotalSpan">共 <%= pageInfo.getRowCnt() %>条&nbsp;&nbsp; 共 <%= pageInfo.getPageCnt() %>页 </span>
			<div id="Pagination" style="text-align: center;"></div>
			<span id="PageJumpSpan" style="display: none"><span class="PageJumpSpan">跳到<input id="JumpInput" style="text-align: right; width: 30px;" type="text" size="3" />页</span><a style="cursor: pointer;" onclick="javascript:paginationJump('JumpInput');">GO</a></span>
		</td>
	</tr>
</table>
