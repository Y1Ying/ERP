package cn.itcast.erp.invoice.order.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
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
}
