<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>后台管理系统</title>
<link href="<%=path %>/Wopop_files/style_log.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="<%=path %>/Wopop_files/style.css"/>
<link rel="stylesheet" type="text/css" href="<%=path %>/Wopop_files/userpanel.css"/>
<script type="text/javascript" src="<%=path%>/manage_files/lib/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#button').click(function(){
		var username = $('#username').val();
		var userpwd =  $('#userpwd').val();
		$.ajax({
			type:'post',
			url:'<%=path%>/admin_login',
			data:{
				username : username,
				pwd : userpwd
			},
			beforeSend:function(){
			},
			complete:function(){
			},
			success:function(data){
				if($.trim(data.say)==""){
					window.location='<%=path%>/admin_admin';
				}
				else {
					$('#hint').html('提示：密码错误！');
				}
			}
		});
	});
});

</script>
</head>

<body class="login">
<div class="login_m">
<div class="login_logo"><img src="<%=path %>/Wopop_files/logo.png" width="196" height="46"></div>
<div class="login_boder">

<div class="login_padding" id="login_model">
<div id="hint" style="color:#f00;float: right"></div>
  <h2>用户名</h2>
  <label>
    <input type="text" id="username" class="txt_input txt_input2" disabled="disabled" value="faithfish"/>
  </label>
  <h2>密码</h2>
  <label>
    <input type="password" name="textfield2" id="userpwd" class="txt_input"/>
  </label>
 
 

 

  <div class="rem_sub">
  <div class="rem_sub_l">
  <input type="checkbox" name="checkbox" id="save_me">
   <label for="checkbox">记住密码</label>
   </div>
    <label>
      <input type="submit" class="sub_button" name="button" id="button" value="登录" style="opacity: 0.7;">
    </label>
  </div>
  
</div>




</div>
    <script src="<%=path %>/manage_files/lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
    
    ${say}
    
 
  
</body></html>