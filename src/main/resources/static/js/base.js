

function postAjax(url, data, callback){
    $.ajax({
        //提交数据的类型 POST GET
        type: "POST",
        dataType: "json",
        url: url,
        //提交的数据
        data: data,
        success: function (result) {
            callback(result)
        },
        error: function (result) {
            callback(result)
        }
    });
}


function getAjax(url,  callback){
    $.ajax({
        //提交数据的类型 POST GET
        type: "get",
        dataType: "json",
        url: url,
        success: function (result) {
            callback(result)
        },
        error: function (result) {
            callback(result)
        }
    });
}


function setCookie(cookieName, value, expiredays) {
    var exdate = new Date();
    exdate.setTime(Number(exdate) + expiredays);
    document.cookie = cookieName + "=" + (value) + ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString());
}

function getCookie(cookieName) {
    var arr,reg=new RegExp("(^| )"+cookieName+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
//        return unescape(arr[2]);
        return (arr[2]);
    else
        return null;
}


//获取选中的radio
 function getCheckedRadio(name){
   var groupCheckbox=$("input[name='"+name+"']");
   for(i=0;i<groupCheckbox.length;i++){
       if(groupCheckbox[i].checked){
           return groupCheckbox[i].value;
       }
   }
 }

//获取选中的checkbox
function getCheckedBoxValue(name){
    var txt = '';
    var groupCheckbox=$("input[name='"+name+"']");
    for(i=0;i<groupCheckbox.length;i++){
         if(groupCheckbox[i].checked){
        //                 return groupCheckbox[i].value;
            txt += groupCheckbox[i].value+",";
         }
    }
    return txt.substring(0,txt.length-1);
}


//获取url中get参数的值，
//调用 var fType = getUrlVars()["type"];
function getUrlVars() {
	var vars = {};
	var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&#]*)/gi,
		function(m,key,value) {
			vars[key] = value;
		}
	);
	return vars;
}

function isEmpty(txt){
    return txt == null || txt == "";
}