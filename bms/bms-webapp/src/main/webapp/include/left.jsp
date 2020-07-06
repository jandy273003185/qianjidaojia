<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String ctx = request.getContextPath();
%>
<a class="menu-toggler" id="menu-toggler" href="#">
	<span class="menu-text"></span>
</a>
				
<div class="sidebar" id="sidebar">
	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
	</script>

	<ul class="nav nav-list">
		<% if(null != user) { %> 
		<c:forEach items="<%=user.getMenuList() %>" var="menu1">
			
				<li class='<c:if test="${gyzbadmin:pathHasFunc(requestFunction, menu1)}">active</c:if>'>
				<a href='<c:if test="${not empty menu1.functionUrl }"><%=ctx %>${menu1.functionUrl}</c:if><c:if test="${empty menu1.functionUrl }">#</c:if>' class="dropdown-toggle">
					<i class="<c:if test="${not empty menu1.iconClass }">${menu1.iconClass}</c:if><c:if test="${empty menu1.iconClass }">icon-list</c:if>"></i>
					<span class="menu-text"> ${menu1.functionName} </span>
					<c:if test="${not empty menu1.subFunctionList}">
					<b class="arrow icon-angle-down"></b>
					</c:if>
				</a>

				<c:if test="${not empty menu1.subFunctionList}">
					<ul class="submenu">
						<c:forEach items="${menu1.subFunctionList}" var="menu2">
							<li class='<c:if test="${gyzbadmin:pathHasFunc(requestFunction, menu2)}">active</c:if>'>
								<a href='<c:if test="${not empty menu2.functionUrl }"><%=ctx %>${menu2.functionUrl}</c:if><c:if test="${empty menu2.functionUrl }">#</c:if>' class="dropdown-toggle">
									<i class="icon-pencil"></i>
									${menu2.functionName}
									<c:if test="${not empty menu2.subFunctionList}">
									<b class="arrow icon-angle-down"></b>
									</c:if>
								</a>
								<c:if test="${not empty menu2.subFunctionList}">
									<ul class="submenu">
										<c:forEach items="${menu2.subFunctionList}" var="menu3">
											<li class='<c:if test="${gyzbadmin:pathHasFunc(requestFunction, menu3)}">active</c:if>'>
												<a href='<c:if test="${not empty menu3.functionUrl }"><%=ctx %>${menu3.functionUrl}</c:if><c:if test="${empty menu3.functionUrl }">#</c:if>'>
													<i class="icon-plus"></i>
													${menu3.functionName}
												</a>
											</li>
										</c:forEach>
									</ul>
								</c:if>
							</li>
						</c:forEach>
					</ul>
				</c:if>
			</li>
		</c:forEach>
		<% } %> 
		
	</ul><!-- /.nav-list -->

	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
	</div>

	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
	</script>
</div>