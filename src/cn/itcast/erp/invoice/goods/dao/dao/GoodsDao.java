package cn.itcast.erp.invoice.goods.dao.dao;

import java.util.List;

import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.util.base.BaseDao;

public interface GoodsDao extends BaseDao<GoodsModel> {

	public List<GoodsModel> getAllByGtmUuid(Long uuid);

}
