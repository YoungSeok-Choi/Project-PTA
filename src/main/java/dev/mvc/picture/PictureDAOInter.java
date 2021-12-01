package dev.mvc.picture;

import java.util.HashMap;
import java.util.List;

public interface PictureDAOInter {
    
    
    /**
     * 생성메서드
     * @param pictureVO
     * @return 처리된 행의 수(int)
     */
    public int create(PictureVO pictureVO);
    
    /**
     * 수정/삭제용 조회메서드
     * @param picture_id
     * @return 사진객체 1개
     */
    public PictureVO read(int picture_id);
    
    
    /**
     *  목록용 조회메서드
     * @param cate_id
     * @return 사진객체 배열
     */
    public List<PictureVO> readAllPictures (int cate_id);
    
    /**
     * 
     * @param hashMap
     * @return list of 사진객체
     */
    public List<PictureVO> listByCateId(HashMap<String, Object> hashMap);
    
    /**
     * 
     * @param hashMap
     * @return
     */
    public int searchCount(HashMap<String, Object> hashMap);

}
