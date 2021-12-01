package dev.mvc.picture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.category.CategoryProcInter;
import dev.mvc.category.CategoryVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class PictureCont {
    
    @Autowired
    @Qualifier("dev.mvc.member.MemberProc")
    MemberProcInter mp;
    
    @Autowired
    @Qualifier("dev.mvc.category.CategoryProc")
    CategoryProcInter cp;
    
    @Autowired
    @Qualifier("dev.mvc.picture.PictureProc")
    PictureProcInter pp;
    
    private String uploadDir =  Contents.getUploadDir(); // OS�� ���� ���ε� ���
    
    private String testURL = "http://localhost:9091/some/path";
    
    /**
     * ���ΰ�ħ ����
     * �� ���̴���, ��� ���̴��� �ٸ������ ���ؽ�ų �� ���������� �˰� �־����. 
     * @return
     */
    @RequestMapping(value="/picture/msg.do", method=RequestMethod.GET)
    public ModelAndView msg(String url){
      ModelAndView mav = new ModelAndView();

      mav.setViewName(url); // forward
      
      return mav; // forward
    }
    
    
    /**
     * 
     * ���� �Է¹޾� ����
     * @return
     */
    @GetMapping("/picture")
    public ModelAndView viewForm(int  cate_id) {
        
        ModelAndView mav = new ModelAndView();
        
        CategoryVO categoryVO =  cp.readOne(cate_id);
        MemberVO memberVO = mp.readOne(categoryVO.getMember_id());
        
        mav.addObject("categoryVO", categoryVO);
        mav.addObject("memberVO", memberVO);
        
        mav.setViewName("/picture/create");
        return mav;
    }
    
    /**
     * ������ �Ѿ�� ���� + ������ �޾� ��� �� ������Ʈ storage ��ο� ��������
     * db�� ������ ���� �ִ� ������ �ƴ�, ��θ� �����ϰ� ���� ������ ���������� ��Ƴ��� ������ ����
     */
    @PostMapping("/picture")
    public ModelAndView create(PictureVO pictureVO) {
        
        ModelAndView mav = new ModelAndView();
       
        String file1 = "";          // ���� ���ϸ�
        String file1saved = "";  // ����� ���ϸ� (�����Է�)
        String thumb1 = "";     // preview image
        String upDir = this.uploadDir;

        // ���� ��� Ȯ��
        //String user_dir = System.getProperty("user.dir");
        // System.out.println("-> User dir: " + user_dir);
       
        
        // ���� ��� ����, static ���� ����
        // �ϼ��� ��� D:\MyProjects\SpringProjects\ptoa\src\main\resources\static\contents\storage
        
        //String upDir =  user_dir + "/src/main/resources/static/contents/storage/";
        // System.out.println("-> upDir: " + upDir);
        
        // ���� ������ ��� file1MF ��ü ����
        MultipartFile mf = pictureVO.getFile1MF();
        
        file1 = Tool.getFname(mf.getOriginalFilename()); // ���� ���� ���ϸ� ����
        // System.out.println("-> file1: " + file1);
        
        long size1 = mf.getSize();  // ���� ũ��
        
        if (size1 > 0) { // ���� ũ�� üũ
          // ���� ���� �� ���ε�� ���ϸ��� ����
          file1saved = Upload.saveFileSpring(mf, upDir); 
          
          if (Tool.isImage(file1saved)) { // �̹������� �˻�
            // thumb �̹��� ������ ���ϸ� ���� , width: 200, height: 150
            thumb1 = Tool.preview(upDir, file1saved, 200, 150); 
          }
          
        }    
        
        pictureVO.setFile1(file1);
        pictureVO.setFile1saved(file1saved);
        pictureVO.setThumb1(thumb1);
        pictureVO.setSize1(size1);
        
        int cnt = pp.create(pictureVO); 
        
        // ------------------------------------------------------------------------------
        // PK�� return
        // ------------------------------------------------------------------------------
        // System.out.println("--> contentsno: " + pictureVO.getContentsno());
        mav.addObject("picture_id", pictureVO.getPicture_id()); // redirect parameter ����
        // ------------------------------------------------------------------------------
        
        if (cnt == 1) {
            mav.addObject("code", "create_success");
        } else {
            mav.addObject("code", "create_fail");
        }
        mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
        
        // System.out.println("--> cateno: " + pictureVO.getCateno());
        // redirect�ÿ� hidden tag�� �����͵��� ������ �ȵ����� request�� �ٽ� ����
        mav.addObject("cateno", pictureVO.getCate_id()); // redirect parameter ����
        mav.addObject("url", "/result_msg"); // redirect parameter ����

        mav.setViewName("redirect:/picture/msg.do"); 
        
        return mav; 
      }
    
    // Ư�� ī�װ� �Ҽ� ��ü ���� ����Ʈ
    @GetMapping("/list_by_cate_id")
    public ModelAndView showPicturesByCateId(int cate_id) {
        
        ModelAndView mav = new ModelAndView();
        
        //db��ȸ
        CategoryVO categoryVO = cp.readOne(cate_id);
        List<PictureVO> list = pp.readAllPictures(cate_id);
        
        mav.addObject("categoryVO", categoryVO);
        mav.addObject("list", list);
        
        mav.setViewName("/picture/list_by_cate_id");
        return mav;
    }
    
    // �Խñ� �ϳ��� ���� ��
    @GetMapping("/read.do")
    public ModelAndView readBoard(int picture_id) {
        
        ModelAndView mav = new ModelAndView();
        
        PictureVO pictureVO = pp.read(picture_id);
        CategoryVO categoryVO = cp.readOne(pictureVO.getCate_id());
        
        mav.addObject("pictureVO", pictureVO);
        mav.addObject("categoryVO", categoryVO);
        
        mav.setViewName("/picture/read");
        return mav;
    }
    
    // sql�� ����� hashmap �ʱ�ȭ + view�� ������ objs�� �ʱ�ȭ �ؾ���.
    @GetMapping("/list_by_name_search")
    public ModelAndView listBySearchName(@RequestParam(value = "cate_id", defaultValue = "1") int cate_id,
                                                            @RequestParam(value = "word", defaultValue = "") String word) {
        
        // 1. ds
        ModelAndView mav = new ModelAndView();
        HashMap<String, Object> hashMap = new HashMap<>();
        List<PictureVO> list = new ArrayList<>(); // --> null pointer exception ����
        
        hashMap.put("cate_id", cate_id);
        hashMap.put("word", word);
        
        int search_count = pp.searchCount(hashMap);
        System.out.println(search_count);
        list = pp.listByCateId(hashMap);
        CategoryVO categoryVO = cp.readOne(cate_id);
        MemberVO memberVO = mp.readOne(categoryVO.getMember_id());
        
        mav.addObject("list", list);
        mav.addObject("categoryVO", categoryVO);
        mav.addObject("memberVO", memberVO);
        mav.addObject("search_count", search_count);
        
        mav.setViewName("/picture/list_by_cateno_search");
        
        return mav;
    }
    /**
     * ���� ��� api �׽�Ʈ ��Ʈ�ѷ� 
     * @param file1MF
     * @return
     */
    @PostMapping("/api/test.do")
    @ResponseBody
    public String testApi(@RequestParam("file1MF") MultipartFile file1MF) {
        
        // private String testURL = "http://localhost:9091/some/path";
        
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", file1MF.getResource());
        
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange(testURL, HttpMethod.POST, requestEntity, String.class);
        
        return "done";
    }
}
