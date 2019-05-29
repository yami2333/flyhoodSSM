<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/14
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Fly Template v3.0，基于 layui 的极简社区页面模版</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="fly,layui,前端社区">
    <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fly/res/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fly/res/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fly/res/editor.md-master/css/editormd.css">

    <style>
        .editormd-preview-container{padding:0;}
        .detail-content{
            padding-top: 20px;
        }
        .detail-content *{
            max-width: 100%;
            height: auto;
        }
    </style>
    <script src="${pageContext.request.contextPath}/fly/res/jq.js"></script>
    <script src="${pageContext.request.contextPath}/fly/res/template-web.js"></script>
    <script>
        $(function () {
            $('pre').addClass('prettyprint linenums prettyprinted');
            $('pre ol').addClass('linenums');
            $('pre .linenums li').addClass('L0');
            
            
            $('#replybtn').click(function () {
                let user = '${login_user}';
                console.log(user);
                if(user != null && user != ''){
                    let myTime = new Date();
                    let json = {
                        'headurl' : '${login_user.headurl}',
                        'nickname' : '${login_user.nickname}',
                        'releasetime' : myTime.getFullYear()+'-'+(myTime.getMonth()+1)+'-'+myTime.getDate(),
                        'uid' : '${login_user.uid}',
                        'goodnum' : 0,
                        'authority' : '${login_user.authority}',
                        'replycontent' : $('#L_content').val()
                    }
                    let html = template('replymodel',json);
                    $('#jieda').append(html);
                    // 发送ajax
                    $.post(
                        '${pageContext.request.contextPath}/article/reply',
                        {
                            'uid' : '${login_user.uid}',
                            'aid' : '${articleDetail.aid}',
                            'commcontent' : $('#L_content').val()
                        },
                        function (res) {
                            if(res == 0){
                                alert('对不起，评论失败')
                            }else{
                                alert('评论成功')
                            }
                        },
                        'json'
                    )
                }
                else{
                    window.location.href = '${pageContext.request.contextPath}/go/login'
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
            <!--
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
             -->

            <!-- 登入后的状态 -->
            <li class="layui-nav-item">
                <a class="fly-nav-avatar" href="javascript:;">
                    <cite class="layui-hide-xs">贤心</cite>
                    <i class="iconfont icon-renzheng layui-hide-xs" title="认证信息：layui 作者"></i>
                    <i class="layui-badge fly-badge-vip layui-hide-xs">VIP3</i>
                    <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg">
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="../user/set.html"><i class="layui-icon">&#xe620;</i>基本设置</a></dd>
                    <dd><a href="../user/message.html"><i class="iconfont icon-tongzhi" style="top: 4px;"></i>我的消息</a></dd>
                    <dd><a href="../user/home.html"><i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>我的主页</a></dd>
                    <hr style="margin: 5px 0;">
                    <dd><a href="" style="text-align: center;">退出</a></dd>
                </dl>
            </li>
        </ul>
    </div>
</div>

<div class="layui-hide-xs">
    <div class="fly-panel fly-column">
        <div class="layui-container">
            <ul class="layui-clear">
                <li class="layui-hide-xs"><a href="/">首页</a></li>
                <li class="layui-this"><a href="">提问</a></li>
                <li><a href="">分享<span class="layui-badge-dot"></span></a></li>
                <li><a href="">讨论</a></li>
                <li><a href="">建议</a></li>
                <li><a href="">公告</a></li>
                <li><a href="">动态</a></li>
                <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><span class="fly-mid"></span></li>

                <!-- 用户登入后显示 -->
                <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="../user/index.html">我发表的贴</a></li>
                <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="../user/index.html#collection">我收藏的贴</a></li>
            </ul>

            <div class="fly-column-right layui-hide-xs">
                <span class="fly-search"><i class="layui-icon"></i></span>
                <a href="add.html" class="layui-btn">发表新帖</a>
            </div>
            <div class="layui-hide-sm layui-show-xs-block" style="margin-top: -10px; padding-bottom: 10px; text-align: center;">
                <a href="add.html" class="layui-btn">发表新帖</a>
            </div>
        </div>
    </div>
</div>

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8 content detail">
            <div class="fly-panel detail-box">
                <h1>${articleDetail.title}</h1>
                <div class="fly-detail-info">
                    <!-- <span class="layui-badge">审核中</span> -->
                    <span class="layui-badge layui-bg-green fly-detail-column">${articleDetail.typename}</span>
                    <c:if test="${articleDetail.isend == 0}">
                        <span class="layui-badge" style="background-color: #999;">未结</span>
                    </c:if>
                    <c:if test="${articleDetail.isend != 0}">
                        <span class="layui-badge" style="background-color: #5FB878;">已结</span>
                    </c:if>
                    <c:if test="${articleDetail.istop != 0}">
                        <span class="layui-badge layui-bg-black">置顶</span>
                    </c:if>
                    <c:if test="${articleDetail.iscream != 0}">
                        <span class="layui-badge layui-bg-red">精帖</span>
                    </c:if>

                    <div class="fly-admin-box" data-id="123">
                        <c:if test="${login_user != null}">
                            <%--如果是管理员或者是文章作者--%>
                            <c:if test="${login_user.authority != 0 || articleDetail.uid == login_user.uid}">
                                <span class="layui-btn layui-btn-xs jie-admin" type="del">删除</span>
                            </c:if>
                            <c:if test="${login_user.authority != 0 && articleDetail.istop == 0}">
                                <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="1">置顶</span>
                            </c:if>
                            <c:if test="${login_user.authority != 0 && articleDetail.istop != 0}">
                                <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="0" style="background-color:#ccc;">取消置顶</span>
                            </c:if>
                            <c:if test="${login_user.authority != 0 && articleDetail.iscream != 0}">
                                <span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="0" style="background-color:#ccc;">取消加精</span>
                            </c:if>
                            <c:if test="${login_user.authority != 0 && articleDetail.iscream == 0}">
                                <span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="1">加精</span>
                            </c:if>
                        </c:if>
                    </div>
                    <span class="fly-list-nums"> 
            <a href="#comment"><i class="iconfont" title="回答">&#xe60c;</i> ${articleDetail.commnum}</a>
            <i class="iconfont" title="人气">&#xe60b;</i> ${articleDetail.viewnum}
          </span>
                </div>
                <div class="detail-about">
                    <a class="fly-avatar" href="../user/home.html">
                        <img src="${pageContext.request.contextPath}/fly/res/images/avatar/${articleDetail.headurl}" alt="贤心">
                    </a>
                    <div class="fly-detail-user">
                        <a href="../user/home.html" class="fly-link">
                            <cite>${articleDetail.nickname}</cite>
                            <i class="iconfont icon-renzheng" title="认证信息：{{ rows.user.approve }}"></i>
                            <i class="layui-badge fly-badge-vip">VIP3</i>
                        </a>
                        <span>${articleDetail.releasetime}</span>
                    </div>
                    <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
                        <span style="padding-right: 10px; color: #FF7200">悬赏：${articleDetail.paykiss}</span>
                        <c:if test="${articleDetail.uid == login_user.uid}">
                            <span class="layui-btn layui-btn-xs jie-admin" type="edit"><a href="add.html">编辑此贴</a></span>
                        </c:if>
                    </div>
                </div>
                <%--文章内容--%>
                <div class="detail-content markdown-body editormd-preview-container">
                    ${articleDetail.hmcontent}
                </div>
            </div>

            <div class="fly-panel detail-box" id="flyReply">
                <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
                    <legend>回帖</legend>
                </fieldset>
                <%--评论列表--%>
                <script id="replymodel" type="text/html">
                    <li data-id="111" class="jieda-daan">
                        <a name="item-1111111111"></a>
                        <div class="detail-about detail-about-reply">
                            <a class="fly-avatar" href="">
                                <img src="${pageContext.request.contextPath}/fly/res/images/avatar/{{headurl}}" alt=" ">
                            </a>
                            <div class="fly-detail-user">
                                <a href="" class="fly-link">
                                    <cite>{{nickname}}</cite>
                                    <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                                    <i class="layui-badge fly-badge-vip">VIP3</i>
                                </a>
                                {{if uid == ${articleDetail.uid}}}
                                <span>(楼主)</span>
                                {{/if}}
                                {{if authority != 0}}
                                <span style="color:#5FB878">(管理员)</span>
                                {{/if}}
                                <!--
                                <span style="color:#5FB878">(管理员)</span>
                                <span style="color:#FF9E3F">（社区之光）</span>
                                <span style="color:#999">（该号已被封）</span>
                                -->
                            </div>

                            <div class="detail-hits">
                                <span>{{releasetime}}</span>
                            </div>

                            <%--                            <i class="iconfont icon-caina" title="最佳答案"></i>--%>
                        </div>
                        <div class="detail-body jieda-body photos">
                            {{replycontent}}
                        </div>
                        <div class="jieda-reply">
              <span class="jieda-zan zanok" type="zan">
                <i class="iconfont icon-zan"></i>
                <em>{{goodnum}}</em>
              </span>
                            <span type="reply">
                <i class="iconfont icon-svgmoban53"></i>
                回复
              </span>
                            <div class="jieda-admin">
                                <span type="edit">编辑</span>
                                <span type="del">删除</span>
                                <!-- <span class="jieda-accept" type="accept">采纳</span> -->
                            </div>
                        </div>
                    </li>
                </script>

                <ul class="jieda" id="jieda">
                    <c:if test="${commList != null}">
                        <c:forEach items="${commList}" var="comm">
                            <li data-id="111" class="jieda-daan">
                                <a name="item-1111111111"></a>
                                <div class="detail-about detail-about-reply">
                                    <a class="fly-avatar" href="">
                                        <img src="${pageContext.request.contextPath}/fly/res/images/avatar/${comm.headurl}" alt=" ">
                                    </a>
                                    <div class="fly-detail-user">
                                        <a href="" class="fly-link">
                                            <cite>${comm.nickname}</cite>
                                            <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                                            <i class="layui-badge fly-badge-vip">VIP3</i>
                                        </a>
                                        <c:if test="${comm.uid == articleDetail.uid}">
                                            <span>(楼主)</span>
                                        </c:if>
                                        <c:if test="${comm.authority != 0}">
                                            <span style="color:#5FB878">(管理员)</span>
                                        </c:if>
                                        <!--
                                        <span style="color:#5FB878">(管理员)</span>
                                        <span style="color:#FF9E3F">（社区之光）</span>
                                        <span style="color:#999">（该号已被封）</span>
                                        -->
                                    </div>

                                    <div class="detail-hits">
                                        <span>${comm.commtime}</span>
                                    </div>
                                    <c:if test="${comm.isaccept != 0}">
                                        <i class="iconfont icon-caina" title="最佳答案"></i>
                                    </c:if>
                                </div>
                                <div class="detail-body jieda-body photos">
                                        ${comm.commcontent}
                                </div>
                                <div class="jieda-reply">
                                  <span class="jieda-zan zanok" type="zan">
                                    <i class="iconfont icon-zan"></i>
                                    <em>${comm.goodnum}</em>
                                  </span>
                                                        <span type="reply">
                                    <i class="iconfont icon-svgmoban53"></i>
                                    回复
                                  </span>
                                    <div class="jieda-admin">
                                        <c:if test="${login_user != null}">
                                            <c:if test="${ login_user.authority != 0 || login_user.uid == comm.uid}">
                                                <span type="edit">编辑</span>
                                                <span type="del">删除</span>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${login_user != null}">
                                            <c:if test="${login_user.uid != comm.uid && comm.isaccept == 0}">
                                                <span class="jieda-accept" type="accept">采纳</span>
                                            </c:if>
                                        </c:if>
                                    </div>
                                </div>
                                <%--回复框--%>
                            </li>
                        </c:forEach>
                    </c:if>
                </ul>

                <div class="layui-form layui-form-pane">
                    <%--主评论--%>
                    <form action="#" method="post">
                        <div class="layui-form-item layui-form-text">
                            <a name="comment"></a>
                            <div class="layui-input-block">
                                <textarea id="L_content" name="content" required lay-verify="required" placeholder="请输入内容"  class="layui-textarea fly-editor" style="height: 150px;"></textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <input type="hidden" name="jid" value="123">
                            <button id="replybtn" class="layui-btn" type="button">提交回复</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <dl class="fly-panel fly-list-one">
                <dt class="fly-panel-title">本周热议</dt>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>

                <!-- 无数据时 -->
                <!--
                <div class="fly-none">没有相关数据</div>
                -->
            </dl>

            <div class="fly-panel">
                <div class="fly-panel-title">
                    这里可作为广告区域
                </div>
                <div class="fly-panel-main">
                    <a href="http://layim.layui.com/?from=fly" target="_blank" class="fly-zanzhu" time-limit="2017.09.25-2099.01.01" style="background-color: #5FB878;">LayIM 3.0 - layui 旗舰之作</a>
                </div>
            </div>

            <div class="fly-panel" style="padding: 20px 0; text-align: center;">
                <img src="${pageContext.request.contextPath}/fly/res/images/weixin.jpg" style="max-width: 100%;" alt="layui">
                <p style="position: relative; color: #666;">微信扫码关注 layui 公众号</p>
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
</body>
</html>