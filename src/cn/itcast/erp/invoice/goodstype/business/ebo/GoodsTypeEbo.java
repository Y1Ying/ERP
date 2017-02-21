package cn.itcast.erp.invoice.goodstype.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.itcast.erp.invoice.goodstype.business.ebi.GoodsTypeEbi;
import cn.itcast.erp.invoice.goodstype.dao.dao.GoodsTypeDao;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.util.base.BaseQueryModel;

public class GoodsTypeEbo implements GoodsTypeEbi {
	private GoodsTypeDao goodsTypeDao;
	public void setGoodsTypeDao(GoodsTypeDao goodsTypeDao) {
		this.goodsTypeDao = goodsTypeDao;
	}

	@Override
	public void save(GoodsTypeModel gm) {
		goodsTypeDao.save(gm);
	}

	@Override
	public void update(GoodsTypeModel gm) {
		goodsTypeDao.update(gm);
	}

	@Override
	public void delete(GoodsTypeModel gm) {
		goodsTypeDao.delete(gm);
	}

	@Override
	public List<GoodsTypeModel> getAll() {
		return goodsTypeDao.getAll();
	}

	@Override
	public GoodsTypeModel get(Serializable uuid) {
		return goodsTypeDao.get(uuid);
	}

	@Override
	public List<GoodsTypeModel> getAll(BaseQueryModel qm, Integer pageNum,
			Integer pageCount) {
		return goodsTypeDao.getAll(qm, pageNum, pageCount);
	}

	@Override
	public Integer getCount(BaseQueryModel qm) {
		return goodsTypeDao.getCount(qm);
	}

	@Override
	public List<GoodsTypeModel> getAllBySm(Long uuid) {
		return goodsTypeDao.getAllBySmUuid(uuid);
	}

}
