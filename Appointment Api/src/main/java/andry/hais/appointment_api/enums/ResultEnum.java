package andry.hais.appointment_api.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    VALID_ERROR(50, "Wrong information"),
    USER_NOT_FOUNT(40, "User is not found!"),

    TIME_IS_ALREADY_RESERVED(30, "This time is already reserved!"),
    TEACHER_HAS_NO_TIME(20, "The teacher does not work at this time!");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
