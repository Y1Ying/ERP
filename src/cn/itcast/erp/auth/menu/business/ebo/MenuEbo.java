package cn.itcast.erp.auth.menu.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.itcast.erp.auth.menu.business.ebi.MenuEbi;
import cn.itcast.erp.auth.menu.dao.dao.MenuDao;
import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.util.base.BaseQueryModel;

public class MenuEbo implements MenuEbi {
	private MenuDao menuDao;
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	// 废弃
	@Override
	public void save(MenuModel mm) {
		menuDao.save(mm);
	}
	// 废弃
	@Override
	public void update(MenuModel mm) {
		// 使用快照来进行修改操作
		MenuModel temp = menuDao.get(mm.getUuid());
		temp.setName(mm.getName());
		temp.setUrl(mm.getUrl());
	}

	@Override
	public void delete(MenuModel mm) {
		// 完成级联删除的前提是，被删除的级联的数据存在
		// 删除OID为4的数据时，对应的对象应该级联哪些必须是已知的，此时对象中的关系数据存在
		// mm对象此时只有uuid数据，关系数据为null
		// hibernate对于关系数据为null的处理方式是：断开所有关系即可，由于此时设置了inverse=true,又不维护关系，没有进行任何操作
		// 解决方案：级联删除前加载关系

		MenuModel temp = menuDao.get(mm.getUuid());
		// temp对象此时就有了延迟加载功能，可以随时加载关系
		menuDao.delete(temp);
	}
	@Override
	public List<MenuModel> getAll() {
		return menuDao.getAll();
	}

	@Override
	public MenuModel get(Serializable uuid) {
		return menuDao.get(uuid);
	}

	@Override
	public List<MenuModel> getAll(BaseQueryModel qm, Integer pageNum,
			Integer pageCount) {
		return menuDao.getAll(qm, pageNum, pageCount);
	}

	@Override
	public Integer getCount(BaseQueryModel qm) {
		return menuDao.getCount(qm);
	}

	@Override
	public List<MenuModel> getAllOneLevel() {
		return menuDao.getByPuuidIsOneOrZero();
	}

	@Override
	public void save(MenuModel mm, Long[] roleUuids) {
		// array->set->mm
		Set<RoleModel> roles = new HashSet<RoleModel>();
		for (Long uuid : roleUuids) {
			RoleModel temp = new RoleModel();
			temp.setUuid(uuid);
			roles.add(temp);
		}
		mm.setRoles(roles);
		menuDao.save(mm);
	}

	@Override
	public void update(MenuModel mm, Long[] roleUuids) {
		// 使用快照来进行修改操作
		MenuModel temp = menuDao.get(mm.getUuid());
		temp.setName(mm.getName());
		temp.setUrl(mm.getUrl());

		Set<RoleModel> roles = new HashSet<RoleModel>();
		for (Long uuid : roleUuids) {
			RoleModel temp2 = new RoleModel();
			temp2.setUuid(uuid);
			roles.add(temp2);
		}
		temp.setRoles(roles);
	}

	// 废弃
	// public List<MenuModel> getAllOneLevel2() {
	// // 1.使用现有的方法
	// // MenuQueryModel mqm = new MenuQueryModel();
	// // MenuModel parent = new MenuModel();
	// // parent.setUuid(MenuModel.MENU_SYSTEM_MENU_UUID);
	// // mqm.setParent(parent);
	// // menuDao.getAll(mqm, 1, Integer.MAX_VALUE);
	//
	// // 2.重写方法
	// return menuDao.getAllOneLevel2();
	// }

	@Override
	public List<MenuModel> getAllOneLevelByEmp(Long uuid) {
		return menuDao.getAllOneLevelByEmpUuid(uuid);
	}

	@Override
	public List<MenuModel> getByEmpAndPuuid(Long uuid, Long puuid) {
		return menuDao.getByEmpUuidAndPuuid(uuid,puuid);
	}

}
