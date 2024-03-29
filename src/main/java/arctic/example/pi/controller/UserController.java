package arctic.example.pi.controller;

import arctic.example.pi.DTO.CountType;
import arctic.example.pi.entity.ERole;
import arctic.example.pi.entity.Role;
import arctic.example.pi.entity.User;
import arctic.example.pi.repository.UserRepository;
import arctic.example.pi.service.RoleService;
import arctic.example.pi.service.UserService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(value = "http://localhost:4200")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
   RoleService roleService;
    @Autowired
    UserRepository userRepo;

    @GetMapping("/allUsers")
    public List<User> retrieveAllUsers() {
        List<User> List = userService.getUsers();
        return List;

    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);

    }

    @PutMapping("/modifUser")
    public User updateUser(@RequestBody User p) {
        return userService.updateUser(p);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> getUserbyId(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updateUser = userService.findById(id);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        updateUser.setPassword(hashedPassword);
        updateUser.setName(user.getName());
        updateUser.setPrenom(user.getPrenom());
        updateUser.setTel(user.getTel());
        updateUser.setAddress(user.getAddress());
        updateUser.setUsername(user.getUsername());
        updateUser.setDatenaissance(user.getDatenaissance());
        updateUser.setEmail(user.getEmail());


        User updateBoite = userService.updateUser(updateUser);
        return ResponseEntity.ok(updateBoite);


    }

    @DeleteMapping("/deleteUser/{id}")
    public void DeleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    // partie service avancés

    //  ajout role to user

   @PostMapping("/add/{user}/{role}")
    public ResponseEntity<User> assignusertorole(@PathVariable Long user, @PathVariable Long role) {
        User user1=userService.findById(user);

        Role role1=roleService.findID(role);

           // User updatedDonation = user1;
            user1.getRoles().add(role1);
            userRepo.save(user1);
            return ResponseEntity.ok(user1);
        }


    @PutMapping("/add/{user}/{role}")
    public User addRoletoUser(@PathVariable String user, @PathVariable ERole role) {
        return userService.addRoleToUser(user, role);
    }


    //RECHERCHE
    @GetMapping("/retrieveUserByState/{state}")
    @ResponseBody
    public List<User> retrieveUserByState(@PathVariable("state") boolean stateUser) {
        return userService.retrieveUserByState(stateUser);
    }

    @GetMapping("/userbyusername")
    @ResponseBody
    public List<User> retrieveuserbynom(@RequestParam String username) {
        return (List<User>) userService.getUser(username);
    }

    @GetMapping("/userbyemail")
    @ResponseBody
    public User retrieveuserbyemail(@RequestParam String email) {
        return userService.getUserByMail(email);
    }

    // Activation User
    @PutMapping("/activateUser")
    public User activateUser(@RequestBody User user) {
        return userService.activateUser(user);
    }

    // desactiver User


    // Modifier Password
    @PutMapping("/updatepassword/{emailUser}/{password}")
    void updatePassword(@PathVariable("emailUser") String emailUser, @PathVariable("password") String newPassword
    ) {
        userService.updatePassword(emailUser, newPassword);
    }

    @PutMapping("/updatepassword")
    public ResponseEntity<?> processResetPassword(HttpServletRequest request) {
        String mail = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.getUserByMail(mail);

        if (user == null) {

            String mesg = "Invalid mail";

            return ResponseEntity.ok(mesg);

        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userService.updatePassword(user.getEmail(), passwordEncoder.encode(password));
            String mesg = "You have successfully changed your password.";
            return ResponseEntity.ok(mesg);

        }

    }

    @GetMapping("/tri")
    public List<User> findAll() {
        return userService.findAllByOrderBOrderByRolesDesc();
    }

    @GetMapping("/stat")
    public List<CountType> statistque() {
        return userService.statistque();
    }

    @GetMapping("/statage")
    public List<CountType> stats() {
        return userService.statistqueAge();
    }


    @GetMapping("/stati")
    public List<CountType> statistque1(){
        return userService.statistque1();
    }
    @GetMapping("/exportpdf")
    public ResponseEntity<InputStreamResource> exportPdf() {
        List<User> users = (List<User>) userService.getUsers();
        ByteArrayInputStream bais = userService.userExport(users);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline;filename=user.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
    }

    // forget password API Mail
    @GetMapping("/sendme/{emailUser}")
    public void forgotpass(@PathVariable("emailUser") String emailUser) {
        userService.forgotpass(emailUser);
    }

    // add Organisation to User

    @PutMapping("/addorganisation/{username}/{id}")
    public User addOrganisationToUser(@PathVariable(value = "username") String username, @PathVariable(value = "id") Long id) {
        return userService.addOrganisationToUser(username, id);
    }


    @GetMapping("/findstream")
    public List<User> searchh(@RequestParam String nom) {
        return userService.searchh(nom);
    }


    // Scheduler
    @GetMapping("/scheduler")
    public List<User> getdisabledUsers() {
        return userService.getdisable();
    }
}
