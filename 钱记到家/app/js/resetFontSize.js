function setFontSize() {

    var html = document.documentElement;
    //alert("1:"+html.clientWidth);
    //alert("2:"+$(".main").width());
    html.style.fontSize = html.clientWidth / 375 * 100 + 'px';

    var html = document.getElementsByTagName('html')[0];
    var settedFs = settingFs = parseInt(html.style.fontSize);
    var whileCount = 0;
    while (true) {
        var realFs = parseInt(window.getComputedStyle(html).fontSize);
        var delta = realFs - settedFs;
        if (Math.abs(delta) >= 1) //不相等
        {
            if (delta > 0) settingFs--; else settingFs++;
            html.setAttribute('style', 'font-size:' + settingFs + 'px!important');
        } else
            break;
        if (whileCount++ > 100) //之所以弄个100的循环跳出的原因，在于实在无法预判设备是否能计算得到36px，比如设备只能计算35px，37px，这样会死循环只能跳出了
            break
    }
}
setFontSize();
window.onresize = function () {
    setFontSize();

}

function cutTel(tel) {
    var ptel = tel.substr(8, tel.length - 1);
    var ptel2 = sc.utils.truncateString(tel, 4);
    return ptel2 + "****" + ptel;
}
$(function () {

    //以下代码是兼容苹果手机返回上一页问题
    var isPageHide = false;
    window.addEventListener('pageshow', function () {
        if (isPageHide) {
            window.location.reload();
        }
    });
    window.addEventListener('pagehide', function () {
        isPageHide = true;
    });
});

