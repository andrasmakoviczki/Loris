package trash;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FeedRefreshExecutor {

	private String poolName;
	private ThreadPoolExecutor pool;
	private LinkedBlockingDeque<Runnable> queue;

	public FeedRefreshExecutor(final String poolName, int threads, int queueCapacity) {
		this.poolName = poolName;
		pool = new ThreadPoolExecutor(threads, threads, 0, TimeUnit.MILLISECONDS, queue = new LinkedBlockingDeque<Runnable>(queueCapacity) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean offer(Runnable r) {
				Task task = (Task) r;
				if (task.isUrgent()) {
					return offerFirst(r);
				} else {
					return offerLast(r);
				}
			}
		}) {
			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				if (t != null) {
				}
			}
		};
		pool.setRejectedExecutionHandler(new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
				try {
					Task task = (Task) r;
					if (task.isUrgent()) {
						queue.putFirst(r);
					} else {
						queue.put(r);
					}
				} catch (InterruptedException e1) {
				}
			}
		});
	}

	public void execute(Task task) {
		pool.execute(task);
	}

	public static interface Task extends Runnable {
		boolean isUrgent();
	}

	public void shutdown() {
		pool.shutdownNow();
		while (!pool.isTerminated()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}
}

