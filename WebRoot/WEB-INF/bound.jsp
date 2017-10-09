<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
 <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
   <script src="http://g.tbcdn.cn/mtb/lib-flexible/0.3.4/??flexible_css.js,flexible.js"></script>
<script src="https://cdn.bootcss.com/vue/2.4.2/vue.min.js"></script>
   <script src="https://cdn.jsdelivr.net/vue.resource/1.0.3/vue-resource.min.js"></script>
</head>
<style type="text/css">
  .login-g{
  width: .4rem;
  height: .4rem;
  border-radius: .3rem;
  text-align: center;
  color: #f5a623;
  font-size: .3rem;
  border:0.01rem solid #F5A623;
  display: inline-block;
  float: left;
  margin-right: .1rem;
}
.login-verify{
  color: #F5A623;
  font-size: 0.32rem;
  width: 8rem;
  text-align: left;
  height: 0.5rem;
  line-height: 0.5rem;
  margin-left: 1.26rem;
  margin-top: .1rem;
}
input::-webkit-input-placeholder{
  font-size: 0.35rem;

}
.clera-phone{
   color: #999;
    font-size: 14px;
    position: absolute;
    top: .6rem;
    left: 7.5rem;
    border: .03rem solid #999;
    border-radius: .4rem;
    width: .6rem;
    height: .6rem;
    line-height: .6rem;
    text-align: center;
}
.again-yan{
    color: #999999;
    border:.026rem solid #999999;
    font-size: 0.32rem;
    border-radius: .4rem;
    border-radius: .4rem;
    position: absolute;
    width:2.706rem;
    height:0.813rem;
    line-height:0.813rem;
    top:-.4rem;
    left: -2.1rem;
}
.login-password{
    display: inline-block;
    position: absolute;
    width: .6rem;
    top:2.4rem;
    left: .3rem; 
}
.login-admin{
    display: inline-block;
    position: absolute;
    width: .6rem;
    top:.58rem;
    left: .3rem;
}
.yan-zheng{
    font-family: 'MicrosoftYaHei';
    border:.026rem solid #2b61f2;
    font-size:0.32rem;
    border-radius: .4rem;
    position: absolute;
    width:2.706rem;
    height:0.813rem;
    line-height:0.813rem;
    top:-.45rem;
    left: -1.9rem;
    letter-spacing:0.06rem;
}
.login-banner{
    width:10rem;
    height:3.3rem;
    background:url(./static/2.png);
    background-size: 100% 100%;
    display: inline-block;
}
 *{
     margin: 0;
     padding: 0;
 }
.login-box{
    position: relative;
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
 .login-box-yan{
     color: #2b61f2;
     background: #fff;
     border:0;
     position: relative;
     z-index: 1;
     border-radius: .2rem;
     float: right;
     margin:-.9rem 1rem 0 1rem;
     padding: .1rem;
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
  input,button{outline:none;}
 .login-box-tr{
     border: 0px;
     outline: none;

 }
 
.login-box{
    width: 9rem;
    display: inline-block;
    margin:0.1333rem auto ;
}
  .login-page{
      width: 10rem;
      /*background:url(../assets/bg.jpg);*/
      background-size: 100% 100%;
  }
  .head{
    display: inline-block;
    width: 2rem;
    height: 2rem;
    margin-top:.5rem;
    margin-left:.5rem;
  }
  .head-p{
    color: #fff;
    font-size: .4rem;
    position: absolute;
    top: 1rem;
    left:3rem;
  }
  .head-p2{
    color: #fff;
    font-size: .4rem;
    position: absolute;
    top: 1.5rem;
    left:3rem;
  }
</style>
<body>
<div id="app">
    <div class="login-page" id="height">
        <div class="login-banner">
            <img src="${Headimgurl}" class="head">
            <span class="head-p">${ Nickname }</span>
            <span class="head-p2"></span>
        </div>
        <div class="login-verify">
           <span class="login-g" v-if="verify==false">!</span>
           <p v-if="verify==false">请输入正确的手机号和验证码</p> 
        </div>
        <!-- 登录功能区 -->
        <div class="login-page-login">
            <!-- 登录功能盒子 -->
            <div class="login-box">
                <tr class="login-box-tr">
                    <td>
                        <!-- 手机号码输入区 -->
                        <input class="login-box-input"
                         placeholder="请输入手机号" id="phone" 
                         type="number" v-model="phone"
                         onfocus="this.placeholder=''" onblur="this.placeholder='请输入手机号'">
                            <!-- <img src="../assets/admin.png" class="login-admin"> -->
                            <span class="clera-phone"
                            v-if="phone!=''" @click="clear()">X</span>
                        </input>
                    </td>
                </tr>
                <tr>
                    <!-- <td class="login-box-code"></td> -->
                    <td>
                        <!-- 验证码输入区 -->
                        <input class="login-box-input" type="number" v-model="yan"
                         placeholder="请输入右图验证码"onfocus="this.placeholder=''" onblur="this.placeholder='请输入右图验证码'"> 
                            <!-- <img src="../assets/password.png" class="login-password"> -->
                        </input>
                       
                    </td>
                </tr>
                <!-- 验证码 -->
                <button class="login-box-yan"
                v-on:click="validation">
                    <span class="yan-zheng" 
                    v-if="!sendMsgDisabled">
                      {{zheng}}
                    </span>
                </button>
            </div>
            <div class="login-ing" @click="validation()">
               点击发送手机验证码
            </div>
        </div>
    </div>
</div>

<script>
new Vue({
  el: '#app',
  data: {
    time: 60, 
          sendMsgDisabled: false,
          yan:'',
          zheng:'',
          phone:'',
          phones:'',
          code:'',
          number:0,
           verify:true,
          height:'',
          getdata:'{"body":null,"success":"true","message":null,"tag":null}',
           // url:'/reg/addUserInfo',
          urlSMS:'/wdl/reg/sendVerificationSMS'
  },
  methods:{
          //获取验证码
          validation:function(){

              let me = this;
              var phone = document.getElementById("phone").value;
                  //  判断手机号是否合法
                  // 不合法
              if(!(/^1[3456789]\d{9}$/.test(phone))){ 
                  // alert("手机号错误"); 
                  me.verify=false;
                   return false;
                }
                if(me.zheng!==Number(me.yan)){ 
                 console.log(22)
                  me.verify=false;
                   return false;
                }  
                // 合法 
               else{
                  let me =this;
                  me.phones = phone;
                  me.verify=true;
                  me.sendMsgDisabled = true;
                  var postdata={ 
                          "param":"{\"phone\":\""+me.phones+"\"}",
                          "userid":"",
                          "token":"",
                          "devid":"",
                          "version":"",
                          "verifycode":"",
                          "appid":""
                      };
                  }

                  if(me.number==0){
                  this.$http.post(this.urlSMS,postdata).then(function(data){
                      let json=data;
                      console.log(data);
                      if (data.body.body.ret == '1')
                      {
                      window.location.href="GetBindingPhone";
                      me.number++;
                      }
                  },function(response){
                       console.log(response);
                  })
              }
          },
          
         
          //清空手机号
          clear:function(){
              this.phone =''
          }
          

      },
      
       mounted:function(){     
            document.getElementById("height").style.height=this.height+"px";
            document.title = '绑定手机';
            this.zheng=Math.floor(Math.random()*9000) + 1000;
       }
})
</script>
</body>
</html>