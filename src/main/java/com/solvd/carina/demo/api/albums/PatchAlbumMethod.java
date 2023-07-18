package com.solvd.carina.demo.api.albums;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${config.env.api_url}/albums/101", methodType = HttpMethodType.PATCH)
@RequestTemplatePath(path = "api/albums/_patch/rq.json")
@ResponseTemplatePath(path = "api/albums/_patch/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class PatchAlbumMethod extends AbstractApiMethodV2 {

    public PatchAlbumMethod() {
    }
}

