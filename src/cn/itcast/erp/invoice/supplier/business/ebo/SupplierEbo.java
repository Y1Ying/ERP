package cn.itcast.erp.invoice.supplier.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.itcast.erp.invoice.supplier.business.ebi.SupplierEbi;
import cn.itcast.erp.invoice.supplier.dao.dao.SupplierDao;
import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.util.base.BaseQueryModel;

public class SupplierEbo implements SupplierEbi {

	private SupplierDao supplierDao;

	public void setSupplierDao(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}

	@Override
	public void save(SupplierModel sm) {
		supplierDao.save(sm);
	}

	@Override
	public void update(SupplierModel sm) {
		supplierDao.update(sm);
	}

	@Override
	public void delete(SupplierModel sm) {
		supplierDao.delete(sm);
	}

	@Override
	public List<SupplierModel> getAll() {
		return supplierDao.getAll();
	}

	@Override
	public SupplierModel get(Serializable uuid) {
		return supplierDao.get(uuid);
	}

	@Override
	public List<SupplierModel> getAll(BaseQueryModel qm, Integer pageNum,
			Integer pageCount) {
		return supplierDao.getAll(qm, pageNum, pageCount);
	}

	@Override
	public Integer getCount(BaseQueryModel qm) {
		return supplierDao.getCount(qm);
	}

}
