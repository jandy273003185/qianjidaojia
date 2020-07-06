(function($){
	
	// gyzbadmin命名空间下绑定相关工具方法
	$.gyzbadmin = {
			
		/**
		 * 后台模拟用POST方式提交一个表单
		 * @param url 要提交的URL地址
		 * @param params 要提交的参数对象,无参数时可以使用不传
		 * @param target 要提交的目标,默认是本页面
		 */
		postForm : function(url, params, target){
			target = target || '_self';
			var form = $('<FORM>').attr('method', 'post')
								.attr('action', url)
								.css('display', 'none')
								.attr('target', target);
			var input_control = null;
			if(params != null){
				for(key in params){
					var values = params[key];
					// 数组要遍历
					if(values instanceof Array){
						for(var idx = 0; idx < values.length; idx++){
							input_control = $('<INPUT TYPE="TEXT" />');
							input_control.attr('name', key).val(values[idx]);
							form.append(input_control);
						}
					} else {
						input_control = $('<INPUT TYPE="TEXT"  />');
						input_control.attr('name', key).val(params[key]);
						form.append(input_control);
					}
					
				}
			}
			
			// 附加时间戳,防止缓存
			input_control = $('<INPUT TYPE="TEXT"  />');
			input_control.attr('name', '_timestamp').val(new Date().getTime());
			form.append(input_control);
			
			$('body').first().append(form);
			$.blockUI();
			form.submit();
		},
		
		alertFailure: function(message, _showFunc, _hideFunc){
			var $_common_model = $('#modal-container-common-alert-danger');
			$_common_model.on('show.bs.modal', function () {
				$_common_model.find('#alertMessage').html(message);
				if(_showFunc) {
					_showFunc();
				}
			});
			$_common_model.on('hide.bs.modal', function () {	
				$_common_model.find('#alertMessage').html('');
				if(_hideFunc){
					_hideFunc();
				}
			});
			$_common_model.modal('show');	
		},
		
		alertSuccess: function(message, _showFunc, _hideFunc){
			var $_common_model = $('#modal-container-common-alert-success');
			$_common_model.on('show.bs.modal', function () {
				$_common_model.find('#alertMessage').html(message);
				if(_showFunc) {
					_showFunc();
				}
			});
			$_common_model.on('hide.bs.modal', function () {
				$_common_model.find('#alertMessage').html('');
				if(_hideFunc){
					_hideFunc();
				}
			});
			$_common_model.modal('show');
			setTimeout("$('#modal-container-common-alert-success').modal('hide')", 1000); 
		}
	};
	
})(jQuery);



jQuery(function($){
	$('.clearBtn').click(function(){
		$(':input','#form')  
		 .not(':button, :submit, :reset, :hidden')  
		 .val('')  
		 .removeAttr('checked')  
		 .removeAttr('selected'); 
	});
});

