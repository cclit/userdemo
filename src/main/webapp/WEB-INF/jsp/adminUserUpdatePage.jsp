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
<title>User Detail</title>
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
      
      <!-- User detail -->

      <div class="table-responsive">
       
       <form:form modelAttribute="userUpdateInfo" method="POST" action="${contextRoot}/admin/user/update/${userUpdateInfo.userId}">
        <c:if test="${updateInfo != null}">
        <label>${updateInfo}</label>
        </c:if>
        <table class="table table-striped table-sm">
          <thead>
            <tr>
              <td>欄位</td>
              <td>資訊</td>
            </tr>
          </thead>
          <tbody>
          	<tr>
          		<td>會員ID</td>
          		<td><form:input path="userId" readonly="true"/></td>          		
            </tr>
            <tr>
          		<td>會員帳號/email</td>
          		<td><form:input path="email" readonly="true"/></td>          		
            </tr>
            <tr>
          		<td>會員姓氏</td>
          		<td>
          			<form:errors path="lastName" class="errorMsg" />
          			<form:input path="lastName"  />
          		</td>          		
            </tr>
            <tr>
          		<td>會員名字</td>
          		<td>
          			<form:errors path="firstName" class="errorMsg" />
          			<form:input path="firstName" />
          		</td>          		
            </tr>
            <tr>
          		<td>會員暱稱</td>
          		<td>
          			<form:errors path="nickName" class="errorMsg" />
          			<form:input path="nickName" />
          		</td>          		
            </tr>
            <tr>
          		<td>會員性別</td>
          		<td>
          			<form:errors path="gender" class="errorMsg" />
          			<form:radiobutton path="gender" value="Male" label="男性" />
	  				<form:radiobutton path="gender" value="Female" label="女性" />
	  				<form:radiobutton path="gender" value="Other" label="其他" />
          		</td>          		
            </tr>
             <tr>
          		<td>是否有工作經驗</td>
          		<td>
          			<form:errors path="workExperienceCheck" class="errorMsg" />
          			<form:radiobutton path="workExperienceCheck" value="1" label="YES" />
					<form:radiobutton path="workExperienceCheck" value="0" label="NO" />
				</td>          		
            </tr>
             <tr>
          		<td>工作經驗(年)</td>
          		<td>
          			<form:errors path="workExperience" class="errorMsg" />
          			<form:input path="workExperience" />
          		</td>          		
            </tr>
            
            
          </tbody>
          
        </table>
      	 	<input type="submit" value="確認送出">
      	 	<a href="${contextRoot}/admin/user/arrangement"><input type="button" value="回會員總覽"></a>
        </form:form>

      </div>

	</main>
  </div>
 </div>

<script src="${contextRoot}/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@4.3.2/dist/chart.umd.js" integrity="sha384-eI7PSr3L1XLISH8JdDII5YN/njoSsxfbrkCTnJrzXt+ENP5MOVBxD+l6sEG4zoLp" crossorigin="anonymous"></script><script src="${contextRoot}/js/dashboard.js"></script></body>
</body>
</html>