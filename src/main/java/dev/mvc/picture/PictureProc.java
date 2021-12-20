package dev.mvc.picture;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;

// 컴포넌트 이름을 만들어주지 않으면, 컨트롤러 단에서 인터페이스와 구현체의 빈이 모두 등록되어 충돌나는 현상이 발생. 
@Component("dev.mvc.picture.PictureProc")
public class PictureProc implements PictureProcInter {
    
    @Autowired // --> singleton이라 qualifier 명시 안해도 되는건가?
    private PictureDAOInter pictureDAO;

    @Override
    public int create(PictureVO pictureVO) {
        return pictureDAO.create(pictureVO);
    }

    /**
     * 특수문자 처리, 파일크기 계산하여 set
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

    @Override // 본문 160자 넘어가는거 컷트하는 로직 만들어보기
    public List<PictureVO> readAllPictures(int cate_id) {
        return pictureDAO.readAllPictures(cate_id);
    }

    
    
    @Override
    public List<PictureVO> listByCateId(HashMap<String, Object> hashMap) {
        
        //dao의 listByCateId..이다 엄연히 다른 메서드
        List<PictureVO> list = pictureDAO.listByCateId(hashMap);
        
        for (PictureVO PictureVO : list) { // 내용이 160자 이상이면 160자만 선택
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
