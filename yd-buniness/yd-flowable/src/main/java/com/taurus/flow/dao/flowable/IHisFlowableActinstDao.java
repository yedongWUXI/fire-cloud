package com.taurus.flow.dao.flowable;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @projectName : flowable
 * @description: 运行时的节点Dao
 */
@Mapper
public interface IHisFlowableActinstDao {

    /**
     * 删除节点信息
     *
     * @param ids ids
     */
    void deleteHisActinstsByIds(List<String> ids);
}
