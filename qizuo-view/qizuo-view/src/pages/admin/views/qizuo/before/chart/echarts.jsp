<%@ include file="../../../frame/base_tags.jsp"%>
<!-- 前端插件 -->
<div class="block-area">
    <div id="mainEcharts" style="width: 100%;height: 100%">

    </div>
</div>
<!-- 执行js -->
<script>
    globalJs(function(){
        //动态引入echarts(因为pjaxbug,script标签无法直接引入，以后解决再说)
        $.getScript('${staticPath}js/echarts/echarts.js',function(){
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('mainEcharts'));

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: 'ECharts 入门示例'
                },
                tooltip: {},
                legend: {
                    data:['销量']
                },
                xAxis: {
                    data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
                },
                yAxis: {},
                series: [{
                    name: '销量',
                    type: 'bar',
                    data: [5, 20, 36, 10, 10, 20]
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        });
    })
    /** ********************************************** */
</script>
