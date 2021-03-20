package com.ego.dubbo.service.impl;

import com.ego.commons.exception.DaoException;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.mapper.TbItemParamItemMapper;
import com.ego.mapper.TbItemParamMapper;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamExample;
import com.ego.pojo.TbItemParamItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TbItemParamDubboServiceImpl implements TbItemParamDubboService {
    @Autowired
    private TbItemParamMapper tbItemParamMapper;
    @Override
    public TbItemParam selectById(Long catId) {
        TbItemParamExample example = new TbItemParamExample();
        example.createCriteria().andItemCatIdEqualTo(catId);
        List<TbItemParam> tbItemParams = tbItemParamMapper.selectByExampleWithBLOBs(example);
        if (tbItemParams !=null && tbItemParams.size() == 1)
            return tbItemParams.get(0);
        return null;
    }

    @Override
    public List<TbItemParam> selectByPage(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        //投影查询时，如果查询结果里面有text类型列，一定使用withBlobs方法
        List<TbItemParam> tbItemParams = tbItemParamMapper.selectByExampleWithBLOBs(null);
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(tbItemParams);
        return pageInfo.getList();
    }

    @Override
    public long selectCount() {
        //countByExample 是查询表的行数，数据量
        return tbItemParamMapper.countByExample(null);
    }

    @Override
    public int insert(TbItemParam tbItemParam) {
        return tbItemParamMapper.insert(tbItemParam);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(long[] ids) throws DaoException {
        int index = 0 ;
        for (long id :ids)
            index += tbItemParamMapper.deleteByPrimaryKey(id);
        if (ids.length == index)
            return 1;
        throw  new DaoException("批量删除失败");
    }


}
