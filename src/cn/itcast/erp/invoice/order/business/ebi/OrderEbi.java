package cn.itcast.erp.invoice.order.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.invoice.order.vo.OrderModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface OrderEbi extends BaseEbi<OrderModel>{

}
