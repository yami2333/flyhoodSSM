<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/7
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="fly,layui,前端社区">
    <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fly/res/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fly/res/css/global.css">
    <script src="${pageContext.request.contextPath}/fly/res/jq.js"></script>
    <script>
        $(function () {
            // 表示邮箱能否注册的开关变量
            let onOff = true;
            //人类验证
            function humanCheck() {
                // 随机生成2个数字
                let num1 = Math.floor(Math.random()*21);
                let num2 = Math.floor(Math.random()*21);
                $('#humanCheck').html(num1+"+"+num2+"="+"?")
                return num1 + num2;
            }
            let num = humanCheck();
            // 邮箱失去焦点查询是否可以作为注册邮箱
            $('#L_email').blur(function () {
                if($('#L_email').val() != null && $('#L_email').val().trim() != ''){
                    // 发送ajax到后台查询
                    $.ajax({
                       url : '${pageContext.request.contextPath}/user/emailCheck',
                       data : {
                           'email' : $('#L_email').val()
                       },
                       type: 'get',
                       dataType : 'json',
                       success : function (res) {
                            // 0代表后代没查到该邮箱，1表示查到了
                            if(res == 0){
                                $('#emailmsg').html('邮箱可以注册').css('color','green')
                                onOff = true;
                            }else{
                                $('#emailmsg').html('邮箱已被注册').css('color','red')
                                onOff = false;
                            }
                       } ,
                       error : function () {
                           alert('响应失败');
                       },
                       async : true
                    })
                }
            })

            // 表单提交时验证注册信息
            $('#regForm').submit(function () {
                $('#passmsg').html('6到16个字符').css('color','#999');
                $('#hcmsg').html('');
                // 两次密码是否一致
                if($('#L_pass').val() != $('#L_repass').val()){
                    $('#passmsg').html('两次密码不一致').css('color','red');
                    return false;
                }
                // 密码是否超出长度
                if($('#L_pass').val().length > 16 || $('#L_pass').val().length < 6){
                    $('#passmsg').html('密码长度必须在6-16之间').css('color','red');
                    return false;
                }
                // 人类验证
                if($('#L_vercode').val() != num){
                    $('#hcmsg').html('请您仔细斟酌在回答！');
                    return false;
                }
                // 判断onOff
                if(!onOff){
                    return false;
                }
            })
        })
    </script>
</head>
<body>
<div class="fly-header layui-bg-black">
    <div class="layui-container">
        <a class="fly-logo" href="/">
            <img src="${pageContext.request.contextPath}/fly/res/images/logo.png" alt="layui">
        </a>
        <ul class="layui-nav fly-nav layui-hide-xs">
            <li class="layui-nav-item layui-this">
                <a href="/"><i class="iconfont icon-jiaoliu"></i>交流</a>
            </li>
            <li class="layui-nav-item">
                <a href="../case/case.html"><i class="iconfont icon-iconmingxinganli"></i>案例</a>
            </li>
            <li class="layui-nav-item">
                <a href="http://www.layui.com/" target="_blank"><i class="iconfont icon-ui"></i>框架</a>
            </li>
        </ul>

        <ul class="layui-nav fly-nav-user">
            <!-- 未登入的状态 -->
            <li class="layui-nav-item">
                <a class="iconfont icon-touxiang layui-hide-xs" href="user/login.html"></a>
            </li>
            <li class="layui-nav-item">
                <a href="user/login.html">登入</a>
            </li>
            <li class="layui-nav-item">
                <a href="user/reg.html">注册</a>
            </li>
            <li class="layui-nav-item layui-hide-xs">
                <a href="/app/qq/" onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})" title="QQ登入" class="iconfont icon-qq"></a>
            </li>
            <li class="layui-nav-item layui-hide-xs">
                <a href="/app/weibo/" onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})" title="微博登入" class="iconfont icon-weibo"></a>
            </li>
        </ul>
    </div>
</div>

<div class="layui-container fly-marginTop">
    <div class="fly-panel fly-panel-user" pad20>
        <div class="layui-tab layui-tab-brief" lay-filter="user">
            <ul class="layui-tab-title">
                <li><a href="login.html">登入</a></li>
                <li class="layui-this">注册</li>
            </ul>
            <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <div class="layui-form layui-form-pane">
                        <form method="post" action="${pageContext.request.contextPath}/user/reg" id="regForm">
                            <div class="layui-form-item">
                                <label for="L_email" class="layui-form-label">邮箱</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_email" name="email" required lay-verify="email" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux"><span id="emailmsg">将会成为您唯一的登入名</span></div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_username" class="layui-form-label">昵称</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_username" name="username" required lay-verify="required" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_pass" class="layui-form-label">密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="L_pass" name="pass" required lay-verify="required" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux"><span id="passmsg">6到16个字符</span></div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_repass" class="layui-form-label">确认密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="L_repass" name="repass" required lay-verify="required" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_vercode" class="layui-form-label">人类验证</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_vercode" name="vercode" required lay-verify="required" placeholder="请回答后面的问题" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid">
                                    <span id="humanCheck" style="color: #c00;"></span>
                                    <span id="hcmsg" style="color: #c00;margin-left: 10px;"></span>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <button class="layui-btn">立即注册</button>
                            </div>
                            <div class="layui-form-item fly-form-app">
                                <span>或者直接使用社交账号快捷注册</span>
                                <a href="" onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})" class="iconfont icon-qq" title="QQ登入"></a>
                                <a href="" onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})" class="iconfont icon-weibo" title="微博登入"></a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<div class="fly-footer">
    <p><a href="http://fly.layui.com/" target="_blank">Fly社区</a> 2017 &copy; <a href="http://www.layui.com/" target="_blank">layui.com 出品</a></p>
    <p>
        <a href="http://fly.layui.com/jie/3147/" target="_blank">付费计划</a>
        <a href="http://www.layui.com/template/fly/" target="_blank">获取Fly社区模版</a>
        <a href="http://fly.layui.com/jie/2461/" target="_blank">微信公众号</a>
    </p>
</div>

<%--<script src="${pageContext.request.contextPath}/fly/res/layui/layui.js"></script>
<script>
    layui.cache.page = 'user';
    layui.cache.user = {
        username: '游客'
        ,uid: -1
        ,avatar: '${pageContext.request.contextPath}/fly/res/images/avatar/00.jpg'
        ,experience: 83
        ,sex: '男'
    };
    layui.config({
        version: "3.0.0"
        ,base: '${pageContext.request.contextPath}/fly/res/mods/'
    }).extend({
        fly: 'index'
    }).use('fly');
</script>--%>

</body>
</html>
