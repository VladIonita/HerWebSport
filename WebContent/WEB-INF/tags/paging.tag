<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ tag import="org.springframework.util.StringUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="pagedList" required="true"
	type="org.springframework.beans.support.PagedListHolder"%>
<%@ attribute name="pagedLink" required="true" type="java.lang.String"%>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">

<c:if test="${pagedList.pageCount > 1}">

	<ul class="pagination">
		<c:if test="${!pagedList.firstPage }">
			<li class="previous"><a
				href="<%=StringUtils.replace(pagedLink, "~", String.valueOf(pagedList.getPage() - 1))%>"><</a></li>
		</c:if>
		<c:if test="${pagedList.firstLinkedPage > 0}">
			<li><a
				href="<%=StringUtils.replace(pagedLink, "~", "0")%>"></a></li>
		</c:if>
		<c:if test="${pagedList.firstLinkedPage > 1}">
			<li><span class="pagingDots">...</span></li>
		</c:if>
		<c:forEach begin="${pagedList.firstLinkedPage}"
			end="${pagedList.lastLinkedPage}" var="i">
			<c:choose>
				<c:when test="${pagedList.page == i}">
					<li class="active"><a href="#">${i+1}</a></li>
				</c:when>
				<c:otherwise>
					<li><a
						href="<%=StringUtils.replace(pagedLink, "~", String.valueOf(jspContext.getAttribute("i")))%>">${i+1}</a>
					</li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pagedList.lastLinkedPage < pagedList.pageCount - 2}">
			<li><span class="pagingDots">...</span></li>
		</c:if>
		<c:if test="${pagedList.lastLinkedPage < pagedList.pageCount - 1}">
			<li class="next"><a
				href="<%=StringUtils.replace(pagedLink, "~", String.valueOf(pagedList.getPageCount() - 1))%>">
					${pagedList.pageCount}</a></li>
		</c:if>
		<c:if test="${!pagedList.lastPage}">
			<li class="next"><a
				href="<%=StringUtils.replace(pagedLink, "~", String.valueOf(pagedList.getPageCount() + 1))%>">
					></a></li>
		</c:if>

	</ul>


</c:if>