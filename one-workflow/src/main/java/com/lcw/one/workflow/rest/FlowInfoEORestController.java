package com.lcw.one.workflow.rest;

import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.utils.ImageUtils;
import com.lcw.one.util.utils.http.HttpUtils;
import com.lcw.one.workflow.entity.FlowInfoEO;
import com.lcw.one.workflow.service.FlowInfoActivitiService;
import com.lcw.one.workflow.service.FlowInfoEOService;
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
@RequestMapping(value = "/${restPath}/flow/flowInfo")
@Api(description = "工作流")
public class FlowInfoEORestController {

    private static final Logger logger = LoggerFactory.getLogger(FlowInfoEORestController.class);

    @Autowired
    private FlowInfoEOService flowInfoEOService;

    @Autowired
    private FlowInfoActivitiService flowInfoActivitiService;

    @ApiOperation(value = "工作流详情")
    @GetMapping("/{processKey}")
    public ResponseMessage<FlowInfoEO> getById(@PathVariable String processKey) {
        return Result.success(flowInfoEOService.get(processKey));
    }

    @ApiOperation(value = "工作流列表")
    @GetMapping("")
    public ResponseMessage<PageInfo<FlowInfoEO>> list(Integer pageNo, Integer pageSize, String likeName) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return Result.success(flowInfoEOService.page(pageInfo, likeName));
    }

    @ApiOperation(value = "部署工作流")
    @PostMapping("")
    public ResponseMessage deploy(@RequestParam("file") MultipartFile file) throws IOException {
        if (file != null) {
            flowInfoActivitiService.deploy(file.getInputStream(), file.getOriginalFilename());
            return Result.success();
        } else {
            return Result.error("上传文件失败");
        }
    }

    @ApiOperation(value = "修改工作流")
    @PutMapping("")
    public ResponseMessage update(@RequestBody FlowInfoEO flowInfoEO) {
        flowInfoEOService.update(flowInfoEO);
        return Result.success(flowInfoEO);
    }

    @ApiOperation(value = "删除工作流")
    @DeleteMapping("/{processKey}")
    public ResponseMessage deleteById(@PathVariable String processKey) {
        flowInfoEOService.delete(processKey);
        return Result.success();
    }

    @ApiOperation(value = "工作流流程图片")
    @GetMapping(value = "/{processKey}/processImage")
    public void processImage(HttpServletResponse response, @PathVariable String processKey) {
        InputStream is = null;
        try {
            is = flowInfoActivitiService.progressImage(processKey);
            byte[] bytes = ImageUtils.cutImage(is, 10);
            HttpUtils.addImageHeader(response);
            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }


}
