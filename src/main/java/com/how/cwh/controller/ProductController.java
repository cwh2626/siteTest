package com.how.cwh.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.how.cwh.util.FileService;





@RequestMapping("Product")
@Controller
public class ProductController {
	
	//주의할점 윈도우의 경우 절대경로를 지정시에 C,D드라이브가 있어서 그냥 복붙하면 되지만 
	//맥의 경우는 전체경로 중 /Users부터 시작으로 한다 주의!!!
	final String uploadPath = "/Users/jounghui/Desktop/springSiteTest/cwhcwh/src/main/webapp/resources/ckeditor/image"; //ckeditor의 이미지 저장위
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@RequestMapping(value ="insertSaleMain", method = RequestMethod.GET)
	public String insertSaleMain() { 
		return "Product/insertSale";
	}
	
	@ResponseBody
	@RequestMapping(value ="imageUpload.do", method = RequestMethod.POST)
	public void fileUpload(HttpServletRequest req, HttpServletResponse resp, MultipartFile upload) throws Exception {
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/html; charset=utf-8");
			logger.debug(upload.getOriginalFilename());	//파일이름
			logger.debug(upload.getContentType());		//업로드한 파일의 종류
			logger.debug(""+upload.getSize());			//파일 사이즈
			logger.debug(""+upload.isEmpty());			//Empty와 비어있다라는 뜻이다 즉 저것이 true뜨면 파일이 비었다는 뜻이다  반대로 false면 파일이 잘들어갔다는 소리겠죠
			logger.debug(req.getContextPath());			//현재위치
	        
			String savedfile = null;
			if(!upload.isEmpty()) {
				savedfile =FileService.saveFile(upload,uploadPath);
//				board.setOriginalfile(upload.getOriginalFilename());
//				board.setSavedfile(savedfile);
			}
			//PrintWriter은writer의 자식으로 출력만 가능하며당연히 문자기반이죠 
			//음 좀더 자세히 공불르 해보
			
			
//			----테스트-----
//	        //업로드한 파일 이름
//	        String fileName=upload.getOriginalFilename();
//	 
//	        //파일을 바이트 배열로 변환
//	        byte[] bytes=upload.getBytes();
//	      //이미지를 업로드할 디렉토리(배포 디렉토리로 설정)
//	        String uploadPath=
//	"/Users/jounghui/Documents/workspace-sts-3.9.10.RELEASE/SiteTest/src/main/webapp/WEB-INF/views/images/";
//	        OutputStream out=new FileOutputStream(
//	                new File(uploadPath+fileName));
//
//	        //서버로 업로드
//	        out.write(bytes);
//
//			
//			----테스트 끝---			
			
			
	        PrintWriter printWriter=resp.getWriter();
			
			//  이것을 config.js파일에 기입을 해줘야 비로서 오류가 안뜬
			//	config.filebrowserUploadMethod='form';
	
			String fileUrl = req.getContextPath()+"/resources/ckeditor/image/"+ savedfile;//savedfile
			//String fileUrl = req.getContextPath()+"/images/"+ fileName;//savedfile 안돼시이이바라아앙아아아 내일 다시해보자 아 진짜 주옥같
			
			String callback=req.getParameter("CKEditorFuncNum");
			//window.parent.CKEDITOR.tools.callFunction(1(성공) or 0(실패),이미지 주소 url반환,성공 메시지)
			
			printWriter.println(
				"<script>window.parent.CKEDITOR.tools.callFunction("
				+callback+",'"+fileUrl+"','이미지가 업로드되었습니다.')"
				+"</script>"
	        		);
	        printWriter.flush();		
	}

}
