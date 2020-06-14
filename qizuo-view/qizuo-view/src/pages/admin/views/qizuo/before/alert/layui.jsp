<%@ include file="../../../frame/base_tags.jsp"%>
<!-- 前端插件 -->
<div class="block-area">
    <div class="m-b-10 col-lg-3 text-center">
        <h3 class="block-title m-b-5" onclick="model()">模态框</h3>
    </div>

    <div class="m-b-10 col-lg-3 text-center">
        <h3 class="block-title m-b-5" onclick="confirm()">询问</h3>
    </div>

    <div class="m-b-10 col-lg-3 text-center">
        <h3 class="block-title m-b-5" onclick="input()">输入</h3>
    </div>

    <div class="m-b-10 col-lg-3 text-center">
        <h3 class="block-title m-b-5" onclick="load()">加载</h3>
    </div>

    <div class="m-b-10 col-lg-3 text-center">
        <h3 class="block-title m-b-5" onclick="msg()">提示</h3>
    </div>

    <div class="m-b-10 col-lg-3 text-center">
        <h3 class="block-title m-b-5" onclick="tips()">tips</h3>
    </div>

    <div class="m-b-10 col-lg-3 text-center">
        <h3 class="block-title m-b-5" onclick="photo()">相册</h3>
    </div>

    <div class="m-b-10 col-lg-3 text-center">
        <h3 class="block-title m-b-5" onclick="tab()">tab</h3>
    </div>

</div>

<!-- 执行js -->
<script>
    globalJs(function(){
        function alert() {
            /** ***************基本使用*************** */
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.open({
                    id: '',//设定该值后，弹出窗只允许同时弹出一个
                    type: 1,//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                    title: '',//title: ['文本', 'font-size:18px;'] title: false
                    skin: '',//主题样式，该样式指的是外部边框样式
                    icon: '',//图标0-6
                    time: 5,//自动关闭秒数

                    area: ['500px', '300px'],//宽高
                    maxWidth: '',//最大宽度
                    maxHeight: '',//最大高度
                    offset: '',//坐标
                    /**
                     *   offset: 'auto'    默认坐标，即垂直水平居中
                     *   offset: '100px'    只定义top坐标，水平保持居中
                     *   offset: ['100px', '50px']    同时定义top、left坐标
                     *   offset: 't'    快捷设置顶部坐标
                     *   offset: 'r'    快捷设置右边缘坐标
                     *   offset: 'b'    快捷设置底部坐标
                     *   offset: 'l'    快捷设置左边缘坐标
                     *   offset: 'lt'    快捷设置左上角
                     *   offset: 'lb'    快捷设置左下角
                     *   offset: 'rt'    快捷设置右上角
                     *   offset: 'rb'    快捷设置右下角
                     */

                    maxmin: true,//是否显示最大化最小化按钮 对type:1和type:2有效
                    resize: false,//是否允许拉伸
                    move: false,//是否允许拖动 move: '.mine-move'只有那个区域能拖动
                    moveOut: true,//是否允许拖动的窗口外面
                    fixed: false,//是否随着鼠标滚动而滚动
                    scrollbar: false,//是否允许浏览器滚动
                    zIndex: '',//层叠顺序

                    btn: '',//按钮 btn: ['按钮1', '按钮2', '按钮3', …] 按钮1的回调是yes，而从按钮2开始，则回调为btn2: function(){}，以此类推
                    btnAlign: '',//按钮排列
                    /**
                     * btnAlign: 'l'    按钮左对齐
                     * btnAlign: 'c'    按钮居中对齐
                     * btnAlign: 'r'    按钮右对齐。默认值，不用设置
                     */
                    closeBtn: 0,//右上角关闭按钮 0-2 1和2是不同风格，0是不显示

                    shade: 0.6,//遮光罩 shade: [0.8, '#393D49'] shade:0
                    shadeClose: false,//是否点击遮光罩自动关闭

                    anim: '',//弹出动画 -1-6 -1则是不带动画
                    isOutAnim: false,//默认关闭弹出窗的时候会有一个过渡动画

                    tips: '',//tips私有参数 tips: [1, '#c00'] 1234四个方向
                    tipsMore: true,//是否同时允许多个tips

                    content: '<div></div>', //url或者html内容
                    resizing: function () {//拉伸时候触发的函数

                    },
                    moveEnd: function (layero) {//move结束触发的函数

                    },
                    success: function (layero, index) {//当前层创建完毕立即执行的函数(layero代表当前层,index代表当前层索引)

                    },
                    yes: function (index, layero) {//确定按钮触发的函数(或者说第一个btn触发的函数)

                    },
                    cancel: function (index, layero) {//右上角关闭按钮触发函数

                    },
                    end: function () {//弹出窗被销毁后触发的函数

                    },
                    full: function () {//最大化触发函数

                    },
                    min: function () {//最小化触发函数

                    },
                    restore: function () {//还原触发函数

                    }
                });

            });

            /** ******************open延伸出来的小工具****************** */
            //弹出框(中间可以添加各种参数)
            layer.alert('加了个图标', {icon: 1},function(){

            });

            //询问框(中间可以添加各种参数)
            layer.confirm('is not?', {icon: 3, title:'提示'}, function(index){

            });

            //提示框(默认三秒消失)
            layer.msg('is not?', {icon: 3, title:'提示'}, function(index){

            });

            //加载(即等待;默认是不会关闭的;0-2三种图标)
            layer.load(1);

            //tips层(自动定位)
            layer.tips('在上面', '#id', {
                tips: 1
            });

            //输入层
            layer.prompt({
                formType: 2,//输入框类型，支持0（文本）默认1（密码）2（多行文本）
                value: '初始值',//初始时的值，默认空字符
                title: '请输入值',//可输入文本的最大长度，默认500
                area: ['800px', '350px'] //自定义文本域宽高
            }, function(value, index, elem){
                alert(value); //得到value
                layer.close(index);
            });

            //tab层
            layer.tab({
                area: ['600px', '300px'],
                tab: [{
                    title: 'TAB1',
                    content: '内容1'
                }, {
                    title: 'TAB2',
                    content: '内容2'
                }, {
                    title: 'TAB3',
                    content: '内容3'
                }]
            });

            //相册层
            //json方式
            var json={
                "title": "", //相册标题
                "id": 123, //相册id
                "start": 0, //初始显示的图片序号，默认0
                "data": [   //相册包含的图片，数组格式
                    {
                        "alt": "图片名",
                        "pid": 666, //图片id
                        "src": "", //原图地址
                        "thumb": "" //缩略图地址
                    }
                ]
            }
            layer.photos({
                photos: json
                ,anim: 5, //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
                tab: function(pic, layero){
                    console.log(pic) //当前图片的一些信息
                }
            });
            //页面获取图片
            /*<div id="layer-photos-demo" class="layer-photos-demo">
            <img layer-pid="图片id，可以不写" layer-src="大图地址" src="缩略图" alt="图片名">
            <img layer-pid="图片id，可以不写" layer-src="大图地址" src="缩略图" alt="图片名">
            </div>*/
            layer.photos({
                photos: '#layer-photos-demo'
                ,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
            });

            //关闭
            layer.close(index);//关闭指定层
            layer.close(layer.index);//关闭最新层
            //当你在iframe页面关闭自身时
            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            parent.layer.close(index); //再执行关闭
            layer.closeAll(); //疯狂模式，关闭所有层
            layer.closeAll('dialog'); //关闭信息框

            /** ****************js操纵层****************** */
            //加载文件和默认参数配置(path作用在于寻找相应的css样式等;如果采用layui加载layer,无需设置path,前置工作都是自动完成。)
            //<script src="?a.js&layer.js" merge="true">这样的话，layer就不会去自动去获取路径，但你需要通过以下方式来完成初始化的配置
            layer.config({
                path: '' //layer.js所在的目录，可以是绝对目录，也可以是相对目录
            });

            //相当于$(function(){})(layer内置了轻量级加载器，所以你根本不需要单独引入css等文件,但是加载也需要相应的时间)
            layer.ready(function(){
                layer.msg('很高兴一开场就见到你');
            });

            //重新给指定层设定样式
            layer.style(index, {
                width: '1000px',
                top: '10px'
            });

            //改变标题
            layer.title('标题变了', index);

            //获取iframe页的DOM
            layer.getChildFrame(selector, index);

            //反过来获取iframe索引
            layer.getFrameIndex("");

            //调用该方法时，iframe层的高度会重新进行适应
            layer.iframeAuto(index)

            //重置特定iframe url
            layer.iframeSrc(index, 'http://sentsin.com')

            //置顶当前窗口
            layer.setTop(layero)

            //手工执行最大小化
            layer.full();layer.min();layer.restore();

        }

    })

    /** ********************************************** */

    /** 示例 */
    function model(){
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.open({
                //id:'',//设定该值后，弹出窗只允许同时弹出一个
                type: 1,//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                title:'model',//title: ['文本', 'font-size:18px;'] title: false
                //skin:'',//主题样式，该样式指的是外部边框样式
                icon:'0',//图标0-6
                time:5000,//自动关闭秒数

                area: ['500px', '300px'],//宽高
                //maxWidth:'',//最大宽度
                //maxHeight:'',//最大高度
                //offset:'',//坐标
                /**
                 *   offset: 'auto' 	默认坐标，即垂直水平居中
                 *   offset: '100px' 	只定义top坐标，水平保持居中
                 *   offset: ['100px', '50px'] 	同时定义top、left坐标
                 *   offset: 't' 	快捷设置顶部坐标
                 *   offset: 'r' 	快捷设置右边缘坐标
                 *   offset: 'b' 	快捷设置底部坐标
                 *   offset: 'l' 	快捷设置左边缘坐标
                 *   offset: 'lt' 	快捷设置左上角
                 *   offset: 'lb' 	快捷设置左下角
                 *   offset: 'rt' 	快捷设置右上角
                 *   offset: 'rb' 	快捷设置右下角
                 */

                maxmin: true,//是否显示最大化最小化按钮 对type:1和type:2有效
                resize:true,//是否允许拉伸
                move:true,//是否允许拖动 move: '.mine-move'只有那个区域能拖动
                moveOut: true,//是否允许拖动的窗口外面
                fixed: true,//是否随着鼠标滚动而滚动
                scrollbar:true,//是否允许浏览器滚动
                zIndex:'100',//层叠顺序

                btn:['确认','取消'],//按钮 btn: ['按钮1', '按钮2', '按钮3', …] 按钮1的回调是yes，而从按钮2开始，则回调为btn2: function(){}，以此类推
                btnAlign:'c',//按钮排列
                /**
                 * btnAlign: 'l' 	按钮左对齐
                 * btnAlign: 'c' 	按钮居中对齐
                 * btnAlign: 'r' 	按钮右对齐。默认值，不用设置
                 */
                closeBtn: 2,//右上角关闭按钮 0-2 1和2是不同风格，0是不显示

                shade:0.6,//遮光罩 shade: [0.8, '#393D49'] shade:0
                shadeClose:true,//是否点击遮光罩自动关闭

                anim:'2',//弹出动画 -1-6 -1则是不带动画
                isOutAnim: false,//默认关闭弹出窗的时候会有一个过渡动画

                //tips:'',//tips私有参数 tips: [1, '#c00'] 1234四个方向
                //tipsMore: true,//是否同时允许多个tips

                content: '<div>model</div>', //url或者html内容
                yes: function(index, layero){//确定按钮触发的函数(或者说第一个btn触发的函数)

                },
                cancel: function(index, layero){//右上角关闭按钮触发函数

                },
                /*resizing:function(){//拉伸时候触发的函数

                },
                moveEnd: function(layero){//move结束触发的函数

                },
                success: function(layero, index){//当前层创建完毕立即执行的函数(layero代表当前层,index代表当前层索引)

                },
                end: function (){//弹出窗被销毁后触发的函数

                },
                full: function(){//最大化触发函数

                },
                min: function(){//最小化触发函数

                },
                restore: function(){//还原触发函数

                }*/
            });

        });
    }
    function confirm(){
        layer.confirm('is not?', {icon: 3, title:'提示'}, function(index){

        });
    }
    function input(){
        layer.prompt({
            formType: 2,//输入框类型，支持0（文本）默认1（密码）2（多行文本）
            value: '初始值',//初始时的值，默认空字符
            title: '请输入值',//可输入文本的最大长度，默认500
            area: ['800px', '350px'] //自定义文本域宽高
        }, function(value, index, elem){
            alert(value); //得到value
            layer.close(index);
        });
    }
    function load(){
        layer.load(2);
    }
    function msg(){
        layer.msg('is not?', {icon: 3, title:'提示'}, function(index){

        });
    }
    function tips(){
        layer.tips('在上面', '#id', {
            tips: 1
        });
    }
    function tab(){
        layer.tab({
            area: ['600px', '300px'],
            tab: [{
                title: 'TAB1',
                content: '内容1'
            }, {
                title: 'TAB2',
                content: '内容2'
            }, {
                title: 'TAB3',
                content: '内容3'
            }]
        });
    }
    function photo(){
        //json方式
        var json={
            "title": "", //相册标题
            "id": 123, //相册id
            "start": 0, //初始显示的图片序号，默认0
            "data": [   //相册包含的图片，数组格式
                {
                    "alt": "图片名",
                    "pid": 666, //图片id
                    "src": "", //原图地址
                    "thumb": "" //缩略图地址
                }
            ]
        }
        layer.photos({
            photos: json
            ,anim: 5, //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
            tab: function(pic, layero){
                console.log(pic) //当前图片的一些信息
            }
        });
    }
</script>
