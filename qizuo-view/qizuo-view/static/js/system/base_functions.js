/**
 * 本js旨在全局的功能性函数，核心
 * backResult(data,func)/backResultAlert(data,func)/backResultBig(data,func)；ajax数据返回处理函数
 * formListSer(dataDetailContainer,listDom)：list表单序号自动生成
 * deleteData(data,url,dom)：js删除数据
 * deleteDataReload(data,url)：js删除数据后刷新页面
 * updateDate(url)：js更新数据
 *
 *
 * addDataTr(templateDom,containerDom)：table增加一行数据
 * removeDataTr(dom,removeDom)：table删除一行数据
 *
 * buttonRemove()：去除页面固定定位的单个按钮
 * layerClose()：关闭模态框
 * f5()：刷新当前页面
 * globalJs(func)：全局js函数，一般用于最外层
 *
 *
 * pjaxFunc(url,container)：异步获取jsp页面（另外两个不用理会，pjax弃用）
 * tplFunc(data,container,tplId)：普通数据模板渲染
 * tplFuncTable(data,container,tplId)：table专用数据模板函数
 * pageHtml(pageFunc,pageSize,pageNo)：分页
 *
 *
 * isNotBlank(value)：值判空
 * isFunction(value)：函数判空
 * getRootPath()：js获取项目根目录
 * countDivision(value1,value2,type)：取模或者取余
 * charViewTimes(char,str)：单个字符出现次数
 *
 */

/** 判断值是否能用 */
function isNotBlank(value){
	if(typeof(value)=='undefined'||null==value||value.length<=0||''==value){
		return false;
	}else{
		return true;
	}
}
/** 判断元素是否是函数 */
function isFunction(value){
	if(''!=value&&null!=value&&Object.prototype.toString.call(value) === "[object Function]"){
		return true;
	}else{
		return false;
	}
}
/** *************对本系统的json返回对象进行处理*************** */
/** 第一种只对返回1的code进行处理 */
function backResult(data,func){
	if(isNotBlank(data)){
		var backCode=data.backCode;
		var backMsg=data.backMsg;
		var backData=data.backData;
		if(global$backSuccessCode==backCode){
			if(isFunction(func)){
                func(backData,data);
			}
		}else{
			alert(backMsg);
		}
	}else{
		alert("系统异常，请联系管理员");
	}
}
/** 综合json和alert处理 */
function backResultAlert(data,func){
    backResult(data,function(data,dt){
        alert(dt.backMsg,function(){
            if(isFunction(func)){
                func(data,dt);
            }
        })
    })
}
/** 第二种只要返回不是error都进行处理 */
function backResultBig(data,func){
    if(isNotBlank(data)){
        var backCode=data.backCode;
        var backMsg=data.backMsg;
        var backData=data.backData;
        if(global$backErrorCode!=backCode){
            if(isFunction(func)){
                func(backData,data);
            }
        }else{
            alert(backMsg);
        }
    }else{
        alert("系统异常，请联系管理员");
    }
}

/** js获取项目根路径 */
function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
}

/** *************************************异步获取html片段********************************** */
/** pjax封装(push必须赋值为false,否则无法使用,应该算个bug)
 * (为什么说pjax最好不要手动调用？因为它的生命周期钩子函数适于某个元素绑定的，a元素是最佳使用对象) */
function pjaxFunc(url,container){
    container=isNotBlank(container)?container:global$frameContainer;
    //去掉上个页面多余的元素
    buttonRemove();
    //异步加载
    ajaxHtml(url,container);
    //$.pjax({url: url, container: container,push:false,replace:false,timeout:6500,cache:false});
    //$.pjax.reload(global$frameContainer, {url: url,push:false,replace:false})//使用pjax机制发起一个当前URL的请求到服务器，并且通过响应的内容替换容器元素中的内容，同时不添加浏览器历史记录。
}
/** pjax封装 */
function pjaxFuncForClick(selector,container,options){
    $(document).pjax(selector, container, options);
}
/** 异步加载html */
function ajaxHtml(url,container){
    $.get(url,{},function(data){
        $(container).html(data);
    })
}

/**  art-template模板方法(只适用于table模板) */
function tplFuncTable(data,container,tplId){
    //数值初始化
    container=isNotBlank(container)?container:'#dataContainer';
    tplId=isNotBlank(tplId)?tplId:'baseTpl';
    //先清空
    $(container+" tbody").empty();
    //tr长度塞入
    template.defaults.imports.th_length = $(container+" thead").find("tr th").length;
    //解决缓存引起的影响
    template.defaults.cache=false;
    //模板转义
    var tplHtml=template(tplId, data);
    //模板添加
    $(container+" tbody").append(tplHtml);
}
function tplFunc(data,container,tplId){
    //数值初始化
    container=isNotBlank(container)?container:'#dataContainer';
    tplId=isNotBlank(tplId)?tplId:'baseTpl';
    //先清空
    $(container).empty();
    //解决缓存引起的影响
    template.defaults.cache=false;
    //模板转义
    var tplHtml=template(tplId, data);
    //模板添加
    $(container).append(tplHtml);
}

/** 自定义分页方法 */
function pageHtml(pageFunc,pageSize,pageNo){
    if(!isFunction(pageFunc)){
        return ;
    }
    //基本配置
    var pageConfig = {
        pageSize: isNotBlank(pageSize)?pageSize:10,
        pageIndex:isNotBlank(pageNo)?pageNo:1,
        prevPage: '<i class="fa fa-angle-left" style="line-height: 1.42857143"></i>',
        nextPage: '<i class="fa fa-angle-right" style="line-height: 1.42857143"></i>',
        firstPage: 'A',
        lastPage: 'Z',
        degeCount: 2,
        ellipsis: true
    };
    //分页方法
    new Paging(pageConfig, function (pageNo, pageSize,pageIng) {
        //为什么要放在这里面，因为异步的问题
        pageFunc(function(pageData){
            if(null!=pageData&&isNotBlank(pageData.entitys)){
                //当前页数
                //var pageNo=isNotBlank(pageData.pageNo)?pageData.pageNo:1;
                //总条数
                var totalCount=isNotBlank(pageData.totalCount)?pageData.totalCount:0;
                //总页数
                var totalPage=isNotBlank(pageData.totalPage)?pageData.totalPage:0;
                //是否隐藏
                if(totalCount>0){
                    //分页元素初始化
                    pageIng.initPage(totalCount, totalPage, pageNo);
                    $("#pageContainer").css("display","block");
                }else{
                    $("#pageContainer").css("display","none");
                }
            }
        },pageNo,pageSize);
    });
}

/** 取模或者取余 */
function countDivision(value1,value2,type){
    if(isNotBlank(type)&&type==1){
        return Math.floor(value1/value2);
    }else{
        return value1%value2;
    }
}

/** springmvc list接受数据序号封装 */
function formListSer(dataDetailContainer,listDom){
    //主容器
    dataDetailContainer=isNotBlank(dataDetailContainer)?dataDetailContainer:'#dataDetailContainer';
    //单个列表容器
    listDom=isNotBlank(listDom)?listDom:'tr';
    $(dataDetailContainer+' '+listDom).each(function(i){
        //input
        $(this).find('input').each(function(){
            var name=$(this).attr('name');
            $(this).attr('name',formListSerCount(name,i));
        });
        //select
        $(this).find('select').each(function(){
            var name=$(this).attr('name');
            $(this).attr('name',formListSerCount(name,i));
        });
        //textarea
        $(this).find('textarea').each(function(){
            var name=$(this).attr('name');
            $(this).attr('name',formListSerCount(name,i));
        });
    })
}
function formListSerCount(name,i){
    var dot=name.indexOf('\.');
    var kh=name.indexOf('[');
    if(isNotBlank(name)&&(dot!=-1||kh!=-1)&&(charViewTimes('[',name)==0||charViewTimes('\.',name)==0)){
        var begin;
        var end;
        if(dot!=-1){
            begin=name.substring(0,dot);
            end=name.substring(dot);
            name=begin+'['+i+']'+end;
        }else{
            begin=name.substring(0,kh+1);
            end=name.substring(kh+1);
            name=begin+i+end;
        }
    }
    return name;
}

/** js判断一个字符在字符串中出现次数 */
function charViewTimes(char,str){
    var array=str.split(char);
    return isNotBlank(array)?array.length-1:0;
}

/** 增加一行数据 */
function addDataTr(templateDom,containerDom){
    templateDom=isNotBlank(templateDom)?templateDom:'#templateDom';
    containerDom=isNotBlank(containerDom)?containerDom:'#dataDetailContainer';
    //克隆
    var clone=$(templateDom).clone();
    //显现添加
    $(clone).css("display","").appendTo(containerDom);
}
/** 删除一行数据 */
function removeDataTr(dom,removeDom){
    removeDom=isNotBlank(removeDom)?removeDom:"tr";
    //移除
    $(dom).parents(removeDom).remove();
}
/** 删除数据 */
function deleteData(data,url,dom){
    //先关闭层
    layerClose();
    //再执行操作
    layer.confirm('<span style="color: red">确认删除？</span>',{icon: 3, title:'提示'},function(){
        $.post(url,data,function(data){
            backResultAlert(data,function(){
                $(dom).remove();
            })
        })
    })
}
//删除后刷新
function deleteDataReload(data,url){
    //先关闭层
    layerClose();
    //再执行操作
    layer.confirm('<span style="color: red">确认删除？</span>',{icon: 3, title:'提示'},function(){
        $.post(url,data,function(data){
            backResultAlert(data,function(){
                f5();
            })
        })
    })
}

/** 更新数据 */
function updateDate(url){
    //先关闭层
    layerClose();
    //再获取模板
    pjaxFunc(url);
}

/** 去除buttonOne */
function buttonRemove(){
    $(".buttonOne").remove();
}

/** 关闭layer */
function layerClose(){
    //先关闭层
    layer.close(layer.index);
}

/** 返回到当前主页面 */
function f5(){
    pjaxFunc(global$frameUrl);
}

/** 全局js函数 */
function globalJs(func){
    try {
        $(function(){
            func();
        })
    }catch (error){
        console.log(error);
        window.location.reload();
    }
}

/** iframe操作 */