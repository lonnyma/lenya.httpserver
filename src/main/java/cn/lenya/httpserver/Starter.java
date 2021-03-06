/* Copyright (c) 2018 白羊人工智能在线技术. All rights reserved.
 * http://www.byond.cn
 */
package cn.lenya.httpserver;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Starter {

	private static final Logger Log = LoggerFactory.getLogger(Starter.class);
	private static HttpServer httpserver = null;

	public static void main(String[] args) {

		Log.info("jetty 正在启动....");
		try {
			init();
			start();
		} catch (Exception ex) {
			stop();
		}

	}

	/**
	 * 初始化数据
	 */
	private static void init() {

		String addr = "127.0.0.1";
		int port = 8888;
		InetSocketAddress intenetAddress = new InetSocketAddress(addr, port);
		httpserver = new HttpServer(intenetAddress);
		httpserver.setHandler(httpserver.createHandlers());
		httpserver.setStopAtShutdown(true);
	}

	/**
	 * 功能描述：启动server
	 */
	public static void start() {
		try {
			httpserver.start();
			Log.info("jetty 启动成功！");
			httpserver.join();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 功能描述：停止server
	 */
	public static void stop() {
		try {
			httpserver.stop();
			System.out.println("jetty 停止成功！");
			httpserver.destroy();
			httpserver = null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
