<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/9
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>发表问题 编辑问题 公用</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="fly,layui,前端社区">
    <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fly/res/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fly/res/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fly/res/editor.md-master/css/editormd.css" />
    <script src="${pageContext.request.contextPath}/fly/res/jq.js"></script>
    <script src="${pageContext.request.contextPath}/fly/res/editor.md-master/editormd.js"></script>
    <script>
        $(function () {
            // 初始化markdown
            var testEditor = editormd({
                id: "test-editormd",
                height: 450,
                width: "100%",
                placeholder : "Markdown编辑器",
                path: "${pageContext.request.contextPath}/fly/res/editor.md-master/lib/",
                toolbarIcons: function () {
                    // Or return editormd.toolbarModes[name]; // full, simple, mini
                    // Using "||" set icons align right.
                    return ["undo", "redo", "|", "watch", "fullscreen", "preview"]
                },
                //toolbar  : false,             // 关闭工具栏
                codeFold: true,
                searchReplace: true,
                saveHTMLToTextarea: true,      // 保存 HTML 到 Textarea
                htmlDecode: "style,script,iframe|on*",            // 开启 HTML 标签解析，为了安全性，默认不开启
                emoji: true,
                taskList: true,
                tocm: true,          // Using [TOCM]
                tex: true,                      // 开启科学公式 TeX 语言支持，默认关闭
                //previewCodeHighlight : false,  // 关闭预览窗口的代码高亮，默认开启
                flowChart: true,                // 疑似 Sea.js与 Raphael.js 有冲突，必须先加载 Raphael.js ，Editor.md 才能在 Sea.js 下正常进行；
                sequenceDiagram: true,          // 同上
                //dialogLockScreen : false,      // 设置弹出层对话框不锁屏，全局通用，默认为 true
                //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为 true
                //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为 true
                //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为 0.1
                //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为 #fff
                imageUpload: true,
                imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                imageUploadURL: "{:url('api/uploader/uploadEditorImg?pic_type=10')}",
                onload: function () {
                    this.on('paste', function () {
                        console.log(1);
                    });
                },
                onpreviewing : function() {
                    this.watch();
                    console.log("onpreviewing =>", this, this.id, this.settings);
                    // on previewing you can custom css .editormd-preview-active
                },
                onpreviewed : function() {
                    console.log("onpreviewed =>", this, this.id, this.settings);
                    this.unwatch();
                }
            });

            /**
             * 上传图片
             */
            $("#test-editormd").on('paste', function (ev) {
                var data = ev.clipboardData;
                var items = (event.clipboardData || event.originalEvent.clipboardData).items;
                for (var index in items) {
                    var item = items[index];
                    if (item.kind === 'file') {
                        var blob = item.getAsFile();
                        var reader = new FileReader();
                        reader.onload = function (event) {
                            // 将剪切板中复制信息转换成一种base64格式的字符串
                            var base64 = event.target.result;
                            console.log(base64);
                            //ajax上传图片
                            $.ajax({
                                url : "${pageContext.request.contextPath}/article/uploadimg",
                                type : 'post',
                                data : {
                                    'base':base64
                                },
                                async : true,
                                dataType: 'json',
                                success : function (res) {
                                    console.log(res.path);
                                    if (res.code === 1) {
                                        //新一行的图片显示"upload/"+uuid+".jpg"
                                        testEditor.insertValue("\n![" + "image.png" + "](${pageContext.request.contextPath}/" + res.path + ")");
                                    }
                                },
                                error : function(){
                                    alert('图片上传错误');
                                }
                            });
                        }; // data url!
                        var url = reader.readAsDataURL(blob);
                    }
                }
            });

            // 飞吻数的验证
            $('#reform').submit(function () {
                let kissnum = '${login_user.kissnum}';
                if(kissnum < Number($('select[name=experience]').val())){
                    $('#kissmsg').html('飞吻数不够').css('color','red');
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

<div class="layui-container fly-marginTop">
    <div class="fly-panel" pad20 style="padding-top: 5px;">
        <!--<div class="fly-none">没有权限</div>-->
        <div class="layui-form layui-form-pane">
            <div class="layui-tab layui-tab-brief" lay-filter="user">
                <ul class="layui-tab-title">
                    <li class="layui-this">发表新帖<!-- 编辑帖子 --></li>
                </ul>
                <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                    <div class="layui-tab-item layui-show">
                        <form id="reform" action="${pageContext.request.contextPath}/article/add" method="post">
                            <div class="layui-row layui-col-space15 layui-form-item">
                                <div class="layui-col-md3">
                                    <label class="layui-form-label">所在专栏</label>
                                    <div class="layui-input-block">
                                        <select lay-verify="required" name="class" lay-filter="column">
                                            <option></option>
                                            <!-- 加载分类信息 -->
                                            <c:if test="${categoryList != null}">
                                                <!-- 普通用户分类信息 -->
                                                <c:if test="${login_user.authority == 0}" >
                                                    <c:forEach items="${categoryList}" var="cate" begin="0" end="3">
                                                        <option value="${cate.typeid}">${cate.typename}</option>
                                                    </c:forEach>
                                                </c:if>
                                                <!-- 管理员分类信息 -->
                                                <c:if test="${login_user.authority != 0}">
                                                    <c:forEach items="${categoryList}" var="cate" >
                                                        <option value="${cate.typeid}">${cate.typename}</option>
                                                    </c:forEach>
                                                </c:if>
                                            </c:if>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-col-md9">
                                    <label for="L_title" class="layui-form-label">标题</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="L_title" name="title" required lay-verify="required" autocomplete="off" class="layui-input">
                                        <!-- <input type="hidden" name="id" value="{{d.edit.id}}"> -->
                                    </div>
                                </div>
                            </div>
                            <div class="layui-row layui-col-space15 layui-form-item layui-hide" id="LAY_quiz">
                                <div class="layui-col-md3">
                                    <label class="layui-form-label">所属产品</label>
                                    <div class="layui-input-block">
                                        <select name="project">
                                            <option></option>
                                            <option value="layui">layui</option>
                                            <option value="独立版layer">独立版layer</option>
                                            <option value="独立版layDate">独立版layDate</option>
                                            <option value="LayIM">LayIM</option>
                                            <option value="Fly社区模板">Fly社区模板</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-col-md3">
                                    <label class="layui-form-label" for="L_version">版本号</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="L_version" value="" name="version" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6">
                                    <label class="layui-form-label" for="L_browser">浏览器</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="L_browser"  value="" name="browser" placeholder="浏览器名称及版本，如：IE 11" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div>
                            <!-- markdown编辑器 -->
                            <div class="editormd" id="test-editormd">
                                <textarea class="editormd-markdown-textarea" name="topic_markdown_content" id = "topic_markdown_content">${topic.topic_markdown_content}</textarea>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-inline">
                                    <label class="layui-form-label">悬赏飞吻</label>
                                    <div class="layui-input-inline" style="width: 190px;">
                                        <select name="experience">
                                            <option value="20">20</option>
                                            <option value="30">30</option>
                                            <option value="50">50</option>
                                            <option value="60">60</option>
                                            <option value="80">80</option>
                                        </select>
                                    </div>
                                    <div class="layui-form-mid layui-word-aux"><span id="kissmsg">发表后无法更改飞吻</span></div>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <button class="layui-btn">立即发布</button>
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

<script src="${pageContext.request.contextPath}/fly/res/layui/layui.js"></script>
<script>
    layui.cache.page = 'jie';
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
</script>

</body>
</html>