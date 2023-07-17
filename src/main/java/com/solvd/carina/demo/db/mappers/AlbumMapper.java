package com.solvd.carina.demo.db.mappers;

import com.solvd.carina.demo.db.models.Album;

public interface AlbumMapper {

    void create(Album album);

    // TODO: maybe add a getByUserId method

    Album findById(long id);

    Album findByAlbumTitle(String title);

    void update(Album album);

    void delete(Album album);

}
