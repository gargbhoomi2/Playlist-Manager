public class PlaylistArrayList implements PlaylistInterface {
    private Song[] songs;
    private int size;
    private RecentlyPlayedQueue recentlyPlayed;

    public PlaylistArrayList(RecentlyPlayedQueue recentlyPlayed) {
        this.songs = new Song[10];
        this.size = 0;
        this.recentlyPlayed = recentlyPlayed;
    }

    public void addSong(Song song) {
        ensureCapacity();
        songs[size++] = song;
    }

    public void insertSong(int index, Song song) {
        if (index < 0 || index > size) {
            System.out.println("Invalid index.");
            return;
        }
        ensureCapacity();
        for (int i = size; i > index; i--) {
            songs[i] = songs[i - 1];
        }
        songs[index] = song;
        size++;
    }

    public void removeSong(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Invalid index.");
            return;
        }
        for (int i = index; i < size - 1; i++) {
            songs[i] = songs[i + 1];
        }
        songs[--size] = null;
    }

    public Song getSong(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Invalid index.");
            return null;
        }
        songs[index].incrementPlayCount();
        recentlyPlayed.addRecentlyPlayed(songs[index]);
        return songs[index];
    }

    public void printPlaylist() {
        if (size == 0) {
            System.out.println("Playlist is empty.");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.println(i + ": " + songs[i]);
        }
    }

    private void ensureCapacity() {
        if (size >= songs.length) {
            Song[] newSongs = new Song[songs.length * 2];
            System.arraycopy(songs, 0, newSongs, 0, songs.length);
            songs = newSongs;
        }
    }

    // Sorting methods (using bubble sort)
    public void sortByTitle() {
        bubbleSort((a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));
    }

    public void sortByArtist() {
        bubbleSort((a, b) -> a.getArtist().compareToIgnoreCase(b.getArtist()));
    }

    public void sortByDuration() {
        bubbleSort((a, b) -> a.getDuration() - b.getDuration());
    }

    public void sortByPlayCount() {
        bubbleSort((a, b) -> b.getPlayCount() - a.getPlayCount());
    }

    private void bubbleSort(SongComparator comparator) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (comparator.compare(songs[j], songs[j + 1]) > 0) {
                    Song temp = songs[j];
                    songs[j] = songs[j + 1];
                    songs[j + 1] = temp;
                }
            }
        }
    }

    interface SongComparator {
        int compare(Song a, Song b);
    }

    public int[] searchByTitle(String title) {
        int[] result = new int[size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (songs[i].getTitle().equalsIgnoreCase(title)) {
                result[count++] = i;
            }
        }
        return result;
    }

    public Song[] searchByArtist(String artist) {
        Song[] result = new Song[size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (songs[i].getArtist().equalsIgnoreCase(artist)) {
                result[count++] = songs[i];
            }
        }
        return result;
    }

    public Song[] searchByGenre(String genre) {
        Song[] result = new Song[size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (songs[i].getGenre().equalsIgnoreCase(genre)) {
                result[count++] = songs[i];
            }
        }
        return result;
    }

    public int getSize() {
        return size;
    }

    public void shuffle() {
        for (int i = 0; i < size; i++) {
            int j = (int) (Math.random() * size);
            Song temp = songs[i];
            songs[i] = songs[j];
            songs[j] = temp;
        }
    }
    @Override
    public RecentlyPlayedQueue getRecentlyPlayed() {
        return recentlyPlayed;
    }
}