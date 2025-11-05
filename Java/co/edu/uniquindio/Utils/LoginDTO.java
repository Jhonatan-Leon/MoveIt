package Java.co.Utils;

import Java.co.Model.DTO.UserLoginDTO;

public class LoginDTO {
    private LoginDTO (){

    }

    public static UserLoginDTO toDTO(UserLoginDTO userLoginDTO){
        return new UserLoginDTO(
                userLoginDTO.getEmail(),
                userLoginDTO.getPassword()
        );
    }
}
