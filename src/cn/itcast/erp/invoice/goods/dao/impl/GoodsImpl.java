package cn.itcast.erp.invoice.goods.dao.impl;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.erp.invoice.goods.dao.dao.GoodsDao;
import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.invoice.goods.vo.GoodsQueryModel;
import cn.itcast.erp.util.base.BaseImpl;
import cn.itcast.erp.util.base.BaseQueryModel;

public class GoodsImpl extends BaseImpl<GoodsModel> implements GoodsDao {

	public void doQbc(DetachedCriteria dc, BaseQueryModel qm) {
		GoodsQueryModel gqm = (GoodsQueryModel) qm;
	}

}
