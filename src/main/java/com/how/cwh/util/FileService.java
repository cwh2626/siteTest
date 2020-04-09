package com.how.cwh.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;
 
/**
 * 파일 관련 유틸
 * 업로드한 파일의 저장 & 서버에 저장된 파일 삭제 등의 기능 제공
 */
public class FileService {

	/**
	 * 업로드 된 파일을 지정된 경로에 저장하고, 저장된 파일명을 리턴
	 * @param mfile 업로드된 파일
	 * @param path 저장할 경로
	 * @return 저장된 파일명
	 */
	public static String saveFile(MultipartFile mfile, String uploadPath) {
		//업로드된 파일이 없거나 크기가 0이면 저장하지 않고 null을 리턴
		if (mfile == null || mfile.isEmpty() || mfile.getSize() == 0) {
			return null;
		}
		
		//저장 폴더가 없으면 생성 "/boardfilr" //File 클래스는 파일의 유무 등 파일에대한 여리 기능을 가지고 있다 
		File path = new File(uploadPath); // /boardfilr라는 이름으로 파일 객체럴 만든다 그래서 있는지 없는지 확인한다
		if (!path.isDirectory()) {  //이게 확인하는방법이다 path.isDirectory()는 존재유무를 묻는다 그 파일이 있으면 ture 없으면 false이다
			path.mkdirs(); //그 폴더가없으면 폴더를 그 그이름으로 생성한다
		}
		
		//원본 파일명
		String originalFilename = mfile.getOriginalFilename();
		
		//저장할 파일명을 오늘 날짜의 년월일로 생성
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String savedFilename = sdf.format(new Date());
		
		//원본 파일의 확장자
		String ext;
		int lastIndex = originalFilename.lastIndexOf('.');  // lastIndexOf('.') 는 내가 지정한 문자열이 가장 맨 마지막의 것을 지정으로 순서를 답해주는것이다
															//앞에서부터 차례대로 순서를 센다 시작번호는 0부터이다	, IndexOf('.')와 차이점은 IndexOf('.')는 제일먼저 나오는것을 지정한다	
		//확장자가 없는 경우										
		if (lastIndex == -1) {
			ext = "";
		}
		//확장자가 있는 경우
		else {
			ext = "." + originalFilename.substring(lastIndex + 1); 
			//substring()은 두가지 경우가 있다 substring(start,end)와 substring(start)인경우가 있다 start 부터 and까지 문자열을 잘라서 저장한다 
			//그런데 start만 있는경우에는 start부터 쭉 저장한다라는 의미이다 
			//그럼 substring(lastIndex + 1)이것은 lastIndex '.'의 위치이다 그런데 우리가 필요한것은 '.'의 다음내용이니 lastIndex + 1 로해서 다음내용을 잘라서 저장하는것이다
		}

		//저장할 전체 경로를 포함한 File 객체
		File serverFile = null;
		
		//같은 이름의 파일이 있는 경우의 처리
		while (true) {
			serverFile = new File(uploadPath + "/" + savedFilename + ext);
			//같은 이름의 파일이 없으면 나감.
			if ( !serverFile.isFile()) break;	//isFile()파일의 존재 유무
			
			//같은 이름의 파일이 있으면 이름 뒤에 long 타입의 시간정보를 덧붙임.
			savedFilename = savedFilename + new Date().getTime();	
		}		
		
		//파일 저장
		try {
			mfile.transferTo(serverFile);//최종적으로 파일을 저장하는것
		} catch (Exception e) {
			savedFilename = null;
			e.printStackTrace();
		}
		
		return savedFilename + ext;
	}
	
	/**
	 * 서버에 저장된 파일의 전체 경로를 전달받아, 해당 파일을 삭제
	 * @param fullpath 삭제할 파일의 경로
	 * @return 삭제 여부
	 */
	public static boolean deleteFile(String fullpath) {
		//파일 삭제 여부를 리턴할 변수
		boolean result = false;
		
		//전달된 전체 경로로 File객체 생성
		File delFile = new File(fullpath);
		
		//해당 파일이 존재하면 삭제
		if (delFile.isFile()) {
			delFile.delete();
			result = true;
		}
		
		return result;
	}
}
