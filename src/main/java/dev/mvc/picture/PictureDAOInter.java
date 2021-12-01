package dev.mvc.picture;

import java.util.HashMap;
import java.util.List;

public interface PictureDAOInter {
    
    
    /**
     * �����޼���
     * @param pictureVO
     * @return ó���� ���� ��(int)
     */
    public int create(PictureVO pictureVO);
    
    /**
     * ����/������ ��ȸ�޼���
     * @param picture_id
     * @return ������ü 1��
     */
    public PictureVO read(int picture_id);
    
    
    /**
     *  ��Ͽ� ��ȸ�޼���
     * @param cate_id
     * @return ������ü �迭
     */
    public List<PictureVO> readAllPictures (int cate_id);
    
    /**
     * 
     * @param hashMap
     * @return list of ������ü
     */
    public List<PictureVO> listByCateId(HashMap<String, Object> hashMap);
    
    /**
     * 
     * @param hashMap
     * @return
     */
    public int searchCount(HashMap<String, Object> hashMap);

}
