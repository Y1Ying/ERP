package cn.itcast.erp.invoice.order.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.invoice.order.vo.OrderModel;
import cn.itcast.erp.invoice.order.vo.OrderQueryModel;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailModel;
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

	public Integer getCountBuyCheck(OrderQueryModel oqm);

	public List<OrderModel> getAllBuyCheck(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount);

	/**
	 * 采购审核通过
	 * 
	 * @param uuid
	 *            被审核的订单id
	 * @param checker
	 *            审核人
	 */
	public void buyCheckPass(Long uuid, EmpModel checker);

	public void buyCheckNoPass(Long uuid, EmpModel checker);

	public Integer getCountTask(OrderQueryModel oqm);

	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount);

	/**
	 * 指派运输任务
	 * 
	 * @param uuid
	 *            订单UUID
	 * @param completer
	 *            跟单人
	 */
	public void assignTask(Long uuid, EmpModel completer);

	public Integer getCountTask(OrderQueryModel oqm, EmpModel login);

	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount, EmpModel login);

	public void endTask(Long uuid);

	public Integer getCountInStore(OrderQueryModel oqm);

	public List<OrderModel> getAllInStore(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount);

	/**
	 * 
	 * @param storeUuid
	 * @param odmUuid
	 * @param num
	 * @param login
	 * @return
	 */
	public OrderDetailModel inGoods(Long storeUuid, Long odmUuid, Integer num,
			EmpModel login);

}
