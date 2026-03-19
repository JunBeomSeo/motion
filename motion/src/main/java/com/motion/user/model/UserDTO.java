package com.motion.user.model;

public class UserDTO {

    private int user_idx;
    private String email;
    private String name;
    private String profile;
    private String join_date;

    /** 기본 생성자 */
    public UserDTO() {

    }

    /** 모든 인자가 있는 생성자 */
    public UserDTO(int user_idx, String email, String name, String profile, String join_date) {
        this.user_idx = user_idx;
        this.email = email;
        this.name = name;
        this.profile = profile;
        this.join_date = join_date;
    }

    /** Getter */
    public int getUser_idx() {
        return user_idx;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getProfile() {
        return profile;
    }

    public String getJoin_date() {
        return join_date;
    }

    /** Setter */
    public void setUser_idx(int user_idx) {
        this.user_idx = user_idx;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
    
    public void setJoin_date(String join_date) {
        this.join_date = join_date;
    }

    
    
}
