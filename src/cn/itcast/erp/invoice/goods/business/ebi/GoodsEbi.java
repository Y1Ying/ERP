package cn.itcast.erp.invoice.goods.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface GoodsEbi extends BaseEbi<GoodsModel> {

	/**
	 * 获取指定商品类别对应的所有商品信息
	 * 
	 * @param uuid
	 *            类别uuid
	 * @return
	 */
	public List<GoodsModel> getAllByGtm(Long uuid);

}
