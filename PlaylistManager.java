import java.util.Scanner;

public class PlaylistManager {
    private static Scanner scanner = new Scanner(System.in);
    private static RecentlyPlayedQueue recentlyPlayed = new RecentlyPlayedQueue();
    private static PlaylistInterface arrayPlaylist = new PlaylistArrayList(recentlyPlayed);
    private static PlaylistInterface linkedPlaylist = new PlaylistLinkedList(recentlyPlayed);
    private static boolean useArray = true;

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addSong();
                    break;
                case 2:
                    insertSong();
                    break;
                case 3:
                    removeSong();
                    break;
                case 4:
                    viewSong();
                    break;
                case 5:
                    getCurrentPlaylist().shuffle();
                    break;
                case 6:
                    getCurrentPlaylist().printPlaylist();
                    break;
                case 7:
                    sortMenu();
                    break;
                case 8:
                    searchMenu();
                    break;
                case 9:
                    recentlyPlayed.printRecentlyPlayed();
                    break;
                case 10:
                    switchPlaylist();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Playlist Manager ---");
        System.out.println("Current Playlist Type: " + (useArray ? "ArrayList" : "LinkedList"));
        System.out.println("1. Add Song");
        System.out.println("2. Insert Song at Position");
        System.out.println("3. Remove Song by Position");
        System.out.println("4. View Song (adds to Recently Played)");
        System.out.println("5. Shuffle Playlist");
        System.out.println("6. Print Playlist");
        System.out.println("7. Sort Playlist");
        System.out.println("8. Search Playlist");
        System.out.println("9. View Recently Played Songs");
        System.out.println("10. Switch Playlist Type");
        System.out.println("0. Exit");
    }

    private static void addSong() {
        Song song = createSong();
        getCurrentPlaylist().addSong(song);
        System.out.println("Song added.");
    }

    private static void insertSong() {
        int index = getIntInput("Enter position: ");
        Song song = createSong();
        getCurrentPlaylist().insertSong(index, song);
    }

    private static void removeSong() {
        int index = getIntInput("Enter position to remove: ");
        getCurrentPlaylist().removeSong(index);
    }

    private static void viewSong() {
        int index = getIntInput("Enter song index to play: ");
        Song song = getCurrentPlaylist().getSong(index);
        if (song != null) {
            System.out.println("Now playing: " + song);
        }
    }

    private static void sortMenu() {
        System.out.println("Sort by:");
        System.out.println("1. Title");
        System.out.println("2. Artist");
        System.out.println("3. Duration");
        System.out.println("4. Play Count");
        int choice = getIntInput("Your choice: ");

        switch (choice) {
            case 1:
                getCurrentPlaylist().sortByTitle();
                break;
            case 2:
                getCurrentPlaylist().sortByArtist();
                break;
            case 3:
                getCurrentPlaylist().sortByDuration();
                break;
            case 4:
                getCurrentPlaylist().sortByPlayCount();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void searchMenu() {
        System.out.println("Search by:");
        System.out.println("1. Title");
        System.out.println("2. Artist");
        System.out.println("3. Genre");
        int choice = getIntInput("Your choice: ");

        switch (choice) {
            case 1:
                String title = getStringInput("Enter title: ");
                int[] positions = getCurrentPlaylist().searchByTitle(title);
                if (positions.length == 0) System.out.println("No results.");
                else for (int pos : positions) System.out.println("Found at index: " + pos);
                break;
            case 2:
                String artist = getStringInput("Enter artist: ");
                for (Song s : getCurrentPlaylist().searchByArtist(artist)) System.out.println(s);
                break;
            case 3:
                String genre = getStringInput("Enter genre: ");
                for (Song s : getCurrentPlaylist().searchByGenre(genre)) System.out.println(s);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void switchPlaylist() {
        useArray = !useArray;
        System.out.println("Switched to " + (useArray ? "ArrayList" : "LinkedList") + " playlist.");
    }

    private static Song createSong() {
        String title = getStringInput("Enter title: ");
        String artist = getStringInput("Enter artist: ");
        int duration = getIntInput("Enter duration (in seconds): ");
        String genre = getStringInput("Enter genre: ");
        return new Song(title, artist, duration, genre);
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static PlaylistInterface getCurrentPlaylist() {
        return useArray ? arrayPlaylist : linkedPlaylist;
}
}  