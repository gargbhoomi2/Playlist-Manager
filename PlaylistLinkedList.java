public class PlaylistLinkedList implements PlaylistInterface {
    
    // Inner Node class representing each song in the linked list
    private class Node {
        Song song;      // Song object
        Node next;      // Reference to the next node in the list

        Node(Song song) {
            this.song = song; // Initialize song in the node
            this.next = null; // Initialize next node as null
        }
    }

    private Node head; // Head of the linked list (start of the playlist)
    private int size;  // Size of the playlist (number of songs)
    private RecentlyPlayedQueue recentlyPlayed; // Queue to store recently played songs

    // Constructor to initialize the playlist with the recently played queue
    public PlaylistLinkedList(RecentlyPlayedQueue recentlyPlayed) {
        this.head = null;  // Initialize head as null (empty playlist)
        this.size = 0;     // Initialize size as 0
        this.recentlyPlayed = recentlyPlayed; // Set the recently played queue
    }

    // Adds a song to the end of the playlist
    public void addSong(Song song) {
        Node newNode = new Node(song); // Create a new node for the song
        if (head == null) {            // If the playlist is empty
            head = newNode;            // Set head to the new node
        } else {
            Node current = head;       // Start from the head node
            while (current.next != null) {  // Traverse until the last node
                current = current.next;
            }
            current.next = newNode;     // Add the new node at the end
        }
        size++;  // Increase the size of the playlist
    }

    // Inserts a song at a specific index in the playlist
    public void insertSong(int index, Song song) {
        if (index < 0 || index > size) { // Check if the index is valid
            System.out.println("Invalid index.");
            return;
        }
        Node newNode = new Node(song);  // Create a new node for the song
        if (index == 0) {               // If inserting at the head
            newNode.next = head;         // Set the new node's next to current head
            head = newNode;              // Set the new node as the new head
        } else {
            Node prev = getNode(index - 1); // Get the node just before the index
            newNode.next = prev.next;        // Set the new node's next to the next of previous node
            prev.next = newNode;             // Set the previous node's next to the new node
        }
        size++;  // Increase the size of the playlist
    }

    // Removes a song at the given index from the playlist
    public void removeSong(int index) {
        if (index < 0 || index >= size) { // Check if the index is valid
            System.out.println("Invalid index.");
            return;
        }
        if (index == 0) {  // If removing the head
            head = head.next;  // Set head to the next node
        } else {
            Node prev = getNode(index - 1);  // Get the node before the one to be removed
            prev.next = prev.next.next;       // Skip the node to be removed
        }
        size--;  // Decrease the size of the playlist
    }

    // Retrieves a song at a given index and increments its play count
    public Song getSong(int index) {
        if (index < 0 || index >= size) { // Check if the index is valid
            System.out.println("Invalid index.");
            return null;
        }
        Node node = getNode(index);  // Get the node at the index
        node.song.incrementPlayCount();  // Increment the play count of the song
        recentlyPlayed.addRecentlyPlayed(node.song);  // Add song to recently played queue
        return node.song;  // Return the song
    }

    // Prints the entire playlist with song index and details
    public void printPlaylist() {
        if (size == 0) {  // Check if the playlist is empty
            System.out.println("Playlist is empty.");
            return;
        }
        Node current = head;  // Start from the head node
        int index = 0;
        while (current != null) {  // Traverse the entire playlist
            System.out.println(index++ + ": " + current.song);  // Print song index and details
            current = current.next;  // Move to the next node
        }
    }

    // Helper method to get the node at a specific index
    private Node getNode(int index) {
        Node current = head;  // Start from the head node
        for (int i = 0; i < index; i++) {  // Traverse to the specified index
            current = current.next;
        }
        return current;  // Return the node at the index
    }

    // Sorting methods using bubble sort for various attributes

    public void sortByTitle() {
        sort((a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));  // Sort by title
    }

    public void sortByArtist() {
        sort((a, b) -> a.getArtist().compareToIgnoreCase(b.getArtist()));  // Sort by artist
    }

    public void sortByDuration() {
        sort((a, b) -> a.getDuration() - b.getDuration());  // Sort by song duration
    }

    public void sortByPlayCount() {
        sort((a, b) -> b.getPlayCount() - a.getPlayCount());  // Sort by play count (descending)
    }

    // Helper method for sorting using bubble sort with custom comparator
    private void sort(SongComparator comp) {
        Song[] array = toArray();  // Convert the linked list to an array for sorting
        for (int i = 0; i < array.length - 1; i++) {  // Bubble sort logic
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (comp.compare(array[j], array[j + 1]) > 0) {
                    Song temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        fromArray(array);  // Convert the sorted array back to a linked list
    }

    // Converts the linked list to an array
    private Song[] toArray() {
        Song[] array = new Song[size];  // Create an array to store the songs
        Node current = head;
        int i = 0;
        while (current != null) {  // Traverse the playlist
            array[i++] = current.song;  // Add each song to the array
            current = current.next;
        }
        return array;  // Return the array
    }

    // Converts an array back to a linked list
    private void fromArray(Song[] array) {
        head = null;  // Reset the head of the playlist
        size = 0;     // Reset the size
        for (Song song : array) {  // Add each song from the array to the playlist
            addSong(song);
        }
    }

    // Interface to compare songs for sorting
    interface SongComparator {
        int compare(Song a, Song b);  // Compare two songs
    }

    // Search for songs by title and return the indices
    public int[] searchByTitle(String title) {
        int[] result = new int[size];  // Array to store the indices of matching songs
        int count = 0;
        Node current = head;
        int index = 0;
        while (current != null) {  // Traverse the playlist
            if (current.song.getTitle().equalsIgnoreCase(title)) {  // Check if the title matches
                result[count++] = index;  // Add the index to the result array
            }
            index++;
            current = current.next;  // Move to the next node
        }
        return result;  // Return the indices of matching songs
    }

    // Search for songs by artist and return them
    public Song[] searchByArtist(String artist) {
        Song[] result = new Song[size];  // Array to store matching songs
        int count = 0;
        Node current = head;
        while (current != null) {  // Traverse the playlist
            if (current.song.getArtist().equalsIgnoreCase(artist)) {  // Check if the artist matches
                result[count++] = current.song;  // Add the song to the result array
            }
            current = current.next;  // Move to the next node
        }
        return result;  // Return the matching songs
    }

    // Search for songs by genre and return them
    public Song[] searchByGenre(String genre) {
        Song[] result = new Song[size];  // Array to store matching songs
        int count = 0;
        Node current = head;
        while (current != null) {  // Traverse the playlist
            if (current.song.getGenre().equalsIgnoreCase(genre)) {  // Check if the genre matches
                result[count++] = current.song;  // Add the song to the result array
            }
            current = current.next;  // Move to the next node
        }
        return result;  // Return the matching songs
    }

    // Shuffle the songs in the playlist
    public void shuffle() {
        for (int i = 0; i < size; i++) {  // Iterate through the playlist
            int j = (int) (Math.random() * size);  // Pick a random index
            Song temp = getNode(i).song;  // Swap the songs at indices i and j
            getNode(i).song = getNode(j).song;
            getNode(j).song = temp;
        }
    }

    @Override
    public RecentlyPlayedQueue getRecentlyPlayed() {
        return recentlyPlayed;  // Return the recently played queue
    }
}

