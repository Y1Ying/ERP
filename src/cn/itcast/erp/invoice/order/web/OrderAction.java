package cn.itcast.erp.invoice.order.web;

import java.util.List;

import cn.itcast.erp.invoice.goods.business.ebi.GoodsEbi;
import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.invoice.goodstype.business.ebi.GoodsTypeEbi;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.invoice.order.business.ebi.OrderEbi;
import cn.itcast.erp.invoice.order.vo.OrderModel;
import cn.itcast.erp.invoice.order.vo.OrderQueryModel;
import cn.itcast.erp.invoice.supplier.business.ebi.SupplierEbi;
import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.util.base.BaseAction;

public class OrderAction extends BaseAction {
	public OrderModel om = new OrderModel();
	public OrderQueryModel oqm = new OrderQueryModel();

	private OrderEbi orderEbi;
	private SupplierEbi supplierEbi;
	private GoodsTypeEbi goodsTypeEbi;
	private GoodsEbi goodsEbi;

	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}
	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}
	public void setOrderEbi(OrderEbi orderEbi) {
		this.orderEbi = orderEbi;
	}

	// 列表
	public String list() {
		setDataTotal(orderEbi.getCount(oqm));
		List<OrderModel> orderList = orderEbi.getAll(oqm, pageNum, pageCount);
		put("orderList", orderList);
		return LIST;
	}

	// 到添加
	public String input() {
		if (om.getUuid() != null) {
			om = orderEbi.get(om.getUuid());
		}
		return INPUT;
	}

	// 添加
	public String save() {
		if (om.getUuid() == null) {
			orderEbi.save(om);
		} else {
			orderEbi.update(om);
		}
		return TO_LIST;
	}

	// 删除
	public String delete() {
		orderEbi.delete(om);
		return TO_LIST;
	}

	// -------------采购相关--------------
	public String buyList() {
		setDataTotal(orderEbi.getCount(oqm));
		List<OrderModel> orderList = orderEbi
				.getAllBuy(oqm, pageNum, pageCount);
		put("orderList", orderList);
		return "buyList";
	}

	// 进入采购订单
	public String buyInput() {
		// 加载具有类别信息的供应商信息，类别里面必须有商品信息
		List<SupplierModel> supplierList = supplierEbi.getAllUnionTwo();
		// 第一个供应商具有商品的类别
		List<GoodsTypeModel> gtmList = goodsTypeEbi
				.getAllUnionBySm(supplierList.get(0).getUuid());
		// 第一个类别的商品
		List<GoodsModel> gmList = goodsEbi
				.getAllByGtm(gtmList.get(0).getUuid());
		put("gmList", gmList);
		put("supplierList", supplierList);
		put("gtmList", gtmList);
		return "buyInput";
	}

	public Long[] goodsUuids;
	public Integer[] nums;
	public Double[] prices;

	// 保存采购订单
	public String buySave() {
		orderEbi.saveBuyOrder(om, goodsUuids, nums, prices, getLogin());
		return "toBuyList";
	}

	// 查看采购订单明细
	public String buyDetail() {
		// 根据om.uuid 获取对应信息，加载到详情页
		om = orderEbi.get(om.getUuid());
		return "buyDetail";
	}

	// 采购审核相关

	public String buyCheckList() {
		// 要加载订单数据列表
		setDataTotal(orderEbi.getCountBuyCheck(oqm));
		List<OrderModel> orderList = orderEbi.getAllBuyCheck(oqm, pageNum,
				pageCount);
		put("orderList", orderList);
		return "buyCheckList";

	}

	// ---------AJAX----------
	public Long supplierUuid;
	public Long gtmUuid;
	public Long gmUuid;

	private List<GoodsTypeModel> gtmList;
	private List<GoodsModel> gmList;
	private GoodsModel gm;

	public GoodsModel getGm() {
		return gm;
	}
	public List<GoodsTypeModel> getGtmList() {
		return gtmList;
	}
	public List<GoodsModel> getGmList() {
		return gmList;
	}
	// ajax根据供应商的uuid获取类别和商品信息
	public String ajaxGetGtmAndGm() {
		gtmList = goodsTypeEbi.getAllUnionBySm(supplierUuid);
		gmList = goodsEbi.getAllByGtm(gtmList.get(0).getUuid());
		gm = gmList.get(0);
		return "ajaxGetGtmAndGm";
	}

	// 已经使用过的，需要过滤的商品uuid
	public String used; // '11','22','33','44',
	// ajax根据供应商的uuid获取类别和商品信息(具有数据过滤功能)
	public String ajaxGetGtmAndGm2() {
		gtmList = goodsTypeEbi.getAllUnionBySm(supplierUuid);
		gmList = goodsEbi.getAllByGtm(gtmList.get(0).getUuid());
		// 当前获取的商品信息的uuid具有重复的，要对其进行过滤
		// 对集合进行迭代删除，逆序进行
		// 从gmList总取出所有的元素，挨个迭代，与本次传递过来的used进行比对，比对完发现重复的，删除掉（逆序进行）
		for (int i = gmList.size() - 1; i >= 0; i--) {
			Long uuid = gmList.get(i).getUuid();
			// 判断该uuid是否出现在used中
			if (used.contains("'" + uuid + "'")) {
				gmList.remove(i);
			}
		}
		gm = gmList.get(0);
		return "ajaxGetGtmAndGm";
	}
	// ajax根据商品类别uuid获取商品信息
	public String ajaxGetGm() {
		gmList = goodsEbi.getAllByGtm(gtmUuid);
		// 过滤数据
		for (int i = gmList.size() - 1; i >= 0; i--) {
			Long uuid = gmList.get(i).getUuid();
			// 判断该uuid是否出现在used中
			if (used.contains("'" + uuid + "'")) {
				gmList.remove(i);
			}
		}
		gm = gmList.get(0);
		return "ajaxGetGm";
	}

	// ajax根据商品Uuid获取商品价格信息
	public String ajaxGetPrice() {
		gm = goodsEbi.get(gmUuid);
		return "ajaxGetPrice";
	}
}
