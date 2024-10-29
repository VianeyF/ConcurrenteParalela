clss MonitorDemo{
    private int counter = 0;

    synchronized void incrementar(){
        counter++;
        System.out.println("Contador:"+counter);
    }

    synchronized int obtenerValor(){
        return counter;
    }

    class HiloIncrementdor extends Thread{
        private MonitorDemo monitor;

        HiloIncrementador(MonitorDemo monitor){
            this.monitor = monitor;
        }

        public void run(){
            for(int i=0; i<5; i++){
                monitor.incrementar();
                try{
                    Thread.sleep(millis:100);
                }catch(InterruptedException e){
                    System.out.println(e);
                }
            }
        }
    }

    public class Ejemplo Monitor{
        public static void main (String[] args){
            MonitorDemo monitor = new MonitorDemo();
            HiloIncrementdor hilo1 = new HiloIncrementdor(monitor); 
            HiloIncrementdor hilo2 = new HiloIncrementdor(monitor); 

            hilo1.start();
            hilo2.start();

            try{
                hilo1.join();
                hilo2.join();
            }catch(InterruptedException e){
                System.out.println(e);
            }
    
            System.out.println("Valor final del contador:" + monitor.obtenerValor());

        }
    }
    

}