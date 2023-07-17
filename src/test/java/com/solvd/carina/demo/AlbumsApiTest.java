package com.solvd.carina.demo;

import com.solvd.carina.demo.api.albums.GetAlbumMethod;
import com.solvd.carina.demo.api.albums.PostAlbumMethod;
import com.zebrunner.carina.api.apitools.validation.JsonCompareKeywords;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

/**
 * This sample shows how create REST API tests.
 *
 * @author dshaur
 */
public class AlbumsApiTest implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    @Test()
    @MethodOwner(owner = "dshaur")
    public void testCreateAlbum() throws Exception {
        PostAlbumMethod api = new PostAlbumMethod();
        api.setProperties("api/albums/album.properties");
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "dshaur")
    public void testCreateAlbumMissingSomeFields() throws Exception {
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
    public void testPatchAlbum() throws Exception {
        // Step 1: Create an album using POST call
        PostAlbumMethod postApi = new PostAlbumMethod();
        postApi.setProperties("api/albums/album.properties");
        Response postResponse = postApi.callAPIExpectSuccess();
        postApi.validateResponse();

        // Step 2: Get the ID of the created album
        String albumId = postResponse.jsonPath().getString("0.id");

        // Step 3: Update the created album using PATCH call
        PatchAlbumMethod patchAlbumMethod = new PatchAlbumMethod();
        patchAlbumMethod.setProperties("api/albums/album.properties");
        patchAlbumMethod.getProperties().put("id", albumId); // Set the ID of the album to be updated
        patchAlbumMethod.callAPIExpectSuccess();
        patchAlbumMethod.validateResponse();
    }

}
