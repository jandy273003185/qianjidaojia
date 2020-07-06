(function( $ ){
    // 当domReady的时候开始初始化
    $(function () {
        var fileVideoObj = {};
        var $wrap = $('#uploader2'),

            // 图片容器
            $queue = $('<ul class="filelist"></ul>')
                .appendTo($wrap.find('.queueList2')),

            // 状态栏，包括进度和控制按钮
            $statusBar = $wrap.find('.statusBar'),

            // 文件总体选择信息。
            $info = $statusBar.find('.info'),

            // 上传按钮
            $upload = $wrap.find('.uploadBtn'),

            // 没选择文件之前的内容。
            $placeHolder = $wrap.find('.placeholder'),

            $progress = $statusBar.find('.progress').hide(),

            // 添加的文件数量
            fileCount = 0,

            // 添加的文件总大小
            fileSize = 0,

            // 优化retina, 在retina下这个值是2
            ratio = window.devicePixelRatio || 1,

            // 缩略图大小
            thumbnailWidth = 110 * ratio,
            thumbnailHeight = 110 * ratio,

            // 可能有pedding, ready, uploading, confirm, done.
            state = 'pedding',

            // 所有文件的进度信息，key为file id
            percentages = {},
            // 判断浏览器是否支持图片的base64
            isSupportBase64 = (function() {
                var data = new Image();
                var support = true;
                data.onload = data.onerror = function() {
                    if (this.width != 1 || this.height != 1) {
                        support = false;
                    }
                }
                data.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
                return support;
            })(),

            

            // WebUploader实例
            uploader;
            //GUID = WebUploader.Base.guid(); //当前页面是生成的GUID作为标示;

        
            
        // 实例化
        uploader = WebUploader.create({
            pick: {
                id: '#filePicker2',
                label: '<i class="fa fa-file-video-o" aria-hidden="true"></i>'
            },
            formData: {
                DataId: 123
            },
            auto: true,
            paste: '#uploader2',
            swf: 'Uploader.swf',
            chunked: true,
            chunkSize: 2 * 1024 * 1024,
            server: sc.serverUrl+ '/System/pic_file_upload',
            chunkRetry: 2,
            duplicate: false,
            resize: true,
            runtimeOrder: 'html5',
            //formData: { guid: GUID },
            //compress: false, //图片在上传前不进行压缩
            compress: {
                width: 1000,
                height: 1000,
                quality: 95,
                allowMagnify: false,
                crop: false,
                preserveHeaders: true,
                noCompressIfLarger: false,
                compressSize: 524288
            },
            threads: 1, //上传并发数
            method: 'POST',

             accept: {
                 title: '上传',
                 extensions: '3gp,mp4,rmvb,mov,avi,m4v',
                 mimeTypes: 'video/*'
             },

            // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
            disableGlobalDnd: true,
            fileNumLimit: 1, //上传文件的数量限制
            fileSizeLimit: 30 * 1024 * 1024,    // 30 M
            fileSingleSizeLimit: 30 * 1024 * 1024    // 30 M
            //fileSizeLimit: 200 * 1024 * 1024,    // 200 M
            //fileSingleSizeLimit: 50 * 1024 * 1024    // 50 M
        });

        // 拖拽时不接受 js, txt 文件。
        /*uploader.on( 'dndAccept', function( items ) {
            var denied = false,
                len = items.length,
                i = 0,
                // 修改js类型
                unAllowed = 'text/plain;application/javascript ';

            for ( ; i < len; i++ ) {
                // 如果在列表里面
                if ( ~unAllowed.indexOf( items[ i ].type ) ) {
                    denied = true;
                    break;
                }
            }

            return !denied;
        });*/

        uploader.on('dialogOpen', function () {
            console.log('here');
        });

        // uploader.on('filesQueued', function() {
        //     uploader.sort(function( a, b ) {
        //         if ( a.name < b.name )
        //           return -1;
        //         if ( a.name > b.name )
        //           return 1;
        //         return 0;
        //     });
        // });

       

        uploader.on('ready', function() {
            window.uploader = uploader;
        });

        // 当有文件添加进来时执行，负责view的创建
        function addFile( file ) {
            var $li = $( '<li id="' + file.id + '">' +                   
                    '<p class="imgWrap videoWrap"></p>'+                   
                    '</li>' ),

                $btns = $('<div class="file-panel">' +
                    '<span class="cancel">X</span>').appendTo( $li ),
                $prgress = $li.find('p.progress span'),
                $wrap = $li.find( 'p.videoWrap' ),
                $info = $('<p class="error"></p>')

                showError = function( code ) {
                    switch( code ) {
                        case 'exceed_size':
                            text = '文件大小超出';
                            break;

                        case 'interrupt':
                            text = '上传暂停';
                            break;

                        default:
                            text = '上传失败，请重试';
                            break;
                    }

                    $info.text( text ).appendTo( $li );
                };

            if ( file.getStatus() === 'invalid' ) {
                showError( file.statusText );
            } else {
                // @todo lazyload
                
                /*uploader.makeThumb( file, function( error, src ) {
                    var video;

                    if ( error ) {
                        $wrap.text( '不能预览' );
                        return;
                    }

                    if( isSupportBase64 ) {
                        video = $('<video src="'+src+'"></video>');
                        $wrap.empty().append(video);
                    } else {
                        $.ajax('preview.ashx', {
                            method: 'POST',
                            data: src,
                            dataType:'json'
                        }).done(function(response) {
                            if (response.result) {
                                video = $('<video src="'+response.result+'"></video>');
                                $wrap.empty().append(video);
                            } else {
                                $wrap.text("预览出错");
                            }
                        });
                    }
                }, thumbnailWidth, thumbnailHeight );*/
                
                    // 建议判断下视频大小及格式，太大的可能会有问题
                    
                var reader = new FileReader();
                
                    reader.readAsDataURL(file.source.source);
                    reader.onload = function(evt){                     
                        $('.videoWrap').append($('<video src="'+evt.target.result+'" preload="auto" controls="controls" id="video"></video>'));
                        //$('.videoWrap').append($('<div id="videoMask"></div>'));                     
                        //initialize();
                    };
                    
                    
                    
                    /*var videoMask,video;
                    var initialize = function() {
                        
                        console.log($('#video').get(0))
                        videoMask = document.getElementById("videoMask");
                        video = document.getElementById("video");
                        video.addEventListener('loadeddata', function() {
                            var canvas = document.createElement("canvas");
                            canvas.width = video.videoWidth;
                            canvas.height = video.videoWidth;
                            canvas.getContext('2d').drawImage(video, 0, 0, canvas.width,
                                    canvas.height);
            
                            var img = document.createElement("img");
                            img.src = canvas.toDataURL("image/png");
                            img.height = 200;
                            video.poster=img.src;
                            videoMask.appendChild(img);
                            this.play();
                            videoPlay()
                        });
                    };

                    var videoPlay = function () {
                        $('#videoMask').on('click',function(){
                            $(this).css('display','none');
                            $('#video').css('display','block');
                            $('#video').get(0).play();
                        })
                    }*/
                                                               
                console.log(file)
                percentages[ file.id ] = [ file.size, 0 ];
                file.rotation = 0;
                
            }

            file.on('statuschange', function( cur, prev ) {
                /*if ( prev === 'progress' ) {
                    $prgress.hide().width(0);
                } else if ( prev === 'queued' ) {
                    $li.off( 'mouseenter mouseleave' );
                    $btns.remove();
                }*/

                // 成功
                if ( cur === 'error' || cur === 'invalid' ) {
                    console.log( file.statusText );
                    showError( file.statusText );
                    percentages[ file.id ][ 1 ] = 1;
                } else if ( cur === 'interrupt' ) {
                    showError( 'interrupt' );
                } else if ( cur === 'queued' ) {
                    percentages[ file.id ][ 1 ] = 0;
                } else if ( cur === 'progress' ) {
                    $info.remove();
                    $prgress.css('display', 'block');
                } else if ( cur === 'complete' ) {
                    $li.append( '<span class="success"></span>' );
                }

                $li.removeClass( 'state-' + prev ).addClass( 'state-' + cur );
            });        
            $btns.on( 'click', 'span', function() {
                var index = $(this).index(),
                    deg;

                switch ( index ) {
                    case 0:
                        uploader.removeFile( file );
                        return;

                    case 1:
                        file.rotation += 90;
                        break;

                    case 2:
                        file.rotation -= 90;
                        break;
                }

                if ( supportTransition ) {
                    deg = 'rotate(' + file.rotation + 'deg)';
                    $wrap.css({
                        '-webkit-transform': deg,
                        '-mos-transform': deg,
                        '-o-transform': deg,
                        'transform': deg
                    });
                } else {
                    $wrap.css( 'filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation='+ (~~((file.rotation/90)%4 + 4)%4) +')');
                    // use jquery animate to rotation
                    // $({
                    //     rotation: rotation
                    // }).animate({
                    //     rotation: file.rotation
                    // }, {
                    //     easing: 'linear',
                    //     step: function( now ) {
                    //         now = now * Math.PI / 180;

                    //         var cos = Math.cos( now ),
                    //             sin = Math.sin( now );

                    //         $wrap.css( 'filter', "progid:DXImageTransform.Microsoft.Matrix(M11=" + cos + ",M12=" + (-sin) + ",M21=" + sin + ",M22=" + cos + ",SizingMethod='auto expand')");
                    //     }
                    // });
                }


            });

            $li.appendTo( $queue );
        }

        // 负责view的销毁
        function removeFile( file ) {
            var $li = $('#'+file.id);

            delete fileVideoObj[file.id];
            //updateTotalProgress();
            $li.off().find('.file-panel').off().end().remove();
            console.log(fileVideoObj)
        }

        /*function updateTotalProgress() {
            var loaded = 0,
                total = 0,
                spans = $progress.children(),
                percent;

            $.each( percentages, function( k, v ) {
                total += v[ 0 ];
                loaded += v[ 0 ] * v[ 1 ];
            } );

            percent = total ? loaded / total : 0;


            spans.eq( 0 ).text( Math.round( percent * 100 ) + '%' );
            spans.eq( 1 ).css( 'width', Math.round( percent * 100 ) + '%' );
            updateStatus();
        }*/

        function updateStatus() {
            var text = '', stats;

            if ( state === 'ready' ) {
                text = '选中' + fileCount + '张图片，共' +
                        WebUploader.formatSize( fileSize ) + '。';
            } else if ( state === 'confirm' ) {
                stats = uploader.getStats();
                if ( stats.uploadFailNum ) {
                    text = '已成功上传' + stats.successNum+ '张照片至XX相册，'+
                        stats.uploadFailNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>'
                }

            } else {
                stats = uploader.getStats();
                text = '共' + fileCount + '张（' +
                        WebUploader.formatSize( fileSize )  +
                        '），已上传' + stats.successNum + '张';

                if ( stats.uploadFailNum ) {
                    text += '，失败' + stats.uploadFailNum + '张';
                }
            }

            $info.html( text );
        }

        function setState( val ) {
            var file, stats;

            if ( val === state ) {
                return;
            }
            $upload.removeClass( 'state-' + state );
            $upload.addClass( 'state-' + val );
            state = val;

            var dataId = $("#thisId").val();

            switch ( state ) {
                case 'pedding':
                    $placeHolder.removeClass( 'element-invisible' );
                    $queue.hide();
                    $statusBar.addClass( 'element-invisible' );
                    uploader.refresh();
                    alert('pedding');
                    break;

                case 'ready':
                    $placeHolder.addClass( 'element-invisible' );
                    $( '#filePicker2' ).removeClass( 'element-invisible');
                    $queue.show();
                    $statusBar.removeClass('element-invisible');
                    uploader.refresh();
                    console.log('ready');
                    break;

                case 'uploading':
                    $( '#filePicker2' ).addClass( 'element-invisible' );
                    $progress.show();
                    $upload.text('暂停上传');
                    alert('暂停上传');
                    break;

                case 'paused':
                    $progress.show();
                    $upload.text('继续上传');
                    alert('继续上传');
                    break;

                case 'confirm':
                    $progress.hide();
                    $( '#filePicker2' ).removeClass( 'element-invisible' );
                    $upload.text( '开始上传' );

                    stats = uploader.getStats();
                    if ( stats.successNum && !stats.uploadFailNum ) {
                        setState( 'finish' );
                        return;
                    }
                    break;
                case 'finish':
                    stats = uploader.getStats();
                    if ( stats.successNum ) {
                        alert('上传成功');
                        //LayerTipsLink("上传成功", "/JoinShop/admin/orderdetail.aspx?id=" + dataId);
                    } else {
                        // 没有成功的图片，重设
                        state = 'done';
                        location.reload();
                    }
                    break;
            }

            updateStatus();
        }

        /*uploader.onUploadProgress = function( file, percentage ) {
            var $li = $('#'+file.id),
                $percent = $li.find('.progress span');

            $percent.css( 'width', percentage * 100 + '%' );
            percentages[ file.id ][ 1 ] = percentage;
            updateTotalProgress();
        };*/

        uploader.onFileQueued = function( file ) {
            fileCount++;
            fileSize += file.size;

            if ( fileCount === 1 ) {
                $placeHolder.addClass( 'element-invisible' );
                $statusBar.show();
            }
            console.log('qqq222');
            addFile( file );
            setState( 'ready' );
            //updateTotalProgress();
        };

        uploader.onFileDequeued = function( file ) {
            fileCount--;
            fileSize -= file.size;

            /*if ( !fileCount ) {
                setState( 'pedding' );
            }*/

            removeFile( file );
            //updateTotalProgress();

        };

        /*uploader.on( 'all', function( type ) {
            var stats;
            switch( type ) {
                case 'uploadFinished':
                    setState( 'confirm' );
                    break;

                case 'startUpload':
                    setState( 'uploading' );
                    break;

                case 'stopUpload':
                    setState( 'paused' );
                    break;

            }
        });*/

        // 文件上传成功,合并文件。
        uploader.on('uploadSuccess', function (file, response) {
        
            fileVideoObj[file.id] = response.filekey;
            console.log(fileVideoObj);
            if (response.chunked) {
                $.post("MergeFiles.ashx", { guid: GUID, fileExt: response.f_ext },
                    function (data) {
                        data = $.parseJSON(data);
                        if (data.hasError) {
                            alert('文件合并失败！');
                        } else {
                            alert(decodeURIComponent(data.savePath));
                        }
                    });
            }
        });

        uploader.onError = function( code ) {
            alert( 'Eroor: ' + code );
        };

        $upload.on('click', function () {
            var dataId = $("#thisId").val();
            var imgFlag = $("#imgFlag").val();
            //var imgUrl = "/upload/idcard/dataId/123.jpg";

            var obj = new Object();
            obj.DataId = dataId;
            obj.ImgFlag = imgFlag;
            //obj.ImgUrl = imgUrl;
            uploader.options.formData = obj;

            if ( $(this).hasClass( 'disabled' ) ) {
                return false;
            }

            if ( state === 'ready' ) {
                uploader.upload();
            } else if ( state === 'paused' ) {
                uploader.upload();
            } else if ( state === 'uploading' ) {
                uploader.stop();
            }
        });

        $info.on( 'click', '.retry', function() {
            uploader.retry();
        } );

        $info.on( 'click', '.ignore', function() {
            alert( 'todo' );
        } );

        $upload.addClass( 'state-' + state );
        //updateTotalProgress();
        window.fileVideoObj = fileVideoObj;
    });
})( jQuery );