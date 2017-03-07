package cn.itcast.erp.invoice.order.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.invoice.order.vo.OrderModel;
import cn.itcast.erp.invoice.order.vo.OrderQueryModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface OrderEbi extends BaseEbi<OrderModel> {

	/**
	 * 保存采购订单
	 * 
	 * @param om
	 *            订单数据模型（封装供应商uuid）
	 * @param goodsUuids
	 *            商品uuid数组
	 * @param nums
	 *            数量数组
	 * @param prices
	 *            单价数据
	 * @param creater
	 *            制单人
	 */
	public void saveBuyOrder(OrderModel om, Long[] goodsUuids, Integer[] nums,
			Double[] prices, EmpModel creater);

	/**
	 * 获取所有采购订单数据
	 * 
	 * @param oqm
	 * @param pageNum
	 * @param pageCount
	 * @return
	 */
	public List<OrderModel> getAllBuy(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount);

	public int getCountBuyCheck(OrderQueryModel oqm);

	public List<OrderModel> getAllBuyCheck(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount);

}
