package cn.com.quanyou.ioc.common.component.web;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 分页结果封装类
 * @Author: yangli
 * @Date: 2019/9/6 - 11:39
**/
@Data
@ApiModel("分页信息")
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = -3641144593310102633L;

    @ApiModelProperty("总条数")
    private long totalCount;

    @ApiModelProperty("结果集合")
    private List<T> result;

    @ApiModelProperty("第几页")
    private int pageNum;

    @ApiModelProperty("每页记录数")
    private int pageSize;

    @ApiModelProperty("是否存在下一页")
    private boolean isHasNextPage;

    public PageResult(int pageNum, int pageSize, long totalCount, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.result = list;
    }

    public PageResult(int pageNum, int pageSize, long totalCount, List<T> list, boolean isHasNextPage) {
        this.totalCount = totalCount;
        this.result = list;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.isHasNextPage = isHasNextPage;
    }

}
