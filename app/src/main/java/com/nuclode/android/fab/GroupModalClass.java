package com.nuclode.android.fab;

public class GroupModalClass {
    int groupIcon;
    String groupName;

    public GroupModalClass() {
    }

    public GroupModalClass(int groupIcon, String groupName) {
        this.groupIcon = groupIcon;
        this.groupName = groupName;
    }

    public int getGroupIcon() {
        return groupIcon;
    }

    public void setGroupIcon(int groupIcon) {
        this.groupIcon = groupIcon;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
