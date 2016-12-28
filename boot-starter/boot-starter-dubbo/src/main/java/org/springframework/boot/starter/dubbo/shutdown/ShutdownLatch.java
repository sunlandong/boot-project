package org.springframework.boot.starter.dubbo.shutdown;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class ShutdownLatch implements ShutdownLatchMBean{
	
	protected AtomicBoolean running = new AtomicBoolean();
	
	public long checkIntervalInSeconds = 10;
	
	private String domain = "net.liuxuejia";
	
	
	public ShutdownLatch() {
	}
	
	public ShutdownLatch(String domain) {
		super();
		this.domain = domain;
	}

	public void await() throws Exception {
		if(running.compareAndSet(false, true)) {
			MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
			mBeanServer.registerMBean(this, new ObjectName(domain, "name", "ShutdownLatch"));
			while (running.get()) {
				TimeUnit.SECONDS.sleep(checkIntervalInSeconds);
			}
		}
	}

	@Override
	public String shutdown() {
		if(running.compareAndSet(true, false)) {
			return "shutdown signal sent ,shutting down ...";
		} else {
			return "shutdown signal had been sent ,no need again and again";
		}
	}
	
	public static void main(String[] args) throws Exception {
		ShutdownLatch shutdownLatch = new ShutdownLatch("net.liuxuejia");
		shutdownLatch.await();
	}
}
