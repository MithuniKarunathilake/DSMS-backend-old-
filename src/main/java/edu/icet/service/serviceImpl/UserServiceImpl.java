package edu.icet.service.serviceImpl;

import edu.icet.constants.DrivingSchoolConstant;
import edu.icet.dao.UserDao;
import edu.icet.model.User;
import edu.icet.service.UserService;
import edu.icet.util.DrivingSchoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> resquestMap) {
        log.info("Inside signup {}", resquestMap);
        try {
            if (validateSignUpMap(resquestMap)) {
                User user = userDao.findByEmailId(resquestMap.get("email"));

                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(resquestMap));
                    return DrivingSchoolUtil.getResponseEntity("Successfully Registered", HttpStatus.OK);
                } else {
                    return DrivingSchoolUtil.getResponseEntity("Email already exist.", HttpStatus.BAD_REQUEST);
                }

            } else {
                return DrivingSchoolUtil.getResponseEntity(DrivingSchoolConstant.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return DrivingSchoolUtil.getResponseEntity(DrivingSchoolConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private User getUserFromMap(Map<String, String> resquestMap) {
        User user = new User();
        user.setName(resquestMap.get("name"));
        user.setContactNumber(resquestMap.get("contactNumber"));
        user.setEmail(resquestMap.get("email"));
        user.setPassword(resquestMap.get("password"));
        user.setStatus("false");
        return user;
    }

    private boolean validateSignUpMap(Map<String, String> requestMap) {
        if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber") && requestMap.containsKey("email")
                && requestMap.containsKey("password")) {
            return true;
        }
        return false;
    }
}
