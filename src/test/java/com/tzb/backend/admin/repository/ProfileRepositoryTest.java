package com.tzb.backend.admin.repository;

import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProfileRepositoryTest {

    @Autowired
    private ProfileRepository profileRepository;

    @Test
    void findByUser_Id() {
        System.out.println(JSON.toJSON(profileRepository.findAllByNicknameIsLike("%1%")));
    }
}
