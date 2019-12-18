package com.ytfs.service.packet.bp;

import java.util.Comparator;

public class UserSpace {

    private int userId;
    private String userName;
    private long spaceTotal;

    /**
     * @return the spaceTotal
     */
    public long getSpaceTotal() {
        return spaceTotal;
    }

    /**
     * @param spaceTotal the spaceTotal to set
     */
    public void setSpaceTotal(long spaceTotal) {
        this.spaceTotal = spaceTotal;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static class UserSpaceComparator implements Comparator<UserSpace> {

        @Override
        public int compare(UserSpace o1, UserSpace o2) {
            if (o1.userId > o2.userId) {
                return 1;
            } else if (o1.userId == o2.userId) {
                return 0;
            } else {
                return -1;
            }
        }

    }
}
