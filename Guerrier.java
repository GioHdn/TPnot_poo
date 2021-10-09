public class Guerrier{

    private double life; // Points de vie
    private int attack; // Points de dégats 
    private double efficacity; // Taux d'efficacité 
    private String personality; // Trait de caractère

    public Guerrier(double life, int attack, double efficacity, String personality){
        this.life=life;
        this.attack=attack;
        this.efficacity=efficacity;
        this.personality=personality;
    }
    
    // GETTER ET SETTER 

    public double getLife(){return life;}
    public void setLife(double life){this.life = life;}

    public int getAttack(){return attack;}
    public void setAttack(int attack){this.attack = attack;}

    public double getEfficacity(){return efficacity;}
    public void setEfficacity(double efficacity){this.efficacity = efficacity;}

    public String getPersonality(){return personality;}
    public void setPersonality(String personality){this.personality = personality;}

    public void attack(Guerrier adversaire){  // Endommage son adversaire 
        if(Math.random() < this.efficacity){
            adversaire.setLife(adversaire.getLife() - this.attack);
        }
    }

    public void heal(double life){    // Healing method
        this.life += life;
    }

    public void justWaiting(){} // Attend pacifiquement la prochaine manche

    public void celebrate(){  // Fête sa victoire en buvant un peu trop de bières.
        this.efficacity = this.efficacity/2;
    }

    public void attackUp(){   //Améliore son arme et augmente ses points d'attaque pour les prochains combats
        this.attack += this.attack+Math.floor(Math.random() * 4) + 1; ;
    }
}
