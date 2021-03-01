package cn.com.quanyou.ioc.common.component.enums;

/**
 * Created by hanlin on 2019/5/30.
 */
public interface RestStatus {

    String code();

    /**
     * @return status enum name
     */
    String name();

    /**
     * @return message summary
     */
    String desc();
}
