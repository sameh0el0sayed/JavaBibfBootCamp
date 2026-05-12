package com.ga.deliverysystem.Service;


import com.ga.deliverysystem.Dto.request.ImageRequest;
import com.ga.deliverysystem.Dto.request.LoginRequest;
import com.ga.deliverysystem.Dto.response.LoginResponse;
import com.ga.deliverysystem.Exception.InformationExistException;
import com.ga.deliverysystem.Model.ImageModel;
import com.ga.deliverysystem.Model.SecureTokenModel;
import com.ga.deliverysystem.Model.UserModel;
import com.ga.deliverysystem.Repository.ImageRepository;
import com.ga.deliverysystem.Repository.UserProfileRepository;
import com.ga.deliverysystem.Repository.UserRepository;
import com.ga.deliverysystem.Security.JWTUtils;
import com.ga.deliverysystem.Security.MyUserDetails;
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
    private  final EmailService emailService;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private MyUserDetails myUserDetails;
     @Autowired
    public UserService(UserRepository userRepository, UserProfileRepository userProfileRepository, SecureTokenService secureTokenService, ImageService imageService, ImageRepository imageRepository,
                       @Lazy PasswordEncoder passwordEncoder, EmailService emailService,
                       JWTUtils jwtUtils,
                       @Lazy AuthenticationManager authenticationManager,
                       @Lazy MyUserDetails myUserDetails) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.secureTokenService = secureTokenService;
        this.imageService = imageService;
        this.imageRepository = imageRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.myUserDetails = myUserDetails;
    }
    public UserModel createUser(UserModel userModelObject){
        System.out.println("Service Calling createUser ==> ");
        if(!userRepository.existsByEmailAddress(userModelObject.getEmailAddress())){
            userModelObject.setPassword(passwordEncoder.encode(userModelObject.getPassword()));

            emailService.sendEmail(
                    userModelObject.getEmailAddress(),
                    "Welcome "+ userModelObject.getUserName(),
                    "Welcome to ower system"
            );
            return userRepository.save(userModelObject);
        }
        else
        {
            throw new InformationExistException("User with email address " + userModelObject.getEmailAddress() + " already exists.");
        }
    }

    public UserModel findUserByEmailAddress(String email){
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
            throw new RuntimeException(e);
            //return ResponseEntity.ok(new LoginResponse("Error : username or password is incorrect"));
        }
    }
    public UserModel setUserImage(ImageRequest image) {
        UserModel userModel = getUser();
        System.out.println("found===" + userModel);
        String imgUrl = imageService.uploadImage(image, "usersImages");
        ImageModel savedImageModel = imageRepository.findByUrl(imgUrl);
        userModel.getUserProfile().setImage(savedImageModel);
        userRepository.save(userModel);

        return userModel;
    }
    public UserModel getUser() {
        // return the user object from the user details object from the security context holder
        return ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

    }


    public void softDelete() {

        UserModel userModel = getUser();
        userModel.setActivated(false);
         userRepository.save(userModel);
    }


    public void resetPassword(String email) {
        SecureTokenModel secureTokenModel = secureTokenService.createToken();
        UserModel userModel = userRepository.findUserByEmailAddress(email);
        System.out.println("service found user ====> " + userModel.getUserName());
        secureTokenModel.setUser(userModel);
        secureTokenService.saveSecureToken(secureTokenModel);


        System.out.println("sending email to " + userModel.getEmailAddress());
        emailService.sendEmail(
                userModel.getEmailAddress(),
                "Reset Password",
                "Click here to reset your password: " + secureTokenModel.getToken()
        );
    }

    public void resetPasswordActivator(String token, UserModel userModelObj) {
        SecureTokenModel secureTokenModel = secureTokenService.findByToken(token);
        UserModel userModel = secureTokenModel.getUser();
        userModel.setPassword(passwordEncoder.encode(userModelObj.getPassword()));
        userRepository.save(userModel);

    }

    public void changePassword(String oldPass, String newPass) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        System.out.println("the user");
        UserModel userModel = myUserDetails.getUser();

        try {
            if (passwordEncoder.matches(oldPass, userModel.getPassword())) {

                userModel.setPassword(passwordEncoder.encode(newPass));
                userRepository.save(userModel);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

//    public void validate(String token) {
//        SecureToken secureToken = secureTokenService.findByToken(token);
//        User user = secureToken.getUser();
//        user.setActivated(true);
//        userRepository.save(user);
//    }
}
