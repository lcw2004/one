/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.modules.esign.web;

import com.lcw.one.common.config.Global;
import com.lcw.one.common.persistence.Page;
import com.lcw.one.common.utils.StringUtils;
import com.lcw.one.common.web.BaseController;
import com.lcw.one.modules.esign.entity.Certificate;
import com.lcw.one.modules.esign.entity.Seal;
import com.lcw.one.modules.esign.service.CertificateService;
import com.lcw.one.modules.esign.service.SealService;
import com.lcw.one.modules.esign.utils.PFXUtil;
import com.lcw.one.modules.sys.entity.Office;
import com.lcw.one.modules.sys.entity.User;
import com.lcw.one.modules.sys.service.OfficeService;
import com.lcw.one.modules.sys.service.SystemService;
import com.lcw.one.modules.sys.utils.DictUtils;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.X509Certificate;

/**
 * 区域Controller
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/esign/seal")
public class SealController extends BaseController {

	@Autowired
	private SealService sealService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CertificateService certificateService;
	
	@ModelAttribute("seal")
	public Seal get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return sealService.get(id);
		}else{
			Seal seal = new Seal();
			String defaultSingCount = DictUtils.getDictValue("签章", "default_sign_count", "签章");
			seal.setSignCount(Integer.valueOf(defaultSingCount));
			return seal;
		}
	}

	@RequiresPermissions("esign:seal:view")
	@RequestMapping(value = {"list", ""})
	public String list(Seal seal, HttpServletRequest request, HttpServletResponse response, Model model) {
 		Page<Seal> page = sealService.find(new Page<Seal>(request, response), seal);
		model.addAttribute("page", page);
		return "modules/esign/seal/list";
	}

	@RequiresPermissions("esign:seal:view")
	@RequestMapping(value = "detail")
	public String detail(String id, Model model) {
		Seal seal = get(id);
		model.addAttribute("seal", seal);
		return "modules/esign/seal/detail";
	}

	@RequiresPermissions("esign:seal:view")
	@RequestMapping(value = "sealImg")
	public void sealImg(String id, HttpServletResponse response) {
		Seal seal = get(id);
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			os.write(seal.getSealImgContent());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(os);
		}
	}

	@RequiresPermissions("esign:seal:edit")
	@RequestMapping(value = "form")
	public String form(@RequestParam(required=false) String id, Model model) {
		Seal seal = get(id);
		model.addAttribute("seal", seal);
		return "modules/esign/seal/form";
	}

	@RequiresPermissions("esign:seal:edit")
	@RequestMapping(value = "form/sealimg")
	public String formUpdateSealImg() {
		return "modules/esign/seal/update_seal_img";
	}

	@RequiresPermissions("esign:seal:edit")
	@RequestMapping(value = "form/sealcert")
	public String formUpdateSealCert() {
		return "modules/esign/seal/update_seal_cert";
	}

	@RequiresPermissions("esign:seal:edit")
	@RequestMapping(value = "update")
	public String update(Seal sealForm) {
		sealService.save(sealForm);
		return "redirect:" + Global.getAdminPath() + "/esign/seal/list";
	}

	@RequiresPermissions("esign:seal:edit")
	@RequestMapping(value = "save")
	public String save(MultipartFile certFile, MultipartFile sealFile, Seal sealForm, String sealName, int sealType, String sealToken, String companyId, String userId, String certPassword, int signCount, RedirectAttributes redirectAttributes) {
		// 解析证书
		try {
			Seal  seal = new Seal();

			if(certFile != null ) {
				Certificate certificate = new Certificate();
				certificate.setContent(certFile.getBytes());
				PFXUtil pfxUtil = new PFXUtil(certFile.getBytes(), certPassword);
				X509Certificate jdkCert = (X509Certificate) pfxUtil.getCertificate();

				certificate.setSerialNumber(String.valueOf(jdkCert.getSerialNumber()));
				certificate.setPassword(certPassword);
				certificate.setNotAfter(jdkCert.getNotAfter());
				certificate.setNotBefore(jdkCert.getNotBefore());
				certificate.setSubjectDN(jdkCert.getSubjectDN().toString());
				certificate.setIssuerDN(jdkCert.getIssuerDN().toString());
				certificate.setCertRoot("123123");
				certificate.setCertType("pfx");

				certificateService.save(certificate);
				seal.setCert(certificate);
			}

			// 保存印章
			if(sealFile != null) {
				seal.setSealImgContent(sealFile.getBytes());
			}

			seal.setSealName(sealName);
			seal.setSealType(sealType);
			seal.setSealToken(sealToken);
			seal.setSignCount(signCount);

			if(seal.getSealType() == 1) {
				seal.setSealOwnerId(companyId);
				Office office = officeService.get(seal.getSealOwnerId());
				if(office != null) {
					seal.setSealOwnerName(office.getName());
				}
			} else if(seal.getSealType() == 2) {
				seal.setSealOwnerId(userId);
				User user = systemService.getUser(seal.getSealOwnerId());
				if(user != null) {
					seal.setSealOwnerName(user.getName());
				}
			}

			sealService.save(seal);
		} catch (IOException e) {
			addMessage(redirectAttributes, "解析证书失败");
			e.printStackTrace();
			return "redirect:" + Global.getAdminPath() + "/esign/seal/list";
		} catch (Exception e) {
			addMessage(redirectAttributes, "制章失败");
			e.printStackTrace();
			return "redirect:" + Global.getAdminPath() + "/esign/seal/list";
		}

		addMessage(redirectAttributes, "操作成功");
		return "redirect:" + Global.getAdminPath() + "/esign/seal/list";
	}
	
	@RequiresPermissions("esign:seal:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		sealService.delete(id);
		addMessage(redirectAttributes, "删除印章成功");
		return "redirect:"+Global.getAdminPath()+"/esign/seal/";
	}
}
