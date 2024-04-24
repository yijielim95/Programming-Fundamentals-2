package models;


import utils.Utilities;

import java.util.Objects;

public class Artist {
    //TODO The artist name (String artistName)  in the system is entered by the user.
    //     Default value is "".
    //     When creating the Artist, truncate the name to 15 characters.
    //     When updating an existing Artist, only update the name if it is 15 characters or less.

    //TODO The verified status (boolean verified)  Default is false.


    //TODO Add the constructor, Artist(String, boolean), that adheres to the above validation rules


    //TODO Add a getter and setter for each field, that adheres to the above validation rules


    //TODO Add a generated equals method.


    //TODO The toString should return the string in this format:
    //      Taylor Swift is a verified artist  OR
    //      Shane Hennessy is not a verified artist
    private String artistName = "" ;
    private boolean verified = false;

    public Artist (String artistName, boolean verified){
        if(artistName != null){
            if (artistName.length() > 15){
                this.artistName = artistName.substring(0,15);
            }
            else{
                this.artistName = artistName;
            }
        }

        setVerified(verified);
    }

    void setArtistName(String artistName){
        if(artistName != null){
            if(artistName.length()<=15){
                this.artistName = artistName;
            }
        }
    }
    public void setVerified(boolean verified){
        this.verified = verified;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getArtistName() {
        return artistName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist artist)) return false;
        return isVerified() == artist.isVerified() && Objects.equals(getArtistName(), artist.getArtistName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArtistName(), isVerified());
    }

    @Override
    public String toString() {
        return artistName + " " + Utilities.booleanToYN(verified);
    }
}
