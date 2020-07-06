package com.sevenpay.agentmanager.common.utils.verfycode;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;


/**
 * User: JIANGZONGLIN
 * Date: 2019/11/26
 * Time: 13:48
 */
public class ToolsUtil
{
    private static Logger log = LoggerFactory.getLogger(ToolsUtil.class);
    /**
     * 获取对象成员变量的set方法
     * @throws Exception
     * author kongtaotao 2013-7-10 上午10:56:24
     * return Method
     */
    public static Method setFiledMethod(Object obj, String fieldName) throws Exception
    {
        return setFiledMethod(obj.getClass(), fieldName);
    }

    public static Method setFiledMethod(Class<?> clz, String fieldName) throws Exception
    {
        Method[] methods = clz.getMethods();
        String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        int k = methods.length;
        for (int i = 0; i < k; i++)
        {
            if (methodName.equals(methods[i].getName()))
            {
                return methods[i];
            }
        }
        return null;
    }

    public static boolean isEmpty(String str)
    {
        if (str == null || "".equals(str.trim()))
        {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(List<?> list)
    {
        if (list == null || list.isEmpty())
        {
            return true;
        }
        return false;
    }

    public static <T> boolean isEmpty(T[] items)
    {
        if (items == null || items.length < 1)
        {
            return true;
        }
        return false;
    }

    public static boolean notEmpty(String str)
    {
        if (str != null && !"".equals(str.trim()))
        {
            return true;
        }
        return false;
    }

    public static boolean notEmpty(List<?> list)
    {
        if (list != null && !list.isEmpty())
        {
            return true;
        }
        return false;
    }

    public static String converToString(Object o)
    {
        if (o == null)
        {
            return "";
        }
        else
        {
            return o.toString();
        }
    }

    /**
     * 校验2个时间的间隔(秒)
     * @param date1
     * @param date2
     * @return
     */
    public static long calTimeSec(Date date1, Date date2)
    {
        long sec = (long)(date1.getTime() - date2.getTime())/1000;
        return sec;
    }

    /**
     * 获取客户端ip
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        String fromSource = "X-Real-IP";
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
            fromSource = "X-Forwarded-For";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            fromSource = "Proxy-Client-IP";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            fromSource = "WL-Proxy-Client-IP";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            fromSource = "request.getRemoteAddr";
        }

        log.info("App Client IP: "+ip+", fromSource: "+fromSource);
        return ip;
    }
    public static void creatDirs(String filePath) {
        String[] paths = filePath.split("/");
        String tempPath = "";
        File aFile = null;
        for (int i = 0; i < paths.length; i++) {
            tempPath += paths[i] + "/";
            log.debug(tempPath);
            aFile = new File(tempPath);
            if (!aFile.exists()) {
                aFile.mkdirs();
            }
        }

    }
}
