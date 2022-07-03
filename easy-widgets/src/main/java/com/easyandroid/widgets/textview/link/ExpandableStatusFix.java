package com.easyandroid.widgets.textview.link;

/**
 * package: com.easyandroid.widgets.textview.link.ExpandableStatusFix
 * author: gyc
 * description: 为LinkedTextView添加展开和收回状态的记录
 * time: create at 2022/7/3 15:19
 */
public interface ExpandableStatusFix {

    void setStatus(StatusType status);

    StatusType getStatus();
}
