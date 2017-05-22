<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Carbin</title>
<meta name="keywords" content="faithfish" />
<meta name="description" content="faithfish's home" />
<link href="<%=path %>/css/styles.css" rel="stylesheet">
<link href="<%=path %>/css/view.css" rel="stylesheet">
<!-- 返回顶部调用 begin -->
<link href="<%=path %>/css/lrtk.css" rel="stylesheet" />
<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path %>/js/js.js"></script>
<!-- 返回顶部调用 end-->
<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->
</head>
<body>
<header>
  <nav id="nav">
    <ul>
      <li><a href="<%=path %>/home_home" title="主页">大海</a></li>
      <li><a href="<%=path %>/home_photoList" title="相片">沙滩</a></li>
      <li><a href="<%=path %>/home_msg" title="贝壳">贝壳</a></li>
      <li><a href="<%=path %>/chat.jsp" title="木屋">木屋</a></li>
      <li><a></a></li>
    </ul>
    <script src="js/silder.js"></script><!--获取当前页导航 高亮显示标题--> 
  </nav>
</header>
<!--header end-->
<div id="mainbody">
  <div class="blogs">
    <div id="index_view">
    		<h1>贝壳</h1>

    		<br>
    		<form action="<%=path %>/home_leaveMsg" method="post" name="msgform">
    			昵称：<input type="text" name="msg.name" value="" id="m_name"/>
    			<br/>
    			<br/>
    			内容：<textarea rows="5" cols="40" name="msg.detail" id="m_detail" style="vertical-align: top;"></textarea>
    			<br/>
    			<br/>
    			　　　<input type="button" value="提交" onclick="sub()" style="width:150px;height:30px;"/>
    			<br/>
    			<br/>
    		</form>
    		<hr>
    		<br>
    		<script type="text/javascript">
    		function sub(){
    			var form = document.forms["msgform"];
    			var name = document.getElementById("m_name").value;
    			var detail = document.getElementById("m_detail").value;
			   	if(null == name || "" == name.replace(/(^\s*)|(\s*$)/g,"") ||null == detail || "" == detail.replace(/(^\s*)|(\s*$)/g,"")){
			       	window.alert("标题和内容都不可为空！");
			    }
    			else{
    				form.submit();
    			}
    		}
    		
    		
    		</script>
    
		    <div class="message_block">
            <s:iterator value="mlist" id="entity" status="ab">
              <div class="message_title">
                <h2 style="margin:0 0;font-family:New Timer;">昵称：<s:property value="name"/></h2>
                <span>留言时间：<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/>              　　${ab.count}#</span></div>
              <p><s:property value="detail"/></p>
              <br/>
            </s:iterator>
		            	
		    <script type="text/javascript">
			function sendPage(msg){
				var hiform = document.forms[1];
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
			<form action="<%=path%>/home_msg" method="post" id="hiform">
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
            
            </div>
 
     
    </div>
    <!--bloglist end-->
    <aside>
       <div class="viny">
        <dl>
          <dt class="art"><img src="<%=path %>/images/artwork.png" alt="专辑"></dt>
          <dd class="icon-song"><span></span><s:property value="al.songName"/></dd>
          <dd class="icon-artist"><span></span>歌手：<s:property value="al.singer"/></dd>
          <dd class="icon-album"><span></span>所属专辑：<s:property value="al.albumName"/></dd>
          <dd class="icon-like"><span></span><a href="#">喜欢</a></dd>
          <dd class="music">
            <audio src="<%=path %>/images/nf.mp3" controls></audio>
          </dd>
          <!--也可以添加loop属性 音频加载到末尾时，会重新播放-->
        </dl>
      </div>
    </aside>
  </div>
  <!--blogs end--> 
</div>
<!--mainbody end-->
<footer>
  <div class="footer-mid">
    <section class="flickr">
      <h2>相册</h2>
      <ul>
      <s:iterator value="plist" id="pentity">
        <li><a href="<%=path%>/home_viewPhoto?id=<s:property value='p_id'/>"><img src="<%= path%>/miniphoto/foot/<s:property value='path'/>"></a></li>
      </s:iterator>
      </ul>
    </section>
  </div>
  <!--
  <div class="footer-bottom">
    <p>Copyright 2016 Design by <a href="">faithfish</a></p>
  </div>
  -->
</footer>
<!-- jQuery仿腾讯回顶部和建议 代码开始 -->
<div id="tbox"> <a id="gotop" href="javascript:void(0)"></a> </div>
<!-- 代码结束 -->
</body>
</html>