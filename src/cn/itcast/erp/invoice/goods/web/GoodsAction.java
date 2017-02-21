package cn.itcast.erp.invoice.goods.web;

import java.util.List;

import cn.itcast.erp.invoice.goods.business.ebi.GoodsEbi;
import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.invoice.goods.vo.GoodsQueryModel;
import cn.itcast.erp.invoice.goodstype.business.ebi.GoodsTypeEbi;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.invoice.supplier.business.ebi.SupplierEbi;
import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.util.base.BaseAction;

public class GoodsAction extends BaseAction {
	public GoodsModel gm = new GoodsModel();
	public GoodsQueryModel gqm = new GoodsQueryModel();

	private GoodsEbi goodsEbi;
	private SupplierEbi supplierEbi;
	private GoodsTypeEbi goodsTypeEbi;

	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}

	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}

	// 列表
	public String list() {
		setDataTotal(goodsEbi.getCount(gqm));
		List<GoodsModel> goodsList = goodsEbi.getAll(gqm, pageNum, pageCount);
		put("goodsList", goodsList);
		return LIST;
	}

	// 到添加
	public String input() {
		// 加载所有供应商信息
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList", supplierList);
		// 加载供应商对应的商品类别信息
		List<GoodsTypeModel> gtmList = goodsTypeEbi.getAllBySm(supplierList
				.get(0).getUuid());
		put("gtmList", gtmList);
		if (gm.getUuid() != null) {
			gm = goodsEbi.get(gm.getUuid());
		}
		return INPUT;
	}
	// 添加
	public String save() {
		if (gm.getUuid() == null) {
			goodsEbi.save(gm);
		} else {
			goodsEbi.update(gm);
		}
		return TO_LIST;
	}

	// 删除
	public String delete() {
		goodsEbi.delete(gm);
		return TO_LIST;
	}

}
