package cn.itcast.erp.auth.res.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.auth.res.vo.ResModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface ResEbi extends BaseEbi<ResModel>{
	/**
	 * 获取指定员工所有可操作资源信息
	 * @param uuid 员工uuid
	 * @return
	 */
	public List<ResModel> getAllByEmp(Long uuid);

}
