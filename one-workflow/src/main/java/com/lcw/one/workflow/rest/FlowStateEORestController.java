package com.lcw.one.workflow.rest;

import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.utils.ImageUtils;
import com.lcw.one.workflow.entity.FlowStateEO;
import com.lcw.one.workflow.service.FlowStateEOService;
import com.lcw.one.workflow.service.flow.ActivitiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/${restPath}/flow/flowState")
@Api(description = "流程图")
public class FlowStateEORestController {

    private static final Logger logger = LoggerFactory.getLogger(FlowStateEORestController.class);

    @Autowired
    private FlowStateEOService flowStateEOService;

    @ApiOperation(value = "流程图详情")
    @GetMapping("/{processKey}")
    public ResponseMessage<FlowStateEO> getById(@PathVariable("processKey") String processKey) {
        return Result.success(flowStateEOService.get(processKey));
    }

    @ApiOperation(value = "流程图列表")
    @GetMapping("")
    public ResponseMessage<PageInfo<FlowStateEO>> list(Integer pageNo, Integer pageSize, String likeName) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return Result.success(flowStateEOService.page(pageInfo, likeName));
    }

    @ApiOperation(value = "部署流程图")
    @PostMapping("")
    public ResponseMessage deploy(@RequestParam("file") MultipartFile file) throws IOException {
        if (file != null) {
            flowStateEOService.deploy(file.getInputStream(), file.getOriginalFilename());
            return Result.success();
        } else {
            return Result.error("上传文件失败");
        }
    }

    @ApiOperation(value = "修改流程图")
    @PutMapping("")
    public ResponseMessage update(@RequestBody FlowStateEO flowStateEO) throws IOException {
        flowStateEOService.update(flowStateEO);
        return Result.success(flowStateEO);
    }

    @ApiOperation(value = "删除流程图")
    @DeleteMapping("/{processKey}")
    public ResponseMessage deleteById(@PathVariable("processKey") String processKey) {
        flowStateEOService.delete(processKey);
        return Result.success();
    }

    @ApiOperation(value = "流程图图片")
    @GetMapping(value = "/{processKey}/processImage")
    public void processImage(HttpServletResponse response, @PathVariable("processKey") String processKey) {
        InputStream is = null;
        try {
            is = flowStateEOService.progressImage(processKey);
            byte[] bytes = ImageUtils.cutImage(is, 10);

            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }


}
