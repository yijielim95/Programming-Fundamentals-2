package main;

import controllers.Playlist;
import models.Artist;
import models.Song;
import utils.ScannerInput;
import utils.Utilities;

public class Driver {

    //TODO Define an object of the Playlist here.  It should be declared private.gi
    private Playlist playlist;
    public static void main(String[] args) {
        new Driver();
    }

    //TODO Refer to the tutors instructions for building this class and for the menu.  You are free to deviate in any way
    //     from the main.Driver menu that is in the tutors instructions, once you have these included:
    //     (with tests still compiling)
    //       - CRUD on Playlist
    //       - Search facility (for Songs)
    //       - Reports
    //       - Persistence
    // Note:  This is the ONLY class that can talk to the user i.e. have System.out.print and Scanner reads in it.
    public Driver(){
        runMenu();
    }


    //----------------------------------------------------------------------------
    // Private methods for displaying the menu and processing the selected options
    //----------------------------------------------------------------------------
    private int mainMenu(){
        if(playlist == null){
            String playlistName = ScannerInput.readNextLine("Enter the Playlist Name: ");
            String description = ScannerInput.readNextLine("Enter the Playlist Description: ");
            playlist = new Playlist(playlistName,description);
        }
        return ScannerInput.readNextInt("""
                -------------------------------------------------------------------
                |                         Song App                                |
                -------------------------------------------------------------------
                | SONG MENU                                                       |
                |   1) Add a song                                                 |
                |   2) List the Songs                                             |
                |   3) List the Songs From The Verified Artists                   |
                |   4) List the Songs that Longer than 180 secs                   |                  
                |   5) List the Songs From A Given Artist                         |
                |   6) Update a song                                              |
                |   7) Update a verified status                                   |
                |   8) Delete a Song                                              |
                -------------------------------------------------------------------
                | SEARCH MENU                                                     |
                |   9) Find Song By Name                                          |
                |   10) Find Song By Code(Song Id)                                |
                |   11) Search Song By Artist Name                                |
                -------------------------------------------------------------------
                | REPORT MENU                                                     |
                |   12) Number Of Songs                                           |
                |   13) Number of Songs Shorter than 180 secs                     |
                |   14) Display the average songs length                          |
                |   15) Display the total playlist songs length                   |
                -------------------------------------------------------------------
                | PERSISTENCE MENU                                                |  
                |   16) Add a like to playlist                                    |
                |   17) Load songs to songs.xml                                   |  
                |   18) Save songs from songs.xml                                 |  
                |   0)  Exit                                                      |  
                -------------------------------------------------------------------
                ==>>  """);
    }
    private void runMenu() {
        int option = mainMenu();

        while (option != 0) {

            switch (option) {
                case 1 -> addSong();
                case 2 -> printSongs();
                case 3 -> printSongsFromVerifiedArtist();
                case 4 -> printSongsLongerThan180();
                case 5 -> printSongsOfGivenArtists();
                case 6 -> updateSongs();
                case 7 -> updateVerifiedStatus();
                case 8 -> deleteSong();
                case 9 -> findSongbyAName();
                case 10 -> findSongByCode();
                case 11 -> searchSongByArtistName();
                case 12 -> printNumberofSongs();
                case 13 -> printNumberofShortSongs();
                case 14 -> printAverageLength();
                case 15 -> printTotalPlaylistLength();
                case 16 -> addLikeToPlaylist();
                case 17 -> load();
                case 18 -> save();

                default -> System.out.println("Invalid option entered: " + option);
            }

            //pause the program so that the user can read what we just printed to the terminal window
            ScannerInput.readNextLine("\nPress enter key to continue...");

            //display the main menu again
            option = mainMenu();
        }
    }
    //------------------------------------
    // Private methods for CRUD on Song
    //------------------------------------
    private void addSong(){
        String name = ScannerInput.readNextLine("Enter the Song Name: ");
        int songId = ScannerInput.readNextInt("Enter the Song Id: ");
        int length = ScannerInput.readNextInt("Enter the Song Length: ");
        String artistName = ScannerInput.readNextLine("Enter the Artist Name: ");
        String verifiedArtist = ScannerInput.readNextLine("Is this artist a verified artist(y/n): ");
        boolean verified = Utilities.YNtoBoolean(verifiedArtist);

        boolean isAdded = playlist.addSong(new Song(songId, name, new Artist(artistName,verified), length));
        if(isAdded){
            System.out.println("Song added Successfully");
        }
        else{
            System.out.println("No song Added");
        }
    }

    private void printSongs(){
        if(playlist.listSongs() != null){
            System.out.println("List of Songs are: ");
            System.out.println(playlist.listSongs());
        }
    }

    private void printSongsFromVerifiedArtist(){
        System.out.println("List of Songs from Verified Artist are: ");
        System.out.println(playlist.listSongsFromVerifiedArtists());
    }

    private void printSongsLongerThan180(){
        System.out.println("List of Songs that Longer than 180 secs: ");
        System.out.println(playlist.listSongsLongerThan(180));
    }

    private void printSongsOfGivenArtists(){
        System.out.println("List of Songs from Given Artist are: ");
        System.out.println(playlist.listOfSongsOfArtist("Taylor Swift"));
    }

    private void updateSongs(){
        printSongs();
        if(playlist.numSongs()>0){
            int indexToUpdate = ScannerInput.readNextInt("Enter the index of the song to update ==> ");
            if(playlist.isValidIndex1(indexToUpdate)){
                String name = ScannerInput.readNextLine("Enter the Song Name: ");
                int songId = ScannerInput.readNextInt("Enter the Song Id: ");
                int length = ScannerInput.readNextInt("Enter the Song Length: ");
                String artistName = ScannerInput.readNextLine("Enter the Artist Name: ");
                String verifiedArtist = ScannerInput.readNextLine("Is this artist a verified artist(y/n): ");
                boolean verified = Utilities.YNtoBoolean(verifiedArtist);

                if(playlist.updateSong(indexToUpdate, new Song(songId, name, new Artist(artistName, verified), length))){
                    System.out.println("Update Successfully");
                }
                else{
                    System.out.println("Update FAIL!!!!!!");
                }
            }
            else{
                System.out.println("SORRY, THERE ARE NO SONGS FOR THIS INDEX NUMBER");
            }
        }
    }

    private void updateVerifiedStatus(){
        printSongsFromVerifiedArtist();
        if(playlist.numSongs()>0){
            int indexToUpdate = ScannerInput.readNextInt("Enter the index of the song to update ==> ");
            if(playlist.isValidIndex1(indexToUpdate)){
                String verifiedArtist = ScannerInput.readNextLine("Is this artist a verified artist(y/n): ");
                boolean verified = Utilities.YNtoBoolean(verifiedArtist);
                if(playlist.updateVerifiedStatus(indexToUpdate,verified) != null){
                    System.out.println("Update Successfully");
                }
                else{
                    System.out.println("Update FAIL!!!!!!");
                }
            }
            else{
                System.out.println("SORRY, THERE ARE NO SONGS FOR THIS INDEX NUMBER");
            }
        }

    }

    private void deleteSong(){
        printSongs();
        if(playlist.numSongs() > 0){
            int indexToDelete = ScannerInput.readNextInt("Enter the index of the song to delete ==> ");
            Song songToDelete = playlist.deleteSong(indexToDelete);
            if (songToDelete != null){
                System.out.println("Delete successful! Deleted song: " + songToDelete.getName() );
            }
            else{
                System.out.println("Delete Not Successful!");
            }
        }
    }
    //-----------------------------------------------------------------
    //  Private methods for Search facility
    //-----------------------------------------------------------------
    private void findSongbyAName(){
        String name = ScannerInput.readNextLine("Enter the artist name to find their songs: ");
        Song foundSong = playlist.findSong(name);

        if(foundSong != null){
            System.out.println("Found Song  " + foundSong.getName());
        }
        else{
            System.out.println("There is no songs is " + name);
        }
    }

    private void findSongByCode(){
        int songId = ScannerInput.readNextInt("Enter the song Id: ");
        Song foundSong = playlist.findSongByCode(songId);

        if (foundSong != null) {
            System.out.println("Found Song: " +  foundSong.getName());
        } else {
            System.out.println("There is no song found by id: " + songId); // Corrected to use songId
        }
    }

    private void searchSongByArtistName(){
        String artistName = ScannerInput.readNextLine("Enter the artist name of the song to search: ");
        String searchResults = playlist.searchSongsByArtistName(artistName);
        System.out.println(searchResults);
    }

    //-----------------------------
    //  Private methods for Reports
    // ----------------------------
    private void printNumberofSongs(){
        System.out.println("The number of songs is: ");
        System.out.println(playlist.numSongs());
    }
    private void printNumberofShortSongs(){
        System.out.println("The number of short songs is: ");
        System.out.println(playlist.numberofShortSongs());
    }
    private void printAverageLength(){
        double averageSong = playlist.getAverageSongLength();
        if(averageSong != -1){
            System.out.println("The average of the song length is: " + averageSong);
        }
        else{
            System.out.println("There are no songs in the playlist.");
        }
    }

    private void printTotalPlaylistLength(){
        int totalLength = playlist.getTotalPlaylistLength();
        if(totalLength != -1){
            System.out.println("The total of the playlist length is: " + totalLength);
        }
        else{
            System.out.println("There are no songs in the playlist.");
        }
    }


    //---------------------------------
    //  Private methods for Persistence
    // --------------------------------
    private void addLikeToPlaylist(){
        char likes = ScannerInput.readNextChar("Like this Playlist? Why don't you give it a like(L/H)?");
        if((likes=='l')||(likes=='L')){
            System.out.println("Thank you! Your like has been added to the playlist.");
        }
        else{
            System.out.println("Why don't you give me a like? I'm crying now");
        }
    }

    //TODO Add a method, load().  The return type is void.
    //    This method uses the XStream component to deserialise the playList object and their associated artists from
    //    an XML file into the Songs array list.
    private void load(){
        try{
            playlist.load();
        }catch (Exception e){
            System.err.println("Error reading from file: " + e);
        }
    }

    //TODO Add a method, save().  The return type is void.
    //    This method uses the XStream component to serialise the playList object and their associated artists to
    //    an XML file.
    private void save() {
        try {
            playlist.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }
}