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
    
    private String uploadDir =  Contents.getUploadDir(); // OS별 파일 업로드 경로
    
    private String testURL = "http://localhost:9091/some/path";
    
    /**
     * 새로고침 방지
     * 왜 쓰이는지, 어떻게 쓰이는지 다른사람을 이해시킬 수 있을정도로 알고 있어야함. 
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
     * 사진 입력받아 생성
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
     * 폼에서 넘어온 사진 + 데이터 받아 디비 및 프로젝트 storage 경로에 사진저장
     * db에 사직을 직접 넣는 형식이 아닌, 경로만 저장하고 실제 사진은 정적데이터 모아놓은 폴더에 저장
     */
    @PostMapping("/picture")
    public ModelAndView create(PictureVO pictureVO) {
        
        ModelAndView mav = new ModelAndView();
       
        String file1 = "";          // 원본 파일명
        String file1saved = "";  // 저장된 파일명 (유저입력)
        String thumb1 = "";     // preview image
        String upDir = this.uploadDir;

        // 기준 경로 확인
        //String user_dir = System.getProperty("user.dir");
        // System.out.println("-> User dir: " + user_dir);
       
        
        // 절대 경로 지정, static 폴더 지정
        // 완성된 경로 D:\MyProjects\SpringProjects\ptoa\src\main\resources\static\contents\storage
        
        //String upDir =  user_dir + "/src/main/resources/static/contents/storage/";
        // System.out.println("-> upDir: " + upDir);
        
        // 전송 파일이 없어도 file1MF 객체 생성
        MultipartFile mf = pictureVO.getFile1MF();
        
        file1 = Tool.getFname(mf.getOriginalFilename()); // 원본 순수 파일명 산출
        // System.out.println("-> file1: " + file1);
        
        long size1 = mf.getSize();  // 파일 크기
        
        if (size1 > 0) { // 파일 크기 체크
          // 파일 저장 후 업로드된 파일명이 리턴
          file1saved = Upload.saveFileSpring(mf, upDir); 
          
          if (Tool.isImage(file1saved)) { // 이미지인지 검사
            // thumb 이미지 생성후 파일명 리턴 , width: 200, height: 150
            thumb1 = Tool.preview(upDir, file1saved, 200, 150); 
          }
          
        }    
        
        pictureVO.setFile1(file1);
        pictureVO.setFile1saved(file1saved);
        pictureVO.setThumb1(thumb1);
        pictureVO.setSize1(size1);
        
        int cnt = pp.create(pictureVO); 
        
        // ------------------------------------------------------------------------------
        // PK의 return
        // ------------------------------------------------------------------------------
        // System.out.println("--> contentsno: " + pictureVO.getContentsno());
        mav.addObject("picture_id", pictureVO.getPicture_id()); // redirect parameter 적용
        // ------------------------------------------------------------------------------
        
        if (cnt == 1) {
            mav.addObject("code", "create_success");
        } else {
            mav.addObject("code", "create_fail");
        }
        mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
        
        // System.out.println("--> cateno: " + pictureVO.getCateno());
        // redirect시에 hidden tag로 보낸것들이 전달이 안됨으로 request에 다시 저장
        mav.addObject("cateno", pictureVO.getCate_id()); // redirect parameter 적용
        mav.addObject("url", "/result_msg"); // redirect parameter 적용

        mav.setViewName("redirect:/picture/msg.do"); 
        
        return mav; 
      }
    
    // 특정 카테고리 소속 전체 사진 리스트
    @GetMapping("/list_by_cate_id")
    public ModelAndView showPicturesByCateId(int cate_id) {
        
        ModelAndView mav = new ModelAndView();
        
        //db조회
        CategoryVO categoryVO = cp.readOne(cate_id);
        List<PictureVO> list = pp.readAllPictures(cate_id);
        
        mav.addObject("categoryVO", categoryVO);
        mav.addObject("list", list);
        
        mav.setViewName("/picture/list_by_cate_id");
        return mav;
    }
    
    // 게시글 하나를 읽을 때
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
    
    // sql에 사용할 hashmap 초기화 + view에 전달할 objs를 초기화 해야함.
    @GetMapping("/list_by_name_search")
    public ModelAndView listBySearchName(@RequestParam(value = "cate_id", defaultValue = "1") int cate_id,
                                                            @RequestParam(value = "word", defaultValue = "") String word) {
        
        // 1. ds
        ModelAndView mav = new ModelAndView();
        HashMap<String, Object> hashMap = new HashMap<>();
        List<PictureVO> list = new ArrayList<>(); // --> null pointer exception 방지
        
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
     * 서버 통신 api 테스트 컨트롤러 
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
