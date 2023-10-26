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
        String command = "sh " + System.getProperty("user.dir") + "/src/main/java/project/bigbuildlinux/scripts/usercreation.sh";
        Boolean isCommandOk = runShellCommand(command, newUser.getUser());

        if(isCommandOk)
            return "User created Successfully!";
        else
            return "User creation failed!";
    }

    public Boolean runShellCommand(String command, String username) throws IOException
    {
        Process proc = Runtime.getRuntime().exec(command);
        InputStream is = proc.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String data = reader.readLine();
        return Objects.equals(data, "User " + username + " Created Successfully!!");
    }
}
