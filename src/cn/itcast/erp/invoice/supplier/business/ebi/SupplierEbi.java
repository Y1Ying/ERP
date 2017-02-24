package cn.itcast.erp.invoice.supplier.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface SupplierEbi extends BaseEbi<SupplierModel> {

	/**
	 * 获取具有商品类别信息的供应商的信息
	 * 
	 * @return
	 */
	public List<SupplierModel> getAllUnion();

	public List<SupplierModel> getAllUnionTwo();

}
