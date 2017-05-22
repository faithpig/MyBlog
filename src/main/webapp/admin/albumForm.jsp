<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>音乐管理</title>
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
            
            <h1 class="page-title">Music</h1>
                    <ul class="breadcrumb">
           
            <li class="active">Management</li>
        	</ul>

        </div>
       <div class="main-content" id="editorDIV" style="margin-left: 20px;">
       
       
       <script type="text/javascript">
       function subForm(){
			var form1 = document.forms[0];
       		var a_name = document.getElementById("a_name").value;
       		var a_singer = document.getElementById("a_singer").value;
       		var a_album = document.getElementById("a_album").value;
       		var file = form1.file.value;
       		if(null == a_name || "" == a_name.replace(/(^\s*)|(\s*$)/g,"")||null == a_singer || "" == a_singer.replace(/(^\s*)|(\s*$)/g,"")||null == a_album || "" == a_album.replace(/(^\s*)|(\s*$)/g,"")){
       			$('#title_say').css({'color':'#ff0000'});
       			$('#title_say').html('（歌名、歌手、所属专辑不可为空）');
       		}
       		else if("" == file){
       			$('#title_say').css({'color':'#ff0000'});
       			$('#title_say').html('（文件不可为空）');
       		}
       		else{
       			form1.submit();
       		}
       		
       }
       
       </script>
       
       
       <form name="example" method="post" action="<%=path%>/admin_updAlbum" enctype="multipart/form-data">
       	<label>歌名：</label>
       	<input type="text" name="album.songName" value="<s:property value='album.songName'/>" id="a_name"/>
       	<br/>
       	<br/>
       	<label>歌手：</label>
       	<input type="text" name="album.singer" value="<s:property value='album.singer'/>" id="a_singer"/>
       	<br/>
		<br />
		<label>所属专辑：</label>
       	<input type="text" name="album.albumName" value="<s:property value='album.albumName'/>" id="a_album"/>
       	<br/>
		<br />
		<s:file name="file" label="文件"></s:file><span id="title_say"></span>
		<br/>
		<br />
		<input type="button" name="button" value="提交内容" onclick="subForm()"/>
		</form>
       
       
       
       
       </div>
    </div>







${say}


    <script src="<%=path %>/manage_files/lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
    
  
</body></html>
