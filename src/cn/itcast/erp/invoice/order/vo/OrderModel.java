package cn.itcast.erp.invoice.order.vo;

import java.util.HashMap;
import java.util.Map;

import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.util.format.FormatUtil;

public class OrderModel {

	public static final Integer ORDER_ORDERTYPE_OF_BUY = 1;
	public static final Integer ORDER_ORDERTYPE_OF_SALE = 2;
	public static final Integer ORDER_ORDERTYPE_OF_RETURN_BUY = 3;
	public static final Integer ORDER_ORDERTYPE_OF_RETURN_SALE = 4;

	public static final String ORDER_ORDERTYPE_OF_BUY_VIEW = "采购";
	public static final String ORDER_ORDERTYPE_OF_SALE_VIEW = "销售";
	public static final String ORDER_ORDERTYPE_OF_RETURN__VIEW = "采购退货";
	public static final String ORDER_ORDERTYPE_OF_RETURN_S_VIEW = "销售退货";

	// 第一位代表买，第二个代表流程第几步，第三个代表状态 1通过 0未通过
	public static final Integer ORDER_TYPE_OF_BUY_NO_CHECK = 111;
	public static final Integer ORDER_TYPE_OF_BUY_CHECK_PASS = 121;
	public static final Integer ORDER_TYPE_OF_BUY_CHECK_NO_PASS = 120;
	public static final Integer ORDER_TYPE_OF_BUY_BUYING = 131;
	public static final Integer ORDER_TYPE_OF_BUY_IN_STORE = 141;
	public static final Integer ORDER_TYPE_OF_BUY_COMPLETE = 199;

	public static final String ORDER_TYPE_OF_BUY_NO_CHECK_VIEW = "未审核";
	public static final String ORDER_TYPE_OF_BUY_CHECK_PASS_VIEW = "通过";
	public static final String ORDER_TYPE_OF_BUY_CHECK_NO_PASS_VIEW = "驳回";
	public static final String ORDER_TYPE_OF_BUY_BUYING_VIEW = "采购中";
	public static final String ORDER_TYPE_OF_BUY_IN_STORE_VIEW = "入库中";
	public static final String ORDER_TYPE_OF_BUY_COMPLETE_VIEW = "结单";

	public static final Integer ORDER_TYPE_OF_SALE_NO_CHECK = 211;
	public static final Integer ORDER_TYPE_OF_SALE_CHECK_PASS = 221;

	public static final String ORDER_TYPE_OF_SALE_NO_CHECK_VIEW = "未审核";
	public static final String ORDER_TYPE_OF_SALE_CHECK_PASS_VIEW = "通过";

	public static final Map<Integer, String> orderTypeMap = new HashMap<Integer, String>();
	public static final Map<Integer, String> typeMap = new HashMap<Integer, String>();
	static {
		orderTypeMap.put(ORDER_ORDERTYPE_OF_BUY, ORDER_ORDERTYPE_OF_BUY_VIEW);
		orderTypeMap.put(ORDER_ORDERTYPE_OF_SALE, ORDER_ORDERTYPE_OF_SALE_VIEW);
		orderTypeMap.put(ORDER_ORDERTYPE_OF_RETURN_BUY,
				ORDER_ORDERTYPE_OF_RETURN__VIEW);
		orderTypeMap.put(ORDER_ORDERTYPE_OF_RETURN_SALE,
				ORDER_ORDERTYPE_OF_RETURN_S_VIEW);

		typeMap.put(ORDER_TYPE_OF_BUY_NO_CHECK, ORDER_TYPE_OF_BUY_NO_CHECK_VIEW);
		typeMap.put(ORDER_TYPE_OF_BUY_CHECK_PASS,
				ORDER_TYPE_OF_BUY_CHECK_PASS_VIEW);
		typeMap.put(ORDER_TYPE_OF_BUY_CHECK_NO_PASS,
				ORDER_TYPE_OF_BUY_CHECK_NO_PASS_VIEW);
		typeMap.put(ORDER_TYPE_OF_BUY_BUYING, ORDER_TYPE_OF_BUY_BUYING_VIEW);
		typeMap.put(ORDER_TYPE_OF_BUY_IN_STORE, ORDER_TYPE_OF_BUY_IN_STORE_VIEW);
		typeMap.put(ORDER_TYPE_OF_BUY_COMPLETE, ORDER_TYPE_OF_BUY_COMPLETE_VIEW);

		typeMap.put(ORDER_TYPE_OF_SALE_NO_CHECK,
				ORDER_TYPE_OF_SALE_NO_CHECK_VIEW);
		typeMap.put(ORDER_TYPE_OF_SALE_CHECK_PASS,
				ORDER_TYPE_OF_SALE_CHECK_PASS_VIEW);
	}

	private Long uuid;

	private String orderNum;
	private Integer totalNum;

	private Long createTime;
	private Long checkTime;
	private Long endTime;
	private Integer orderType;
	private Integer type;
	private Double totalPrice;

	private String createTimeView;
	private String checkTimeView;
	private String endTimeView;
	private String orderTypeView;
	private String typeView;
	private String totalPriceView;

	private EmpModel creater;
	private EmpModel checker;
	private EmpModel completer;
	private SupplierModel sm;

	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
		this.createTimeView = FormatUtil.formatDate(createTime);
	}
	public Long getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Long checkTime) {
		this.checkTime = checkTime;
		this.checkTimeView = FormatUtil.formatDate(checkTime);
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
		this.endTimeView = FormatUtil.formatDate(endTime);
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
		this.orderTypeView = orderTypeMap.get(orderType);
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
		this.typeView = typeMap.get(type);
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
		this.totalPriceView = FormatUtil.formatMoney(totalPrice);
	}
	public EmpModel getCreater() {
		return creater;
	}
	public void setCreater(EmpModel creater) {
		this.creater = creater;
	}
	public EmpModel getChecker() {
		return checker;
	}
	public void setChecker(EmpModel checker) {
		this.checker = checker;
	}
	public EmpModel getCompleter() {
		return completer;
	}
	public void setCompleter(EmpModel completer) {
		this.completer = completer;
	}
	public SupplierModel getSm() {
		return sm;
	}
	public void setSm(SupplierModel sm) {
		this.sm = sm;
	}
	public String getCreateTimeView() {
		return createTimeView;
	}
	public String getCheckTimeView() {
		return checkTimeView;
	}
	public String getEndTimeView() {
		return endTimeView;
	}
	public String getOrderTypeView() {
		return orderTypeView;
	}
	public String getTypeView() {
		return typeView;
	}
	public String getTotalPriceView() {
		return totalPriceView;
	}

}
