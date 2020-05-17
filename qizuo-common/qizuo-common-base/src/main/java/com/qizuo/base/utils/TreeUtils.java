/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.base.utils;


import com.qizuo.base.model.tree.TreeDto;
import com.qizuo.base.model.tree.AbstractTreeService;
import com.qizuo.util.common.ObjectIsEmptyUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 树工具，基于base里面tree.
 */
public class TreeUtils<T extends TreeDto<T, ID>, ID extends Serializable> extends AbstractTreeService<T, ID> {
    /**
     * Gets child tree nodes.
     *
     * @param list     the list
     * @param parentId the parent id
     *
     * @return the child tree nodes
     */
    public static List<TreeDto> getChildTreeNodes(List<TreeDto> list, Long parentId) {
        List<TreeDto> returnList = new ArrayList<>();

        for (TreeDto treeNode : list) {
            if (treeNode.getPid() == null) {
                continue;
            }

            if (Objects.equals(treeNode.getPid(), parentId)) {
                recursionFn2(list, treeNode);
                returnList.add(treeNode);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private static void recursionFn2(List<TreeDto> list, TreeDto node) {
        List<TreeDto> childList = getChildList2(list, node);
        if (ObjectIsEmptyUtils.isEmpty(childList)) {
            return;
        }
        node.setChildren(childList);
        for (TreeDto tChild : childList) {
            recursionFn2(list, tChild);
        }
    }

    /**
     * 得到子节点列表
     */
    private static List<TreeDto> getChildList2(List<TreeDto> list, TreeDto t) {
        List<TreeDto> tList = new ArrayList<>();

        for (TreeDto treeNode : list) {
            if (ObjectIsEmptyUtils.isEmpty(treeNode.getPid())) {
                continue;
            }
            if (Objects.equals(treeNode.getPid(), t.getId())) {
                tList.add(treeNode);
            }
        }
        return tList;
    }
}
