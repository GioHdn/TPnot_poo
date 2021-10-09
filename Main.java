import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class Main{
    
    public static ArrayList<Guerrier> createTeam(int nbreGuerrier){
        ArrayList<Guerrier> team = new ArrayList<Guerrier>();
        
        double life = 100;
        int attack = 14;
        double efficacity = 0.8;
        ArrayList<String> personality = new ArrayList<String>();
        personality.add("Healer");
        personality.add("Helper");
        personality.add("Alcoholic");
        personality.add("Waiter");
        personality.add("Upgrader");

        for(int i = 0; i < nbreGuerrier; i++){
            int randomNumber = new Random().nextInt(5);
            team.add(new Guerrier(life, attack, efficacity, personality.get(randomNumber)));
        }
        return team;
    }

    public static void main(String args[]){

        int nbreGuerrier=5;  
        ArrayList<Guerrier> team1 = createTeam(nbreGuerrier);
        ArrayList<Guerrier> team2 = createTeam(nbreGuerrier);

        ExecutorService executor = Executors.newFixedThreadPool(nbreGuerrier);
        List<Future<Guerrier>> futures = new ArrayList<>();

        int nbreCombat = (team1.size() > team2.size()) ? team1.size() : team2.size();
        ArrayList<Guerrier> largerTeam = (team1.size() > team2.size()) ? team1 : team2;
        ArrayList<Guerrier> smallerTeam = (team1.size() < team2.size()) ? team1 : team2;

        
        for(int i = 0; i < nbreCombat; i++){
            futures.add(executor.submit(new Combat(team1.get(i), team2.get(i))));
        }

       
        for(int j = nbreCombat - 1; j < largerTeam.size(); j++){
            switch(largerTeam.get(j).getPersonality()){
                case "Helper": 
                    futures.add(executor.submit(new Combat(largerTeam.get(j), smallerTeam.get(new Random().nextInt(smallerTeam.size())))));
                    System.out.println("Ce guerrier attaque un guerrier en combat.");
                    break;
                case "Healer":   
                    largerTeam.get(j).setLife(100);
                    System.out.println("Ce guerrier a régénéré toute sa vie.");
                    break;
                case "Waiter":   
                    largerTeam.get(j).justWaiting();
                    System.out.println("Ce guerrier attend le prochain combat.");
                    break;
                case "Upgrader":  
                    largerTeam.get(j).attackUp();
                    System.out.println("Ce guerrier a amélioré son attaque.");
                    break;
                case "Alcoholic":
                    largerTeam.get(j).celebrate();
                    System.out.println("Ce guerrier a bu trop de bière, il divise son efficacité par 2.");
                    break;
            }
        }
        futures.forEach(
            future -> {
                try {
                    Guerrier vainqueur = future.get();
                    String message = (team1.contains(vainqueur)) ? "Vainqueur de l'équipe 1" : "Vainqueur de l'équipe 2";
                    System.out.println(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        );

        executor.shutdown();
    }
}
