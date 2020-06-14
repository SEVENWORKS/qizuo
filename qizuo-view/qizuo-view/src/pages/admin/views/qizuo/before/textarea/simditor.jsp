<%@ include file="../../../frame/base_tags.jsp"%>
<link href="${staticPath}js/simditor/simditor.css" rel="stylesheet">
<!-- 前端插件 -->
<div class="block-area">
    <textarea id="evenText" autofocus></textarea>
</div>
<!-- 执行js -->
<script src="${staticPath}js/simditor/module.js"></script>
<script src="${staticPath}js/simditor/hotkeys.js"></script>
<script src="${staticPath}js/simditor/uploader.js"></script>
<script>
    globalJs(function(){
        //引入js
        $.getScript("${staticPath}js/simditor/simditor.js",function(){
            //初始化富文本
            var editor = new Simditor({
                textarea: $('#evenText'),
                toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', /*'link', 'image', 'hr', '|',*/ 'indent', 'outdent', 'alignment'],
                toolbarFloat:false,
                /*upload: {
                    url: '', //文件上传的接口地址
                    params: {dayType:""}, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
                    fileKey: 'files', //服务器端获取文件数据的参数名
                    connectionCount: 3,
                    leaveConfirm: '正在上传文件'
                }*/
            });
        })
    })
    /** ********************************************** */
</script>