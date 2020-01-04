
var o = 0;
//商品规格选择
$(function(){
	$(".sys_item_spec .sys_item_specpara").each(function(){
		var i=$(this);
		var p=i.find("ul>li");
		p.click(function(){
			var s = 0;
			if(!!$(this).hasClass("selected")){
				$(this).removeClass("selected");
			}else{
				$(this).addClass("selected").siblings("li").removeClass("selected");
				s=$(this).attr("name");
				i.attr("data-val",s);
			}
			if(s != 0){
				o = i.attr("data-val")*100/100;
				$(".sys_item_price").text(o);
			}
		})
	})
	function getattrprice(data){
	}

	/*购买数量增加*/
	$(".optnumadd").click(function(){
		var num=$(this).parent().children("input");
		num.val(parseInt(num.val())+1);
		$(this).parent().siblings("p.ppri").children("span.num").text('x'+num.val());
		
	})
	/*数量减少*/
	$(".optnumdec").click(function(){
		var num=$(this).parent().children("input");
		if(num.val()>0){
			num.val(parseInt(num.val())-1);
			$(this).parent().siblings("p.ppri").children("span.num").text('x'+num.val());
		}
		else{
			num.val(0);
		}
		
	});
	
	/*添加购物车成功*/
	$("#addcar").on('click', function(){
		layer.alert("购物车添加成功",function(){
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index); //再执行关闭
		})
	    
	  });
				  
	/*立刻购买 下一步 关闭遮罩层，跳转到订单页面*/	
	/*$("#buy").on('click', function(){
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index); //再执行关闭
			
	  });*/
})