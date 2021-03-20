package com.ego.dubbo.service.impl;

import com.ego.commons.exception.DaoException;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.mapper.TbItemDescMapper;
import com.ego.mapper.TbItemMapper;
import com.ego.mapper.TbItemParamItemMapper;
import com.ego.mapper.TbItemParamMapper;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sun.util.calendar.LocalGregorianCalendar;

import java.util.Date;
import java.util.List;
@Service
public class TbItemDubboServiceImpl implements TbItemDubboService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;
    @Override
    public List<TbItem> selectByPage(int pageSize, int pageNumber) {
        // 分页插件要写在查询的上面。否则插件无效，一般写在第一行
        PageHelper.startPage(pageNumber,pageSize);
        // Example 相当于sql中where，没有where时参数为null即可
        // select * from tb_item limit ?,?;
        List<TbItem> list = tbItemMapper.selectByExample(null);
        PageInfo<TbItem> pi = new PageInfo<>(list);
        return pi.getList();
    }

    @Override
    public long selectCount() {
        return tbItemMapper.countByExample(null);
    }

    @Override
    // 监听到异常，执行事务回滚，声明式事务注解
    @Transactional
    public int updateStatusByIds(long[] ids, int status) throws DaoException {
        int index = 0;
        Date date = new Date();
        for (long id : ids){
            TbItem tbItem = new TbItem();
            tbItem.setId(id);
            tbItem.setStatus((byte) status);
            tbItem.setUpdated(date);
            index+=tbItemMapper.updateByPrimaryKeySelective(tbItem);
        }
        if (index==ids.length){
            return 1;
        }
        throw new DaoException("批量修改失败");
    }

    /**
     * 实现商品新增的功能
     * @param tbItem
     * @param tbItemDesc
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(TbItem tbItem, TbItemDesc tbItemDesc, TbItemParamItem tbItemParamItem) throws DaoException {
        int index = tbItemMapper.insert(tbItem);
        if (index == 1){
            int index2 = tbItemDescMapper.insert(tbItemDesc);

            if (index2 == 1){
                int result3 = tbItemParamItemMapper.insert(tbItemParamItem);
                if (result3 == 1)
                    return 1;
            }
        }
        throw new DaoException("新增失败");
    }

    /**
     * 商品修改的功能的实现
     * @param tbItem
     * @param tbItemDesc
     * @return
     * @throws DaoException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(TbItem tbItem, TbItemDesc tbItemDesc,TbItemParamItem tbItemParamItem) throws DaoException {
        int result1 = tbItemMapper.updateByPrimaryKeySelective(tbItem);
        if(result1 == 1){
            int result2 = tbItemDescMapper.updateByPrimaryKeySelective(tbItemDesc);
            if (result2 == 1){
                int result3 = tbItemParamItemMapper.updateByPrimaryKeySelective(tbItemParamItem);
                if (result3 == 1)
                    return 1;
            }
        }
        throw new DaoException("商品修改信息失败");
    }
}
