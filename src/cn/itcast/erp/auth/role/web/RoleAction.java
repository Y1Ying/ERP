package cn.itcast.erp.auth.role.web;

import java.util.List;

import cn.itcast.erp.auth.menu.business.ebi.MenuEbi;
import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.auth.res.business.ebi.ResEbi;
import cn.itcast.erp.auth.res.vo.ResModel;
import cn.itcast.erp.auth.role.business.ebi.RoleEbi;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.auth.role.vo.RoleQueryModel;
import cn.itcast.erp.util.base.BaseAction;

public class RoleAction extends BaseAction {
	public RoleModel rm = new RoleModel();
	public RoleQueryModel rqm = new RoleQueryModel();

	private RoleEbi roleEbi;
	private ResEbi resEbi;
	private MenuEbi menuEbi;

	public void setMenuEbi(MenuEbi menuEbi) {
		this.menuEbi = menuEbi;
	}
	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}
	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}

	// 列表
	public String list() {
		setDataTotal(roleEbi.getCount(rqm));
		List<RoleModel> roleList = roleEbi.getAll(rqm, pageNum, pageCount);
		put("roleList", roleList);
		return LIST;
	}

	// 到添加
	public String input() {
		// 加载菜单信息
		List<MenuModel> menuList = menuEbi.getAll();
		put("menuList", menuList);
		// 加载资源列表
		List<ResModel> resList = resEbi.getAll();
		put("resList", resList);
		if (rm.getUuid() != null) {
			rm = roleEbi.get(rm.getUuid());
			// 对resUuids初始化
			resUuids = new Long[rm.getReses().size()];
			int i = 0;
			for (ResModel temp : rm.getReses()) {
				resUuids[i++] = temp.getUuid();
			}

			menuUuids = new Long[rm.getMenus().size()];
			i = 0;
			for (MenuModel temp : rm.getMenus()) {
				menuUuids[i++] = temp.getUuid();
			}
		}
		return INPUT;
	}

	public Long[] resUuids;
	public Long[] menuUuids;
	// 添加
	public String save() {
		if (rm.getUuid() == null) {
			roleEbi.save(rm, resUuids, menuUuids);
		} else {
			roleEbi.update(rm, resUuids, menuUuids);
		}
		return TO_LIST;
	}

	// 删除
	public String delete() {
		roleEbi.delete(rm);
		return TO_LIST;
	}

}
