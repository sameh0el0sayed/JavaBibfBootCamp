package com.ga.TicketSystemProject3.Service;


import com.ga.TicketSystemProject3.Exception.InformationExistException;
import com.ga.TicketSystemProject3.Model.Image;
import com.ga.TicketSystemProject3.Model.SecureToken;
import com.ga.TicketSystemProject3.Model.User;
import com.ga.TicketSystemProject3.Model.UserProfile;
import com.ga.TicketSystemProject3.Model.request.ImageRequest;
import com.ga.TicketSystemProject3.Model.request.LoginRequest;
import com.ga.TicketSystemProject3.Model.response.LoginResponse;
import com.ga.TicketSystemProject3.Repository.ImageRepository;
import com.ga.TicketSystemProject3.Repository.UserProfileRepository;
import com.ga.TicketSystemProject3.Repository.UserRepository;
import com.ga.TicketSystemProject3.Security.JWTUtils;
import com.ga.TicketSystemProject3.Security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private  final SecureTokenService secureTokenService;
    private  final ImageService imageService;
    private  final ImageRepository imageRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private MyUserDetails myUserDetails;

    @Autowired
    public UserService(UserRepository userRepository, UserProfileRepository userProfileRepository, SecureTokenService secureTokenService, ImageService imageService, ImageRepository imageRepository,
                       @Lazy PasswordEncoder passwordEncoder,
                       JWTUtils jwtUtils,
                       @Lazy AuthenticationManager authenticationManager,
                       @Lazy MyUserDetails myUserDetails) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.secureTokenService = secureTokenService;
        this.imageService = imageService;
        this.imageRepository = imageRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.myUserDetails = myUserDetails;
    }
    public User createUser(User userObject){
        System.out.println("Service Calling createUser ==> ");
        if(!userRepository.existsByEmailAddress(userObject.getEmailAddress())){
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        }
        else
        {
            throw new InformationExistException("User with email address " + userObject.getEmailAddress() + " already exists.");
        }
    }

    public User findUserByEmailAddress(String email){
        return userRepository.findUserByEmailAddress(email);
    }
    public ResponseEntity<?> loginUser (LoginRequest loginRequest){

        try{

            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            myUserDetails = (MyUserDetails) authentication.getPrincipal();
            final String JWT = jwtUtils.generateJwtToken(myUserDetails);
            return ResponseEntity.ok(new LoginResponse(JWT));
        }catch (Exception e){
            return ResponseEntity.ok(new LoginResponse("Error : username or password is incorrect"));
        }
    }
    public User setUserImage(ImageRequest image) {
        User user = getUser();
        System.out.println("fouuuuuuuund===" + user);
        String imgUrl = imageService.uploadImage(image, "usersImages");
        Image savedImage = imageRepository.findByUrl(imgUrl);
        user.getUserProfile().setImage(savedImage);


        return user;


    }
    public User getUser() {
        // return the user object from the user details object from the security context holder
        return ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

    }


    public void softDelete() {

        User user = getUser();
        user.setActivated(false);
         userRepository.save(user);
    }


    public SecureToken resetPassword(String email) {
        SecureToken secureToken = secureTokenService.createToken();
        User user = userRepository.findUserByEmailAddress(email);
        System.out.println("service found user ====> " + user.getUserName());
        secureToken.setUser(user);
        secureTokenService.saveSecureToken(secureToken);


        System.out.println("sending email to " + user.getEmailAddress());
       return secureToken;
    }

    public void resetPasswordActivator(String token, User userObj) {
        SecureToken secureToken = secureTokenService.findByToken(token);
        User user = secureToken.getUser();
        user.setPassword(passwordEncoder.encode(userObj.getPassword()));
        userRepository.save(user);

    }

    public void changePassword(String oldPass, String newPass) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        System.out.println("the useeeer");
        User user = myUserDetails.getUser();
        if (newPass == null) {
            throw new MissingFieldException("New password must not be null");
        }
        try {
            if (passwordEncoder.matches(oldPass, user.getPassword())) {

                user.setPassword(passwordEncoder.encode(newPass));
                userRepository.save(user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
