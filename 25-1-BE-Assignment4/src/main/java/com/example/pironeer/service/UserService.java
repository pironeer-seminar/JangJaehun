package com.example.pironeer.service;

import com.example.pironeer.dto.request.UserCreateReq;
import com.example.pironeer.entity.User;
import com.example.pironeer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

//    public UserCreateRes create(UserCreateReq req) {
//        User user = User.create(req.getName());
//
//        user = userRepository.save(user); // 생성한 user 객체를 DB에 저장 후, 반환 값을 그대로 user 객체에 넣어줘야 함
//        // user 객체의 id 값은 DB에서 알아서 생성하기에 id 값을 가지고 오려면
//        // DB에 저장한 내용을 한번 더 불러와 user 객체에 저장해야 id 값이 정상적으로 user 객체에 저장되어 user.getId() 메서드 사용 가능!
//
//        return new UserCreateRes(user.getId()); // 응답 dto 안에 user 객체의 id 값을 넣어 반환
//    }

    public Long create(UserCreateReq req) {
        User user = User.create(req.getName());

        user = userRepository.save(user);

        return user.getId(); // 편의를 위해 id를 반환하도록 함
    }
}
