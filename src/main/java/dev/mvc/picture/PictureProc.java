package dev.mvc.picture;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;

// ������Ʈ �̸��� ��������� ������, ��Ʈ�ѷ� �ܿ��� �������̽��� ����ü�� ���� ��� ��ϵǾ� �浹���� ������ �߻�. 
@Component("dev.mvc.picture.PictureProc")
public class PictureProc implements PictureProcInter {
    
    @Autowired // --> singleton�̶� qualifier ��� ���ص� �Ǵ°ǰ�?
    private PictureDAOInter pictureDAO;

    @Override
    public int create(PictureVO pictureVO) {
        return pictureDAO.create(pictureVO);
    }

    /**
     * Ư������ ó��, ����ũ�� ����Ͽ� set
     */
    @Override
    public PictureVO read(int picture_id) {
        
        PictureVO pictureVO = pictureDAO.read(picture_id);      
        
        String title = pictureVO.getTitle();
        String content = pictureVO.getContent();
        
        title = Tool.convertChar(title);
        content = Tool.convertChar(content);
        
        pictureVO.setTitle(title);
        pictureVO.setContent(content);
        
        long size1 = pictureVO.getSize1();
        pictureVO.setSize1_label(Tool.unit(size1));
        
        return pictureVO;
    }

    @Override // ���� 160�� �Ѿ�°� ��Ʈ�ϴ� ���� ������
    public List<PictureVO> readAllPictures(int cate_id) {
        return pictureDAO.readAllPictures(cate_id);
    }

    
    
    @Override
    public List<PictureVO> listByCateId(HashMap<String, Object> hashMap) {
        
        //dao�� listByCateId..�̴� ������ �ٸ� �޼���
        List<PictureVO> list = pictureDAO.listByCateId(hashMap);
        
        for (PictureVO PictureVO : list) { // ������ 160�� �̻��̸� 160�ڸ� ����
          String content = PictureVO.getContent();
          if (content.length() > 160) {
            content = content.substring(0, 160) + "...";
            PictureVO.setContent(content);
          }
        }
        return list;
    }

    @Override
    public int searchCount(HashMap<String, Object> hashMap) {
        return pictureDAO.searchCount(hashMap);
    }
    
    
    
}
