(function( $ ){
    // 当domReady的时候开始初始化
    $(function () {
        var filePicObj = {};
        var $wrap = $('#uploader'),

            // 图片容器
            $queue = $('<ul class="filelist"></ul>')
                .appendTo($wrap.find('.queueList')),

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
            auto: true,
            pick: {
                id: '#filePicker',
                label: '<i class="fa fa-picture-o" aria-hidden="true"></i>'
            },
            formData: {
                DataId: 123
            },
            dnd: '#dndArea',
            paste: '#uploader',
            swf: 'Uploader.swf',
            chunked: true,
            chunkSize: 512 * 1024,
            server: sc.serverUrl+ '/System/pic_file_upload',
            // runtimeOrder: 'flash',
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
                 extensions: 'jpg,jpeg,bmp,png',
                 mimeTypes: 'image/*'
             },

            // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
            disableGlobalDnd: true,
            fileNumLimit: 3, //上传文件的数量限制
            fileSizeLimit: 30 * 1024 * 1024,    // 30 M
            fileSingleSizeLimit: 10 * 1024 * 1024    // 10 M
            //fileSizeLimit: 200 * 1024 * 1024,    // 200 M
            //fileSingleSizeLimit: 50 * 1024 * 1024    // 50 M
        });


        uploader.on('dialogOpen', function () {
            console.log('here');
        });
          
        uploader.on('ready', function() {
            window.uploader = uploader;
        });
        // 当有文件添加进来时执行，负责view的创建
        function addFile( file ) {
            var $li = $('<li id="' +
                    file.id +
                    '">' +
                    '<p class="imgWrap"></p>' +
                    '</li>'),

                $btns = $('<div class="file-panel"><span class="cancel">X</span>').appendTo($li),
                $wrap = $li.find('p.imgWrap'),
                $info = $('<p class="error"></p>');
                console.log($wrap);
                var text = '';
                var showError = function( code ) {
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
            console.log(file);
            if ( file.getStatus() === 'invalid' ) {
                showError( file.statusText );
            } else {
                // @todo lazyload
                $wrap.text( '预览中' );
                uploader.makeThumb( file, function( error, src ) {
                    var img;

                    if ( error ) {
                        $wrap.text( '不能预览' );
                        return;
                    }

                    if( isSupportBase64 ) {
                        img = $('<img src="' + src + '">');
                        console.log($wrap);
                        $wrap.empty().append(img);
                        console.log(888)
                    } else {
                        $.ajax('preview.ashx', {
                            method: 'POST',
                            data: src,
                            dataType:'json'
                        }).done(function(response) {
                            if (response.result) {
                                img = $('<img src="'+response.result+'">');
                                $wrap.empty().append(img);
                            } else {
                                $wrap.text("预览出错");
                            }
                        });
                    }
                }, thumbnailWidth, thumbnailHeight );

                percentages[ file.id ] = [ file.size, 0 ];
                file.rotation = 0;
            

            }

            file.on('statuschange', function( cur, prev ) {
               
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
                    //$prgress.css('display', 'block');
                } else if ( cur === 'complete' ) {
                    //$li.append( '<span class="success"></span>' );
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
                }               

            });

            $li.appendTo( $queue );
        }

        // 负责view的销毁
        function removeFile(file) {
            var $li = $('#' + file.id);
            delete filePicObj[file.id];
            //updateTotalProgress();
            //updateStatus();
            console.log(file);
            $li.off().find('.file-panel').off().end().remove();
            console.log(filePicObj)
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

        /*function updateStatus() {
            var text = '', stats;

            if ( state === 'ready' ) {
                text = '选中' + fileCount + '张图片，共' +
                    WebUploader.formatSize(fileSize);
                console.log('updateStatus');
            } else if ( state === 'confirm' ) {
                stats = uploader.getStats();
                if ( stats.uploadFailNum ) {
                    text = '已成功上传' + stats.successNum+ '张照片至XX相册，'+
                        stats.uploadFailNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>';
                }
                console.log('updateStatus2');

            } else {
                stats = uploader.getStats();
                text = '共' + fileCount + '张（' +
                        WebUploader.formatSize( fileSize )  +
                        '），已上传' + stats.successNum + '张';

                if ( stats.uploadFailNum ) {
                    text += '，失败' + stats.uploadFailNum + '张';
                }
                console.log('updateStatus3');
            }

            $info.html( text );
        }*/

        function setState(val) {
            
            console.log(val);
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
                    break;

                case 'ready':
                    $placeHolder.addClass( 'element-invisible' );
                    $( '#filePicker' ).removeClass( 'element-invisible');
                    $queue.show();
                    $statusBar.removeClass('element-invisible');
                    uploader.refresh();
                    console.log('ready--')
                    break;

                case 'uploading':
                    $( '#filePicker' ).addClass( 'element-invisible' );
                    $progress.show();
                    $upload.text( '暂停上传' );
                    break;

                case 'paused':
                    $progress.show();
                    $upload.text( '继续上传' );
                    break;

                case 'confirm':
                    $progress.hide();
                    $( '#filePicker' ).removeClass( 'element-invisible' );
                    $upload.text( '开始上传' );

                    stats = uploader.getStats();
                    if ( stats.successNum && !stats.uploadFailNum ) {
                        setState('finish');
                        //alert('上传成功');
                        return;
                    }
                    break;
                case 'finish':
                    stats = uploader.getStats();
                    if ( stats.successNum ) {
                        //alert('上传成功222');
                        //LayerTipsLink("上传成功", "/JoinShop/admin/orderdetail.aspx?id=" + dataId);
                        console.log(dataId)
                        $('#logo')
                    } else {
                        // 没有成功的图片，重设
                        state = 'done';
                        location.reload();
                    }
                    break;
            }

            //updateStatus();
        }

        /*uploader.onUploadProgress = function (file, percentage) {           
            var $li = $('#'+file.id),
                $percent = $li.find('.progress span');

            $percent.css( 'width', percentage * 100 + '%' );
            percentages[ file.id ][ 1 ] = percentage;
            //updateTotalProgress();
        };*/

        uploader.onFileQueued = function( file ) {
            fileCount++;
            fileSize += file.size;

            if ( fileCount === 1 ) {
                $placeHolder.addClass( 'element-invisible' );
                $statusBar.show();
            }
            console.log('qqq');
            addFile( file );
            setState( 'ready' );
            //updateTotalProgress();
        };

        uploader.onFileDequeued = function( file ) {
            fileCount--;
            fileSize -= file.size;

            /*if ( !fileCount ) {
                setState('pedding');
                console.log('77888')
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
           
            filePicObj[file.id] = response.filekey;
            console.log(filePicObj);
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
        window.filePicObj = filePicObj;
    });
    
})( jQuery );