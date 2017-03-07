package cn.itcast.erp.invoice.order.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cn.itcast.erp.invoice.order.dao.dao.OrderDao;
import cn.itcast.erp.invoice.order.vo.OrderModel;
import cn.itcast.erp.invoice.order.vo.OrderQueryModel;
import cn.itcast.erp.util.base.BaseImpl;
import cn.itcast.erp.util.base.BaseQueryModel;

public class OrderImpl extends BaseImpl<OrderModel> implements OrderDao {

	public void doQbc(DetachedCriteria dc, BaseQueryModel qm) {
		OrderQueryModel oqm = (OrderQueryModel) qm;
		if (oqm.getOrderType() != null && oqm.getOrderType() != -1) {
			dc.add(Restrictions.eq("orderType", oqm.getOrderType()));
		}
		if (oqm.getCreater() != null && oqm.getCreater().getName() != null
				&& oqm.getCreater().getName().trim().length() > 0) {
			dc.createAlias("creater", "c1");
			dc.add(Restrictions.like("c1.name", "%"
					+ oqm.getCreater().getName().trim() + "%"));
		}
		if (oqm.getType() != null && oqm.getType() != -1) {
			dc.add(Restrictions.eq("type", oqm.getType()));
		}
	}

	public void doQbc2(DetachedCriteria dc, BaseQueryModel qm,
			Integer[] orderTypes) {
		dc.add(Restrictions.in("orderType", orderTypes));
		doQbc(dc, qm);
	}

	@Override
	public List<OrderModel> getAllOrderTypes(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount, Integer[] orderTypes) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderModel.class);

		// orderType在指定形参orderTypes范围内
		doQbc2(dc, oqm, orderTypes);
		return this.getHibernateTemplate().findByCriteria(dc,
				(pageNum - 1) * pageCount, pageCount);
	}

	@Override
	public int getCountOrderTypes(OrderQueryModel oqm, Integer[] orderTypes) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderModel.class);
		dc.setProjection(Projections.rowCount());
		doQbc2(dc, oqm, orderTypes);
		List<Long> count = this.getHibernateTemplate().findByCriteria(dc);
		return count.get(0).intValue();
	}
}
