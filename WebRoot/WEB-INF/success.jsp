<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
	 <script src="http://g.tbcdn.cn/mtb/lib-flexible/0.3.4/??flexible_css.js,flexible.js"></script>
<script src="https://cdn.bootcss.com/vue/2.4.2/vue.min.js"></script>
   <script src="https://cdn.jsdelivr.net/vue.resource/1.0.3/vue-resource.min.js"></script>
	<title>
		绑定成功
	</title>
	<style type="text/css">
	  .clera{
	  	margin-top:2rem;
		 text-align: center;
	     width: 8.6rem;
	     height:1.4rem;
	     line-height:1.4rem;
	     font-size: 0.5333rem;
	     color:#fff;
	     background: #ffab00;
	     border-radius: .5rem;
	     line-height: 1.4rem;
	     margin-left: 0.6rem;
		}
		.success{
			font-size: .55rem;
			color: #000;
			width: 10rem;
			text-align: center;
		}
		.page{
			width: 1.71rem;
			height: 1.71rem;
			margin-left:4.2rem;
			margin-top:1rem;
			margin-bottom: .8rem;
		}
	</style>
</head>

<body>
<div>
	<img src="./static/Page1.png" class="page">
	<p class="success">恭喜您绑定成功</p>
	<div class="clera" onclick="validation()" >返回</div>
</div>
</body>
<script type="text/javascript">

			function validation() {
                 window.location.href="MyInfoMenu"
			}

</script>
</html>