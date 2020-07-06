/*回到顶部*/
$(function() {	
	$('body').prepend('<div id="go-top" class="go-top"><a href="javascript:;" title="返回顶部"></a></div>');
	var $top = $('#go-top');
	$(window).bind('scroll', function() {
		var $this = $(this);
		if($this.scrollTop() > 500) {
			$top.show();
		} else {
			$top.hide();
		}
	})
	function goTop() {
		$('html,body').animate({
			scrollTop: 0
		}, 400)
	}
	 $top.click(function(){
	 	goTop();
	 })
})