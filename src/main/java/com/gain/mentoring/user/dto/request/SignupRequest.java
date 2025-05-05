package com.gain.mentoring.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequest {

    @Schema(description = "이름", example = "홍길동")
    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @Schema(description = "아이디", example = "gain123")
    @NotBlank(message = "아이디는 필수입니다.")
    private String userName;

    @Schema(description = "이메일", example = "test1234@google.com")
    @Pattern(
            regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",
            message = "올바른 이메일 형식이 아닙니다."
    )
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;


    @Schema(description = "비밀번호", example = "Test1234!")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-])[A-Za-z\\d!@#$%^&*()_+=-]{8,16}$",
            message = "비밀번호는 8~16자의 영문, 숫자, 특수문자를 포함해야 합니다."
    )
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @Schema(description = "생년월일", example = "2000.01.01")
    @Pattern(
            regexp = "^(19|20)\\d{2}\\.(0[1-9]|1[0-2])\\.(0[1-9]|[12][0-9]|3[01])$",
            message = "유효한 생년월일 형식이 아닙니다."
    )
    @NotBlank(message = "생년월일은 필수입니다.")
    private String birthday;

    @Schema(description = "성별", example = "MALE")
    @NotBlank(message = "성별은 필수입니다.")
    private String gender;

    @Schema(description = "휴대전화번호", example = "010-1234-5678")
    @Pattern(
            regexp = "^(01[016789])[ -]?(\\d{3,4})[ -]?(\\d{4})$",
            message = "유효한 휴대전화번호 형식이 아닙니다."
    )
    @NotBlank(message = "휴대전화번호는 필수입니다.")
    private String phoneNumber;
}
