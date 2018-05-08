/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: QQUserInfo
 * Author:   HuangTaiHong
 * Date:     2018-05-04 下午 5:20
 * Description: QQ返回用户信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.social.qq.api;

import lombok.Getter;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br>
 * 〈QQ返回用户信息〉
 *
 * @author HuangTaiHong
 * @create 2018-05-04
 * @since 1.0.0
 */
@Getter
@Setter
public class QQUserInfo {
    /** 返回码 **/
    private String ret;

    /** 如果ret<0，会有相应的错误信息提示，返回数据全部用UTF-8编码 **/
    private String msg;

    /** openId **/
    private String openId;

    /** 不知道什么东西，文档上没写，但是实际api返回里有 **/
    private String is_lost;

    /** 省(直辖市) **/
    private String province;

    /** 市(直辖市区) **/
    private String city;

    /** 出生年月 **/
    private String year;

    /** 用户在QQ空间的昵称 **/
    private String nickname;

    /** 大小为30×30像素的QQ空间头像URL **/
    private String figureurl;

    /** 大小为50×50像素的QQ空间头像URL **/
    private String figureurl_1;

    /** 大小为100×100像素的QQ空间头像URL **/
    private String figureurl_2;

    /** 大小为40×40像素的QQ头像URL **/
    private String figureurl_qq_1;

    /** 大小为100×100像素的QQ头像URL 需要注意不是所有的用户都拥有QQ的100×100的头像，但40×40像素则是一定会有 **/
    private String figureurl_qq_2;

    /** 性别 如果获取不到则默认返回男 **/
    private String gender;

    /** 标识用户是否为黄钻用户(0：不是；1：是) **/
    private String is_yellow_vip;

    /** 标识用户是否为黄钻用户(0：不是；1：是) **/
    private String vip;

    /** 黄钻等级 **/
    private String yellow_vip_level;

    /** 黄钻等级 **/
    private String level;

    /** 标识是否为年费黄钻用户(0：不是； 1：是) **/
    private String is_yellow_year_vip;
}