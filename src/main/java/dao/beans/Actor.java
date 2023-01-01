package dao.beans;


public class Actor {

  private long actorId;
  private String firstName;
  private String lastName;
  private java.sql.Timestamp lastUpdate;

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Actor{");
    sb.append("actorId=").append(actorId);
    sb.append(", firstName='").append(firstName).append('\'');
    sb.append(", lastName='").append(lastName).append('\'');
    sb.append(", lastUpdate=").append(lastUpdate);
    sb.append('}');
    return sb.toString();
  }

  public long getActorId() {
    return actorId;
  }

  public void setActorId(long actorId) {
    this.actorId = actorId;
  }


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


  public java.sql.Timestamp getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(java.sql.Timestamp lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

}
