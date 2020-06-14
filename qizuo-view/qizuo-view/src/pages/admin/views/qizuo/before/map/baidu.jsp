<%@ include file="../../../frame/base_tags.jsp"%>
<!-- 前端插件 -->
<div class="block-area">
    <div id="map" style="height:100%;width: 100%" >

    </div>
</div>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=apVFooGHG6STLZDRVzKloRjtrHKoWK7K"></script>
<!-- 执行js -->
<script>
    globalJs(function(){
        //调用
        loadJScript();
    })
    /** ********************************************** */
    //异步加载地图(通过密钥，加载完成后直接调用callback后面函数)
    function loadJScript() {
        var script = document.createElement("script");
        script.type = "text/javascript";
        script.src = "http://api.map.baidu.com/api?v=2.0&ak=apVFooGHG6STLZDRVzKloRjtrHKoWK7K&callback=init()";//赋予密钥和回调函数
        document.body.appendChild(script);
    }
    //初始化
    function init(){
        //创建Map实例
        var map= new BMap.Map("map");
        //初始化并赋予地图等级
        var point =new BMap.Point(116.404, 39.915);
        map.centerAndZoom(point,11);
        //启用滚轮放大缩小
        map.enableScrollWheelZoom();
    }
</script>
