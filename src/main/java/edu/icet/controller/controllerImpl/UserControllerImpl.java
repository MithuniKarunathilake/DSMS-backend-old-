package edu.icet.controller.controllerImpl;

import edu.icet.constants.DrivingSchoolConstant;
import edu.icet.controller.UserController;
import edu.icet.service.UserService;
import edu.icet.util.DrivingSchoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserControllerImpl implements UserController {
    @Autowired
    UserService userService;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            userService.signUp(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return DrivingSchoolUtil.getResponseEntity(DrivingSchoolConstant.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
