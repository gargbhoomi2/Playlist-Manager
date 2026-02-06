public class RecentlyPlayedQueue {
    private class Node {
        Song song;
        Node next;

        Node(Song song) {
            this.song = song;
        }
    }

    private Node head;
    private Node tail;
    private int size;
    private final int MAX_SIZE = 10;

    public RecentlyPlayedQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    public void addRecentlyPlayed(Song song) {
        Node newNode = new Node(song);

        if (size == 0) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;

        if (size > MAX_SIZE) {
            head = head.next;
            size--;
        }
    }

    public void printRecentlyPlayed() {
        if (size == 0) {
            System.out.println("No recently played songs.");
            return;
        }

        Node curr = head;
        int index = 1;
        while (curr != null) {
            System.out.println(index++ + ". " + curr.song);
            curr = curr.next;
        }
    }
}