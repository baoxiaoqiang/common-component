package cn.com.quanyou.ioc.common.component.exception;

import java.io.Serializable;

/**
 * @Description: 自定义参数非法异常
 * @Author: yangli
 * @Date: 2019/9/6 - 11:06
**/
public class ArgumentNotValidException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -2602003186306129379L;

    private String code = "400";

    private String message;

    public ArgumentNotValidException(String message) {
        super(message);
    }

}
