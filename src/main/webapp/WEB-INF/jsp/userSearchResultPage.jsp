<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
<html>
<head><script src="${contextRoot}/js/color-modes.js"></script>
<meta charset="UTF-8">
<title>User Arrangement</title>
<link href="${contextRoot}/css/bootstrap.min.css" rel="stylesheet">
	<style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        width: 100%;
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }

      .btn-bd-primary {
        --bd-violet-bg: #712cf9;
        --bd-violet-rgb: 112.520718, 44.062154, 249.437846;

        --bs-btn-font-weight: 600;
        --bs-btn-color: var(--bs-white);
        --bs-btn-bg: var(--bd-violet-bg);
        --bs-btn-border-color: var(--bd-violet-bg);
        --bs-btn-hover-color: var(--bs-white);
        --bs-btn-hover-bg: #6528e0;
        --bs-btn-hover-border-color: #6528e0;
        --bs-btn-focus-shadow-rgb: var(--bd-violet-rgb);
        --bs-btn-active-color: var(--bs-btn-hover-color);
        --bs-btn-active-bg: #5a23c8;
        --bs-btn-active-border-color: #5a23c8;
      }

      .bd-mode-toggle {
        z-index: 1500;
      }

      .bd-mode-toggle .dropdown-menu .active .bi {
        display: block !important;
      }
      
      .errorMsg {
      	color : red;
     }
    </style>
    <!-- Custom styles for this template -->
    <link href="${contextRoot}/css/dashboard.css" rel="stylesheet">

<body>
	
	<jsp:include page="./layout/adminHeader.jsp"></jsp:include>
	
<div class="container-fluid">
  <div class="row">
	<jsp:include page="./layout/sideBar.jsp"></jsp:include>

	<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">會員管理</h1>
        <div class="btn-toolbar mb-2 mb-md-0">
          <div class="btn-group me-2">
            <button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
            <button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
          </div>
          <button type="button" class="btn btn-sm btn-outline-secondary dropdown-toggle d-flex align-items-center gap-1">
            <svg class="bi"><use xlink:href="#calendar3"/></svg>
            This week
          </button>
        </div>
      </div>
      
      <!-- search region -->
      <div>
      	<c:if test="${keywordEmpty != null}">
        <label class="errorMsg">${keywordEmpty}</label>
        </c:if>
        <form action="${contextRoot}/admin/user/search/account" method="GET">
          <label for="">會員帳號關鍵字搜尋 : </label>
          <input type="text" name="keyword">
          <input type="submit" value="搜尋">
        </form>
      </div>
      <div>
        <form action="${contextRoot}/admin/user/search/name" method="GET">
          <label for="">會員姓名關鍵字搜尋 : </label>
          <input type="text" name="keyword">
          <input type="submit" value="搜尋">
        </form>
      </div>
      
      <div>
        <form action="${contextRoot}/admin/user/search/multicondition" method="GET">
          <label for="">複合式條件查詢 : </label>
          <label for="">會員帳號 : </label>
          <input type="text" name="accountKeyword">
          <label for="">會員暱稱 : </label>
          <input type="text" name="nickNameKeyword">
          <label for="">性別 : </label>
          <select name="gender">
          	<option value="">---</option>
          	<option value="Male">男性</option>
          	<option value="Female">女性</option>
          	<option value="Other">其他</option>
          </select>
          <input type="submit" value="搜尋">
        </form>
      </div>
      
      
      <!-- <h2>Section title</h2> -->
      <div class="table-responsive">
        <table class="table table-striped table-sm">
          <thead>
            <tr>
              <th scope="col">會員ID</th>
              <th scope="col">會員姓名</th>
              <th scope="col">會員暱稱</th>
              <th scope="col">email</th>
              <th scope="col">性別</th>
              <th scope="col">最後編輯時間</th>
              <th scope="col">刪除</th>
            </tr>
          </thead>
          <tbody>
          	
          	<c:forEach var="user" items="${page.content}">
          	<tr>
              <td><a href="${contextRoot}/admin/user/detail/${user.userId}">${user.userId}</a></td>
              <td>${user.lastName} ${user.firstName}</td>
              <td>${user.nickName}</td>
              <td>${user.email}</td>
              <th>${user.gender}</th>
              <td>${user.updateTime}</td>
              <td>
              	<form action="${contextRoot}/admin/user/delete" method="post" onsubmit="return confirm('確定要刪除 id 為 ${user.userId} 這筆資料嗎?');">
						<input type="hidden" name="_method" value="delete" />
						<input type="hidden" name="id" value="${user.userId}" />
						<input type="submit" class="btn btn-danger" value="刪除" id="deleteSub" />
				</form>
              </td>
            </tr>
          	</c:forEach>
           
          </tbody>
        </table>
      </div>
      
      
		<c:forEach var="pageNum" begin="1" end="${page.totalPages}">
		<c:choose>
		<c:when test = "${page.number+1 == pageNum}">
			${pageNum}
		</c:when>
		<c:otherwise>
			<a href="${contextRoot}/admin/user/arrangement?p=${pageNum}">${pageNum}</a>
		</c:otherwise>
		</c:choose>
		
		<c:if test ="${pageNum != page.totalPages}">
		--
		</c:if>
		
		</c:forEach>


	</main>
  </div>
 </div>

<script src="${contextRoot}/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@4.3.2/dist/chart.umd.js" integrity="sha384-eI7PSr3L1XLISH8JdDII5YN/njoSsxfbrkCTnJrzXt+ENP5MOVBxD+l6sEG4zoLp" crossorigin="anonymous"></script><script src="${contextRoot}/js/dashboard.js"></script></body>
</body>
</html>