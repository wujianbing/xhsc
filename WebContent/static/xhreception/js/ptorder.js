/*轮播图*/
				TouchSlide({ 
					slideCell:"#slideBox",
					titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
					mainCell:".bd ul", 
					effect:"leftLoop", 
					autoPage:true,//自动分页
					autoPlay:true //自动播放   
				});
				
				
			/*活动结束剩余时间*/
			  var addTimer = function () { 
			    var list = [], 
			      interval; 
			  
			    return function (id, time) { 
			      if (!interval) 
			        interval = setInterval(go, 1000); 
			      list.push({ ele: document.getElementById(id), time: time }); 
			    } 
			  
			    function go() { 
			      for (var i = 0; i < list.length; i++) { 
			        list[i].ele.innerHTML = getTimerString(list[i].time ? list[i].time -= 1 : 0); 
			        if (!list[i].time) 
			          list.splice(i--, 1); 
			      } 
			    } 
			  
			    function getTimerString(time) { 
			      var not0 = !!time, 
			        d = Math.floor(time / 86400), 
			        h = Math.floor((time %= 86400) / 3600), 
			        m = Math.floor((time %= 3600) / 60), 
			        s = time % 60; 
			      if (not0) 
			        return "还有" + d + "天" + h + "小时" + m + "分" + s + "秒"; 
			      else return "时间到"; 
			    } 
			  } (); 
			  
			/*  addTimer("timer1", 86400); 
			  addTimer("timer2", 66500); 
			  addTimer("timer3", 43000); 
			  addTimer("timer4", 23000); */
			  
			  
			//带天数的倒计时
			  function countDown(times){
			    var timer=null;
			    timer=setInterval(function(){
			      var day=0,
			        hour=0,
			        minute=0,
			        second=0;//时间默认值
			      if(times > 0){
			        day = Math.floor(times / (60 * 60 * 24));
			        hour = Math.floor(times / (60 * 60)) - (day * 24);
			        minute = Math.floor(times / 60) - (day * 24 * 60) - (hour * 60);
			        second = Math.floor(times) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
			      }
			      if (day <= 9) day = '0' + day;
			      if (hour <= 9) hour = '0' + hour;
			      if (minute <= 9) minute = '0' + minute;
			      if (second <= 9) second = '0' + second;
			      //
			      console.log(day+"天:"+hour+"小时："+minute+"分钟："+second+"秒");
			      times--;
			    },1000);
			    if(times<=0){
			      clearInterval(timer);
			    }
			  }
				
