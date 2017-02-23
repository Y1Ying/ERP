package cn.itcast.erp.invoice.goods.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.itcast.erp.invoice.goods.dao.dao.GoodsDao;
import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.invoice.goods.vo.GoodsQueryModel;
import cn.itcast.erp.util.base.BaseImpl;
import cn.itcast.erp.util.base.BaseQueryModel;

public class GoodsImpl extends BaseImpl<GoodsModel> implements GoodsDao {

	public void doQbc(DetachedCriteria dc, BaseQueryModel qm) {
		GoodsQueryModel gqm = (GoodsQueryModel) qm;
		if (gqm.getUnit() != null && gqm.getUnit().trim().length() > 0) {
			dc.add(Restrictions.eq("unit", gqm.getUnit().trim()));
		}

		// gqm.gtm.sm.uuid
		if (gqm.getGtm() != null && gqm.getGtm().getSm() != null
				&& gqm.getGtm().getSm().getUuid() != null
				&& gqm.getGtm().getSm().getUuid() != -1) {
			// 设置别名
			// dc.createAlias("gtm", "g");
			// dc.createAlias("g.sm", "s");
			// dc.add(Restrictions.eq("s.uuid",
			// gqm.getGtm().getSm().getUuid()));

			// 优化
			dc.createAlias("gtm", "g");
			dc.add(Restrictions.eq("g.sm", gqm.getGtm().getSm()));
		}
	}

	@Override
	public List<GoodsModel> getAllByGtmUuid(Long uuid) {
		String hql = "from GoodsModel where gtm.uuid =  ?";
		return this.getHibernateTemplate().find(hql, uuid);
	}
}
