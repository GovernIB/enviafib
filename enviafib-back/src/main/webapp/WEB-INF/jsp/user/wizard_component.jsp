<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<style>
ul.nav-wizard {
  background-color: #f9f9f9;
  border: 1px solid #d4d4d4;
  -webkit-border-radius: 6px;
  -moz-border-radius: 6px;
  border-radius: 6px;
  *zoom: 1;
  position: relative;
  overflow: hidden;
}
ul.nav-wizard li {
  position: relative;
  float: left;
  height: 46px;
  display: inline-block;
  text-align: middle;
  padding: 0 20px 0 30px;
  margin: 0;
  font-size: 16px;
  line-height: 46px;
}
ul.nav-wizard li a {
  color: #468847;
  padding: 0;
}
ul.nav-wizard li a:hover {
  background-color: transparent;
}
ul.nav-wizard li:before {
  position: absolute;
  display: block;
  border: 24px solid transparent;
  border-left: 16px solid #d4d4d4;
  border-right: 0;
  top: -1px;
  z-index: 10;
  content: '';
  right: -16px;
}
ul.nav-wizard li:after {
  position: absolute;
  display: block;
  border: 24px solid transparent;
  border-left: 16px solid #f9f9f9;
  border-right: 0;
  top: -1px;
  z-index: 10;
  content: '';
  right: -15px;
}
ul.nav-wizard li.active {
  color: #3a87ad;
  background: #d9edf7;
}
ul.nav-wizard li.active:after {
  border-left: 16px solid #d9edf7;
}
ul.nav-wizard li.active a,
ul.nav-wizard li.active a:active,
ul.nav-wizard li.active a:visited,
ul.nav-wizard li.active a:focus {
  color: #3a87ad;
  background: #d9edf7;
}
ul.nav-wizard .active ~ li {
  color: #999999;
  background: #ededed;
}
ul.nav-wizard .active ~ li:after {
  border-left: 16px solid #ededed;
}
ul.nav-wizard .active ~ li a,
ul.nav-wizard .active ~ li a:active,
ul.nav-wizard .active ~ li a:visited,
ul.nav-wizard .active ~ li a:focus {
  color: #999999;
  background: #ededed;
}
ul.nav-wizard.nav-wizard-backnav li:hover {
  color: #468847;
  background: #f6fbfd;
}
ul.nav-wizard.nav-wizard-backnav li:hover:after {
  border-left: 16px solid #f6fbfd;
}
ul.nav-wizard.nav-wizard-backnav li:hover a,
ul.nav-wizard.nav-wizard-backnav li:hover a:active,
ul.nav-wizard.nav-wizard-backnav li:hover a:visited,
ul.nav-wizard.nav-wizard-backnav li:hover a:focus {
  color: #468847;
  background: #f6fbfd;
}
ul.nav-wizard.nav-wizard-backnav .active ~ li {
  color: #999999;
  background: #ededed;
}
ul.nav-wizard.nav-wizard-backnav .active ~ li:after {
  border-left: 16px solid #ededed;
}
ul.nav-wizard.nav-wizard-backnav .active ~ li a,
ul.nav-wizard.nav-wizard-backnav .active ~ li a:active,
ul.nav-wizard.nav-wizard-backnav .active ~ li a:visited,
ul.nav-wizard.nav-wizard-backnav .active ~ li a:focus {
  color: #999999;
  background: #ededed;
}


</style>

<c:if test="${not empty wizardstep}">

	<ul class='nav nav-wizard'>
	  
	  <li ${(wizardstep==1)?"class='active'":""}><a ${(wizardstep>=1)?'href="#step1" data-toggle="tab"':''} ><fmt:message key="menu.flux.wizard.init" /></a></li>
	  
	  <li ${(wizardstep==2)?"class='active'":""}><a ${(wizardstep>=2)?'href="#step2" data-toggle="tab"':''} ><fmt:message key="menu.flux.wizard.validar" /></a></li>
	  
	  <li ${(wizardstep==3)?"class='active'":""}><a ${(wizardstep>=3)?'href="#step3" data-toggle="tab"':''} ><fmt:message key="menu.flux.wizard.fitxer" /></a></li>
	
	  <li ${(wizardstep==4)?"class='active'":""}><a ${(wizardstep>=4)?'href="#step4" data-toggle="tab"':''} ><fmt:message key="menu.flux.wizard.final" /></a></li>
	
	</ul>

</c:if>


