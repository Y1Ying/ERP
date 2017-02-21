package cn.itcast.erp.invoice.goodstype.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface GoodsTypeEbi extends BaseEbi<GoodsTypeModel> {

	/**
	 * 获取指定供应商商品类别信息
	 * 
	 * @param uuid
	 *            供应商uuid
	 * @return
	 */
	public List<GoodsTypeModel> getAllBySm(Long uuid);

}
