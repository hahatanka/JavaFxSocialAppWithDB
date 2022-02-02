package com.socialapp.socialapp.repository;

public class DataManager {
    //MAKE CLASS TOTALLY STATIC
    //1 create instance of the same class inside it:
    private static DataManager dataManager_instance;

    //2 private constructor - doesn't have "public" before the name

    DataManager(){}

    private Integer loggedInUserId = null;
    private Integer friendId = null;
    private Integer postId= null;

    public static DataManager getInstance(){
        if(dataManager_instance == null) dataManager_instance = new DataManager();
        return dataManager_instance;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }



    public Integer getLoggedInUserId() {
        return loggedInUserId;
    }

    public void setLoggedInUserId(Integer loggedInUserId) {
        this.loggedInUserId = loggedInUserId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }
}
