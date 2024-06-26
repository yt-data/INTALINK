package com.intalink.configoperations.controller.dataTableRP;

import com.intalink.configoperations.service.dataTableRP.IHandleService;
import com.intalink.configoperations.web.controller.BaseController;
import com.intalink.configoperations.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Controller
@RequestMapping({"/importFile"})
public class PdmLdmController extends BaseController {
    @Autowired
    private IHandleService iHandleService;

    @PostMapping({"/pdm/upload"})
    @ResponseBody
    public AjaxResult pdmImport(@RequestParam MultipartFile file, @RequestParam String modelId) throws Exception {
        try {
            InputStream inputStream = file.getInputStream();
            iHandleService.tableRpFromPdm(inputStream, modelId);
        } catch (OutOfMemoryError error) {
            //自定义返回
            return new AjaxResult(500, "内存溢出导致的错误", null);
        }
        return success();
    }

    @PostMapping({"/ldm/upload"})
    @ResponseBody
    public AjaxResult ldmImport(@RequestParam MultipartFile file, @RequestParam String modelId) throws Exception {
        try {
            InputStream inputStream = file.getInputStream();
            iHandleService.tableRpFromLdm(inputStream, modelId);
        } catch (OutOfMemoryError error) {
            //自定义返回
            return new AjaxResult(500, "内存溢出导致的错误", null);
        }
        return success();
    }
}
