package cn.itcast.erp.invoice.order.dao.dao;

import java.util.List;

import cn.itcast.erp.invoice.order.vo.OrderModel;
import cn.itcast.erp.invoice.order.vo.OrderQueryModel;
import cn.itcast.erp.util.base.BaseDao;

public interface OrderDao extends BaseDao<OrderModel> {

	public List<OrderModel> getAllOrderTypes(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount, Integer[] orderTypes);

	public Integer getCountOrderTypes(OrderQueryModel oqm,
			Integer[] buyCheckOrderTypes);

	public List<OrderModel> getAllTypes(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount, Integer[] types);

	public Integer getAllTypes(OrderQueryModel oqm, Integer[] types);

}
