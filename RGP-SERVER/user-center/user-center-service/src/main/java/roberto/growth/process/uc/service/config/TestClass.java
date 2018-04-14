/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: TestClass
 * Author:   HuangTaiHong
 * Date:     2018-04-13 下午 5:32
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.uc.service.config;


/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author HuangTaiHong
 * @create 2018-04-13
 * @since 1.0.0
 */
public class TestClass {
    public static void main(String[] args) {
        Haha haha = new Haha();
        Mono<String> res = haha.hasKey();
        System.out.println(res);
    }
}

class Haha<K> {

    public Mono<Boolean> hasKey() {
        return new Mono<Boolean>();

    }
}

class Mono<E>{

}