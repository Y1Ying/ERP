package cn.itcast.erp.invoice.storeDetail.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.invoice.storeDetail.vo.StoreDetailModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface StoreDetailEbi extends BaseEbi<StoreDetailModel>{

}
