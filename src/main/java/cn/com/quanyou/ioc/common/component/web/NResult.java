package cn.com.quanyou.ioc.common.component.web;

import cn.com.quanyou.ioc.common.component.enums.ReturnInfoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by hanlin on 2019/5/30.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("返回信息")
public class NResult<T> implements Serializable {

    private static final long serialVersionUID = 1204353478886230661L;

    @ApiModelProperty("请求结果标示")
    private boolean success;

    @ApiModelProperty("状态码")
    private String code;

    @ApiModelProperty("状态描述")
    private String message;

    @ApiModelProperty("结果集")
    private T data;

    private NResult(T data) {
        this.code = ReturnInfoEnum.Success.getCode();
        this.message = ReturnInfoEnum.Success.getDesc();
        this.data = data;
        this.success = true;
    }

    public NResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = true;
    }

    private NResult(String code, String message) {
        this.code = code;
        this.message = message;
        this.success = true;
        this.data = null;
    }

    private NResult(String code, String message, boolean status) {
        this.code = code;
        this.message = message;
        this.success = status;
    }

    /**
     * 公共静态方法：携带状态、消息、数据对象
     *
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static <T> NResult build(String code, String message, T data) {
        return new NResult<>(code, message, data);
    }

    /**
     * 公共静态方法：携带状态、消息
     *
     * @param code
     * @param message
     * @return
     */
    public static NResult build(String code, String message) {
        return new NResult<>(code, message);
    }

    /**
     * 公共静态方法：返回错误消息
     * @param msg
     * @return
     */
    public static NResult error(String msg) {
        return new NResult<>("400", msg, null);
    }

    /**
     * 公共静态方法：返回成功消息[success默认为true，code默认000000]
     * @param data
     * @return
     */
    public static <T> NResult<T> success(T data) {
        return new NResult<>(data);
    }

    /**
     * 公共静态方法：返回失败消息【success：false，code：000001，message：自定义】
     * @param msg
     * @return
     */
    public static NResult fail(String msg) {
        return new NResult(ReturnInfoEnum.Fail.getCode(), msg, false);
    }
}
