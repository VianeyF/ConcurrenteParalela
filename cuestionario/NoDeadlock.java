class Resource {
    private final String name;

    public Resource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class ThreadDemo extends Thread {
    private final Resource resource1;
    private final Resource resource2;

    public ThreadDemo(Resource resource1, Resource resource2) {
        this.resource1 = resource1;
        this.resource2 = resource2;
    }

    @Override
    public void run() {
        synchronized (resource1) {
            System.out.println(Thread.currentThread().getName() + " locked " + resource1.getName());

            try {
                // Simular trabajo
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (resource2) {
                System.out.println(Thread.currentThread().getName() + " locked " + resource2.getName());
            }
        }
    }
}

public class NoDeadlock {
    public static void main(String[] args) {
        Resource resA = new Resource("Resource A");
        Resource resB = new Resource("Resource B");

        ThreadDemo t1 = new ThreadDemo(resA, resB);
        ThreadDemo t2 = new ThreadDemo(resB, resA); // Cambiar el orden aquí podría causar deadlock

        t1.start();
        t2.start();
    }
}