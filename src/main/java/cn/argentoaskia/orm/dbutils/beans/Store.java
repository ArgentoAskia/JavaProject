package cn.argentoaskia.orm.dbutils.beans;


import java.sql.Timestamp;

public class Store {

  private Integer storeId;
  private Integer managerStaffId;
  private Integer addressId;
  private java.sql.Timestamp lastUpdate;

  @Override
  public String toString() {
    return "Store{" +
            "storeId=" + storeId +
            ", managerStaffId=" + managerStaffId +
            ", addressId=" + addressId +
            ", lastUpdate=" + lastUpdate +
            '}';
  }

  public Integer getStoreId() {
    return storeId;
  }

  public void setStoreId(Integer storeId) {
    this.storeId = storeId;
  }

  public Integer getManagerStaffId() {
    return managerStaffId;
  }

  public void setManagerStaffId(Integer managerStaffId) {
    this.managerStaffId = managerStaffId;
  }

  public Integer getAddressId() {
    return addressId;
  }

  public void setAddressId(Integer addressId) {
    this.addressId = addressId;
  }

  public Timestamp getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(Timestamp lastUpdate) {
    this.lastUpdate = lastUpdate;
  }
}
