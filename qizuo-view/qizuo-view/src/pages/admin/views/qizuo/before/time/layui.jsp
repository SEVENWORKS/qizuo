<%@ include file="../../../frame/base_tags.jsp"%>
<!-- 前端插件 -->
<div class="block-area">
    <input type="text" class="layui-input" id="test1">
</div>

<!-- 执行js -->
<script>
    globalJs(function(){
        layui.use('laydate', function(){
            var laydate = layui.laydate;

            //执行一个laydate实例
            var ins1=laydate.render({
                elem: '#test1', //指定元素
                type:'datetime',//类别
                /**
                 * year 	年选择器 	只提供年列表选择
                 * month 	年月选择器 	只提供年、月选择
                 * date 	日期选择器 	可选择：年、月、日。type默认值，一般可不填
                 * time 	时间选择器 	只提供时、分、秒选择
                 * datetime 	日期时间选择器 	可选择：年、月、日、时、分、秒
                 */
                range:true,//开启面板范围 range: true 或 range: '~' 来自定义分割字符
                format:'yyyy',//格式
                /**
                 * yyyy 	年份，至少四位数。如果不足四位，则前面补零
                 * y 	年份，不限制位数，即不管年份多少位，前面均不补零
                 * MM 	月份，至少两位数。如果不足两位，则前面补零。
                 * M 	月份，允许一位数。
                 * dd 	日期，至少两位数。如果不足两位，则前面补零。
                 * d 	日期，允许一位数。
                 * HH 	小时，至少两位数。如果不足两位，则前面补零。
                 * H 	小时，允许一位数。
                 * mm 	分钟，至少两位数。如果不足两位，则前面补零。
                 * m 	分钟，允许一位数。
                 * ss 	秒数，至少两位数。如果不足两位，则前面补零。
                 * s 	秒数，允许一位数
                 */
                value:'2018',//初始化值 value: '2018-08-18' 必须遵循format参数设定的格式 / value: new Date(1534766888000) 参数即为：2018-08-20 20:08:08 的时间戳
                isInitValue: false, //是否允许填充初始值，配合value使用
                min: '1995',//最小值 不在范围内的将不可选中
                max: '',//最大值 不在范围内的将不可选中
                /**
                 * 1. 	如果值为字符类型，则：年月日必须用 -（中划线）分割、时分秒必须用 :（半角冒号）号分割。这里并非遵循 format 设定的格式
                 * 2. 	如果值为整数类型，且数字＜86400000，则数字代表天数，如：min: -7，即代表最小日期在7天前，正数代表若干天后
                 * 3. 	如果值为整数类型，且数字 ≥ 86400000，则数字代表时间戳，如：max: 4073558400000，即代表最大日期在：公元3000年1月1日
                 */
                trigger: 'click', //如何触发弹出,默认值：focus，如果绑定的元素非输入框，则默认事件为：click
                show:'',//是否直接显示，直接显示默认在绑定元素的区域
                position:'',//显示方式
                /**
                 * abolute 	绝对定位，始终吸附在绑定元素周围。默认值
                 * fixed 	固定定位，初始吸附在绑定元素周围，不随浏览器滚动条所左右。一般用于在固定定位的弹层中使用。
                 * static 	静态定位，控件将直接嵌套在指定容器中。
                 */
                zIndex:'',//层叠顺序
                showBottom:false,//是否显示底部栏，就是确定按钮那部分
                btns:['clear', 'confirm'],//底部栏按钮 可识别三种 clear、now、confirm
                lang:'',//语言 cn（中文版）、en（国际版，即英文版）
                theme:'',//主题 default（默认简约）、molv（墨绿背景）、#颜色值（自定义颜色背景）、grid（格子主题）
                calendar: true,//是否显示公历重要节日(我国的)
                mark:'',//标注重要日子
                /**
                 * 每年的日期 	{'0-9-18': '国耻'} 	0 即代表每一年
                 * 每月的日期 	{'0-0-15': '中旬'} 	0-0 即代表每年每月（layui 2.1.1/layDate 5.0.4 新增）
                 * 特定的日期 	{'2017-8-21': '发布') 	-
                 */
                ready: function(date){//初始化弹出打开触发的函数
                    console.log(date); //得到初始的日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
                },
                change: function(value, date, endDate){//日期切换后触发的函数
                    console.log(value); //得到日期生成的值，如：2017-08-18
                    console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
                    console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
                },
                done: function(value, date, endDate){//点击日期、清空、现在、确定均会触发
                    console.log(value); //得到日期生成的值，如：2017-08-18
                    console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
                    console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
                }
            });

            //控件上弹出某个值
            ins1.hint(value);//ins1代表render后对象

            //获取某月最后一天
            laydate.getEndDate(month, year);
        });
    })
    /** ********************************************** */
</script>
