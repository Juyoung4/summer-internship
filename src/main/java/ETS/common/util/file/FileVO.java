package ETS.common.util.file;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author yuno
 * @version 2012. 10. 17.
 */
public class FileVO {
	private MultipartFile uploadFile;
	private List<MultipartFile> upload;

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public List<MultipartFile> getUpload() {
		return upload;
	}

	public void setUpload(List<MultipartFile> upload) {
		this.upload = upload;
	}
}
