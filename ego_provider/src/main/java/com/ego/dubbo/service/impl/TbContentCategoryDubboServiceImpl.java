package com.ego.dubbo.service.impl;

import com.ego.commons.exception.DaoException;
import com.ego.dubbo.service.TbContentCategoryDubboService;
import com.ego.mapper.TbContentCategoryMapper;
import com.ego.pojo.TbContent;
import com.ego.pojo.TbContentCategory;
import com.ego.pojo.TbContentCategoryExample;
import com.ego.pojo.TbContentExample;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class TbContentCategoryDubboServiceImpl implements TbContentCategoryDubboService {
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;
    @Override
    public List<TbContentCategory> selectByPid(Long pid) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(pid).andStatusEqualTo(1);
        example.setOrderByClause("sort_order asc");
        return tbContentCategoryMapper.selectByExample(example);
    }

    @Override
    public int insert(TbContentCategory category) throws DaoException {
        TbContentCategoryExample example = new TbContentCategoryExample();
        //查询有没有重复的内容分类
        example.createCriteria().andNameEqualTo(category.getName()).andStatusEqualTo(1);
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
        if (list != null && list.size()==0){
            int index = tbContentCategoryMapper.insert(category);
            if (index==1) {
                //判断父类目是否为true
                TbContentCategory categoryParent = tbContentCategoryMapper.selectByPrimaryKey(category.getParentId());
                if (!categoryParent.getIsParent()) {
                    TbContentCategory parentUpdate = new TbContentCategory();
                    parentUpdate.setId(categoryParent.getId());
                    parentUpdate.setUpdated(category.getCreated());
                    parentUpdate.setIsParent(true);
                    int indexParent = tbContentCategoryMapper.updateByPrimaryKeySelective(parentUpdate);
                    if (indexParent != 1)
                        throw new DaoException("新增类目--修改父节点失败");
                }
                return 1;
            }
        }
        throw new DaoException("新增类目失败！");
    }

    @Override
    public int update(TbContentCategory category) throws DaoException {
        return 0;
    }
}
