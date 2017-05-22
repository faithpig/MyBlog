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
      <li><a href="/" title="相片">沙滩</a></li>
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
      <h1 class="c_titile">404</h1>
      <p class="box"></p>
      <ul><li>404</li></ul>
 
     
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