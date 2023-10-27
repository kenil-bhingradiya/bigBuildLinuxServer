package project.bigbuildlinux.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.bigbuildlinux.endpoints.dtos.NewKerberosUserRequest;
import project.bigbuildlinux.endpoints.dtos.NewUserRequest;
import project.bigbuildlinux.services.CreateKerberosService;
import project.bigbuildlinux.services.CreateUserService;

import java.io.IOException;

@RestController
public class UserController
{
    @Autowired
    CreateUserService createUserService;

    @Autowired
    CreateKerberosService createKerberosService;

    @GetMapping("/")
    public String helloWorld()
    {
        return "Hello from Linux Server";
    }

    @PostMapping("/create/newLinuxUser")
    public String createNewUser(@RequestBody NewUserRequest newUser) throws IOException
    {
        return createUserService.createUser(newUser);
        //return "new user created!";
    }

    @PostMapping("/create/newKerberosUser")
    public String createKerberosUser(@RequestBody NewKerberosUserRequest newKerberosUser) throws IOException
    {
        return createKerberosService.createKerberosService(newKerberosUser);
        //return "new kerberos user created!!";
    }
    @GetMapping("/deleteUser/{username}")
    public Boolean deleteUser(@PathVariable("username") String username) throws Exception
    {
        return createUserService.removeUser(username);
    }

    @GetMapping("/deleteKerberosUser/{username}")
    public Boolean deleteKerberosUser(@PathVariable("username") String username) throws Exception
    {
        return createKerberosService.removeKerberosUser(username);
    }

}
