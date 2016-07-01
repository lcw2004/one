/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.modules.esign.web;

import com.lcw.one.common.config.Global;
import com.lcw.one.common.utils.StringUtils;
import com.lcw.one.common.web.BaseController;
import com.lcw.one.modules.esign.entity.Certificate;
import com.lcw.one.modules.esign.service.CertificateService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 区域Controller
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/esign/cert")
public class CertificateController extends BaseController {

	@Autowired
	private CertificateService certificateService;
	
	@ModelAttribute("cert")
	public Certificate get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return certificateService.get(id);
		}else{
			return new Certificate();
		}
	}

	@RequiresPermissions("esign:cert:view")
	@RequestMapping(value = {"list", ""})
	public String list(Model model) {
		return "modules/esign/cert/list";
	}

	@RequiresPermissions("esign:cert:view")
	@RequestMapping(value = "form")
	public String form(Certificate cert, Model model) {
		model.addAttribute("cert", cert);
		return "modules/esign/cert/form";
	}
	
	@RequiresPermissions("esign:cert:edit")
	@RequestMapping(value = "save")
	public String save(Certificate cert, Model model, RedirectAttributes redirectAttributes, MultipartFile certFile) {
//		String password ="1111";
//
//		System.out.println(certFile.getSize());
//
//		Certificate certificate = new Certificate();
//		try {
//			certificate.setContent(certFile.getBytes());
//			PFXUtil pfxUtil = new PFXUtil(certFile.getBytes(), password);
//			java.security.cert.X509Certificate jdkCert = (X509Certificate)pfxUtil.getCertificate();
//			jdkCert.getIssuerDN();
//
//			certificate.setPassword(password);
//			certificate.setNoteAfter(jdkCert.getNotAfter());
//			certificate.setNoteBefore(jdkCert.getNotBefore());
//			certificate.setCertCn(jdkCert.getIssuerDN().toString());
//			certificate.setCertRoot("123123");
//			certificate.setCertType("pfx");
//		} catch (IOException e) {
//			addMessage(redirectAttributes, "上传证书失败");
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

//		certificateService.save(certificate);

		return "redirect:"+Global.getAdminPath()+"/esign/cert/";
	}
	
	@RequiresPermissions("esign:cert:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		certificateService.delete(id);
		addMessage(redirectAttributes, "删除区域成功");
		return "redirect:"+Global.getAdminPath()+"/esign/cert/";
	}
}
