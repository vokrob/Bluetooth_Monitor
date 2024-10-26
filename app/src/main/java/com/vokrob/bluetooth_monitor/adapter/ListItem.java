package com.vokrob.bluetooth_monitor.adapter;

public class ListItem {
    private String btName;
    private String btMac;
    private String itemType = BtAdapter.DEF_ITEM_TYPE;

    public String getBtName() {
        return btName;
    }

    public void setBtName(String btName) {
        this.btName = btName;
    }

    public String getBtMac() {
        return btMac;
    }

    public void setBtMac(String btMac) {
        this.btMac = btMac;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
