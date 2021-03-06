package com.rbc.common.controller;

import com.rbc.common.config.AppConfig;
import com.rbc.common.domain.FileDO;
import com.rbc.common.service.FileService;
import com.rbc.common.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传
 */
@Controller
@RequestMapping("/common/sysFile")
public class FileController extends BaseController {

	@Autowired
	private FileService sysFileService;

	@Autowired
	private AppConfig appConfig;

	@GetMapping()
	@RequiresPermissions("common:sysFile:sysFile")
	String sysFile(Model model) {
		Map<String, Object> params = new HashMap<>(16);
		return "common/file/file";

	}

	@RequestMapping(value="/imgUpload/{partId}/{partType}/{uploadType}",method=RequestMethod.GET)
	///@RequiresPermissions("common:sysFile:sysFile")
	public String  imgUpload(@PathVariable Long partId,@PathVariable String partType,@PathVariable int uploadType,Model model) {
		model.addAttribute("partId",partId);
		model.addAttribute("partType",partType);
		model.addAttribute("uploadType",uploadType);
		return "common/file/imgUpload";
	}

	@ResponseBody
	@GetMapping("/list")
	//@RequiresPermissions("common:sysFile:sysFile")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<FileDO> sysFileList = sysFileService.list(query);
		int total = sysFileService.count(query);
		PageUtils pageUtils = new PageUtils(sysFileList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	// @RequiresPermissions("common:bComments")
	String add() {
		return "common/sysFile/add";
	}

	@GetMapping("/edit")
	// @RequiresPermissions("common:bComments")
	String edit(Long id, Model model) {
		FileDO sysFile = sysFileService.get(id);
		model.addAttribute("sysFile", sysFile);
		return "common/sysFile/edit";
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("common:info")
	public R info(@PathVariable("id") Long id) {
		FileDO sysFile = sysFileService.get(id);
		return R.ok().put("sysFile", sysFile);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("common:save")
	public R save(FileDO sysFile) {
		if (sysFileService.save(sysFile) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("common:update")
	public R update(@RequestBody FileDO sysFile) {
		sysFileService.update(sysFile);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	// @RequiresPermissions("common:remove")
	public R remove(Long id, HttpServletRequest request) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		String fileName = appConfig.getUploadPath() + sysFileService.get(id).getUrl().replace("/files/", "");
		if (sysFileService.remove(id) > 0) {
			boolean b = FileUtil.deleteFile(fileName);
			if (!b) {
				return R.error("数据库记录删除成功，文件删除失败");
			}
			return R.ok();
		} else {
			return R.error();
		}
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("common:remove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		sysFileService.batchRemove(ids);
		return R.ok();
	}

	@ResponseBody
	@PostMapping("/upload")
	R upload(@RequestParam("file") MultipartFile file, @RequestParam("params") String  params) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		Map<String, Object> jsMap=JSONUtils.jsonToMap(params);
		Long partId=new Long(jsMap.get("partId").toString());
		String partType=jsMap.get("partType").toString();

		String fileName = file.getOriginalFilename();
		String realFileName=fileName;
		fileName = FileUtil.renameToUUID(fileName);
		//FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
		FileDO sysFile=new FileDO();
		sysFile.setType(FileType.fileType(fileName));
		sysFile.setUrl("/files/" + fileName);
		sysFile.setCreateDate(new Date());
		sysFile.setFileRealName(realFileName);
		sysFile.setPartId(partId);
		sysFile.setPartType(partType);
		try {
			FileUtil.uploadFile(file.getBytes(), appConfig.getUploadPath(), fileName);
		} catch (Exception e) {
			return R.error();
		}

		if (sysFileService.save(sysFile) > 0) {
			return R.ok().put("fileName",sysFile.getUrl());
		}
		return R.error();
	}

	@RequestMapping(value="/download/{id}")
	public String  downLoad(@PathVariable Long id, HttpServletResponse response){
		String fileName = appConfig.getUploadPath() + sysFileService.get(id).getUrl().replace("/files/", "");
		File file = new File(fileName);
		if(file.exists()){ //判断文件父目录是否存在
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;fileName=" + sysFileService.get(id).getUrl().replace("/files/", ""));
			response.setContentLengthLong(file.length());

			byte[] buffer = new byte[1024];
			FileInputStream fis = null; //文件输入流
			BufferedInputStream bis = null;

			OutputStream os = null; //输出流
			try {
				os = response.getOutputStream();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);

				response.setContentLength(fis.available());

				int i = bis.read(buffer);
				while(i != -1){
					os.write(buffer);
					i = bis.read(buffer);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				bis.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
