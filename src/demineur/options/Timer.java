package demineur.options;
/*
 * Timer.java
 *
 */
 
 
/**
 * Timer base sur le javax.swing.Timer et permet de connaitre le temps reel 
 * ecoule. Quand le Timer est lance, la methode run() est appelee successivement 
 * selon le delai specifie.
 *
 * @author Yann D'ISANTO
 */
abstract public class Timer {
    
    public static final int DEFAULT_DELAY = 100;
    private javax.swing.Timer timer;
    private long start;
    private long previousTop;
    private long totalElapsed;
    private boolean reset;
    private boolean running;
    
    /**
     * Instancie un Timer avec un delai par defaut de 100 millisecondes.
     */
    public Timer() {
        this(DEFAULT_DELAY);
    }
    
    /**
     * Instancie un Timer avec le delai specifie.
     * @param delay delai en milliseconde.
     */
    public Timer(int delay) {
        reset = false;
        running = false;
        timer = new javax.swing.Timer(delay,
                new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                long newTop = System.currentTimeMillis();
                long elapsed = newTop - previousTop;
                totalElapsed += elapsed;
                run(elapsed);
                previousTop = newTop;
            }
        });
    }
    
    /**
     * Retourne le temps du compteur.
     * @return le temps du compteur.
     */
    public long getTime() {
        return totalElapsed;
    }
    
    /**
     * Methode appelee par le timer quand celui-ci tourne. 
     * @param delay delai reel depuis le precedent appel.
     */
    abstract public void run(long delay);
    
    /**
     * Demarre le Timer et le compteur de temps.
     */
    public void start() {
        if(!running) {
            start = System.currentTimeMillis();
            previousTop = start;
            timer.start();
            reset = false;
            running = true;
        }
    }
    
    /**
     * Arrete le Timer et le compteur de temps.
     */
    public void stop() {
        if(running) {
            timer.stop();
            running = false;
        }
    }
    
    /**
     * Arrete le Timer et remet le compteur de temps a zero.
     */
    public void reset() {
        if(!reset) {
            stop();
            totalElapsed = 0;
            reset = true;
        }
    }
    
    /**
     * Indique si le Timer tourne.
     * @return true si le Timer tourne.
     */
    public boolean isRunning() {
        return running;
    }
    /**
     * Indique si le compteur de temps a ete remis a zero et 
     * n'a pas ete redemarre.
     * @return true si le compteur de temps a ete remis a zero 
     * et n'a pas ete redemarre.
     */
    public boolean isReset() {
        return reset;
    }
 
    /**
     * Retourne le delai du timer, le nombre de milliseconde entre 
     * deux appels a la methode run().
     *@return delai.
     */
    public int getDelay() {
        return timer.getDelay();
    }
 
    /**
     * Fixe le delai du timer, le nombre de milliseconde entre 
     * deux appels a la methode run().
     *@param delay delai.
     */
    public void setDelay(int delay) {
        timer.setDelay(delay);
    }
}