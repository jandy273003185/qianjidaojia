package com.qifenqian.bms.v2.biz.merchant.store.service;

import com.github.pagehelper.PageInfo;
import com.jaood.qrcode.geneerate_qrcode.QRCode;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.basemanager.merchant.bean.CustScan;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.bean.StoreManage;
import com.qifenqian.bms.basemanager.merchant.mapper.CustScanMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.StoreManageMapper;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.platform.common.utils.DateUtils;
import com.qifenqian.bms.v2.biz.merchant.merchantregister.bean.vo.MerchantComboVO;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiBin
 * @Description: 门店管理
 * @date 2020/4/17 17:56
 */
@Service
public class BizStoreManageService extends BaseService {

    @Autowired
    private MerchantMapper merchantMapper;
    @Value("${CERTIFY_FILE_SAVE_PATH}")
    private String CERTIFY_FILE_SAVE_PATH;
    @Autowired
    private TdCustInfoMapper tdCustInfoMapper;
    @Autowired
    private StoreManageMapper storeManageMapper;
    @Autowired
    private CustScanMapper custScanMapper;

    public PageInfo<StoreManage> findStoreManageList(StoreManage queryBean) {
        List<StoreManage> storeManages = storeManageMapper.getStoreList(queryBean);
        return new PageInfo<>(storeManages);
    }

    public ResultData add(StoreManage storeManage) {
        storeManage.setShopId(GenSN.getOperateID());
        storeManage.setShopNo("MD" + DateUtils.getDateTimeStrNo());
        if (storeManageMapper.repeats(storeManage) > 0) {
            throw new BizException("商户门店编号在系统中已存在");
        }
        try {
            storeManageMapper.insert(storeManage);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData update(StoreManage storeManage) {
        try {
            storeManageMapper.update(storeManage);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData delete(StoreManage storeManage) {
        try {
            storeManageMapper.delete(storeManage);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData verify(StoreManage storeManage) {
        int exist = storeManageMapper.exists(storeManage);
        if (exist < 1) {
            return ResultData.success("商户在系统中不存在");
        }
        return ResultData.success("商户在系统中已存在");
    }

    public ResultData repeat(StoreManage storeManage) {
        int exist = storeManageMapper.repeats(storeManage);
        if (exist > 1) {
            return ResultData.success("商户门店编号在系统中已存在");
        }
        return ResultData.success();
    }

    public ResultData getQRCode(StoreManage storeManage) {
        Map<String, String> resultMap = new HashMap<String, String>();
        // 获取商户编号
        TdCustInfo tdCustInfo = tdCustInfoMapper.selectById(storeManage.getMchId());
        if (tdCustInfo == null) {
            throw new BizException("查询商户信息有误：商户为空");
        }
        String merchantCode = storeManage.getMerchantCode();
        if (StringUtils.isBlank(merchantCode)) {
            throw new BizException("查询商户信息有误：商户编号为空");
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("https://combinedpay.qifenqian.com/pub/merchantqr.do?mid=");
            sb.append(storeManage.getMerchantCode());
            sb.append("&sn=");
            sb.append(storeManage.getShopNo());

            String fileUploadPath = CERTIFY_FILE_SAVE_PATH;
            String filedName = "recode";
            String urlToImg = QRCode.urlToImg(fileUploadPath, sb.toString(), GenSN.getRandomNum(2) + filedName, 20);
//            HttpSession session = request.getSession();
//            String path = session.getServletContext().getRealPath("/WEB-INF/classes/static/static/images/pics_04.jpg");
            /**
             * TODO 设置path地址
             */
            String path = "/data";
            InputStream noePic = new FileInputStream(path);// 第一张背景图片可以写死
            InputStream twoPic = new FileInputStream(urlToImg);
            BufferedImage image = ImageIO.read(noePic);
            BufferedImage image2 = ImageIO.read(twoPic);
            Graphics g = image.getGraphics();
            g.drawImage(image2, image.getWidth() - image2.getWidth() + 21,
                    image.getHeight() - image2.getHeight() - 38, image2.getWidth() - 120, image2.getHeight() - 120,
                    null);
            File saveFile = new File(
                    fileUploadPath + File.separator + "re" + File.separator + storeManage.getMchId());
            if (!saveFile.exists()) {
                saveFile.mkdirs();
            }
            OutputStream outImage = new FileOutputStream(fileUploadPath + File.separator + "re" + File.separator
                    + storeManage.getMchId() + File.separator + "recode.jpg");// 指定生成的图片路径
            //JPEGImageEncoder enc = JPEGCodec.createJPEGEncoder(outImage);
            //enc.encode(image);
            ImageIO.write(image, /*"GIF"*/ "jpg" /* format desired */, outImage /* target */);
            noePic.close();
            twoPic.close();
            resultMap.put("url", sb.toString());
            resultMap.put("custId", storeManage.getMchId());
            // 保存
            CustScan custScan = new CustScan();
//					TdCustScanCopy scanCopyIdCard = new TdCustScanCopy();
            custScan.setCustId(storeManage.getMchId());
            custScan.setCertifyType("re");// 二维码

            String imagePath = custScanMapper.findPath(custScan);
            if (StringUtils.isBlank(imagePath)) {
                custScan.setCustId(tdCustInfo.getCustId());
                custScan.setCustName(tdCustInfo.getCustName());
                custScan.setScanCopyPath(fileUploadPath + File.separator + "re" + File.separator
                        + storeManage.getMchId() + File.separator + "recode.jpg");
                custScan.setCertifyNo(tdCustInfo.getCertifyNo());
                custScanMapper.insertCustScan(custScan);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success(resultMap);
    }

    /**
     * 读取管道中的流数据
     */
    private byte[] readStream(InputStream inStream) {
        ByteArrayOutputStream bops = new ByteArrayOutputStream();
        int data = -1;
        try {
            while ((data = inStream.read()) != -1) {
                bops.write(data);
            }
            return bops.toByteArray();
        } catch (Exception e) {
            return null;
        } finally {
            if (null != bops) {
                try {
                    bops.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void getNewPic(StoreManage storeManage, HttpServletResponse response) throws IOException {
        CustScan custScan = new CustScan();
        custScan.setCustId(storeManage.getCustId());
        custScan.setCertifyType(storeManage.getCertifyType());
        String imagePath = custScanMapper.findPath(custScan);
        if (StringUtils.isNotBlank(imagePath)) {
            File file = new File(imagePath);
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                OutputStream os = response.getOutputStream();
                byte[] btImg = readStream(fileInputStream);
                os.write(btImg);
                os.flush();
                if (null != fileInputStream) {
                    fileInputStream.close();
                }
                if (null != os) {
                    os.close();
                }
            }
        }
    }

    public void downNewPic(StoreManage storeManage, HttpServletResponse response) throws IOException {
        CustScan custScan = new CustScan();
        custScan.setCustId(storeManage.getCustId());
        custScan.setCertifyType(storeManage.getCertifyType());
        String name = "recode.png";
        String imagePath = custScanMapper.findPath(custScan);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;fileName=" + name);
        if (StringUtils.isNotBlank(imagePath)) {
            File file = new File(imagePath);
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                OutputStream os = response.getOutputStream();
                byte[] btImg = readStream(fileInputStream);
                os.write(btImg);
                os.flush();
                if (null != fileInputStream) {
                    fileInputStream.close();
                }
                if (null != os) {
                    os.close();
                }
            }
        }
    }

    public List<Merchant> findMerchantList() {
        List<MerchantComboVO> merchantComboVOList = new ArrayList<>();
        List<Merchant> merchants = merchantMapper.selectMerchant();
//        for (Merchant merchant : merchants) {
//            MerchantComboVO merchantComboVO = new MerchantComboVO();
//            merchantComboVO.setMerchantCode(merchant.getMerchantCode());
//            merchantComboVO.setValue(merchant.getShortName());
//            merchantComboVOList.add(merchantComboVO);
//        }
        return merchants;
    }
}
