public class TestPlaylistManager {
    public static void main(String[] args) {
        System.out.println("Running Playlist Tests...\n");

        testAddAndPrintSongsArrayList();
        testInsertSongArrayList();
        testRemoveSongArrayList();
        testSearchByTitle();
        testRecentlyPlayedQueue();

        System.out.println("\nAll tests completed.");
    }

    public static void testAddAndPrintSongsArrayList() {
        System.out.println("Test: Add and Print Songs (ArrayList)");

        PlaylistArrayList playlist = new PlaylistArrayList(new RecentlyPlayedQueue());
        Song song1 = new Song("Imagine", "John Lennon", 183, "Rock");
        Song song2 = new Song("Bohemian Rhapsody", "Queen", 354, "Rock");

        playlist.addSong(song1);
        playlist.addSong(song2);

        playlist.printPlaylist();

        System.out.println("Expected: 2 songs listed.\n");
    }

    public static void testInsertSongArrayList() {
        System.out.println("Test: Insert Song at Position (ArrayList)");

        PlaylistArrayList playlist = new PlaylistArrayList(new RecentlyPlayedQueue());
        playlist.addSong(new Song("Song A", "Artist A", 200, "Pop"));
        playlist.addSong(new Song("Song C", "Artist C", 300, "Pop"));

        playlist.insertSong(1, new Song("Song B", "Artist B", 250, "Pop"));
        playlist.printPlaylist();

        System.out.println("Expected order: A, B, C\n");
    }

    public static void testRemoveSongArrayList() {
        System.out.println("Test: Remove Song (ArrayList)");

        PlaylistArrayList playlist = new PlaylistArrayList(new RecentlyPlayedQueue());
        playlist.addSong(new Song("Song 1", "Artist", 100, "Pop"));
        playlist.addSong(new Song("Song 2", "Artist", 100, "Pop"));
        playlist.removeSong(0);

        playlist.printPlaylist();
        System.out.println("Expected: Only Song 2 listed.\n");
    }

    public static void testSearchByTitle() {
        System.out.println("Test: Search By Title (ArrayList)");

        PlaylistArrayList playlist = new PlaylistArrayList(new RecentlyPlayedQueue());
        playlist.addSong(new Song("Thunderstruck", "AC/DC", 292, "Rock"));
        playlist.addSong(new Song("Thunder", "Imagine Dragons", 187, "Pop"));

        int[] positions = searchByTitle(playlist, "Thunder");

        System.out.print("Found at positions: ");
        for (int pos : positions) {
            System.out.print(pos + " ");
        }
        System.out.println("\nExpected: 1\n");
    }

    // ✅ Fixed method: checks size before accessing songs
    public static int[] searchByTitle(PlaylistArrayList playlist, String title) {
        int[] positions = new int[playlist.getSize()];
        int count = 0;
        for (int i = 0; i < playlist.getSize(); i++) {
            Song s = playlist.getSong(i); // increments play count
            if (s != null && s.getTitle().toLowerCase().contains(title.toLowerCase())) {
                positions[count++] = i;
            }
        }
        int[] result = new int[count];
        System.arraycopy(positions, 0, result, 0, count);
        return result;
    }

    // ✅ Updated to use PlaylistArrayList for correct play count
    public static void testRecentlyPlayedQueue() {
        System.out.println("Test: Recently Played Queue");

        RecentlyPlayedQueue queue = new RecentlyPlayedQueue();
        PlaylistArrayList playlist = new PlaylistArrayList(queue);

        for (int i = 1; i <= 12; i++) {
            playlist.addSong(new Song("Song " + i, "Artist", 120, "Pop"));
        }

        // Simulate playback for songs 3 to 12
        for (int i = 2; i < 12; i++) {
            playlist.getSong(i); // increments play count and adds to recently played
        }

        queue.printRecentlyPlayed();
        System.out.println("Expected: Songs 3 to 12 (only last 10 played)\n");
    }
}
