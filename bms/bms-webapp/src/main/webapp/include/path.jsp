<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="breadcrumbs" id="breadcrumbs">
	<script type="text/javascript">
		try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
	</script>

	<ul class="breadcrumb" style="line-height: 40px; font-size: 13px;font-weight: 600;">
		<li style="color: rgb(71,143,202)">
			<i class="icon-home home-icon"></i>
		</li>
		<% 
			if(null != requestFunction) { 
				List<Function> parentList = requestFunction.getParentFunctionList();
				if(null != parentList) {
					for(int i=parentList.size()-1; i>=0; i--) {
		%>
					<li class="active" style="color: rgb(71,143,202)"><%=parentList.get(i).getFunctionName() %></li>
		<% 
					}
				}
		%>
		<li class="active" style="color: rgb(71,143,202)"><%=requestFunction.getFunctionName() %></li>
		<% } %>
	</ul><!-- .breadcrumb -->

	<div class="nav-search" id="nav-search">
		<form class="form-search">
			<span class="input-icon">
				<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
				<i class="icon-search nav-search-icon"></i>
			</span>
		</form>
	</div><!-- #nav-search -->
</div>