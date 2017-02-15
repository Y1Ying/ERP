package cn.itcast.erp.auth.menu.bussiness.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface MenuEbi extends BaseEbi<MenuModel> {
	/**
	 * 获取系统菜单和所有的一级菜单
	 * 
	 * @return
	 */
	public List<MenuModel> getAllOneLevel();

}
