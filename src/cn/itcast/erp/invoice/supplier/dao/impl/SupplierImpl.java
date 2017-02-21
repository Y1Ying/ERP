package cn.itcast.erp.invoice.supplier.dao.impl;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.erp.invoice.supplier.dao.dao.SupplierDao;
import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.util.base.BaseImpl;
import cn.itcast.erp.util.base.BaseQueryModel;

public class SupplierImpl extends BaseImpl<SupplierModel>
		implements
			SupplierDao {

	@Override
	public void doQbc(DetachedCriteria dc, BaseQueryModel qm) {

	}

}
