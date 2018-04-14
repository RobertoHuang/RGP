/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SendSMSCaptchaUtils
 * Author:   HuangTaiHong
 * Date:     2018-04-03 上午 10:49
 * Description: 发送短信验证码工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import roberto.growth.process.security.core.enums.RGPSecurityExceptionCodeEnum;
import roberto.growth.process.security.core.exception.RGPSecurityException;

/**
 * 〈一句话功能简述〉<br>
 * 〈发送短信验证码工具类〉
 *
 * @author HuangTaiHong
 * @create 2018-04-03
 * @since 1.0.0
 */
public class SendSMSCaptchaUtils {
    /**
     * 短信API产品名称
     **/
    public static final String PRODUCT = "Dysmsapi";

    /**
     * 短信API产品域名
     **/
    public static final String DOMAIN = "dysmsapi.aliyuncs.com";

    /**
     * 阿里云账户配置
     **/
    public static final String ACCESSKEYID = "LTAIP8C6TE30Qd4W";
    public static final String ACCESSKEYSECRET = "FSPbG0HB1mb5vI3wy3ueISMLE8CDJp";

    /**
     * 功能描述: <br>
     * 〈发送短信验证码〉
     *
     * @param phoneNumber
     * @param code
     * @return:void
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/3 上午 10:51
     */
    public static void sendSMSCaptcha(String phoneNumber, String code) throws RGPSecurityException {
        try {
            // 设置超时时间
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");

            // 初始化ACSClient
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESSKEYID, ACCESSKEYSECRET);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            // 组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            // 使用POST提交
            request.setMethod(MethodType.POST);
            // 必填:待发送手机号
            // 支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码
            // 批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
            request.setPhoneNumbers(phoneNumber);
            // 短信签名-可在短信控制台中找到
            request.setSignName("RGP信息技术有限公司");
            // 短信模板-可在短信控制台中找到
            request.setTemplateCode("SMS_129748601");
            // 可选:模板中的变量替换JSON串 如模板内容为"亲爱的${name},您的验证码为${code}"时
            // 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            request.setTemplateParam("{\"code\":\"" + code + "\"}");

            // 请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() == null || !sendSmsResponse.getCode().equals("OK")) {
                throw new RGPSecurityException(RGPSecurityExceptionCodeEnum.SEND_SMS_CAPTCHA_ERROR);
            }
        } catch (Exception e) {
            throw new RGPSecurityException(RGPSecurityExceptionCodeEnum.SEND_SMS_CAPTCHA_ERROR, e);
        }
    }

    public static void main(String[] args) throws RGPSecurityException {
        SendSMSCaptchaUtils.sendSMSCaptcha("18649870156", "6666");
    }
}