package com.sevenpay.agentmanager.biz.old.controller;

import com.alibaba.fastjson.JSONObject;
import com.sevenpay.agentmanager.common.utils.YouTuUtils;
import com.sevenpay.agentmanager.core.bean.ResultData;
import com.sevenpay.agentmanager.core.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.gyzb.platform.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.UUID;

/**
 * 文件上传和优图解析
 */
@Controller
@RequestMapping("common")
public class UploadFileController {
    private static final Logger logger = LoggerFactory.getLogger(UploadFileController.class);

    @Value("${images.uri}")
    private String uri;
    @Value("${images.relativePath}")
    private String relativePath;
    //存储路径
    @Value("${images.absolutePaths}")
    private String absolutePaths;


    @PostMapping("upload")
    @ResponseBody
    public ResultData fileUpload(@RequestParam("file") MultipartFile file) {

        // 获取文件名后缀名
        String suffix = file.getOriginalFilename();
        String prefix = suffix.substring(suffix.lastIndexOf("."));
        String Filename = DateUtils.getDateStr8() + "_" + UUID.randomUUID().toString().replaceAll("-", "");
        if (!file.isEmpty()) {//文件不为空
            try {
                //上传路径
                StringBuilder filePath = new StringBuilder(absolutePaths).append("/").append(Filename).append(prefix);
                File saveDir = new File(String.valueOf(filePath));
                // 转存文件
                file.transferTo(saveDir);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("imagePath", Filename + prefix);
                jsonObject.put("uri", uri);
                jsonObject.put("url", new StringBuilder(relativePath).append(Filename).append(prefix));
                return ResultData.success(jsonObject.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("上传失败");
            }
        }
        logger.error("上传文件为空");
        return ResultData.error("网络延迟，请重新提交");

    }

    /**
     * 优图解析，并上传到本地
     *
     * @return
     */
    @PostMapping("youTu")
    @ResponseBody
    public ResultData youTu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("********************获取图片内容********************");
        JSONObject object = new JSONObject();
        try {
            //优图工具
            YouTuUtils youto = new YouTuUtils();
            //文件base64字符串
            String base64String = request.getParameter("str");
            //图片标识
            String str = base64String.substring(base64String.lastIndexOf(",") + 1);
            String flag = request.getParameter("flag");
            logger.info("********************图片上传中********************");
            //图片上传，返回路径
            String[] resultMsg = BASE64CodeToBeImage(base64String);
            logger.info("********************图片解析中********************");
            //解析图片，返回图片信息
            object = youto.youTu(str, flag);
            logger.info("********************图片解析成功********************");
            if (!StringUtils.isBlank(resultMsg[1])) {
                object.put("imagePath", resultMsg[1]);
                object.put("url", new StringBuilder(relativePath).append(resultMsg[1]));
            }
            object.put("uri", uri);
            return ResultData.success(object);
        } catch (Exception e) {
            logger.error("解析图片出现问题" + e);
            throw new BizException("解析图片出现问题" + e.getMessage());
        }
    }

    /**
     * base64转图片
     *
     * @param str bas64字符串
     * @return 存储地址
     */
    public String[] BASE64CodeToBeImage(String str) {
        String BASE64str = str.substring(str.lastIndexOf(",") + 1);
        //统一图片后缀
        String ext = str.substring(str.indexOf("/") + 1, str.indexOf(";"));
        //文件名称
        String uploadFileName = DateUtils.getDateStr8() + "_" + UUID.randomUUID().toString().replaceAll("-", "") + "." + ext;
        //存储地址
        StringBuilder path = new StringBuilder(absolutePaths).append("/").append(uploadFileName);//路径读取不到
        File saveFile = new File(String.valueOf(path));
        Decoder decoder = Base64.getDecoder();
        try (OutputStream out = new FileOutputStream(saveFile)) {
            byte[] b = decoder.decode(BASE64str);
            for (int i = 0; i < b.length; i++) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            out.write(b);
            out.flush();
            String[] result = {path + uploadFileName, uploadFileName};
            logger.info("********************图片上传成功********************");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("********************图片上传失败********************");
            throw new BizException("图片上传失败，请重新上传");
        }
    }
}
