package com.example.user.computerassembly;

/**
 * Created by User on 11/15/2018.
 */

public class Artist {
    String artistId;
    String artistName;
    String artistGenre;
    String artistBrand;
    String artistQuantity;
    String artistNumber;
    String artistAddress;

    public Artist()
    {

    }

    public Artist(String artistId, String artistName, String artistGenre, String artistBrand, String artistQuantity, String artistNumber, String artistAddress) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistGenre = artistGenre;
        this.artistBrand = artistBrand;
        this.artistQuantity = artistQuantity;
        this.artistNumber = artistNumber;
        this.artistAddress = artistAddress;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistGenre() {
        return artistGenre;
    }

    public void setArtistGenre(String artistGenre) {
        this.artistGenre = artistGenre;
    }

    public String getArtistBrand() {
        return artistBrand;
    }

    public void setArtistBrand(String artistBrand) {
        this.artistBrand = artistBrand;
    }

    public String getArtistQuantity() {
        return artistQuantity;
    }

    public void setArtistQuantity(String artistQuantity) {
        this.artistQuantity = artistQuantity;
    }

    public String getArtistNumber() {
        return artistNumber;
    }

    public void setArtistNumber(String artistNumber) {
        this.artistNumber = artistNumber;
    }

    public String getArtistAddress() {
        return artistAddress;
    }

    public void setArtistAddress(String artistAddress) {
        this.artistAddress = artistAddress;
    }
}
