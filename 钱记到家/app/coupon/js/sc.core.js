/**
/**
 * jquery扩展，依赖jquery
 * 添加sc空间，不污染全局
 */
var sc = sc || {};
(function($) {
	/* Application paths *****************************************/

	//Current application root path (including virtual directory if exists).
	sc.appPath = sc.appPath || '/';
	sc.pageLoadTime = new Date();

	//Converts given path to absolute path using sc.appPath variable.
	sc.toAbsAppPath = function(path) {
		if(path.indexOf('/') == 0) {
			path = path.substring(1);
		}

		return sc.appPath + path;
	};

	/* UTILS ***************************************************/
	sc.utils = sc.utils || {};

	/* 
	 * 在给定的字符串（str）中找到（search）并替换成另一个字符串（replacement）。
	 *  例如:
	 *  sc.utils.replaceAll('This is a test string', 'is', 'X') = 'ThX X a test string'
	 ************************************************************/
	sc.utils.replaceAll = function(str, search, replacement) {
		var fix = search.replace(/[.*+?^${}()|[\]\\]/g, "\\$&");
		return str.replace(new RegExp(fix, 'g'), replacement);
	};

	/* 格式化字符串.
	 *  例如:
	 *  sc.utils.formatString('Hello {0}','Tuana') = 'Hello Tuana'
	 ************************************************************/
	sc.utils.formatString = function() {
		if(arguments.length < 1) {
			return null;
		}

		var str = arguments[0];

		for(var i = 1; i < arguments.length; i++) {
			var placeHolder = '{' + (i - 1) + '}';
			str = sc.utils.replaceAll(str, placeHolder, arguments[i]);
		}

		return str;
	};

	/**
	 * 将首字母换成大写
	 * @param {any} str
	 * 例如：
	 * sc.utils.toPascalCase('china') = 'China'
	 */
	sc.utils.toPascalCase = function(str) {
		if(!str || !str.length) {
			return str;
		}

		if(str.length === 1) {
			return str.charAt(0).toUpperCase();
		}

		return str.charAt(0).toUpperCase() + str.substr(1);
	}

	/**
	 * 将首字母换成小写
	 * @param {any} str
	 * 例如：
	 * sc.utils.toCamelCase('China') = 'china'
	 */
	sc.utils.toCamelCase = function(str) {
		if(!str || !str.length) {
			return str;
		}

		if(str.length === 1) {
			return str.charAt(0).toLowerCase();
		}

		return str.charAt(0).toLowerCase() + str.substr(1);
	}

	/**
	 * 截取字符串
	 * 例如：
	 * sc.utils.truncateString('China',3) = 'Chi'
	 */
	sc.utils.truncateString = function(str, maxLength) {
		if(!str || !str.length || str.length <= maxLength) {
			return str;
		}

		return str.substr(0, maxLength);
	};

	/**
	 * 截取字符串并加上后缀
	 * 例如：
	 * sc.utils.truncateString('China',3) = 'Chi...'
	 */
	sc.utils.truncateStringWithPostfix = function(str, maxLength, postfix) {
		postfix = postfix || '...';

		if(!str || !str.length || str.length <= maxLength) {
			return str;
		}

		if(maxLength <= postfix.length) {
			return postfix.substr(0, maxLength);
		}

		return str.substr(0, maxLength - postfix.length) + postfix;
	};

	/**
	 * Sets a cookie value for given key.
	 * This is a simple implementation created to be used by sc.
	 * Please use a complete cookie library if you need.
	 * @param {string} key
	 * @param {string} value 
	 * @param {Date} expireDate (optional). If not specified the cookie will expire at the end of session.
	 * @param {string} path (optional)
	 */
	sc.utils.setCookieValue = function(key, value, expireDate, path, domain) {
		var cookieValue = encodeURIComponent(key) + '=';

		if(value) {
			cookieValue = cookieValue + encodeURIComponent(value);
		}

		if(expireDate) {
			cookieValue = cookieValue + "; expires=" + expireDate.toUTCString();
		}

		if(path) {
			cookieValue = cookieValue + "; path=" + path;
		}

		if(domain) {
			cookieValue = cookieValue + "; domain=" + domain;
		}

		document.cookie = cookieValue;
	};

	/**
	 * Gets a cookie with given key.
	 * This is a simple implementation created to be used by sc.
	 * Please use a complete cookie library if you need.
	 * @param {string} key
	 * @returns {string} Cookie value or null
	 */
	sc.utils.getCookieValue = function(key) {
		var equalities = document.cookie.split('; ');
		for(var i = 0; i < equalities.length; i++) {
			if(!equalities[i]) {
				continue;
			}

			var splitted = equalities[i].split('=');
			if(splitted.length != 2) {
				continue;
			}

			if(decodeURIComponent(splitted[0]) === key) {
				return decodeURIComponent(splitted[1] || '');
			}
		}

		return null;
	};

	/**
	 * Deletes cookie for given key.
	 * This is a simple implementation created to be used by sc.
	 * Please use a complete cookie library if you need.
	 * @param {string} key
	 * @param {string} path (optional)
	 */
	sc.utils.deleteCookie = function(key, path) {
		var cookieValue = encodeURIComponent(key) + '=';

		cookieValue = cookieValue + "; expires=" + (new Date(new Date().getTime() - 86400000)).toUTCString();

		if(path) {
			cookieValue = cookieValue + "; path=" + path;
		}

		document.cookie = cookieValue;
	}

	/**
	 * 获取给定Url的域名
	 * @param {string} url 
	 * @returns {string} 
	 */
	sc.utils.getDomain = function(url) {
		var domainRegex = /(https?:){0,1}\/\/((?:[\w\d-]+\.)+[\w\d]{2,})/i;
		var matches = domainRegex.exec(url);
		return(matches && matches[2]) ? matches[2] : '';
	}
	/**
	 * 获取当前URL 地址，不带参数
	 * @returns {string} 
	 */
	sc.utils.getCurrentURL = function() {
		return document.URL;
	}

	/**
	 * 获取当前URL 的路径部分，不带参数
	 * @returns {string} 
	 */
	sc.utils.getCurrentPathname = function() {
		return window.location.pathname;
	}

	/**
	 * 对象是否为null、undefined，或者字符串为空值
	 * @param {any} obj
	 */
	sc.utils.isNullOrEmpty = function(obj) {
		if(obj == undefined || obj === "" || obj == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取当前url请求中的参数
	 * 例如
	 * 当前请求 http:m.scmall.com?id=123, sc.utils.getQueryString('id') = '123'
	 */
	sc.utils.getQueryString = function(name) {
		//		name = name.toLowerCase();
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var url = window.location.search.substr(1); //.toLowerCase();
		var r = url.match(reg);
		if(r != null) return unescape(r[2]);
		//		if(r != null) return decodeURI(r[2]);
		return null;
	}
	/**
	 * 获取当前编码的url请求中的参数
	 * 例如
	 * 当前请求 http:m.scmall.com?id=123, sc.utils.getQueryString('id') = '123'
	 */
	sc.utils.getDecodeQueryString = function(name) {
		//		name = name.toLowerCase();
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var url = window.location.search.substr(1); //.toLowerCase();
		url = decodeURIComponent(url);
		var r = url.match(reg);
		if(r != null) return unescape(r[2]);
		//		if(r != null) return decodeURI(r[2]);
		return null;
	}
	/**
	 *
	 * 格式化Date，变成指定的类型
	 * @param {Date} date
	 * @param {string} format
	 * 例如：sc.utils.dateFormat(new Date('2018/08/09 12:08:00'),'MM-dd hh:mm') = '08-09 12:08'
	 * 返回执行格式的string类型
	 */
	sc.utils.dateFormat = function(date, format) {

		/*
		 * format="yyyy-MM-dd hh:mm:ss";
		 */
		if(date == null || date == undefined) {
			return;
		}
		if(!format) format = "yyyy/MM/dd";
		date = new Date(date);
		var o = {
			"M+": date.getMonth() + 1,
			"d+": date.getDate(),
			"h+": date.getHours(),
			"m+": date.getMinutes(),
			"s+": date.getSeconds(),
			"q+": Math.floor((date.getMonth() + 3) / 3),
			"S": date.getMilliseconds()
		}

		if(/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
		}

		for(var k in o) {
			if(new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return format;
	}

	/**
	 *
	 * 时间格式字符串转Date对象
	 * @param {string} dateString
	 * 例如：sc.utils.dateFormat(new Date('2018/08/09 12:08:00'),'MM-dd hh:mm') = '08-09 12:08'
	 * 返回Date对象，出错时返回默认1970/01/01
	 */
	sc.utils.toDate = function(dateString) {
		var arr1 = dateString.replace("T", " ").replace(/-/g, "/");
		try {
			return new Date(arr1);
		} catch(ex) {
			console.log("转换日期失败：");
			console.log(ex);
			return null;
		}
	}

	/* AUTHORIZATION **********************************************/
	//Implements Authorization API that simplifies usage of authorization scripts generated by sc.

	sc.auth = sc.auth || {};

	sc.auth.tokenCookieName = 'Token';
    sc.auth.userIdName = 'UserId';
    sc.auth.userInfoName = 'UserInfo';
    sc.auth.tokenExpiresUtcName = 'TokenExpiresUtc';
	sc.auth.setToken = function(authToken, expireDate) {
		sc.utils.setCookieValue(sc.auth.tokenCookieName, authToken, expireDate, sc.appPath, sc.domain);
	};

	sc.auth.getToken = function() {
		return sc.utils.getCookieValue(sc.auth.tokenCookieName);
	}

	sc.auth.clearToken = function() {
		sc.auth.setToken();
	}

	sc.auth.setUserId = function(userId, expireDate) {
		sc.utils.setCookieValue(sc.auth.userIdName, userId, expireDate, sc.appPath, sc.domain);
	}
	sc.auth.getUserId = function() {
		return sc.utils.getCookieValue(sc.auth.userIdName);
	}
	sc.auth.clearUserId = function() {
		sc.auth.setUserId();
	}
    sc.auth.setUserInfo = function (userInfo, expireDate) {
        sc.utils.setCookieValue(sc.auth.userInfoName, userInfo, expireDate, sc.appPath, sc.domain);
    };

    sc.auth.getUserInfo = function () {
        return sc.utils.getCookieValue(sc.auth.userInfoName);
    }

    sc.auth.clearUserInfo = function () {
        sc.auth.setUserInfo();
    }
    sc.auth.setTokenExpiresUtc = function (authTokenExpiresUtc, expireDate) {
        sc.utils.setCookieValue(sc.auth.tokenExpiresUtcName, authTokenExpiresUtc, expireDate, sc.appPath, sc.domain);
    };

    sc.auth.getTokenExpiresUtc = function () {
        return sc.utils.getCookieValue(sc.auth.tokenExpiresUtcName);
    }

    sc.auth.clearTokenExpiresUtc = function () {
        sc.auth.setTokenExpiresUtc();
    }

    sc.setDefaultCity = function (city, expireDate) {
        sc.utils.setCookieValue('LocalCity', city, expireDate, sc.appPath, sc.domain);
    };

   
	/**
	 * 是否登陆（是否有token）
	 */
	sc.auth.isLogin = function() {
		if(sc.utils.isNullOrEmpty(sc.auth.getToken()) || sc.utils.isNullOrEmpty(sc.auth.getUserId())) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * 判断授权，未授权（登陆）就跳转登陆
	 */
	sc.auth.CheckLogin = function() {
		var islogin = sc.auth.isLogin();
		//检查是否登陆，未登陆直接跳转登陆页
		if(!islogin) {
			window.location.href = "/login.html?returnUrl=" + sc.utils.getCurrentPathname();
			return false;
		}
	}
	/* MESSAGE **************************************************/
	//Defines Message API, not implements it

	sc.message = sc.message || {};

	var showMessage = function(message, title) {
		//alert((title || '') + ' ' + message);

		if(!$) {
			console.warn('消息不能应答，因为jQuery没有定义！');
			return null;
		}

		return $.Deferred(function($dfd) {
			$dfd.resolve();
		});
	};

	sc.message.info = function(message, title) {
		return showMessage(message, title);
	};

	sc.message.success = function(message, title) {
		return showMessage(message, title);
	};

	sc.message.warn = function(message, title) {
		return showMessage(message, title);
	};

	sc.message.error = function(message, title) {
		return showMessage(message, title);
	};

	sc.message.confirm = function(message, titleOrCallback, callback) {
		if(titleOrCallback && !(typeof titleOrCallback == 'string')) {
			callback = titleOrCallback;
		}

		var result = confirm(message);
		callback && callback(result);

		if(!$) {
			console.warn('消息不能应答，因为jQuery没有定义！');
			return null;
		}

		return $.Deferred(function($dfd) {
			$dfd.resolve();
		});
	};

	/* JQUERY ENHANCEMENTS ***************************************************/

	// sc.ajax -> uses $.ajax ------------------------------------------------
	//sc.serverUrl = 'http://api.qianjidaojia.com/';
	sc.serverUrl = '';//'http://localhost:65034/';
	sc.ajax = function(userOptions) {
		userOptions = userOptions || {};

		var options = $.extend(true, {}, sc.ajax.defaultOpts, userOptions);
		var oldBeforeSendOption = options.beforeSend;
		options.beforeSend = function(xhr) {
			if(oldBeforeSendOption) {
				oldBeforeSendOption(xhr);
			}

			xhr.setRequestHeader("Pragma", "no-cache");
			xhr.setRequestHeader("Cache-Control", "no-cache");
			xhr.setRequestHeader("Expires", "Sat, 01 Jan 2000 00:00:00 GMT");

			var token = sc.auth.getToken();
			if(!sc.utils.isNullOrEmpty(token)) {
				xhr.setRequestHeader("Authorization", 'Bearer ' + sc.auth.getToken());
			}
			xhr.withCredentials = false;
		};

		options.success = undefined;
		options.error = undefined;
		options.url = sc.serverUrl + options.url;
		if(options.type.toUpperCase() != 'GET') {
			options.data = JSON.stringify(userOptions.data);
		}
		return $.Deferred(function($dfd) {
			$.ajax(options)
				.done(function(data, textStatus, jqXHR) {
					if(data.__abp) {
						sc.ajax.handleResponse(data, userOptions, $dfd, jqXHR);
					} else {
						$dfd.resolve(data);
						userOptions.success && userOptions.success(data);
					}
				}).fail(function(jqXHR) {
					console.log(arguments);
					if(jqXHR.responseJSON && jqXHR.responseJSON.__abp) {
						sc.ajax.handleResponse(jqXHR.responseJSON, userOptions, $dfd, jqXHR);
					} else {
						sc.ajax.handleNonscErrorResponse(jqXHR, userOptions, $dfd);
					}
				});
		});
	};

	$.extend(sc.ajax, {
		defaultOpts: {
			dataType: 'json',
			type: 'POST',
			contentType: 'application/json',
			headers: {
				'X-Requested-With': 'XMLHttpRequest'
			}
		},

		defaultError: {
			message: '发生了错误!',
			details: '错误详情未知。'
		},

		defaultError401: {
			message: '您的请求未认证！',
			details: '如需执行这个请求，您需要先进行身份认证（登录）。'
		},

		defaultError403: {
			message: '您的请求未授权！',
			details: '您不允许执行这个请求。'
		},

		defaultError404: {
			message: '资源未找到！',
			details: '所请求的资源在服务器上未找到。'
		},

		logError: function(error) {
			console.error(error);
		},

		showError: function(error) {
			if(error.details) {
				return sc.message.error(error.details, error.message);
			} else {
				return sc.message.error(error.message || sc.ajax.defaultError.message);
			}
		},

		handleTargetUrl: function(targetUrl) {
			if(!targetUrl) {
				location.href = sc.appPath;
			} else {
				location.href = targetUrl;
			}
		},

		handleNonscErrorResponse: function(jqXHR, userOptions, $dfd) {
			if(userOptions.scHandleError !== false) {
				switch(jqXHR.status) {
					case 401:
						sc.ajax.handleUnAuthorizedRequest(
							sc.ajax.showError(sc.ajax.defaultError401),
							sc.appPath
						);
						break;
					case 403:
						sc.ajax.showError(sc.ajax.defaultError403);
						break;
					case 404:
						sc.ajax.showError(sc.ajax.defaultError404);
						break;
					default:
						sc.ajax.showError(sc.ajax.defaultError);
						break;
				}
			}

			$dfd.reject.apply(this, arguments);
			userOptions.error && userOptions.error.apply(this, arguments);
		},

		handleUnAuthorizedRequest: function(messagePromise, targetUrl) {
			sc.auth.clearToken();
			sc.utils.deleteCookie(sc.auth.tokenCookieName, sc.appPath);
			sc.auth.clearUserId();
			sc.utils.deleteCookie(sc.auth.userIdName, sc.appPath);

			//			alert("未登录");
			//           $.toast("验证码不能为空！", "text");
			location.href = "/login.html";
			//console.log(messagePromise);
			//      	return;
			//          if (messagePromise) {
			//              messagePromise.done(function () {
			//                  sc.ajax.handleTargetUrl(targetUrl);
			//              });
			//          } else {
			//              sc.ajax.handleTargetUrl(targetUrl);
			//          }
		},

		handleResponse: function(data, userOptions, $dfd, jqXHR) {
			if(data) {
				if(data.success === true) {
					$dfd && $dfd.resolve(data.result, data, jqXHR);
					userOptions.success && userOptions.success(data.result, data, jqXHR);

					if(data.targetUrl) {
						sc.ajax.handleTargetUrl(data.targetUrl);
					}
				} else if(data.success === false) {
					var messagePromise = null;

					if(data.error) {
						if(userOptions.scHandleError !== false) {
							messagePromise = sc.ajax.showError(data.error);
						}
					} else {
						data.error = sc.ajax.defaultError;
					}

					sc.ajax.logError(data.error);

					$dfd && $dfd.reject(data.error, jqXHR);
					userOptions.error && userOptions.error(data.error, jqXHR);

					if(jqXHR.status === 2 && userOptions.scHandleError !== false) {
						sc.ajax.handleUnAuthorizedRequest(messagePromise, data.targetUrl);
					}
				} else { //not wrapped result
					$dfd && $dfd.resolve(data, null, jqXHR);
					userOptions.success && userOptions.success(data, null, jqXHR);
				}
			} else { //no data sent to back
				$dfd && $dfd.resolve(jqXHR);
				userOptions.success && userOptions.success(jqXHR);
			}
		}
	});

	/**
	 * /
	 * @param {any} url  请求接口，不用带域名
	 * @param {any} data 请求参数
	 * @param {any} callback 成功或者失败之后回调
	 * @param {any} parameter 回调传参
	 */
	sc.get = function(url, data, success, error, parameter) {
		sc.ajax({
			type: 'get',
			url: url,
			data: data,
			cache: false,
			async: true,
			success: function(data) {
				if($.isFunction(success)) {
					success(data, parameter);
				}
			},
			error: function(data) {
				if($.isFunction(error)) {
					error(data, parameter);
				}
			}
		});
	}
	/**
	 * /
	 * @param {any} url  请求接口，不用带域名
	 * @param {any} data 请求参数
	 * @param {any} callback 成功或者失败之后回调
	 * @param {any} parameter 回调传参
	 */
	sc.post = function (url, data, success, error, parameter) {
		sc.ajax({
			type: 'post',
			url: url,
			data: data,
			cache: false,
			async: true,
			success: function(data) {
				if($.isFunction(success)) {
					success(data, parameter);
				}
			},
			error: function(data) {
				if($.isFunction(error)) {
					error(data, parameter);
				}
			}
		});
	}

	/**
	 * 登陆
	 * @param {any} username 用户名或者手机号
	 * @param {any} password 登陆密码
	 */
	sc.login = function(username, password, gotoType) {
		var url = 'api/Login/LoginByMobile';
		var options = {
			type: 'post',
			url: url,
			data: {
				"Mobile": username,
                "PassWord": password,
                "UserInfo": sc.auth.getUserInfo()
			},
			cache: false,
			async: true,
			success: function(data) {
				if(data.code == 0) {
					sc.auth.setToken(data.data.Token);
                    sc.auth.setUserId(data.data.UserId);
					layer.open({
						content: '登录成功!',
						skin: 'msg',
						time: 2 //2秒后自动关闭
					});

					if(document.referrer) {
						if(gotoType == 1) {
							setTimeout(function() {
                                window.location.href = "/member/newpeople.html";
							}, 2000);
						} else {
							setTimeout(function() {
								window.location.href = document.referrer;
							}, 2000);
						}

					} else {
						setTimeout(function() {
							window.location.href = "/member/member.html";
						}, 2000);
					}
				} else if(data.code == 1) {
					layer.open({
						content: '登录失败，账户名或密码错误!',
						skin: 'msg',
						time: 2 //2秒后自动关闭
					});

				}else if(data.code == 2){
					layer.open({
						content: data.msg,
						skin: 'msg',
						time: 2 //2秒后自动关闭
					});
				}
			},
			error: function(data) {
				if($.isFunction(error)) {
					error(data);
				}
			}
		}
		sc.ajax(options);
	}

	/**
	 * 登出
	 */
	sc.logout = function() {
		sc.auth.clearToken();
		sc.utils.deleteCookie(sc.auth.tokenCookieName, sc.appPath);
		sc.auth.clearUserId();
		sc.utils.deleteCookie(sc.auth.userIdName, sc.appPath);
		window.location.href = "/indexV.html";
	}

    //sc.fileServerUrl = 'https://mall.qianjidaojia.com';
    sc.fileServerUrl = 'http://localhost:40085/';
	/**
	 * 获取文件信息里面的标准文件路径（已拼接域名，默认和接口使用一个域名
	 * @param {any} fileinfo
	 */
	sc.getFileNormalPath = function(fileinfo) {
		if(!fileinfo || !fileinfo.normalPath) {
			return '';
		}
		var domain = sc.utils.isNullOrEmpty(sc.fileServerUrl) ? sc.serverUrl : sc.fileServerUrl;
		return domain + fileinfo.normalPath;
	}

	/**
	 * 替换内容中的url链接，查找 src=\"/upload/  并替换成 src=\"{domain}/upload/
	 * @param {any} content
	 */
	sc.replaceContentUrl = function(content) {
		var domain = sc.utils.isNullOrEmpty(sc.fileServerUrl) ? sc.serverUrl : sc.fileServerUrl;
		var patternStr = "<img\\s*([^>]*)\\s*src=\\\"(.*?)\\\"\\s*([^>]*)>";
		var reg = new RegExp(patternStr, "g");
		var matcher = content.match(reg);
		if(matcher.length > 0) {
			for(var i = 0; i < matcher.length; i++) {
				var reg1 = /src=[\'\"]?([^\'\"]*)[\'\"]?/i;
				var srcs = matcher[i].match(reg1);
				var src = srcs[1];
				console.debug("pattern string:" + src);
				var replaceSrc = "";
				if(!src.startsWith("http://") && !src.startsWith("https://")) {
					replaceSrc = domain + src;
				}
				content = content.replace(src, replaceSrc);
			}
		}
		return content;
	}

	/**
	 * 
	 */
	sc.slicePicString = function(content) {
		var arr = [];
		if(typeof content == "string") {
			if(content !==""){
				arr = content.split(",");
			}
			
		}
		return arr;
	}
	
	//银行卡验证
    sc.luhmCheck = function (bankno) {
        var lastNum = bankno.substr(bankno.length - 1, 1); //取出最后一位（与luhm进行比较）
        var first15Num = bankno.substr(0, bankno.length - 1); //前15或18位
        var newArr = new Array();
        for (var i = first15Num.length - 1; i > -1; i--) {    //前15或18位倒序存进数组
            newArr.push(first15Num.substr(i, 1));
        }
        var arrJiShu = new Array();  //奇数位*2的积 <9
        var arrJiShu2 = new Array(); //奇数位*2的积 >9
        var arrOuShu = new Array();  //偶数位数组
        for (var j = 0; j < newArr.length; j++) {
            if ((j + 1) % 2 == 1) {//奇数位
                if (parseInt(newArr[j]) * 2 < 9)
                    arrJiShu.push(parseInt(newArr[j]) * 2);
                else
                    arrJiShu2.push(parseInt(newArr[j]) * 2);
            }
            else //偶数位
                arrOuShu.push(newArr[j]);
        }

        var jishu_child1 = new Array(); //奇数位*2 >9 的分割之后的数组个位数
        var jishu_child2 = new Array(); //奇数位*2 >9 的分割之后的数组十位数
        for (var h = 0; h < arrJiShu2.length; h++) {
            jishu_child1.push(parseInt(arrJiShu2[h]) % 10);
            jishu_child2.push(parseInt(arrJiShu2[h]) / 10);
        }

        var sumJiShu = 0; //奇数位*2 < 9 的数组之和
        var sumOuShu = 0; //偶数位数组之和
        var sumJiShuChild1 = 0; //奇数位*2 >9 的分割之后的数组个位数之和
        var sumJiShuChild2 = 0; //奇数位*2 >9 的分割之后的数组十位数之和
        var sumTotal = 0;
        for (var m = 0; m < arrJiShu.length; m++) {
            sumJiShu = sumJiShu + parseInt(arrJiShu[m]);
        }

        for (var n = 0; n < arrOuShu.length; n++) {
            sumOuShu = sumOuShu + parseInt(arrOuShu[n]);
        }

        for (var p = 0; p < jishu_child1.length; p++) {
            sumJiShuChild1 = sumJiShuChild1 + parseInt(jishu_child1[p]);
            sumJiShuChild2 = sumJiShuChild2 + parseInt(jishu_child2[p]);
        }
        //计算总和
        sumTotal = parseInt(sumJiShu) + parseInt(sumOuShu) + parseInt(sumJiShuChild1) + parseInt(sumJiShuChild2);
        //计算Luhm值
        var k = parseInt(sumTotal) % 10 == 0 ? 10 : parseInt(sumTotal) % 10;
        var luhm = 10 - k;
        if (lastNum == luhm) {
            //        $("#banknoInfo").html("Luhm验证通过");
            return true;
        }
        else {
            //        $("#banknoInfo").html("银行卡号必须符合Luhm校验");
            return false;
        }
    }

})(jQuery);