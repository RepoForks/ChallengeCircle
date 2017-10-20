package com.jemshit.challenge.data.web_service;

import com.jemshit.challenge.data.entity.web_responses.GetProfileListResponseEntity;
import com.jemshit.challenge.data.entity.web_responses.LoginResponseEntity;
import com.jemshit.challenge.data.entity.web_responses.ProfileEntity;
import com.jemshit.challenge.data.other.ConstantsWebService;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET(ConstantsWebService.URL_LOGIN)
    Single<List<LoginResponseEntity>> login(@Query(ConstantsWebService.QUERY_LOGIN_USERNAME) String username,
                                            @Query(ConstantsWebService.QUERY_LOGIN_PASSWORD) String password);

    @GET(ConstantsWebService.URL_GET_PROFILE_LIST)
    Single<List<GetProfileListResponseEntity>> getProfileList(@Query(ConstantsWebService.QUERY_GET_PROFILE_LIST_TOKEN) String token);

    @GET(ConstantsWebService.URL_GET_PROFILE)
    Single<List<ProfileEntity>> getProfile(@Query(ConstantsWebService.QUERY_GET_PROFILE_ID) String profileId);
}
