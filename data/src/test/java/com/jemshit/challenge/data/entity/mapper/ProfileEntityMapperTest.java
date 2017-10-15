package com.jemshit.challenge.data.entity.mapper;

import com.jemshit.challenge.data.entity.web_responses.ProfileEntity;
import com.jemshit.challenge.data.other.DateHelper;
import com.jemshit.challenge.data.test_helper.DataGenerator;
import com.jemshit.challenge.domain.model.ProfileModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

public class ProfileEntityMapperTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    private ProfileEntityMapper profileEntityMapper;
    @Mock DateHelper dateHelper;

    @Before
    public void setUp() throws Exception {
        profileEntityMapper = new ProfileEntityMapper(dateHelper);
    }

    //region Transform
    @Test
    public void transform_shouldReturnValidModel() throws Exception {
        // Assign
        ProfileEntity entity = DataGenerator.createProfileEntity(true);
        ProfileModel profileModel;

        // Act
        profileModel = profileEntityMapper.transform(entity);

        // Assert
        Assert.assertNotNull(profileModel);
        Assert.assertThat(profileModel, is(instanceOf(ProfileModel.class)));
        Assert.assertEquals(profileModel.getName(), entity.getName());
        Assert.assertEquals(profileModel.getAddress(), entity.getAddress());
    }

    @Test
    public void transform_ShouldReturnNullModel() throws Exception {
        // Assign
        ProfileEntity entity = DataGenerator.createProfileEntity(false);
        ProfileModel profileModel;

        // Act
        profileModel = profileEntityMapper.transform(entity);

        // Assert
        Assert.assertNull(profileModel);
    }
    //endregion

    //region Transform List
    @Test
    public void transform_ShouldReturnValidModelList() throws Exception {
        // Assign
        List<ProfileEntity> entityList = DataGenerator.createProfileEntityList(true);
        List<ProfileModel> modelList;

        // Act
        modelList = profileEntityMapper.transform(entityList);

        // Assert
        Assert.assertNotNull(modelList);
        Assert.assertEquals(modelList.get(0).getName(), entityList.get(0).getName());
        Assert.assertEquals(modelList.get(0).getAddress(), entityList.get(0).getAddress());
    }

    @Test
    public void transform_shouldReturnEmptyModelList() throws Exception {
        // Assign
        List<ProfileEntity> entityList = DataGenerator.createProfileEntityList(false);
        List<ProfileModel> modelList;

        // Act
        modelList = profileEntityMapper.transform(entityList);

        // Assert
        Assert.assertTrue(modelList.size() == 0);
    }
    //endregion
}