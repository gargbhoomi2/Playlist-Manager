public class Song {
    private String title;
    private String artist;
    private int duration;  // in seconds
    private String genre;
    private int playCount;

    public Song(String title, String artist, int duration, String genre) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.genre = genre;
        this.playCount = 0;  // starts at 0
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }

    public String getGenre() {
        return genre;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void incrementPlayCount() {
        playCount++;
    }

    @Override
    public String toString() {
        return title + " by " + artist + " | Duration: " + duration + "s | Genre: " + genre + " | Plays: " + playCount;
    }
}
