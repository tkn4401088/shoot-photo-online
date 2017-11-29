package com.finger.shoot.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.jim2mov.core.DefaultMovieInfoProvider;
import org.jim2mov.core.ImageProvider;
import org.jim2mov.core.Jim2Mov;
import org.jim2mov.core.MovieInfoProvider;
import org.jim2mov.utils.MovieUtils;

import com.finger.shoot.common.FileObject;
import com.finger.shoot.configuration.OssBeanConfiguration;

public class VideoUtils {

	public static FileObject imageToVideo(List<String> photoList, Long orderId,
			OssBeanConfiguration ossBeanConfiguration) {
		FileObject fileObject = null;
		if (photoList == null || photoList.size() == 0) {
			return null;
		}
		// 创建临时目录
		String tempPath = System.getProperty("user.dir") + "\\tempFiles";
		File tempFile = new File(tempPath);
		if (!tempFile.exists()) {
			tempFile.mkdir();
		}
		try {
			Properties prop = System.getProperties();
			prop.put("awt.toolkit", "com.eteks.awt.PJAToolkit");
			prop.put("java.awt.headless", "true");
			System.setProperties(prop);
			// download and to File obj
			final List<File> files = new ArrayList<File>();
			for (int i = 0; i < photoList.size(); i++) {
				String fileUrl = photoList.get(i);
				URL url = new URL(fileUrl);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				InputStream is = conn.getInputStream();
				// 文件扩展名
				String type = fileUrl.substring(fileUrl.lastIndexOf("."),
						fileUrl.length());
				String imgFileName = tempPath + "/"
						+ KeyUtils.getRandom32BeginTimePK() + type;
				FileOutputStream fs = new FileOutputStream(imgFileName);
				byte[] buffer = new byte[1204];
				int byteread = 0;
				int bytesum = 0;
				while ((byteread = is.read(buffer)) != -1) {
					bytesum += byteread;
					fs.write(buffer, 0, byteread);
				}
				fs.close();
				is.close();
				conn.disconnect();
				File imgFile = new File(imgFileName);
				files.add(imgFile);
			}
			String videoFileName = "tempFiles\\"
					+ KeyUtils.getRandom32BeginTimePK() + ".avi";
			DefaultMovieInfoProvider dmip = new DefaultMovieInfoProvider(
					videoFileName);
			dmip.setFPS(6);
			dmip.setNumberOfFrames(files.size() * 12);
			dmip.setMWidth(860);
			dmip.setMHeight(1440);
			// 开始制作
			new Jim2Mov(new ImageProvider() {
				public byte[] getImage(int frame) {
					System.out.println(frame);
					try {
						// 设置压缩比
						return MovieUtils.convertImageToJPEG(
								(files.get(frame / 12)), 0.5f);
					} catch (IOException e) {
						System.err.println(e);
					}
					return null;
				}
			}, dmip, null).saveMovie(MovieInfoProvider.TYPE_AVI_MJPEG);
			// 删除临时图片
			for (int i = 0; i < files.size(); i++) {
				File delFile = files.get(i);
				delFile.delete();
			}
			// 将视频上传到OSS
			File videoFile = new File(dmip.getMediaLocator().getRemainder());
			String key = "photo/online/video/" + orderId + "/"
					+ videoFile.getName();
			InputStream is = new FileInputStream(videoFile);
			String filename = OSSUtils.getInstance(ossBeanConfiguration)
					.uploadFile(ossBeanConfiguration.getPic(), key, is);
			if (null != filename) {
				fileObject = new FileObject();
				fileObject.setFileName(filename);
				fileObject.setUrl(ossBeanConfiguration.getUrl() + filename);
				fileObject.setFileSize(OSSUtils.getInstance(
						ossBeanConfiguration).getFileSize(
						ossBeanConfiguration.getPic(), key));
				// 删除本地视频临时文件
				videoFile.delete();
				return fileObject;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileObject;
	}
}
