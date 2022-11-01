package cdtdemo;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.concurrent.CompletableFuture;

public class DemoActivator implements BundleActivator {

    private CompletableFuture<Void> mainThread;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        mainThread = CompletableFuture.runAsync(() -> {
            try {
                Main.main(new String[0]);
            } catch (RuntimeException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        } );
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        mainThread.join();
        System.exit(0);
    }
}
