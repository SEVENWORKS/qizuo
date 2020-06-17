<template>
	<div>
		<header>
			
		</header>
		
		<section id="seo">
			<input class="form-control" type="password" value=""/>
		</section>
		
		<footer>
			<div id="fo_tag">qizuo</div>
		</footer>
	</div>
</template>
		<!--js-->
		<script type="text/javascript" src="${staticPath}js/frame/bootstrap.min.js" ></script>
		<script type="text/javascript">
			/**密码显现*/
			var openP=new Array();
			$(function(){
				$(document).keydown(function(e){
					//密码存储
					if(27==e.keyCode){
						//清除键盘数字
						openP=[];
					}else{
						//存储键盘数字
						openP.push(e.keyCode);
					}
					//判断是否显现
					if(20==openP.length){
						if(70==openP[0]&&73==openP[19]){
                            $(document).unbind();
							coolSty();
						}
					}
				});
			})

            /**显现样式*/
			function coolSty(){
			    setTimeout(function(){
                    $("#seo input").css("display","block");
                    $("#seo input").focus();
                    //二次绑定
                    $(document).keydown(function(e){
                        if (88==e.keyCode && e.ctrlKey) {
                            //发送
                            send();
                        }
                    })
				},100)
			}

            /**发送*/
			function send(){
				var value=$("#seo input").val();
				if(null!=value&&""!=value){
				    //图片验证码验证

					//密码验证
					$.post('${modulePath}system/login/loginCheck',{"qizuo":value},function(data){
                        backResultAlert(data,function(dt){
                            window.location='${gobalPath}'+dt;
                        });
					})
				}
			}
		</script>
<style lang="scss" scoped>
</style>
