import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
public class WordLadder {
    public int ladderLength(String startWord, String endWord, List<String> wordList){
        if (!wordList.contains(endWord)) return 0;
        HashMap<String, Boolean> Vmap = new HashMap<>();
        for (int i =0; i < wordList.size(); i++) {
            Vmap.put(wordList.get(i), false);
        }
        Queue<String> queue = new LinkedList<>();
        int length = 1;
        queue.add(startWord);
        Vmap.put(startWord, true);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i=0; i<size; i++){
                String w = queue.poll();
                if (w.equals(endWord)) return length;
                wordMatch(w,Vmap,queue);
            }
            length ++;
        }
        return 0;
    }
    public void wordMatch(String w, HashMap<String, Boolean> Vmap, Queue<String> queue){
        for (int i=0; i < w.length(); i++){
            char[] word = w.toCharArray();
            for (int j=0; j<26; j++){
                char c = (char) ('a' + j);
                word[i] = c;
                String s = String.valueOf(word);
                if (Vmap.containsKey(s) && Vmap.get(s) == false){
                    queue.add(s);
                    Vmap.put(s, true);
                }
            }
        }
    }
    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder();
        String startWord = "hit";
        String endWord = "cog";
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
        int shortestLadderLength = wordLadder.ladderLength(startWord, endWord, wordList);
        System.out.println("Shortest ladder length: " + shortestLadderLength);
    }
}
