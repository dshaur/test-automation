package com.solvd.carina.demo.api.albums;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.config.Configuration;

@Endpoint(url = "${base_url}/albums", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/albums/_get/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetAlbumMethod extends AbstractApiMethodV2 {
    public GetAlbumMethod() {
        replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
    }
}