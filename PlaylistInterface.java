public interface PlaylistInterface {
    void addSong(Song song);
    void insertSong(int index, Song song);
    void removeSong(int index);
    Song getSong(int index);
    void shuffle();
    void printPlaylist();
    void sortByTitle();
    void sortByArtist();
    void sortByDuration();
    void sortByPlayCount();
    int[] searchByTitle(String title);
    Song[] searchByArtist(String artist);
    Song[] searchByGenre(String genre);
    
    RecentlyPlayedQueue getRecentlyPlayed();
}