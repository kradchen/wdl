<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>绑定手机号</title>
   <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
   <script src="http://g.tbcdn.cn/mtb/lib-flexible/0.3.4/??flexible_css.js,flexible.js"></script>
  <script src="https://cdn.bootcss.com/vue/2.4.2/vue.min.js"></script>
   <script src="https://cdn.jsdelivr.net/vue.resource/1.0.3/vue-resource.min.js"></script>
   <style type="text/css">
    .login-banner{
      width:10rem;
      height:3.3rem;
      background:url(/wdl/static/2.png);
      background-size: 100% 100%;
      display: inline-block;
  }
   *{
     margin: 0;
     padding: 0;
  }
   .login-box-input{
   margin:0 auto;
     width:7.4rem;
     height:1.2rem;
     line-height: 1.2rem;
     border-radius: .4rem;
     border:0.02rem solid #ccc;
     margin:0.33333rem .8rem ;
     padding:0 0 0 0.66rem; 
 }
  .login-ing{
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
 .message{
  width: 9rem;
  height: .6rem;
  margin: 0 auto;
  text-align: left;
  color: #ccc;
  font-size: .4rem;
  
 }
   </style>
 
</head>
<body>
  <div id="app">
    <div class="login-banner">
      
    </div>
    <input class="login-box-input"
          type="number" v-model="phone"  placeholder="请输入手机验证码"
             onfocus="this.placeholder=''" onblur="this.placeholder='请输入手机验证码'">
              <p v-if="message!==''" class="message">{{message}}</p>
         <div class="login-ing" @click="validation()">
               确认绑定
         </div>
  </div>
</body>
<script>
  new Vue({
    el:'#app',
    data:{
      phone:'',
      number:0,
      url:'/wdl/reg/addUserPhone',
      message:''
    },
    methods:{
      validation:function() {
        let postdata={ 
                      "param":"{\"code\":\""+this.phone+"\"}",
                          "userid":"",
                          "token":"",
                          "devid":"",
                          "version":"",
                          "verifycode":"",
                          "appid":""
                      };
        let me=this;
        if(me.number==0){
           me.$http.post(me.url,postdata).then(data=>{
               
            if (data.body.body.ret == '1'){
                  window.location.href="GetBindingSuccess";
                  me.number++;
                  }
            if (data.body.body.ret == '0'){
               me.message=data.body.message
              }
           })
         }
      }
    }
  })
</script>
</html>