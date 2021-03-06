/* Copyright (c) 2018 白羊人工智能在线技术. All rights reserved.
 * http://www.byond.cn
 */
package cn.lenya.httpserver;

import java.net.InetSocketAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

public class HttpServer extends Server {

	private static final String WEB_XML = "WEB-INF/web.xml";

	public HttpServer() {

	}

	public HttpServer(InetSocketAddress intenetAddress) {
		super(intenetAddress);

	}

	/**
	 * 功能描述
	 * 
	 * @return
	 */
	public HandlerCollection createHandlers() {
		WebAppContext _ctx = new WebAppContext();
		_ctx.setContextPath("/");
		_ctx.setWar(getShadedWarUrl());

		List<Handler> _handlers = new ArrayList<Handler>();
		_handlers.add(_ctx);

		HandlerList _contexts = new HandlerList();
		_contexts.setHandlers(_handlers.toArray(new Handler[0]));

		HandlerCollection _result = new HandlerCollection();
		_result.setHandlers(new Handler[] { _contexts });
		return _result;
	}

	public URL getResource(String aResource) {
		return Thread.currentThread().getContextClassLoader().getResource(aResource);
	}

	private String getShadedWarUrl() {
		String _urlStr = null;
		try {
			_urlStr = getResource(WEB_XML).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _urlStr.substring(0, _urlStr.length() - 15);
	}

	public ThreadPool createThreadPool(int minThreads, int maxThreads) {
		// for your environment - this is an example only
		QueuedThreadPool _threadPool = new QueuedThreadPool();
		_threadPool.setMinThreads(minThreads);
		_threadPool.setMaxThreads(maxThreads);
		return _threadPool;
	}

}
