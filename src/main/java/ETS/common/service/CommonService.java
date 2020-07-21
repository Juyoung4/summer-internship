package ETS.common.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import ETS.common.Global;
import ETS.common.mapper.CommonMapper;
import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;
import ETS.common.util.DateFmt;
import ETS.common.util.FileUtil;

/**************************************************
 * @FileName : CommonService.java
 * @Description: 공통 비지니스 로직
 * @Author : joon
 * @Version : 2016. 4. 16.
 * @Copyright : ⓒADUP. All Right Reserved
 **************************************************/
@Service
public class CommonService {

	private static final Logger logger = LoggerFactory.getLogger(CommonService.class);

	@Autowired
	private CommonMapper commonMapper;
	
	public CamelMap selectUserLoginInfo(DataMap paramMap) throws Exception{
		CamelMap userInfo = new CamelMap();
		
		userInfo = commonMapper.selectUserLoginInfo(paramMap);
		if(userInfo != null){
			return userInfo;
		}else{
			return commonMapper.selectUserLoginInfo(paramMap);
		}
		
	}

	/**************************************************
	 * @MethodName : saveFile
	 * @Description: 파일 저장 후 파일 정보 리턴
	 * @param multipartRequest
	 * @param paramMap
	 *            - filePath : 저장할 파일 경로
	 * @return List<DataMap> saveFileName : 저장된 파일 명 realFileName : 실제 파일 명
	 *         saveFilePath : 저장된 파일 경로 fileSize : 파일 사이즈 uploadName : 업로드 오브젝트의
	 *         네임값
	 * @throws Exception
	 * @Author : Won-Joon. Lee
	 * @Version : 2017. 6. 1.
	 **************************************************/
	@SuppressWarnings("unchecked")
	public List<DataMap> saveFile(HttpServletRequest request, DataMap paramMap)
			throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartRequest.getFileNames();
		MultipartFile multipartFile = null;

		List<DataMap> rstFileList = new ArrayList<DataMap>();

		String root = "";
		String osname = System.getProperty("os.name");
		if(osname.startsWith("Window")){
			root = Global.resource.getString("FILE_ROOT_WINDOW");
		}else{
			root = Global.resource.getString("FILE_ROOT_LINUX");
		}
		
		String filePath = null;
		if (StringUtils.isNotEmpty(paramMap.getString("filePath")))
			filePath = paramMap.getString("filePath");
		else {
			filePath = "ETS/" + DateFmt.getSimpleDate("yyyy") + "/" + DateFmt.getSimpleDate("MMdd");
		}

		root = root + "/" + filePath;
		FileUtil.mkdirs(root);

		while (iterator.hasNext()) {
			multipartFile = multipartRequest.getFile(iterator.next());
			if (multipartFile.isEmpty() == false) {
				logger.debug("------------- file start -------------");
				logger.debug("name : " + multipartFile.getName());
				logger.debug("filename : " + multipartFile.getOriginalFilename());
				logger.debug("size : " + multipartFile.getSize());
				logger.debug("-------------- file end --------------\n");
				String filename = multipartFile.getOriginalFilename();

				String saveFile = DateFmt.getSimpleDate() + "_" + RandomStringUtils.randomAlphabetic(8);
				if (filename.lastIndexOf(".") > -1)
					saveFile += filename.substring(filename.lastIndexOf("."));

				writeFile(multipartFile, root, saveFile);

				DataMap fileMap = new DataMap();
				fileMap.put("saveFileName", saveFile);
				fileMap.put("realFileName", filename);
				fileMap.put("saveFilePath", filePath);
				fileMap.put("fileSize", multipartFile.getSize());
				fileMap.put("uploadName", multipartFile.getName());

				rstFileList.add(fileMap);
			}
		}

		return rstFileList;
	}

	/**
	 * @param multipartFile
	 * @param pathNm
	 * @param saveFile
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	private void writeFile(MultipartFile multipartFile, String pathNm, String saveFile) throws IllegalStateException, IOException {
		FileUtil.mkdirs(pathNm);
		File file = new File(pathNm + "/" + saveFile);
		multipartFile.transferTo(file);
	}

}