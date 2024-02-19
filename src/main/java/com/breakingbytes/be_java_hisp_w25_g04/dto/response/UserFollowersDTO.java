package com.breakingbytes.be_java_hisp_w25_g04.dto.response;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserFollowersDTO {
    int user_id;
    String user_name;
    List<UserDTO> followers;
}
