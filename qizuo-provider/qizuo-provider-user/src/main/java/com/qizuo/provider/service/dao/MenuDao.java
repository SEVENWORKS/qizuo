package com.qizuo.provider.service.dao;

import com.qizuo.base.model.dao.BaseDao;
import com.qizuo.provider.model.po.MenuPoJo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/** @Author: fangl @Description: 菜单 @Date: 11:42 2019/1/1 */
// 在springboot
// 中，给mapper的接口上加上@Repository，无法生成相应的bean,从而无法@Autowired，这是因为spring扫描注解时，自动过滤掉了接口和抽象类，这种情况下可以在启动的类前加
// 上@MapperScan（“×××.×××.mapper”，从而使mapper可以自动注入，但是idea还会提示bean无法找到，但是不会影响运行
// 也可以用@mapper解决这个问题，它除了这个作用，还有以下作用
// 1:为了把mapper这个DAO交給Spring管理 http://412887952-qq-com.iteye.com/blog/2392672
// 2:为了不再写mapper映射文件 https://blog.csdn.net/weixin_39666581/article/details/103899495
// 3:为了给mapper接口 自动根据一个添加@Mapper注解的接口生成一个实现类 http://www.tianshouzhi.com/api/tutorials/mapstruct/292
@Mapper
@Repository
public interface MenuDao extends BaseDao<MenuPoJo> {

  /**
   * @author: fangl
   * @description: 查询菜单递归列表
   * @date: 17:44 2019/1/9
   */
  List<MenuPoJo> qEachList(MenuPoJo menuPoJo);
}
