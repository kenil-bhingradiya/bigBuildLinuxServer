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
        String command = "sudo " + System.getProperty("user.dir") + "/src/main/java/project/bigbuildlinux/scripts/kerberoscreation.sh " + newKerberos.getUser() + " " + newKerberos.getPasswd();
        System.out.println(command);
        Boolean isCommandOk = runShellCommand(command, newKerberos.getUser());

        if(isCommandOk)
            return "Kerberos User Created Successfully!!";
        else
            return "Kerberos User creation failed!";
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
        return Objects.equals(res, "kadmin.local:  User " + username + " successfully created in the Kerberos.");

    }

    public boolean removeKerberosUser(String username) throws IOException
    {
        String command = "sudo delete_principle " + username;
        Process proc = Runtime.getRuntime().exec(command);
        proc.getOutputStream().close();
        return true;
    }
}
