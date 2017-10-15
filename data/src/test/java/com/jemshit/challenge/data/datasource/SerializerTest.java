package com.jemshit.challenge.data.datasource;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.jemshit.challenge.data.test_helper.DataGenerator;
import com.jemshit.challenge.data.entity.web_responses.ProfileEntity;
import com.jemshit.challenge.data.entity.web_responses.UserEntity;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

// This is integration test, since Gson is not mocked
public class SerializerTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Rule public ExpectedException expectedException = ExpectedException.none();
    private Serializer serializer;

    @Before
    public void setUp() throws Exception {
        serializer = new Serializer(new Gson());
    }

    // region Serialize, Deserialize
    @Test
    public void shouldSerializeAndDeserialize() throws Exception {
        // Assign
        UserEntity userEntity = DataGenerator.createUserEntity(true);
        String json;
        // Act
        json = serializer.serialize(userEntity, UserEntity.class);
        UserEntity deserialized = serializer.deserialize(json, UserEntity.class);

        // Assert
        Assert.assertThat(deserialized, CoreMatchers.is(CoreMatchers.instanceOf(UserEntity.class)));
        Assert.assertEquals(userEntity.getName(), deserialized.getName());
        Assert.assertEquals(userEntity.getId(), deserialized.getId());
    }

    @Test
    public void serialize_shouldThrowException() throws Exception {
        // Assign
        UserEntity userEntity = DataGenerator.createUserEntity(true);

        // Assert
        expectedException.expect(IllegalArgumentException.class);

        // Act
        serializer.serialize(userEntity, ProfileEntity.class); // wrong class type is given

    }

    @Test
    public void deserialize_shouldThrowException() throws Exception {
        // Assign
        String wrongSyntax = "{wrong syntax}";

        // Assert
        expectedException.expect(JsonSyntaxException.class);

        // Act
        serializer.deserialize(wrongSyntax, UserEntity.class); // wrong class type is given

    }
    //endregion

    // region Serialize List, Deserialize List
    @Test
    public void shouldSerializeAndDeserializeList() throws Exception {
        // Assign
        List<UserEntity> userEntityList = DataGenerator.createUserEntityList(true);
        String json;
        Type type = new TypeToken<ArrayList<UserEntity>>() {
        }.getType();

        // Act
        json = serializer.serializeList(userEntityList, type);
        List<UserEntity> deserializedList = serializer.deserializeList(json, type);

        // Assert
        Assert.assertNotNull(deserializedList);
        Assert.assertEquals(userEntityList.get(0).getName(), deserializedList.get(0).getName());
        Assert.assertEquals(userEntityList.get(0).getId(), deserializedList.get(0).getId());
    }

    @Test
    public void serializeList_shouldThrowException() throws Exception {
        // Assign
        List<UserEntity> userEntityList = DataGenerator.createUserEntityList(true);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();

        // Assert
        expectedException.expect(ClassCastException.class);

        // Act
        serializer.serializeList(userEntityList, type);

    }

    @Test
    public void deserializeList_shouldThrowException() throws Exception {
        // Assign
        String wrongSyntax = "{wrong syntax}";
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();

        // Assert
        expectedException.expect(JsonSyntaxException.class);

        // Act
        serializer.deserializeList(wrongSyntax, type);

    }
    //endregion

}