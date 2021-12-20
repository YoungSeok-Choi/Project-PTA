package dev.mvc.category;

import java.util.List;

public interface CategoryDAOInter {
	
	/**
	 * @param categoryVO
	 * @return ó���� ���� ��.
	 */
	public int create(CategoryVO categoryVO);
	
	/**
	 * 
	 * @param member_id
	 * @return List of Categories
	 */
	public List<CategoryVO> listAllCate(int member_id);
	
	/**
	 * 
	 * @param cate_id (int)
	 * @return one of Object(ī�װ�)
	 */
	public CategoryVO readOne(int cate_id);
	
	/**
	 * 
	 * @param categoryVO
	 * @return int(ó���� ���� ��)
	 */
	public int updateCate(CategoryVO categoryVO);
	
	/**
	 * 
	 * @param cate_id
	 * @return int(ó���� ���� ��)
	 */
	public int deleteCate(int cate_id);

}
