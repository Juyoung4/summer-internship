package ETS.common;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ETS.common.service.CommonService;

/***************************************************
 * @FileName : CommonController.java
 * @Description: Commonly used functions
 * @Author : joon
 * @Version : 2016. 01. 14.
 * @Copyright : ⓒ ADUP. All Right Reserved
 ***************************************************/
@Controller
@RequestMapping("common")
public class CommonController {

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	private CommonService commonService;

	/**************************************************
	 * @MethodName : fileDownoad
	 * @Description:
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 *             byte[]
	 * @Author : Jong-Hoon. Jung
	 * @Version : 2018. 11. 29.
	 **************************************************/
	@RequestMapping(value = "/imageload", method = RequestMethod.GET)
	@ResponseBody
	public byte[] fileDownoad(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException {

		String fullImageFileNm = request.getParameter("fullImageFileNm");

		logger.debug("***** fullImageFileNm={}", fullImageFileNm);

		String osname = System.getProperty("os.name");
		if (osname.startsWith("Window")) {
			fullImageFileNm = Global.resource.getString("FILE_ROOT_WINDOW") + "/" + fullImageFileNm;
		} else {
			fullImageFileNm = Global.resource.getString("FILE_ROOT_LINUX") + "/" + fullImageFileNm;
		}
		logger.debug("***** fullImageFileNm={}", fullImageFileNm);
		File file = new File(fullImageFileNm);

		if (!(file.exists() && file.isFile())) {
			logger.info("file is not exists");
			return null;
		}

		byte[] bytes = FileCopyUtils.copyToByteArray(file);

		String fileNm = fullImageFileNm.substring(fullImageFileNm.lastIndexOf("/") + 1);

		response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileNm.getBytes("UTF-8"), "8859_1")); // 아랍어:
		response.setContentLength(bytes.length);

		return bytes;
	}

}