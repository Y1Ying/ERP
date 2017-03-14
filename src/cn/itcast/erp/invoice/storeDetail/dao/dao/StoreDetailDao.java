package cn.itcast.erp.invoice.storeDetail.dao.dao;

import cn.itcast.erp.invoice.storeDetail.vo.StoreDetailModel;
import cn.itcast.erp.util.base.BaseDao;

public interface StoreDetailDao extends BaseDao<StoreDetailModel> {

	public StoreDetailModel getBySmAndGm(Long storeUuid, Long uuid);

}
