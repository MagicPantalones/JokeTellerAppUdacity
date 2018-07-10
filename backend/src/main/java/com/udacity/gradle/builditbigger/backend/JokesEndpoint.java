package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import io.magics.jokewizard.JokeSmith;

@Api(
        name = "jokeApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class JokesEndpoint {

    @ApiMethod(name = "visitJoker")
    public MyBean visitJoker() {
        MyBean response = new MyBean();
        JokeSmith jokeSmith = new JokeSmith();
        response.setData(jokeSmith.getJoke());

        return response;
    }

}
