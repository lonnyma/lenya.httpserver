package com.tcl.video.httpserver.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.tcl.video.httpserver.bean.ThumbnailBean;
import com.tcl.video.httpserver.bean.VideoBean;
import com.tcl.video.httpserver.dao.IUploadDao;

import cn.lenya.soft.core.rs.BaseRs;

@Transactional  
public class UploadMB {

	private IUploadDao uploadDao= null;
	
	private TransactionTemplate tranTemplate;
	
	private static final Logger Log = LoggerFactory.getLogger(UploadMB.class);
	/**
	 * 功能描述：持久保存视频文件
	 * @param vBean
	 * @return
	 */
	@Transactional(rollbackFor=RuntimeException.class) 
	public BaseRs saveAndUpdate(VideoBean vBean){
		BaseRs rs = new BaseRs();
		//Boolean rs = false;
		try{
			Object [] args= new Object[]{
					vBean.getVideoId()==null?"":vBean.getVideoId().trim(),
					vBean.getVideoTitle()==null?"":vBean.getVideoTitle().trim(),
					vBean.getVideoFileName()==null?"":vBean.getVideoFileName().trim(),
					vBean.getVideoFileType()==null?"":vBean.getVideoFileType().trim(),
					vBean.getResourceUrl()==null?"":vBean.getResourceUrl().trim(),
					vBean.getDomain()==null?"":vBean.getDomain().trim(),
					vBean.getTranscodingUrl()==null?"":vBean.getTranscodingUrl().trim(),
					vBean.getLinkUrl()==null?"":vBean.getLinkUrl().trim(),
					vBean.getPlayerType()==null?"":vBean.getPlayerType().trim(),
					vBean.getVideoSize()==null?"0":vBean.getVideoSize(),
					vBean.getUserId()==null?0001:vBean.getUserId(),
					vBean.getUploadTime()==null?"":vBean.getUploadTime().trim(),
					vBean.getUploadIP()==null?"":vBean.getUploadIP().trim(),
					vBean.getKeyWord()==null?"":	vBean.getKeyWord().trim(),
					vBean.getIntroduce()==null?"":vBean.getIntroduce().trim(),
					vBean.getCopyRight()==null?"":vBean.getCopyRight().trim(),
					vBean.getPerimit()==null?"":vBean.getPerimit().trim(),
					vBean.getNotes()==null?"":vBean.getNotes().trim(),
					vBean.getPlayPage()==null?"":vBean.getPlayPage().trim(),//2016-01-06
					vBean.getDuration()==null?"":vBean.getDuration().trim(),
					vBean.getStatus()==null?"":vBean.getStatus().trim(),
					vBean.getReleaseTime()==null?"":vBean.getReleaseTime(),
					vBean.getPublishedTime()==null?"":vBean.getPublishedTime(),
					vBean.getCategoryId()==null?"":vBean.getCategoryId()				
			};
			
			String sql = "insert into t_videos("
					+ "`videoId`, "
					+ " `videoTitle` ,"
					+ " `videoFileName` ,"
					+ " `videoFileType`,"
					+ " `resourceUrl` , "
					+"`domain`,"
					+ "`transcodingUrl`,"
					+ " `linkUrl` , "
					+ "`playerType`, "
					+ "`videoSize` ,"
					+ " `userId` ,  `uploadTime`,  `uploadIP` ,"
					+ "  `keyword` ,"
					+ "  `intoduce` ,"
					+ "  `copyRight` ,"
					+ "  `perimit` , "
					+ "	 `notes`,"
					+ "  `playPage` ,"
					+ " `duration` , "
					+ " `status` , "
					+ " `releasedTime` , "
					+ " `publishedTime` ,"
					+ " `categoryId` )"
					+ "values(?,?,?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?"
					+ ")";
			
			
			boolean rs0 = uploadDao.saveOrUpdate(sql, args);	
			
			sql = null;
			args = null;
			
			if(rs0){
				sql ="insert into t_thumbnails("
						+ "`videoId`,"
						+ "`height`,"
						+ "`width`,"
						+ "`imgResourceUrl`,"
						+ "`imgDomain`,"
						+ "`imgLinkUrl`,"
						+ "`size`,"
						+ "`notes` ,"
						+ "`resolutionType`)values(?,?,?,?,?,?,?,?,?)";
				
			   ThumbnailBean tb = vBean.getThumbnailBean();	
			   args= new Object[]{
						vBean.getVideoId()==null?"":vBean.getVideoId().trim(),
						tb.getHeight()==null?0:tb.getHeight(),
						tb.getWidth()==null?0:tb.getWidth(),
						tb.getImgResourceUrl().trim()==null?"":tb.getImgResourceUrl().trim(),
						tb.getImgDomain()==null?"":tb.getImgDomain().trim(),
						tb.getImgLinkUrl().trim()==null?"":tb.getImgLinkUrl().trim(),
						tb.getSize().trim()==null?"":tb.getSize().trim(),
						tb.getNotes().trim()==null?"":tb.getNotes().trim(),
						tb.getResloutionType()==null?"MIDDLE":tb.getResloutionType()
				};
			   try{
				rs0 =uploadDao.saveOrUpdate(sql, args);
				rs.setResult(rs0);
				
				if(!rs0){
					rs0 =uploadDao.saveOrUpdate("delete from t_videos where videoId =?",new Object[]{
							vBean.getVideoId()==null?"0":vBean.getVideoId().trim()});
				}
			   }catch(Exception e){
				   
				   rs.setRsMsg("截取缩略图入库失败！");
					rs.setResult(false);
					rs.setRsCode("100001");
				  
					Log.info("saveAndUpdate()-->  "+e.getMessage());
					e.printStackTrace();
					 return rs;
			   } 
								
			
			}else{
				rs.setRsMsg("视频入库失败！");
				rs.setRsCode("100000");
			}			
			
			rs.setRsMsg("视频入库成功！");
			rs.setResult(true);
			rs.setRsCode("0");
			rs.setResult(rs0);
		} catch (Exception e) {
			Log.info("saveAndUpdate()-->  "+e.getMessage());
			e.printStackTrace();
			rs.setRsMsg("视频入库失败！");
			rs.setResult(false);
			rs.setRsCode("100000");
			return rs;
		}		
		return rs;                              
	}

	/**
	 * 功能描述：持久保存上传视频记录 使用spring 事务模板进行事务回滚
	 * @param vBean
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public BaseRs saveAndUpdate2(final VideoBean vBean){
		//Boolean rs = false;
		BaseRs rs =  this.tranTemplate.execute( new TransactionCallback() {

			public Object doInTransaction(TransactionStatus arg0) {
				BaseRs rs = new BaseRs();
		try{
			Object [] args= new Object[]{
					vBean.getVideoId()==null?"":vBean.getVideoId().trim(),
					vBean.getVideoTitle()==null?"":vBean.getVideoTitle().trim(),
					vBean.getVideoFileName()==null?"":vBean.getVideoFileName().trim(),
					vBean.getVideoFileType()==null?"":vBean.getVideoFileType().trim(),
					vBean.getResourceUrl()==null?"":vBean.getResourceUrl().trim(),
					vBean.getDomain()==null?"":vBean.getDomain().trim(),
					vBean.getTranscodingUrl()==null?"":vBean.getTranscodingUrl().trim(),
					vBean.getLinkUrl()==null?"":vBean.getLinkUrl().trim(),
					vBean.getPlayerType()==null?"":vBean.getPlayerType().trim(),
					vBean.getVideoSize()==null?"0":vBean.getVideoSize(),
					vBean.getUserId()==null?0001:vBean.getUserId(),
					vBean.getUploadTime()==null?"":vBean.getUploadTime().trim(),
					vBean.getUploadIP()==null?"":vBean.getUploadIP().trim(),
					vBean.getKeyWord()==null?"":	vBean.getKeyWord().trim(),
					vBean.getIntroduce()==null?"":vBean.getIntroduce().trim(),
					vBean.getCopyRight()==null?"":vBean.getCopyRight().trim(),
					vBean.getPerimit()==null?"":vBean.getPerimit().trim(),
					vBean.getNotes()==null?"":vBean.getNotes().trim(),
					vBean.getPlayPage()==null?"":vBean.getPlayPage().trim(),//2016-01-06
					vBean.getDuration()==null?"":vBean.getDuration().trim(),
					vBean.getStatus()==null?"":vBean.getStatus().trim(),
					vBean.getReleaseTime()==null?"":vBean.getReleaseTime(),
					vBean.getPublishedTime()==null?"":vBean.getPublishedTime(),
					vBean.getCategoryId()==null?"":vBean.getCategoryId()
							
					
			};
			
			String sql = "insert into t_videos("
					+ "`videoId`, "
					+ " `videoTitle` ,"
					+ " `videoFileName` ,"
					+ " `videoFileType`,"
					+ " `resourceUrl` , "
					+"`domain`,"
					+ "`transcodingUrl`,"
					+ " `linkUrl` , "
					+ "`playerType`, "
					+ "`videoSize` ,"
					+ " `userId` ,  `uploadTime`,  `uploadIP` ,"
					+ "  `keyword` ,"
					+ "  `intoduce` ,"
					+ "  `copyRight` ,"
					+ "  `perimit` , "
					+ "	 `notes`,"
					+ "  `playPage` ,"
					+ " `duration` , "
					+ " `status` , "
					+ " `releasedTime` , "
					+ " `publishedTime` ,"
					+ " `categoryId` )"
					+ "values(?,?,?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?"
					+ ")";
			
			
			boolean rs0 = uploadDao.saveOrUpdate(sql, args);	
			
			sql = null;
			args = null;
			
			if(rs0){
				sql ="insert into t_thumbnails("
						+ "`videoId`,"
						+ "`height`,"
						+ "`width`,"
						+ "`imgResourceUrl`,"
						+ "`imgDomain`,"
						+ "`imgLinkUrl`,"
						+ "`size`,"
						+ "`notes` ,"
						+ "`resolutionType`)values(?,?,?,?,?,?,?,?,?)";
				
			   ThumbnailBean tb = vBean.getThumbnailBean();	
			   args= new Object[]{
						vBean.getVideoId()==null?"":vBean.getVideoId().trim(),
						tb.getHeight()==null?0:tb.getHeight(),
						tb.getWidth()==null?0:tb.getWidth(),
						tb.getImgResourceUrl().trim()==null?"":tb.getImgResourceUrl().trim(),
						tb.getImgDomain()==null?"":tb.getImgDomain().trim(),
						tb.getImgLinkUrl().trim()==null?"":tb.getImgLinkUrl().trim(),
						tb.getSize().trim()==null?"":tb.getSize().trim(),
						tb.getNotes().trim()==null?"":tb.getNotes().trim(),
						tb.getResloutionType()==null?"MIDDLE":tb.getResloutionType()
				};			
			   try{
					rs0 =uploadDao.saveOrUpdate(sql, args);
					rs.setResult(rs0);
					
					if(!rs0){
						rs0 =uploadDao.saveOrUpdate("delete from t_videos where videoId =?",new Object[]{
								vBean.getVideoId()==null?"0":vBean.getVideoId().trim()});
					}
				   }catch(Exception e){					   
					   rs.setRsMsg("截取缩略图入库失败！");
						rs.setResult(false);
						rs.setRsCode("01");					  
						Log.info("saveAndUpdate()-->  "+e.getMessage());
						e.printStackTrace();
						 return rs;
				   } 									
				
				}else{
					rs.setRsMsg("视频入库失败！");
					rs.setRsCode("00");
					rs.setResult(false);
				}			
				rs.setResult(rs0);
			
			
			} catch (Exception e) {
				Log.info("saveAndUpdate()-->  "+e.getMessage());
				e.printStackTrace();
				rs.setRsMsg("视频入库失败！");
				rs.setResult(false);
				rs.setRsCode("00");
				return rs;
			}		
			
			
			rs.setRsMsg("视频入库成功！");
			rs.setResult(true);
			rs.setRsCode("0");
			return rs;
			}
			
			});
			
		
		return rs;
	}
	
	
	
	public void setTranTemplate(TransactionTemplate tranTemplate) {
		this.tranTemplate = tranTemplate;
	}

	public void setUploadDao(IUploadDao uploadDao) {
		this.uploadDao = uploadDao;
	}
	
}
