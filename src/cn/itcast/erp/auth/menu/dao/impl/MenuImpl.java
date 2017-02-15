package cn.itcast.erp.auth.menu.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.itcast.erp.auth.menu.dao.dao.MenuDao;
import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.auth.menu.vo.MenuQueryModel;
import cn.itcast.erp.util.base.BaseImpl;
import cn.itcast.erp.util.base.BaseQueryModel;

public class MenuImpl extends BaseImpl<MenuModel> implements MenuDao {

	@Override
	public void doQbc(DetachedCriteria dc, BaseQueryModel qm) {
		MenuQueryModel mqm = (MenuQueryModel) qm;
		// 设置过滤掉系统菜单的条件 uuid != 1
		dc.add(Restrictions.not(Restrictions.eq("uuid",
				MenuModel.MENU_SYSTEM_MENU_UUID)));

	}

	@Override
	public List<MenuModel> getByPuuidIsOneOrZero() {
		String hql = "from MenuModel where parent.uuid = ? or uuid = ?";
		return this.getHibernateTemplate().find(hql,
				MenuModel.MENU_SYSTEM_MENU_UUID,
				MenuModel.MENU_SYSTEM_MENU_UUID);
	}

}
