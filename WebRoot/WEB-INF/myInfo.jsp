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
		 		background: #f5f5f5;
		 		font-family:"Microsoft YaHei"! important; 
		 	}
		 	.box{
		 		display: inline-block;
		 		width: 8rem;
		 		background: #fff;
		 		margin:1rem 0 .6rem 1rem;
		 		/*box-shadow: 5px 2px 6px #BFBFBF;*/
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
		 		margin-top: .12rem;
		 		margin-left: .3rem;
		 		
		 	}
		 	.box-orcode{
		 		    width: 3.6rem;
				    height: 3.6rem;
				    display: block;
				    margin: 0 auto;
		 	}
		 	.box-b1{
		 		height: 2rem;
		 		display: inline-block;
		 		width: 100%;
		 		margin-top: .5rem;
		 	
		 	}
		 	.box-b2{
		 		width:100%;
		 		margin-top: .8rem;
		 		display: inline-block;
		 		margin:.8rem auto;
		 	}
		 	.box-b2-p{
		 		margin-top: .4rem;
			    font-size: .5rem;
			    color: #909090;
			    color: #f7a700;
			    text-align: center;
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
		 	.box-gz{
		 		width: 3rem;
		 		height: .8rem;
		 		overflow: hidden;
		 		display: inline-block;
		 		margin-top: .1rem;
		 		margin-left: .2rem;
		 		word-wrap: normal;
		 	}
		 	.box-p{
		 		width: 7.1rem;
		 		height: .8rem;
		 		border-bottom: 0.04rem dotted #979797;
		 		border-top: 0.04rem dotted #979797;
		 		margin: 0 auto;
		 		text-align: center;
		 		line-height: .8rem;
		 		color: #909090;
		 		margin-bottom: .2rem;
		 	}
		 	.box-infolist svg{
		 		width: .5rem;
		 		height: .5rem;
		 		float: left;

		 	}
		 	.box-infolist{
		 		margin-left: .7rem;
		 		margin-top: .2rem;
		 		display: inline-block;
		 	}
		 	.info-kh{
		 		display: inline-block;
			    width: 2rem;
			    color: #909090;
			    margin-left: .2rem;
		 	}
		 	.info-khname{
		 		color: #585858;
		 		margin-left:.2rem; 
		 	}
		 	.box-info{
		 		    margin-bottom: .5rem;
		 	}
		 	.footer{
		 		color: #909090;
		 		font-size: .5rem;
		 		width: 7rem;
		 		margin:0 auto;
		 		text-align: center;
		 	}
		 	.footer-p1{
		 		width: 3rem;
		 	}
		 	.line{
		 		width: .1rem;
		 		height: .03rem;
		 	}
		 	/*.qiu{
		 		width: .4rem;
			    height: .4rem;
			    background: #f2f2f2;
			    border-radius: .5rem;
			    position: absolute;
			    top: 11.6rem;
			    left: .78rem;
		 	}
		 	.yuan{
		 		width: .4rem;
			    height: .4rem;
			    background: #f2f2f2;
			    border-radius: .5rem;
			    position: absolute;
			    top: 11.6rem;
			    left: 8.78rem;
		 	}*/
	 </style>
</head>
<body>

	<div id="vessel">
		<div class="box">
			<div class="box-b1">
				<img src="${Headimgurl}" class="box-head admin">
				<!-- <img src="aa.jpg" class="box-head admin"> -->
				<!-- 	<p class="box-name ${userType}">${Nickname}</p> -->
				<p class="box-name ${userType}">${Nickname}</p>
				<!-- <p class="box-name ${userType}">${userPhone}</p> -->
				<p class="box-name ${userType}">${userPhone}</p>

				<span class="box-company">${userType}</span>
				${ExportCnt}
			</div>
			<div class="box-b2">
				<img src="./GetMyQRInfo" class="box-orcode">
				<p class="box-b2-p">长按图片  分享二维码</p>
			</div>
			<div class="box-info">
				<p class="box-p">客户经理信息</p>
				<div class="box-infolist">
					<svg t="1507624159359" class="icon" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1870" xmlns:xlink="http://www.w3.org/1999/xlink" width="32" height="32"><defs><style type="text/css"></style></defs><path d="M614.047971 562.249122 409.953053 562.249122c-191.061243 0-346.511034 153.064021-346.511034 341.257221l0 20.077327c0 100.415306 152.922718 100.415306 346.511034 100.415306l204.095942 0c185.892417 0 346.482364 0 346.511034-100.415306l0-20.077327C960.560029 715.342837 805.080544 562.249122 614.047971 562.249122zM900.693592 925.487166c0 4.815569-7.316019 21.148364-101.439241 29.154515-59.857221 5.092031-131.23986 5.092031-183.360224 5.092031L408.078227 959.733713c-62.848137 0-134.083329 0-191.279341-5.088959-84.409146-7.510567-93.521148-22.892126-93.521148-29.157587l0-20.445944c0-154.111507 127.761551-279.49036 284.801514-279.49036l207.8159 0c157.038939 0 284.80049 125.378853 284.80049 279.49036L900.69564 925.487166z" p-id="1871" fill="#909090"></path><path d="M501.790851 0c-151.701163 0-275.034193 121.570837-275.034193 271.029581 0 149.45772 123.33303 271.058251 275.034193 271.058251 151.644846 0 275.034193-121.598483 275.034193-271.058251C776.825044 121.570837 653.493038 0 501.790851 0zM501.791875 477.849192c-116.733766 0-211.704807-93.148436-211.704807-207.643878 0-114.47906 94.970017-207.614184 211.703783-207.614184 116.73479 0 211.705831 93.135124 211.705831 207.614184C713.496682 384.700756 618.526665 477.849192 501.791875 477.849192z" p-id="1872" fill="#909090"></path></svg>
					<span class="info-kh">客户经理</span>
					<span class="info-khname">${MySaller}</span>
				</div>
				<div class="box-infolist">
					<svg t="1507624203554" class="icon" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2689" xmlns:xlink="http://www.w3.org/1999/xlink" width="32" height="32"><defs><style type="text/css"></style></defs><path d="M737.231 992h-450.461c-66.461 0-118.153-55.385-118.153-118.153v-720c-0.001-66.463 51.691-121.847 118.152-121.847h450.461c66.461 0 118.153 55.385 118.153 118.153v720c0 66.462-51.692 121.847-118.153 121.847zM796.308 153.847c0-33.232-25.847-59.077-59.078-59.077h-450.461c-33.231 0-59.078 25.847-59.078 59.077v29.539h568.616v-29.539zM796.308 242.461h-568.616v480h568.616v-480zM796.308 781.539h-568.616v88.615c0 33.231 25.847 59.077 59.078 59.077h450.461c33.231 0 59.078-25.847 59.078-59.077v-88.615zM512 903.385c-25.847 0-44.308-18.461-44.308-44.308s18.462-44.307 44.308-44.307c25.847 0 44.308 18.461 44.308 44.307s-18.462 44.308-44.308 44.308zM512 903.385z" p-id="2690" fill="#909090"></path></svg>
					<span class="info-kh">联系方式:</span>
					<span class="info-khname">${MySallerPhone}</span>
				</div>
			</div>
		</div>
		<div class="footer">
			<span class="line">—</span> 
			<span class="footer-p1">亲,扫一下我的二维码</span>
			<span class="line">—</span>
			<p>会发现惊喜呦</p>
		</div>
		<!-- <div class="qiu">
			
		</div>
		<div class="yuan">
			
		</div> -->
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