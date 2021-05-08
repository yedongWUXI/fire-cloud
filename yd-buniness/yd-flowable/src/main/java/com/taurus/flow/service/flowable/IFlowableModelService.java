package com.taurus.flow.service.flowable;

import com.taurus.flow.vo.flowable.ModelVo;
import com.dragon.tools.vo.ReturnVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @projectName : flowable
 * @description: 模型service
 */
public interface IFlowableModelService {

    /**
     * 导入模型
     *
     * @param file 文件
     * @return
     */
    public ReturnVo<String> importProcessModel(MultipartFile file);

    /**
     * 添加模型
     *
     * @param modelVo
     * @return
     */
    public ReturnVo<String> addModel(ModelVo modelVo);
}
