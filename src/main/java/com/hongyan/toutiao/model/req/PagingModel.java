package com.hongyan.toutiao.model.req;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class PagingModel {

    // 分页模型
    private int pageNum;
    private int pageSize;
    private String orderBy;
    private String orderType;
    private String keyword;



    /**
     * 生成分页模型
     *
     * 此方法根据提供的分页信息生成一个分页模型对象，用于后续的数据查询和处理
     * 它将具体的分页参数封装到一个泛型分页对象中，以便在不同的数据处理场景中复用
     *
     * @param pagingModel 分页模型，包含页码和页面大小等分页信息
     * @return 返回一个泛型分页对象，用于后续的数据分页处理
     */
    public static <T> Page<T> genPagingModel(PagingModel pagingModel){
        // 记录日志，输出当前的分页模型信息，用于调试和追踪
        log.info("pagingModel:{}", pagingModel);

        // 创建一个泛型分页对象，初始化当前页和每页大小
        // 这里的分页信息来源于传入的pagingModel对象
        Page<T> page = new Page<>(pagingModel.getPageNum(), pagingModel.getPageSize());

        // 返回初始化后的分页对象
        return page;
    }
}
