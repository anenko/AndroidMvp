package com.alex.pattern.data.models.response;

import com.alex.pattern.data.models.UserModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Alex
 */

public class SearchResponse {

    @SerializedName("total_count")
    private int totalCount;
    @SerializedName("incomplete_results")
    private boolean incompleteResults;
    @SerializedName("items")
    private List<UserModel> users;

    //region getters and setters
    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }
    //endregion

}
