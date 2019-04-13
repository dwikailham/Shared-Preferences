package com.dwikailham.notesapp.models;

public class Session {
    private Settings settings;
    private String user;
    private int layout;

    public Session(Settings settings) {
        this.settings = settings;
        user = settings.getUser();
    }

    public User doLogin(String username, String password){
        User foundUser = null;
        for(User user : Data.getUsers()){
            if(username.equals(user.getUsername())
                && password.equals(user.getPassword())){
                foundUser = user;
                break;
            }
        }
        return foundUser;
    }

    public void doLogout(){
        settings.removeUser();
        this.user = null;
    }

    public boolean isLogin(){
        return user != null;
    }

    public String getUser(){
        return user;
    }

    public void setUser(String user){
        settings.setUser(user);
        this.user = user;
    }

    public void setLayout(int layout) {
        settings.setLayoutMode(layout);
        this.layout = layout;
    }

    public int getLayout() {
        return settings.preferences.getInt(Constant.LAYOUT_MODE,0);
    }
}
