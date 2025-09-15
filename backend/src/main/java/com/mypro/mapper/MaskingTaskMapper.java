package com.mypro.mapper;

import com.mypro.entity.MaskingTask;
import com.mypro.entity.MaskingTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MaskingTaskMapper {
    long countByExample(MaskingTaskExample example);

    int deleteByExample(MaskingTaskExample example);

    int deleteByPrimaryKey(Integer taskId);

    int insert(MaskingTask row);

    int insertSelective(MaskingTask row);

    List<MaskingTask> selectByExample(MaskingTaskExample example);

    MaskingTask selectByPrimaryKey(Integer taskId);

    int updateByExampleSelective(@Param("row") MaskingTask row, @Param("example") MaskingTaskExample example);

    int updateByExample(@Param("row") MaskingTask row, @Param("example") MaskingTaskExample example);

    int updateByPrimaryKeySelective(MaskingTask row);

    int updateByPrimaryKey(MaskingTask row);
}