package com.solvd.carina.demo;

import com.solvd.carina.demo.api.albums.GetAlbumMethod;
import com.solvd.carina.demo.api.albums.PatchAlbumMethod;
import com.solvd.carina.demo.api.albums.PostAlbumMethod;
import com.zebrunner.carina.api.apitools.validation.JsonCompareKeywords;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import io.restassured.response.Response;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

/**
 * This sample shows how create REST API tests.
 *
 * @author dshaur
 */
public class AlbumsApiTest implements IAbstractTest {


    @Test()
    @MethodOwner(owner = "dshaur")
    public void testCreateAlbum() {
        PostAlbumMethod api = new PostAlbumMethod();
        api.setProperties("api/albums/album.properties");
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "dshaur")
    public void testCreateAlbumMissingSomeFields() {
        PostAlbumMethod api = new PostAlbumMethod();
        api.setProperties("api/albums/album.properties");
        api.getProperties().remove("title");
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "dshaur")
    public void testGetAlbums() {
        GetAlbumMethod getAlbumMethod = new GetAlbumMethod();
        getAlbumMethod.callAPIExpectSuccess();
        getAlbumMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getAlbumMethod.validateResponseAgainstSchema("api/albums/_get/rs.schema");
    }


    // Test PATCH, first use POST call to create album and then use PATCH
    @Test()
    @MethodOwner(owner = "dshaur")
    public void testPatchAlbum() {
        // Step 1: Create an album using POST call
        PostAlbumMethod postApi = new PostAlbumMethod();
        postApi.setProperties("api/albums/album.properties");
        Response postResponse = postApi.callAPIExpectSuccess();

        // Step 2: Get the ID of the created album
        String postId = postResponse.jsonPath().getString("id");


        // Step 3: Update the created album using PATCH call
        PatchAlbumMethod patchAlbumMethod = new PatchAlbumMethod();
        patchAlbumMethod.replaceUrlPlaceholder("id", postId);
        patchAlbumMethod.setProperties("api/albums/album.properties");
        patchAlbumMethod.callAPIExpectSuccess();
        patchAlbumMethod.validateResponse();
    }
}
