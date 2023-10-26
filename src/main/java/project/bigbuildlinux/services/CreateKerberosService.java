package project.bigbuildlinux.services;

import org.springframework.stereotype.Service;
import project.bigbuildlinux.endpoints.dtos.NewKerberosUserRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

@Service
public class CreateKerberosService
{
    public String createKerberosService(NewKerberosUserRequest newKerberos) throws IOException
    {
        String command = "sh " + System.getProperty("user.dir") + "/src/main/java/project/bigbuildlinux/scripts/usercreation.sh";
        Boolean isCommandOk = runShellCommand(command, newKerberos.getUser());

        if(isCommandOk)
            return "Kerberos User created Successfully!";
        else
            return "Kerberos User creation failed!";
    }

    public Boolean runShellCommand(String command, String username) throws IOException
    {
        Process proc = Runtime.getRuntime().exec(command);
        InputStream is = proc.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String data = reader.readLine();
        return Objects.equals(data, "User " + username + " successfully created in the Kerberos.");
    }
}
