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
		return "buyList";
	}

	// 进入采购订单
	public String buyInput() {
		// 供应商
		List<SupplierModel> supplierList = supplierEbi.getAll();
		// 第一个供应商类别
		List<GoodsTypeModel> gtmList = goodsTypeEbi.getAllBySm(supplierList
				.get(0).getUuid());
		// 第一个类别的商品
		List<GoodsModel> gmList = goodsEbi
				.getAllByGtm(gtmList.get(0).getUuid());
		put("gmList", gmList);
		put("supplierList", supplierList);
		put("gtmList", gtmList);
		return "buyInput";
	}

}
