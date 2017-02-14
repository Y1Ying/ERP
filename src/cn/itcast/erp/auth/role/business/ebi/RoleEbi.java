package cn.itcast.erp.auth.role.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface RoleEbi extends BaseEbi<RoleModel>{

	public void save(RoleModel rm, Long[] resUuids);

	public void update(RoleModel rm, Long[] resUuids);

}
