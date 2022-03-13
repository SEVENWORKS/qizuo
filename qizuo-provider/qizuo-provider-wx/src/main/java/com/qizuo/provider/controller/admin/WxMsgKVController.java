package com.qizuo.provider.controller.admin;

import com.qizuo.base.annotation.LogAnnotation;
import com.qizuo.base.annotation.SqlDisplay;
import com.qizuo.base.annotation.ValidateRequestAnnotation;
import com.qizuo.base.model.base.KvDto;
import com.qizuo.base.model.result.BackResult;
import com.qizuo.base.utils.BackResultUtils;
import com.qizuo.util.parse.JacksonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/** 消息回复controller */
@AllArgsConstructor
@RestController
@RequestMapping("/msgKV")
@Api(value = "Wx-MsgKVController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WxMsgKVController {
  // 存储key
  public static final String key = "WXMSGKV";
  // redis操作对象
  @Resource private RedisTemplate<String, Object> redisTemplate;

  @PostMapping("/list")
  @ApiOperation(httpMethod = "POST", value = "消息回复列表")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  public BackResult list() {
    return BackResultUtils.ok(
        redisTemplate.opsForList().range(key, 0, redisTemplate.opsForList().size(key) - 1));
  }

  @PostMapping("/delete")
  @ApiOperation(httpMethod = "POST", value = "消息回复删除")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  public BackResult delete() {
    redisTemplate.delete(key);
    return BackResultUtils.ok();
  }

  @PostMapping("/iuDo")
  @ApiOperation(httpMethod = "POST", value = "消息回复增加")
  @LogAnnotation
  @ValidateRequestAnnotation
  @SqlDisplay
  public BackResult iuDo(@RequestBody KvDto<String, String> kvDto) throws IOException {
    Map<String, String> map = new HashMap<>();
    map.put(kvDto.getKey(), kvDto.getValue());
    redisTemplate.opsForList().rightPush(key, JacksonUtil.toJson(map));
    return BackResultUtils.ok();
  }
}
