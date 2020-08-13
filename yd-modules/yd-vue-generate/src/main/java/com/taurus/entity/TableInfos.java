package com.taurus.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: yedong
 * @Date: 2020/8/13 12:19
 * @Modified by:
 */
@Data
@Builder
@Accessors(chain = true)
public class TableInfos {

    List<Map<String, String>> columns;

    Map<String, String> table;
}
