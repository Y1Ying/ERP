package cn.itcast.erp.invoice.order.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.invoice.order.business.ebi.OrderEbi;
import cn.itcast.erp.invoice.order.dao.dao.OrderDao;
import cn.itcast.erp.invoice.order.vo.OrderModel;
import cn.itcast.erp.invoice.order.vo.OrderQueryModel;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.itcast.erp.util.base.BaseQueryModel;
import cn.itcast.erp.util.num.NumUtil;

public class OrderEbo implements OrderEbi {
	private OrderDao orderDao;
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void save(OrderModel om) {
		orderDao.save(om);
	}

	public void update(OrderModel om) {
		orderDao.update(om);
	}

	public void delete(OrderModel om) {
		orderDao.delete(om);
	}

	public OrderModel get(Serializable uuid) {
		return orderDao.get(uuid);
	}

	public List<OrderModel> getAll() {
		return orderDao.getAll();
	}

	public List<OrderModel> getAll(BaseQueryModel qm, Integer pageNum,
			Integer pageCount) {
		return orderDao.getAll(qm, pageNum, pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return orderDao.getCount(qm);
	}

	@Override
	public void saveBuyOrder(OrderModel om, Long[] goodsUuids, Integer[] nums,
			Double[] prices, EmpModel creater) {
		// 保存订单

		// 设置订单号:订单号唯一
		String orderNum = NumUtil.generatorOrderNum();
		om.setOrderNum(orderNum);

		// 订单创建时间是当前系统时间
		om.setCreateTime(System.currentTimeMillis());

		// 当前保存的是采购订单
		om.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		// 新保存的订单的状态
		om.setType(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK);
		// 制单人
		om.setCreater(creater);
		// 设置订单中

		Integer totalNum = 0;
		Double totalPrice = 0.0d;

		Set<OrderDetailModel> odms = new HashSet<OrderDetailModel>();
		for (int i = 0; i < goodsUuids.length; i++) {
			// 创建订单明细的对象并添加到集合中
			OrderDetailModel odm = new OrderDetailModel();
			// 设置订单明细的数量
			odm.setNum(nums[i]);
			// 设置订单明细单价
			odm.setPrice(prices[i]);
			// 设置订单明细商品
			GoodsModel gm = new GoodsModel();
			gm.setUuid(goodsUuids[i]);
			odm.setGm(gm);
			// 设置所属订单
			odm.setOm(om);
			// 将明细对象加入集合
			odms.add(odm);

			totalNum += nums[i];
			totalPrice += nums[i] * prices[i];
		}
		// 设置订单对应的所有明细数据
		om.setOdms(odms);
		// 设置订单总数量
		om.setTotalNum(totalNum);
		// 设置订单总价值
		om.setTotalPrice(totalPrice);

		orderDao.save(om);
	}

	@Override
	public List<OrderModel> getAllBuy(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount) {
		// 设置固定条件：订单类别为采购
		oqm.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		return orderDao.getAll(oqm, pageNum, pageCount);
	}

}
