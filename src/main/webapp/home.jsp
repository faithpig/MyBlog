<%@page import="com.opensymphony.xwork2.ActionContext"%>
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
<title>Ocean</title>
<meta name="keywords" content="faithfish" />
<meta name="description" content="faithfish's home" />
<link href="<%=path %>/css/styles.css" rel="stylesheet">
<link href="<%=path %>/css/animation.css" rel="stylesheet">
<!-- 返回顶部调用 begin -->
<link href="<%=path %>/css/lrtk.css" rel="stylesheet" />
<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path %>/js/js.js"></script>
<!-- 返回顶部调用 end-->
<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->


<script type="text/javascript">
/* 文本溢出显示省略号 */
/* 用法：$(".box").ellipsis(50) */
/*box为需要实现的Class*/
(function($){
jQuery.fn.ellipsis = function(maxwidth){
	this.each(function(){
		if ($(this).text().length > maxwidth) {
			$(this).attr("title",$(this).text().substring(0, maxwidth));
			$(this).text($(this).text().substring(0, maxwidth));
			$(this).html($(this).html() + '...');
		}
	});};
})(jQuery);
$(document).ready(function(){
	$(".hide").ellipsis(180);
});
</script>

</head>


<body>
<header>
  <nav id="nav">
    <ul>
      <li><a href="<%=path %>/home_home" title="主页">大海</a></li>
      <li><a href="<%=path %>/home_photoList" title="相片">沙滩</a></li>
      <li><a href="<%=path %>/home_msg" title="贝壳">贝壳</a></li>
      <li><a href="<%=path %>/chat.jsp" title="木屋">木屋</a></li>
      <li><a href="<%=path %>/admin_admin" target="_blank" title="相片">后台接口</a></li>
      <li><a></a></li>
    </ul>
    <script src="js/silder.js"></script><!--获取当前页导航 高亮显示标题--> 
  </nav>
</header>
<!--header end-->
<div id="mainbody">
  <div class="info">
    <figure> <img src="<%=path %>/images/post1.jpg" width="600" height="280">
      <figcaption><strong>渡人如渡己，渡已，亦是渡</strong> 当我们被误解时，会花很多时间去辩白。 但没有用，没人愿意听，大家习惯按自己的所闻、理解做出判别，每个人其实都很固执。与其努力且痛苦的试图扭转别人的评判，不如默默承受，给大家多一点时间和空间去了解。而我们省下辩解的功夫，去实现自身更久远的人生价值。其实，渡人如渡己，渡已，亦是渡人。</figcaption>
    </figure>
    <div class="card">
      <h1>名片</h1>
      <p>姓名：faithfish</p>
      <p>工作: swim</p>
      <p>手机：18972812968</p>
      <p>E-Mail：xcmorning@126.com</p>
      <ul class="linkmore">
        <li><a href="#" class="talk" title="给我留言"></a></li>
        <li><a href="#" class="address" title="联系地址"></a></li>
        <li><a href="#" class="email" title="给我写信"></a></li>
        <li><a href="#" class="photos" title="生活照片"></a></li>
        <li><a href="#" class="heart" title="关注我"></a></li>
      </ul>
    </div>
  </div>
  <!--info end-->
  <div class="blank"></div>
  <div class="blogs">
    <ul class="bloglist">
<s:iterator value="blist" id="bentity">
	<li>
        <div class="arrow_box">
          <div class="ti"></div>
          <!--三角形-->
          <div class="ci"></div>
          <!--圆形-->
          <h2 class="title"><a href="<%=path%>/home_viewBlog?id=<s:property value='b_id'/>" target="_blank"><s:property value="title"/></a></h2>
           <ul class="textinfo">
        <li>
            
            <p class="hide" onclick="javascript:window.open('<%=path%>/home_viewBlog?id=<s:property value="b_id"/>')" style="cursor:pointer;"><s:property value="content" escape="false"/></p>
          </li> 
          </ul> 
          <ul class="details">
            <li class="icon-time"><a href="#"><s:date name="alterTime" format="yyyy-MM-dd" /></a></li>
          </ul>
        </div>
        <!--arrow_box end--> 
      </li>
</s:iterator>
	

	<li>
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
	<form action="<%=path%>/home_home" method="post" id="hiform">
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
    </li>
	
    </ul>

    <!--bloglist end-->
    <aside>
    <!--
      <div class="tuijian">
        <h2>推荐文章</h2>
        <ol>
          <li><span><strong>1</strong></span><a href="/">有一种思念，是淡淡的幸福,一个心情一行文字</a></li>
          <li><span><strong>2</strong></span><a href="/">励志人生-要做一个潇洒的女人</a></li>
          <li><span><strong>3</strong></span><a href="/">女孩都有浪漫的小情怀——浪漫的求婚词</a></li>
          <li><span><strong>4</strong></span><a href="/">Green绿色小清新的夏天-个人博客模板</a></li>
          <li><span><strong>5</strong></span><a href="/">女生清新个人博客网站模板</a></li>
          <li><span><strong>6</strong></span><a href="/">Wedding-婚礼主题、情人节网站模板</a></li>
          <li><span><strong>7</strong></span><a href="/">Column 三栏布局 个人网站模板</a></li>
          <li><span><strong>8</strong></span><a href="/">时间煮雨-个人网站模板</a></li>
          <li><span><strong>9</strong></span><a href="/">花气袭人是酒香—个人网站模板</a></li>
        </ol>
      </div>
     -->
	<!--
      <div class="clicks">
        <h2>热门点击</h2>
        <ol>
          <li><span><a href="/">慢生活</a></span><a href="/">有一种思念，是淡淡的幸福,一个心情一行文字</a></li>
          <li><span><a href="/">爱情美文</a></span><a href="/">励志人生-要做一个潇洒的女人</a></li>
          <li><span><a href="/">慢生活</a></span><a href="/">女孩都有浪漫的小情怀——浪漫的求婚词</a></li>
          <li><span><a href="/">博客模板</a></span><a href="/">Green绿色小清新的夏天-个人博客模板</a></li>
          <li><span><a href="/">女生个人博客</a></span><a href="/">女生清新个人博客网站模板</a></li>
          <li><span><a href="/">Wedding</a></span><a href="/">Wedding-婚礼主题、情人节网站模板</a></li>
          <li><span><a href="/">三栏布局</a></span><a href="/">Column 三栏布局 个人网站模板</a></li>
          <li><span><a href="/">个人网站模板</a></span><a href="/">时间煮雨-个人网站模板</a></li>
          <li><span><a href="/">古典风格</a></span><a href="/">花气袭人是酒香—个人网站模板</a></li>
        </ol>
      </div>
      -->
<!--      <div class="search">
        <form class="searchform" method="get" action="#">
          <input type="text" name="s" value="Search" onfocus="this.value=''" onblur="this.value='Search'">
        </form>
      </div> -->
      
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