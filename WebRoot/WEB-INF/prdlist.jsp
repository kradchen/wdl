<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
	<title>精品线路</title>
	 <script src="http://g.tbcdn.cn/mtb/lib-flexible/0.3.4/??flexible_css.js,flexible.js"></script>
	 <script type="text/javascript" src="https://unpkg.com/vue/dist/vue.js"></script>
	 <script src="https://cdn.jsdelivr.net/vue.resource/1.0.3/vue-resource.min.js"></script>
</head>
<style type="text/css">
body{background:#eeeeee}
	.imgs{
	margin-bottom:2px;
	display:inline-block;
		width: 100%;
	}
</style>
<body>
	<div id="app">
		<a v-for="item in list" :href = " 'getProductDetail?uuid=' + item.prdUid"> <img  :src=" '/wdl/static/product/'+item.pageName+'/'+item.pageName+'.jpg' "  class="imgs"></a>
	</div>
</body>
<script type="text/javascript">
	new Vue({
		el:'#app',
		data:{
			list:'',
		},
		created(){
		
			let postdata ={ "param":"{}","userid":"","token":"","devid":"","version":"","verifycode":"","appid":""};
			 this.$http.post('/wdl/getProduct',postdata).then(data=>{
			 	console.log(data);
			 	this.list = data.body.body;
			 })
		}
	})
</script>
</html>