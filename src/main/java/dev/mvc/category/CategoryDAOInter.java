package dev.mvc.category;

import java.util.List;

public interface CategoryDAOInter {
	
	/**
	 * @param categoryVO
	 * @return 贸府等 青狼 荐.
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
	 * @return one of Object(墨抛绊府)
	 */
	public CategoryVO readOne(int cate_id);
	
	/**
	 * 
	 * @param categoryVO
	 * @return int(贸府等 青狼 荐)
	 */
	public int updateCate(CategoryVO categoryVO);
	
	/**
	 * 
	 * @param cate_id
	 * @return int(贸府等 青狼 荐)
	 */
	public int deleteCate(int cate_id);

}
