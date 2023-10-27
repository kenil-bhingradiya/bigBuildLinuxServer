package project.bigbuildlinux.services;

import org.springframework.stereotype.Service;
import project.bigbuildlinux.endpoints.dtos.NewUserRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

@Service
public class CreateUserService
{
    public String createUser(NewUserRequest newUser) throws IOException
    {
        String command = "sudo " + System.getProperty("user.dir") + "/src/main/java/project/bigbuildlinux/scripts/usercreation.sh " + newUser.getUser() + " " + newUser.getPasswd() + " " + newUser.getDepartment();
        Boolean isCommandOk = runShellCommand(command, newUser.getUser());
        System.out.println(command);
        if(isCommandOk)
            return "User Created Successfully!!";
        else
            return "User creation failed!";
    }

    public Boolean runShellCommand(String command, String username) throws IOException
    {
        Process proc = Runtime.getRuntime().exec(command);
        InputStream is = proc.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String data = "", res="";
        while( (data = reader.readLine()) != null)
            res = data;
        System.out.println(res);

        return Objects.equals(res, "User '" + username + "' created successfully!!");
    }

    public boolean removeUser(String username) throws IOException
    {
        String command = "sudo userdel -r " + username;
        Process proc = Runtime.getRuntime().exec(command);
        proc.getOutputStream().close();
        return true;
    }
}
