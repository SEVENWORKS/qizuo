package com.qizuo.provider.controller.error;

import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.utils.BackResultUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre>
 * 出错页面控制器
 * </pre>
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

  @GetMapping(value = "/404")
  public BackResult error404() {
    return BackResultUtils.error("404");
  }

  @GetMapping(value = "/500")
  public BackResult error500() {
    return BackResultUtils.error("500");
  }

}
