package cn.itcast.erp.auth.menu.web;

import java.util.List;

import cn.itcast.erp.auth.menu.bussiness.ebi.MenuEbi;
import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.auth.menu.vo.MenuQueryModel;
import cn.itcast.erp.util.base.BaseAction;

public class MenuAction extends BaseAction {
	public MenuModel mm = new MenuModel();
	public MenuQueryModel mqm = new MenuQueryModel();

	private MenuEbi menuEbi;
	public void setMenuEbi(MenuEbi menuEbi) {
		this.menuEbi = menuEbi;
	}

	// 列表
	public String list() {
		// 加载所有的一级菜单
		List<MenuModel> parentList = menuEbi.getAllOneLevel();
		put("parentList", parentList);
		setDataTotal(menuEbi.getCount(mqm));
		List<MenuModel> menuList = menuEbi.getAll(mqm, maxPageNum, pageCount);
		put("menuList", menuList);
		return LIST;
	}

	// 到添加
	public String input() {
		// 加载所有的一级菜单
		List<MenuModel> menuList = menuEbi.getAllOneLevel();
		put("menuList", menuList);
		if (mm.getUuid() != null) {
			mm = menuEbi.get(mm.getUuid());
		}
		return INPUT;
	}

	// 添加
	public String save() {
		if (mm.getUuid() == null) {
			menuEbi.save(mm);
		} else {
			menuEbi.update(mm);
		}
		return TO_LIST;
	}

	// 删除
	public String delete() {
		menuEbi.delete(mm);
		return TO_LIST;
	}

}
