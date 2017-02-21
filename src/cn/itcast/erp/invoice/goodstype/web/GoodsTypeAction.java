package cn.itcast.erp.invoice.goodstype.web;

import java.util.List;

import cn.itcast.erp.invoice.goodstype.business.ebi.GoodsTypeEbi;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeQueryModel;
import cn.itcast.erp.invoice.supplier.business.ebi.SupplierEbi;
import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.util.base.BaseAction;

public class GoodsTypeAction extends BaseAction {

	public GoodsTypeModel gm = new GoodsTypeModel();
	public GoodsTypeQueryModel gqm = new GoodsTypeQueryModel();

	private GoodsTypeEbi goodsTypeEbi;
	private SupplierEbi supplierEbi;

	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}
	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}

	// 列表
	public String list() {
		setDataTotal(goodsTypeEbi.getCount(gqm));
		List<GoodsTypeModel> goodsTypeList = goodsTypeEbi.getAll(gqm,
				maxPageNum, pageCount);
		put("goodsTypeList", goodsTypeList);
		return LIST;
	}

	// 到添加
	public String input() {
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList", supplierList);
		if (gm.getUuid() != null) {
			gm = goodsTypeEbi.get(gm.getUuid());
		}
		return INPUT;
	}

	// 添加
	public String save() {
		if (gm.getUuid() == null) {
			goodsTypeEbi.save(gm);
		} else {
			goodsTypeEbi.update(gm);
		}
		return TO_LIST;
	}

	// 删除
	public String delete() {
		goodsTypeEbi.delete(gm);
		return TO_LIST;
	}

	// ---AJAX-------

	// 1.设置struts返回result的type为json
	// 2.设置对应action所在的package继承至json_default
	// 3.将要返回的数据提供对应的get方法
	private List<GoodsTypeModel> gtmList;
	public List<GoodsTypeModel> getGtmList() {
		return gtmList;
	}
	// ajax获取供应商对应的类别信息
	public String ajaxGetBySm() {
		// 根据供应商的uuid获取对应的类别信息
		gtmList = goodsTypeEbi.getAllBySm(gm.getSm().getUuid());
		// 传递数据（JSON）
		// 1.使用json工具类JSONArray

		return "ajaxGetBySm";
	}
}

/*
 * { "gtmList": [ { "name":"鼠标",
 * "sm":{"address":"中关村大街鼎好大厦3楼","contact":"刘电脑","name"
 * :"中关村鼎好302号摊","needs":0,"needsView":"自提","tele":"88888888","uuid":2},
 * "uuid":5 }, { "name":"散热器",
 * "sm":{"address":"中关村大街鼎好大厦3楼","contact":"刘电脑","name"
 * :"中关村鼎好302号摊","needs":0,"needsView":"自提","tele":"88888888","uuid":2},
 * "uuid":6 }, { "name":"键盘",
 * "sm":{"address":"中关村大街鼎好大厦3楼","contact":"刘电脑","name"
 * :"中关村鼎好302号摊","needs":0,"needsView":"自提","tele":"88888888","uuid":2},
 * "uuid":7 } ] }
 */
