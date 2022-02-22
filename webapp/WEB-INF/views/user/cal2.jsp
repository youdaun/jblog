<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style TYPE="text/css">

	
	
</style>

</head>
<body>

		<div id="calendar">
		
			<!--날짜 네비게이션  -->
			<div class="navigation">
				<a class="before_after_year" href="${pageContext.request.contextPath}/calendar2?year=${today_info.search_year-1}&month=${today_info.search_month-1}"> &lt;&lt; <!-- 이전해 -->
				</a> <a class="before_after_month" href="${pageContext.request.contextPath}/calendar2?year=${today_info.before_year}&month=${today_info.before_month}"> &lt; <!-- 이전달 -->
				</a> <span class="this_month"> &nbsp;${today_info.search_year}. <c:if test="${today_info.search_month<10}">0</c:if>${today_info.search_month}
				</span> <a class="before_after_month" href="${pageContext.request.contextPath}/calendar2?year=${today_info.after_year}&month=${today_info.after_month}"> <!-- 다음달 --> &gt;
				</a> <a class="before_after_year" href="${pageContext.request.contextPath}/calendar2?year=${today_info.search_year+1}&month=${today_info.search_month-1}"> <!-- 다음해 --> &gt;&gt;
				</a>
			</div>
		
			<table class="calendar_body">
				<thead>
					<tr>
						<th class="day sun">일</th>
						<th class="day">월</th>
						<th class="day">화</th>
						<th class="day">수</th>
						<th class="day">목</th>
						<th class="day">금</th>
						<th class="day sat">토</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach var="date" items="${dateList}" varStatus="date_status">
							<c:choose>
								<c:when test="${date.value=='today'}">
									<td class="today">
										<div class="date">${date.date}</div>
										<div></div>
									</td>
								</c:when>
								
								<c:when test="${date_status.index%7==6}">
									<td class="sat_day">
										<div class="sat">${date.date}</div>
										<div></div>
									</td>
								</c:when>
								
								<c:when test="${date_status.index%7==0}">
					</tr>
					<tr>
									<td class="sun_day">
										<div class="sun">${date.date}</div>
										<div></div>
									</td>
								</c:when>
								<c:otherwise>
									<td class="normal_day">
										<div class="date">${date.date}</div>
										<div></div>
									</td>
							</c:otherwise>
							</c:choose>
						</c:forEach>
					</tr>
				</tbody>
			</table>
		</div>
		

</body>
</html>