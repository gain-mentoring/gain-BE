package com.gain.mentoring.user.domain;

import com.gain.mentoring.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "이름은 필수입니다.")
    @Column(nullable = false)
    private String name;

    @Pattern(
            regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",
            message = "올바른 이메일 형식이 아닙니다."
    )
    @NotBlank(message = "이메일은 필수입니다.")
    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OauthProvider oauthProvider;

    @Column(nullable = true, unique = true)
    private String oauthId; // 소셜 로그인 고유 ID

    @Column(nullable = true, unique = true)
    private String userName; // 이메일 가입시 사용하는 ID

    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-])[A-Za-z\\d!@#$%^&*()_+=-]{8,16}$",
            message = "비밀번호는 8~16자의 영문, 숫자, 특수문자를 포함해야 합니다."
    )
    @Column(nullable = true)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Pattern(
            regexp = "^(19|20)\\d{2}\\.(0[1-9]|1[0-2])\\.(0[1-9]|[12][0-9]|3[01])$",
            message = "유효한 생년월일 형식이 아닙니다."
    )
    @NotBlank(message = "생년월일은 필수입니다.")
    @Column(nullable = false)
    private String birthday;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Pattern(
            regexp = "^(01[016789])[ -]?(\\d{3,4})[ -]?(\\d{4})$",
            message = "유효한 휴대전화번호 형식이 아닙니다."
    )
    @NotBlank(message = "휴대전화 번호는 필수입니다.")
    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = true)
    private String profileImgUrl;
}
