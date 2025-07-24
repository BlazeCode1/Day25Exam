package org.example.day25exam.Service;

import org.example.day25exam.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers(){
        return users;
    }


    public boolean addUser(User user){
        for (User u : users){
            if(user.getID().equals(u.getID())){
                return false;
            }
        }
        users.add(user);
        return true;

    }


    public boolean updateUser(User user){
        for (User u : users){
            if(user.getID().equals(u.getID())){
                users.set(users.indexOf(u),user);
                return true;
            }
        }
        return false;
    }


    public boolean deleteUser(String ID){
        for(User u : users){
            if(ID.equals(u.getID())){
                users.remove(u);
                return true;
            }
        }
        return false;
    }


    public ArrayList<User> getUsersWithBalance(double balance){
        ArrayList<User> sameBalance = new ArrayList<>();

        for (User u : users){
            if(u.getBalance() >= balance){
                sameBalance.add(u);
            }
        }
        return sameBalance;
    }

    public ArrayList<User> getUsersWithSameAge(int age){
        ArrayList<User> usersSameAge = new ArrayList<>();

        for (User u : users){
            if(u.getAge() >= age){
                usersSameAge.add(u);
            }
        }
        return usersSameAge;
    }








}
