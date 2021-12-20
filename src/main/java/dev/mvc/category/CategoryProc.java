package dev.mvc.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * Description:

   Field cp in dev.mvc.category.CategoryCont required a single bean, but 2 were found:
	- categoryProc: defined in file [D:\MyProjects\SpringProjects\ptoa\bin\main\dev\mvc\category\CategoryProc.class]
	- categoryProcInter: defined in file [D:\MyProjects\SpringProjects\ptoa\bin\main\dev\mvc\category\CategoryProcInter.class]
   
   Action:

  Consider marking one of the beans as @Primary,
   updating the consumer to accept multiple beans,
   or using @Qualifier to identify the bean that should be consumed
 */

//스프링이 찾아서 사용할 수 있도록 Bean등록 (이름생)
@Component("dev.mvc.category.CategoryProc")
public class CategoryProc implements CategoryProcInter {
	
	@Autowired
	private CategoryDAOInter categoryDAO;

	@Override
	public int create(CategoryVO categoryVO) {
		return categoryDAO.create(categoryVO);
	}

	@Override
	public List<CategoryVO> listAllCate(int member_id) {
		return categoryDAO.listAllCate(member_id);
	}

	@Override
	public CategoryVO readOne(int cate_id) {
		return categoryDAO.readOne(cate_id);
	}

	@Override
	public int updateCate(CategoryVO categoryVO) {
		return categoryDAO.updateCate(categoryVO);
	}

	@Override
	public int deleteCate(int cate_id) {
		return categoryDAO.deleteCate(cate_id);
	}
	
	
}
