package cn.com.quanyou.ioc.common.component.util.utilBean;

import lombok.Data;

/**
 * @Description //时间参数类【供同比等查询用】
 * @Author yangli
 * @Date 2019/9/16-16:37
 **/
@Data
public class TimeArgs {

    /** 请求月-start*/
    private String currentStart;
    /** 请求月-end*/
    private String currentEnd;

    /** 同比-start*/
    private String compareEnd;
    /** 同比-end*/
    private String compareStart;
}
