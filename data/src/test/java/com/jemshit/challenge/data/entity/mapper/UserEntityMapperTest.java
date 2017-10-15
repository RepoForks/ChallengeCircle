package com.jemshit.challenge.data.entity.mapper;

import com.jemshit.challenge.data.entity.web_responses.UserEntity;
import com.jemshit.challenge.data.test_helper.DataGenerator;
import com.jemshit.challenge.domain.model.UserModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

public class UserEntityMapperTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    private UserEntityMapper userEntityMapper;

    @Before
    public void setUp() throws Exception {
        userEntityMapper = new UserEntityMapper();
    }

    //region Transform
    @Test
    public void transform_shouldReturnValidModel() throws Exception {
        // Assign
        UserEntity entity = DataGenerator.createUserEntity(true);
        UserModel UserModel;

        // Act
        UserModel = userEntityMapper.transform(entity);

        // Assert
        Assert.assertNotNull(UserModel);
        Assert.assertThat(UserModel, is(instanceOf(UserModel.class)));
        Assert.assertEquals(UserModel.getProfileId(), entity.getId());
        Assert.assertEquals(UserModel.getAddress(), entity.getAddress());
    }

    @Test
    public void transform_ShouldReturnNullModel() throws Exception {
        // Assign
        UserEntity entity = DataGenerator.createUserEntity(false);
        UserModel UserModel;

        // Act
        UserModel = userEntityMapper.transform(entity);

        // Assert
        Assert.assertNull(UserModel);
    }
    //endregion

    //region Transform List
    @Test
    public void transform_ShouldReturnValidModelList() throws Exception {
        // Assign
        List<UserEntity> entityList = DataGenerator.createUserEntityList(true);
        List<UserModel> modelList;

        // Act
        modelList = userEntityMapper.transform(entityList);

        // Assert
        Assert.assertNotNull(modelList);
        Assert.assertEquals(modelList.get(0).getProfileId(), entityList.get(0).getId());
        Assert.assertEquals(modelList.get(0).getAddress(), entityList.get(0).getAddress());
    }

    @Test
    public void transform_shouldReturnEmptyModelList() throws Exception {
        // Assign
        List<UserEntity> entityList = DataGenerator.createUserEntityList(false);
        List<UserModel> modelList;

        // Act
        modelList = userEntityMapper.transform(entityList);

        // Assert
        Assert.assertTrue(modelList.size() == 0);
    }
    //endregion

}