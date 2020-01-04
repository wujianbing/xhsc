/*轮播图*/
				TouchSlide({ 
					slideCell:"#slideBox",
					titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
					mainCell:".bd ul", 
					effect:"leftLoop", 
					autoPage:true,//自动分页
					autoPlay:true //自动播放   
				});
				/*配送方式*/  
				$(".psfs").on('click', function(){
				    layer.open({
				      type: 1,
				      area: ['100%', '130px'],
				      offset: 'b',
				      title:['支持以下配送方式','text-align:center'],
				      shadeClose: true, //点击遮罩关闭
				      content: '<p style="margin:3%">快递发货（免运费）</p><p style="margin:3%">到店自提</p>'
				    });
				  });
			/*	  加入购物车
				var btn = document.getElementById('buy');
				var addcar = document.getElementById('addcar');
				var close = document.getElementById('close');
				var div = $('.back');
				var form1 = document.getElementById('form1');
				var form2 = document.getElementById('form2');
				var close = document.getElementById('close-button');
				 
				 
				var o = 0;
				//商品规格选择
				
					$(".sys_item_spec .sys_item_specpara").each(function(){
						var i=$(this);
						var p=i.find("ul>li");
						p.click(function(){
							var s = 0;
							if(!!$(this).hasClass("selected")){
								$(this).removeClass("selected");
							}else{
								$(this).addClass("selected").siblings("li").removeClass("selected");
								$(this).children("input").attr("name",);
								s=$(this).attr("name");
								i.attr("data-val",s);
							}
							if(s != 0){
								o = i.attr("data-val")*100/100;
								$(".sys_item_price").text(o);
							}
						})
					})
					
				 
				btn.onclick = function show() {
					div.show();
				}
				
				 addcar.onclick = function show() {
					div.show();
					form1.style.display="none";
					form2.style.display="block";
					
				}
				 
				
				close.onclick = function close() {
					
					div.hide();
					
				}
				 
				 购买数量增加
					$(".optnumadd").click(function(){
						var num=$(this).parent().children("input");
						num.val(parseInt(num.val())+1);
						$(this).parent().siblings("p.ppri").children("span.num").text('x'+num.val());
						
					})
					数量减少
					$(".optnumdec").click(function(){
						var num=$(this).parent().children("input");
						if(num.val()>0){
							num.val(parseInt(num.val())-1);
							$(this).parent().siblings("p.ppri").children("span.num").text('x'+num.val());
						}
						else{
							num.val(0);
						}
						
					});*/