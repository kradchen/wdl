<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>我的页面</title>
	 <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
	 <script src="http://g.tbcdn.cn/mtb/lib-flexible/0.3.4/??flexible_css.js,flexible.js"></script>
	 <script typet="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	 <style type="text/css">
	 		.box-company{
	 			color: #000;
	 			text-align: center;
	 			border-radius: .3rem;
	 			font-size: .3rem;
	 			border: .01rem solid #000;
	 			padding:0 .2rem;
	 			height: .66rem;
	 			line-height: .66rem;
	 			float: left;
	 			margin-left: .3rem;
	 		}
		 	#vessel{
		 		width: 10rem;
		 		background: #f2f3f2;
		 		font-family:"Microsoft YaHei"! important; 
		 	}
		 	.box{
		 		display: inline-block;
		 		width: 8rem;
		 		height: 10rem;
		 		background: #fff;
		 		margin:1.5rem 0 .6rem 1rem;
		 	}
		 	.box-head{
		 		width: 2rem;
		 		height: 2rem;
		 		border-radius: 1rem;
		 		float: left;
		 		margin-left: .3rem;
		 	}
		 	.box-name{
		 		width: 5rem;
		 		font-size: .4rem;
		 		font-weight: 700;
		 		color: #ff6800;
		 		float: left;
		 		margin-top: .5rem;
		 		margin-left: .3rem;
		 		
		 	}
		 	.box-orcode{
		 		width: 4.7rem;
		 		height: 4.7rem;
		 		display: inline-block;
		 		margin-left: 1.65rem;
		 	}
		 	.box-b1{
		 		height: 2rem;
		 		display: inline-block;
		 		width: 100%;
		 		margin-top: .5rem;
		 	
		 	}
		 	.box-b2{
		 		width:100%;
		 		/*margin:.8rem 1.8rem;*/
		 		margin-top: .8rem;
		 		
		 		margin:.8rem auto;
		 	}
		 	.box-b2-p{
		 		margin-top: .2rem;
		 		font-size: .35rem;
		 		color: #909090;
		 		margin-left:1rem;
		 	}
		 	.share{
		 		width: 8rem;
		 		height:1rem;
		 		background: #f7a700;
		 		color: #fff;
		 		font-size: .5rem;
		 		margin:0 auto; 
		 		text-align: center;
		 		line-height: 1rem;
		 	}
		 	.admin{
		 		color: #d0021b;
		 	}
		 	.agent{
		 		color: #ff6800;
		 	}
		 	.seller{
		 		color: #7ed321;
		 	}
		 	.customer{
		 		color: #000;
		 	}
	 </style>
</head>
<body>

	<div id="vessel">
		<div class="box">
			<div class="box-b1">
				<img src="${Headimgurl}" class="box-head admin">
				<p class="box-name ${userType}">${Nickname}</p>
				<p class="box-name ${userType}">${userPhone}</p>
				
				<p class="box-company">${userType}</p>
				${ExportCnt}
			</div>
			<div class="box-b2">
				<img src="./GetMyQRInfo" class="box-orcode">
				<p class="box-b2-p">亲，扫一下我的二维码会发现有惊喜呦</p>
			</div>
		</div>
		${MySaller}
		${MySallerPhone}
		<div class="share" >
			点击右上角 分享二维码
		</div>
	</div>
</body>
<script type="text/javascript">
	var ss = document.documentElement.clientHeight;
	document.getElementById("vessel").style.height=ss+"px";
</script>
<script type="text/javascript">
	function weixinSendAppMessage(){
	    
}
	
</script>
</html>