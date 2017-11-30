package com.finger.shoot.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.finger.shoot.configuration.OssBeanConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 
 * 阿里云OSS工具 <br>
 *
 */
@Slf4j
public class OSSUtils {

	private static OSSClient client;
	public static OSSUtils INSTANCE = null;
	private OSSUtils(){}

	/**
	 * 获取实例
	 * @return
	 */
	public static OSSUtils getInstance(OssBeanConfiguration ossConfiguration){
		log.info("OSSUtils OssBeanConfiguration getEndpoint :"+ ossConfiguration.getEndpoint());
		log.info("OSSUtils OssBeanConfiguration getKey :"+ ossConfiguration.getKey());
		log.info("OSSUtils OssBeanConfiguration getSecret :"+ ossConfiguration.getSecret());
		if(INSTANCE == null){
			synchronized (OSSUtils.class) {
				if(INSTANCE == null) {
					INSTANCE = new OSSUtils();
				}
			}
		}
		client = new OSSClient(ossConfiguration.getEndpoint(), ossConfiguration.getKey(), ossConfiguration.getSecret());

		return INSTANCE;
	}

	/**
	 * 
	 * 根据指定key删除在bucket中的对象<br>
	 *
	 * @param bucketName
	 * @param key
	 */
	public void deleteObject(String bucketName, String key) {
		try {
			client.deleteObject(bucketName, key);
		} catch (Exception ex) {
			log.error("出现错误 bucketName:" + ex.toString());
			ex.printStackTrace();
		} finally {
			client.shutdown();
		}
	}

	/**
	 * 
	 * 根据前缀批量删除在bucket中的对象<br>
	 *
	 * @param bucketName
	 * @param prefix
	 */
	public void deleteObjectWithPrefix(String bucketName, String prefix) {
		try {
			ObjectListing objectListing = client.listObjects(new ListObjectsRequest(bucketName).withPrefix(prefix));
			if (!objectListing.getObjectSummaries().isEmpty()) {
				List<String> keys = new ArrayList<String>();
				for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
					keys.add(objectSummary.getKey());
				}
				// 批量删除
				if (!keys.isEmpty()) {
					client.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
				}
			}
		} catch (Exception ex) {
			log.error("出现错误 bucketName:" + ex.toString());
			ex.printStackTrace();
		} finally {
			client.shutdown();
		}
	}

	/**
	 * 获取指定文件大小
	 * @param bucketName
	 * @param key
	 * @return
	 */
	public Long getFileSize(String bucketName, String key){
		ObjectListing objectListing = client.listObjects(bucketName, key);
		if(objectListing==null || objectListing.getObjectSummaries()==null || objectListing.getObjectSummaries().size()<=0){
			return 0L;
		}

		List<OSSObjectSummary> objectSummaries = objectListing.getObjectSummaries();
		return objectSummaries.get(0).getSize();
	}

	/**
	 * 
	 * 根据sourceKey查找对象拷贝至destinationKey<br>
	 *
	 * @param bucketName
	 *            说明:oss支持跨bucket拷贝,咱这里只需要bucket内部拷贝,因此就只用了一个bucketName
	 * @param sourceKey
	 * @param destinationKey
	 */
	public void copyObject(String bucketName, String sourceKey, String destinationKey) {
		try {
			client.copyObject(bucketName, sourceKey, bucketName, destinationKey);
		} catch (Exception ex) {
			log.error("出现错误 bucketName:" + ex.toString());ex.printStackTrace();
		} finally {
			client.shutdown();
		}
	}

	/**
	 * 
	 * 根据前缀sourcePrefix查找对象集合进行批量拷贝至destinationPrefix前缀下<br>
	 *
	 * @param bucketName
	 * @param sourcePrefix
	 * @param destinationPrefix
	 */
	public void copyObjectWithPrefix(String bucketName, String sourcePrefix, String destinationPrefix) {
		try {
			ObjectListing objectListing = client.listObjects(new ListObjectsRequest(bucketName).withPrefix(sourcePrefix));
			if (!objectListing.getObjectSummaries().isEmpty()) {
				String sourceKey = null;
				for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
					sourceKey = objectSummary.getKey();
					client.copyObject(bucketName, sourceKey, bucketName, sourceKey.replace(sourcePrefix, destinationPrefix));
				}
			}
		} catch (Exception ex) {
			log.error("出现错误 bucketName:" + ex.toString());ex.printStackTrace();
		} finally {
			client.shutdown();
		}
	}

	/**
	 * 
	 * 根据前缀sourcePrefix查找对象集合进行批量更名为destinationPrefix前缀<br>
	 *
	 * @param bucketName
	 * @param sourcePrefix
	 * @param destinationPrefix
	 */
	public void renameObjectWithPrefix(String bucketName, String sourcePrefix, String destinationPrefix) {
		try {
			ObjectListing objectListing = client.listObjects(new ListObjectsRequest(bucketName).withPrefix(sourcePrefix));
			if (!objectListing.getObjectSummaries().isEmpty()) {
				List<String> keys = new ArrayList<String>();
				String sourceKey = null;
				for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
					sourceKey = objectSummary.getKey();
					client.copyObject(bucketName, sourceKey, bucketName, sourceKey.replace(sourcePrefix, destinationPrefix));
					keys.add(sourceKey);
				}
				// 批量删除
				if (!keys.isEmpty()) {
					client.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
				}
			}
		} catch (Exception ex) {
			log.error("出现错误 bucketName:" + ex.toString());ex.printStackTrace();
		} finally {
			client.shutdown();
		}
	}

	/**
	 * 
	 * 根据前缀prefix查找对象集合,将content中未引用的对象删除<br>
	 *
	 * @param bucketName
	 * @param prefix
	 * @param content
	 */
	public void cleanObjectWithPrefixAndContent(String bucketName, String prefix, String content) {
		try {
			ObjectListing objectListing = client.listObjects(new ListObjectsRequest(bucketName).withPrefix(prefix));
			if (!objectListing.getObjectSummaries().isEmpty()) {
				List<String> keys = new ArrayList<String>();
				String sourceKey = null;
				for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
					sourceKey = objectSummary.getKey();
					if (!content.contains(sourceKey)) {
						keys.add(sourceKey);
					}
				}
				// 批量删除
				if (!keys.isEmpty()) {
					client.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
				}
			}
		} catch (Exception ex) {
			log.error("出现错误 bucketName:" + ex.toString());ex.printStackTrace();
		} finally {
			client.shutdown();
		}
	}

	/**
	 * 上传文件到OSS
	 * @param bucketName
	 * @param key
	 * @param source
	 * @return
     * @throws Exception
     */
	public String uploadFile(String bucketName,String key,InputStream source)throws Exception{
		try{
			client.putObject(bucketName, key, source);
		}catch (Exception e){
			key = null;
			log.error("出现错误 bucketName:" + e.toString());
			e.printStackTrace();
		}finally {
			client.shutdown();
		}
		return key;
	}

    public ObjectListing getPrefixList(String bucetName,String sourcePrefix,boolean shutdown){
		log.info("OSSClient getPrefixList");
		ObjectListing listing = null;
		try{
			ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucetName);
			listObjectsRequest.setPrefix(sourcePrefix);
			listObjectsRequest.setMaxKeys(999);
			//listing = client.listObjects(bucetName,sourcePrefix);
			listing = client.listObjects(listObjectsRequest);
		}catch (Exception e){
			log.error("OSSClient getPrefixList err :"+e.getMessage());
		}finally {
			if(shutdown) {
				client.shutdown();
			}
		}
		return listing;
	}


	/**
	 * 读取oss指定路径下的文件
	 * @param bucketName
	 * @param sourcePrefix
	 * @param key
	 * @param shutdown 是否关闭
	 * @return
     * @throws Exception
     */
	public OSSObject  getPrefuxFile(String bucketName,String sourcePrefix,String key,boolean shutdown)throws Exception{
		InputStream in =null;
		ZipOutputStream out = null;
		String prefix = null;
		OSSObject ossObject = null;
		try{
				if(StringUtils.hasText(sourcePrefix)){
					prefix = sourcePrefix+"/"+key;
				}else{
					prefix = key;
				}
				GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, prefix);
				ossObject =  client.getObject(getObjectRequest);
				ObjectMetadata objectMeta = ossObject.getObjectMetadata();
				log.info("content-Disposition:"+objectMeta.getContentDisposition());
				log.info("Content-Length :"+objectMeta.getContentLength());
				log.info("Content-Type :"+objectMeta.getContentType());
		}catch (Exception e){
			log.error("出现错误 bucketName:" + e.toString());
		}finally {
			try {
				if(out!=null){
					out.flush();
					out.close();
				}
			}catch (Exception e){}
			if(null != in){
				try {
					in.close();
				}catch (Exception e){}
			}
			if(shutdown) {
				client.shutdown();
			}
		}
		return ossObject;
	}

	/**
	 * 关闭oss
	 */
	public void shutdown(){
		client.shutdown();
	}

	/**
	 * 下载文件成zip
 	 * @param ossObject
	 * @param out
	 * @param fileName
     */
	public void downloadObjectWrite(OSSObject ossObject,ZipOutputStream out,String fileName){
		if (null != ossObject) {
			InputStream in = ossObject.getObjectContent();
			try {
				out.putNextEntry(new ZipEntry(fileName)); // 设置ZipEntry对象
				byte[] buf = new byte[1024];
				int L = 0;
				while ((L = in.read(buf)) != -1) {
					out.write(buf, 0, L);
				}
			}catch (IOException e) {
				//log.error("downloadPhoto downloadObjectWrite IOException: "+e.getMessage());
			}finally
			{
				if (null != in) {
					try {
						in.close();
					} catch (Exception e) {}
				}
			}
		}
	}

	/**
	 * 图片处理方法
 	 * @param bucketName
	 * @param key
	 * @param style
	 * @param destinationPrefix
     * @param shutdown
     */
	public void processImg(String bucketName,String key,String style ,String destinationPrefix,boolean shutdown){
		try {
			GetObjectRequest request = new GetObjectRequest(bucketName, key);
			request.setProcess(style);
			OSSObject ossObject =  client.getObject(request);
			ObjectMetadata objectMeta = ossObject.getObjectMetadata();
			log.info("content-Disposition:"+objectMeta.getContentDisposition());
			log.info("Content-Length :"+objectMeta.getContentLength());
			log.info("Content-Type :"+objectMeta.getContentType());
			String[] keys = ossObject.getKey().split("/");
			String destinationKey  = keys[keys.length-1];
			client.putObject(bucketName, destinationPrefix+"/"+destinationKey, ossObject.getObjectContent());
		}finally {
			if(shutdown){
				client.shutdown();
			}
		}
	}
	
	/**
	 * 创建文件夹
	 * @param bucketName 
	 * @param key 文件夹名称 OSS是没有文件夹这个概念的，所有元素都是以Object来存储。创建模拟文件夹本质上来说是创建了一个size为0的Object。对于这个Object可以上传下载，只是控制台会对以”/“结尾的Object以文件夹的方式展示。多级目录创建最后一级即可，比如dir1/dir2/dir3/，创建dir1/dir2/dir3/即可，dir1/、dir1/dir2/不需要创建；
	 * @return
	 */
	public String createFolder(String bucketName,String key){
		
		if(!client.doesObjectExist(bucketName,key)){
			client.putObject(bucketName, key, new ByteArrayInputStream(new byte[0]));
		}
		
		return key;
	}
	
	public String getUrl(String bucketName,String key) {
		// 设置URL过期时间为100年 3600l* 1000*24*365*100
		Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 100);
		// 生成URL
		URL url = client.generatePresignedUrl(bucketName, key, expiration);
		if (url != null) {
			StringBuffer result = new StringBuffer();
	        result.append(url.getProtocol());
	        result.append(":");
	        if (url.getAuthority() != null && url.getAuthority().length() > 0) {
	            result.append("//");
	            result.append(url.getAuthority());
	        }
	        if (url.getPath() != null) {
	            result.append(url.getPath());
	        }
			return result.toString();
		}
		return null;
	}

	/**
	 * 下载文件
	 * @param bucketName
	 * @param key
	 * @param file
	 */
	public void getObject(String bucketName, String key, File file){
		client.getObject(new GetObjectRequest(bucketName, key), file);
	}
}


