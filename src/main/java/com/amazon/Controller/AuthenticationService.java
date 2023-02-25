package com.amazon.Controller;

import com.amazon.DB.UserDAO;
import com.amazon.Model.User;
import java.util.List;
public class AuthenticationService {
      private static AuthenticationService auth= new AuthenticationService();
        UserDAO dao=new UserDAO();
        private AuthenticationService() {}
        public boolean loginUser(User user) {
            String sql= "SELECT * FROM User WHERE accountNo = "+user.accountNo+" AND password = '"+user.password+"'";
            List<User> users = dao.retrieve(sql);
            if(users.size()>0) {
                User u = users.get(0);// to access first element of the list
                user.id = u.id;
                user.name=u.name;
                user.accountNo=u.accountNo;
                user.password=u.password;
                user.accountBalance=u.accountBalance;
                user.lastUpdatedOn=u.lastUpdatedOn;
                return true;
            }
            return false;
        }
        public boolean registerUser(User user) {
            user.accountBalance=0.0;
            int result = dao.insert(user);
            return result > 0;
        }
        public boolean updateUser(User user) {
            int result = dao.update(user);
            return result > 0;
        }
        public void showAccount(int uid){
            String sql="select * from user where id="+uid;
            List<User> userList=dao.retrieve(sql);
            userList.get(0).prettyPrint();
        }
        public static AuthenticationService getInstance() {
            return auth;
        }
}
