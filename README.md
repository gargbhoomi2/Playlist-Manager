# PlaylistManager-Java

## Project Overview
Playlist Manager is a **Java command-line program** that lets users create and manage music playlists.  
It demonstrates **manual implementations of ArrayList, LinkedList, and a queue** to track recently played songs.  
The project focuses on **core Java, object-oriented design, and data structures**, while providing an interactive CLI for users.

---

## Features
- Add, insert, remove, and view songs in a playlist.  
- Shuffle playlists manually without built-in utilities.  
- Sort playlists by title, artist, duration, or play count.  
- Search for songs by title, artist, or genre.  
- Keep track of the **last 10 recently played songs** using a queue.  
- Switch between **ArrayList and LinkedList implementations** dynamically.  

---

## File Overview
- `Song.java` – Represents a song with title, artist, duration, genre, and play count.  
- `PlaylistInterface.java` – Defines the common playlist operations.  
- `PlaylistArrayList.java` / `PlaylistLinkedList.java` – Implements playlist using ArrayList or LinkedList.  
- `RecentlyPlayedQueue.java` – Tracks recently played songs using a custom queue.  
- `PlaylistManager.java` – CLI interface to interact with playlists.  
- `TestPlaylistManager.java` – Contains unit tests for all major functionalities.  

---

## To Run
Open the project in a **Java IDE** (preferably **Eclipse**).  
- **Run `TestPlaylistManager.java`** to automatically test all features, including adding, removing, shuffling, sorting, searching, and recently played songs  
- **Run `PlaylistManager.java`** to explore the playlist interactively via the CLI.
