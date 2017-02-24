package cn.itcast.erp.invoice.supplier.dao.impl;

import java.util.List;

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

	@Override
	public List<SupplierModel> getAllUnion() {
		// 需要查询供应商与类别信息有关联的数据
		String hql = "select distinct s from GoodsTypeModel gtm join gtm.sm s";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public List<SupplierModel> getAllUnionTwo() {
		String hql = "select distinct s from  GoodsModel gm join gm.gtm gt join gt.sm s ";
		return this.getHibernateTemplate().find(hql);
	}

	// 测试
	// public static void main(String[] args) {
	// ApplicationContext ctx = new ClassPathXmlApplicationContext(
	// "applicationContext.xml", "applicationContext-supplier.xml");
	// SupplierDao dao = (SupplierDao) ctx.getBean("supplierDao");
	// System.out.println(dao.getAllUnion());
	// }

}
