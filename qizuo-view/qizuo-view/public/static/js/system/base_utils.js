/**
 * 本js旨在页面工具渲染
 * alert(Msg,func)：全局alert重写
 * modelBase(content,width,height)：模态框弹出
 * buttonOne(text,func,left,bottom)：单个页面固定定位按钮创建
 * buttonPanel(name,func...):按钮面板，区别于上
 * treeMenu(list,name,setting,treeContainer,dataString)：树
 * checkAndradio()/checkSwitch(dom,trueFunc,falseFunc)/maskForInput()/selectInit()/spinnerInit()/timeInit()/colorInit()/textareaInit()/bigTextArae()/sliderInit()/mediaInit()：框架自带前端插件
 *
 */

/** 弹出窗(重写，基于layer) */
window.alert=function(msg,func){
	//弹
	layer.msg(msg,{time:1*1000},func);
}

/** 模态框(基于layer,只针对于普通和frame) */
function modelBase(content,width,height){
	//参数
    width=isNotBlank(width)?width:'30%';
    height=isNotBlank(height)?height:'30%';
    var type=1;
    //判断是页面层还是iframe层
    if(content.charAt(0)=='@'){
        type=2;
        content=content.substring(1);
	}else if(content.charAt(0)=='#'){
        content=$(content);
    }
    var modelBase=layer.open({
        type: type,
        title: false,
        area: [width, height],
        shade: 0.3,
        closeBtn: 0,
        shadeClose: true,
        resize: false,
        skin:'baseBgc',
        content: content
    });
    return modelBase;
}

/** 整个页面功能性按钮单个 */
function buttonOne(text,func,left,bottom){
	//属性
	left=isNotBlank(left)?left:'50%';
	bottom=isNotBlank(bottom)?bottom:'10%';
	//模板(这个样式代表的是当前页面的，最终替换页面的时候会remove调)
	var htmlQ=$('<button class="btn btn-sm btn-alt m-r-5 buttonOne" style="position: fixed;left: '+left+';bottom: '+bottom+'" type="button">'+text+'</button>');
    htmlQ.appendTo('body');
    //触发函数
    if(isFunction(func)){
        htmlQ.click(function(){
            func();
        })
	}
}

/** 针对某个批量共性产生的面板公用多个按钮 */
function buttonPanel(){
	var arLength=arguments.length;
	if(arLength>0&&countDivision(arLength,2)==0){
	    var arLengthMiddle=countDivision(arLength,2,1);
        var html=' <div>\n';
	    for(var i=0;i<arLengthMiddle;i++){
	        if(i==0){
                html+='<button class="btn btn-sm btn-alt m-r-5" onclick="'+arguments[i+1]+'" type="button">'+arguments[i]+'</button>\n';
            }else{
	            var j=i*2;
                html+='<button class="btn btn-sm btn-alt m-r-5" onclick="'+arguments[j+1]+'" type="button">'+arguments[j]+'</button>\n';
            }
        }
        html+=' </div>\n';
        modelBase(html,'auto','auto');
	}
}

/** 树形菜单 */
function treeMenu(list,name,setting,treeContainer,dataString){
    treeContainer=isNotBlank(treeContainer)?treeContainer:"#tree";
    //基础设置
    if(!isNotBlank(setting)){
        //多选
        /*setting = {
            check: {
                enable: true,//是否开启check或者radio
                chkStyle: "checkbox",//设置选择类型是check还是radio
                autoCheckTrigger: false,//设置事件是否自动关联到下方子节点
                chkboxType: { "Y": "ps", "N": "ps" }//Y 属性定义 checkbox 被勾选后的情况；N 属性定义 checkbox 取消勾选后的情况； "p" 表示操作会影响父级节点；"s" 表示操作会影响子级节点。
            },
            data: {
                simpleData: {
                    enable: true,//这个开启代表简单参数的搭配，即无需再使用json嵌套形式，直接用数组对象list就行
                    rootPId: 0 //根节点代表的数据，默认Null
                }
            },
            view: {
                fontCss : {color:"#FFFFFF"},//字体颜色
                showIcon: false //设置是否显示图标
            },
            callback : {
                onClick : function(event, treeId, treeNode) {
                    //去掉背景色
                    $(".curSelectedNode").removeClass("curSelectedNode");
                },
                onCheck : function(event, treeId, treeNode) {

                }
            }
        }*/
        //普通
        setting = {
            data: {
                simpleData: {
                    enable: true,//这个开启代表简单参数的搭配，即无需再使用json嵌套形式，直接用数组对象list就行
                    rootPId: 0 //根节点代表的数据，默认Null
                }
            },
            view: {
                fontCss : {color:"#FFFFFF"},//字体颜色
                showIcon: false //设置是否显示图标
            },
            callback : {
                onClick : function(event, treeId, treeNode) {
                    //去掉背景色
                    $(".curSelectedNode").removeClass("curSelectedNode");
                }
            }
        }
    }
    //节点配置
    var zNodes =[];
    for(var o=0;o<list.length;o++){
        //id和pId是setting中data中simpleData的设定，name和open则是treeNode本身的属性设置
        var html={id:list[o].baseId, pId:list[o].parentId, name:list[o][name], open:1};
        //checked回显专属
        if(isNotBlank(dataString)){
            if(dataString.indexOf(list[o].baseId)!=-1){
                html.checked=true;
            }
        }
        zNodes.push(html);
    }
    //初始化
    return $.fn.zTree.init($(treeContainer), setting, zNodes);
}

/** *******************框架自带初始化插件******************************* */
/** checkbox和radio初始化调用(icheck.js),not后面的主要是为了剔除bootstrap按钮的，这个主要是优化普通checkbox和radio样式的 */
function checkAndradio(){
    $('input:checkbox:not([data-toggle="buttons"] input,.make-switch input),input:radio:not([data-toggle="buttons"] input)').iCheck({
        checkboxClass: 'icheckbox_minimal',
        radioClass: 'iradio_minimal',
        increaseArea: '20%'
    });
}

/** 开关按钮ckeckbox和radio使用方法，就是引用<script src="${staticPath}js/base/toggler.min.js"></script>，这个js专门为这两个开关按钮写的,这个是bootstrap switch(http://www.bootcss.com/p/bootstrap-switch/) */
function checkSwitch(dom,trueFunc,falseFunc){
    //开关控制(默认false右边字)
    $(dom).on('switch-change', function (e, data) {
        var $el = $(data.el), value = data.value;
        if(value){
            //true左边字
            if(isFunction(trueFunc)){
                trueFunc();
            }
        }else{
            //false右边字
            if(isFunction(falseFunc)){
                falseFunc();
            }
        }
    });
}


/** mask插件控制input格式(input-mask.js) */
function maskForInput(){
    $('.mask-date').mask('00/00/0000');
    $('.mask-time').mask('00:00:00');
    $('.mask-date_time').mask('00/00/0000 00:00:00');
    $('.mask-cep').mask('00000-000');
    $('.mask-phone').mask('0000-0000');
    $('.mask-phone_with_ddd').mask('(00) 0000-0000');
    $('.mask-phone_us').mask('(000) 000-0000');
    $('.mask-mixed').mask('AAA 000-S0S');
    $('.mask-cpf').mask('000.000.000-00', {reverse: true});
    $('.mask-money').mask('000.000.000.000.000,00', {reverse: true});
    $('.mask-money2').mask("#.##0,00", {reverse: true, maxlength: false});
    $('.mask-ip_address').mask('0ZZ.0ZZ.0ZZ.0ZZ', {translation: {'Z': {pattern: /[0-9]/, optional: true}}});
    $('.mask-ip_address').mask('099.099.099.099');
    $('.mask-percent').mask('##0,00%', {reverse: true});
    $('.mask-credit_card').mask('0000 0000 0000 0000');
}

/** select类初始化类 */
function selectInit(){
    //slect插件初始化(bootstrap selectpicker)
    if($('.select')[0]) {
        $('.select').selectpicker();
    }
    //choose插件初始化(jquery.chosen.min.js)
    if($('.tag-select')[0]) {
        $('.tag-select').chosen();
    }
    //choose插件初始化2(jquery.chosen.min.js)
    $(".tag-select-limited").chosen({
        max_selected_options: 5
    });
}

/** 数字加减初始化(spinner.js) */
function spinnerInit(){
    //Basic
    $('.spinner-1').spinedit();

    //Set Value
    $('.spinner-2').spinedit('setValue', 100);

    //Set Minimum
    $('.spinner-3').spinedit('setMinimum', -10);

    //Set Maximum
    $('.spinner-4').spinedit('setMaxmum', 100);

    //Set Step
    $('.spinner-5').spinedit('setStep', 10);

    //Set Number Of Decimals
    $('.spinner-6').spinedit('setNumberOfDecimals', 2);
}

/** 时间日期初始化(datetimepicker.min.js) */
function timeInit(){
    //Date Only
    if($('.date-only')[0]) {
        $('.date-only').datetimepicker({
            pickTime: false
        });
    }

    //Time only
    if($('.time-only')[0]) {
        $('.time-only').datetimepicker({
            pickDate: false
        });
    }

    //12 Hour Time
    if($('.time-only-12')[0]) {
        $('.time-only-12').datetimepicker({
            pickDate: false,
            pick12HourFormat: true
        });
    }

    $('.datetime-pick input:text').on('click', function(){
        $(this).closest('.datetime-pick').find('.add-on i').click();
    });
}

/** 颜色选择器初始化(colorpicker.min.js) */
function colorInit(){
    //Default - hex
    if($('.color-picker')[0]) {
        $('.color-picker').colorpicker();
    }

    //RGB
    if($('.color-picker-rgb')[0]) {
        $('.color-picker-rgb').colorpicker({
            format: 'rgb'
        });
    }

    //RGBA
    if($('.color-picker-rgba')[0]) {
        $('.color-picker-rgba').colorpicker({
            format: 'rgba'
        });
    }

    //Output Color
    if($('[class*="color-picker"]')[0]) {
        $('[class*="color-picker"]').colorpicker().on('changeColor', function(e){
            var colorThis = $(this).val();
            $(this).closest('.color-pick').find('.color-preview').css('background',e.color.toHex());
        });
    }
}

/** 普通textarea初始化 */
function textareaInit(){
    //扩展型(autosize.min.js，这款插件不仅仅在textarea上有体现)
    if($('.auto-size')[0]) {
        $('.auto-size').autosize();
    }
    //超出滑动型(scroll.min.js，这款插件不仅仅在textarea上有体现)
    if($('.overflow')[0]) {
        var overflowRegular, overflowInvisible = false;
        overflowRegular = $('.overflow').niceScroll();
    }
}

/** 两种富文本编辑框初始化 */
function bigTextArae(){
    //Markedown
    if($('.markdown-editor')[0]) {
        $('.markdown-editor').markdown({
            autofocus:false,
            savable:false
        });
    }

    //WYSIWYE Editor
    if($('.wysiwye-editor')[0]) {
        $('.wysiwye-editor').summernote({
            height: 200
        });
    }
}

/** 进度条初始化(slider.min.js) */
function sliderInit(){
    if($('.input-slider')[0]) {
        $('.input-slider').slider().on('slide', function(ev){
            $(this).closest('.slider-container').find('.slider-value').val(ev.value);
        });
    }
}

/** 媒体插件初始化(media-player.min.js视屏,pirobox.min.js图片) */
function mediaInit(){
    //视屏和音频
    if($('audio,video')[0]) {
        $('audio,video').mediaelementplayer({
            success: function(player, node) {
                $('#' + node.id + '-mode').html('mode: ' + player.pluginType);
            }
        });
    }
    //图片组
    if($('.pirobox_gall')[0]) {
        //Fix IE
        jQuery.browser = {};
        jQuery.browser.msie = false;
        jQuery.browser.version = 0;
        if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
            jQuery.browser.msie = true;
            jQuery.browser.version = RegExp.$1;
        }

        //Lightbox
        $().piroBox_ext({
            piro_speed : 700,
            bg_alpha : 0.5,
            piro_scroll : true // pirobox always positioned at the center of the page
        });
    }

}
/** *******************框架自带初始化插件******************************* */



