package com.ynhuang.websocket.demo2;

import com.ynhuang.websocket.demo2.util.AuthService;
import com.ynhuang.websocket.demo2.util.Base64Util;
import com.ynhuang.websocket.demo2.util.FileUtil;
import com.ynhuang.websocket.demo2.util.HttpUtil;

import java.net.URLEncoder;

/**
 * @Auther: 018399
 * @Date: 2019/2/28 13:12
 * @Description:
 */
public class IdCard {

    private static String accessToken;

    public static void main(String[] args) {
        getResult("");
    }

    /**
     * 百度官方提供的api有坑
     *      * 经过研究发现：
     *      * 1、去除回车 imageString=imageString.replaceAll("\r\n","");
     *      * 2、替换加号 imageString=imageString.replaceAll("\\+","%2B");
     *      * 3、不要urlencode
     *        4、图片格式：PNG、JPG、JPEG、BMP
     *      * 不然就会报错，图片格式不对
     * @return
     */
    public static String getResult(String path){
        // 身份证识别url
        String idcardIdentificate = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        // 本地图片路径
        String filePath = path; //"C://Users//018399.SSS//Desktop//idcard.jpg";
        try {
            //byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = path.replaceAll("\r\n","").replaceAll("\\+","%2B");//Base64Util.encode(imgData).replaceAll("\r\n","").replaceAll("\\+","%2B");

            // 识别身份证正面id_card_side=front;识别身份证背面id_card_side=back;
            String params = "id_card_side=front&"
                    + URLEncoder.encode("image", "UTF-8") + "=" + imgStr;//URLEncoder.encode(imgStr, "UTF-8");

            /**
             * 线上环境access_token有过期时间，客户端可自行缓存，过期后重新获取。 一般设置为30d
             */
            if(accessToken == null) {
                accessToken = AuthService.getAuth();
            }
            String result = HttpUtil.post(idcardIdentificate, accessToken, params);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            accessToken = null;
            throw new RuntimeException("xxx");
        }
    }

}
