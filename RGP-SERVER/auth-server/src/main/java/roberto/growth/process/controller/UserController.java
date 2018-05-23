/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: UserController
 * Author:   HuangTaiHong
 * Date:     2018/5/21 22:46
 * Description: 用户Token校验
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户Token校验〉
 *
 * @author HuangTaiHong
 * @create 2018/5/21
 * @since 1.0.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public Principal getUser(Principal principal) {
		return principal;
	}
}