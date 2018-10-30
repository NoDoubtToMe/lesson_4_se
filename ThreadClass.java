class ThreadClass{
    final Object obj = new Object();
    int flag = 0;

    Thread t1 = new Thread(new Runnable() {
        public void run() {
            synchronized (obj) {
                for (int i = 0; i < 5; i++) {
                    while (flag != 0) {
                        try {
                            obj.wait();//Не могли бы написать здесь полный пример использования @SneakyThrow. Все
                                       //перепробовал. Прописал зависимость ломбока в мавене и все. Может еще
                                      // что-то надо? Главное анотация нормально подцвечивается, но не работает.
                        } catch (InterruptedException e) { System.out.println("Exception"); }
                    }
                    System.out.println("A");
                    flag = 1;
                    obj.notifyAll();
                }
            }
        }
    });

    Thread t2 = new Thread(new Runnable() {
        public void run() {
            synchronized (obj) {
                for (int i = 0; i < 5; i++) {
                    while (flag != 1) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) { System.out.println("Exception"); }
                    }
                    System.out.println("B");
                    flag = 2;
                    obj.notifyAll();
                }
            }
        }
    });

    Thread t3 = new Thread(new Runnable() {
        public void run() {
            synchronized (obj) {
                for (int i = 0; i < 5; i++) {
                    while (flag != 2) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) { System.out.println("Exception"); }
                    }
                    System.out.println("C");
                    flag = 0;
                    obj.notifyAll();
                }
            }
        }
    });

}