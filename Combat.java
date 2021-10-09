import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

class Combat implements Callable<Guerrier>{

    private Guerrier guerrierA, guerrierB;
    double maxPDV =100;

    public Combat(Guerrier guerrierA, Guerrier guerrierB){
        this.guerrierA = guerrierA;
        this.guerrierB = guerrierB;
    }

    @Override
    public Guerrier call() throws Exception{

        while(true){
            Thread.sleep(ThreadLocalRandom.current().nextInt(200, 500));
            this.guerrierA.attack(guerrierB);
            Thread.sleep(ThreadLocalRandom.current().nextInt(200, 500));
            this.guerrierB.attack(guerrierA);

            if(guerrierA.getLife() <= 0){
                guerrierB.heal(guerrierB.getLife()/100*30);
                if(guerrierB.getLife() > maxPDV) { guerrierB.setLife(maxPDV); }
                return guerrierB;
            }
            else if(guerrierB.getLife() <= 0){
                guerrierA.heal(guerrierA.getLife()/100*30);
                if(guerrierA.getLife() > maxPDV) { guerrierA.setLife(maxPDV); }
                return guerrierA;
            }
        }
    }
}
