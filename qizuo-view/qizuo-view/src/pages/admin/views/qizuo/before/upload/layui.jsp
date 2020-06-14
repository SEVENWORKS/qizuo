<%@ include file="../../../frame/base_tags.jsp"%>
<!-- 前端插件 -->
<div class="block-area">
    <input type="file" id="upload"/>
</div>

<!-- 执行js -->
<script>
    globalJs(function(){
        /**
         *  注意点：
         *  1.多文件时，主要从上传文件数组来控制(包括数量、删除、添加等)，记住这个数组只是一个对象而已
         *  2.控制上传的时候主要从上传对象来着手
         *  3.单页面多个上传按钮，每个上传的实例化可以用上传对象工厂来着手
         */
        /** 第一种 */
            //上传对象工厂对象(可以一直render上传对象)
        var uploadFactory;
        //上传对象
        var uploadInst;
        //上传文件对象数组
        var files;
        layui.use('upload', function(){
            //工厂初始化
            uploadFactory = layui.upload;
            //上传对象实例化
            uploadInst = uploadFactory.render({
                /** **********config配置********** */
                elem: '#upload' //绑定元素
                ,url: '${ctx}/inf/upload/uploadFile_pd?timeStamp='+new Date().getTime() //上传接口
                ,size:0//单个文件大小限制(0即不限制)
                ,multiple:true//是否多选(不管是否多选，传到后台的时候都是单个文件排队)
                ,number:10 //配合多选来设置,同时上传文件数量，会控制上传前后before等方法进入(0即不限制)
                ,accept:'images'//文件类型
                ,acceptMime:'image/jpg, image/png'//规定打开窗口后能选择文件的类型
                ,exts:'zip|rar|7z'//文件类型后缀限制，配合accept来使用
                ,auto:false //是否自动上传(就是选中后是否自动上传)
                ,field:'files'//上传字段(对应后台)
                ,drag:true//是否接受拖曳上传文件
                ,data : {
                    dataType: 'met_meeting',
                }//上传携带数据
                /** **********方法控制********** */
                ,before: function(obj){//上传前参数配置(如果是多个文件上传也只会进入一次,注入这种情况和number有关系)
                    //上传loading
                    layer.load();
                    //举例:上传配置设定
                    var data = {
                        data_id: uploadId,
                        dataType: 'met_meetingbefore'
                    }
                    uploadInst.config.data = data;
                }
                ,done: function(res, index, upload){//上传完成进入方法(如果是多个上传文件，每次上传后都会进入这个方法中，不管number同时上传文件多少)
                    //res（服务端响应信息）、index（当前文件的索引）、upload（重新上传的方法，一般在文件上传失败后使用）
                    //多文件时候，删除本次队列里已经上传完成的某个对象
                    return delete this.files[index];
                }
                ,allDone: function(obj){ //当文件全部被提交后，才触发
                    console.log(obj.total); //得到总文件数
                    console.log(obj.successful); //请求成功的文件数
                    console.log(obj.aborted); //请求失败的文件数
                }
                ,error: function(index,upload){//上传错误进入方法(返回两个参数，分别为：index（当前文件的索引）、upload（重新上传的方法）)
                }
                ,choose:function(obj){//选择文件后执行的方法
                    //多文件追加，并赋值给文件对象和当前this对象
                    files = this.files = obj.pushFile();

                    //举例：删除(注意obj.preview有兼容性问题)
                    obj.preview(function(index, file, result){
                        var tr =$(['<span class="btn btn-default btn-sm" style="margin-left: 10px">'
                            ,file.name
                            ,'&nbsp;<a class="badge badge_delete">X</a></span>'].join(''));

                        //删除
                        tr.find(".badge_delete").click(function(){
                            delete files[index]; //删除对应的文件
                            $(this).parent().remove();
                            uploadInst.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                        });

                        $("#uploadNames").append(tr);
                    });
                }
            });
        });
        /** 手动上传(这个需要把auto设置为false) */
        //uploadInst.upload();
    })
    /** ********************************************** */
</script>
