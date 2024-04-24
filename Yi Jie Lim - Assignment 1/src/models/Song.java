package models;
import utils.Utilities;
import java.util.Objects;

public class Song {
    //TODO The song id (int songId) is between 1000 to 9999(both inclusive).  Default is 9999.


    //TODO The song name (String name).
    //     Default value is "".
    //     When creating the song, truncate the name to 20 characters.
    //     When updating an existing song, only update the name if it is 20 characters or less.

    //TODO The song's artist (Artist artist)
    //    You should have already written the Artist class
    //     When creating the song, you should have the artist object as a parameter

    //TODO The length of the song in seconds (int length) is between 1 and 600. Default is 1.

    //TODO Add the constructor, Song(int, String, Artist), that adheres to the above validation rules
    //TODO Add the constructor, Song(int, String, String, boolean, int), that adheres to the above validation rules

    //TODO Add a getter and setter for each field, that adheres to the above validation rules


    //TODO Add a generated equals method.


    //TODO The toString should return the string containing each of the field values including the use of the artist's toString()
    private int songId = 9999;
    private String name = "";
    private Artist artist;
    private int length = 1;

    public Song(int songId, String name, Artist artist, int length) {
        setSongId(songId);
        if (name != null) {
            if (name.length() > 20) {
                this.name = name.substring(0, 20);
            } else {
                this.name = name;
            }
        }
        setArtist(new Artist(artist.getArtistName(), artist.isVerified()));
        setLength(length);
    }

    public Song(int songId, String name, String artistName, boolean verified, int length){
        setSongId(songId);
        this.name = Utilities.truncateString(name,20);
        setArtist(new Artist(artistName,verified));;
        setLength(length);
    }

    public void setName(String name){
        if (Utilities.validateStringLength(name, 20)) {
            this.name = name;
        }
    }
    public String getName() {
        return name;
    }
    public void setSongId(int songId) {
        if (Utilities.validRange(songId, 1000, 9999)) {
            this.songId = songId;
        }
    }
    public int getSongId() {
        return songId;
    }
    public void setLength(int length) {
        if ((length >= 1) && (length<=600) ) {
            this.length = length;
        }
    }
    public int getLength() {
        return length;
    }
    public void setArtist(Artist artist){
        this.artist=artist;
    }
    public Artist getArtist() {
        return artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song song)) return false;
        return getSongId() == song.getSongId() && getLength() == song.getLength() && Objects.equals(getName(), song.getName()) && Objects.equals(getArtist(), song.getArtist());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSongId(), getName(), getArtist(), getLength());
    }

    @Override
    public String toString() {
          return songId + name + artist.toString() + length ;
    }
}