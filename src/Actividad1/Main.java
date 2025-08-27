//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;

public class Main {
    public static void main(String[] args) {
        SocialGraph graph = new SocialGraph();

        graph.addUser("Ana");
        graph.addUser("Luis");
        graph.addUser("Marta");

        graph.follow("Ana", "Luis");
        graph.follow("Luis", "Marta");
        graph.follow("Marta", "Ana");

        System.out.println("Ana sigue a: " + graph.getFollowing("Ana"));
        System.out.println("Luis sigue a: " + graph.getFollowing("Luis"));
        System.out.println("Marta sigue a: " + graph.getFollowing("Marta"));

        System.out.println("Seguidores de Ana: " + graph.getFollowers("Ana"));
        System.out.println("Seguidores de Luis: " + graph.getFollowers("Luis"));
        System.out.println("Seguidores de Marta: " + graph.getFollowers("Marta"));
    }
}

class SocialGraph {
    private final Map<String, List<String>> adjList;

    public SocialGraph() {
        adjList = new HashMap<>();
    }

    public void addUser(String user) {
        adjList.putIfAbsent(user, new ArrayList<>());
    }

    public void follow(String follower, String followed) {
        addUser(follower);
        addUser(followed);
        adjList.get(follower).add(followed);
    }

    public List<String> getFollowing(String user) {
        return adjList.getOrDefault(user, Collections.emptyList());
    }

    public List<String> getFollowers(String user) {
        List<String> followers = new ArrayList<>();
        for (String u : adjList.keySet()) {
            if (adjList.get(u).contains(user)) {
                followers.add(u);
            }
        }
        return followers;
    }
}