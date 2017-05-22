<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>博客空间管理</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">


    <link rel="stylesheet" type="text/css" href="<%=path %>/manage_files/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="<%=path %>/manage_files/lib/font-awesome/css/font-awesome.css">

    <script src="<%=path %>/manage_files/lib/jquery-1.11.1.min.js" type="text/javascript"></script>

    

    <link rel="stylesheet" type="text/css" href="<%=path %>/manage_files/stylesheets/theme.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/manage_files/stylesheets/premium.css">

</head>
<body class=" theme-blue">

    <!-- Demo page code -->

    <script type="text/javascript">
        $(function() {
            var match = document.cookie.match(new RegExp('color=([^;]+)'));
            if(match) var color = match[1];
            if(color) {
                $('body').removeClass(function (index, css) {
                    return (css.match (/\btheme-\S+/g) || []).join(' ');
                });
                $('body').addClass('theme-' + color);
            }

            $('[data-popover="true"]').popover({html: true});
            
        });
    </script>
    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .navbar-default .navbar-brand, .navbar-default .navbar-brand:hover { 
            color: #fff;
        }
    </style>

    <script type="text/javascript">
        $(function() {
            var uls = $('.sidebar-nav > ul > *').clone();
            uls.addClass('visible-xs');
            $('#main-menu').append(uls.clone());
        });
        
        function del(id){
        	if(confirm("确定要删除吗？")){
        		window.location.href="<%=path%>/admin_delMsg?id="+id;
        	}
        }
        $(document).ready(function(){
	        $('#delAll').click(function(){
	        	var checklist =[];
	        	var params="";
	        	$('input[name="allSelect"]:checked').each(function(){
	        		checklist.push($(this).val());

	        	});
	        	if(checklist.length==0) {
	        		$('#btn_say').html("　你还没有选取任何内容！");
	        		$('#btn_say').css({'color': '#f00'});
	        	}
	        	else{
				    $.each(checklist,function(n,value){
			            params+="idList["+n+"]="+value+"&";
			        });
			        if(confirm("确定要删除所有选中项吗？")){
						$.ajax({
									type:'post',
									url:'<%=path%>/admin_delMsgList',
									data:params,
									beforeSend:function(){
									},
									complete:function(){
									},
									success:function(data){
											if($.trim(data.say)=="") window.location.href="<%=path%>/admin/login.jsp";
											else{
												window.location.href="<%=path%>/admin_listMsg";
											}
									}
								});	
					}
	        	}
	        });
        });
    </script>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="<%=path %>/manage_files/assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%=path %>/manage_files/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=path %>/manage_files/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=path %>/manage_files/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="<%=path %>/manage_files/assets/ico/apple-touch-icon-57-precomposed.png">
  

  <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!--> 
   
  <!--<![endif]-->

    <div class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="" href="index.html"><span class="navbar-brand"><span class="fa fa-paper-plane"></span></span></a></div>

        <div class="navbar-collapse collapse" style="height: 1px;">
          <ul id="main-menu" class="nav navbar-nav navbar-right">
            <li class="dropdown hidden-xs">
                <a href="<%=path %>/admin_clear" class="dropdown-toggle">
                    <span class="glyphicon glyphicon-user padding-right-small" style="position:relative;top: 3px;"></span> faithfish
                   
                </a>

            </li>
          </ul>

        </div>
      </div>
    </div>
    

    <div class="sidebar-nav">
    <ul>
    <li><a href="#" data-target=".dashboard-menu" class="nav-header" data-toggle="collapse"><i class="fa fa-fw fa-dashboard"></i> 操作面板<i class="fa fa-collapse"></i></a></li>
    <li><ul class="dashboard-menu nav nav-list collapse in">

            <li class="active"><a href="<%=path%>/admin_listBlog"><span class="fa fa-caret-right"></span>博客管理</a></li>
            <li ><a href="<%=path%>/admin_listPhoto"><span class="fa fa-caret-right"></span> 相册管理</a></li>
            <li ><a href="<%=path%>/admin_showAlbum"><span class="fa fa-caret-right"></span> 音乐管理</a></li>
            <li ><a href="<%=path%>/admin_listMsg"><span class="fa fa-caret-right"></span> 留言管理</a></li>
    </ul></li>

    </ul>
    </div>

    <div class="content">
        <div class="header">
            
            <h1 class="page-title">Message</h1>
                    <ul class="breadcrumb">
           
            <li class="active">Management</li>
        </ul>

        </div>
        <div class="main-content">
<div class="btn-toolbar list-toolbar">
    <button class="btn btn-primary" id="delAll"><i class=""></i>批量删除</button><span id="btn_say"></span>
  <div class="btn-group">
  </div>
</div>            

<table class="table">
  <thead>
    <tr>
      <th>选择</th>
      <th>编号</th>
      <th>创建时间</th>
      <th>昵称</th>
      <th>内容</th>
      <th style="width: 3.5em;"></th>
    </tr>
  </thead>
  <tbody>
  <s:iterator value="mlist" id="bentity" status="st">
    <tr>
      <td><input type="checkbox" name="allSelect" value='<s:property value="m_id"/>'/></td>
      <td><s:property value="#st.index+1"/></td>
      <td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
      <td><a href="<%=path%>/home_msg" target="_blank"><s:property value="name"/></a></td>
      <td><s:property value="detail"/></td>
      <td>
          <a href=""><i class="fa fa-pencil"></i></a>
          <a href="javascript:del(<s:property value='m_id'/>)"><i class="fa fa-trash-o"></i></a>
      </td>
    </tr>
</s:iterator>
    <tr>
  		<td colspan="5">
  			
  			<script type="text/javascript">
			function sendPage(msg){
				var hiform = document.forms[0];
				var k = document.getElementById("p_k").value;
				var mtd = document.getElementById("method");
				mtd.value = msg;
				if(msg == "before" && <s:property value="pager.currentPage"/> <=1){}
				else if(msg == "next" && <s:property value="pager.currentPage"/> >=<s:property value="pager.total"/>){}
				else if(msg =="jump" && (!(!isNaN(k) && k > 0 && k <= <s:property value="pager.total"/>))){
					
				}
				else{	
		      		hiform.submit();
		      	}
			}
			</script>
			<form action="<%=path%>/admin_listMsg" method="post" id="hiform">
		    	<span style="color:#aaa;">页码&nbsp;${pager.currentPage}/${pager.total}</span>&nbsp;&nbsp;
				<a href="javascript:sendPage('before')">上页</a>&nbsp;&nbsp;
				<a href="javascript:sendPage('next')">下页</a>&nbsp;&nbsp;
				<input type="hidden" name="p_method" value="" id="method"/>
				<input type="hidden" name="p_c" value="${ pager.currentPage}"/>
				<input type="hidden" name="p_t" value="${ pager.total}"/>
				<input type="hidden" name="p_m" value="${ pager.m}"/>
				<input type="text" style="width:20px" name="k" id="p_k"/>&nbsp;&nbsp;<a href="javascript:sendPage('jump')">跳页</a>
				<input type="text" name="notautosubmit" style="display:none"/>
				
			</form>
  		</td>
    </tr>
  </tbody>
</table>
<div class="modal small fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="myModalLabel">Delete Confirmation</h3>
        </div>
        <div class="modal-body">
            <p class="error-text"><i class="fa fa-warning modal-icon"></i>Are you sure you want to delete the user?<br>This cannot be undone.</p>
        </div>
        <div class="modal-footer">
            <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">Cancel</button>
            <button class="btn btn-danger" data-dismiss="modal">Delete</button>
        </div>
      </div>
    </div>
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
    
  